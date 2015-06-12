package cn.vfunding.common.framework.utils.mvc;

import org.springframework.beans.factory.InitializingBean;

import cn.vfunding.common.framework.utils.rest.RestResourceUtil;

public class ApiBaseController extends BaseController implements InitializingBean{

	/**
	 * 处理API信息
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		RestResourceUtil.addRestDescriptorByClass(this.getClass());
	}
	
	

}
