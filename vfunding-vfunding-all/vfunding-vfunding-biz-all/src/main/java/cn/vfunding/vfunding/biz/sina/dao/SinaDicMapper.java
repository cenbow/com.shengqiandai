package cn.vfunding.vfunding.biz.sina.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import cn.vfunding.vfunding.biz.sina.model.SinaDic;
public interface SinaDicMapper {
    int insert(SinaDic record);

    int insertSelective(SinaDic record);
    
    List<SinaDic> selectByDicType(String dicType);
    
    SinaDic selectByDicTypeAndDicCode(SinaDic sd);
}