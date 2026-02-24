package com.crm.comcast.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfo;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateContact;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.crm.generic.baseutility.BaseClass;

import junit.framework.Assert;

public class CreateContactTest extends BaseClass
{
	@Test(groups = "SmokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException
	{
		JavaUtility ju = new JavaUtility();
		int random=ju.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();
		
		ExcelUtility eu=new ExcelUtility();
		String lastName=eu.getDataFromExcel("Contact", 4, 2)+random;
		
		CreateContact cc=new CreateContact(driver);
		cc.getLastname().sendKeys(lastName);
		cc.getSaveBtn().click();
		
		ContactInfo ci = new ContactInfo(driver);
		String actLastName=ci.getLastName().getText();
		
		Assert.assertEquals(actLastName, lastName);
	}
	
	@Test(groups = "RegressionTest")
	public void createContactWithDateTest() throws EncryptedDocumentException, IOException
	{
		JavaUtility ju = new JavaUtility();
		int random=ju.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();
		
		ExcelUtility eu=new ExcelUtility();
		String lastName=eu.getDataFromExcel("Contact", 4, 2)+random;
		
		CreateContact cc=new CreateContact(driver);
		cc.getLastname().sendKeys(lastName);
		
		String supportStart = ju.getSystemDate();
		String supportEnd = ju.getRequiredDate(30);
		WebElement startDate = cc.getStartDate();
		WebElement endDate = cc.getEndDate();
		
		startDate.clear();
		startDate.sendKeys(supportStart);
		endDate.clear();
		endDate.sendKeys(supportEnd);
		cc.getSaveBtn().click();
		
		ContactInfo ci = new ContactInfo(driver);
		String actStartDate = ci.getActStartDate().getText();
		String actEndDate = ci.getActEndDate().getText();
		Assert.assertEquals(actStartDate, supportStart);
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actEndDate, supportEnd);
		sa.assertAll();
	}	
}