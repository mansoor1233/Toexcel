package datadriven;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Datatabletoexcel {

	public static void main(String[] args) throws IOException, InterruptedException {
	

			System.setProperty("webdriver.chrome.driver", "Driver//chromedriver.exe");

			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			driver.get("https://datatables.net/examples/advanced_init/dt_events.html");

	int pagination=1, counter=1;
	while(pagination!=0)
	{
			String excel = "datafiles//toexcel.xlsx";
			XLUtility xlUtil = new XLUtility(excel);
			xlUtil.setCellData("Sheet1", 0, 0, "Name");
			xlUtil.setCellData("Sheet1", 0, 1, "Position");
			xlUtil.setCellData("Sheet1", 0, 2, "Office");
			xlUtil.setCellData("Sheet1", 0, 3, "Age");
			xlUtil.setCellData("Sheet1", 0, 4, "Startdate");
			xlUtil.setCellData("Sheet1", 0, 5, "Salary");

			List<WebElement> table = driver.findElements(By.xpath("//table[@id='example']//tbody//tr"));
			int rows = table.size();
			System.out.println(rows);
			for (int r = 1; r <= rows; r++) {
				String Name = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + r + "]//td[1]")).getText();
				String Position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + r + "]//td[2]")).getText();
				String Office = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + r + "]//td[3]")).getText();
				String Age = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + r + "]//td[4]")).getText();
				String Startdate = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + r + "]//td[5]")).getText();
				String Salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + r + "]//td[6]")).getText();


				xlUtil.setCellData("Sheet1", counter, 0, Name);
				xlUtil.setCellData("Sheet1", counter, 1, Position);
				xlUtil.setCellData("Sheet1", counter, 2, Office);
				xlUtil.setCellData("Sheet1", counter, 3, Age);
				xlUtil.setCellData("Sheet1", counter, 4, Startdate);
				xlUtil.setCellData("Sheet1", counter, 5, Salary);

				counter++;
				System.out.println("Name:" + Name+", Position: "+Position+", Office: "+Office+ ", Age: "+Age+ ", Startdate: "+Startdate+ ", Salary: "+Salary );
			}
			if (driver.findElement(By.xpath("//a[@id='example_next']")).getAttribute("tabindex").equals("-1")) 
			{
				break;
			} 
			else
			{
				WebElement next = driver.findElement(By.xpath("//a[@id='example_next']"));
				System.out.println(driver.findElement(By.xpath("//a[@id='example_next']")).getAttribute("tabindex"));
				next.click();
			}
			
			pagination++;
	}
			System.out.println("Webscrapping  is successfully done and fetch to excel file ");
//			driver. Close();

		}
	}


