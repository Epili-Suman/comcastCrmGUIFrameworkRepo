package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	public HomePage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText="Organizations")
	private WebElement orgLink;

	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;

	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOut;
	
	public WebElement getOrgLink() 
	{
		return orgLink;
	}

	public WebElement getContactLink() 
	{
		return contactLink;
	}

	public WebElement getCampaignLink()
	{
		return campaignLink;
	}

	public WebElement getMoreLink()
	{
		return moreLink;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOut() {
		return signOut;
	}
	
	public void navigateToCampaignPage()
	{
		Actions action = new Actions(driver);
		action.moveToElement(moreLink).perform();
		campaignLink.click();
	}
	
	public void logout()
	{
		Actions action = new Actions(driver);
		action.moveToElement(adminImg).perform();
		getSignOut().click();
	}
}











