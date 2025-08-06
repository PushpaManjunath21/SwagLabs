package practicebytrainer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAsserts {
    @Test
    public void HardAsserts() {

	//Note:- here all methods consists of static methods so we using it by "class name"

	//1.assertEquals()
	//	System.out.println("Execution Started");
	//	Assert.assertEquals("hdfc", "hfdc");
	//	System.out.println("After Assertion");

	//2.assertNotEquals()
	//	System.out.println("Execution Started");
	//	Assert.assertNotEquals("hdfc", "hfdc");
	//	System.out.println("After Assertion");

	//3.assertTrue() 
	// it expects condtion in method
	//	System.out.println("Execution Started");
	//	Assert.assertTrue("hdfc".equals("hfdc"));
	//	System.out.println("After Assertion");

	//4.assertFalse() 
	// it expects condtion in method
	//	System.out.println("Execution Started");
	//	Assert.assertFalse("hdfc".equals("hfdc"));
	//	System.out.println("After Assertion");

	//5.assertNull() 
	// a container is required to show it null/not null
	//	String var = null;
	//	System.out.println("Execution Started");
	//	Assert.assertNull(var);
	//	System.out.println("After Assertion");

	//6.assertNotNull() 
	// a container is required to show it null/not null
	//	String var1 = "Dell";
	//	System.out.println("Execution Started");
	//	Assert.assertNotNull(var1);
	//	System.out.println("After Assertion");

    }

    @Test
    public void SoftAsserts() {
	//Note:- here all methods consists of Non-static methods so we using it by "Object reference"
	// by creating Object

	//a.Object creating for SoftAssert
	SoftAssert sftassert= new SoftAssert();
	String var2=null;
	System.out.println("Execution Started");
	//a.with the help of objectReference "SftAssert" we calling methods
	sftassert.assertNull(var2);
	System.out.println("After Assertion");
	sftassert.assertAll();

    }
}
