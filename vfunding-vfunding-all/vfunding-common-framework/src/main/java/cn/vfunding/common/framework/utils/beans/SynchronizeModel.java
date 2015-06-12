package cn.vfunding.common.framework.utils.beans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * notice:
 * 	1)declare final variable to use in method scope<br>
 *  2)oldList, newList各自中的元素按照Identify定义可能有重复
 * @author zachary.zhu
 *
 *
 * @param <Told>
 * @param <Tnew>
 */
public abstract class SynchronizeModel<Told,Tnew> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Told> oldlList;

    private List<Tnew> newList;


    private int insertCount = 0;
    private int deleteCount = 0;

    /**
     *
     * @param oldlList DB中原来存在对象集合
     * @param newList	准备向DB保存的对象集合
     */
    public SynchronizeModel(List<Told> oldlList, List<Tnew> newList){
        this.oldlList = oldlList;
        this.newList = newList;
    }

    public abstract String genIdentifyOld(Told obj);

    public abstract String genIdentifyNew(Tnew obj);
    /**
     * 同步，insert&delete
     */
    public void synchronize(){

        HashMap<String,Told> includeMap = new HashMap<String,Told>();
        Set<String> existsSet = new HashSet<String>(); //已存在集合，避免重复插入
        if(null!=oldlList){
            for(Told obj : oldlList){
                includeMap.put(genIdentifyOld(obj), obj);  //避免oldList重复
            }
        }

        if(null!=newList){
            for(Tnew obj : newList){
                String identify = genIdentifyNew(obj);
                if(existsSet.contains(identify)){  //skip repeat record
                    continue;
                }
                if (includeMap.containsKey(identify) ){
                    this.forExists(obj, includeMap.get(identify));
                    includeMap.remove(identify);
                }else{
                    this.insertNew(obj);
                    insertCount++;
                }
                existsSet.add(identify); //避免newList重复
            }
        }
        //删除
        for(Entry<String,Told> entry : includeMap.entrySet()){
            this.deleteOld(entry.getValue());
            deleteCount++ ;
        }

        logger.debug("insertNew：" + this.getInsertCount() );
        logger.debug("deleteOld：" + this.getDeleteCount() );
    }

    public abstract void  insertNew(Tnew obj) ;

    public abstract void deleteOld(Told obj) ;

    public abstract void forExists(Tnew objNew, Told objOld) ;

    public int getInsertCount() {
        return insertCount;
    }

    public int getDeleteCount() {
        return deleteCount;
    }

}
