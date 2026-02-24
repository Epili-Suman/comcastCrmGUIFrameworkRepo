package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage
{
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchField;
	
	@FindBy(name="search_field")
	private WebElement dropDown;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	

	public WebElement getSearchBtn() 
	{
		return searchBtn;
	}

	public WebElement getSearchField() 
	{
		return searchField;
	}

	public WebElement getDropDown() 
	{
		return dropDown;
	}

	public WebElement getCreateOrgBtn() 
	{
		return createOrgBtn;
	}
}