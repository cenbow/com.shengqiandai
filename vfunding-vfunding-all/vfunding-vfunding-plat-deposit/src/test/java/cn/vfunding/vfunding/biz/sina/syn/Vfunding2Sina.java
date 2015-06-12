package cn.vfunding.vfunding.biz.sina.syn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import com.alibaba.fastjson.JSON;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

public class Vfunding2Sina implements Callable<Result>  {

	private static String outputFile = "Vfunding2SinaMember.xls";
	private static long tempNumber = 13511220000L;
	private static int rows = 500;
	private static int index = 0;
	private static ThreadLocal<String> ThreadLocal = new ThreadLocal<String>();
	
	public synchronized static int getIndex() {

		return ++index;
	}
	
	public synchronized static String getTempNumber() {

		return String.valueOf(++tempNumber);
	}

	ISinaMemberManagerService sinaMemberManagerService;

	IUserService userService;

	private List<User> users;

	private Vfunding2Sina() {
	}

	public Vfunding2Sina(ISinaMemberManagerService sinaMemberManagerService, IUserService userService) {
		super();
		this.sinaMemberManagerService = sinaMemberManagerService;
		this.userService = userService;
	}
	
	private static int theadCount = 0;
	public synchronized static int getThreadCount(){
		return ++theadCount;
	}
	
	/*public Result call(){
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println(Thread.currentThread().getName() +" : " + i);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}*/
	
	public Result call() {
		ThreadLocal.set(Thread.currentThread().getName());
		
		queryUser();
		LinkedHashMap<String, HashMap<String, SynResult>> map = new LinkedHashMap<String,HashMap<String,SynResult>>();
		HashMap<String, SynResult> propertyMap  = null;
		List<String> titlelist = null;
		String firstUserId = null , lastUserId = null , userId = null;
		int count =0;
		for (User user : users) {
			propertyMap = new HashMap<String,SynResult>();
			titlelist = new ArrayList<String>();
			userId = user.getUserId() + "";
			if(count == 0)
				firstUserId = userId;
			lastUserId = userId;
			count ++ ;
			titlelist.add("user_id");
			propertyMap.put("user_id", new SynResult( true , userId));
			
			
			//创建激活会员
			BaseSyn createActivateMemberSyn = new CreateActivateMemberSyn(sinaMemberManagerService, user);
			titlelist.add(createActivateMemberSyn.operationName());
			
			BaseSyn mobileBindingVerifySyn = new MobileBindingVerifySyn(sinaMemberManagerService, user);
			titlelist.add(mobileBindingVerifySyn.operationName());
			
			BaseSyn setRealNameSyn = new SetRealNameSyn(sinaMemberManagerService, user);
			titlelist.add(setRealNameSyn.operationName());
			
			createActivateMemberSyn.setSuccessor(mobileBindingVerifySyn , setRealNameSyn);
			
			propertyMap.putAll(createActivateMemberSyn.doHandler());
			
			
			map.put(user.getUserId().toString(), propertyMap);
			System.out.println("["+ThreadLocal.get()+"]" + JSON.toJSONString(propertyMap));
		}
		System.out.println("["+ThreadLocal.get()+"]" + JSON.toJSONString(map));
		if(users.size()!=0)
			map2excel(firstUserId+"-"+lastUserId+"("+count+")" , titlelist , map);
		
		return new Result( !(users.size() == rows) , ThreadLocal.get());
	}

	private void queryUser() {
		PageSearch pageSearch = new PageSearch();
		pageSearch.setPage(getIndex());
		pageSearch.setRows(rows);
		pageSearch.setOrder("user_id");
		pageSearch.setSort("asc");
		users = userService.selectAllUsersByListPage(pageSearch);
	}
	
	public static int  sheetIndex = 0;
	public synchronized static void map2excel(String sheetName , List<String> titlelist , LinkedHashMap<String, HashMap<String, SynResult>> map){
		System.out.println(ThreadLocal.get() +" map2excel start!");
		HSSFWorkbook workbook = null;
		try {
			InputStream is = new FileInputStream(outputFile);  
			workbook = new HSSFWorkbook(is);
		} catch (FileNotFoundException e1) {
			workbook = new HSSFWorkbook();
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HSSFSheet sheet = null;
		try {
			sheet = workbook.createSheet(sheetName);
		} catch (Exception e1) {
			sheet = workbook.createSheet(sheetName+"("+getTempNumber()+")");
			e1.printStackTrace();
		} finally{
			sheetIndex ++ ;
		}
		HSSFRow row = sheet.createRow(0);
		
		for (int i = 0; i < titlelist.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(titlelist.get(i));
		}
		for (int i = 0; i < titlelist.size() ; i++) sheet.setColumnWidth(i, 10000);
		
		Iterator<String> it=map.keySet().iterator();
		int rowIndex=1;
		
		CellStyle style = workbook.createCellStyle();
	    //style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直    
       // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		//style.setFillForegroundColor(IndexedColors.RED.getIndex());
		//style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		HSSFFont font=workbook.createFont();
        font.setColor(HSSFColor.RED.index);//HSSFColor.VIOLET.index //字体颜色
        //font.setFontHeightInPoints((short)12);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        style.setFont(font);
		
		while(it.hasNext()){
			rowIndex++;
			HSSFRow newrow = sheet.createRow(rowIndex);
			String userId = it.next();
			HashMap<String, SynResult> propertyMap = map.get(userId);
			for (int i = 0; i < titlelist.size(); i++) {
				SynResult r = propertyMap.get(titlelist.get(i));
				HSSFCell cell = newrow.createCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(r.getResult());
				while(true){
					if(r != null){
						if(!r.isOk())
							cell.setCellStyle(style);
						break;
					}
				}
			}
		}
		try {
			FileOutputStream fOut = new FileOutputStream(outputFile);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.out.println(ThreadLocal.get()+" map2excel end! [sheetCount:"+sheetIndex+"]");
		}
	}
}
