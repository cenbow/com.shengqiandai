package cn.vfunding.vfunding.biz.report.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.dao.UserActivityZanMapper;
import cn.vfunding.vfunding.biz.report.model.UserActivityZan;
import cn.vfunding.vfunding.biz.report.service.IUserActivityZanSerivce;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

@Service("userAcitivityZanSerivce")
public class UserActivityZanSerivceImpl implements IUserActivityZanSerivce {

	@Autowired
	private UserActivityZanMapper mapper;
	@Autowired
	private UserMapper userMapper;
	@Resource(name = "fileRestInvokerFactory")
	private RestInvokerFactory fileRestInvokerFactory;

	@Override
	public int savaUserActivityZan(UserActivityZan record,
			MultipartEntityBuilder multipartEntity) {
		String fileId = this.fileRestInvokerFactory.getRestInvoker().postFiles(
				"/upload", multipartEntity);
		UserActivityZan lastInfo = this.mapper.selectByUserNameAndLast(record
				.getUserName());
		if (EmptyUtil.isNotEmpty(lastInfo)) {// 已存在记录
			int num = lastInfo.getActivityBonuses() + record.getAddBonuses();
			if(num>50){
				num=50;
				record.setAddBonuses(50-lastInfo.getActivityBonuses());
			}			
			record.setActivityBonuses(num);
			record.setUserPhone(lastInfo.getUserPhone());
		} else {
			UserWithBLOBs user = this.userMapper.selectByUserName(record
					.getUserName());
			record.setActivityBonuses(record.getAddBonuses());
			record.setUserPhone(StringUtil.doPhoneNumber(user.getPhone()));
		}
		record.setStatus("N");
		record.setAddTime(new Date());
		record.setFileUrls(fileId);
		return this.mapper.insert(record);
	}

	@Override
	public List<UserActivityZan> selectByUserName(PageSearch pageSearch) {
		return this.mapper.selectByUserNameListPage(pageSearch);
	}

	@Override
	public void doAffirm(Integer id) {
		UserActivityZan record = this.mapper.selectByPrimaryKey(id);
		if (EmptyUtil.isNotEmpty(record)) {
			record.setStatus("Y");
			UserWithBLOBs user = this.userMapper.selectByUserName(record
					.getUserName());
			if (EmptyUtil.isNotEmpty(user)) {
				BigDecimal addValue = new BigDecimal(record.getAddBonuses()
						.toString());
				if (EmptyUtil.isNotEmpty(user.getHongbao())) {
					user.setHongbao(user.getHongbao().add(addValue));
				} else {
					user.setHongbao(new BigDecimal(record.getAddBonuses()
							.toString()));
				}
				this.userMapper.updateByPrimaryKeySelective(user);
			}
			this.mapper.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public UserActivityZan selectByUserNameAndLast(String userName) {
		return this.mapper.selectByUserNameAndLast(userName);
	}

}
