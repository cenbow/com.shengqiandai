package cn.vfunding.vfunding.prd.www.util.financialPlanner;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author wang.zeyan
 * @date 2015年3月3日
 * @version 1.0
 */
public class FinancialPlannerUtil {

	
	/**
	 * 初窥门径
	 */
	public final static int CKMJ = 0;
	/**
	 * 理财师
	 */
	public final static int LCS = 1;
	
	public final static String CKMJ_STR="初窥门径";
	public final static String LCS_STR="理财师";
	public static Map<Integer,Object> fpLevelMap = new HashMap<Integer,Object>();
	static{
		fpLevelMap.put(CKMJ, CKMJ_STR);
		fpLevelMap.put(LCS, LCS_STR);
	}
	
}
