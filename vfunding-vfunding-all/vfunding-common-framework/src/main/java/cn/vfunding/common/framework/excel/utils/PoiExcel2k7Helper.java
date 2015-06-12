package cn.vfunding.common.framework.excel.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiExcel2k7Helper extends PoiExcelHelper
{
  public ArrayList<String> getSheetList(InputStream is)
  {
    ArrayList sheetList = new ArrayList(0);
    try {
      XSSFWorkbook wb = new XSSFWorkbook(is);
      Iterator iterator = wb.iterator();
      while (iterator.hasNext())
        sheetList.add(((XSSFSheet)iterator.next()).getSheetName());
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
      XSSFWorkbook wb = new XSSFWorkbook(is);
      XSSFSheet sheet = wb.getSheetAt(sheetIndex);

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
      XSSFWorkbook wb = new XSSFWorkbook(is);
      XSSFSheet sheet = wb.getSheetAt(sheetIndex);

      dataList = readExcel(sheet, rows, cols);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dataList;
  }
}