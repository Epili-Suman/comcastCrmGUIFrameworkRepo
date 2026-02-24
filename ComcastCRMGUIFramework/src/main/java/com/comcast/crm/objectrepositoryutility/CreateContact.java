package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContact 
{
	public CreateContact(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastname;
	
	@FindBy(name="support_start_date")
	private WebElement startDate;
	
	@FindBy(id="jscal_field_support_end_date")
	private WebElement endDate;
	
	@FindBy(xpath="//input[contains(@value,'Save')and @name='button']")
	private WebElement saveBtn;

	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement addOrgBtn;
	
	public WebElement getAddOrgBtn() {
		return addOrgBtn;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getLastname() {
		return lastname;
	}

}