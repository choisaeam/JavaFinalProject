package edu.handong.csee.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	public ArrayList<String> getData(String path) {
		ArrayList<String> values = new ArrayList<String>();
		
		System.out.println(path);
		
		try (InputStream inp = new FileInputStream(path)) {
		    //InputStream inp = new FileInputStream("workbook.xlsx");
		    
		        Workbook wb = WorkbookFactory.create(inp);
		        Sheet sheet = wb.getSheetAt(0);
		        Row row = sheet.getRow(2);
		        Cell cell = row.getCell(1);
		        if (cell == null)
		            cell = row.createCell(3);
		        
		        values.add(cell.getStringCellValue());
		        
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return values;
	}
	
	public ArrayList<ArrayList<String>> getData(InputStream is, int option) {
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		
		try (InputStream inp = is) {
		    //InputStream inp = new FileInputStream("workbook.xlsx");
		    
		        Workbook wb = WorkbookFactory.create(inp);
		        Sheet sheet = wb.getSheetAt(0);
		        Iterator<Row> iterator = sheet.iterator();
		        iterator.next();
		        if(option == 1) iterator.next();
		        while (iterator.hasNext()) {
		        	Row currentRow = iterator.next();
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                String str;
	                ArrayList<String> value = new ArrayList<String>();
	                
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();
		        		try {
		        	        str = cell.getStringCellValue();
		        	    } catch(IllegalStateException e) {
		        	        str = Integer.toString((int)cell.getNumericCellValue());            
		        	    }
		        		value.add(str);
		        	}
	                values.add(value);
	      
		        }
		        
		        
		        
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return values;
	}
}
