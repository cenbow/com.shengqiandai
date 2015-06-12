package cn.vfunding.vfunding.biz.borrowMobile.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrowMobile.model.BorrowTenderMobile;

public interface IBorrowTenderMobileService {

    /**
     * 根据borrowId查询投资次数
     */
    int selectTenderBorrowByIdCount(Integer borrowId);
    
    List<BorrowTenderMobile> selectTenderBorrowByIdListPage(PageSearch pageSearch);
    
}
