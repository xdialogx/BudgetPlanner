package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import budgetPlanner.app.Loger;
import budgetPlanner.core.WicketHttpsSession;
import budgetPlanner.model.IncomePay;

public class Page2 extends WebPage {

	private static final long serialVersionUID = 1L;
	private boolean errorFlag = false;
	IncomePay incomePay = null;
	
	public Page2() {
		HomePage page2 = new HomePage("navomaticBorder");
		Form<Object> payForm = new Form<Object>("payForm");
		Integer userId = WicketHttpsSession.get().getAuthenticatedUserId();
		try {
			incomePay = new IncomePay(userId);
		} catch (Exception e) {
			new Loger().LogExceptions(e);
			errorFlag = true;
		} 

		final TextField<String> yourPayValueField = new TextField<String>("yourPayValue", Model.of(Double.toString(incomePay.getYourPay())));
		final TextField<String> partnersPayValueField = new TextField<String>("partnersPayValue", Model.of(Double.toString(incomePay.getPartnersPay())));
		final TextField<String> bonusesValueField = new TextField<String>("bonusesValue", Model.of(Double.toString(incomePay.getBonuses())));
		final TextField<String> familyPaymentsValueField = new TextField<String>("familyPaymentsValue", Model.of(Double.toString(incomePay.getFamilyPayments())));
		final TextField<String> otherValueField = new TextField<String>("otherValue", Model.of(Double.toString(incomePay.getOther())));
				
		payForm.add(new Label("yourPay" , "Enter your pay:"));
		payForm.add(new Label("partnersPay" , "Your partner's take-home pay:"));
		payForm.add(new Label("bonuses" , "Bonuses / overtime:"));
		payForm.add(new Label("familyPayments" , "Family benefit payments:"));
		payForm.add(new Label("other" , "Other:"));
		add(page2);
		page2.add(new Label("heder" , "Income pay:"));
		page2.add(payForm);
		payForm.add(yourPayValueField);
		payForm.add(partnersPayValueField);
		payForm.add(bonusesValueField);
		payForm.add(familyPaymentsValueField);
		payForm.add(otherValueField);
		
		Button incomePayButton = new Button("incomePayButton") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSubmit(){
				if((WicketHttpsSession.get().isLogedIn()) && (!errorFlag)) {
					final double yourPayValue = Double.parseDouble(yourPayValueField.getValue().replace(',','.'));
					final double partnersPayValue = Double.parseDouble(partnersPayValueField.getValue());
					final double bonusesValue = Double.parseDouble(bonusesValueField.getValue().replace(',','.'));
					final double familyPaymentsValue = Double.parseDouble(familyPaymentsValueField.getValue().replace(',','.'));
					final double otherValue = Double.parseDouble(otherValueField.getValue().replace(',','.'));
					
					incomePay.setYourPay(yourPayValue);
					incomePay.setPartnersPay(partnersPayValue);
					incomePay.setBonuses(bonusesValue);
					incomePay.setFamilyPayments(familyPaymentsValue);
					incomePay.setOther(otherValue);
					try {
						incomePay.save();
					} catch (Exception e) {
						new Loger().LogExceptions(e);
						errorFlag = true;
					}
				} else {
					setResponsePage(Error.class);
				}
			}
		};
		payForm.add(incomePayButton);				
	}
}
