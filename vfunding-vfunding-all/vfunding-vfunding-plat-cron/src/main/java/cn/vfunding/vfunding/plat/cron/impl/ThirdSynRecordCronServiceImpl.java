package cn.vfunding.vfunding.plat.cron.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvoker;
import cn.vfunding.vfunding.biz.system.model.ThirdSynRecord;
import cn.vfunding.vfunding.biz.system.service.IThirdSynRecordService;
import cn.vfunding.vfunding.plat.cron.interfaces.IThirdSynRecordCronService;
import cn.vfunding.vfunding.plat.cron.vo.CJDMessage;

import com.alibaba.fastjson.JSON;

@Component("thirdSynRecordCronService")
public class ThirdSynRecordCronServiceImpl implements
		IThirdSynRecordCronService {

	@Value("${thirdSynRecord.time}")
	private String thirdSynRecordTime;
	@Autowired
	private IThirdSynRecordService thirdSynRecordService;

	@Override
	public String thirdSysRecord() {
		List<ThirdSynRecord> records = this.thirdSynRecordService
				.selectNeedSyn();
		if (EmptyUtil.isNotEmpty(records)) {
			RestInvoker f = new RestInvoker();
			for (ThirdSynRecord thirdSynRecord : records) {
				if (EmptyUtil.isNotEmpty(thirdSynRecord.getExctNum())) {
					thirdSynRecord.setExctNum(thirdSynRecord.getExctNum()
							.intValue() + 1);
				} else {
					thirdSynRecord.setExctNum(1);
				}
				if (EmptyUtil.isNotEmpty(thirdSynRecord.getUrl())) {
					f.setBaseURL(thirdSynRecord.getUrl());
					String result = f
							.post("?" + thirdSynRecord.getArgs(), null);
					if (EmptyUtil.isNotEmpty(result)) {
						CJDMessage message = JSON.parseObject(result,
								CJDMessage.class);
						if (message.getCode().equals("0")) {
							thirdSynRecord.setStatus(0);
						}
					}
				}

				if (thirdSynRecord.getExctNum().intValue() <= 5) {
					Date newDate = DateUtil.getAfterDateByMINUTE(
							thirdSynRecord.getNextTime(), 1);
					thirdSynRecord.setNextTime(newDate);
				} else {
					int num = thirdSynRecord.getExctNum().intValue();
					int addMin = num * 2;
					Date newDate = DateUtil.getAfterDateByMINUTE(
							thirdSynRecord.getNextTime(), addMin);
					thirdSynRecord.setNextTime(newDate);
				}

				if (thirdSynRecord.getExctNum() >= 10
						&& thirdSynRecord.getStatus() == 1) {
					thirdSynRecord.setStatus(2);
				}

				this.thirdSynRecordService.updateByPrimaryKey(thirdSynRecord);

			}
		}
		return thirdSynRecordTime;
	}

}
