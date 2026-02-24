package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithOrgTest 
{
	static WebDriver driver;
	public static void main(String[] args) throws IOException
	{
		JavaUtility ju=new JavaUtility();
		FileInputStream fis=new FileInputStream("D:\\Training\\Selenium\\SeleniumCRMGUIFramework\\Files\\CommonData.properties");

		Properties p=new Properties();
		p.load(fis);
		
		if (p.getProperty("browser").equals("chrome")) {
			driver = new ChromeDriver();
		} else if (p.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (p.getProperty("browser").equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(p.getProperty("url"));
		String parent=driver.getWindowHandle();
		
		FileInputStream fis2=new FileInputStream("D:\\Training\\Selenium\\SeleniumCRMGUIFramework\\Files\\TestScriptData.xlsx");
		
		Workbook workbook = WorkbookFactory.create(fis2);
		
		Sheet sheet=workbook.getSheet("Contact");
		
		sheet.getRow(1).createCell(3).setCellValue("Offline");
		
		driver.findElement(By.name("user_name")).sendKeys(p.getProperty("username"));
		driver.findElement(By.name("user_password")).sendKeys(p.getProperty("password"));
		driver.findElement(By.id("submitButton")).click();
		
		String orgName = sheet.getRow(1).getCell(2).getStringCellValue()+ju.getRandomNumber();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("bill_street")).sendKeys("KPHB");
		driver.findElement(By.xpath("//input[@onclick='return copyAddressRight(EditView)']")).click();
		driver.findElement(By.xpath("//input[contains(@value,'Save')and @name='button']")).click();
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
	
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String lastName = sheet.getRow(4).getCell(2).getStringCellValue()+ju.getRandomNumber();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		/*
		String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actLastName.equals(lastName))
		{
			System.out.println(lastName+" was verified");
		}
		else
		{			
			System.out.println(lastName+" was not verified");
		}
		*/
		String startDate=ju.getSystemDate();
		String endDate=ju.getRequiredDate(30);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		Set<String> ids=driver.getWindowHandles();
		for(String id:ids)
		{
			driver.switchTo().window(id);
			if(!id.equals(parent))
			{
				driver.findElement(By.id("search_txt")).sendKeys(actOrgName);
				driver.findElement(By.xpath("//input[@name='search']")).click();
				driver.findElement(By.linkText(actOrgName)).click();
				driver.switchTo().window(parent);
			}
		}
		
		driver.findElement(By.xpath("//input[contains(@value,'Save')and @name='button']")).click();
		/*
		String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startDate))
		{
			System.out.println(startDate+" was verified");
		}
		else
		{			
			System.out.println(startDate+" was not verified");
		}
		String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.equals(endDate))
		{
			System.out.println(endDate+" was verified");
		}
		else
		{			
			System.out.println(endDate+" was not verified");
		}
		*/		
	}
}



























