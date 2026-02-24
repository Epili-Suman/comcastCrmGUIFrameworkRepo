package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.crm.generic.baseutility.BaseClass;

/**
 * test case for contact
 * @author suman
 * 
 */
public class SearchTest extends BaseClass
{
	/**
	 * scenario: login=> navigateContact => createContact() => verify
	 */
	@Test
	public void searchContact()
	{
		/* step 1:login to app */
		LoginPage lp = new LoginPage(driver);
		lp.login(null, null);
	}
}