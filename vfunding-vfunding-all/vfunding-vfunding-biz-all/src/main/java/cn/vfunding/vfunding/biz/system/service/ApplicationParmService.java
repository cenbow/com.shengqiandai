package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.vfunding.vfunding.biz.system.model.ApplicationParm;
import cn.vfunding.vfunding.biz.system.model.DataGridVO;

/**
 * 
 * @author wang.zeyan
 * @date 2015年3月27日
 * @version 1.0
 */
public interface ApplicationParmService {

	int deleteByPrimaryKey(String key);

    int insert(ApplicationParm record);

    int insertSelective(ApplicationParm record);

    ApplicationParm selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(ApplicationParm record);

    int updateByPrimaryKey(ApplicationParm record);
    
    List<ApplicationParm> queryAll();
    
    int saveDataGrid(DataGridVO<JSONObject> dgo);
}
