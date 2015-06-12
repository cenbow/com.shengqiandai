package cn.vfunding.vfunding.biz.trial.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.excel.utils.PoiExcel2k3Helper;
import cn.vfunding.common.framework.excel.utils.PoiExcel2k7Helper;
import cn.vfunding.common.framework.excel.utils.PoiExcelHelper;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.hikes.service.IHikesCardService;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;
import cn.vfunding.vfunding.biz.message.service.IGiftboxMessageService;
import cn.vfunding.vfunding.biz.message.service.IHikesMessageService;
import cn.vfunding.vfunding.biz.trial.dao.GiftboxBatchMapper;
import cn.vfunding.vfunding.biz.trial.model.GiftboxBatch;
import cn.vfunding.vfunding.biz.trial.model.GiftboxBatchInfo;
import cn.vfunding.vfunding.biz.trial.model.GiftboxTemplate;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxBatchInfoService;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxBatchService;
import cn.vfunding.vfunding.biz.trial.service.IGiftboxTemplateService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Service("giftboxBatchService")
public class GiftboxBatchServiceImpl implements IGiftboxBatchService {

	@Autowired
	private GiftboxBatchMapper giftboxBatchMapper;
	@Autowired
	private IGiftboxBatchInfoService giftboxBatchInfoService;
	@Autowired
	private IGiftboxTemplateService giftboxTemplateService;
	@Autowired
	private IGiftboxMessageService giftboxMessageService;
	@Autowired
	private IHikesCardService hikesCardService;
	@Autowired
	private IHikesMessageService hikesMessageService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return giftboxBatchMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(GiftboxBatch record) {
		// TODO Auto-generated method stub
		return giftboxBatchMapper.insertSelective(record);
	}

	@Override
	public GiftboxBatch selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return giftboxBatchMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GiftboxBatch record) {
		// TODO Auto-generated method stub
		return giftboxBatchMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Json importExcelAndBatch(GiftboxBatch giftboxBatch, ArrayList<ArrayList<String>> is, String fileName) {
		Json j = new Json();
		this.insertSelective(giftboxBatch);
		GiftboxTemplate gt = giftboxTemplateService.selectByPrimaryKey(giftboxBatch.getTemplateId());
		// 获取excel第一个sheet 的所有数据
		ArrayList<ArrayList<String>> dataList = is;
		int dlSize = dataList.size();
		BigDecimal moneyCount = new BigDecimal(0);
		if (gt.getType() == 1) {// 红包
			moneyCount = this.readGift(dlSize, dataList, moneyCount, giftboxBatch, gt, j);
		} else if (gt.getType() == 2) {// 加息
			moneyCount = this.readHikes(dlSize, dataList, moneyCount, giftboxBatch, gt, j);
		}
		giftboxBatch.setMoneyCount(moneyCount);
		giftboxBatch.setUserCount(dataList.size() - 1);
		this.updateByPrimaryKeySelective(giftboxBatch);
		j.setMsg("导入Excel成功！");
		j.setSuccess(true);
		return j;
	}

	private BigDecimal readGift(int dlSize, ArrayList<ArrayList<String>> dataList, BigDecimal moneyCount, GiftboxBatch giftboxBatch, GiftboxTemplate gt, Json j) {
		// 循环读取每一行，第一行为标题不进行循环
		for (int i = 1; i < dlSize; i++) {
			// 读取每行中的每个单元格
			if (!StringUtils.isEmpty(dataList.get(i).get(0)) && Integer.parseInt(dataList.get(i).get(0).trim()) > 0) {
				ArrayList<String> data = dataList.get(i);
				GiftboxBatchInfo gbi = new GiftboxBatchInfo();
				String otherData = "";
				// 判断当前数据所在的列的标题是否为金额，如果
				for (int k = 3; k < data.size(); k++) {
					ArrayList<String> titleList = dataList.get(0);
					String title = titleList.get(k);
					if (title.equals("money")) {
						gbi.setMoney(new BigDecimal(data.get(k)));
						BigDecimal m = new BigDecimal(data.get(k));
						moneyCount = moneyCount.add(m);
					}
					// 存储除 ID 生效 失效时间外的数据集合
					otherData += data.get(k) + ",";
				}
				try {
					gbi.setAddtime(new Date());
					gbi.setBatchId(giftboxBatch.getId());
					gbi.setReceiveUser(Integer.parseInt(data.get(0)));
					gbi.setTitle(giftboxBatch.getTitle());
					gbi.setSendUser(EmployeeSession.getEmpSessionEmpId());
					gbi.setTakeTime(new SimpleDateFormat("yyyy-MM-dd").parse(data.get(1)));
					gbi.setLoseTime(new SimpleDateFormat("yyyy-MM-dd").parse(data.get(2)));
					gbi.setOtherdata(otherData);
					if (!StringUtils.isEmpty(otherData)) {
						String gbiContent = String.format(gt.getContent(), otherData.split(","));
						gbi.setContent(gbiContent);
					}
					giftboxBatchInfoService.insertSelective(gbi);
				} catch (ParseException e) {
					j.setMsg("导入Excel异常！请检查Excel!");
					e.printStackTrace();
				}
			}
		}
		return moneyCount;
	}

	private BigDecimal readHikes(int dlSize, ArrayList<ArrayList<String>> dataList, BigDecimal moneyCount, GiftboxBatch giftboxBatch, GiftboxTemplate gt, Json j){
		// 循环读取每一行，第一行为标题不进行循环
		for (int i = 1; i < dlSize; i++) {
			// 读取每行中的每个单元格
			if (!StringUtils.isEmpty(dataList.get(i).get(0)) && Integer.parseInt(dataList.get(i).get(0).trim()) > 0) {
				ArrayList<String> data = dataList.get(i);
				GiftboxBatchInfo gbi = new GiftboxBatchInfo();
				gbi.setMoney(new BigDecimal(data.get(1)));
				BigDecimal m = new BigDecimal(data.get(1));
				moneyCount = moneyCount.add(m);
				gbi.setAddtime(new Date());
				gbi.setBatchId(giftboxBatch.getId());
				gbi.setReceiveUser(Integer.parseInt(data.get(0)));
				gbi.setTitle(giftboxBatch.getTitle());
				gbi.setSendUser(EmployeeSession.getEmpSessionEmpId());
				gbi.setTakeTime(null);
				gbi.setLoseTime(null);
				gbi.setOtherdata(data.get(1));
				String gbiContent = String.format(gt.getContent(), data.get(1));
				gbi.setContent(gbiContent);
				giftboxBatchInfoService.insertSelective(gbi);
			}
		}
		return moneyCount;
	}

	@Override
	public List<GiftboxBatch> selectTiralGiftboxListPage(PageSearch pageSearch) {
		List<GiftboxBatch> gbList = this.giftboxBatchMapper.selectTiralGiftboxListPage(pageSearch);
		for (GiftboxBatch gb : gbList) {
			GiftboxTemplate gt = giftboxTemplateService.selectByPrimaryKey(gb.getTemplateId());
			if (gt != null) {
				gb.setTemplateName(gt.getName());
				gb.setTemplateContent(gt.getContent());
			}
		}
		return gbList;
	}

	@Override
	public void tiralGiftbox(GiftboxBatch giftboxBatch) {
		giftboxBatchMapper.updateByPrimaryKeySelective(giftboxBatch);
		List<GiftboxBatchInfo> gbiList = giftboxBatchInfoService.selectByBacthId(giftboxBatch.getId());
		GiftboxTemplate gt = this.giftboxTemplateService.selectByPrimaryKey(giftboxBatch.getTemplateId());
		if(gt.getType() == 1){
			this.sendGiftbox(gbiList);
		}else if(gt.getType() == 2){
			this.sendHikes(gbiList);
		}
	}

	private void sendGiftbox(List<GiftboxBatchInfo> gbiList){
		for (GiftboxBatchInfo gbi : gbiList) {
			GiftboxMessage gm = new GiftboxMessage();
			gm.setAddtime(new Date());
			gm.setContent(gbi.getContent());
			gm.setIsLook(0);
			gm.setLoseTime(gbi.getLoseTime());
			gm.setMoney(gbi.getMoney());
			gm.setReceiveUser(gbi.getReceiveUser());
			gm.setSendUser(gbi.getSendUser());
			gm.setStatus(0);
			gm.setTakeTime(gbi.getTakeTime());
			gm.setTitle(gbi.getTitle());
			gm.setType(gbi.getType());
			giftboxMessageService.insertSelective(gm);
		}
	}
	
	private void sendHikes(List<GiftboxBatchInfo> gbiList){
		for (GiftboxBatchInfo giftboxBatchInfo : gbiList) {
			HikesCard hc = this.hikesCardService.selectByPrimaryKey(giftboxBatchInfo.getReceiveUser());
			BigDecimal rate = hc.getUseRate().add(giftboxBatchInfo.getMoney());
			hc.setUseRate(rate);
			this.hikesCardService.updateByPrimaryKeySelective(hc);
			HikesMessage hm = new HikesMessage();
			hm.setAddtime(new Date());
			hm.setContent(giftboxBatchInfo.getContent());
			hm.setIsLook(0);
			hm.setRate(giftboxBatchInfo.getMoney());
			hm.setReceiveUser(giftboxBatchInfo.getReceiveUser());
			hm.setSendUser(giftboxBatchInfo.getSendUser());
			hm.setTitle(giftboxBatchInfo.getTitle());
			this.hikesMessageService.insertSelective(hm);
		}
	}
	
}
