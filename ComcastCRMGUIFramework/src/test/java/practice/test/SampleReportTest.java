package practice.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.demowebshop.listenerutility.ListenerImp;

@Listeners(com.comcast.demowebshop.listenerutility.ListenerImp.class)
public class SampleReportTest extends ListenerImp
{
	@Test
	public void createContactTest()
	{
		
	}
}