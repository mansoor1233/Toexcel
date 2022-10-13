package datadriven;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class nopcommerce {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver", "Driver//chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.navigate().to("https://admin-demo.nopcommerce.com/Admin");

		driver.findElement(By.xpath("//input[@id='Email']")).clear();

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("admin@yourstore.com");

		driver.findElement(By.xpath("//input[@id='Password']")).clear();

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("admin");

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.manage().deleteAllCookies();

		String path = "datafiles//Orderrecords1.xlsx";

		XLUtility xlutil = new XLUtility(path);

		// Write headers in excel sheet
		xlutil.setCellData("Sheet1", 0, 0, "OrderStatus");
		xlutil.setCellData("Sheet1", 0, 1, "Today");
		xlutil.setCellData("Sheet1", 0, 2, "Thisweek");
		xlutil.setCellData("Sheet1", 0, 3, "ThisMonth");
		xlutil.setCellData("Sheet1", 0, 4, "Thisyear");
		xlutil.setCellData("Sheet1", 0, 5, "Alltime");

		// capture table

		WebElement table = driver
				.findElement(By.xpath("//div[@id='average-order-report-grid_wrapper']//div[@class='row']"));
		// capture table row

		int rows = table.findElements(By.xpath("//table//tbody//tr")).size();

		for (int r = 1; r <= rows; r++) {

			// Read the datacolumn from table

			String OrderStatus = table.findElement(By.xpath("//tr[" + r + "]//td[1]")).getText();
			String Today = table.findElement(By.xpath("//tr[" + r + "]//td[2]")).getText();
			String Thisweek = table.findElement(By.xpath("//tr[" + r + "]//td[3]")).getText();
			String ThisMonth = table.findElement(By.xpath("//tr[" + r + "]//td[4]")).getText();
			String Thisyear = table.findElement(By.xpath("//tr[" + r + "]//td[5]")).getText();
			String Alltime = table.findElement(By.xpath("//tr[" + r + "]//td[6]")).getText();
			System.out.println(OrderStatus + Today + Thisweek + ThisMonth + Thisyear + Alltime);
			
			// Writing the data into excel

			xlutil.setCellData("Sheet1", r, 0, OrderStatus);
			xlutil.setCellData("Sheet1", r, 1, Today);
			xlutil.setCellData("Sheet1", r, 2, Thisweek);
			xlutil.setCellData("Sheet1", r, 3, ThisMonth);
			xlutil.setCellData("Sheet1", r, 4, Thisyear);
			xlutil.setCellData("Sheet1", r, 5, Alltime);

		}
		System.out.println("Webscrapping  is successfully done and fetch to excel file ");
		driver.close();
	}

}
