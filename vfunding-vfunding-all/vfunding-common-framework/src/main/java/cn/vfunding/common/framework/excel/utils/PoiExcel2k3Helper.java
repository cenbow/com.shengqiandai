package cn.vfunding.common.framework.excel.utils;

import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiExcel2k3Helper extends PoiExcelHelper
{
  public ArrayList<String> getSheetList(InputStream is)
  {
    ArrayList sheetList = new ArrayList(0);
    try {
      HSSFWorkbook wb = new HSSFWorkbook(is);
      int i = 0;
      try {
        while (true) {
          String name = wb.getSheetName(i);
          sheetList.add(name);
          i++;
        }
      } catch (Exception e) {
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return sheetList;
  }

  public ArrayList<ArrayList<String>> readExcel(InputStream is, int sheetIndex, String rows, String columns)
  {
    ArrayList dataList = new ArrayList();
    try {
      HSSFWorkbook wb = new HSSFWorkbook(is);
      HSSFSheet sheet = wb.getSheetAt(sheetIndex);

      dataList = readExcel(sheet, rows, getColumnNumber(sheet, columns));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dataList;
  }

  public ArrayList<ArrayList<String>> readExcel(InputStream is, int sheetIndex, String rows, int[] cols)
  {
    ArrayList dataList = new ArrayList();
    try {
      HSSFWorkbook wb = new HSSFWorkbook(is);
      HSSFSheet sheet = wb.getSheetAt(sheetIndex);

      dataList = readExcel(sheet, rows, cols);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dataList;
  }
}