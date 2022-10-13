package datadriven;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebtToExcel {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver", "Driver//chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.navigate().to("https://www.worldometers.info/world-population/population-by-country/");

		String path = "datafiles//Worldpopulation1.xlsx";

		XLUtility xlutil = new XLUtility(path);

		// Write headers in excel sheet
		xlutil.setCellData("Sheet1", 0, 0, "Country");
		xlutil.setCellData("Sheet1", 0, 1, "Population(2020)");
		xlutil.setCellData("Sheet1", 0, 2, "YearlyChange");
		xlutil.setCellData("Sheet1", 0, 3, "NetChange");
		xlutil.setCellData("Sheet1", 0, 4, "Density");
		xlutil.setCellData("Sheet1", 0, 5, "LandArea");
		xlutil.setCellData("Sheet1", 0, 6, "Migrants");
		xlutil.setCellData("Sheet1", 0, 7, "FertRate");
		xlutil.setCellData("Sheet1", 0, 8, "MedAge");
		xlutil.setCellData("Sheet1", 0, 9, "Urbanpop");
		xlutil.setCellData("Sheet1", 0, 10, "WorldShare");

		// capture table

		WebElement table = driver
				.findElement(By.xpath("//table[@class='table table-striped table-bordered dataTable no-footer']//tbody"));
		// capture table row

		int rows = table.findElements(By.xpath("tr")).size();

		for (int r = 1; r <= rows; r++) {

			// Read the datacolumn from table

			String Country = table.findElement(By.xpath("tr[" + r + "]//td[1]")).getText();
			String Population = table.findElement(By.xpath("tr[" + r + "]//td[2]")).getText();
			String YearlyChange = table.findElement(By.xpath("tr[" + r + "]//td[3]")).getText();
			String NetChange = table.findElement(By.xpath("tr[" + r + "]//td[4]")).getText();
			String Density = table.findElement(By.xpath("tr[" + r + "]//td[5]")).getText();
			String LandArea = table.findElement(By.xpath("tr[" + r + "]//td[6]")).getText();
			String Migrants = table.findElement(By.xpath("tr[" + r + "]//td[7]")).getText();
			String FertRate = table.findElement(By.xpath("tr[" + r + "]//td[8]")).getText();
			String MedAge = table.findElement(By.xpath("tr[" + r + "]//td[9]")).getText();
			String Urbanpop = table.findElement(By.xpath("tr[" + r + "]//td[10]")).getText();
			String WorldShare = table.findElement(By.xpath("tr[" + r + "]//td[11]")).getText();

			// Writing the data into excel

			xlutil.setCellData("Sheet1", r, 0, Country);
			xlutil.setCellData("Sheet1", r, 1, Population);
			xlutil.setCellData("Sheet1", r, 2, YearlyChange);
			xlutil.setCellData("Sheet1", r, 3, NetChange);
			xlutil.setCellData("Sheet1", r, 4, Density);
			xlutil.setCellData("Sheet1", r, 5, LandArea);
			xlutil.setCellData("Sheet1", r, 6, Migrants);
			xlutil.setCellData("Sheet1", r, 7, FertRate);
			xlutil.setCellData("Sheet1", r, 8, MedAge);
			xlutil.setCellData("Sheet1", r, 9, Urbanpop);
			xlutil.setCellData("Sheet1", r, 10, WorldShare);

		}
		System.out.println("Webscrapping  is successfully done and fetch to excel file ");
		driver.close();
	}

}
