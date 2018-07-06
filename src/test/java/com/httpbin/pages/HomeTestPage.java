package com.httpbin.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class HomeTestPage extends WebDriverBaseTestPage<WebDriverTestPage> {
	
	@FindBy(locator = "lnk.htmlform.homepage")
	private QAFWebElement lnkHtmlForm;
	

	@Override
	protected void openPage(PageLocator arg0, Object... arg1) {
		driver.get("/");
		driver.manage().window().maximize();
		
	}
	
	//This method clicks on html form link
	public void navigateToHtmlForm(){
		lnkHtmlForm.click();
	}

}
