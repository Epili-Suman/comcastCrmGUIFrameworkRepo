package com.crm.generic.baseutility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass
{
	public static WebDriver driver;
	public static WebDriver sdriver;
	public FileUtility fu =new FileUtility();
	public LoginPage lp = new LoginPage(driver);
	public HomePage hp=new HomePage(driver);
	@BeforeSuite(groups = {"RegressionTest","SmokeTest"})
	public void BS()
	{
		System.out.println("Connect to DB, report config");
	}
	
	@BeforeClass(groups = {"RegressionTest","SmokeTest"})
	public void BC()
	{
		driver =new ChromeDriver();
		sdriver=driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@BeforeMethod(groups = {"RegressionTest","SmokeTest"})
	public void BM() throws IOException
	{
		String url = fu.getDataFromPropertiesFile("url");
		String username=fu.getDataFromPropertiesFile("username");
		String password=fu.getDataFromPropertiesFile("password");
		
		driver.get(url);
		lp.login(username, password);
	}
	
	@AfterMethod(groups = {"RegressionTest","SmokeTest"})
	public void AM()
	{
		hp.logout();
	}
	
	@AfterClass(groups = {"RegressionTest","SmokeTest"})
	public void AC()
	{
		driver.close();
	}
	
	@AfterSuite(groups = {"RegressionTest","SmokeTest"})
	public void AS()
	{
		
	}
}
