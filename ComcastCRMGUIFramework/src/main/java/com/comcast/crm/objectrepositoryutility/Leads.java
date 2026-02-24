package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Leads
{
	@FindBy(xpath="Create Leads...")
	private WebElement element;

	public WebElement getElement() {
		return element;
	}
}