package cn.vfunding.vfunding.biz.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountFeelLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountFeelMapper;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.account.model.AccountFeelLog;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.common.utils.CardPassword;
import cn.vfunding.vfunding.biz.common.vo.FeelVO;
import cn.vfunding.vfunding.biz.system.dao.FeelMapper;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.system.model.Feel;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
@Service("accountFeelService")
public class AccountFeelServiceImpl implements IAccountFeelService {

	@Autowired
	private AccountFeelMapper accountFeelMapper;
	@Autowired
	private AccountFeelLogMapper accountFeelLogMapper;
	@Autowired
	private FeelMapper feelMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BorrowTenderMapper borrowTenderMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return accountFeelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AccountFeel record) {
		return accountFeelMapper.insert(record);
	}

	@Override
	public int insertSelective(AccountFeel record) {
		return accountFeelMapper.insertSelective(record);
	}

	@Override
	public AccountFeel selectByPrimaryKey(Integer id) {
		return accountFeelMapper.selectByPrimaryKey(id);
	}

	@Override
	public AccountFeel selectByUserId(Integer userId) {
		return accountFeelMapper.selectByUserId(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(AccountFeel record) {
		return accountFeelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccountFeel record) {
		return accountFeelMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByUserId(AccountFeel record) {
		return accountFeelMapper.updateByUserId(record);
	}

	/**
	 * 增加体验金 并写入日志
	 * @param feel
	 * @param feelLog
	 * @param status 0:插入  1：更新
	 * @return
	 * @author liuhuan
	 */
	@Override
	public String insertFeelLog(AccountFeel accountFeel, AccountFeelLog feelLog, Feel feel, Integer status) {
		if(status == 0){
			accountFeelMapper.insertSelective(accountFeel);
		} else {
			accountFeelMapper.updateByPrimaryKeySelective(accountFeel);
		}
		accountFeelLogMapper.insert(feelLog);
		feelMapper.updateByPrimaryKeySelective(feel);
		return "充值成功.";
	}

	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @Description:给新用户派发体验券
	 * @param userId
	 * @param account:体验券面值
	 * @return
	 * @author liuhuan
	 */
	@Override
	public synchronized String updateSendCodeForNewuser(Integer userId,BigDecimal account, String ip) {
		code = CardPassword.getRandomNum();
		while (feelMapper.selectByCode(code) != null){
			code = CardPassword.getRandomNum();
		}
		Feel feelvo = new Feel();
		feelvo.setNo("81");
		List<Feel> feelList = feelMapper.selectByNoStatus(feelvo);
		
		Feel feel = new Feel();
		feel.setMoney(account);
		feel.setAddtime(Integer.parseInt(DateUtil.getTime()));
		feel.setCode(code);
		feel.setNo(feelList==null?"81000001":Integer.parseInt(feelList.get(0).getNo())+1+"");
		feel.setUserId(userId);
		feel.setStatus(1);
		feelMapper.insert(feel);
		
		//投资人站内信
		Message message = new Message();
		message.setSentUser(0);
		message.setReceiveUser(userId);
		message.setName("体验券发放");
		message.setContent("恭喜您，您已获得"+feel.getMoney()+"体验券，密码为:"+feel.getCode()+"前往相应页面充值.");
		message.setStatus(0);
		message.setType("system");
		message.setSented(null);
		message.setDeltype(0);
		message.setAddtime(DateUtil.getTime());
		message.setAddip(ip);
		messageMapper.insert(message);
		return "发送成功";
	}
	/**
     * @Description:线上申请体验券
     * @param userId
     * @param account
     * @param ip
     * @return
     * @author liuhuan
     */
	@Override
	public String updateSendCodeForOnline(String type, BigDecimal account, Integer size) {
		String code = "";
		Feel feelvo = new Feel();
		feelvo.setNo(type);
		feelvo.setMoney(null);
		feelvo.setStatus(null);
		List<Feel> feelList = feelMapper.selectByNoStatus(feelvo);
		for(int i=1;i<=size;i++){
			Feel feel = new Feel();
			code = CardPassword.getRandomNum();
			while (feelMapper.selectByCode(code) != null){
				code = CardPassword.getRandomNum();
			}
			feel.setMoney(account);
			feel.setCode(code);
			feel.setNo(feelList.isEmpty()? type+"000001":Integer.parseInt(feelList.get(0).getNo())+1+"");
			feel.setUserId(null);
			feel.setGroupName(null);
			feel.setStatus(1);
			feel.setAddtime(null);
			feelMapper.insert(feel);
		}
		return "申请成功";
	}

	@Override
	public synchronized String updateRechargeFeel(User user, String feelCode,String ip) {
		String msg = "";
		Feel feel = feelMapper.selectByCode(feelCode);
		AccountFeel accountFeel = accountFeelMapper.selectByUserId(user.getUserId());
		List<AccountFeelLog> accountFeelLogs = accountFeelLogMapper.selectByUserIdType(new AccountFeelLog(user.getUserId(),"recharge")); //是否充值过体验券
		List<BorrowTender> borrowTenders = borrowTenderMapper.selectListByUserIdBorrowId(user.getUserId(), null); //是否是新用户
		if(feel == null){
			msg = "该体验码无效，请咨询在线客服.";
		} else if(feel.getStatus() == 2){
			msg = "该体验码已使用，请勿重复操作！";
		} else if(!accountFeelLogs.isEmpty()){
			msg = "您已充值过体验券，不能再次充值.";
		} else if(!borrowTenders.isEmpty()){
			msg = "您不是新用户，不能参与该体验活动.";
		} else if(accountFeel == null){ //开户
			//建立好友关系
			if((user.getInviteUserid() == null || "".equals(user.getInviteUserid())) && user.getTypeId() == 2){
				if(feel.getUserId() != null && feel.getUserId() != 0){
					UserWithBLOBs userwith = new UserWithBLOBs();
					user.setInviteUserid(feel.getUserId().toString());
					userwith.setInviteUserid(user.getInviteUserid());
					userwith.setUserId(user.getUserId());
					userMapper.updateByPrimaryKeySelective(userwith);
				}
			}
			AccountFeel accountFeel_ = new AccountFeel();
			accountFeel_.setUserId(user.getUserId());
			accountFeel_.setUseMoney(feel.getMoney());
			accountFeel_.setTotal(feel.getMoney());
			accountFeel_.setOther("0");
			AccountFeelLog feelLog = new AccountFeelLog();
			feelLog.setUserId(user.getUserId());
			feelLog.setType("recharge");
			feelLog.setMoney(feel.getMoney());
			feelLog.setUseMoney(accountFeel_.getUseMoney());
			feelLog.setNoUseMoney(accountFeel_.getNoUseMoney());
			feelLog.setCollection(new BigDecimal("0"));
			feelLog.setTotal(accountFeel_.getTotal());
			feelLog.setToUser(0);
			feelLog.setRemark("充值金额:"+feel.getMoney()+"到账,"+"体验券"+feel.getCode());
			feelLog.setBorrowId(0);
			feelLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			feelLog.setAddip(ip);
			feel.setStatus(2);
			feel.setAddtime(Integer.parseInt(DateUtil.getTime()));//激活时间
			String result = this.insertFeelLog(accountFeel_, feelLog, feel, 0);
			msg = result;
		} else { //更新账户
			// 建立好友关系
			if((user.getInviteUserid() == null || "".equals(user.getInviteUserid())) && user.getTypeId() == 2){
				if(feel.getUserId() != null && feel.getUserId() != 0){
					UserWithBLOBs userwith = new UserWithBLOBs();
					user.setInviteUserid(feel.getUserId().toString());
					userwith.setInviteUserid(user.getInviteUserid());
					userwith.setUserId(user.getUserId());
					userMapper.updateByPrimaryKeySelective(userwith);
				}
			}
			accountFeel.setUseMoney(accountFeel.getUseMoney().add(feel.getMoney()));
			accountFeel.setTotal(accountFeel.getTotal().add(feel.getMoney()));
			AccountFeelLog feelLog = new AccountFeelLog();
			feelLog.setUserId(user.getUserId());
			feelLog.setType("recharge");
			feelLog.setMoney(feel.getMoney());
			feelLog.setUseMoney(accountFeel.getUseMoney());
			feelLog.setNoUseMoney(accountFeel.getNoUseMoney());
			feelLog.setCollection(accountFeel.getCollection());
			feelLog.setTotal(accountFeel.getTotal());
			feelLog.setToUser(0);
			feelLog.setRemark("充值金额:"+feel.getMoney()+"到账,"+"体验券"+feel.getCode());
			feelLog.setBorrowId(0);
			feelLog.setAddtime(Integer.parseInt(DateUtil.getTime()));
			feelLog.setAddip(ip);
			feel.setStatus(2);
			feel.setAddtime(Integer.parseInt(DateUtil.getTime()));//激活时间
			String result = this.insertFeelLog(accountFeel, feelLog, feel, 1);
			msg = result;
		}
		return msg;
	}
	
	@Override
	public List<FeelVO> selectFeelsListPage(PageSearch pageSearch) {
		return accountFeelMapper.selectFeelsListPage(pageSearch);
	}

	
}
