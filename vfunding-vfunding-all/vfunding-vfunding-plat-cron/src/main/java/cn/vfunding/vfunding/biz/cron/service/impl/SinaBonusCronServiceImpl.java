package cn.vfunding.vfunding.biz.cron.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.cron.service.ISinaBonusCronService;
import cn.vfunding.vfunding.biz.sina.service.ISinaBonusService;

@Service("sinaBonusCronService")
public class SinaBonusCronServiceImpl implements ISinaBonusCronService {

	@Value("${sinaBonus.cron.time}")
	private String sinaBonusCronTime;
	
	@Value("${sinaBonusSync.cron.time}")
	private String sinaBonusSyncCronTime;
	
	@Autowired
	private ISinaBonusService sinaBonusService;
	
	@Override
	public String loadCreate() {
		sinaBonusService.loadCreateBaseSinaSettlementData();
		return sinaBonusCronTime;
	}

	@Override
	public String loadSync() {
		sinaBonusService.loadSyncSinaSettlementData();
		return sinaBonusSyncCronTime;
	}

}
