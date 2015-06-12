package cn.vfunding.vfunding.biz.message.beanutil;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;

/**
 * 系统消息和红包消息和各种消息的工具类
 * @author louchen 2015-04-21
 *
 */
public class MessageUtil {
	
	/**
	 * 发送人ID，0=system,系统发送
	 */
	public final static Integer Send_User_Vfunding = 0;
	
	/**
	 * 未使用红包
	 */
	public final static Integer Status_Unuse_Message_Vfunding = 0;
	
	/**
	 * 已使用红包
	 */
	public final static Integer Status_Use_Message_Vfunding = 1;
	
	/**
	 * 已过期红包
	 */
	public final static Integer Status_Expire_Message_Vfunding = 2;
	
	/**
	 * 未查看消息
	 */
	public final static Integer Status_Unlook_Message_Vfunding = 0;
	
	/**
	 * 已查看消息
	 */
	public final static Integer Status_Look_Message_Vfunding = 1;
	
	/**
	 * 活动红包
	 */
	public final static Integer Type_Activity_Message_Vfunding = 0;
	
	/**
	 * 集团内部返利红包
	 */
	public final static Integer Type_TenderReturn_Message_Vfunding = 1;
	
	/**
	 * 系统消息type
	 */
	public final static Integer Type_Normal_SystemMessage_Vfunding = 0;
	
	/**
	 * 财经道注册送的红包金额
	 */
	public final static BigDecimal Money_Register_Cjd = new BigDecimal(7);
	
	/**
	 * 财经道注册系统消息标题
	 */
	public final static String Title_SystemMessage_Cjd = "财经道注册红包";
	
	/**
	 * 财经道注册红包标题
	 */
	public final static String Title_GiftboxMessage_Cjd = "财经道"+Money_Register_Cjd.toString()+"元注册红包激活";
	
	/**
	 * 财经道注册系统消息内容
	 */
	public final static String Content_SystemMessage_Cjd = "恭喜您已获得财经注册红包12元，完成一次投资后可激活使用。激活后红包会自动加入礼品盒内。";
	
	/**
	 * 财经道注册红包内容
	 */
	public final static String Content_GiftboxMessage_Cjd = "您已激活"+Money_Register_Cjd.toString()+"元注册红包，点击“使用”即可充值到您的账户余额。";
	
	/**
	 * 破两亿活动0.28加息
	 */
	public final static BigDecimal Rate_Activity_TwoMillons = new BigDecimal(0.28);
	
	/**
	 * 破两亿活动_加息卡_标题
	 */
	public final static String Title_HikesMessage_Activity_TwoMillons = "破两亿活动加息奖励！";
	
	/**
	 * 破两亿活动_加息卡_内容
	 */
	public final static String Content_HikesMssage_Activity_TwoMillons = "感谢您参与破两亿红包活动，成功获得0.28%加息，获得后可投资使用,投资加息将于满标审核后以现金红包（可用于投资或提现）的形式统一发放到礼品盒。";
		
	/**
	 * 返回当前时间
	 * @return
	 */
	public final static Date getCurrentDate(){
		return new Date();
	}
	
	/**
	 * 返回红包失效时间
	 * @return
	 */
	public final static Date getCjdActivityLoseDate(){
		return getLoseDate(90);
	}
	
	/**
	 * 返回红包失效时间
	 * @return
	 */
	public final static Date getLoseDate(){
		return getLoseDate(15);
	}
	
	/**
	 * 根据参数返回当前时间后X天
	 * @param day
	 * @return
	 */
	public final static Date getLoseDate(Integer day){
		return DateUtil.getNextDayTime(new Date(), day);
	}
	
	//==================================================消息创建=======================================
	private static Factory factory = null;
	
	private MessageUtil(){
		if(factory==null){
			factory = new Factory();
		}
	}
	
	/**
	 * 创建各种消息的内部类
	 * @return
	 */
	public static Factory getMessageFactory(){
		if(factory==null){
			new MessageUtil();
		}
		return factory;
	}
	
	public class Factory{
		
		public Factory(){}
		
		/**
		 * 创建系统消息
		 * @param userId
		 * @param title
		 * @param content
		 * @return
		 */
		public SystemMessage createBaseSystemMessage(Integer userId,String title,String content){
			SystemMessage sm = new SystemMessage();
			sm.setSendUser(MessageUtil.Send_User_Vfunding);
			sm.setAddtime(MessageUtil.getCurrentDate());
			sm.setIsLook(MessageUtil.Status_Unlook_Message_Vfunding);
			sm.setType(MessageUtil.Type_Normal_SystemMessage_Vfunding);
			sm.setReceiveUser(userId);
			sm.setTitle(title);
			sm.setContent(content);
			return sm;
		}
		
		/**
		 * 创建红包消息
		 * 金额、生效时间、失效时间未赋值(重要)
		 * @param userId
		 * @param title
		 * @param content
		 * @return
		 */
		public GiftboxMessage createBaseGiftboxMessage(Integer userId,String title,String content){
			GiftboxMessage gm = new GiftboxMessage();
			gm.setSendUser(MessageUtil.Send_User_Vfunding);
			gm.setAddtime(MessageUtil.getCurrentDate());
			gm.setIsLook(MessageUtil.Status_Unlook_Message_Vfunding);
			gm.setStatus(MessageUtil.Status_Unuse_Message_Vfunding);
			gm.setType(MessageUtil.Type_Activity_Message_Vfunding);
			gm.setAddip("127.0.0.1");
			gm.setReceiveUser(userId);
			gm.setTitle(title);
			gm.setContent(content);
			return gm;
		}
		
		/**
		 * 创建加息卡消息
		 * 加息额度未赋值(重要)
		 * @param userId
		 * @param title
		 * @param content
		 * @return
		 */
		public HikesMessage createBaseHikesMessage(Integer userId,String title,String content){
			HikesMessage hm = new HikesMessage();
			hm.setSendUser(MessageUtil.Send_User_Vfunding);
			hm.setAddip("127.0.0.1");
			hm.setAddtime(MessageUtil.getCurrentDate());
			hm.setIsLook(MessageUtil.Status_Unlook_Message_Vfunding);
			hm.setReceiveUser(userId);
			hm.setTitle(title);
			hm.setContent(content);
			return hm;
		}
		
		/**
		 * 创建财经道用户注册的系统消息
		 * @param userId
		 * @return
		 */
		public SystemMessage createSystemMessageForCjdRegister(Integer userId){
			return createBaseSystemMessage(userId,MessageUtil.Title_SystemMessage_Cjd,MessageUtil.Content_SystemMessage_Cjd);
		}
		
		/**
		 * 创建财经道用户注册并投资后的红包消息
		 * @param userId
		 * @return
		 */
		public GiftboxMessage createGiftboxMessageForCjdRegister(Integer userId){
			GiftboxMessage gm = createBaseGiftboxMessage(userId,MessageUtil.Title_GiftboxMessage_Cjd,MessageUtil.Content_GiftboxMessage_Cjd);
			gm.setTakeTime(MessageUtil.getCurrentDate());
			gm.setLoseTime(MessageUtil.getCjdActivityLoseDate());
			gm.setMoney(MessageUtil.Money_Register_Cjd);
			return gm;
		}
		
		/**
		 * 创建破两亿活动加息卡消息
		 * @param userId
		 * @return
		 */
		public HikesMessage createHikesMessageForActivityTwoMillons(Integer userId){
			HikesMessage hm = createBaseHikesMessage(userId, MessageUtil.Title_HikesMessage_Activity_TwoMillons, MessageUtil.Content_HikesMssage_Activity_TwoMillons);
			BigDecimal rate = MessageUtil.Rate_Activity_TwoMillons;
			rate.setScale(2, BigDecimal.ROUND_HALF_UP);
			hm.setRate(rate);
			return hm;			
		}
		
	}
	

}
