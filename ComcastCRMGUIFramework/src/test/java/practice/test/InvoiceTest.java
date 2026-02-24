 package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

//class InvoiceTest extends BaseClass
class InvoiceTest extends BaseClass
{
	@Test
	public void invoice()
	{
		System.out.println("1");
		System.out.println("2");
		Assert.assertEquals("a","a");
		System.out.println("3");
		System.out.println("4");
		System.out.println("5");
	}
	
	@Test(retryAnalyzer = com.comcast.demowebshop.listenerutility.RetryListenerImp.class)
	public void activateSim()
	{
		System.out.println("1");
		System.out.println("2");
		Assert.assertEquals("", "");
		System.out.println("3");
		System.out.println("4");
		System.out.println("5");
	}
}