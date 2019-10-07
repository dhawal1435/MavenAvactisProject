package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.google.common.io.Files;

public class Utilities 
{
// ***************** THIS PART IS FOR READING ADMIN LOGIN INFORMATION  **********************************************************************
	
	
	
	
	public static Object[][] generateDataArray(List<String> dataList, int rowCount, int colCount)
	{
		int k=0;
		String[][] xlsData = new String[rowCount][colCount];
		for(int i=0;i<rowCount;i++)
		{
			for(int j=0;j<colCount;j++)
			{
				xlsData[i][j] = dataList.get(k++);
			}
		}
		return xlsData;
	}
	
	@DataProvider
	public static Object[][] provideAdminLoginData() throws IOException
	{
		FileReader file = new FileReader("C:\\Users\\kejal\\eclipse-workspace\\MavenAvactisProject\\src\\test\\resources\\AdminData.csv");
		BufferedReader reader = new BufferedReader(file);
		List<String> records = new ArrayList<String>();
		String line;
		int rowCount = 0;
		int colCount = 0;
		
		while((line = reader.readLine()) !=null)
		{
			rowCount++;
			colCount=0;
			String [] tokens = line.split(",");
			for(int i=0; i<tokens.length; i++)
			{
				records.add(tokens[i]);
				colCount++;
			}
		}
		return generateDataArray(records, rowCount, colCount);
	}	
	
// ***************** THIS PART IS FOR READING CUSTORMER REGISTERATION DETAILS********************************************************
		
		public static Object[][] generateDataArray1(List<String> dataList, int rowCount, int colCount)
		{
			int k=0;
			String[][] xlsData = new String[rowCount][colCount];
			for(int i=0;i<rowCount;i++)
			{
				for(int j=0;j<colCount;j++)
				{
					xlsData[i][j] = dataList.get(k++);
				}
			}
			return xlsData;
		}
		
		@DataProvider
		public static Object[][] provideDataFromCsv1() throws IOException
		{
			return provideCustomerRegisterData();
		}
		@DataProvider
		public static Object[][] provideCustomerRegisterData() throws IOException
		{
			FileReader file = new FileReader("C:\\Users\\kejal\\eclipse-workspace\\MavenAvactisProject\\src\\test\\resources\\CustomerRegisterData.csv");
			BufferedReader reader = new BufferedReader(file);
			List<String> records = new ArrayList<String>();
			String line;
			int rowCount = 0;
			int colCount = 0;
			
			while((line = reader.readLine()) !=null)
			{
				rowCount++;
				colCount=0;
				String [] tokens = line.split(",");
				for(int i=0; i<tokens.length; i++)
				{
					records.add(tokens[i]);
					colCount++;
				}
			}
			return generateDataArray1(records, rowCount, colCount);			
	    }
		
// ***************** THIS PART IS FOR READING CUSTOMER SIGN IN INFORMATION *****************************************************************
	
		public static Object[][] generateDataArray2(List<String> dataList, int rowCount, int colCount)
		{
			int k=0;
			String[][] xlsData = new String[rowCount][colCount];
			for(int i=0;i<rowCount;i++)
			{
				for(int j=0;j<colCount;j++)
				{
					xlsData[i][j] = dataList.get(k++);
				}
			}
			return xlsData;
		}
		@DataProvider
		public static Object[][] provideDataFromCsv2() throws IOException
		{
			return provideCustomerSignInData();
		}
		@DataProvider
		public static Object[][] provideCustomerSignInData() throws IOException
		{
			FileReader file = new FileReader("C:\\Users\\kejal\\eclipse-workspace\\MavenAvactisProject\\src\\test\\resources\\CustomerSignInData.csv");
			BufferedReader reader = new BufferedReader(file);
			List<String> records = new ArrayList<String>();
			String line;
			int rowCount = 0;
			int colCount = 0;
			
			while((line = reader.readLine()) !=null)
			{
				rowCount++;
				colCount=0;
				String [] tokens = line.split(",");
				for(int i=0; i<tokens.length; i++)
				{
					records.add(tokens[i]);
					colCount++;
				}
			}
			return generateDataArray2(records, rowCount, colCount);
		}		

	
// ***************** THIS PART IS FOR READING PRODUCT DETAILS INFORMATION  **********************************************************************
		
		public static Object[][] generateDataArray5(List<String> dataList, int rowCount, int colCount)
		{
			int k=0;
			String[][] xlsData = new String[rowCount][colCount];
			for(int i=0;i<rowCount;i++)
			{
				for(int j=0;j<colCount;j++)
				{
					xlsData[i][j] = dataList.get(k++);
				}
			}
			return xlsData;
		}
		@DataProvider
		public static Object[][] provideDataFromCsv5() throws IOException
		{
			return provideProductData();
		}
		@DataProvider
		public static Object[][] provideProductData() throws IOException
		{
			FileReader file = new FileReader("C:\\Users\\kejal\\eclipse-workspace\\MavenAvactisProject\\src\\test\\resources\\SelectProductData.csv");
			BufferedReader reader = new BufferedReader(file);
			List<String> records = new ArrayList<String>();
			String line;
			int rowCount = 0;
			int colCount = 0;
			
			while((line = reader.readLine()) !=null)
			{
				rowCount++;
				colCount=0;
				String [] tokens = line.split(",");
				for(int i=0; i<tokens.length; i++)
				{
					records.add(tokens[i]);
					colCount++;
				}
			}
			return generateDataArray5(records, rowCount, colCount);
		}	
				
// ***************** THIS PART IS FOR READING GUEST USER DETAILS  **********************************************************************
		
		public static Object[][] generateDataArray6(List<String> dataList, int rowCount, int colCount)
		{
			int k=0;
			String[][] xlsData = new String[rowCount][colCount];
			for(int i=0;i<rowCount;i++)
			{
				for(int j=0;j<colCount;j++)
				{
					xlsData[i][j] = dataList.get(k++);
				}
			}
			return xlsData;
		}
//		@DataProvider
//		public static Object[][] provideDataFromCsv6() throws IOException
//		{
//			return provideGuestUserData();
//		}
		@DataProvider
		public static Object[][] provideGuestUserData() throws IOException
		{
				FileReader file = new FileReader("C:\\Users\\kejal\\eclipse-workspace\\MavenAvactisProject\\src\\test\\resources\\GuestUserData.csv");
				BufferedReader reader = new BufferedReader(file);
				List<String> records = new ArrayList<String>();
			String line;
			int rowCount = 0;
			int colCount = 0;
			
			while((line = reader.readLine()) !=null)
			{
				rowCount++;
				colCount=0;
				String [] tokens = line.split(",");
				for(int i=0; i<tokens.length; i++)
				{
					records.add(tokens[i]);
					colCount++;
				}
			}
			return generateDataArray6(records, rowCount, colCount);
		}		
		
// ***************** SCREENSHOT METHOD  ************************************************************************************************************************************

		public static void screenshot(String file, WebDriver driver) throws IOException 
		{
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-m-s");
	        Date date = new Date();
			TakesScreenshot capture = (TakesScreenshot) driver;
	        File scrFile = capture.getScreenshotAs(OutputType.FILE);
	        String filename = file;
	        Files.copy(scrFile, new File("D:\\"+filename+"-"+dateFormat.format(date)+".png"));		
			
		}

// ***************** END OF SCREENSHOT METHOD  ********************************************************************************************************************************
		
		
}          //END OF CLASS UTILITY


