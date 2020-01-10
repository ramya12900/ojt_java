package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import CommanFunLibrary.FuntionLibray;
import Utilities.ExcelFileUtil;

public class DriverScript {
static WebDriver driver;
@Test
public void ERP_Account()throws Throwable
{
	//creating reference object for excel util methods
	ExcelFileUtil xl=new ExcelFileUtil();
	//iterating all row in MasterTestCases sheet
	for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
	{
	if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
	{
		//store module name into TCModule 
		String TCModule=xl.getCellData("MasterTestCases", i, 1);
		//iterate all rows in TCModule sheet
		for(int j=1;j<=xl.rowCount(TCModule);j++)
		{
			//read all columns from TC Module
		String Description=xl.getCellData(TCModule, j, 0);
		String Function_Name=xl.getCellData(TCModule, j, 1);
		String Locator_Type=xl.getCellData(TCModule, j, 2);
		String Locator_Value=xl.getCellData(TCModule, j, 3);
		String Test_Data=xl.getCellData(TCModule, j, 4);
		try
		{
		if(Function_Name.equalsIgnoreCase("startBrowser"))
		{
		driver=FuntionLibray.startBrowser();	
		}
		else if(Function_Name.equalsIgnoreCase("openApplication"))
		{
			FuntionLibray.openApplication(driver);
		}
		else if(Function_Name.equalsIgnoreCase("waitForElement"))
		{
FuntionLibray.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
		}
		else if(Function_Name.equalsIgnoreCase("typeAction"))
		{
FuntionLibray.typeAction(driver, Locator_Type, Locator_Value, Test_Data);			
		}
		else if(Function_Name.equalsIgnoreCase("clickAction"))
		{
FuntionLibray.clickAction(driver, Locator_Type, Locator_Value);			
		}
		else if(Function_Name.equalsIgnoreCase("closeBrowser"))
		{
			FuntionLibray.closeBrowser(driver);
		}
		//write as pass into status column
		xl.setCellData(TCModule, j, 5, "Pass");
		xl.setCellData("MasterTestCases", i, 3, "Pass");
		}catch(Throwable t)
		{
		System.out.println("exception handled");
		xl.setCellData(TCModule, j, 5, "Fail");
		xl.setCellData("MasterTestCases", i, 3, "Fail");
		}
		}
	}
	else 
	{
		//write as not executed into status column in MasterTestCases
	xl.setCellData("MasterTestCases", i, 3, "Not Executed");	
	}
	}
}
}