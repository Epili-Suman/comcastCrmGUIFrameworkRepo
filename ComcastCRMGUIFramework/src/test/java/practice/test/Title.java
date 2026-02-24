package practice.test;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.AddOrgToContact;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateContact;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.crm.generic.baseutility.BaseClass;

public class Title extends BaseClass
{
	@Test
	public void title() throws EncryptedDocumentException, IOException
	{
		JavaUtility ju = new JavaUtility();
		int random=ju.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		String parent=driver.getWindowHandle();
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();
		
		ExcelUtility eu=new ExcelUtility();
		String lastName=eu.getDataFromExcel("Contact", 4, 2)+random;
		
		CreateContact cc=new CreateContact(driver);
		cc.getLastname().sendKeys(lastName);
		cc.getAddOrgBtn().click();
		Set<String> ids=driver.getWindowHandles();
		System.out.println(ids);
		for(String id:ids)
		{
			driver.switchTo().window(id);
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());
		}
		AddOrgToContact aotc = new AddOrgToContact(driver);
		aotc.addOrg("aiii2", parent);
	}
}