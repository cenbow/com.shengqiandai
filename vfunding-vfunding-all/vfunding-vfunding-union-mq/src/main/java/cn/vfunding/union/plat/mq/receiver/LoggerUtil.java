package cn.vfunding.union.plat.mq.receiver;

import org.slf4j.Logger;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class LoggerUtil {
	public static void doLogger(Logger logger,String result){
		if (EmptyUtil.isNotEmpty(result) && result.equals("success")) {
			logger.info("网站联盟用户同步微积金成功");			
		} else {
			logger.error("网站联盟用户同步微积金发生错误");
		}
	}
}
