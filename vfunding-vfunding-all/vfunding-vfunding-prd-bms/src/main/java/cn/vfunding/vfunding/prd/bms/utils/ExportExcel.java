package cn.vfunding.vfunding.prd.bms.utils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.alibaba.fastjson.JSON;

public class ExportExcel {

	public static void createExcel(List<Map<String, String>> listMap,
			List listDate, ByteArrayOutputStream out) {
		try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("iso8859-1");
			WritableWorkbook book = Workbook.createWorkbook(out, ws);
			WritableSheet sheet = book.createSheet("sheet1", 0);
			for (int i = 0; i < listMap.size(); i++) {
				sheet.addCell(new Label(i, 0, listMap.get(i).keySet()
						.iterator().next()));
			}
			for (int i = 0; i < listDate.size(); i++) {
				Map<String, String> map = (Map<String, String>) JSON.parse(JSON
						.toJSONString(listDate.get(i)));
				for (int j = 0; j < listMap.size(); j++) {
					String mapKey = listMap.get(j).keySet().iterator().next();
					String mapVal = listMap.get(j).get(mapKey);
					sheet.addCell(new Label(j, i + 1, String.valueOf(map
							.get(mapVal))));
				}
			}
			book.write();
			out.flush();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
