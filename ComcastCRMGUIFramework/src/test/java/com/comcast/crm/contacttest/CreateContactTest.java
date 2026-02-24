package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateContact;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactTest 
{
	static WebDriver driver;
	public static void main(String[] args) throws IOException
	{
		FileUtility fu=new FileUtility();
		JavaUtility ju=new JavaUtility();
		
		if (fu.getDataFromPropertiesFile("browser").equals("chrome")) {
			driver = new ChromeDriver();
		} else if (fu.getDataFromPropertiesFile("browser").equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (fu.getDataFromPropertiesFile("browser").equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(fu.getDataFromPropertiesFile("url"));
		
		LoginPage lp=new LoginPage(driver);
		lp.login(fu.getDataFromPropertiesFile("username"), fu.getDataFromPropertiesFile("password"));
		
		ExcelUtility eu=new ExcelUtility();
		String lastName=eu.getDataFromExcel("Contact", 4, 2)+ju.getRandomNumber();

		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		ContactPage cp=new ContactPage(driver);
		cp.getCreateContactBtn().click();
		
		CreateContact cc=new CreateContact(driver);
		cc.getLastname().sendKeys(lastName);
		
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
		cc.getStartDate().sendKeys(startDate);		
		cc.getEndDate().clear();
		cc.getEndDate().sendKeys(endDate);
		cc.getSaveBtn().click();
		
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
			System.out.println(actEndDate+" was not verified");
		}
	}
}