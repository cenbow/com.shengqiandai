package cn.vfunding.vfunding.biz.activity.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.activity.dao.ActivityHongbaoLogMapper;
import cn.vfunding.vfunding.biz.activity.dao.ActivityHongbaoMapper;
import cn.vfunding.vfunding.biz.activity.dao.WeixinOpenIdMapper;
import cn.vfunding.vfunding.biz.activity.model.ActivityHongbao;
import cn.vfunding.vfunding.biz.activity.model.ActivityHongbaoLog;
import cn.vfunding.vfunding.biz.activity.model.WeixinOpenId;
import cn.vfunding.vfunding.biz.activity.service.IActivityHongbaoService;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

@Service("activityHongbaoService")
public class ActivityHongbaoServiceImpl implements IActivityHongbaoService {

	@Autowired
	private ActivityHongbaoMapper ahMapper;

	@Autowired
	private ActivityHongbaoLogMapper ahlMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private AccountLogMapper accountLogMapper;

	@Autowired
	private WeixinOpenIdMapper openIdMapper;

	@Override
	public Json insertActivityHongbao(String phone, Integer shareId,
			String openId) {
		// TODO Auto-generated method stub
		Json json = new Json();

		String share_phone = null;
		Integer id = 0;

		// 获取openId所绑定的用户
		WeixinOpenId isExistOpenId = this.openIdMapper.selectByOpenid(openId);
		if (EmptyUtil.isEmpty(isExistOpenId)) {
			WeixinOpenId openIdBean = new WeixinOpenId();
			openIdBean.setOpenid(openId);
			openIdBean.setPhone(phone);
			openIdBean.setAddtime(new Date());
			this.openIdMapper.insertSelective(openIdBean);
			id = openIdBean.getId();
		}
		json.setObj(id);

		if (EmptyUtil.isNotEmpty(shareId) && shareId > 0) {
			WeixinOpenId shareOpenIdBean = this.openIdMapper
					.selectByPrimaryKey(shareId);
			if (EmptyUtil.isNotEmpty(shareOpenIdBean)
					&& shareOpenIdBean.getId() > 0) {
				share_phone = shareOpenIdBean.getPhone();
			}

		}
		// 查询用户是否为微积金用户
		List<UserWithBLOBs> userList = this.userMapper.selectByLogin(phone);
		if (EmptyUtil.isNotEmpty(phone) && phone.length() == 11) {
			// 查询用户的红包记录
			ActivityHongbaoLog beanLog = new ActivityHongbaoLog();
			beanLog.setPhone(phone);
			beanLog.setFromPhone("0");
			beanLog.setStatus(1);
			List<ActivityHongbaoLog> logList = this.ahlMapper
					.selectByParameters(beanLog);
			// 老用户 并且没有领取过红包
			if (userList.size() > 0 && logList.size() == 0) {
				json.setSuccess(true);
				json.setMsg("10元");
				this.insertActivityHongbaoLog(phone, "0", 10, 1);
				this.insertOrUpdateActivityHongbao(phone, 10);
				// 给老用户添加站内信和资金记录
				this.insertMessage(userList.get(0).getUserId(), 10, null, null);
				this.insertAccount(userList.get(0).getUserId(), 10);
			} else if (userList.size() == 0 && logList.size() == 0) {// 新用户,并且没有领取过红包
				json.setSuccess(true);
				json.setMsg("10元");
				this.insertActivityHongbaoLog(phone, "0", 10, 1);
				this.insertOrUpdateActivityHongbao(phone, 10);
			} else {// 领取过红包
				ActivityHongbaoLog ahbl = logList.get(0);
				json.setSuccess(false);
				json.setMsg(ahbl.getHongbao() + "元");
			}
		} else {
			throw new BusinessException("8005012", "参数有误");
		}
		if (EmptyUtil.isNotEmpty(share_phone) && share_phone.length() == 11) {
			// 查询用户是否为微积金用户
			List<UserWithBLOBs> fromUser = this.userMapper
					.selectByLogin(share_phone);
			// 用户为新用户 并且领取成功
			if (userList.size() == 0 && json.getSuccess()) {
				// 新用户并且领取成功
				this.insertActivityHongbaoLog(share_phone, phone, 5, 1);
				this.insertOrUpdateActivityHongbao(share_phone, 5);
				// 分享人为老用户,被分享者领取成功, 则更新账户余额,并添加站内信
				if (fromUser.size() > 0) {
					this.insertMessage(fromUser.get(0).getUserId(), 5, phone, 1);
					this.insertAccount(fromUser.get(0).getUserId(), 5);
				}

			} else if (userList.size() > 0) {
				// 老用户
				this.insertActivityHongbaoLog(share_phone, phone, 0, 3);
				// 分享人为老用户,被分享者领取失败, 则添加站内信
				if (fromUser.size() > 0) {
					this.insertMessage(fromUser.get(0).getUserId(), 5, phone, 3);
				}
			} else {
				// 重复获得的用户
				this.insertActivityHongbaoLog(share_phone, phone, 0, 2);
				// 分享人为老用户,被分享者领取失败, 则添加站内信
				if (fromUser.size() > 0) {
					this.insertMessage(fromUser.get(0).getUserId(), 5, phone, 2);
				}
			}

		}
		return json;
	}

	/**
	 * 添加或更新ActivityHongbao
	 * 
	 * @Description:
	 * @param phone
	 * @param hongbao
	 * @param bean
	 * @author lilei
	 */
	private void insertOrUpdateActivityHongbao(String phone, Integer hongbao) {
		// 查询红包账户
		ActivityHongbao bean = this.ahMapper.selectByPhone(phone);
		if (EmptyUtil.isEmpty(bean) || bean.getId() < 1) {
			bean = new ActivityHongbao();
			bean.setPhone(phone);
			bean.setHongbao(new BigDecimal(hongbao));
			bean.setUpdatetime(new Date());
			bean.setAddtime(new Date());
			this.ahMapper.insertSelective(bean);
		} else {
			bean.setId(bean.getId());
			bean.setUpdatetime(new Date());
			bean.setHongbao(bean.getHongbao().add(new BigDecimal(hongbao)));
			this.ahMapper.updateByPrimaryKeySelective(bean);
		}

	}

	private void insertActivityHongbaoLog(String phone, String from_phone,
			Integer hongbao, Integer status) {
		ActivityHongbaoLog ahbl = new ActivityHongbaoLog();
		ahbl.setPhone(phone);
		ahbl.setHongbao(new BigDecimal(hongbao));
		ahbl.setFromPhone(from_phone);
		ahbl.setStatus(status);
		ahbl.setAddtime(new Date());
		this.ahlMapper.insertSelective(ahbl);
	}

	private void insertMessage(Integer userId, Integer hongbao,
			String from_phone, Integer status) {
		Message message = new Message();
		message.setSentUser(0);
		message.setReceiveUser(userId);
		if (EmptyUtil.isEmpty(from_phone)) {
			message.setName("红包入账");
			message.setContent("成功领取" + hongbao + "元红包");
		} else if (EmptyUtil.isNotEmpty(from_phone) && status == 1) {
			message.setName("红包入账");
			message.setContent("成功分享给手机号为：" + from_phone.substring(0, 3)
					+ "****" + from_phone.substring(7, 11) + ",获得5元红包");
		} else if (EmptyUtil.isNotEmpty(from_phone) && status == 2) {
			message.setName("红包分享失败");
			message.setContent("手机号为：" + from_phone.substring(0, 3) + "****"
					+ from_phone.substring(7, 11) + "已被他人分享过");
		} else if (EmptyUtil.isNotEmpty(from_phone) && status == 3) {
			message.setName("红包分享失败");
			message.setContent("手机号为：" + from_phone.substring(0, 3) + "****"
					+ from_phone.substring(7, 11) + "已是微积金用户,无法获取红包");
		}
		message.setStatus(0);
		message.setType("system");
		message.setSented(null);
		message.setDeltype(0);
		message.setAddtime(DateUtil.getTime());
		message.setAddip("127.0.0.1");
		messageMapper.insert(message); // 投资人站内信
	}

	private void insertAccount(Integer userId, Integer hongbaoAcount) {
		// 查询用户账户信息,添加5元红包
		Account useraccount = this.accountMapper.selectByUserId(userId);
		useraccount.setTotal(useraccount.getTotal().add(
				new BigDecimal(hongbaoAcount)));
		useraccount.setUseMoney(useraccount.getUseMoney().add(
				new BigDecimal(hongbaoAcount)));
		this.accountMapper.updateByPrimaryKeySelective(useraccount);

		// 增加资金交易记录-accountLog
		AccountLog accountLog = new AccountLog();
		accountLog.setUserId(userId);
		accountLog.setType("hongbao");
		accountLog.setMoney(new BigDecimal(hongbaoAcount));
		accountLog.setTotal(useraccount.getTotal());
		accountLog.setNoUseMoney(useraccount.getNoUseMoney());
		accountLog.setUseMoney(useraccount.getUseMoney());
		accountLog.setCollection(useraccount.getCollection());
		accountLog.setToUser(0);
		accountLog.setRemark("红包入账");
		accountLog.setAddtime(Integer.parseInt(DateUtil.getLongTime()
				.toString()));
		accountLog.setAddip("127.0.0.1");
		this.accountLogMapper.insertSelective(accountLog);
	}

	@Override
	public boolean selectActivityHongbao(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		String phone = "13951889475";
		System.out.println();

	}

	@Override
	public void updateUserAccount(Integer userId, String phone) {
		// TODO Auto-generated method stub
		ActivityHongbao bean = this.ahMapper.selectByPhone(phone);
		if (EmptyUtil.isNotEmpty(bean) && bean.getHongbao().doubleValue() > 0) {
			Account useraccount = this.accountMapper.selectByUserId(userId);
			useraccount.setTotal(useraccount.getTotal().add(bean.getHongbao()));
			useraccount.setUseMoney(useraccount.getUseMoney().add(
					bean.getHongbao()));
			this.accountMapper.updateByPrimaryKeySelective(useraccount);

			// 增加资金交易记录-accountLog
			AccountLog accountLog = new AccountLog();
			accountLog.setUserId(userId);
			accountLog.setType("hongbao");
			accountLog.setMoney(bean.getHongbao());
			accountLog.setTotal(useraccount.getTotal());
			accountLog.setNoUseMoney(useraccount.getNoUseMoney());
			accountLog.setUseMoney(useraccount.getUseMoney());
			accountLog.setCollection(useraccount.getCollection());
			accountLog.setToUser(0);
			accountLog.setRemark("累积红包入账");
			accountLog.setAddtime(Integer.parseInt(DateUtil.getLongTime()
					.toString()));
			accountLog.setAddip("127.0.0.1");
			this.accountLogMapper.insertSelective(accountLog);

			// 站内信
			Message message = new Message();
			message.setSentUser(0);
			message.setReceiveUser(userId);
			message.setName("累积红包入账");
			message.setContent(bean.getHongbao() + "元累积红包已经进入您的账户,请您笑纳");
			message.setStatus(0);
			message.setType("system");
			message.setSented(null);
			message.setDeltype(0);
			message.setAddtime(DateUtil.getTime());
			message.setAddip("127.0.0.1");
			messageMapper.insert(message); // 投资人站内信
		}

	}

	@Override
	public ArrayList<ActivityHongbao> selectTopFive() {
		// TODO Auto-generated method stub
		return this.ahMapper.selectTopFive();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.ahMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ActivityHongbao record) {
		// TODO Auto-generated method stub
		return this.ahMapper.insert(record);
	}

	@Override
	public int insertSelective(ActivityHongbao record) {
		// TODO Auto-generated method stub
		return this.ahMapper.insertSelective(record);
	}

	@Override
	public ActivityHongbao selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.ahMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityHongbao record) {
		// TODO Auto-generated method stub
		return this.ahMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActivityHongbao record) {
		// TODO Auto-generated method stub
		return this.ahMapper.updateByPrimaryKey(record);
	}

	@Override
	public ActivityHongbao selectByPhone(String phone) {
		// TODO Auto-generated method stub
		return this.ahMapper.selectByPhone(phone);
	}

}