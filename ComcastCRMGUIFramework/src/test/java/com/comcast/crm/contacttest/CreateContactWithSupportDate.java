package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDate
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
		
		Sheet sheet=workbook.getSheet("Contact");
		
		sheet.getRow(1).createCell(3).setCellValue("Offline");
		
		driver.findElement(By.name("user_name")).sendKeys(p.getProperty("username"));
		driver.findElement(By.name("user_password")).sendKeys(p.getProperty("password"));
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String lastName = sheet.getRow(4).getCell(2).getStringCellValue()+random.nextInt(1000);
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		Date date = new Date();
		SimpleDateFormat sif = new SimpleDateFormat("yyyy-MM-dd");
		String startDate=sif.format(date);
		
		Calendar cal = sif.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate=sif.format(cal.getTime());
		
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		driver.findElement(By.xpath("//input[contains(@value,'Save')and @name='button']")).click();
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
	}
}
