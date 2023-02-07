package com.tcs;
	
	import java.io.FileInputStream;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DateUtil;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	public class ExcelReadDP {

		public static WebDriver driver = null;

		@BeforeClass
		public void browserlaunch() {
			driver = new ChromeDriver();
			driver.get("https://adactinhotelapp.com/");
			driver.manage().window().maximize();
		}

		@Test(dataProvider = "testdata")
		public void excelread(String username, String password) throws Exception {
			
			//System.out.println(username);
			//System.out.println(password);

			WebElement username1 = driver.findElement(By.id("username"));
			username1.sendKeys(username);
			WebElement password1 = driver.findElement(By.id("password"));
			password1.sendKeys(password);
			WebElement login = driver.findElement(By.id("login"));
			login.click();
			Thread.sleep(2000);
		}

		@DataProvider(name = "testdata")
		public Object[][] loginData() throws Exception {
			Object[][] value = ExcelData("Sheet1");
			return value;
		}

		public String[][] ExcelData(String sheet) throws Exception {

			FileInputStream fis = new FileInputStream("C:\\Users\\Hari\\eclipse-workspace\\Project_Testing\\src\\test\\resources\\Data\\Test Data.xlsx");
			Workbook wb = new XSSFWorkbook(fis);
			Sheet ws = wb.getSheet(sheet);
			Row row = ws.getRow(0);
			int RowNum = ws.getPhysicalNumberOfRows();
			int ColNum = row.getLastCellNum();

			String[][] Data = new String[RowNum][ColNum];

			for (int i = 0; i < RowNum; i++) {
				
				for (int j = 0; j < ColNum; j++) {
					row = ws.getRow(i);
					Cell cell = row.getCell(j);
					int cellType = cell.getCellType();
					if (cellType == 1) {
						
						Data[i][j] = cell.getStringCellValue();
						System.out.println(Data[i][j]);

					} else if (cellType == 0) {
						if (DateUtil.isCellDateFormatted(cell)) {
							Date dateCellValue = cell.getDateCellValue();
							SimpleDateFormat s = new SimpleDateFormat("MMM/dd/yyyy");
							Data[i][j] = s.format(dateCellValue);
							System.out.println(Data[i][j]);
						} else {
							double numericCellValue = cell.getNumericCellValue();
							long l = (long) numericCellValue;
							Data[i][j] = String.valueOf(l);
							System.out.println(Data[i][j]);
						}
					}
				}

			}
			return Data;
		}

		/*@AfterClass
		public void closebrowser() {
			driver.close();
		}*/

	}


