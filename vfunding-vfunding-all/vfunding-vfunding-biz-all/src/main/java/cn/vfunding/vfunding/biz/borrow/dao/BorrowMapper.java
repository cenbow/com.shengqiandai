package cn.vfunding.vfunding.biz.borrow.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.common.vo.ReleaseBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.TheTrialBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyBorrowVO;

public interface BorrowMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Borrow record);

	int insertSelective(Borrow record);

	Borrow selectById(Integer id);

	int updateByPrimaryKeySelective(Borrow record);

	int updateByPrimaryKeyWithBLOBs(Borrow record);

	int updateByPrimaryKey(Borrow record);

	/**
	 * 根据用户ID分页查询
	 * 
	 * @param pageSearch
	 * @return 2014年4月29日 liuyijun
	 */
	List<Borrow> selectByUserIdListPage(PageSearch pageSearch);

	/**
	 * 根据borrowID查询borrow的详情
	 * 
	 * @param borrowId
	 * @return author LiLei 2014年5月12日
	 */
	Borrow selectBorrowById(Integer borrowId);

	/**
	 * 主页显示borrowList
	 * 
	 * @param pageSearch
	 * @return
	 */
	List<Borrow> selectBorrowListPage(PageSearch pageSearch);

	/**
	 * 体验标查询
	 * 
	 * @param rows
	 * @return author LiLei 2014年5月8日
	 */
	List<Borrow> selectFeelBorrow(Integer rows);

	List<Borrow> selectFeelBorrowForIndex();

	/**
	 * 借款总额
	 */
	BigDecimal selectSumBorrowAccount(Integer userId);

	/**
	 * 根据userID查询借款信息
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年5月19日
	 */
	List<Borrow> selectBorrowByUserIdListPage(PageSearch pageSearch);

	ReleaseBorrowVO selectBorrowForRelease(int borrowId);

	List<Borrow> selectAllBorrowListPage(PageSearch pageSearch);

	List<Borrow> selectByStatus(Integer status);

	/**
	 * 后台复审
	 * 
	 * @author liuhuan
	 */
	List<VerifyBorrowVO> selectFinalBorrowListPage(PageSearch pageSearch);
	
	/**
	 * 后台借款管理
	 * @author liuhuan
	 */
	List<VerifyBorrowVO> selectBorrowSystemListPage(PageSearch pageSearch);

	/**
	 * 后台初审
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年6月5日
	 */
	List<TheTrialBorrowVO> selectTheTrialListPage(PageSearch pageSearch);
	
	
	/**
	 * 查询需要转换Content的borrow
	 * @return
	 * 2014年6月20日
	 * liuyijun
	 */
	List<Borrow> selectByConvertContent();

	/**
	 * 后台撤标
	 * @author liuhuan
	 */
	List<VerifyBorrowVO> failBorrowListPage(PageSearch pageSearch);
	
	
	
/***********************************************************www.8jie.com********************************************************/
	
	
	/**
	 * 天标查询
	 * @param pageSearch
	 * @return
	 */
	List<VerifyBorrowVO> dayBorrowListPage(PageSearch pageSearch);

}