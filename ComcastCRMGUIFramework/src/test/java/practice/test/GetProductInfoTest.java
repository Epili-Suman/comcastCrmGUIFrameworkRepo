package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest
{
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName)
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.com");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		String price=driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../../div[@class='puisg-row']/div/div/div/div/div/a/span/span/span[@class='a-price-whole']")).getText();
		System.out.println(price);
		
		driver.close();
	}
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility eu=new ExcelUtility();
		int row = eu.getRowCount("Product");
		int col = eu.getColumnCount("Product");
		Object objArr[][]=new Object[row][col];
		
		for(int i=0;i<=row-1;i++)
		{
			for(int j=0;j<=col-1;j++)
			{
				objArr[i][j]=eu.getDataFromExcel("Product", i+1, j);
			}
		}	
		return objArr;
	}
}