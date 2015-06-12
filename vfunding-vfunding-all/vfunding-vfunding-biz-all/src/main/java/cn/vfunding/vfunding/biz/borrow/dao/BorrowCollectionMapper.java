package cn.vfunding.vfunding.biz.borrow.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.common.vo.CollectionTenderUserVO;

public interface BorrowCollectionMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(BorrowCollection record);

	int insertSelective(BorrowCollection record);

	BorrowCollection selectByPrimaryKey(Integer id);

	List<BorrowCollection> selectByTenderId(Integer tenderId);

	BorrowCollection selectByTenderIdOrder(@Param("tenderId") Integer tenderId, @Param("order") Integer order);

	BigDecimal selectByTenderIdStatus(@Param("tenderId") Integer tenderId, @Param("status") Integer status);
	
	BigDecimal selectByTenderIdAndStatusOfInterest(@Param("tenderId") Integer tenderId, @Param("status") Integer status);

	int updateByPrimaryKeySelective(BorrowCollection record);

	int updateByPrimaryKey(BorrowCollection record);

	/**
	 * @Description:查询该用户该标的待收记录
	 * @param userId
	 * @param borrowId
	 * @return
	 * @author liuhuan
	 */
	List<BorrowCollection> selectByUserIdBorrowId(@Param("userId") Integer userId, @Param("borrowId") Integer borrowId);

	List<BorrowCollection> selectCollectionTimeByUserId(@Param("userId") Integer userId, @Param("status") Integer status);

	BigDecimal selectWaitFeelInterest(int userId);

	String selectFeelRepayTime(int userId);

	/**
	 * 待收记录
	 * 
	 * @author liuhuan
	 */
	List<CollectionTenderUserVO> selectCollectionRecordListPage(PageSearch pageSearch);

	/**
	 * 体验标待收
	 * 
	 * @author liuhuan
	 */
	List<CollectionTenderUserVO> selectFeelList(PageSearch pageSearch);
	
	/**
	 * 与还款相关的逾期待收
	 * @param repaymentId
	 * @return
	 * @author louchen
	 * @date 2015-05-11
	 */
	List<BorrowCollection> selectLateBorrowCollectionByRepaymentId(@Param("repaymentId") Integer repaymentId);
}