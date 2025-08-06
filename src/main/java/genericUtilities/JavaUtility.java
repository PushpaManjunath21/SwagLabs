package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class consists of reusable methods related to java
 * @author manju
 */

public class JavaUtility {
	
	/**
	 * This method will capture the current system date in required format and return to caller
	 * @author manju
	 * @return string
	 */
	
	public String GetSystemDateInFormat() {
		
		//creating date object first from Java.Util Package
		Date d= new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
		 String dateInString = f.format(d);
		 return dateInString;
		
	}
}
