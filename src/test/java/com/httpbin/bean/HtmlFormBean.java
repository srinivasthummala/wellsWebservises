package com.httpbin.bean;

import com.qmetry.qaf.automation.data.BaseFormDataBean;
import com.qmetry.qaf.automation.ui.annotations.UiElement;
import com.qmetry.qaf.automation.ui.annotations.UiElement.Type;
import com.qmetry.qaf.automation.util.Randomizer;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;

public class HtmlFormBean extends BaseFormDataBean {
	
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY,length=6)
	@UiElement(fieldLoc = "txt.custname.formpage", fieldType = Type.textbox, order=1)
	public String txtCustName;
	

	@Randomizer(type=RandomizerTypes.DIGITS_ONLY,length=9)
	@UiElement(fieldLoc = "txt.telephone.formpage" , fieldType = Type.textbox, order=2)
	public String txtTelePhone;
	
	@Randomizer(suffix="@gmail.com", length=6)
	@UiElement(fieldLoc = "txt.custemail.formpage", fieldType = Type.textbox, order=3)
	public String txtCustEmail;
	
	//@Randomizer(dataset= {"small", "medium", "large"})
	@UiElement(fieldLoc = "radio.lst.pizzasize.formpage", fieldType = Type.optionbox, order=4)
	public String radioPizzaSize;
	
	//@Randomizer(dataset= {"bacon", "cheese", "onion"})
	@UiElement(fieldLoc = "chbx.lst.pizzatoppings.formpage", fieldType = Type.checkbox, order=5)
	public String chbxPizzaToppings;

	@Randomizer(type=RandomizerTypes.DIGITS_ONLY,minval=11, maxval=21)
	//@UiElement(fieldLoc = "txt.prefdeliverytime.formpage", fieldType = Type.textbox, order=6)
	public String txtPrefDeliveryTime;
	
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY,length=6)
	@UiElement(fieldLoc = "txtarea.deliveryInstructions.formpage", fieldType = Type.textarea, order=7)
	public String txtDeliveryInstructions;

	public String getTxtCustName() {
		return txtCustName;
	}

	public String getTxtTelePhone() {
		return txtTelePhone;
	}

	public String getTxtCustEmail() {
		return txtCustEmail;
	}

	public String getTxtDeliveryInstructions() {
		return txtDeliveryInstructions;
	}

	public void setTxtCustName(String txtCustName) {
		this.txtCustName = txtCustName;
	}

	public void setTxtTelePhone(String txtTelePhone) {
		this.txtTelePhone = txtTelePhone;
	}

	public void setTxtCustEmail(String txtCustEmail) {
		this.txtCustEmail = txtCustEmail;
	}

	public void setTxtDeliveryInstructions(String txtDeliveryInstructions) {
		this.txtDeliveryInstructions = txtDeliveryInstructions;
	}
	
	public String getTxtPrefDeliveryTime() {
		return txtPrefDeliveryTime;
	}

	public void setTxtPrefDeliveryTime(String txtPrefDeliveryTime) {
		this.txtPrefDeliveryTime = txtPrefDeliveryTime;
	}
	
}
