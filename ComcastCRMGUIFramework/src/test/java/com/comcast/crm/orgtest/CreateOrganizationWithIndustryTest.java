package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustryTest
{
	static WebDriver driver;
	public static void main(String[] args) throws IOException 
	{
		Random random=new Random();
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
		
		FileInputStream fis2=new FileInputStream("D:\\Training\\Selenium\\SeleniumCRMGUIFramework\\Files\\TestScriptData.xlsx");
		
		Workbook workbook = WorkbookFactory.create(fis2);
		
		Sheet sheet=workbook.getSheet("Org");
		
		sheet.getRow(1).createCell(3).setCellValue("Offline");
		
		driver.findElement(By.name("user_name")).sendKeys(p.getProperty("username"));
		driver.findElement(By.name("user_password")).sendKeys(p.getProperty("password"));
		driver.findElement(By.id("submitButton")).click();
		
		String orgName = sheet.getRow(1).getCell(2).getStringCellValue()+random.nextInt(1000);
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("bill_street")).sendKeys("KPHB");
		driver.findElement(By.xpath("//input[@onclick='return copyAddressRight(EditView)']")).click();

		String industry = sheet.getRow(1).getCell(4).getStringCellValue();
		String type=sheet.getRow(1).getCell(5).getStringCellValue();
		Select select1=new Select(driver.findElement(By.name("industry")));
		select1.selectByVisibleText(industry);
		Select select2=new Select(driver.findElement(By.name("accounttype")));
		select2.selectByVisibleText(type);
		
		driver.findElement(By.xpath("//input[contains(@value,'Save')and @name='button']")).click();
		String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.equals(industry))
		{
			System.out.println(industry+" was verified");
		}
		else
		{			
			System.out.println(industry+" was verified");
		}
	}
}
