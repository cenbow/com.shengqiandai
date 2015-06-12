package cn.vfunding.common.framework.workflow.apply;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建流程申请号工具类
 * @author liuyijun
 *
 */
public class ApplyCodeUtil {

	/**
	 * 获取申请流程的申请号
	 * @param key
	 * @return
	 */
	public static String getCodeBykey(String key){
		StringBuffer value=new StringBuffer();
		String keyUp=key.toUpperCase();
		Date now=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr=dateFormat.format(now);
		long nowMillis=System.currentTimeMillis();
		dateStr+=String.valueOf(nowMillis);
		value.append(keyUp);
		value.append(dateStr);
		return value.toString();
	}
}
