package cn.vfunding.vfunding.biz.sina.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.sina.dao.SinaSendLogMapper;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLog;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service("sinaSendLogService")
public class SinaSendLogServiceImpl implements ISinaSendLogService {

	@Autowired
	private SinaSendLogMapper sinaSendLogMapper;
	
	@Override
	public int deleteByPrimaryKey(String orderNo) {
		return this.sinaSendLogMapper.deleteByPrimaryKey(orderNo);
	}

	@Override
	public int insert(SinaSendLogWithBLOBs record) {
		return this.sinaSendLogMapper.insert(record);
	}

	@Override
	public int insertSelective(SinaSendLogWithBLOBs record) {
		return this.sinaSendLogMapper.insertSelective(record);
	}

	@Override
	public SinaSendLogWithBLOBs selectByPrimaryKey(String orderNo) {
		return this.sinaSendLogMapper.selectByPrimaryKey(orderNo);
	}

	@Override
	public int updateByPrimaryKeySelective(SinaSendLogWithBLOBs record) {
		return this.sinaSendLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(SinaSendLogWithBLOBs record) {
		return this.sinaSendLogMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(SinaSendLog record) {
		return this.sinaSendLogMapper.updateByPrimaryKey(record);
	}

    /**
     * 记录接口请求信息
     * @param no  关键标识编号,订单类接口 传订单,会员类传userId
     * @param interfaceName  接口名称
     * @param status 0 失败  1 修复成功 2直接成功
     * @param retry 重复次数
     * @param args 参数列表
     * @param responseObj  接口响应对象
     * @return
     */
	@SuppressWarnings("unchecked")
	@Override
	public int insertSinaLog(String no, Integer status, Integer retry,
			String interfaceName, Object args, Object responseObj) {
		SinaSendLogWithBLOBs ssl = new SinaSendLogWithBLOBs();
		ssl.setOrderNo(no);
		ssl.setStatus(status);
		ssl.setRetry(retry);
		ssl.setArgs(JSON.toJSONString(args));
		ssl.setInterfaceName(interfaceName);
		
		if(responseObj == null){
			ssl.setResponseCode("Null");
			ssl.setResponseMsg("未知错误");
			ssl.setResponseContent("Null");
		}else{
			Map<String, String> map = (Map<String, String>)JSONObject.toJSON(responseObj);
			ssl.setResponseCode(map.get("response_code"));
			ssl.setResponseMsg(map.get("response_message"));
			ssl.setResponseContent(JSON.toJSONString(responseObj));
		}
		ssl.setAddtime(new Date());
		return this.sinaSendLogMapper.insertSelective(ssl);
	}

    /**
     * 更新错误日志，并且retry+1
     * @param orderNo
     * @author louchen 2015-01-20
     */
	@Override
	public void updateInCorrectSendLog(String orderNo) {
		SinaSendLogWithBLOBs ssl = this.selectByPrimaryKey(orderNo);
		if(EmptyUtil.isNotEmpty(ssl)){
			ssl.setRetry(ssl.getRetry()+1);
			this.updateByPrimaryKeyWithBLOBs(ssl);
		}		
	}

	/**
     * 更新错误日志status=1(失败发送的日志=0，=1是重新发送后成功)
     * @param orderNo
     * @author louchen 2015-01-20
     */
	@Override
	public void updateInCorrectToCorrectSendLog(String orderNo) {
		SinaSendLogWithBLOBs ssl = this.selectByPrimaryKey(orderNo);
		if(EmptyUtil.isNotEmpty(ssl)){
			ssl.setStatus(1);
			this.updateByPrimaryKeyWithBLOBs(ssl);
		}	
	}

	 /**
     * 记录成功请求信息
     * @param no
     * @param interfaceName
     * @param args
     * @param responseObj
     * @return
     * @author louchen 2015-01-20
     */
	@SuppressWarnings("unchecked")
	@Override
	public int insertSuccessSinaLog(String no, String interfaceName,
			Object args, Object responseObj) {
		SinaSendLogWithBLOBs ssl = this.selectByPrimaryKey(no);
		if(EmptyUtil.isEmpty(ssl)){
			ssl = new SinaSendLogWithBLOBs();
		}
		ssl.setArgs(JSON.toJSONString(args));
		ssl.setInterfaceName(interfaceName);
		if(EmptyUtil.isNotEmpty(responseObj)){
			Map<String, String> map = (Map<String, String>)JSONObject.toJSON(responseObj);
			ssl.setResponseCode(map.get("response_code"));
			ssl.setResponseMsg(map.get("response_message"));
			ssl.setResponseContent(JSON.toJSONString(responseObj));
		}else{
			ssl.setResponseCode("Null");
			ssl.setResponseMsg("未知错误");
			ssl.setResponseContent("Null");
		}
		ssl.setAddtime(new Date());
		if(EmptyUtil.isEmpty(ssl.getOrderNo())){
			ssl.setOrderNo(no);
			ssl.setRetry(0);
			ssl.setStatus(2);
			return this.sinaSendLogMapper.insertSelective(ssl);
		}else{
			ssl.setRetry(ssl.getRetry()+1);
			ssl.setStatus(1);
			return this.sinaSendLogMapper.updateByPrimaryKeyWithBLOBs(ssl);
		}
	}

    /**
     * 记录失败请求信息
     * @param no
     * @param interfaceName
     * @param args
     * @param responseObj
     * @return
     * @author louchen 2015-01-20
     */
	@SuppressWarnings("unchecked")
	@Override
	public int insertFailedSinaLog(String no, String interfaceName,
			Object args, Object responseObj) {
		SinaSendLogWithBLOBs ssl = this.selectByPrimaryKey(no);
		if(EmptyUtil.isEmpty(ssl)){
			ssl = new SinaSendLogWithBLOBs();
		}
		ssl.setStatus(0);
		ssl.setArgs(JSON.toJSONString(args));
		ssl.setInterfaceName(interfaceName);
		if(EmptyUtil.isNotEmpty(responseObj)){
			Map<String, String> map = (Map<String, String>)JSONObject.toJSON(responseObj);
			ssl.setResponseCode(map.get("response_code"));
			ssl.setResponseMsg(map.get("response_message"));
			ssl.setResponseContent(JSON.toJSONString(responseObj));
		}else{
			ssl.setResponseCode("Null");
			ssl.setResponseMsg("未知错误");
			ssl.setResponseContent("Null");
		}
		ssl.setAddtime(new Date());
		if(EmptyUtil.isEmpty(ssl.getOrderNo())){
			ssl.setOrderNo(no);
			ssl.setRetry(0);
			return this.sinaSendLogMapper.insertSelective(ssl);
		}else{
			ssl.setRetry(ssl.getRetry()+1);
			return this.sinaSendLogMapper.updateByPrimaryKeyWithBLOBs(ssl);
		}
		
	}

	/**
	 * 根据流水号查找成功日志
	 * @param orderNo
	 * @return SinaSendLogWithBLOBs
	 * @author louchen 2015-01-26
	 */
	@Override
	public SinaSendLogWithBLOBs selectSuccessLogByOrderNo(String orderNo) {
		return this.sinaSendLogMapper.selectSuccessLogByOrderNo(orderNo);
	}

	/**
	 * 根据流水号查找失败日志
	 * @param orderNo
	 * @return SinaSendLogWithBLOBs
	 * @author louchen 2015-01-26
	 */
	@Override
	public SinaSendLogWithBLOBs selectFailedLogByOrderNo(String orderNo) {
		return this.sinaSendLogMapper.selectFailedLogByOrderNo(orderNo);
	}
}
