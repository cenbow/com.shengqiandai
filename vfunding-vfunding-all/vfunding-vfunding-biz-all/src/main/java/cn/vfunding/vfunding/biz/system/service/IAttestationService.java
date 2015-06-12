package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.system.model.Attestation;

public interface IAttestationService {

	int deleteByPrimaryKey(Integer id);

	int insert(Attestation record);

	int insertSelective(Attestation record);

	Attestation selectByPrimaryKey(Integer id);

	/**
	 * 根据用户ID分页查询
	 * 
	 * @param userId
	 * @return 2014年4月24日 liuyijun
	 */
	List<Attestation> selectByUserId(PageSearch pageSearch);

	/**
	 * 上传证明资料
	 * 
	 * @param record
	 * @param multipartEntity
	 * @return 2014年4月28日 liuyijun
	 */
	int addAttestation(Attestation record,
			MultipartEntityBuilder multipartEntity);

	/**
	 * 上传资料
	 * 
	 * @param multipartEntity
	 * @return 2014年5月7日 liuyijun
	 */
	String uploadAttestation(MultipartEntityBuilder multipartEntity);

	/**
	 * 发标上传图片
	 * 
	 * @param multipartEntity
	 * @return 2014年5月7日 liuyijun
	 */
	MessageVO borrowUploadAttestation(MultipartEntityBuilder multipartEntity)
			throws Exception;

	/**
	 * 发标上传图片，如果ID存在 则覆盖 wang.zeyan 2015年3月26日
	 * 
	 * @param multipartEntity
	 * @param id
	 * @return
	 * @throws Exception
	 */
	MessageVO borrowUploadAttestation(MultipartEntityBuilder multipartEntity,
			Integer id, Integer carId, String type) throws Exception;

	/**
	 * 短期理财计划 标的上传图片
	 * 
	 * @param multipartEntity
	 * @return
	 * @throws Exception
	 * @author louchen 2014-12-5
	 */
	MessageVO WeekborrowUploadAttestation(MultipartEntityBuilder multipartEntity)
			throws Exception;
}
