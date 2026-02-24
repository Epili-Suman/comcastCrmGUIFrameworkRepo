package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationPage 
{
	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	@FindBy(name="accountname")
	private WebElement orgName;

	@FindBy(xpath="//input[contains(@value,'Save')and @name='button']")
	private WebElement saveBtn;

	@FindBy(name="bill_street")
	private WebElement billAddress;

	@FindBy(xpath="//input[@onclick='return copyAddressRight(EditView)']")
	private WebElement copyBtn;
	
	@FindBy(name="industry")
	private WebElement industry;	
	
	public WebElement getOrgName() 
	{
		return orgName;
	}

	public WebElement getBillAddress()
	{
		return billAddress;
	}
	
	public WebElement getCopyBtn() 
	{
		return copyBtn;
	}	

	public WebElement getSaveBtn()
	{
		return saveBtn;
	}
	
	public void createOrg(String name,String billAdrs)
	{
		orgName.sendKeys(name);
		billAddress.sendKeys(billAdrs);
		copyBtn.click();
		saveBtn.click();
	}

	public void createOrg(String name,String billAdrs,String industry)
	{
		orgName.sendKeys(name);
		billAddress.sendKeys(billAdrs);
		copyBtn.click();
		Select select=new Select(this.industry);
		select.selectByVisibleText(industry);
		saveBtn.click();
	}
}
















