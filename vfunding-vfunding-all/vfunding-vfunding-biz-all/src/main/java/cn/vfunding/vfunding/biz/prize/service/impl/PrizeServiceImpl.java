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
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.message.dao.GiftboxMessageMapper;
import cn.vfunding.vfunding.biz.message.dao.SystemMessageMapper;
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
import cn.vfunding.vfunding.biz.prize.service.IPrizeService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

@Service("prizeService")
public class PrizeServiceImpl implements IPrizeService{
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
	
	//活动开始日期
//	@Value("${AnniversarySeason1TurnplateStart}")
//	private String anniversarySeason1TurnplateStart;
	
	//活动结束日期
//	@Value("${AnniversarySeason1TurnplateEnd}")
//	private String anniversarySeason1TurnplateEnd;
	
	//个人单日剩余抽奖次数
	private Integer PRIZE_CHOOSE_DAY_COUNT;
	//总优先级
	private BigDecimal PRIZE_PROBABILITY_TOTAL;
	//优先级基数
	private BigDecimal PRIZE_PROBABILITY_BASE = new BigDecimal(100);

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
	 * @author louchen 2014-12-16
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
	 * @author louchen 2014-12-26
	 */
	private PrizeRepository ChoosePrizeMethod(UserSession user,String lastip,List<Prize> prizeList) { 
		BigDecimal r = new BigDecimal(Math.random());
		BigDecimal randomNumber =  PRIZE_PROBABILITY_BASE.multiply(r);
		randomNumber = randomNumber.setScale(2,BigDecimal.ROUND_HALF_UP);
		for(Prize p:prizeList){
			//抽中奖品
			if(p.getProbability().compareTo(randomNumber) >= 0
				&& p.getCount()-p.getRealityCount()>0){
				//特等奖特殊规则(净资产超过10w才能获得该奖,集团用户最多3个,一共10个)
				if(p.getId().equals(1000)){
					if(this.accountMapper.selectByUserId(user.getUserId()).getTotal().compareTo(new BigDecimal(100000))<0){
						continue;
					}else{
						//当前登录用户是内部用户，且内部用户已获得这个奖品超过3次
						if((user.getTypeId().equals(27)||user.getTypeId().equals(31))
							&& this.prizeRepositoryMapper.selectInternalUserPrizeCount(p.getId())>3){
								continue;
						}	
					}
				}
				//一等奖特殊规则(净资产超过5w才能获得该奖,集团用户最多15个,一共30个)
				if(p.getId().equals(1001)){
					if(this.accountMapper.selectByUserId(user.getUserId()).getTotal().compareTo(new BigDecimal(50000))<0){
						continue;
					}else{
						//当前登录用户是内部用户，且内部用户已获得这个奖品超过15次
						if((user.getTypeId().equals(27)||user.getTypeId().equals(31))
							&& this.prizeRepositoryMapper.selectInternalUserPrizeCount(p.getId())>15){
							continue;
						}						
					}					
				}
				//四等奖特殊规则(抽奖用户必须充值>=500元才能获得该奖)		
				if(p.getId().equals(1004)
					&& new BigDecimal(this.accountRechargeMapper.selectCountHistoryByUserId(user.getUserId()))
					.compareTo(new BigDecimal(500))<0){
					continue;
				}
				//所有奖品单个用户只能获得一次,1003三等奖投资提升10%不在这个规则内。
				PrizeRepository pr = new PrizeRepository();
				pr.setPrizeId(p.getId());
				pr.setChooseUser(user.getUserId());
				List<PrizeRepository> prizeRepositoryList = this.prizeRepositoryMapper.selectByEntity(pr);
				if(prizeRepositoryList.size()>0){
					if(p.getId().equals(1003)){
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
				if(p.getId().equals(1005)){
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
				//发送提现抵扣券5元
				UserWithBLOBs modifyUser = this.userMapper.selectByPrimaryKey(user.getUserId());
				if(p.getId().equals(1005)){
					if(EmptyUtil.isEmpty(modifyUser.getHongbao())){
						modifyUser.setHongbao(new BigDecimal(0));
					}
					modifyUser.setHongbao(modifyUser.getHongbao().add(new BigDecimal(5)));
					this.userMapper.updateByPrimaryKeySelective(modifyUser);
				}
				//发送系统消息
				SystemMessage sm = new SystemMessage();
				sm.setIsLook(0);
				sm.setType(0);
				sm.setReceiveUser(user.getUserId());
				sm.setAddtime(new Date());
				sm.setTitle(this.getTipsTitle(p.getId()));
				if(p.getId().equals(1005)){
					sm.setContent("恭喜您获得微积金壹周年庆幸运大转盘抽奖活动阳光奖！5元提现抵扣券！系统已自动发送至您的账户,变更前提现抵扣券余额"+modifyUser.getHongbao().subtract(new BigDecimal(5))+"元,变更后提现抵扣券余额"+modifyUser.getHongbao()+"元。");
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
		if(id.equals(1000)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动特等奖500元加油卡！";
		}else if(id.equals(1001)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动一等奖！";
		}else if(id.equals(1002)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动二等奖！";
		}else if(id.equals(1003)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动三等奖！";
		}else if(id.equals(1004)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动四等奖！";
		}else if(id.equals(1005)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动阳光奖！";
		}
		return null;
	}
	
	private String getTipsDescription(Integer id){
		if(id.equals(1000)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动特等奖500元加油卡！客服人员会在活动结束以后五个工作日内与您联系，请您保持通讯畅通！";
		}else if(id.equals(1001)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动一等奖！50%收益提升！一等奖奖励有效时间周期为自中奖日起7日内有效。奖励收益将在满标审核后次日发送至礼品盒！一等奖奖励限制单笔投资10万元以下，超过10万元的部分按照正常收益计算。";
		}else if(id.equals(1002)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动二等奖！荣昌E袋洗价值30元体验券一张！体验券券号和兑换地址将在次日发送至消息中心！";
		}else if(id.equals(1003)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动三等奖！10%收益提升！三等奖奖励有效时间周期为自中奖日起当日内有效。奖励收益将在满标审核后次日发送至礼品盒！";
		}else if(id.equals(1004)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动四等奖！5元现金红包！现金红包将在次日发送至礼品盒！您可以直接用于投资或者提现！";
		}else if(id.equals(1005)){
			return "恭喜您获得微积金壹周年庆幸运大转盘抽奖活动阳光奖！5元提现抵扣券！系统已自动发送至您的账户";
		}
		return null;
	}
	
	/**
	 * 模拟抽奖X次
	 * @param user
	 * @param prizeList
	 * @author louchen 2014-12-18
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
					if(p.getId()==1000){
						a++;
					}
					if(p.getId()==1001){
						b++;
					}
					if(p.getId()==1002){
						c++;
					}
					if(p.getId()==1003){
						d++;
					}
					if(p.getId()==1004){
						e++;
					}
					if(p.getId()==1005){
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
	 * @author louchen 2014-12-16
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
	 * @author louchen 2014-12-18
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
	 * @author louchen 2014-12-29
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
}
