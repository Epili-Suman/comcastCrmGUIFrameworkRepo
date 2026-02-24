package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Leads
{
	public Leads(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="Create Leads...")
	private WebElement element;

	public WebElement getElement() 
	{
		return element;
	}

	public WebElement getElement2() {
		return element2;
	}

	@FindBy(xpath="Create Leads...")
	private WebElement element2;
}