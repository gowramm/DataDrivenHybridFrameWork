package com.qa.linkedin.test;

import org.testng.annotations.Test;


import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedInHomePage;
import com.qa.linkedin.page.LinkedInLoggedInPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDDTest extends TestBase {
	
	private Logger log=Logger.getLogger(SearchDDTest.class);
	private LinkedInHomePage lhpg = null;
	private LinkedInLoggedInPage llpg = null;
	private SearchResultsPage srpg = null;
	private String excelpath= System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\linkedintestdata.xlsx";

	@BeforeClass
	public void beforeClass() throws IOException {
		
		log.debug("create object to classes");
		
		lhpg=new LinkedInHomePage();
		llpg=new LinkedInLoggedInPage();
		srpg=new SearchResultsPage(); 
		
		log.debug("call and verify the homepage title");
		lhpg.verifyLinkedHomePageTitle();
		log.debug("verify the linkedin home page logo");
		lhpg.verifyLinkedinLogo();
		log.debug("click on signin link on home page");
		lhpg.clickSignInLink();
		lhpg.verifyWelcomeBackHeaderText();
		lhpg.verifyLinkedInLoginPageTitle();
		log.debug("perform the login only once");
		//do login method returning LinkedinLoggedinPage object
		llpg = lhpg.doLogin(readProperty("email"), readProperty("pwd"));
		llpg.verifyProfileRailcard();
			
	}

	@Test(dataProvider="testData")
	public void searchPeopleTest(String keyword) throws InterruptedException {
		
		log.debug("started executing the searchPeopleTest ");
		
		//dopeoplesearch method returning the SearchResultPage class object
		srpg= llpg.doPeopleSearch(keyword);
		log.debug("fetch the result count for"+keyword);
		
		//getResultsCount method returns long
		String count= srpg.getResultsCount();
		log.debug("the results count for"+keyword+ " is: "+ count);
		
		log.debug("click on home icon");
		srpg.clickHomeTab();
	}
	
	@DataProvider
	public Object[][] testData() throws InvalidFormatException, IOException {
		
		//create 2d object array, read the data from excelsheet using ExcelUtils class method
		Object[][] data=new ExcelUtils().getTestData(excelpath, "searchtdata");
		return data;
		
	}
	
	@AfterTest
	public void verifyHomePage() {
		log.debug("Aftertest");
	}

	@AfterClass
	public void afterClass() {
		log.debug("do logout");
		
	}

}
