package cn.vfunding.vfunding.biz.report.service;

import java.util.List;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.UserActivityZan;

public interface IUserActivityZanSerivce {

	 /**
     * 保存对象
     * @param record
     * @return
     * @author liuyijun
     */
    int savaUserActivityZan(UserActivityZan record,MultipartEntityBuilder multipartEntity); 
    
    List<UserActivityZan> selectByUserName(PageSearch pageSearch);
    
    void doAffirm(Integer id);
    
    UserActivityZan selectByUserNameAndLast(String userName);
 
    
}
