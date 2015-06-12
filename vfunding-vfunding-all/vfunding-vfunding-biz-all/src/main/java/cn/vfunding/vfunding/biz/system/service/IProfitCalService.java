package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.common.vo.ProfitCalJsonVO;
import cn.vfunding.vfunding.biz.common.vo.ProfitCalVO;

public interface IProfitCalService {

	List<ProfitCalJsonVO> selectProfitCal(ProfitCalVO vo);
}
