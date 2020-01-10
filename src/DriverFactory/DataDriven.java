package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommanFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDriven {
WebDriver driver;
@BeforeTest
public void setUp() throws Throwable
{
	String launch=ERP_Functions.LaunchApp("http://webapp.qedge.com");
	Reporter.log(launch,true);
	String login=ERP_Functions.verifyLogin("admin", "master");
}
@Test
public void supplierCreation() throws Throwable
{
	//acessing excel util method
	ExcelFileUtil x1= new ExcelFileUtil();
	//row count 
	int rc=x1.rowCount("Suppiler");
	int cc=x1.colCount("Suppiler");
	Reporter.log("no of rows are ::"+rc+"   "+"no of coloumns are ::"+cc+"",true);
	for(int i=1;i<=rc;i++)
	{
		String sname=x1.getCellData("Suppiler", i, 0);
		String address=x1.getCellData("Suppiler", i, 1);
		String city=x1.getCellData("Suppiler", i, 2);
		String country=x1.getCellData("Suppiler", i, 3);
		String cperson=x1.getCellData("Suppiler", i, 4);
		String pnumber=x1.getCellData("Suppiler", i, 5);
		String mail=x1.getCellData("Suppiler", i, 6);
		String mnumber=x1.getCellData("Suppiler", i, 7);
		String note=x1.getCellData("Suppiler", i, 8);
		//call suppiler creation method
		String Result=ERP_Functions.verifySuppiler(sname, address, city, country, cperson, pnumber, mail, mnumber, note);
		Reporter.log(Result,true);
		x1.setCellData("Suppiler", i, 9, Result);
	}
	
}
@AfterTest
public void tearDown()
{
	ERP_Functions.verifylogout();
}
}
