package com.httpbin.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

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

	@QAFTestStep(description = "user navigates to httpbin homepage")
	public void userNavigatesToHttpbinHomepage() {
		HomeTestPage homeTestPage = new HomeTestPage();
		homeTestPage.launchPage(null);
	}

	@QAFTestStep(description = "user clicks html form")
	public void userClicksHtmlForm() {
		HomeTestPage homeTestPage = new HomeTestPage();
		homeTestPage.navigateToHtmlForm();
	}

	@QAFTestStep(description = "fill the html form and submit")
	public void userFillHtmlFormAndSubmit() {
		HtmlFormTestPage htmlFormTestPage = new HtmlFormTestPage();
		htmlFormTestPage.fillHtmlForm();
		htmlFormTestPage.clickSubmitOrder();
	}

	@QAFTestStep(description = "verifies form succesfull submition")
	public void userVerifiesFormSuccesfullSubmittion() {
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
		Validator.verifyThat(formObject.get("custname").toString(), Matchers.containsString(htmlFormBean.getTxtCustName()));
		Validator.verifyThat(formObject.get("custtel").toString(), Matchers.containsString(htmlFormBean.getTxtTelePhone()));
		Validator.verifyThat(formObject.get("custemail").toString(),  Matchers.containsString(htmlFormBean.getTxtCustEmail()));
		Validator.verifyThat(formObject.get("delivery").toString(),  Matchers.containsString(htmlFormBean.getTxtPrefDeliveryTime()));
		Validator.verifyThat(formObject.get("comments").toString(),  Matchers.containsString(htmlFormBean.getTxtDeliveryInstructions()));
	}

	
	@QAFTestStep(description = "user want to verify get method response")
	public void usereVrifiesGetMethodResponse() {
		
	}
	
	@QAFTestStep(description = "verifies the presence of headers and attributes in response body")
	public void userVerifiesPresenceOfHeadersInResponseBody() {
		JsonObject responseBody, headersObject;
		Response response;
		getWebResource("/get").type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		response = getResponse();
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();

		headersObject = responseBody.get("headers").getAsJsonObject();
		if (Validator.verifyThat(headersObject, Matchers.notNullValue())) {
			Validator.verifyThat(headersObject.get("Accept"), Matchers.notNullValue());
			Validator.verifyThat(headersObject.get("User-Agent"), Matchers.notNullValue());
			Validator.verifyThat(headersObject.get("Connection"), Matchers.notNullValue());
			Validator.verifyThat(headersObject.get("Host"), Matchers.notNullValue());
		}

		Validator.verifyThat(responseBody.get("origin"), Matchers.notNullValue());
		Validator.verifyThat(responseBody.get("url"), Matchers.notNullValue());
	}
	
	@QAFTestStep(description = "verifies the current time formates in response body of {url}")
	public void usereVrifiesGetMethodResponseOfUrl(String url) {
		JsonObject responseBody, currentTimeObject;
		JsonArray urlsArray;
		Response response;
		getClient().resource(url).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		response = getResponse();
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();

		currentTimeObject = responseBody.get("now").getAsJsonObject();
		if (Validator.verifyThat(currentTimeObject, Matchers.notNullValue())) {
			Validator.verifyThat(currentTimeObject.get("epoch"), Matchers.notNullValue());
			Validator.verifyThat(currentTimeObject.get("slang_date"), Matchers.notNullValue());
			Validator.verifyThat(currentTimeObject.get("slang_time"), Matchers.notNullValue());
			Validator.verifyThat(currentTimeObject.get("iso8601"), Matchers.notNullValue());
			Validator.verifyThat(currentTimeObject.get("rfc2822"), Matchers.notNullValue());
			Validator.verifyThat(currentTimeObject.get("rfc3339"), Matchers.notNullValue());
		}

		urlsArray = responseBody.get("urls").getAsJsonArray();
		Reporter.log(urlsArray.toString());
		if (urlsArray.size() > 0) {
			Validator.verifyThat(urlsArray, Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user want to verify http post method response")
	public void usereVrifiesHttpPostMethodResponse() {
		
	}
	
	@QAFTestStep(description = "verifies the presence of headers and attributes in post method response body")
	public void userVerifiesPresenceOfHeadersInPostMethodResponseBody() {
		JsonObject responseBody, headersObject;
		Response response;
		getWebResource("/post").type(MediaType.APPLICATION_JSON).post();
		response = getResponse();
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();

		headersObject = responseBody.get("headers").getAsJsonObject();
		if (Validator.verifyThat(headersObject, Matchers.notNullValue())) {
			Validator.verifyThat(headersObject.get("Accept"), Matchers.notNullValue());
			Validator.verifyThat(headersObject.get("User-Agent"), Matchers.notNullValue());
			Validator.verifyThat(headersObject.get("Connection"), Matchers.notNullValue());
			Validator.verifyThat(headersObject.get("Host"), Matchers.notNullValue());
			Validator.verifyThat(headersObject.get("Content-Type"), Matchers.notNullValue());
		}

		Validator.verifyThat(responseBody.get("origin"), Matchers.notNullValue());
		Validator.verifyThat(responseBody.get("url"), Matchers.notNullValue());
	}
	
	@QAFTestStep(description = "user want to verify http delete method response")
	public void usereVrifiesHttpDeleteMethodResponse() {
		
	}
	
	@QAFTestStep(description = "verifies the presence of headers and attributes in delete method response body")
	public void userVerifiesPresenceOfHeadersInDeleteMethodResponseBody() {
		JsonObject responseBody, headersObject;
		Response response;
		getWebResource("/delete").type(MediaType.APPLICATION_JSON).delete();
		response = getResponse();
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();

		headersObject = responseBody.get("headers").getAsJsonObject();
		if (Validator.verifyThat(headersObject, Matchers.notNullValue())) {
			Validator.verifyThat("Accept : ", headersObject.get("Accept"), Matchers.notNullValue());
			Validator.verifyThat("User-Agent : ", headersObject.get("User-Agent"), Matchers.notNullValue());
			Validator.verifyThat("Connection : ", headersObject.get("Connection"), Matchers.notNullValue());
			Validator.verifyThat("Host : ", headersObject.get("Host"), Matchers.notNullValue());
			Validator.verifyThat("Content-Type : ", headersObject.get("Content-Type"), Matchers.notNullValue());
		}

		Validator.verifyThat("origin : ", responseBody.get("origin"), Matchers.notNullValue());
		Validator.verifyThat("url : ", responseBody.get("url"), Matchers.notNullValue());
	}

	@QAFTestStep(description = "user want to verify http put method response")
	public void usereVrifiesHttpPutMethodResponse() {
		
	}
	
	@QAFTestStep(description = "verifies the presence of headers and attributes in put method response body")
	public void userVerifiesPresenceOfHeadersInPutMethodResponseBody() {
		JsonObject responseBody, headersObject;
		Response response;
		getWebResource("/put").type(MediaType.APPLICATION_JSON).put();
		response = getResponse();
		Validator.verifyThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();

		headersObject = responseBody.get("headers").getAsJsonObject();
		if (Validator.verifyThat(headersObject, Matchers.notNullValue())) {
			Validator.verifyThat("Accept : ", headersObject.get("Accept"), Matchers.notNullValue());
			Validator.verifyThat("User-Agent : ", headersObject.get("User-Agent"), Matchers.notNullValue());
			Validator.verifyThat("Connection : ", headersObject.get("Connection"), Matchers.notNullValue());
			Validator.verifyThat("Host : ", headersObject.get("Host"), Matchers.notNullValue());
			Validator.verifyThat("Content-Type : ", headersObject.get("Content-Type"), Matchers.notNullValue());
		}

		Validator.verifyThat("data : ", responseBody.get("data"), Matchers.notNullValue());
		Validator.verifyThat("form : ", responseBody.get("form"), Matchers.notNullValue());
		Validator.verifyThat("origin : ", responseBody.get("origin"), Matchers.notNullValue());
		Validator.verifyThat("url : ", responseBody.get("url"), Matchers.notNullValue());
	}
	
}
