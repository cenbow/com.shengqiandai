package cn.vfunding.vfunding.biz.week.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.system.model.ThirdSynRecord;
import cn.vfunding.vfunding.biz.system.service.IThirdSynRecordService;
import cn.vfunding.vfunding.biz.thirdplat.dao.CashVolumeMapper;
import cn.vfunding.vfunding.biz.thirdplat.dao.ThirdRelationshipMapper;
import cn.vfunding.vfunding.biz.thirdplat.dao.ThirdTendercheckMapper;
import cn.vfunding.vfunding.biz.thirdplat.model.CashVolume;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdTendercheck;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.week.dao.WeekMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekRateMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekTenderMapper;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekRate;
import cn.vfunding.vfunding.biz.week.model.WeekTender;
import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;
import cn.vfunding.vfunding.biz.week.vo.HoldingAssetsVO;
import cn.vfunding.vfunding.biz.week.vo.UserTenderWeekActionResultVO;
import cn.vfunding.vfunding.biz.week.vo.UserTenderWeekActionVO;
import cn.vfunding.vfunding.biz.week.vo.WeekTenderVO;
import cn.vfunding.vfunding.common.module.activemq.message.CjdTenderMessage;
import cn.vfunding.vfunding.common.thirdconfig.CjdaoConfig;

@Service("weekTenderService")
public class WeekTenderServiceImpl implements IWeekTenderService {

	@Autowired
	private WeekMapper weekMapper;
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private WeekTenderMapper weekTenderMapper;

	@Autowired
	private CashVolumeMapper cashVolumeMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private WeekRateMapper weekRateMapper;

	@Autowired
	private AccountLogMapper accountLogMapper;

	@Autowired
	private ThirdRelationshipMapper thirdRelationshipMapper;

	@Autowired
	private ThirdTendercheckMapper thirdTendercheckMapper;

	@Autowired
	private IThirdSynRecordService thirdSynRecordService;

	@Override
	public void weekTenderAssist(UserTenderActionResultVO vo) {
		// 所购买的weekId
		Integer weekId = vo.getWeekId();
		// 购买人的userId
		Integer userId = vo.getUserId();
		// 记录user所购买份数,用于添加weekTender表中的money字段
		Integer userShare = vo.getBuyShare();
		// 用于真实购买份数,通过此属性计算之后的利息
		Integer buyShare = vo.getRealBuyShare();
		Week week = this.weekMapper.selectByPrimaryKey(weekId);
		WeekRate wr = this.weekRateMapper.selectByPrimaryKey(weekId);
		// 根据购买份数计算购买金额
		BigDecimal buyMoney = vo.getRealPayMoney();
		// 平台费率
		BigDecimal platformRate = wr.getPlatformRate().divide(
				new BigDecimal(100));
		// 担保费率
		BigDecimal guaranteeRate = wr.getGuaranteeRate().divide(
				new BigDecimal(100));
		// week利率
		BigDecimal apr = week.getApr().divide(new BigDecimal(100));
		// week期限
		BigDecimal timeLimit = new BigDecimal(week.getTimeLimit());
		// 用户所得利息
		BigDecimal interest = buyMoney.multiply(apr).multiply(timeLimit)
				.divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
		// 平台服务费
		BigDecimal platformFee = buyMoney.multiply(platformRate)
				.multiply(timeLimit)
				.divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
		// 担保服务费
		BigDecimal guaranteeFee = buyMoney.multiply(guaranteeRate)
				.multiply(timeLimit)
				.divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
		// 添加用户认购记录
		WeekTender wt = new WeekTender();
		wt.setWeekId(weekId);
		wt.setUserId(userId);
		wt.setStatus(1);
		wt.setBuyShare(new BigDecimal(userShare));
		wt.setRealbuyShare(new BigDecimal(buyShare));
		wt.setMoney(buyMoney);
		wt.setInterest(interest);
		wt.setPlatformFee(platformFee);
		wt.setGuaranteeFee(guaranteeFee);
		wt.setRepaymentAccount(buyMoney.add(interest));
		wt.setRepaymentYesaccount(new BigDecimal(0));
		wt.setRepaymentTime(DateUtil.getNextDayTime(new Date(), 8));
		wt.setAddTime(new Date());
		wt.setAddIp(vo.getUserip());
		this.weekTenderMapper.insertSelective(wt);
		vo.setTenderId(wt.getId());
	}

	/**
	 * 财经道购买推送接口
	 * 
	 * @param userId
	 * @param weekId
	 *            -----
	 * @param tenderId
	 * @param tenderStatus
	 * @throws Exception
	 */
	@Override
	public void updateProductBuy(Integer userId, Integer weekId,
			Integer tenderId, Integer tenderStatus) throws Exception {

		ThirdRelationship third = thirdRelationshipMapper.selectByUserIdType(
				userId, 1);

		if (third.getUserType() == 1) {
			// 添加老用户投资账单
			ThirdTendercheck tenderCheck = new ThirdTendercheck();
			tenderCheck.setTenderId(tenderId);
			tenderCheck.setTrackTime(third.getUserTracktime());
			tenderCheck.setUserId(userId);
			this.thirdTendercheckMapper.insertSelective(tenderCheck);
		}
		// 获取week详情
		Week week = weekMapper.selectByPrimaryKey(weekId);
		// 获取user信息
		UserWithBLOBs user = userMapper.selectByPrimaryKey(userId);
		// 获取tender的信息
		WeekTender wt = weekTenderMapper.selectByPrimaryKey(tenderId);
		// 计息开始时间
		String intereststarttime = new SimpleDateFormat("yyyy-MM-dd")
				.format(DateUtil.getNextDayTime(wt.getAddTime(), 1));
		// 到期时间
		String interestfinishtime = new SimpleDateFormat("yyyy-MM-dd")
				.format(wt.getRepaymentTime());
		// if (borrow.getRepaymentTime() > 0 && borrow.getEndTime() > 0) {
		// intereststarttime = DateUtil.getStringSortDateByLongDate(borrow
		// .getRepaymentTime().longValue());
		// interestfinishtime = DateUtil.getStringSortDateByLongDate(borrow
		// .getEndTime().longValue());
		// }

		// 查询平台服务费信息
		// WeekRate wr = weekRateMapper.selectByPrimaryKey(weekId);
		// InvestorsFee investorsFee = this.investorsFeeMapper
		// .selectByBorrowId(borrowId);
		BigDecimal expectApr = week.getApr();

		/*
		 * md5_value = md5(uaccount+ phone+ idcard+ usertype+
		 * companyid+thirdproductid+productid+ productname + buytime+ money+
		 * expectedrate+ realincome + transactionstatus+ ordercode+
		 * intereststarttime + interestfinishtime + accountbalance +签名密钥)
		 * 请严格按照参数顺序加密，参数值为空时不放入加密列表中
		 */
		// 推送消息
		// URL url = new URL("http://service.cjdao.com/productbuy/saveproduct");
		String urlStr = CjdaoConfig.getInstance().getTenderUrl();
		URL url = new URL(urlStr);

		ThirdSynRecord record = new ThirdSynRecord();
		record.setUrl(urlStr);
		record.setNextTime(new Date());

		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");// 提交模式
		httpConn.setDoOutput(true);// 是否输入参数

		StringBuffer params = new StringBuffer();
		StringBuffer value = new StringBuffer();// MD5 所需加密的值

		// 购买用户在财经道的账号(回传参数)
		params.append("uaccount=" + third.getThirdName());
		value.append(third.getThirdName());

		// 手机号码
		params.append("&phone=" + user.getPhone());
		value.append(user.getPhone());

		// 身份证号码
		if (EmptyUtil.isNotEmpty(user.getCardId())
				&& user.getCardId().length() >= 15) {
			params.append("&idcard=" + user.getCardId());
			value.append(user.getCardId());
		}

		// 是否新用户
		params.append("&usertype=" + third.getUserType());
		value.append(third.getUserType());

		// 机构id(回传参数)
		params.append("&companyid=" + CjdaoConfig.getInstance().getCompanyid());
		value.append(CjdaoConfig.getInstance().getCompanyid());

		// 第三方产品id
		params.append("&thirdproductid=" + week.getId());
		value.append(week.getId());

		// 产品名称
		params.append("&productname=" + week.getName());
		value.append(week.getName());

		// 购买日期 格式：yyyy-mm-dd
		String buytime = DateUtil.parseDate(new Date());
		params.append("&buytime=" + buytime);
		value.append(buytime);

		// 购买金额，Double类型
		params.append("&money="
				+ wt.getBuyShare().multiply(week.getSharePrice()));
		value.append(wt.getBuyShare().multiply(week.getSharePrice()));

		// 收益率 百分比，Double类型(0.0)
		params.append("&expectedrate=" + expectApr.divide(new BigDecimal(100)));
		value.append(expectApr.divide(new BigDecimal(100)));

		// 当前收益: 该笔购买的累计总收益 金额Double类型(0.0)
		BigDecimal realincome = wt.getInterest();
		params.append("&realincome=" + realincome);
		value.append(realincome);

		// 交易状态，分别有0,1,2 交易状态有如下几种： 投标成功：2 交易失败：1 交易成功：0
		params.append("&transactionstatus=" + tenderStatus);
		value.append(tenderStatus);

		// 订单号
		params.append("&ordercode=" + tenderId);
		value.append(tenderId);

		if (EmptyUtil.isNotEmpty(intereststarttime)
				&& intereststarttime.startsWith("20")) {
			// 计息开始时间(满标后需同步该时间)
			params.append("&intereststarttime=" + intereststarttime);
			value.append(intereststarttime);
		}

		if (EmptyUtil.isNotEmpty(interestfinishtime)
				&& interestfinishtime.startsWith("20")) {
			// 计息开始时间(满标后需同步该时间)
			params.append("&interestfinishtime=" + interestfinishtime);
			value.append(interestfinishtime);
		}

		// 持有金额
		BigDecimal accountbalance = wt.getBuyShare().multiply(
				week.getSharePrice());
		if (EmptyUtil.isEmpty(accountbalance)) {
			accountbalance = new BigDecimal(0);
		}
		params.append("&accountbalance=" + accountbalance);
		value.append(accountbalance);
		value.append(CjdaoConfig.getInstance().getKey());// 密钥
		String md5_value = DigestUtils.md5Hex(value.toString());
		params.append("&md5_value=" + md5_value);
		System.out.println("updateProductBuy的参数" + params);
		byte[] bypes = params.toString().getBytes();
		httpConn.getOutputStream().write(bypes);// 输入参数

		InputStreamReader input = new InputStreamReader(
				httpConn.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		String buf = contentBuf.toString();
		System.out.println("updateProductBuy的结果:" + buf);
		// 解析json code=0成功 code==1失败;
		if ((new org.codehaus.jettison.json.JSONObject(buf).get("code"))
				.equals("0")) {
			record.setStatus(0);
		}
		record.setArgs(params.toString());
		record.setId(ObjectId.get());
		this.thirdSynRecordService.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.weekTenderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WeekTender record) {
		// TODO Auto-generated method stub
		return this.weekTenderMapper.insertSelective(record);
	}

	@Override
	public WeekTender selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.weekTenderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeekTender record) {
		// TODO Auto-generated method stub
		return this.weekTenderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<WeekTender> selectWeekTenderListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.weekTenderMapper.selectWeekTenderListPage(pageSearch);
	}

	@Override
	public HoldingAssetsVO selectHoldingAssetsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return weekTenderMapper.selectHoldingAssetsByUserId(userId);
	}

	@Override
	public List<WeekTender> selectByUserId(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return weekTenderMapper.selectByUserId(pageSearch);
	}

	@Override
	public List<WeekTenderVO> selectWeekTenderByWeekIdAndRepaymentTimeListPage(
			PageSearch pageSearch) {
		return this.weekTenderMapper
				.selectWeekTenderByWeekIdAndRepaymentTimeListPage(pageSearch);
	}

	@Override
	public void sendUrlByTenderInfo(CjdTenderMessage ms) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BorrowTenderVO> selectAgreementPage(Integer weekId) {
		return weekTenderMapper.selectAgreementPage(weekId);
	}

}