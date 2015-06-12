package cn.p2p.p2p.prd.mobile.method.all;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.account.model.InterestDays;
import cn.p2p.p2p.biz.account.service.IInterestDaysService;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.PageUtilVO;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

@Service
public class InterestDaysMethod {
	
	@Autowired
	private IInterestDaysService interestDaysService;
	
	@Autowired
	private IUserTokenService userTokenService;

	/**
	 * 查询生息明细
	 * @param vo
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse selectByUserIdListPage(PageUtilVO vo){
		PageSearch pageSearch = new PageSearch();
		InterestDays interestDays = new InterestDays();
		interestDays.setUserId(this.userTokenService.selectUserIdByToken(vo.getToken()));
		pageSearch.setPage(vo.getPage());
		pageSearch.setRows(vo.getRows());
		pageSearch.setEntity(interestDays);
		List<InterestDays> listInterest = this.interestDaysService.selectByUserIdListPage(pageSearch);
		return new MobileBaseResponse(listInterest);
	}
	
	/**
	 * 根据时间查询明细
	 * @param vo
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse selectByUserDateStr(GeneralRequestVO vo){
		InterestDays interestDays = new InterestDays();
		interestDays.setUserId(this.userTokenService.selectUserIdByToken(vo.getToken()));
		interestDays.setInterestDate(DateUtil.getDateToString(vo.getDateTime(), "yyyy-MM-dd"));
		List<InterestDays> listInterest = this.interestDaysService.selectByUserDateStr(interestDays);
		return new MobileBaseResponse(listInterest);
	}
	
	public static void main(String[] args) {
		String time = "2015-05-29 15:25:00";
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date date = df1.parse(time);
			String date1 =df2.format(date);
			System.out.println(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
