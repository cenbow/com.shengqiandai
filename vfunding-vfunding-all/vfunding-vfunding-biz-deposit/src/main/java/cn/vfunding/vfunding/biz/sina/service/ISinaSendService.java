package cn.vfunding.vfunding.biz.sina.service;

import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;

public interface ISinaSendService {
	/**
	 * 会员接口发送
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public <T extends BaseSinaReturnVO> T sinaSendMgs(Object obj,Class<T> clazz) throws Exception;
	
	/**
	 * 收单类接口
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public  <T extends BaseSinaReturnVO> T sinaSendMas(Object obj,Class<T> clazz) throws Exception;
}
