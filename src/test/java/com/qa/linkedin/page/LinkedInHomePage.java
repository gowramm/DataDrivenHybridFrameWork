package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import com.qa.linkedin.base.TestBase;

public class LinkedInHomePage extends TestBase {
	
	private Logger log=Logger.getLogger(LinkedInHomePage.class);
	
	private String homePageTitle="LinkedIn: Log In or Sign Up";
	private String signInPageTitle="LinkedIn Login, Sign in | LinkedIn";
	
	public LinkedInHomePage() {
		
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath="//*[@class='nav__logo-link']")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	@FindBy(css="h1.header__content__heading")
	private WebElement welcomeBackHeading;
	
	@FindBy(id="username")
	private WebElement emailEditBox;
	
	@FindBy(xpath="//input[@id='password']")
	private WebElement pwdEditBox;
	
	@FindBy(xpath="//button[text()='Sign in']")
	private WebElement signInBtn;
	

	public void verifyLinkedHomePageTitle() {
		log.debug("wait for the linkedin home page title");
		wait.until(ExpectedConditions.titleContains(homePageTitle));
		Assert.assertEquals(driver.getTitle(), homePageTitle);
	}
	
	public void verifyLinkedinLogo() {
		log.debug("wait and verify the Linkedin Logo in HomePage");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(),"LinkedinLogo element is not present");
	}
	
	public void clickSignInLink() {
		if(signInLink.isDisplayed()&&signInLink.isEnabled()) {
		log.debug("click on signin link");
		wait.until(ExpectedConditions.elementToBeClickable(signInLink));
		signInLink.click();
		}
	
	}
	
	public void verifyWelcomeBackHeaderText() {
		log.debug("wait and verify welcomeback headertext");
		wait.until(ExpectedConditions.visibilityOf(welcomeBackHeading));
		Assert.assertTrue(welcomeBackHeading.isDisplayed(),"welcomeBackHeading element is not present");
	}
	
	public void verifyLinkedInLoginPageTitle() {
		log.debug("wait and verify the linkedin login page title");
		wait.until(ExpectedConditions.titleContains(signInPageTitle));
		Assert.assertEquals(driver.getTitle(), signInPageTitle);
	}
	
	public void clickSignInBtn() {
		if(signInBtn.isDisplayed()&&signInBtn.isEnabled()) {
		log.debug("click on signin button");
		wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
		signInBtn.click();
		}
	
	}
	
	public LinkedInLoggedInPage doLogin(String email, String pwd) {
		log.debug("Started executing doLogin method");
		log.debug("clear the content in the email editbox");
		emailEditBox.clear();
		log.debug("type the email id in emaileditbox" +email);
		emailEditBox.sendKeys(email);
		
		log.debug("clear the content in the password editbox");
		pwdEditBox.clear();
		log.debug("type the email id in pwdeditbox" +pwd);
		pwdEditBox.sendKeys(pwd);
		
		log.debug("click on signin button");
		clickSignInBtn();
		
		//when u click on signin button it'll navigate to new page that means it is returning a new page 
		return new LinkedInLoggedInPage();
	}
	
	
	
}
