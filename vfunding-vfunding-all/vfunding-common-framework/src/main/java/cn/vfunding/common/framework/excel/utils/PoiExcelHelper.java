package cn.vfunding.common.framework.excel.utils;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public abstract class PoiExcelHelper
{
  public static final String SEPARATOR = ",";
  public static final String CONNECTOR = "-";

  public abstract ArrayList<String> getSheetList(InputStream paramInputStream);

  public ArrayList<ArrayList<String>> readExcel(InputStream is, int sheetIndex)
  {
    return readExcel(is, sheetIndex, "1-", "1-");
  }

  public ArrayList<ArrayList<String>> readExcel(InputStream is, int sheetIndex, String rows)
  {
    return readExcel(is, sheetIndex, rows, "1-");
  }

  public ArrayList<ArrayList<String>> readExcel(InputStream is, int sheetIndex, String[] columns)
  {
    return readExcel(is, sheetIndex, "1-", columns);
  }

  public abstract ArrayList<ArrayList<String>> readExcel(InputStream paramInputStream, int paramInt, String paramString1, String paramString2);

  public ArrayList<ArrayList<String>> readExcel(InputStream is, int sheetIndex, String rows, String[] columns)
  {
    int[] cols = getColumnNumber(columns);

    return readExcel(is, sheetIndex, rows, cols);
  }

  public abstract ArrayList<ArrayList<String>> readExcel(InputStream paramInputStream, int paramInt, String paramString, int[] paramArrayOfInt);

  protected ArrayList<ArrayList<String>> readExcel(Sheet sheet, String rows, int[] cols)
  {
    ArrayList dataList = new ArrayList();

    String[] rowList = rows.split(",");
    for (String rowStr : rowList) {
      if (rowStr.contains("-")) {
        String[] rowArr = rowStr.trim().split("-");
        int start = Integer.parseInt(rowArr[0]) - 1;
        int end;
        if (rowArr.length == 1)
          end = sheet.getLastRowNum();
        else {
          end = Integer.parseInt(rowArr[1].trim()) - 1;
        }
        dataList.addAll(getRowsValue(sheet, start, end, cols));
      } else {
        dataList.add(getRowValue(sheet, Integer.parseInt(rowStr) - 1, cols));
      }
    }
    return dataList;
  }

  protected ArrayList<ArrayList<String>> getRowsValue(Sheet sheet, int startRow, int endRow, int startCol, int endCol)
  {
    if ((endRow < startRow) || (endCol < startCol)) {
      return null;
    }

    ArrayList data = new ArrayList();
    for (int i = startRow; i <= endRow; i++) {
      data.add(getRowValue(sheet, i, startCol, endCol));
    }
    return data;
  }

  private ArrayList<ArrayList<String>> getRowsValue(Sheet sheet, int startRow, int endRow, int[] cols)
  {
    if (endRow < startRow) {
      return null;
    }

    ArrayList data = new ArrayList();
    for (int i = startRow; i <= endRow; i++) {
      data.add(getRowValue(sheet, i, cols));
    }
    return data;
  }

  private ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int startCol, int endCol)
  {
    if (endCol < startCol) {
      return null;
    }

    Row row = sheet.getRow(rowIndex);
    ArrayList rowData = new ArrayList();
    for (int i = startCol; i <= endCol; i++) {
      rowData.add(getCellValue(row, i));
    }
    return rowData;
  }

  private ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int[] cols)
  {
    Row row = sheet.getRow(rowIndex);
    ArrayList rowData = new ArrayList();
    for (int colIndex : cols) {
      rowData.add(getCellValue(row, colIndex));
    }
    return rowData;
  }

  protected String getCellValue(Row row, String column)
  {
    return getCellValue(row, getColumnNumber(column));
  }

  private String getCellValue(Row row, int col)
  {
    if (row == null) {
      return "";
    }
    Cell cell = row.getCell(col);
    return getCellValue(cell);
  }

  private String getCellValue(Cell cell)
  {
    if (cell == null) {
      return "";
    }
    String value = "";
    DecimalFormat df = new DecimalFormat("##.##");
    switch (cell.getCellType()) {
    case 1:
      value = cell.getRichStringCellValue().getString().trim();
      break;
    case 0:
      if (HSSFDateUtil.isCellDateFormatted(cell))
        value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
      else {
        value = df.format(cell.getNumericCellValue()).toString();
      }
      break;
    case 4:
      value = String.valueOf(cell.getBooleanCellValue()).trim();
      break;
    case 2:
      value = cell.getCellFormula();
      break;
    case 3:
    default:
      value = "";
    }
    return value;
  }

  private int[] getColumnNumber(String[] columns)
  {
    int[] cols = new int[columns.length];
    for (int i = 0; i < columns.length; i++) {
      cols[i] = getColumnNumber(columns[i]);
    }
    return cols;
  }

  private int getColumnNumber(String column)
  {
    int length = column.length();
    short result = 0;
    for (int i = 0; i < length; i++) {
      char letter = column.toUpperCase().charAt(i);
      int value = letter - 'A' + 1;
      result = (short)(int)(result + value * Math.pow(26.0D, length - i - 1));
    }
    return result - 1;
  }

  protected int[] getColumnNumber(Sheet sheet, String columns)
  {
    ArrayList result = new ArrayList();
    String[] colList = columns.split(",");
    for (String colStr : colList) {
      if (colStr.contains("-")) {
        String[] colArr = colStr.trim().split("-");
        int start = Integer.parseInt(colArr[0]) - 1;
        int end;
        if (colArr.length == 1)
          end = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum() - 1;
        else {
          end = Integer.parseInt(colArr[1].trim()) - 1;
        }
        for (int i = start; i <= end; i++)
          result.add(Integer.valueOf(i));
      }
      else {
        result.add(Integer.valueOf(Integer.parseInt(colStr) - 1));
      }

    }

    int len = result.size();
    int[] cols = new int[len];
    for (int i = 0; i < len; i++) {
      cols[i] = ((Integer)result.get(i)).intValue();
    }

    return cols;
  }
}