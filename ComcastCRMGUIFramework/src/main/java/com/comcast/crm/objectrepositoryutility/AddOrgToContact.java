package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddOrgToContact
{
	WebDriver driver;
	public AddOrgToContact(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="search_txt")
	private WebElement seatchField;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement seatchBtn;
	
	public WebElement getSeatchField() {
		return seatchField;
	}
	
	public WebElement getSeatchBtn() {
		return seatchBtn;
	}

	public void addOrg(String orgName,String parent)
	{
		seatchField.sendKeys(orgName);
		seatchBtn.click();
		driver.findElement(By.linkText(orgName)).click();
		driver.switchTo().window(parent);
	}
}
