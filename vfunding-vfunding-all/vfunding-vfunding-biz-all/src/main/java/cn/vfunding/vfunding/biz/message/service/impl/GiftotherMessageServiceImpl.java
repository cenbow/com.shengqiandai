package cn.vfunding.vfunding.biz.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.dao.GiftotherMessageMapper;
import cn.vfunding.vfunding.biz.message.model.GiftotherMessage;
import cn.vfunding.vfunding.biz.message.service.IGiftotherMessageService;

@Service("giftotherMessageService")
public class GiftotherMessageServiceImpl implements IGiftotherMessageService {

	@Autowired
	private GiftotherMessageMapper giftotherMessageMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return giftotherMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GiftotherMessage record) {
		return giftotherMessageMapper.insert(record);
	}

	@Override
	public int insertSelective(GiftotherMessage record) {
		return giftotherMessageMapper.insertSelective(record);
	}

	@Override
	public GiftotherMessage selectByPrimaryKey(Integer id) {
		return giftotherMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GiftotherMessage record) {
		return giftotherMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GiftotherMessage record) {
		return giftotherMessageMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer selectUnLookCountByUserId(Integer userId) {
		List<GiftotherMessage> list = this.giftotherMessageMapper.selectUnLookByUserId(userId);
		return list.size();
	}

	@Override
	public List<GiftotherMessage> selectGiftboxMessageListPage(
			PageSearch pageSearch) {
		return this.giftotherMessageMapper.selectGiftboxMessageListPage(pageSearch);
	}

	@Override
	public int updateGiftIsLook(GiftotherMessage record) {
		if(EmptyUtil.isNotEmpty(record)){
			//已查看
			record.setIsLook(1);
			return this.updateByPrimaryKeySelective(record);
		}else{
			return 0;
		}
	}

}
