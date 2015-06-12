package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.dao.MortgageCarPicMapper;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.system.dao.AttestationMapper;
import cn.vfunding.vfunding.biz.system.model.Attestation;
import cn.vfunding.vfunding.biz.system.service.IAttestationService;
import cn.vfunding.vfunding.biz.week.dao.WeekBorrowPawnPicMapper;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawnPic;

@Service("attestationService")
public class AttestationServiceImpl implements IAttestationService {

	/**
	 * 文件服务器
	 */
	@Resource(name = "fileRestInvokerFactory")
	private RestInvokerFactory fileRestInvokerFactory;
	@Autowired
	private AttestationMapper mapper;
	@Autowired
	private WeekBorrowPawnPicMapper weekBorrowPawnPicMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Attestation record) {
		if (EmptyUtil.isEmpty(record.getStatus())) {
			record.setStatus((byte) 0);
		}
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(Attestation record) {
		if (EmptyUtil.isEmpty(record.getStatus())) {
			record.setStatus((byte) 0);
		}
		return this.mapper.insertSelective(record);
	}

	@Override
	public Attestation selectByPrimaryKey(Integer id) {
		return this.selectByPrimaryKey(id);
	}

	@Override
	public List<Attestation> selectByUserId(PageSearch pageSearch) {
		return this.mapper.selectByUserIdListPage(pageSearch);
	}

	@Override
	public int addAttestation(Attestation record, MultipartEntityBuilder multipartEntity) {
		if (EmptyUtil.isNotEmpty(multipartEntity)) {
			String fileId = this.fileRestInvokerFactory.getRestInvoker().postFiles("upload?from=vfunding_www", multipartEntity);
			record.setLitpic(fileId);
			this.mapper.insert(record);
		}
		return 0;
	}

	@Override
	public String uploadAttestation(MultipartEntityBuilder multipartEntity) {
		if (EmptyUtil.isNotEmpty(multipartEntity)) {
			String fileId = this.fileRestInvokerFactory.getRestInvoker().postFiles("upload?from=vfunding_www", multipartEntity);
			return fileId;
		}
		return null;
	}

	@Autowired
	private MortgageCarPicMapper mortgageCarPicMapper;

	/**
	 * @Description:发标上传图片
	 * @param multipartEntity
	 * @return
	 * @author liuhuan
	 * @throws Exception
	 */
	@Override
	public MessageVO borrowUploadAttestation(MultipartEntityBuilder multipartEntity) throws Exception {
		MessageVO vo = new MessageVO();
		if (EmptyUtil.isNotEmpty(multipartEntity)) {
			String param = "upload?from=vfunding_www";
			String fileId = this.fileRestInvokerFactory.getRestInvoker().postFiles(param, multipartEntity);
			MortgageCarPic carPic = new MortgageCarPic();
			carPic.setAddtime(new Date());
			carPic.setPic(fileId);// 路径
			carPic.setType("0");
			mortgageCarPicMapper.insertSelective(carPic);
			vo.setId(carPic.getId());
			vo.setMsg(fileId);
			return vo;
		}
		return vo;
	}
	
	@Override
	public MessageVO borrowUploadAttestation(
			MultipartEntityBuilder multipartEntity, Integer id ,Integer carId ,String type)
			throws Exception {
		MessageVO vo = new MessageVO();
		if (EmptyUtil.isNotEmpty(multipartEntity)) {
			String param = "upload?from=vfunding_www";
			String fileId = this.fileRestInvokerFactory.getRestInvoker().postFiles(param, multipartEntity);
			MortgageCarPic carPic = new MortgageCarPic();
			if(id == null){
				carPic.setAddtime(new Date());
				carPic.setPic(fileId);// 路径
				if(StringUtils.isBlank(type))
					carPic.setType("0");
				else
					carPic.setType(type);
				if(carId != null)
					carPic.setCarId(carId);
				mortgageCarPicMapper.insertSelective(carPic);
			}else{
				carPic = mortgageCarPicMapper.selectByPrimaryKey(id);
				carPic.setPic(fileId);
				if(StringUtils.isNotBlank(type))
					carPic.setType(type);
				if(carId != null)
					carPic.setCarId(carId);
				mortgageCarPicMapper.updateByPrimaryKeySelective(carPic);
			}
			vo.setId(carPic.getId());
			vo.setMsg(fileId);
			return vo;
		}
		return vo;
	}

	@Override
	public MessageVO WeekborrowUploadAttestation(
			MultipartEntityBuilder multipartEntity) throws Exception {
		MessageVO vo = new MessageVO();
		if (EmptyUtil.isNotEmpty(multipartEntity)) {
			String param = "upload?from=vfunding_bms";
			String fileId = this.fileRestInvokerFactory.getRestInvoker().postFiles(param, multipartEntity);
			WeekBorrowPawnPic pic = new WeekBorrowPawnPic();
			pic.setAddTime(new Date());
			pic.setPic(fileId);
			this.weekBorrowPawnPicMapper.insertSelective(pic);
			vo.setId(pic.getId());
			vo.setMsg(fileId);
			return vo;
		}
		return vo;
	}

	

}
