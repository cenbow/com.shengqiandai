package cn.vfunding.vfunding.biz.week.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.dao.ThirdSynRecordMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekMapper;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawnPic;
import cn.vfunding.vfunding.biz.week.model.WeekRate;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowPawnPicService;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowPawnService;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowService;
import cn.vfunding.vfunding.biz.week.service.IWeekRateService;
import cn.vfunding.vfunding.biz.week.service.IWeekService;
import cn.vfunding.vfunding.biz.week.vo.TrialBorrowVO;
import cn.vfunding.vfunding.biz.week.vo.WeekVO;
@Service("weekService")
public class WeekServiceImpl implements IWeekService{
	@Autowired
	private WeekMapper weekMapper;
	@Autowired
	private IWeekBorrowService weekBorrowService;
	@Autowired
	private IWeekRateService weekRateService;	
	@Autowired
	private IWeekBorrowPawnService weekBorrowPawnService;
	@Autowired
	private IWeekBorrowPawnPicService weekBorrowPawnPicService;
	@Autowired 
	private ThirdSynRecordMapper thirdSynRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.weekMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Week record) {
		return this.weekMapper.insert(record);
	}

	@Override
	public int insertSelective(Week record) {
		return this.weekMapper.insertSelective(record);
	}

	@Override
	public Week selectByPrimaryKey(Integer id) {
		
		return this.weekMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Week record) {
		return weekMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Week record) {
		return this.weekMapper.updateByPrimaryKey(record);
	}

	@Override
	public Week selectWeekAndBorrowById(Integer id) {
		return weekMapper.selectWeekAndBorrowById(id);
	}


	@Override
	public int weekSubTrial(Week week) {
		WeekBorrow wb = new WeekBorrow();
		wb.setStatus(1);
		wb.setSaveStatus(1);
		wb.setWeekId(week.getId());
		weekBorrowService.weekBorrowSubTrial(wb);
		return weekMapper.updateByPrimaryKeySelective(week);
	}

	@Override
	public List<Week> selectWeekListPage(PageSearch pageSearch) {
		return this.weekMapper.selectWeekListPage(pageSearch);
	}
	
	@Override
	public List<WeekVO> selectWeekVOListPage(PageSearch pageSearch) {
		return this.weekMapper.selectWeekVOListPage(pageSearch);
	}

	/**
	 * 保存week和weekRate
	 * @author louchen 2014-12-11
	 */
	@Override
	public Json saveWeek(Week week,WeekRate weekRate) {
		Json json = new Json();
		// 计算【年化收益率】 公式:计划年化收益率=产品年化收益率-服务费率-担保费率
		week.setApr(weekRate.getWeekRate().subtract(weekRate.getPlatformRate().add(weekRate.getGuaranteeRate())));
		// 计算【计划总金额】 公式:计划总金额=计划总份数*每份价值
		week.setPlanMoney(week.getSharePrice().multiply(
				new BigDecimal(week.getShareCount())));
		if (EmptyUtil.isEmpty(week.getId())) {
			// 新增week
			week.setCreateUser(EmployeeSession.getEmpSessionEmpId());
			week.setCreateTime(new Date());
			weekRate.setAddTime(new Date());
			this.insertSelective(week);
			json.setSuccess(true);

		} else {
			// 更新week
			this.updateByPrimaryKeySelective(week);
			json.setSuccess(true);
		}
		//save weekRate
		weekRate.setWeekId(week.getId());
		if(EmptyUtil.isEmpty(this.weekRateService.selectByPrimaryKey(weekRate.getWeekId()))){
			this.weekRateService.insertSelective(weekRate);
		}else{
			this.weekRateService.updateByPrimaryKeySelective(weekRate);
		}		
		return json;
	}

	@Override
	public List<Week> selectWeekForBuyListPage(PageSearch pageSearch) {
		return this.weekMapper.selectWeekForBuyListPage(pageSearch);
	}

	@Override
	public void updateRealityMoneyByWeekId(Integer id) {
		this.weekMapper.updateRealityMoneyByWeekId(id);
		
	}

	@Override
	public Week selectIndexWeek() {
		return weekMapper.selectIndexWeek();
	}

	@Override
	public Json updateWeekStatus(Week week, TrialBorrowVO tbv, String trialStatus) {
		Json j = new Json();
		int i = this.updateByPrimaryKeySelective(week);
		int b = 1;
		if (!tbv.getWbs().isEmpty()) {
			for (WeekBorrow wb : tbv.getWbs()) {
				wb.setVerifyUser(EmployeeSession.getEmpSessionEmpId());
				wb.setVerifyTime(new Date());
				//不为save状态时将 weekBorrow的 status  saveStatus 这是一致进行保存
				if (!trialStatus.equals("save")) {
					if(trialStatus.equals("ok")){
						wb.setVerifyRemark("");
					}
					wb.setStatus(wb.getSaveStatus());
				}
				b = weekBorrowService.updateByPrimaryKeySelective(wb);
			}
		}
		if (i > 0 && b > 0) {
			j.setSuccess(true);
			j.setMsg("操作成功.");
		} else {
			j.setSuccess(false);
			j.setMsg("系统异常,审核失败.");
		}
		return j;
	}

	@Override
	public Week selectByWeekPreview(Integer id) {
		Week week = this.weekMapper.selectByPrimaryKey(id);
		week.setWr(weekRateService.selectByPrimaryKey(id));
		List<WeekBorrow> wbs = weekBorrowService.selectWeekBorrowByWeekId(week
				.getId());
		if (!wbs.isEmpty()) {
			for (WeekBorrow w : wbs) {
				WeekBorrowPawn wbp = weekBorrowPawnService.selectByBorrowId(w
						.getId());
				if (wbp != null) {
					List<WeekBorrowPawnPic> wbpcs = weekBorrowPawnPicService
							.selectPicByPawnId(wbp.getId());
					if (!wbpcs.isEmpty()) {
						wbp.setWeekBorrowPawnPicList(wbpcs);
					}
					w.setWeekBorrowPawn(wbp);
				}
			}
			week.setWeekBorrowList(wbs);
		}
		return week;
	}  
	
	
	
	
}