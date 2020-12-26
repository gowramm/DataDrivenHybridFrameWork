package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import com.qa.linkedin.base.TestBase;

public class LinkedInLoggedInPage extends TestBase {
	
	private Logger log= Logger.getLogger(LinkedInLoggedInPage.class);
	
	public LinkedInLoggedInPage() {
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[contains(@class,'feed-identity-module__actor-meta profile-rail-card__actor-meta break-words')]")
	private WebElement railCard;
	
	@FindBy(xpath="//*[contains(@class,'search-global-typeahead__input always-show-placeholder')]")
	private WebElement searchEditBox;
	
	@FindBy(xpath="//*[contains(@class,'global-nav__me-photo ember-view')]")
	private WebElement profileIcon;
	
	@FindBy(xpath="//*[contains(@id,'ember42')]")
	private WebElement signOut;
	
	public void verifyProfileRailcard() {
		log.debug("wait and verify the profilerailcard");
		wait.until(ExpectedConditions.visibilityOf(profileIcon));
		Assert.assertTrue(profileIcon.isDisplayed(),"profileRailCard element is not present");
		
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("started executing dopeoplesearch"+ keyword);
		log.debug("clear the content in search editbox");
		searchEditBox.clear();
		log.debug("Type the keyword "+ keyword);
		searchEditBox.sendKeys(keyword);
		searchEditBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		return new SearchResultsPage();
	}
	
	public void doLogout() {
		log.debug("wait for the visibility of profile image icon");
		wait.until(ExpectedConditions.visibilityOf(profileIcon));
		profileIcon.click();
		
		log.debug("wait for the visibility of signOut icon");
		wait.until(ExpectedConditions.visibilityOf(signOut));
		signOut.click();
	}
	
	
}
