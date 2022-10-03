package com.jquery;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGenerator;

public class Level_11_Upload_Files extends BaseTest {
	String csharpFileName = "csharp.png";
	String javaFileName = "java.jpg";
	String pythonFileName = "python.png";
	String rubyFileName = "ruby.png";

	String[] multipleFileName = { csharpFileName, javaFileName, pythonFileName, rubyFileName};

	private WebDriver driver;
	private HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGenerator.getHomePage(driver);

	}

	@Test
	public void Upload_01_One_File_Per_Time() {
		// Step 01 - Load single file
		homePage.uploadMultipleFiles(driver, csharpFileName);
		// Step 02 - Verify single file load success
		Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
		// Step 03 - Click to start button
		homePage.clickToStartButton();
		// Step 04: Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(csharpFileName));
		// Step 05: Verify single file image uploaded success
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(csharpFileName));

	}

	@Test
	public void Upload_02_Multiple_File_Per_Time() {
		homePage.refreshCurrentPage(driver);
		
		// Step 01 - Load multiple file
		homePage.uploadMultipleFiles(driver, multipleFileName);

		// Step 02 - Verify multiple file load success
		// "csharpFileName", "javaFileName", "pythonFileName", "rubyFileName"
		Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(rubyFileName));

		// Step 03 - Click to start button
		homePage.clickToStartButton();

		// Step 04: Verify multiple file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(rubyFileName));

		// Step 05: Verify multiple file image uploaded success
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(rubyFileName));
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();

	}

}
