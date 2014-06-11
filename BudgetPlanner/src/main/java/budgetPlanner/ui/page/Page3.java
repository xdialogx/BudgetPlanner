package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import budgetPlanner.app.Loger;
import budgetPlanner.core.WicketHttpsSession;
import budgetPlanner.model.Expenses;

public class Page3 extends WebPage {

	private static final long serialVersionUID = 1L;
	private boolean errorFlag = false;
	Expenses myExpenses = null;

	public Page3() {
		HomePage page3 = new HomePage("navomaticBorder");
		Form<Object> expensesForm = new Form<Object>("expensesForm");
		Integer userId = WicketHttpsSession.get().getAuthenticatedUserId();
		try {
			myExpenses = new Expenses(userId);
		} catch (Exception e) {
			new Loger().LogExceptions(e);
			errorFlag = true;
		}
		
		final TextField<String> financialCommitmentsFieldValue = new TextField<String>("financialCommitmentsField", Model.of(Double.toString(myExpenses.getFinancialCommitments())));	
		final TextField<String> homeExpensesFieldValue = new TextField<String>("homeExpensesField", Model.of(Double.toString(myExpenses.getHomeExpenses())));		
		final TextField<String> utilitiesExpensesFieldValue = new TextField<String>("utilitiesExpensesField", Model.of(Double.toString(myExpenses.getUtilitiesExpenses())));
		final TextField<String> educationExpensesFieldValue = new TextField<String>("educationExpensesField", Model.of(Double.toString(myExpenses.getEducationExpenses())));
		final TextField<String> healthExpensesFieldValue = new TextField<String>("healthExpensesField", Model.of(Double.toString(myExpenses.getHealthExpenses())));
		final TextField<String> shoppingExpensesFieldValue = new TextField<String>("shoppingExpensesField", Model.of(Double.toString(myExpenses.getShoppingExpenses())));
		final TextField<String> transportExpensesFieldValue = new TextField<String>("transportExpensesField", Model.of(Double.toString(myExpenses.getTransportExpenses())));
		final TextField<String> entertainmentExpensesFieldValue = new TextField<String>("entertainmentExpensesField", Model.of(Double.toString(myExpenses.getEntertainmentExpenses())));
		final TextField<String> eatingOutExpensesFieldValue = new TextField<String>("eatingOutExpensesField", Model.of(Double.toString(myExpenses.getEatingOutExpenses())));
		final TextField<String> otherExpensesFieldValue = new TextField<String>("otherExpensesField", Model.of(Double.toString(myExpenses.getOtherExpenses())));
		
		add(page3);
		page3.add(new Label("heder" , "Expenses:"));
		page3.add(expensesForm);
		
		expensesForm.add(new Label("financialCommitmentsLabel" , "Financial commitments:"));
		expensesForm.add(new Label("homeExpensesLabel" , "Home:"));
		expensesForm.add(new Label("utilitiesExpensesLabel" , "Utilities:"));
		expensesForm.add(new Label("educationExpensesLabel" , "Education:"));
		expensesForm.add(new Label("healthExpensesLabel" , "Health:"));
		expensesForm.add(new Label("shoppingExpensesLabel" , "Shopping:"));
		expensesForm.add(new Label("transportExpensesLabel" , "Transport:"));
		expensesForm.add(new Label("entertainmentExpensesLabel" , "Entertainment:"));
		expensesForm.add(new Label("eatingOutExpensesLabel" , "Eating out:"));
		expensesForm.add(new Label("otherExpenses" , "Other:"));
		
		expensesForm.add(financialCommitmentsFieldValue);
		expensesForm.add(homeExpensesFieldValue);
		expensesForm.add(utilitiesExpensesFieldValue);
		expensesForm.add(educationExpensesFieldValue);
		expensesForm.add(healthExpensesFieldValue);
		expensesForm.add(shoppingExpensesFieldValue);
		expensesForm.add(transportExpensesFieldValue);
		expensesForm.add(entertainmentExpensesFieldValue);
		expensesForm.add(eatingOutExpensesFieldValue);
		expensesForm.add(otherExpensesFieldValue);
		
		Button expensesButton = new Button("expensesButton") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				if((WicketHttpsSession.get().isLogedIn()) && (!errorFlag)) {
					final double financialCommitmentsValue = Double.parseDouble(financialCommitmentsFieldValue.getValue().replace(',','.'));
					final double homeExpensesValue = Double.parseDouble(homeExpensesFieldValue.getValue().replace(',','.'));
					final double utilitiesExpensesValue = Double.parseDouble(utilitiesExpensesFieldValue.getValue().replace(',','.'));
					final double educationExpensesValue = Double.parseDouble(educationExpensesFieldValue.getValue().replace(',','.'));
					final double healthExpensesValue = Double.parseDouble(healthExpensesFieldValue.getValue().replace(',','.'));
					final double shoppingExpensesValue = Double.parseDouble(shoppingExpensesFieldValue.getValue().replace(',','.'));
					final double transportExpensesValue = Double.parseDouble(transportExpensesFieldValue.getValue().replace(',','.'));
					final double entertainmentExpensesValue = Double.parseDouble(entertainmentExpensesFieldValue.getValue().replace(',','.'));
					final double eatingOutExpensesValue = Double.parseDouble(eatingOutExpensesFieldValue.getValue().replace(',','.'));
					final double otherExpensesValue = Double.parseDouble(otherExpensesFieldValue.getValue().replace(',','.'));
					myExpenses.setFinancialCommitments(financialCommitmentsValue);
					myExpenses.setHomeExpenses(homeExpensesValue);
					myExpenses.setUtilitiesExpenses(utilitiesExpensesValue);
					myExpenses.setEducationExpenses(educationExpensesValue);
					myExpenses.setHealthExpenses(healthExpensesValue);
					myExpenses.setShoppingExpenses(shoppingExpensesValue);
					myExpenses.setTransportExpenses(transportExpensesValue);
					myExpenses.setEntertainmentExpenses(entertainmentExpensesValue);
					myExpenses.setEatingOutExpenses(eatingOutExpensesValue);
					myExpenses.setOtherExpenses(otherExpensesValue);
					try {
						myExpenses.save();
					} catch (Exception e) {
						new Loger().LogExceptions(e);
						errorFlag = true;
					}
				} else {
					setResponsePage(Error.class);
				}
			}
		};
		
		expensesForm.add(expensesButton);
    }
}
