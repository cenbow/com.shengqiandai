package cn.vfunding.vfunding.biz.regAction.service;

import cn.vfunding.vfunding.biz.common.vo.RegisterVO;

public interface RegActionService {
	public void doUserAction(RegisterVO vo);

	public void doRegisterInfo(Integer userId, String registerIp);
}
