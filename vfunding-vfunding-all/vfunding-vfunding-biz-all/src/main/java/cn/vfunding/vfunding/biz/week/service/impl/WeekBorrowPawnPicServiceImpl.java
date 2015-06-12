package cn.vfunding.vfunding.biz.week.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.week.dao.WeekBorrowPawnPicMapper;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawnPic;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowPawnPicService;


@Service("weekBorrowPawnPicService")
public class WeekBorrowPawnPicServiceImpl implements IWeekBorrowPawnPicService {

	@Autowired
	private WeekBorrowPawnPicMapper weekBorrowPawnPicMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.weekBorrowPawnPicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WeekBorrowPawnPic record) {
		return this.weekBorrowPawnPicMapper.insertSelective(record);
	}

	@Override
	public WeekBorrowPawnPic selectByPrimaryKey(Integer id) {
		return this.weekBorrowPawnPicMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeekBorrowPawnPic record) {
		return this.weekBorrowPawnPicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<WeekBorrowPawnPic> selectPicByPawnId(Integer pawnId) {
		return this.weekBorrowPawnPicMapper.selectPicByPawnId(pawnId);
	}
	
	

	@Override
	public int saveWeekBorrowPawnPic(HttpSession httpSession,
			WeekBorrowPawn weekBorrowPawn) {
		this.saveSinglePic(httpSession, weekBorrowPawn,"card_pic1","0");
		this.saveSinglePic(httpSession, weekBorrowPawn,"card_pic2","1");
		this.savePic(httpSession, weekBorrowPawn,"car_pic","carpic_old",1,"2");	
		this.savePic(httpSession, weekBorrowPawn,"car_pic","carpic_old",2,"2");
		this.savePic(httpSession, weekBorrowPawn,"car_pic","carpic_old",3,"2");
		this.savePic(httpSession, weekBorrowPawn,"car_pic","carpic_old",4,"2");
		this.savePic(httpSession, weekBorrowPawn,"carcard_pic","carcardpic_old",1,"3");
		this.savePic(httpSession, weekBorrowPawn,"carcard_pic","carcardpic_old",2,"3");
		this.savePic(httpSession, weekBorrowPawn,"carcard_pic","carcardpic_old",3,"3");
		this.savePic(httpSession, weekBorrowPawn,"other_pic","otherpic_old",1,"4");
		return 1;
	}
	
	/**
	 * 先删除旧图片再保存图片（单个）
	 * @param httpSession
	 * @param weekBorrowPawn
	 * @param newAttr
	 * @param type
	 * @author louchen 2014-12-8
	 */
	private void saveSinglePic(HttpSession httpSession,WeekBorrowPawn weekBorrowPawn
			,String newAttr,String type) {
		if (EmptyUtil.isNotEmpty(httpSession.getAttribute(newAttr))) {
			WeekBorrowPawnPic wbpp = this.selectByPrimaryKey((Integer) httpSession
					.getAttribute(newAttr));
			if(EmptyUtil.isNotEmpty(wbpp) && wbpp.getId()>0){
				//先删除
				WeekBorrowPawnPic old = new WeekBorrowPawnPic();
				old.setPawnId(weekBorrowPawn.getId());
				old.setType(type);
				List<WeekBorrowPawnPic> list = this.selectPicListByEntity(old);
				if(list.size()>0){
					old = list.get(0);
					this.deleteByPrimaryKey(old.getId());
				}
				//保存
				wbpp.setPawnId(weekBorrowPawn.getId());
				wbpp.setType(type);
				this.updateByPrimaryKeySelective(wbpp);
			}
		}
	}
	
	/**
	 * 先删除旧图片再保存图片（多个图片）
	 * @param httpSession
	 * @param weekBorrowPawn
	 * @param newAttr
	 * @param oldAttr
	 * @param type
	 * @author louchen 2014-12-8
	 */
	private void savePic(HttpSession httpSession, WeekBorrowPawn weekBorrowPawn
			,String newAttr,String oldAttr,Integer cursor,String type) {
		System.out.println(httpSession.getAttribute(newAttr+cursor));
		if (EmptyUtil.isNotEmpty(httpSession.getAttribute(newAttr+cursor))) {
			WeekBorrowPawnPic wbpp = this.selectByPrimaryKey((Integer) httpSession
					.getAttribute(newAttr+cursor));
			if(EmptyUtil.isNotEmpty(wbpp) && wbpp.getId()>0){
				//先删除
				ArrayList<Integer> ids = (ArrayList<Integer>) httpSession.getAttribute(oldAttr);
				cursor--;
				if(EmptyUtil.isNotEmpty(ids) && cursor<ids.size() && EmptyUtil.isNotEmpty(ids.get(cursor))){
					WeekBorrowPawnPic old = this.weekBorrowPawnPicMapper.selectByPrimaryKey(ids.get(cursor));
					if(EmptyUtil.isNotEmpty(old) && old.getId()>0 ){
						this.weekBorrowPawnPicMapper.deleteByPrimaryKey(old.getId());
					}
				}
				//保存
				wbpp.setPawnId(weekBorrowPawn.getId());
				wbpp.setType(type);
				this.updateByPrimaryKeySelective(wbpp);
			}
		}
	}

	@Override
	public List<WeekBorrowPawnPic> selectPicListByEntity(
			WeekBorrowPawnPic entity) {
		return this.weekBorrowPawnPicMapper.selectPicListByEntity(entity);
	}

}