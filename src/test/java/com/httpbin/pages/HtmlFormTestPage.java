package com.httpbin.pages;

import com.httpbin.bean.HtmlFormBean;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class HtmlFormTestPage extends WebDriverBaseTestPage<WebDriverTestPage> {

	@FindBy(locator = "btn.submitOrder.formpage")
	private QAFWebElement btnSubmitOrder;
	
	@Override
	protected void openPage(PageLocator arg0, Object... arg1) {
		
	}
	
	//This method fill the html form using form bean
	public void fillHtmlForm(){
		HtmlFormBean htmlFormBean = new HtmlFormBean();
		htmlFormBean.fillRandomData();
		htmlFormBean.fillUiElements();
		
	}
	
	//This method clicks on submit order
	public void clickSubmitOrder(){
		btnSubmitOrder.click();
	}

}
