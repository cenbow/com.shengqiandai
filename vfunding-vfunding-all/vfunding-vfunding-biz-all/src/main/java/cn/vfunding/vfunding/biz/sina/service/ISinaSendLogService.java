package cn.vfunding.vfunding.biz.sina.service;

import cn.vfunding.vfunding.biz.sina.model.SinaSendLog;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;

public interface ISinaSendLogService {
    int deleteByPrimaryKey(String orderNo);

    int insert(SinaSendLogWithBLOBs record);

    int insertSelective(SinaSendLogWithBLOBs record);

    SinaSendLogWithBLOBs selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(SinaSendLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SinaSendLogWithBLOBs record);

    int updateByPrimaryKey(SinaSendLog record);
    
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
    int insertSinaLog(String no,Integer status,Integer retry,String interfaceName,Object args,Object responseObj);
        
    /**
     * 更新错误日志，并且retry+1
     * @param orderNo
     * @author louchen 2015-01-20
     */
    public void updateInCorrectSendLog(String orderNo);
    
    /**
     * 更新错误日志status=1(失败发送的日志=0，=1是重新发送后成功)
     * @param orderNo
     * @author louchen 2015-01-20
     */
    public void updateInCorrectToCorrectSendLog(String orderNo);
    
    /**
     * 记录成功请求信息
     * @param no
     * @param interfaceName
     * @param args
     * @param responseObj
     * @return
     * @author louchen 2015-01-20
     */
    public int insertSuccessSinaLog(String no,String interfaceName,Object args,Object responseObj);
    
    /**
     * 记录失败请求信息
     * @param no
     * @param interfaceName
     * @param args
     * @param responseObj
     * @return
     * @author louchen 2015-01-20
     */
    public int insertFailedSinaLog(String no,String interfaceName,Object args,Object responseObj);
    
    /**
     * 根据流水号查找成功日志
     * @param orderNo
     * @return
     */
    public SinaSendLogWithBLOBs selectSuccessLogByOrderNo(String orderNo);
    
    /**
     * 根据流水号查找失败日志
     * @param orderNo
     * @return
     */
    public SinaSendLogWithBLOBs selectFailedLogByOrderNo(String orderNo);
    
}