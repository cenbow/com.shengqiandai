package cn.vfunding.vfunding.interceptors;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import cn.vfunding.common.framework.jms.activemq.JmsSender;
import cn.vfunding.common.framework.third.annotations.NeedAfterInterceptor;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.module.activemq.message.CjdBorrowMessage;
import cn.vfunding.common.module.activemq.message.CjdTenderMessage;
import cn.vfunding.common.module.activemq.message.CjdUserMessage;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.system.dao.ThirdRelationshipMapper;
import cn.vfunding.vfunding.biz.system.model.ThirdRelationship;

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
	public void afterReturning(Object arg0, Method method, Object[] arg2, Object arg3) throws Throwable {
		NeedAfterInterceptor need = method.getAnnotation(NeedAfterInterceptor.class);
		// arg0:拦截方法的返回值
		// arg2:拦截方法的参数
		// arg3:被拦截的类对象
		if (EmptyUtil.isNotEmpty(need)) {// 充值
			if (need.value().equals("recharge")) {
				AccountRecharge rec = (AccountRecharge) arg2[0];
				if (rec.getStatus().intValue() == 1) {// 充值成功
					Integer userId = rec.getUserId();
					ThirdRelationship ship = this.thirdRelationshipMapper.selectByUserIdType(userId, 1);
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
				ThirdRelationship ship = this.thirdRelationshipMapper.selectByUserIdType(userId, 1);
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

				// 发送MQ异步消息
			} else if (need.value().equals("borrowTender")) {// 投资
				UserTenderActionVO actionVO = (UserTenderActionVO) arg2[0];// 方法第一个参数
				Integer userId = actionVO.getUserId();
				ThirdRelationship ship = this.thirdRelationshipMapper.selectByUserIdType(userId, 1);
				if (EmptyUtil.isNotEmpty(ship)) {
					UserTenderActionResultVO resultVO = (UserTenderActionResultVO) arg0;// 方法放回值

					// 发送投资MQ
					CjdTenderMessage tenderMsg = new CjdTenderMessage();
					tenderMsg.setUserId(userId);
					tenderMsg.setBorrowId(actionVO.getBorrowId());
					tenderMsg.setTenderId(resultVO.getTenderId());
					tenderMsg.setType(1);// 1代表单例投资
					this.jmsSender.sendAsynchronousMessage(tenderMsg);

					// 更新borrow信息的MQ
					CjdBorrowMessage borrowMsg = new CjdBorrowMessage();
					borrowMsg.setBorrowId(actionVO.getBorrowId());
					borrowMsg.setType(1);// 1为更新borrow可借金额
					this.jmsSender.sendAsynchronousMessage(borrowMsg);

					// 发送余额MQ
					CjdUserMessage message = new CjdUserMessage();
					message.setType(3);// 3-余额同步
					message.setUserId(userId);
					message.setUaccount(null);
					this.jmsSender.sendAsynchronousMessage(message);
				}

			} else if (need.value().equals("borrwoVerify")) {// 复审
				FinalVerifyVO verifyVO = (FinalVerifyVO) arg2[0];// 方法参数
				String result = (String) arg0;// 方法返回值

				// 更新所有用户投资的MQ
				CjdTenderMessage tenderMsg = new CjdTenderMessage();
				tenderMsg.setBorrowId(verifyVO.getBorrowId());
				tenderMsg.setType(2);// 2代表循环更新投资信息
				this.jmsSender.sendAsynchronousMessage(tenderMsg);

				// 更新borrow信息的MQ
				CjdBorrowMessage borrowMsg = new CjdBorrowMessage();
				borrowMsg.setBorrowId(verifyVO.getBorrowId());
				borrowMsg.setType(2);// 2更新borrow的计息时间
				this.jmsSender.sendAsynchronousMessage(borrowMsg);
				// 发送MQ异步消息
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
			}

		}
	}

}
