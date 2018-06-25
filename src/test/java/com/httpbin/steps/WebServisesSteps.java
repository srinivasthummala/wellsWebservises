package com.httpbin.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.httpbin.bean.HtmlFormBean;
import com.httpbin.pages.HomeTestPage;
import com.httpbin.pages.HtmlFormTestPage;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
import com.qmetry.qaf.automation.ws.rest.RestWSTestCase;
import com.sun.jersey.api.client.ClientResponse;

public class WebServisesSteps extends RestWSTestCase {

	
	@QAFTestStep(description="user navigates to httpbin homepage")
	public void userNavigatesToHttpbinHomepage(){
		HomeTestPage homeTestPage = new HomeTestPage();
		homeTestPage.launchPage(null);
	}
	
	@QAFTestStep(description="user clicks html form")
	public void userClicksHtmlForm(){
		HomeTestPage homeTestPage = new HomeTestPage();
		homeTestPage.navigateToHtmlForm();
	}
	
	@QAFTestStep(description="fill the html form and submit")
	public void userFillHtmlFormAndSubmit(){
		HtmlFormTestPage htmlFormTestPage = new HtmlFormTestPage();
		htmlFormTestPage.fillHtmlForm();
		htmlFormTestPage.clickSubmitOrder();
	}
	
	
	//@QAFTestStep(description="verifies form succesfull submittion")
	public void userVerifiesFormSuccesfullSubmittion(){
		HtmlFormBean htmlFormBean = new HtmlFormBean();
		htmlFormBean.fillRandomData();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("custname", htmlFormBean.getTxtCustName());
		jsonObject.put("custtel", htmlFormBean.getTxtTelePhone());
		jsonObject.put("custemail", htmlFormBean.getTxtCustEmail());
		jsonObject.put("delivery", htmlFormBean.getTxtPrefDeliveryTime());
		jsonObject.put("comments", htmlFormBean.getTxtDeliveryInstructions());
	
		getWebResource("/post").type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = getResponse();
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		Reporter.log(responseBody.toString());
		JsonObject formObject = responseBody.get("json").getAsJsonObject();
		Reporter.log(formObject.toString());
		Validator.verifyThat(formObject, Matchers.notNullValue());
		Validator.verifyThat(formObject.get("custname"), Matchers.notNullValue());
		Validator.verifyThat(formObject.get("custtel"), Matchers.notNullValue());
		Validator.verifyThat(formObject.get("custemail"), Matchers.notNullValue());
		Validator.verifyThat(formObject.get("delivery"), Matchers.notNullValue());
		Validator.verifyThat(formObject.get("comments"), Matchers.notNullValue());
	}
	
	
	
	@Test
	public void test(){
		getClient().resource("https://now.httpbin.org/").type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		Response response = getResponse();
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		System.out.println(responseBody.toString());
		JsonArray urlsArray = responseBody.get("urls").getAsJsonArray();
		Reporter.log(urlsArray.toString());
		Validator.verifyThat(urlsArray, Matchers.notNullValue());
//		Validator.verifyThat(formObject.get("custname"), Matchers.notNullValue());
//		Validator.verifyThat(formObject.get("custtel"), Matchers.notNullValue());
//		Validator.verifyThat(formObject.get("custemail"), Matchers.notNullValue());
//		Validator.verifyThat(formObject.get("delivery"), Matchers.notNullValue());
//		Validator.verifyThat(formObject.get("comments"), Matchers.notNullValue());
//		
	}
}
