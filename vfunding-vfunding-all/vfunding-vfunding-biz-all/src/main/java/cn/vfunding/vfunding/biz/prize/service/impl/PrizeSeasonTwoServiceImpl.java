package cn.vfunding.vfunding.biz.prize.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountRechargeMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.message.dao.CashMessageMapper;
import cn.vfunding.vfunding.biz.message.dao.GiftboxMessageMapper;
import cn.vfunding.vfunding.biz.message.dao.HikesMessageMapper;
import cn.vfunding.vfunding.biz.message.dao.SystemMessageMapper;
import cn.vfunding.vfunding.biz.message.model.CashMessage;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.prize.dao.PrizeChooseMapper;
import cn.vfunding.vfunding.biz.prize.dao.PrizeChooseRuleLogMapper;
import cn.vfunding.vfunding.biz.prize.dao.PrizeChooseRuleMapper;
import cn.vfunding.vfunding.biz.prize.dao.PrizeMapper;
import cn.vfunding.vfunding.biz.prize.dao.PrizeRepositoryMapper;
import cn.vfunding.vfunding.biz.prize.model.Prize;
import cn.vfunding.vfunding.biz.prize.model.PrizeChoose;
import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRule;
import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRuleLog;
import cn.vfunding.vfunding.biz.prize.model.PrizeRepository;
import cn.vfunding.vfunding.biz.prize.service.IPrizeSeasonTwoService;
import cn.vfunding.vfunding.biz.prize.vo.ActivityPrizeVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

@Service("prizeSeasonTwoService")
public class PrizeSeasonTwoServiceImpl implements IPrizeSeasonTwoService{
	//奖品mapper
	@Autowired
	private PrizeMapper prizeMapper;
	//中奖mapper
	@Autowired
	private PrizeRepositoryMapper prizeRepositoryMapper;
	//抽奖mapper
	@Autowired
	private PrizeChooseMapper prizeChooseMapper;
	//抽奖规则mapper
	@Autowired
	private PrizeChooseRuleMapper prizeChooseRuleMapper;
	//抽奖规则日志mapper
	@Autowired
	private PrizeChooseRuleLogMapper prizeChooseRuleLogMapper;
	//礼品盒mapper
	@Autowired
	private GiftboxMessageMapper giftboxMessageMapper;
	//系统消息mapper
	@Autowired
	private SystemMessageMapper systemMessageMapper;
	//用户mapper
	@Autowired
	private UserMapper userMapper;
	//充值mapper
	@Autowired
	private AccountRechargeMapper accountRechargeMapper;
	//账户mapper
	@Autowired
	private AccountMapper accountMapper;
	//站内信mapper
	@Autowired
	private MessageMapper messageMapper;
	//投资mapper
	@Autowired
	private BorrowTenderMapper borrowTenderMapper;
	//提现抵扣券消息mapper
	@Autowired
	private CashMessageMapper cashMessageMapper;
	//加息卡消息mapper
	@Autowired
	private HikesMessageMapper hikesMessageMapper;
	//加息卡mapper
	@Autowired
	private HikesCardMapper hikesCardMapper;
	
//	@Value("${Season2TurnplateStart}")
//	private String season2TurnplateStart;
//	
//	@Value("${Season2TurnplateEnd}")
//	private String season2TurnplateEnd;
	
	//个人单日剩余抽奖次数
	private Integer PRIZE_CHOOSE_DAY_COUNT;
	//总优先级
	private BigDecimal PRIZE_PROBABILITY_TOTAL;
	//优先级基数
	private BigDecimal PRIZE_PROBABILITY_BASE = new BigDecimal(100);
	
	//特等奖
	private final static Integer PRIZE_TEDENG = 1006;
	//一等奖
	private final static Integer PRIZE_YIDENG = 1007;
	//二等奖
	private final static Integer PRIZE_ERDENG = 1008;
	//三等奖
	private final static Integer PRIZE_SANDENG = 1009;
	//四等奖
	private final static Integer PRIZE_SIDENG = 1010;
	//阳光奖
	private final static Integer PRIZE_YANGGUANG = 1011;
	

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.prizeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Prize record) {
		return this.prizeMapper.insert(record);
	}

	@Override
	public int insertSelective(Prize record) {
		return this.prizeMapper.insertSelective(record);
	}

	@Override
	public Prize selectByPrimaryKey(Integer id) {
		return this.prizeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Prize record) {
		return this.prizeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Prize record) {
		return this.prizeMapper.updateByPrimaryKey(record);
	}

	/**
	 * 抽取奖品
	 * @param user
	 * @param squad
	 * @return json
	 * @author louchen 2015-03-10
	 */
	@Override
	public synchronized Json choosePrize(UserSession user,HttpServletRequest request,String squad) {
		Json json = new Json();
		PRIZE_CHOOSE_DAY_COUNT = 0;
		PRIZE_PROBABILITY_TOTAL = new BigDecimal(0);
		if( EmptyUtil.isNotEmpty(user) && user.getUserId()>0 ){
			//查询奖品列表
			List<Prize> prizeList = this.prizeMapper.selectListBySquad(squad);
			if(prizeList.size()>0){
				//计算用户今日已抽奖次数
				PrizeChooseRule pcr = this.getUserPrizeChooseRule(user.getLoginUserId());
				PRIZE_CHOOSE_DAY_COUNT = pcr.getChooseCount()-pcr.getChooseRealityCount();
				if(PRIZE_CHOOSE_DAY_COUNT>0){
					//计算总优先级
					for(Prize p:prizeList){
						PRIZE_PROBABILITY_TOTAL = PRIZE_PROBABILITY_TOTAL.add(p.getProbability());
					}
					//抽奖
					json.setSuccess(true);
					String lastIp = ModelAndViewUtil.getIpAddr(request);
					PrizeRepository pr = this.ChoosePrizeMethod(user,lastIp,prizeList);
					if(EmptyUtil.isNotEmpty(pr)){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("prizeId", pr.getPrizeId());
						map.put("prizeName", pr.getPrize().getName().substring(0, 3));
						map.put("prizeTitle", this.getTipsTitle(pr.getPrizeId()));
						map.put("prizeDescription", this.getTipsDescription(pr.getPrizeId()));
						json.setObj(map);
						json.setMsg("1");
					}else{
						json.setMsg("0");
					}
					//实际抽奖次数+1
					pcr.setChooseRealityCount(pcr.getChooseRealityCount()+1);
					this.prizeChooseRuleMapper.updateByPrimaryKeySelective(pcr);	
				}else{
					json.setMsg("今日抽奖次数已用完！");
				}
			}else{
				json.setMsg(squad+"该活动组没有奖品！");
			}			
		}else{
			json.setMsg("您还没有登录！");
		}		
		return json;
	}

	/**
	 * 抽奖方法
	 * @param user
	 * @param prizeList
	 * @author louchen 2015-03-10
	 */
	private PrizeRepository ChoosePrizeMethod(UserSession user,String lastip,List<Prize> prizeList) { 
		BigDecimal r = new BigDecimal(Math.random());
		BigDecimal randomNumber =  PRIZE_PROBABILITY_BASE.multiply(r);
		randomNumber = randomNumber.setScale(2,BigDecimal.ROUND_HALF_UP);
		for(Prize p:prizeList){
			//抽中奖品
			if(p.getProbability().compareTo(randomNumber) >= 0
				&& p.getCount()-p.getRealityCount()>0){
				//用户有效投资总额
				BigDecimal userTenderTotal = borrowTenderMapper.sumTenderAccountByUserId(user.getUserId());
				//特等奖特殊规则(投资超过10w才能获得该奖)
				if(p.getId().equals(PRIZE_TEDENG)){
					if(userTenderTotal.compareTo(new BigDecimal(100000))<0){
						continue;
					}
				}
				//一等奖特殊规则(投资超过5k才能获得该奖)
				if(p.getId().equals(PRIZE_YIDENG)){
					if(userTenderTotal.compareTo(new BigDecimal(5000))<0){
						continue;
					}			
				}
				//二等奖特殊规则(投资超过1k才能获得该奖)
				if(p.getId().equals(PRIZE_ERDENG)){
					if(userTenderTotal.compareTo(new BigDecimal(1000))<0){
						continue;
					}			
				}
				//三等奖特殊规则(投资超过3w才能获得该奖)
				if(p.getId().equals(PRIZE_SANDENG)){
					if(userTenderTotal.compareTo(new BigDecimal(30000))<0){
						continue;
					}			
				}
				//所有奖品单个用户只能获得一次  不包括二等奖
				PrizeRepository pr = new PrizeRepository();
				pr.setPrizeId(p.getId());
				pr.setChooseUser(user.getUserId());
				List<PrizeRepository> prizeRepositoryList = this.prizeRepositoryMapper.selectByEntity(pr);
				if(prizeRepositoryList.size()>0){
					if(p.getId().equals(PRIZE_ERDENG)){
						//一共只能获取4次
						if(prizeRepositoryList.size()>4){
							continue;
						}
						//当天只能获取1次
						Date todayStart = DateUtil.getDateToString(DateUtil.parseDate(new Date())+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
						Boolean isExpire = false;
						for(PrizeRepository prEntity:prizeRepositoryList){
							if(prEntity.getChooseTime().compareTo(todayStart)>0){
								isExpire = true;
								break;
							}
						}	
						if(isExpire){
							continue;
						}
					}else{
						//其他奖品整个活动周期内只能获得一次
						continue;
					}		
				}				
				//更新奖品表奖品实际抽中数量+1
				p.setRealityCount(p.getRealityCount()+1);
				this.prizeMapper.updateByPrimaryKeySelective(p);
				//新增中奖记录
				pr = new PrizeRepository();
				pr.setSn(this.getPrizeRepositoryKey(p.getId()));  
				pr.setPrizeId(p.getId());
				if(p.getId().equals(PRIZE_YANGGUANG)){
					pr.setStatus(2);
				}else{
					pr.setStatus(1);
				}				
				pr.setChooseUser(user.getUserId());
				pr.setChooseIp(lastip);
				pr.setChooseTime(new Date());
				pr.setAddTime(new Date());
				pr.setAddIp(lastip);
				this.prizeRepositoryMapper.insertSelective(pr);
				pr.setPrize(p);
				//新增抽奖记录(抽中)
				PrizeChoose pc = new PrizeChoose();
				pc.setRepoId(pr.getId());
				pc.setUserId(user.getUserId());
				pc.setAddTime(new Date());
				pc.setAddIp(lastip);
				this.prizeChooseMapper.insertSelective(pc);
				//发送特等奖,500元红包
//				if(p.getId().equals(PRIZE_TEDENG)){
//					GiftboxMessage gm = new GiftboxMessage();
//					gm.setMoney(new BigDecimal(500)); 
//					gm.setTitle("小主，500元旅行基金到账啦！");
//					gm.setContent("恭喜您获得微积金幸运大转盘抽奖活动特等奖500元春季踏青旅行基金！点击“使用”即可充值到您的现金账户。");
//					gm.setTakeTime(new Date());
//					gm.setLoseTime(DateUtil.getAfterDateAsDate(new Date(), 7));
//					gm.setIsLook(0);
//					gm.setStatus(0);
//					gm.setType(0);
//					gm.setReceiveUser(user.getUserId());
//					gm.setAddtime(new Date());
//					gm.setAddip(lastip);
//					this.giftboxMessageMapper.insertSelective(gm);
//				}
				//发送一等奖,二等奖,2%或者1%加息卡
				if(p.getId().equals(PRIZE_YIDENG)||p.getId().equals(PRIZE_ERDENG)){
					BigDecimal hikes = new BigDecimal(0);
					String title = "";
					String content = "";
					if(p.getId().equals(PRIZE_YIDENG)){
						hikes = new BigDecimal(2);
						title = "恭喜您抽中幸运大转盘一等奖！";
						content = "微积金大转盘活动一等奖2%投资加息。获得之后可即刻投资使用，投资加息将于满标审核后以现金红包（可再次用于投资或者提现）的形式统一发放到礼品盒。";
					}else if(p.getId().equals(PRIZE_ERDENG)){
						hikes = new BigDecimal(1);
						title = "恭喜您抽中幸运大转盘二等奖！";
						content = "微积金大转盘活动二等奖1%投资加息。获得之后可即刻投资使用，投资加息将于满标审核后以现金红包（可再次用于投资或者提现）的形式统一发放到礼品盒。";
					}
					HikesCard hc = this.hikesCardMapper.selectByPrimaryKey(user.getUserId());
					hc.setUseRate(hc.getUseRate().add(hikes));
					this.hikesCardMapper.updateByPrimaryKeySelective(hc);
					HikesMessage hm = new HikesMessage();
					hm.setReceiveUser(user.getUserId());
					hm.setTitle(title);
					hm.setContent(content);
					hm.setRate(hikes);
					hm.setAddtime(new Date());
					this.hikesMessageMapper.insertSelective(hm);
				}
				//发送四等奖,5元现金红包
				if(p.getId().equals(PRIZE_SIDENG)){
					GiftboxMessage gm = new GiftboxMessage();
					gm.setMoney(new BigDecimal(5)); 
					gm.setTitle("恭喜您抽中幸运大转盘四等奖！");
					gm.setContent("微积金大转盘活动四等奖5元现金红包。生效后点击“使用”即可充值到您的现金账户。");
					gm.setTakeTime(new Date());
					gm.setLoseTime(DateUtil.getAfterDateAsDate(new Date(),30));
					gm.setIsLook(0);
					gm.setStatus(0);
					gm.setType(0);
					gm.setReceiveUser(user.getUserId());
					gm.setAddtime(new Date());
					gm.setAddip(lastip);
					this.giftboxMessageMapper.insertSelective(gm);
				}
				//发送阳光奖,提现抵扣券5元
				UserWithBLOBs modifyUser = this.userMapper.selectByPrimaryKey(user.getUserId());
				if(p.getId().equals(PRIZE_YANGGUANG)){
					if(EmptyUtil.isEmpty(modifyUser.getHongbao())){
						modifyUser.setHongbao(new BigDecimal(0));
					}
					modifyUser.setHongbao(modifyUser.getHongbao().add(new BigDecimal(5)));
					this.userMapper.updateByPrimaryKeySelective(modifyUser);
					CashMessage cm = new CashMessage();
					cm.setIsLook(0);
					cm.setTitle("恭喜您抽中幸运大转盘阳光奖！");
					cm.setContent("微积金大转盘阳光奖5元提现抵扣券。");
					cm.setMoney(new BigDecimal(5));
					cm.setSendUser(0);
					cm.setReceiveUser(user.getUserId());
					cm.setAddtime(new Date());
					cm.setAddip("127.0.0.1");
					this.cashMessageMapper.insertSelective(cm);
				}
				//发送系统消息
				SystemMessage sm = new SystemMessage();
				sm.setIsLook(0);
				sm.setType(0);
				sm.setReceiveUser(user.getUserId());
				sm.setAddtime(new Date());
				sm.setTitle(this.getTipsTitle(p.getId()));
				if(p.getId().equals(PRIZE_YANGGUANG)){
					sm.setContent("恭喜您获得微积金幸运大转盘抽奖活动阳光奖！5元提现抵扣券！系统已自动发送至您的账户,变更前提现抵扣券余额"+modifyUser.getHongbao().subtract(new BigDecimal(5))+"元,变更后提现抵扣券余额"+modifyUser.getHongbao()+"元。");
				}else{
					sm.setContent(this.getTipsDescription(p.getId()));
				}		
				this.systemMessageMapper.insertSelective(sm);				
				return pr;
			}
		}	
		//新增抽奖记录(未抽中)
		PrizeChoose pc = new PrizeChoose();
		pc.setUserId(user.getUserId());
		pc.setAddTime(new Date());
		pc.setAddIp(lastip);
		this.prizeChooseMapper.insertSelective(pc);
		return null;
	}
	
	private String getTipsTitle(Integer id){
		if(id.equals(PRIZE_TEDENG)){
			return "恭喜您抽中500元旅行基金！";
		}else if(id.equals(PRIZE_YIDENG)){
			return "恭喜您抽中幸运大转盘一等奖！";
		}else if(id.equals(PRIZE_ERDENG)){
			return "恭喜您抽中幸运大转盘二等奖！";
		}else if(id.equals(PRIZE_SANDENG)){
			return "恭喜您抽中幸运大转盘三等奖！";
		}else if(id.equals(PRIZE_SIDENG)){
			return "恭喜您抽中幸运大转盘四等奖！";
		}else if(id.equals(PRIZE_YANGGUANG)){
			return "恭喜您抽中幸运大转盘阳光奖！";
		}
		return null;
	}
	
	private String getTipsDescription(Integer id){
		if(id.equals(PRIZE_TEDENG)){
			return "恭喜您获得微积金幸运大转盘抽奖活动特等奖500元春季踏青旅行基金！客服人员会在活动结束以后五个工作日内与您联系，请您保持通讯畅通！";
		}else if(id.equals(PRIZE_YIDENG)){
			return "微积金大转盘活动一等奖2%投资加息。获得之后可即刻投资使用，投资加息将于满标审核后以现金红包（可再次用于投资或者提现）的形式统一发放到礼品盒。";
		}else if(id.equals(PRIZE_ERDENG)){
			return "微积金大转盘活动二等奖1%投资加息。获得之后可即刻投资使用，投资加息将于满标审核后以现金红包（可再次用于投资或者提现）的形式统一发放到礼品盒。";
		}else if(id.equals(PRIZE_SANDENG)){
			return "恭喜您获得微积金幸运大转盘抽奖活动三等奖！微积金精美笔记本一本！微积金将在活动结束后7个工作日内联系并快递至您指定地址！请您保持通讯畅通！";
		}else if(id.equals(PRIZE_SIDENG)){
			return "微积金大转盘活动四等奖5元现金红包。";
		}else if(id.equals(PRIZE_YANGGUANG)){
			return "微积金大转盘阳光奖5元提现抵扣券。";
		}
		return null;
	}
	
	/**
	 * 模拟抽奖X次
	 * @param user
	 * @param prizeList
	 * @author louchen 2015-03-10
	 */
	@SuppressWarnings("unused")
	private void simulateChoosePrizeMethod(UserSession user,List<Prize> prizeList) {
		int x=5000;
		int j = 0;
		int a=0,b=0,c=0,d=0,e=0,f=0;
		for(int i=1;i<=x;i++){
			BigDecimal r = new BigDecimal(Math.random());
			BigDecimal randomNumber =  PRIZE_PROBABILITY_BASE.multiply(r);
			randomNumber = randomNumber.setScale(2,BigDecimal.ROUND_HALF_UP);
			for(Prize p:prizeList){
				if(p.getProbability().compareTo(randomNumber) >= 0
					&& p.getCount()-p.getRealityCount()>0){
					j++;	
					if(p.getId().equals(PRIZE_TEDENG)){
						a++;
					}
					if(p.getId().equals(PRIZE_YIDENG)){
						b++;
					}
					if(p.getId().equals(PRIZE_ERDENG)){
						c++;
					}
					if(p.getId().equals(PRIZE_SANDENG)){
						d++;
					}
					if(p.getId().equals(PRIZE_SIDENG)){
						e++;
					}
					if(p.getId().equals(PRIZE_YANGGUANG)){
						f++;
					}
					break;
				}
			}						
		}
		System.out.println("一共抽中a="+a);
		System.out.println("一共抽中b="+b);
		System.out.println("一共抽中c="+c);
		System.out.println("一共抽中d="+d);
		System.out.println("一共抽中e="+e);
		System.out.println("一共抽中f="+f);
		System.out.println("一共抽中"+j);
	}
	
	/**
	 * 获取用户抽奖规则
	 * @param user
	 * @return
	 * @author louchen 2015-03-10
	 */
	@Override
	public PrizeChooseRule getUserPrizeChooseRule(Integer userId){
		PrizeChooseRule pcr = this.prizeChooseRuleMapper.selectByPrimaryKey(userId);
		if(EmptyUtil.isNotEmpty(pcr)){
			if(!pcr.getModifyTime().equals(DateUtil.getDateToString(DateUtil.parseDate(new Date()),"yyyy-MM-dd")) 
					&& pcr.getModifyTime().compareTo(DateUtil.getDateToString(DateUtil.parseDate(new Date()),"yyyy-MM-dd")) < 0
					){
				//重置抽奖日期和抽奖次数
				pcr.setChooseCount(0);
				pcr.setChooseRealityCount(0);
				pcr.setModifyTime(DateUtil.getDateToString(DateUtil.parseDate(new Date()),"yyyy-MM-dd"));
				this.prizeChooseRuleMapper.updateByPrimaryKeySelective(pcr);
			}
		}else{
			//第一次抽奖
			pcr = new PrizeChooseRule();
			pcr.setUserId(userId);
			pcr.setChooseCount(0);
			pcr.setChooseRealityCount(0);
			pcr.setModifyTime(DateUtil.getDateToString(DateUtil.parseDate(new Date()),"yyyy-MM-dd"));
			this.prizeChooseRuleMapper.insertSelective(pcr);
		}
		return pcr;
	}
	
	/**
	 * 获取奖品库id
	 * @param i
	 * @return
	 * @author louchen 2015-03-10
	 */
	private String getPrizeRepositoryKey(int i) {
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String prefix = "PR";
		Long id = new Long(simpleDateFormat.format(date))+i;
		String str = id+""; 
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		return prefix+rannum + str;
	}
	
	/**
	 * 增加抽奖次数
	 * @param userId,type
	 * @return
	 * @author louchen 2015-03-10
	 */
	@Override
	public PrizeChooseRule addUserPrizeChooseCount(Integer userId,Integer type) {
		PrizeChooseRule pcr = this.getUserPrizeChooseRule(userId);
		PrizeChooseRuleLog search = new PrizeChooseRuleLog();
		search.setUserId(userId);
		search.setType(type);
		search.setAddtime(DateUtil.getDateToString(DateUtil.parseDate(new Date()), "yyyy-MM-dd"));
		PrizeChooseRuleLog pcrl = this.prizeChooseRuleLogMapper.selectByEntity(search);
		if(EmptyUtil.isEmpty(pcrl)){
			pcr.setChooseCount(pcr.getChooseCount()+1);
			this.prizeChooseRuleMapper.updateByPrimaryKeySelective(pcr);
			pcrl = new PrizeChooseRuleLog();
			pcrl.setUserId(userId);
			pcrl.setType(type);
			pcrl.setAddtime(new Date());
			this.prizeChooseRuleLogMapper.insertSelective(pcrl);
		}
		return pcr;
	}

	@Override
	public List<ActivityPrizeVO> selectRegisterAndSetRealNameFromInvite() {
		return this.prizeMapper.selectRegisterAndSetRealNameFromInvite();
	}

	@Override
	public List<ActivityPrizeVO> selectTenderFromInvite() {
		return this.prizeMapper.selectTenderFromInvite();
	}

	@Override
	public List<ActivityPrizeVO> selectAccountFromInvite() {
		return this.prizeMapper.selectAccountFromInvite();
	}

}

