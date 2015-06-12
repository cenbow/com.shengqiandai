package cn.vfunding.vfunding.plat.mq.receiver;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowAutoMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowAuto;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.mq.service.IBorrowAutoService;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.common.module.activemq.message.BorrowAutoMessage;
import cn.vfunding.vfunding.common.module.activemq.message.UserTaskMessage;

/**
 * 自动投标
 * 
 * @author lilei
 * 
 */
@Component
public class BorrowAutoReceiver extends MessageReceiver {
	@Autowired
	private IBorrowAutoService borrowAutoService;
	@Autowired
	private BorrowAutoMapper borrowAutoMapper;
	@Autowired
	private BorrowMapper borrowMapper;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IBorrowTenderService borrowTenderService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void receive(Object message) {
		BorrowAutoMessage ms = (BorrowAutoMessage) message;
		logger.info("执行自动投标MQ");
		this.insertBorrowAutoUserId(ms.getBorrowId());
	}

	public void insertBorrowAutoUserId(Integer borrowId) {
		Borrow borrow = this.borrowMapper.selectBorrowById(borrowId);
		List<BorrowAuto> List = this.borrowAutoMapper.selectBorrowAutoUserId(borrow);
		for (BorrowAuto borrowAuto : List) {
			Integer userId = borrowAuto.getUserId();
			if (userId != borrow.getUserId()&& !borrow.getBiaoType().equals("huodong")) {
				Integer tenderMoney = borrowAuto.getTenderAccount();
				Account useraccount = this.accountMapper.selectByUserId(userId);
				Double useMoney = useraccount.getUseMoney().doubleValue();
				if (useMoney >= borrow.getLowestAccount().doubleValue() && tenderMoney >= 50) {
					try {
						User user = this.userMapper.selectByPrimaryKey(userId);
						Integer userType = user.getTypeId();
						UserTenderActionVO vo = new UserTenderActionVO();
						vo.setUserId(userId);
						vo.setBorrowId(borrowId);
						vo.setUserType(userType);
						vo.setPayMoney(new BigDecimal(tenderMoney));
						vo.setUserip("borrowAuto");
						//UserTenderActionResultVO result = this.borrowTenderService.insertUserTenderAction(vo);
						UserTenderActionResultVO result = this.borrowTenderService.userTenderAction(vo);
						if (result.getStatus() == 3) {
							logger.info("标的已满");
						} else if (result.getStatus() == 1 || result.getStatus() == 2) {
							UserTaskMessage ut = new UserTaskMessage();
							ut.setUserId(userId);
							ut.setTaskId(7);
							//this.jmsSender.sendAsynchronousMessage(ut);
							this.jmsSenderUtil.asynSendSystemJms(ut);
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
		}

	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.BorrowAutoMessage");
	}

}
