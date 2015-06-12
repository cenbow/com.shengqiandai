package cn.vfunding.vfunding.biz.sina.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.sina.dao.SinaCardMapper;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
@Service("sinaCardService")
public class SinaCardServiceImpl implements ISinaCardService {
	@Autowired
	private SinaCardMapper sinaCardMapper;
	
	@Override
	public int deleteByPrimaryKey(String sinaCard) {
		return sinaCardMapper.deleteByPrimaryKey(sinaCard);
	}

	@Override
	public int insertSelective(SinaCard record) {
		return sinaCardMapper.insertSelective(record);
	}

	@Override
	public SinaCard selectByPrimaryKey(Integer scId) {
		return sinaCardMapper.selectByPrimaryKey(scId);
	}

	@Override
	public int updateByPrimaryKeySelective(SinaCard record) {
		return sinaCardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SinaCard> selectSelective(SinaCard sc) {
		return sinaCardMapper.selectSelective(sc);
	}

	@Override
	public List<SinaCard> selectByUserId(String userId) {
		return this.sinaCardMapper.selectByUserId(userId);
	}

	@Override
	public List<SinaCard> selectQuickPayByUserId(String userId) {
		return this.sinaCardMapper.selectQuickPayByUserId(userId);
	}

	@Override
	public SinaCard updateQuickPayCardWeight(SinaCard sc) throws Exception {
		if(EmptyUtil.isNotEmpty(sc) && sc.getScId()>0 && sc.getUserId()>0 ){
			 this.sinaCardMapper.updateQuickPayCardWeight(sc);		
			 sc = this.selectByPrimaryKey(sc.getScId());
			 sc.setWeight(1);
			 this.updateByPrimaryKeySelective(sc);
		}else{
			throw new Exception("参数异常");
		}
		return sc;
	}


}
