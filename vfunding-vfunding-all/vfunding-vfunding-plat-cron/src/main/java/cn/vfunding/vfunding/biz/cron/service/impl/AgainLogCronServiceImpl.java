package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvoker;
import cn.vfunding.vfunding.biz.cron.service.IAgainLogCronService;
import cn.vfunding.vfunding.biz.redislog.model.AgainLog;
import cn.vfunding.vfunding.biz.redislog.service.IAgainLogService;

@Service("againLogsCronService")
public class AgainLogCronServiceImpl implements IAgainLogCronService {
	@Autowired
	private IAgainLogService againLogService;

	@Value("${retry.request.cron.time}")
	private String retryRequestTime;

	@Override
	public String retryRequest() {
		try {
			List<AgainLog> logs = this.againLogService.selectByAuto();
			RestInvoker invoker = null;
			for (AgainLog againLog : logs) {
				try {
					if (EmptyUtil.isNotEmpty(againLog.getNextTime())) {
						if (DateUtil.checkMax(new Date(),
								againLog.getNextTime())) {
							if (!againLog.isLocked()) {
								againLog.setLocked(true);
								// 锁定该补发信息
								this.againLogService
										.updateByPrimaryKey(againLog);
								if (EmptyUtil.isNotEmpty(againLog.getUrl())) {
									invoker = new RestInvoker();
									invoker.setBaseURL(againLog.getUrl());
									String res = "";
									if (againLog.getRequestMethod().equals(
											"post")) {
										res = invoker.post("",
												againLog.getArg());
									} else {
										List<NameValuePair> list = this
												.createGetParamByStringArg(againLog
														.getArg());
										res = invoker.get("", list);
									}
									if (EmptyUtil.isNotEmpty(res)
											&& res.equals("success")) {
										this.againLogService
												.deleteByPrimaryKey(againLog
														.getId());
									} else {
										this.doAagainLogInfoByError(againLog);
									}
								}
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					this.doAagainLogInfoByError(againLog);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retryRequestTime;
	}

	private void doAagainLogInfoByError(AgainLog againLog) throws Exception {
		againLog.setLocked(false);
		againLog.setCount(againLog.getCount() + 1);
		if (againLog.getCount().intValue() >= 5) {
			int num = againLog.getCount().intValue();
			int addMin = num;
			Date newDate = DateUtil.getAfterDateByMINUTE(
					againLog.getNextTime(), addMin);
			againLog.setNextTime(newDate);
		}
		if (againLog.getCount().intValue() >= 20 && againLog.getStuts() == 0) {
			againLog.setStuts(1);
		}
		this.againLogService.updateByPrimaryKey(againLog);
	}

	private List<NameValuePair> createGetParamByStringArg(String arg) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (arg.contains("&")) {
			String[] as = arg.split("&");
			for (String string : as) {
				String[] f = string.split("=");
				list.add(new BasicNameValuePair(f[0], f[1]));
			}
		} else {
			if (arg.contains("=")) {
				String[] f = arg.split("=");
				list.add(new BasicNameValuePair(f[0], f[1]));
			}
		}
		return list;
	}

}
