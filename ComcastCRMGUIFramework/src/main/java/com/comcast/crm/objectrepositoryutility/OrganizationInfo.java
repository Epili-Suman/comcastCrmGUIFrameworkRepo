package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfo 
{
	WebDriver driver;
	public OrganizationInfo(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;

	@FindBy(id="dtlview_Industry")
	private WebElement actIndustry;

	@FindBy(id="dtlview_Organization Name")
	private WebElement actOrg;

	public WebElement getActOrg() {
		return actOrg;
	}

	public WebElement getActIndustry() {
		return actIndustry;
	}

	public WebElement getHeaderMsg() 
	{
		return headerMsg;
	}
}

/*
 String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.equals(industry))
		{
			System.out.println(industry+" was verified");
		}
		else
		{			
			System.out.println(industry+" was verified");
		}
 */