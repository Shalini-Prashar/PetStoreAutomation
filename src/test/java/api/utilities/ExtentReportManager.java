package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener; //used for post actions.
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtentReportManager implements ITestListener
	
		{
			public ExtentSparkReporter sparkReporter; // responsible for UI part of the report
			public ExtentReports extent; // common data in the report: environment info., OS info, User info., project name, module name etc.
			public ExtentTest test; // creating the entries in the report
			
			String repName;
			
			
			public void onStart(ITestContext testContext)
			{
				
				// Get the current working directory (PetStoreAutomation)
		        String workingDir = System.getProperty("user.dir");
				
				
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Time stamp           
				repName = "Test-Report-" + timeStamp + ".html";
				
				   // Define the path for the existing "reports" folder
		        String reportsDir = workingDir + File.separator + "reports";
				
		        // Create the path for the report file
		        String reportPath = reportsDir + File.separator + repName;
		        sparkReporter = new ExtentSparkReporter(reportPath);
				
			        
			        
				// look and feel of the report
				//sparkReporter = new ExtentSparkReporter("." + File.separator + "reports" + repName); // specify location of the file
				
				sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of the report
				sparkReporter.config().setReportName("Pet StoreUsers API"); // name of the report
				sparkReporter.config().setTheme(Theme.DARK);
				
				
//				sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);

				
				// common data
				extent = new ExtentReports();
				extent.attachReporter(sparkReporter);
				extent.setSystemInfo("Application", "Pet StoreUsers API");
				extent.setSystemInfo("Operating System", System.getProperty("os.name")); 
				extent.setSystemInfo("User Name", System.getProperty("user.name")); 
				extent.setSystemInfo("Environment", "QA");
				extent.setSystemInfo("user", "pavan");
			}
		
			
			// creating entries in the report
			public void onTestSuccess(ITestResult result)
			{
				test = extent.createTest(result.getName());
				test.assignCategory(result.getMethod().getGroups());
				test.createNode(result.getName());
				test.log(Status.PASS, "Test Passed");
			}
			
			public void onTestFailure(ITestResult result)
			{
				test = extent.createTest(result.getName());
				test.createNode(result.getName());
				test.assignCategory(result.getMethod().getGroups());
				test.log(Status.FAIL, "Test Failed");
				test.log(Status.FAIL, result.getThrowable().getMessage());
			}
			
			public void onTestSkipped(ITestResult result)
			{
				test = extent.createTest(result.getName());
				test.createNode(result.getName());
				test.assignCategory(result.getMethod().getGroups());
				test.log(Status.SKIP, "Test Skipped");
				test.log(Status.SKIP, result.getThrowable().getMessage());
			}
			
				public void onFinish(ITestContext testContext)
				{
//					if (extent != null) {
//				        extent.flush();  // Ensure extent is flushed to write the report
//				    }
				    extent.flush();  // make everything ready in the report. If we don't call this method then, the report will not generate.
				}
		
			
}
	