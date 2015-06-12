package cn.vfunding.vfunding.biz.icbcCard.service.impl;

import javax.annotation.Resource;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.icbcCard.dao.IcbcCardMapper;
import cn.vfunding.vfunding.biz.icbcCard.model.IcbcCard;
import cn.vfunding.vfunding.biz.icbcCard.service.IIcbcCardService;



@Service("icbcCardService")
public class IcbcCardServiceImpl implements IIcbcCardService {

	/**
	 * 文件服务器
	 */
	@Resource(name = "fileRestInvokerFactory")
	private RestInvokerFactory fileRestInvokerFactory;
	@Autowired
	private IcbcCardMapper icbcCardMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return icbcCardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(IcbcCard record) {
		return icbcCardMapper.insertSelective(record);
	}

	@Override
	public IcbcCard selectByPrimaryKey(Integer id) {
		return icbcCardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(IcbcCard record) {
		return icbcCardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public IcbcCard selectByUserId(Integer userId) {
		return icbcCardMapper.selectByUserId(userId);
	}

	@Override
	public MessageVO idCardUpload(MultipartEntityBuilder multipartEntity,Integer userId,String idCardType) {
		MessageVO vo = new MessageVO();
		if (EmptyUtil.isNotEmpty(multipartEntity)) {
			String param = "upload?from=vfunding_www";
			String fileId = this.fileRestInvokerFactory.getRestInvoker().postFiles(param, multipartEntity);
			
			IcbcCard ic = this.selectByUserId(userId);
			if(ic == null){
				ic = new IcbcCard();
				ic.setUserId(userId);
				if(idCardType.equals("face")){
					ic.setIdcardPicFace("http://file.8jielicai.com/ori/"+fileId);
				}else if(idCardType.equals("back")){
					ic.setIdcardPicBack("http://file.8jielicai.com/ori/"+fileId);
				}
				this.insertSelective(ic);
			}else{
				ic.setUserId(userId);
				if(idCardType.equals("face")){
					ic.setIdcardPicFace("http://file.8jielicai.com/ori/"+fileId);
				}else if(idCardType.equals("back")){
					ic.setIdcardPicBack("http://file.8jielicai.com/ori/"+fileId);
				}
				this.updateByPrimaryKeySelective(ic);
			}
			vo.setId(ic.getId());
			vo.setMsg(fileId);
			return vo;
		}
		return vo;
	}

	@Override
	public void addNoFeeCountOne(Integer userId) {
		// TODO Auto-generated method stub
		this.icbcCardMapper.addNoFeeCountOne(userId);
	}
	
}
