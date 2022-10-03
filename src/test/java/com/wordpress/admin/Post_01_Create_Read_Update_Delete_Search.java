package com.wordpress.admin;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserSearchPostPO;



public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {
	//Declare
	private WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO admninDashboardPage;
	AdminPostSearchPO adminPostSearchPage;
	AdminPostAddNewPO adminPostAddNewPage;
	UserHomePO userHomePage;
	UserPostDetailPO userPostDetailPage;
	UserSearchPostPO userSearchPostPage;
	
	String searchPostUrl ;
	int randomNumber =  generateFakeNumber();
	String postTitle ="Live Coding Title " + randomNumber;
	String postBody ="Live Coding Body " + randomNumber;
	String editPostitle ="Edit Title" + randomNumber;
	String editPostBody ="Edit Body"+ randomNumber;
	String authorName ="automationfc";
	String adminUsername ="automationfc";
	String adminPassword ="automationfc";
	String adminUrl, endUserUrl;
	String currentDay= getCurrentDay();

	@Parameters({"browser", "urlAdmin","urlUser"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-Condition - Step 01: Open browser and Admin site");
		this.adminUrl = adminUrl;
		this.endUserUrl =endUserUrl;
		driver = getBrowserDriver(browserName,this.adminUrl);		
	//	adminLoginPage = new AdminLoginPO(driver); // Cách thông thường
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-Condition - Step 02: Enter to Username textbox with value : " + adminUsername);
		adminLoginPage.enterToUserNameTextbox(adminUsername);
		
		log.info("Pre-Condition - Step 03: Enter to Password textbox with value :" + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		
		log.info("Pre-Condition - Step 04:	Click to Login button");
		admninDashboardPage =	adminLoginPage.clickTologinButton();
		
	//	admninDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);  -> dua vao clicklogin, roi dung gián lại value, ko có việc khởi tạo trên TC nữa
		
		
		
		
		
	}

	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01:Click to 'Post' menu link");		
		adminPostSearchPage =	admninDashboardPage.clickToPostMenuLink();
		
	
		log.info("Create_Post - Step 02: Get 'Search Posts' page Url");
		searchPostUrl = adminPostSearchPage.getPageUrl(driver) ;  // lấy url của page hiện tại
		
		
		log.info("Create_Post - Step 03:Click to 'Add New' button ");		
		adminPostAddNewPage =	adminPostSearchPage.clickToAddNewButton();
		
		
				
		log.info("Create_Post - Step 04: Enter to post title ");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);
		
		log.info("Create_Post - Step 05: Enter to body ");
		adminPostAddNewPage.enterToAddNewPostBody(postBody);
		
		log.info("Create_Post - Step 06: Click to 'Publish ' button ");
		adminPostAddNewPage.clickToPublishOrUpdateButton();
		
		log.info("Create_Post - Step 07: Click to 'Pre-publish ' button ");
		adminPostAddNewPage.clickToPreBulishButton();
		
		log.info("Create_Post - Step 08: Verify 'Post published' message is displayed ");
		verifyTrue(adminPostAddNewPage.isPostPubishMessageDisplayed("Post published."));
		
		
		
	}
	

	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page ");
		// Open searchPostUrl
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 02: Enter to Search textbox ");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Search_Post - Step 03: Click to 'Search Posts' button ");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Search_Post - Step 04: Verify Search table contains ' " + postTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title",postTitle));
		
		log.info("Search_Post - Step 05: Verify Search table contains ' " + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author",authorName));
		
		log.info("Search_Post - Step 06: Open User site ");
		userHomePage = adminPostSearchPage.openEndUserSite(driver,this.endUserUrl); // sau này trang admin bất kì vị trí nào cũng có thể mở dc endUser ra 
		
		log.info("Search_Post - Step 07: Verify Post Infor displayed at Home Page ");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postTitle,postBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(postTitle,authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(postTitle,currentDay));
		
		log.info("Search_Post - Step 08: Click to Post Title ");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);
		
		log.info("Search_Post - Step 09: Verify Post infor displayed at Post detail Page ");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postTitle,postBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(postTitle,authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostCurrentDay(postTitle,currentDay));
	}
	

	
	@Test
	public void Post_03_Edit_Post() {
		// đang ở trang User có thể truy cập dc trang ở Admin (vì đã viết ở BasePage)
		log.info("Edit_Post - Step 01: Open Admin site ");
		admninDashboardPage =	userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Edit_Post - Step 02:Click to 'Post' menu link");		
		adminPostSearchPage =	admninDashboardPage.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to Search textbox ");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Edit_Post - Step 04: Click to 'Search Posts' button ");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Edit_Post - Step 05: Click to Post title detail link and navigate to Edit Post page ");
		// Khi click vào PostTitleLink thì Add new vs Edit lúc này là 1 page nên k tạo thành 2 cái
		adminPostAddNewPage  =	adminPostSearchPage.clickToPostTitleLink(postTitle);
		
		log.info("Edit_Post - Step 06: Edit to post title ");
		adminPostAddNewPage.enterToAddNewPostTitle(editPostitle);
		
		log.info("Edit_Post - Step 07: Edit to body ");
		adminPostAddNewPage.enterToEditPostBody(editPostBody);
		
		log.info("Edit_Post - Step 08: Click to 'Update ' button ");
		adminPostAddNewPage.clickToPublishOrUpdateButton();
		
		log.info("Create_Post - Step 09: Verify 'Post upadate' message is displayed ");
		verifyTrue(adminPostAddNewPage.isPostPubishMessageDisplayed("Post updated."));
		
		
		log.info("Edit_Post - Step 10: Open 'Search Post' page ");
		// Open searchPostUrl
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Edit_Post - Step 11: Enter to Search textbox ");
		adminPostSearchPage.enterToSearchTextbox(editPostitle);
		
		log.info("Edit_Post - Step 12: Click to 'Search Posts' button ");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Edit_Post - Step 13: Verify Search table contains ' " + editPostitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title",editPostitle));
		
		log.info("Edit_Post - Step 14: Verify Search table contains ' " + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author",authorName));
		
		log.info("Edit_Post - Step 15: Open User site ");
		userHomePage = adminPostSearchPage.openEndUserSite(driver,this.endUserUrl); // sau này trang admin bất kì vị trí nào cũng có thể mở dc endUser ra 
		
		log.info("Edit_Post - Step 16: Verify Post Infor displayed at Home Page ");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(editPostitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(editPostitle,editPostBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(editPostitle,authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(editPostitle,currentDay));
		
		log.info("Edit_Post - Step 17: Click to Post Title ");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostitle);
		
		log.info("Edit_Post - Step 18: Verify Post infor displayed at Post detail Page ");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(editPostitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(editPostitle,editPostBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(editPostitle,authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostCurrentDay(editPostitle,currentDay));
		
	}
	
	@Test
	public void Post_04_Delete_Post() {
		log.info("Delete_Post - Step 01:Open Admin Site");
		userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Delete_Post - Step 02:Click to 'Post' menu link");		
		adminPostSearchPage =	admninDashboardPage.clickToPostMenuLink();
		
		log.info("Delete_Post - Step 03: Enter to Search textbox ");
		adminPostSearchPage.enterToSearchTextbox(editPostitle);
		
		log.info("Delete_Post - Step 04: Click to 'Search Posts' button ");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Delete_Post - Step 05: Select Post detail checkbox ");
		adminPostSearchPage.selectPostCheckboxByTitle(editPostitle);
		
		log.info("Delete_Post - Step 06: Select 'Move to Trash'item  in dropdown");
		adminPostSearchPage.selectTextItemInActionDropDown("Move to Trash");
		
		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearchPage.clickApplybutton();
		
		log.info("Delete_Post - Step 08: Verify '1 post moved to the Trash.' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash."));
		
		log.info("Delete_Post - Step 09: Enter to Search textbox ");
		adminPostSearchPage.enterToSearchTextbox(editPostitle);
		
		log.info("Delete_Post - Step 10: Click to 'Search Posts' button ");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Delete_Post - Step 11: Verify 'No posts found' message is displayed");
		verifyTrue(adminPostSearchPage.isNotPostFoundMessageDisplayed("No posts found."));
		
		log.info("Delete_Post - Step 12: Open User site ");
		userHomePage = adminPostSearchPage.openEndUserSite(driver,this.endUserUrl); // sau này trang admin bất kì vị trí nào cũng có thể mở dc endUser ra 
		
		
		log.info("Delete_Post - Step 13: Verify Postitle undisplayed at Home Page ");
		verifyTrue(userHomePage.isPostInforUndisplayedWithPostTitle(editPostitle));
		
		log.info("Delete_Post - Step 14: Enter to search Textbox at Home Page ");	
		userHomePage.enterToSearchTextbox(editPostitle);
		
		log.info("Delete_Post - Step 15: Click search button ");	
		userSearchPostPage	 = userHomePage.clickToSearchButton();
		
		log.info("Delete_Post - Step 16: Verify 'Nothing Found' message is displayed");
		verifyTrue(userSearchPostPage.isNothingFoundMessaeDisplayed("Nothing Found"));
		
	}
	
	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		//closeBrowserAndDriver();
	}






}
