package com.crm.comcast.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreateOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfo;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.crm.generic.baseutility.BaseClass;

@Listeners(com.comcast.demowebshop.listenerutility.ListenerImp.class)
public class CreateOrgTest extends BaseClass
{	
	@Test(groups = "SmokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException
	{
		JavaUtility ju = new JavaUtility();
		int random=ju.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		
		ExcelUtility eu = new ExcelUtility();
		String orgName = eu.getDataFromExcel("Org", 1, 2)+random;
		String address = eu.getDataFromExcel("Org", 1, 7);
		
		CreateOrganizationPage createOrg = new CreateOrganizationPage(driver);
		createOrg.createOrg(orgName, address);
		
		OrganizationInfo orgInfo = new OrganizationInfo(driver);
		String headerInfo = orgInfo.getHeaderMsg().getText();
		boolean status = headerInfo.contains(orgName);
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(status);
		
		String actOrgName = orgInfo.getActOrg().getText();
		Assert.assertEquals(actOrgName, orgName);
	}
	
	@Test(groups = "RegressionTest")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException
	{
		JavaUtility ju = new JavaUtility();
		int random=ju.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		
		ExcelUtility eu = new ExcelUtility();
		String orgName = eu.getDataFromExcel("Org", 1, 2)+random;
		String address = eu.getDataFromExcel("Org", 1, 7);
		String industry = eu.getDataFromExcel("Org", 1, 4);
		
		CreateOrganizationPage createOrg = new CreateOrganizationPage(driver);
		createOrg.createOrg(orgName, address, industry);
		OrganizationInfo oi = new OrganizationInfo(driver);
		String actIndustry = oi.getActIndustry().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actIndustry, industry);
	}
}