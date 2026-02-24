package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author suman
 * 
 * Contains login page elements @ business lib like login
 * 
 */
public class LoginPage 
{
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="user_name")
	private WebElement username;

	@FindBy(name="user_password")
	private WebElement password;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	public WebElement getUsername() {
		return username;
	}
	
	public WebElement getPassword() {
		return password;
	}
	
	public WebElement getLoginButton() {
		return loginButton;
	}
	
	/**
	 * login to application based on username and password arguments
	 * @param username
	 * @param password
	 */
	public void login(String username,String password)
	{
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.loginButton.click();
	}
}
