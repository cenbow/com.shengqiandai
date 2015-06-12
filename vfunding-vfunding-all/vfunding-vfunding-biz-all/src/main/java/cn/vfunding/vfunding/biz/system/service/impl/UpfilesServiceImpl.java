package cn.vfunding.vfunding.biz.system.service.impl;

import javax.annotation.Resource;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.system.dao.UpfilesMapper;
import cn.vfunding.vfunding.biz.system.model.Upfiles;
import cn.vfunding.vfunding.biz.system.service.IUpfilesService;

@Service("upfilesService")
public class UpfilesServiceImpl implements IUpfilesService {

	@Autowired
	private UpfilesMapper upMapper;
	/**
	 * 图片服务器
	 */
	@Resource(name="fileRestInvokerFactory")
	private RestInvokerFactory fileRestInvokerFactory;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.upMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Upfiles record,MultipartEntityBuilder multipartEntity) {
		if(EmptyUtil.isNotEmpty(multipartEntity)){
			String picId =this.fileRestInvokerFactory.getRestInvoker().postFiles(
					  "upload", multipartEntity);
			record.setFileurl(picId);
		}		
		this.upMapper.insert(record);
		return record.getId();
		
		
		
		
	}

	@Override
	public int insertSelective(Upfiles record) {
		this.upMapper.insertSelective(record);
		return record.getId();
	}

	@Override
	public Upfiles selectByPrimaryKey(Integer id) {
		return this.upMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Upfiles record) {
		return this.upMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Upfiles record) {
		return this.upMapper.updateByPrimaryKey(record);
	}

}
