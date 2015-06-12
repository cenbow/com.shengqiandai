package cn.vfunding.vfunding.biz.cron.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.cron.dao.AccountRecordMapper;
import cn.vfunding.vfunding.biz.cron.dao.FinancialPlannerCronMapper;
import cn.vfunding.vfunding.biz.cron.dao.FinancialPlannerStatusCronMapper;
import cn.vfunding.vfunding.biz.cron.model.AccountRecord;
import cn.vfunding.vfunding.biz.cron.model.FinancialPlannerCron;
import cn.vfunding.vfunding.biz.cron.model.FinancialPlannerStatusCron;
import cn.vfunding.vfunding.biz.cron.service.IFinancialPlannerCronService;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

@Service("financialPlannerCronService")
public class FinancialPlannerCronServiceImpl implements IFinancialPlannerCronService {

	@Autowired
	private FinancialPlannerCronMapper fpMapper;

	@Autowired
	private MessageMapper umMapper;

	@Autowired
	private AccountRecordMapper recordMapper;

	@Autowired
	private UserMapper uMapper;

	@Autowired
	private FinancialPlannerStatusCronMapper fpsMapper;

	@Value("${financial.upordown.judge.time}")
	private String selectUserFinancialplannerTime;

	private static Integer common = 2;
	private static Integer baseFp = 28;
	private static Integer middleFp = 29;
	private static Integer seniorFp = 30;

	// 推荐达人
	private static Integer masterFp = 32;
	// 推荐达人是否执行
	@Value("${financialPlanner.masterFpStatus}")
	private Integer masterFpStatus;

	@Override
	public String selectUserFinancialplanner() {
		this.insertAccountRecord();
		this.fpMapper.callProcedure();
		List<FinancialPlannerCron> listfp = this.fpMapper.selectUserFinancialplanner();
		Message um = null;
		UserWithBLOBs u = null;
		FinancialPlannerStatusCron fps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// InetAddress addr;
		// String ip = "";
		// try {
		// addr = InetAddress.getLocalHost();
		// ip = addr.getHostAddress().toString();// 获得本机IP
		// } catch (UnknownHostException e) {
		// e.printStackTrace();
		// }

		for (FinancialPlannerCron fp : listfp) {

			// 普通用户的升级判断
			if (fp.getTypeId() == common) {
				if (fp.getUserSonMoney() >= 100 && fp.getUserStatus() != masterFp && fp.getUserStatus() != baseFp
						&& masterFpStatus == 1) {
					// 添加用户消息
					um = new Message();
					um.setReceiveUser(fp.getUserId());
					um.setAddtime(sdf.format(new Date()));
					um.setType("system");
					um.setContent("尊敬的用户,系统已为您自动升级为理财师达人,[<a href='/friend/financiaDetail' target=_blank><font color=red>点击此处</font></a>]查看");
					um.setAddtime(String.valueOf(new Date().getTime() / 1000));
					um.setAddip("127.0.0.1");
					um.setName("尊敬的用户,系统已为您自动升级为理财师达人");
					// 更改用户类型
					u = new UserWithBLOBs();
					u.setUserId(fp.getUserId());
					u.setTypeId(masterFp);
					this.uMapper.updateByPrimaryKeySelective(u);
					this.umMapper.insertSelective(um);
				}
				if (fp.getTypeId() == masterFp && fp.getUserMoney() >= 5000 && fp.getUserStatus() != baseFp) {
					// 添加用户消息
					um = new Message();
					um.setReceiveUser(fp.getUserId());
					um.setName("尊敬的用户,您已满足特约理财师的条件,可在30天内申请");
					um.setType("system");
					um.setContent("恭喜您，[<a href='/friend/financiaDetail' target=_blank><font color=red>点击此处</font></a>]申请特约理财师");
					um.setAddtime(String.valueOf(new Date().getTime() / 1000));
					um.setAddip("127.0.0.1");
					this.umMapper.insertSelective(um);
					// 添加升降级历史记录
					fps = new FinancialPlannerStatusCron();
					fps.setUserid(fp.getUserId());
					fps.setUserstatus(baseFp);
					fps.setAddtime(sdf.format(new Date()));
					this.fpsMapper.insertSelective(fps);
				}
			}
			// 特约理财师的升降级判断
			else if (fp.getTypeId() == baseFp) {
				if (fp.getUserMoney() >= 5000 && fp.getUserSonMoney() >= 1000000 && fp.getUserStatus() != baseFp
						&& fp.getUserStatus() != middleFp) {
					// 添加用户消息
					um = new Message();
					um.setReceiveUser(fp.getUserId());
					um.setType("system");
					um.setContent("恭喜您，[<a href='/friend/financiaDetail' target=_blank><font color=red>点击此处</font></a>]高级理财师");
					um.setAddtime(String.valueOf(new Date().getTime() / 1000));
					um.setAddip("127.0.0.1");
					um.setName("尊敬的用户,您已满足高级理财师的条件,可在30天内申请");
					this.umMapper.insertSelective(um);
					// 添加升降级历史记录
					fps = new FinancialPlannerStatusCron();
					fps.setUserid(fp.getUserId());
					fps.setUserstatus(middleFp);
					fps.setAddtime(sdf.format(new Date()));
					this.fpsMapper.insertSelective(fps);
				}
				if (fp.getDays() >= 30 && fp.getUserSonMoney() < 833334 && fp.getUserInviteCount() < 1) {
					// 添加用户消息
					um = new Message();
					um.setReceiveUser(fp.getUserId());
					um.setAddtime(sdf.format(new Date()));
					um.setType("system");
					um.setContent("尊敬的用户,您已不满足特约理财师的条件,已降为普通用户");
					um.setAddtime(String.valueOf(new Date().getTime() / 1000));
					um.setAddip("127.0.0.1");
					um.setName("尊敬的用户,您已不满足特约理财师的条件,已降为普通用户");
					// 更改用户类型
					u = new UserWithBLOBs();
					u.setUserId(fp.getUserId());
					u.setTypeId(common);
					this.uMapper.updateByPrimaryKeySelective(u);
					this.umMapper.insertSelective(um);
				}
			}
			// 高级理财师的升降级判断
			else if (fp.getTypeId() == middleFp) {
				if (fp.getUserMoney() >= 10000 && fp.getUserSonMoney() >= 5000000 && fp.getTheNumber() >= 20
						&& fp.getUserStatus() != baseFp && fp.getUserStatus() != middleFp
						&& fp.getUserStatus() != seniorFp) {
					// 添加用户消息
					um = new Message();
					um.setReceiveUser(fp.getUserId());
					um.setType("system");
					um.setContent("恭喜您，[<a href='/friend/financiaDetail' target=_blank><font color=red>点击此处</font></a>]申请首席理财师");
					um.setAddtime(String.valueOf(new Date().getTime() / 1000));
					um.setAddip("127.0.0.1");
					um.setName("尊敬的用户,您已满足首席理财师的条件,可在30天内申请");
					this.umMapper.insertSelective(um);
					// 添加升降级历史记录
					fps = new FinancialPlannerStatusCron();
					fps.setUserid(fp.getUserId());
					fps.setUserstatus(seniorFp);
					fps.setAddtime(sdf.format(new Date()));
					this.fpsMapper.insertSelective(fps);
				}
				if (fp.getDays() >= 30 && fp.getUserSonMoney() < 833334 && fp.getUserInviteCount() < 1) {
					// 添加用户消息
					um = new Message();
					um.setReceiveUser(fp.getUserId());
					um.setType("system");
					um.setContent("尊敬的用户,您已不满足高级理财师的条件,已降为特约理财师");
					um.setAddtime(String.valueOf(new Date().getTime() / 1000));
					um.setAddip("127.0.0.1");
					um.setName("尊敬的用户,您已不满足高级理财师的条件,已降为特约理财师");
					// 更改用户类型
					u = new UserWithBLOBs();
					u.setUserId(fp.getUserId());
					u.setTypeId(baseFp);
					this.uMapper.updateByPrimaryKeySelective(u);
					this.umMapper.insertSelective(um);
				}
			}
			// 首席理财师的降级判断
			else if (fp.getTypeId() == seniorFp) {
				if (fp.getDays() >= 30 && fp.getUserSonMoney() < 833334 && fp.getUserInviteCount() < 1) {
					// 添加用户消息
					um = new Message();
					um.setReceiveUser(fp.getUserId());
					um.setAddtime(sdf.format(new Date()));
					um.setType("system");
					um.setContent("尊敬的用户,您已不满足首席理财师的条件,已降为高级理财师");
					um.setAddtime(String.valueOf(new Date().getTime() / 1000));
					um.setAddip("127.0.0.1");
					um.setName("尊敬的用户,您已不满足首席理财师的条件,已降为高级理财师");
					// 更改用户类型
					u = new UserWithBLOBs();
					u.setUserId(fp.getUserId());
					u.setTypeId(middleFp);
					this.uMapper.updateByPrimaryKeySelective(u);
					this.umMapper.insertSelective(um);
				}
			}
		}
		return selectUserFinancialplannerTime;
	}

	public void insertAccountRecord() {
		AccountRecord account = null;
		Map<Integer, Integer> recordMap = new HashMap<Integer, Integer>();
		// 查询资产为5000的记录
		List<AccountRecord> listRecord = this.recordMapper.selectAllRecord(5000);
		for (AccountRecord ar : listRecord) {
			recordMap.put(ar.getUserId(), ar.getDays());
		}
		// 获取用户资金信息
		List<AccountRecord> listUserAccount = this.recordMapper.selectUserAccount();
		for (AccountRecord ua : listUserAccount) {
			if (ua.getMoney() != null && ua.getMoney() < 5000) {
				if (recordMap.get(ua.getUserId()) != null) {
					account = new AccountRecord();
					account.setDays(recordMap.get(ua.getUserId()) + 1);
					account.setUserId(ua.getUserId());
					account.setMoney(5000);
					this.recordMapper.updateByUserIdAndMoney(account);
				} else {
					account = new AccountRecord();
					account.setDays(0);
					account.setUserId(ua.getUserId());
					account.setMoney(5000);
					this.recordMapper.insert(account);
				}
			} else {
				if (recordMap.get(ua.getUserId()) != null && recordMap.get(ua.getUserId()) != 0) {
					account = new AccountRecord();
					account.setDays(0);
					account.setUserId(ua.getUserId());
					account.setMoney(5000);
					this.recordMapper.updateByUserIdAndMoney(account);
				}
			}
		}
	}
}
