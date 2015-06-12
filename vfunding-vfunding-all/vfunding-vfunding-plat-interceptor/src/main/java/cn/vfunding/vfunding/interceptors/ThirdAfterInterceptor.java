package cn.vfunding.vfunding.interceptors;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import cn.vfunding.common.framework.jms.activemq.JmsSender;
import cn.vfunding.common.framework.third.annotations.NeedAfterInterceptor;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.thirdplat.dao.ThirdRelationshipMapper;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.vo.UserTenderWeekActionResultVO;
import cn.vfunding.vfunding.biz.week.vo.UserTenderWeekActionVO;
import cn.vfunding.vfunding.common.module.activemq.message.CjdBorrowMessage;
import cn.vfunding.vfunding.common.module.activemq.message.CjdTenderMessage;
import cn.vfunding.vfunding.common.module.activemq.message.CjdUserMessage;
import cn.vfunding.vfunding.common.module.activemq.message.useraction.UserTenderMessage;
/**
 * 第三方合作伙伴在用户资金发生变化时及时同步资金信息的后通知拦截器 
 * @author liuyijun
 *
 */
public class ThirdAfterInterceptor implements AfterReturningAdvice {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ThirdRelationshipMapper thirdRelationshipMapper;
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	@Autowired
	private IBorrowTenderService borrowTenderService;

	@Resource(name = "jms.sender")
	private JmsSender jmsSender;

	@Override
	public void afterReturning(Object arg0, Method method, Object[] arg2,
			Object arg3) throws Throwable {
		NeedAfterInterceptor need = method
				.getAnnotation(NeedAfterInterceptor.class);
		// arg0:拦截方法的返回值
		// arg2:拦截方法的参数
		// arg3:被拦截的类对象
		if (EmptyUtil.isNotEmpty(need)) {// 充值
			if (need.value().equals("recharge")) {
				AccountRecharge rec = (AccountRecharge) arg2[0];
				if (rec.getStatus().intValue() == 1) {// 充值成功
					Integer userId = rec.getUserId();
					ThirdRelationship ship = this.thirdRelationshipMapper
							.selectByUserIdType(userId, 1);
					if (EmptyUtil.isNotEmpty(ship)) {
						// 组建MQ消息
						// 发送MQ异步消息
						CjdUserMessage message = new CjdUserMessage();
						message.setType(3);// 3-余额同步
						message.setUserId(userId);
						message.setUaccount(null);
						this.jmsSender.sendAsynchronousMessage(message);
					}
				}
			} else if (need.value().equals("takeCash")) {// 提现
				AccountCash cash = (AccountCash) arg2[0];
				Integer userId = cash.getUserId();
				ThirdRelationship ship = this.thirdRelationshipMapper
						.selectByUserIdType(userId, 1);
				if (EmptyUtil.isNotEmpty(ship)) {
					AccountCashVO cashVO = (AccountCashVO) arg2[1];
					// 组建MQ消息
					// 发送MQ异步消息
					CjdUserMessage message = new CjdUserMessage();
					message.setType(3);// 3-余额同步
					message.setUserId(userId);
					message.setUaccount(null);
					this.jmsSender.sendAsynchronousMessage(message);
				}

			} else if (need.value().equals("borrowExamine")) {// 初审
				Borrow borrow = (Borrow) arg2[0];
				// 组建MQ消息
				// 更新borrow信息的MQ
				CjdBorrowMessage borrowMsg = new CjdBorrowMessage();
				borrowMsg.setBorrowId(borrow.getId());
				borrowMsg.setType(1);
				this.jmsSender.sendAsynchronousMessage(borrowMsg);
				// 发送MQ异步消息
			} else if (need.value().equals("borrowTender")) {// 投资
				UserTenderActionVO actionVO = (UserTenderActionVO) arg2[0];// 方法第一个参数
				Integer userId = actionVO.getUserId();
				ThirdRelationship ship = this.thirdRelationshipMapper
						.selectByUserIdType(userId, 1);
				UserTenderActionResultVO resultVO = (UserTenderActionResultVO) arg0;// 方法放回值
				if (EmptyUtil.isNotEmpty(ship)) {
					Calendar cal = Calendar.getInstance();
					if (EmptyUtil.isEmpty(ship.getUserTracktime())) {
						cal.setTime(new Date());
					} else {
						cal.setTime(ship.getUserTracktime());
					}
					cal.add(Calendar.HOUR, 24);
					if (ship.getUserType() == 0
							|| (ship.getUserType() == 1 && cal.getTime().after(
									new Date()))) {
						
						// 发送投资MQ
						CjdTenderMessage tenderMsg = new CjdTenderMessage();
						tenderMsg.setUserId(userId);
						tenderMsg.setBorrowId(actionVO.getBorrowId());
						tenderMsg.setTenderId(resultVO.getTenderId());
						tenderMsg.setType(1);// 1代表单例投资
						this.jmsSender.sendAsynchronousMessage(tenderMsg);

						// 发送余额MQ
						CjdUserMessage message = new CjdUserMessage();
						message.setType(3);// 3-余额同步
						message.setUserId(userId);
						message.setUaccount(null);
						this.jmsSender.sendAsynchronousMessage(message);
					}
				}
				// 更新borrow信息的MQ
				CjdBorrowMessage borrowMsg = new CjdBorrowMessage();
				borrowMsg.setBorrowId(actionVO.getBorrowId());
				borrowMsg.setType(1);// 1为更新borrow可借金额
				this.jmsSender.sendAsynchronousMessage(borrowMsg);
				//用户投资MQ消息 
				UserTenderMessage userTenderMsg = new UserTenderMessage();
				userTenderMsg.setUserId(userId);
				userTenderMsg.setBorrowId(actionVO.getBorrowId());
				userTenderMsg.setTenderId(resultVO.getTenderId());
				this.jmsSender.sendAsynchronousMessage(userTenderMsg);
				
			} else if (need.value().equals("borrwoVerify")) {// 复审
				FinalVerifyVO verifyVO = (FinalVerifyVO) arg2[0];// 方法参数
				String result = (String) arg0;// 方法返回值

				// 更新所有用户投资的MQ
				CjdTenderMessage tenderMsg = new CjdTenderMessage();
				tenderMsg.setBorrowId(verifyVO.getBorrowId());
				tenderMsg.setType(2);// 2代表循环更新投资信息
				if (verifyVO.getStatus() == 3) {
					tenderMsg.setTenderStatus(0);
				} else {
					tenderMsg.setTenderStatus(1);
				}
				this.jmsSender.sendAsynchronousMessage(tenderMsg);

				// 更新borrow信息的MQ
				CjdBorrowMessage borrowMsg = new CjdBorrowMessage();
				borrowMsg.setBorrowId(verifyVO.getBorrowId());
				borrowMsg.setType(1);
				this.jmsSender.sendAsynchronousMessage(borrowMsg);

				if (verifyVO.getStatus() != 3) {
					// 发送余额MQ
					CjdUserMessage message = new CjdUserMessage();
					message.setType(5);// 5-集合处理
					message.setBorrowId(verifyVO.getBorrowId());
					this.jmsSender.sendAsynchronousMessage(message);
				}

			} else if (need.value().equals("pepayFeelBorrow")) {// 体验金还款
				String repaymentId = (String) arg2[0];
				// 组建MQ消息
				// 发送MQ异步消息
				CjdUserMessage message = new CjdUserMessage();
				message.setType(4);// 4-集合处理
				message.setRepaymentId(repaymentId);
				this.jmsSender.sendAsynchronousMessage(message);

			} else if (need.value().equals("pepayBorrow")) {// 真实还款
				String repaymentId = (String) arg2[0];
				// 组建MQ消息
				// 发送MQ异步消息
				CjdUserMessage message = new CjdUserMessage();
				message.setType(4);// 4-集合处理
				message.setRepaymentId(repaymentId);
				this.jmsSender.sendAsynchronousMessage(message);
				// 更新所有用户投资的MQ
				CjdTenderMessage tenderMsg = new CjdTenderMessage();
				tenderMsg.setRepaymentId(Integer.parseInt(repaymentId));
				tenderMsg.setType(3);// 3更新所有用户的持有金额
				this.jmsSender.sendAsynchronousMessage(tenderMsg);
			} else if (need.value().equals("weekTender")){ //7天理财投资
				UserTenderWeekActionVO vo = (UserTenderWeekActionVO)arg2[0];
				Integer userId = vo.getUserId();
				ThirdRelationship ship = this.thirdRelationshipMapper
						.selectByUserIdType(userId, 1);
				if (EmptyUtil.isNotEmpty(ship)) {
					Calendar cal = Calendar.getInstance();
					if (EmptyUtil.isEmpty(ship.getUserTracktime())) {
						cal.setTime(new Date());
					} else {
						cal.setTime(ship.getUserTracktime());
					}
					cal.add(Calendar.HOUR, 24);
					if (ship.getUserType() == 0
							|| (ship.getUserType() == 1 && cal.getTime().after(
									new Date()))) {
						UserTenderWeekActionResultVO rvo = (UserTenderWeekActionResultVO)arg0;
						// 发送投资MQ
						CjdTenderMessage tenderMsg = new CjdTenderMessage();
						tenderMsg.setUserId(userId);
						tenderMsg.setBorrowId(vo.getWeekId());
						tenderMsg.setTenderId(rvo.getTenderId());
						tenderMsg.setType(4);// TODO
						this.jmsSender.sendAsynchronousMessage(tenderMsg);
						// 发送余额MQ
						CjdUserMessage message = new CjdUserMessage();
						message.setType(3);// 余额同步
						message.setUserId(userId);
						message.setUaccount(null);
						this.jmsSender.sendAsynchronousMessage(message);
					}
				}
			}else if(need.value().equals("weekRepayment")){//7天理财还款
				Integer repaymentId = (Integer) arg2[0];
				// 组建MQ消息
				// 发送MQ异步消息
				CjdUserMessage message = new CjdUserMessage();
				message.setType(6);// 4-集合处理
				message.setRepaymentId(repaymentId.toString());
				this.jmsSender.sendAsynchronousMessage(message);
				// 更新所有用户投资的MQ
				CjdTenderMessage tenderMsg = new CjdTenderMessage();
				tenderMsg.setRepaymentId(repaymentId);
				tenderMsg.setType(5);// 3更新所有用户的持有金额
				this.jmsSender.sendAsynchronousMessage(tenderMsg); 
			}else if (need.value().equals("weekExamine")) {// 7天理财发标
				Week week = (Week) arg2[0];
				String subType = (String) arg2[2];
				// 组建MQ消息
				// 更新borrow信息的MQ
				if(subType.equals("ok")){
					CjdBorrowMessage borrowMsg = new CjdBorrowMessage();
					borrowMsg.setBorrowId(week.getId());
					borrowMsg.setType(2);
					this.jmsSender.sendAsynchronousMessage(borrowMsg);
					// 发送MQ异步消息
				}
			}
			
		}
	}

	public static void main(String[] args) {
		String date = "2014-10-15 15:24:00";
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1;

		try {
			// Calendar cal = Calendar.getInstance();
			// cal.setTime(dateformat.parse(date));
			// cal.add(Calendar.HOUR, 24);
			//
			// d1 = new Date();
			// if (d1.before(cal.getTime())) {
			// System.out.println("(d1.before(cal.getTime())) ");
			// }
			// if (d1.after(cal.getTime())) {
			// System.out.println("(d1.after(cal.getTime()))");
			// }
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateformat.parse(date));
			cal.add(Calendar.HOUR, 24);
			if (cal.getTime().after(new Date())) {
				System.out.println("zhixing");
			}
			System.out.println(cal.getTime());
			System.out.println(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
