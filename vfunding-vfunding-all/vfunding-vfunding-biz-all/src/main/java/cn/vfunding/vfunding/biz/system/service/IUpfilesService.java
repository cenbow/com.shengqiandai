package cn.vfunding.vfunding.biz.system.service;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import cn.vfunding.vfunding.biz.system.model.Upfiles;

public interface IUpfilesService {
	/**
	 * 根据主键删除对象
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入数据
	 * @param record
	 * @param multipartEntity文件对象
	 * @return
	 */
	int insert(Upfiles record,MultipartEntityBuilder multipartEntity);

	/**
	 * 条件插入数据（为空字段不插入）
	 * 
	 * @param record
	 * @return 自增长ID
	 */
	int insertSelective(Upfiles record);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	Upfiles selectByPrimaryKey(Integer id);

	/**
	 * 根據主鍵更新（字段為空不修改）
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Upfiles record);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Upfiles record);
}
