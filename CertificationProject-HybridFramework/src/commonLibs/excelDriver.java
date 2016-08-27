package commonLibs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDriver {

 private InputStream oFileReader;
 private OutputStream oFileWriter;
 private Workbook excelWorkbook;
 private String excelFileName;

 
//*******************************************************************************************************************************************************************
//This is the excelDriver class. This class contains all the methods that are needed by framework to interact with excel sheets. Using this class
//the activities like creating, opening, saving, excel files and workbooks.
//*******************************************************************************************************************************************************************
 
 //method createExcelWorkbook
 //This method creates a new excel workbook with the filename and path provided.
 public void createExcelWorkbook(String fileName) {
   try {
    fileName = fileName.trim();

    if (fileName.isEmpty()) {
     throw new Exception("excelDriver->createExcelWorkbook()->Empty filename supplied to create new Excel workbook");
    }

    if (new File(fileName).exists()) {
     throw new Exception("excelDriver->createExcelWorkbook()->Could not create Excel workbook; file already exists");
    }

    if (fileName.endsWith("xlsx")) {
     excelWorkbook = new XSSFWorkbook();
    } else if (fileName.endsWith("xls")) {
     excelWorkbook = new HSSFWorkbook();
    } else {
     throw new Exception("excelDriver->createExcelWorkbook()->Invalid file type supplied to create Excel workbook");
    }

    oFileWriter = new FileOutputStream(fileName);
    excelWorkbook.write(oFileWriter);
    oFileWriter.close();
    excelWorkbook.close();
   } catch (Exception e) {
    System.out.println("excelDriver->createExcelWorkbook()->Could not create Excel workbook; here is some more detail: ");
    e.printStackTrace();
   }
  } //END createExcelWorkbook
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method openExcelSheet
  //This method opens the excel files from the given path
 public void openExcelSheet(String fileName) {
   try {
    fileName = fileName.trim();

    if (fileName.isEmpty()) {
     throw new Exception("excelDriver->openExcelSheet()->Empty filename provided to open Excel sheet");
    }

    oFileReader = new FileInputStream(fileName);
    excelFileName = fileName;
    excelWorkbook = WorkbookFactory.create(oFileReader);

   } catch (Exception e) {
    System.out.println("excelDriver->openExcelSheet()->Could not open specified Excel sheet; here is some more detail: ");
    e.printStackTrace();
   }
  } //END openExcelSheet
 
 
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method save
  //This method saves an open excel file.
 public void save() {
   try {
    oFileWriter = new FileOutputStream(excelFileName);
    excelWorkbook.write(oFileWriter);
    oFileWriter.close();
   } catch (Exception e) {
    System.out.println("excelDriver->save()->Could not save the Excel file; here is some more detail: ");
    e.printStackTrace();
   }
  } //END save
 
 
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method saveAs
  //This method saves a currently open excel file, into a new file
 public void saveAs(String newFileName) {
   try {
    newFileName = newFileName.trim();

    if (newFileName.isEmpty()) {
     throw new Exception("excelDriver->saveAs()->Could not save Excel file. Reason: Empty filename");
    }
    if (new File(newFileName).exists()) {
     throw new Exception("excelDriver->saveAs()->File alrady exists");
    }

    oFileWriter = new FileOutputStream(newFileName);
    excelWorkbook.write(oFileWriter);
    oFileWriter.close();

   } catch (Exception e) {
    System.out.println("excelDriver->saveAs()->Could not save the Excel file; here is some more detail: ");
    e.printStackTrace();
   }
  } //END SaveAs
 
 
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method close
  //This method closed an open excel file
 public void close() {
   try {
    excelWorkbook.close();
    oFileReader.close();
   } catch (Exception e) {
    System.out.println("excelDriver->close()->Could not close Excel workbook; here is some more detail: ");
    e.printStackTrace();
   }
  } //END close
 
 
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method createExcelSheet
  //This method create new excel sheet in a workbook
 public void createExcelSheet(String sheetName) {
   try {
    sheetName = sheetName.trim();

    if (sheetName.isEmpty()) {
     throw new Exception("excelDriver->createExcelSheet()->Empty sheet name supplied");
    }
    Sheet oSheet = excelWorkbook.getSheet(sheetName);
    if (oSheet != null) {
     throw new Exception("excelDriver->createExcelSheet()->Sheet already exists");
    }
    excelWorkbook.createSheet(sheetName);
   } catch (Exception e) {
    System.out.println("excelDriver->createExcelSheet()->Could not create Excel sheet; here is some more detail: ");
    e.printStackTrace();
   }
  } //END createExcelSheet
  
 
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method getRowCount
  //This method gets the physical row count from an excel sheet
 public int getRowCount(String sheetName) {
   try {	   
    sheetName = sheetName.trim();
    if (sheetName.isEmpty()) {
     throw new Exception("excelDriver->getRowCount()->Sheetname is empty");
    }

    Sheet excelSheet = excelWorkbook.getSheet(sheetName);
    if (excelSheet == null) {
     throw new Exception("excelDriver->getRowCount()->Sheet not found in the Excel file");
    }

    return excelSheet.getPhysicalNumberOfRows();

   } catch (Exception e) {
    System.out.println("excelDriver->getRowCount()->Could not get row count; here is some more detail: ");
    e.printStackTrace();
    return -1;
   }
  } //END getRowCount
 
 
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method getCellCount
  //This methods gets the cell count from an excel sheet row 
 public int getCellCount(String sheetName, int rowNumber) {
   try {
    sheetName = sheetName.trim();
    if (sheetName.isEmpty()) {
     throw new Exception("excelDriver->getCellCount()->Invalid sheetname");
    }
    if (rowNumber < 1) {
     throw new Exception("excelDriver->getCellCount()->Invalid row number");
    }
    Sheet excelSheet = excelWorkbook.getSheet(sheetName);
    if (excelSheet == null) {
     throw new Exception("excelDriver->getCellCount()->Sheet doesn't exist");
    }

    Row excelRow;
    excelRow = excelSheet.getRow(rowNumber - 1);
    if (excelRow == null) {
     throw new Exception("excelDriver->getCellCount()->Row doesn't exist");
    } else {
     return excelRow.getLastCellNum();
    }
   } catch (Exception e) {
    System.out.println("excelDriver->getCellCount()->Could not get cell count; here is some more detail: ");
    e.printStackTrace();
    return -1;
   }
  } //END getCellCount
 
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method getCellData
  //This methods reads the data from a specific cell in an excel sheet
 public String getCellData(String sheetName, int rowNumber, int cellNumber) {
   try {
    sheetName = sheetName.trim();   	
    if (sheetName.isEmpty()) {
     throw new Exception("excelDriver->getCellData()->Invalid sheetname");
    }
    if (rowNumber < 1 || cellNumber < 1) {
     throw new Exception("excelDriver->getCellData()->Invalid row/cell number");
    }
    Sheet excelSheet = excelWorkbook.getSheet(sheetName);
    if (excelSheet == null) {    	
    throw new Exception("excelDriver->getCellData()->Sheet doesn't exist");
    }
    Row excelRow = excelSheet.getRow(rowNumber - 1);   
    if (excelRow == null) {
     return ""; //throw new Exception ("excelDriver->getCellData()->Invalid row number");
    }
    Cell excelCell = excelRow.getCell(cellNumber - 1);
    if (excelCell == null) {
     return ""; //throw new Exception ("excelDriver->getCellData()->Invalid cell number");			
    }
    if (excelCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
    return String.valueOf((int) excelCell.getNumericCellValue());
    } else {    
     return excelCell.getStringCellValue();
    }

   } catch (Exception e) {
    System.out.println("excelDriver->getCellData()->Could not get cell data; here is some more detail: ");
    e.printStackTrace();
    return "";
   }
  }
  
 
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------
  //method setCellData
  //This method writes the data from a specific cell
 public void setCellData(String sheetName, String stringData, int rowNumber, int cellNumber) {
   try {
    sheetName = sheetName.trim();
    if (sheetName.isEmpty()) {
     throw new Exception("excelDriver->setCellData()->Invalid sheet name; cannot set cell data");
    }
    if (rowNumber < 1 || cellNumber < 1) {
     throw new Exception("excelDriver->setCellData()->Invalid row/cell number");
    }
    Sheet excelSheet = excelWorkbook.getSheet(sheetName);    
    if (excelSheet == null) {
     throw new Exception("excelDriver->setCellData()->Sheet does not exist");
    }

    Row excelRow = excelSheet.getRow(rowNumber - 1);
    if (excelRow == null) {
     excelRow = excelSheet.createRow(rowNumber - 1);
     excelRow = excelSheet.getRow(rowNumber - 1);
    }

    Cell excelCell = excelRow.getCell(cellNumber - 1);
    if (excelCell == null) {
     excelCell = excelRow.createCell(cellNumber - 1);
     excelCell = excelRow.getCell(cellNumber - 1);
    }

    excelCell.setCellValue(stringData);

   } catch (Exception e) {
    System.out.println("excelDriver->setCellData()->Could not set cell data; here is some more detail: ");
    e.printStackTrace();
   }
  }
  //---------------------------------------------------------------------------------------------------------------------------------------------	
  //---------------------------------------------------------------------------------------------------------------------------------------------

} //END CLASS