package com.comcast.crm.orgtest;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfo;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class DeleteOrgTest 
{
	static WebDriver driver;
	public static void main(String[] args) throws IOException 
	{
		FileUtility fu=new FileUtility();
		ExcelUtility eu=new ExcelUtility();
		JavaUtility ju=new JavaUtility();
		WebDriverUtility wdu=new WebDriverUtility();
		
		String browser=fu.getDataFromPropertiesFile("browser");
		String url=fu.getDataFromPropertiesFile("url");
		
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		
		LoginPage lp=new LoginPage(driver);
		lp.login("admin", "admin");
		
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		String orgName=eu.getDataFromExcel("Org", 4, 2)+ju.getRandomNumber();
//		String industry=eu.getDataFromExcel("Org", 1, 4);
		String org=eu.getDataFromExcel("Org", 3, 2);
		String address=eu.getDataFromExcel("Org", 1, 7);
		
		OrganizationPage orgPage=new OrganizationPage(driver);
		orgPage.getCreateOrgBtn().click();
		
		CreateOrganizationPage createOrgPage=new CreateOrganizationPage(driver);
		createOrgPage.createOrg(orgName, address);
//		createOrgPage.createOrg(orgName, "KPHB",industry);
		
		
		OrganizationInfo orgInfo = new OrganizationInfo(driver);
		String headerInfo = orgInfo.getHeaderMsg().getText();
		if(headerInfo.contains(orgName))
		{
			System.out.println(orgName+" created successful");
		}
		else
		{			
			System.out.println(orgName+" not successful");
		}
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgName.equals(orgName))
		{
			System.out.println(orgName+" created successful");
		}
		else
		{			
			System.out.println(orgName+" not successful");
		}
		
//		go to organization page
		hp.getOrgLink().click();
		
//		search for organization
		orgPage.getSearchField().sendKeys(orgName);
		wdu.select(orgPage.getDropDown(), org);
		orgPage.getSearchBtn().click();
		
//		in dunamic webtable select and delete org
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/..//../td[8]/a[text()='del']")).click();
		wdu.switchToAlertAccept(driver);
		
		hp.logout();
		driver.quit();
	}
}


















