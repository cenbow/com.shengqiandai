package cn.vfunding.vfunding.biz.inviteCode.status;

import java.util.HashMap;
import java.util.Map;

public class DialogStatusIndex extends StatusIndex{

	
	/**
	 * 邀请好友(第一位好友注册成功) dialog状态位索引
	 */
	public final static int inviteFriendsFirstDialogIndex = 1;
	
	/**
	 * 好友第一个投资 dialog状态位索引
	 */
	public final static int friendsFirstInvestmentDialogIndex = 2;
	
	
	/**
	 * JJ斗地主二期 周赛赛参赛码发送状态
	 */
	public final static int JJ2WeekEntryCodeSendStatus = 3;
	
	/**
	 * JJ斗地主二期 月赛参赛码发送状态
	 */
	public final static int JJ2MonthEntryCodeSendStatus = 4;
	
	/**
	 * JJ斗地主二期 活动期间内用户是否扫描状态
	 */
	public final static int JJ2ScanUserStatus = 5;
	
	
	
	public static Map<Integer , String> dialogMap = new HashMap<Integer , String>(); 
	static{
		dialogMap.put(inviteFriendsFirstDialogIndex , "inviteFriendsFirstDialog");
		dialogMap.put(friendsFirstInvestmentDialogIndex , "friendsFirstInvestmentDialog");
	}
}
