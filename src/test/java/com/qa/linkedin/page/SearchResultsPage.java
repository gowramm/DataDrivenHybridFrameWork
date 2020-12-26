package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import com.qa.linkedin.base.TestBase;

public class SearchResultsPage extends TestBase {
	
private Logger log= Logger.getLogger(LinkedInLoggedInPage.class);
	
	public SearchResultsPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(@class,'search-results__total')]")
	private WebElement searchResultText;
	
	@FindBy(xpath="(//*[contains(text(),'Home')])[2]")
	private WebElement homeTab;
	
	public void validateSearchResultsTitle() {
		
		log.debug("wait for the search results page title");
		wait.until(ExpectedConditions.titleContains(" Search | LinkedIn"));
		Assert.assertTrue(driver.getPageSource().contains(driver.getTitle()));
		
	}
	
	public String getResultsCount() {
		validateSearchResultsTitle();
		log.debug("wait for the search results text");
		wait.until(ExpectedConditions.visibilityOf(searchResultText));
		log.debug("geeting the results text from webpage");
		String txt=searchResultText.getText();
		String[] str=txt.split(" ");
		log.debug("results count in string format is "+ str[1]);
		String finalstringcount=str[1].replace(",", " ").trim();
		//long count=Long.parseLong(finalstringcount);
		return finalstringcount;
		
	}
	
	public void clickHomeTab() {
		log.debug("wait for the home tab");
		log.debug("check whether hometab is clickable or not");
		wait.until(ExpectedConditions.elementToBeClickable(homeTab));
		homeTab.click();
		
	}

}
