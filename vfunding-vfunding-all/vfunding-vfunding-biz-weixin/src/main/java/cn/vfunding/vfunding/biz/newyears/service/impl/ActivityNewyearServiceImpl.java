package cn.vfunding.vfunding.biz.newyears.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.activity.dao.WeixinOpenIdMapper;
import cn.vfunding.vfunding.biz.activity.model.WeixinOpenId;
import cn.vfunding.vfunding.biz.newyears.dao.ActivityNewyearLogMapper;
import cn.vfunding.vfunding.biz.newyears.dao.ActivityNewyearMapper;
import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyear;
import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyearLog;
import cn.vfunding.vfunding.biz.newyears.service.IActivityNewyearService;
import cn.vfunding.vfunding.biz.thirdlogin.dao.ThirdLoginMapper;
import cn.vfunding.vfunding.biz.thirdlogin.model.ThirdLogin;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

@Service("activityNewyearService")
public class ActivityNewyearServiceImpl implements IActivityNewyearService {

	@Autowired
	private ActivityNewyearMapper activityNewyearMapper;

	@Autowired
	private WeixinOpenIdMapper openIdMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ThirdLoginMapper thirdLoginMapper;

	@Autowired
	private ActivityNewyearLogMapper activityNewyearLogMapper;
	
	@Override
	public int deleteByPrimaryKey(String phone) {
		return this.activityNewyearMapper.deleteByPrimaryKey(phone);
	}

	@Override
	public int insertSelective(ActivityNewyear record) {
		return this.activityNewyearMapper.insertSelective(record);
	}

	@Override
	public ActivityNewyear selectByPrimaryKey(String phone) {
		return this.activityNewyearMapper.selectByPrimaryKey(phone);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityNewyear record) {
		return this.activityNewyearMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Json insertNewyearActivity(String phone, String openId) {
		Json json = new Json();
		Integer id = 0;
		// 获取openId所绑定的用户
		WeixinOpenId isExistOpenId = this.openIdMapper.selectByOpenid(openId);
		if (EmptyUtil.isEmpty(isExistOpenId)) {
			WeixinOpenId openIdBean = new WeixinOpenId();
			openIdBean.setOpenid(openId);
			openIdBean.setPhone(phone);
			openIdBean.setAddtime(new Date());
			this.openIdMapper.insertSelective(openIdBean);
			id = openIdBean.getId();
			ThirdLogin search = new ThirdLogin();
			search.setCategory("weixin");
			search.setLoginAccount(openId);
			ThirdLogin tl = this.thirdLoginMapper.selectByLoginAccountAndCategory(search);
			if(tl!=null){
				tl.setLastLogin(new Date());
				this.thirdLoginMapper.updateByPrimaryKeySelective(tl);
			}			
		} else {
			json.setSuccess(false);
			json.setMsg("重复领取");
			return json;
		}
		json.setObj(id);
		// 查询用户是否为微积金用户
		List<UserWithBLOBs> userList = this.userMapper.selectByLogin(phone);
		if (userList.size() == 0) {
			// 新用户获取红包 加息卡
			this.insertOrUpdateActivityNewyear(phone, "0.0", "0");
		} else {
			// 老用户 无可领取奖励
			this.insertOrUpdateActivityNewyear(phone, "0.0", "0");
		}
		json.setSuccess(true);
		return json;
	}

	private void insertOrUpdateActivityNewyear(String phone, String hikes,
			String hongbao) {
		ActivityNewyear activityNewyear = this.activityNewyearMapper
				.selectByPrimaryKey(phone);
		if (activityNewyear == null) {
			activityNewyear = new ActivityNewyear();
			activityNewyear.setPhone(phone);
			activityNewyear.setHikes(new BigDecimal(hikes));
			activityNewyear.setHongbao(new BigDecimal(hongbao));
			activityNewyear.setAddtime(new Date());
			this.activityNewyearMapper.insertSelective(activityNewyear);
		} else {
			activityNewyear.setHikes(activityNewyear.getHikes().add(
					new BigDecimal(hikes)));
			activityNewyear.setHongbao(activityNewyear.getHongbao().add(
					new BigDecimal(hongbao)));
			this.activityNewyearMapper
					.updateByPrimaryKeySelective(activityNewyear);
		}

	}

	private void insertActivityNewyearLog(String phone, String from_phone,
			String hikes, String hongbao, Integer status) {
		ActivityNewyearLog activityNewyearLog = new ActivityNewyearLog();
		activityNewyearLog.setPhone(phone);
		activityNewyearLog.setFromPhone(from_phone);
		activityNewyearLog.setHikes(new BigDecimal(hikes));
		activityNewyearLog.setHongbao(new BigDecimal(hongbao));
		activityNewyearLog.setStatus(status);
		activityNewyearLog.setAddtime(new Date());
		this.activityNewyearLogMapper.insertSelective(activityNewyearLog);
	}

	@Override
	public ActivityNewyear selectByPhone(String phone) {
		return this.activityNewyearMapper.selectByPhone(phone);
	}

	
}