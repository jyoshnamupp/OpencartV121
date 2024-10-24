package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils
{

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	String path;
	
	public ExcelUtils(String path)
	{
		this.path=path;
	}
	
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		wb.close();
		fi.close();
		
		 String data;
		 
		 try
		 {
			 data=cell.toString();
		 }
		 catch(Exception e)
		 {
			 data ="";
		 }
		 wb.close();
		 fi.close();
		 return data;
	
	}
	
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		 fi.close();
		 fo.close();
	}
	
	
	
	
	
}
