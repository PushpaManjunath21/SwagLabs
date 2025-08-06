package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author 
 * this class consists of reusable methods related to file Operations
 * 
 */

public class FileUtility {

	/**
	 * DESCRIPTION for GENERIC(Reusable) METHOD
	 * This method will read data from property file and return the value to caller MAIN method
	 * @param key
	 * @return value
	 * @throws IOException
	 */

	public String readDataFromPropertyFile(String key) throws IOException {

		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties(); // creating object for Property file 
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}


	/**
	 *  DESCRIPTION for GENERIC(Reusable) METHOD
	 * This method will read data from Excel file and return the value to caller MAIN method
	 * @param sheetname
	 * @param rowno
	 * @param cellno
	 * @return Value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcelFile(String sheetname, int rowno, int cellno  ) throws EncryptedDocumentException, IOException {

		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\SwagLabTestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);// creating object for Excel file 
		Sheet sh = wb.getSheet(sheetname);
		Row rw = sh.getRow(rowno);
		Cell cl = rw.getCell(cellno);
		String value = cl.getStringCellValue();
		return value;

	}

}
