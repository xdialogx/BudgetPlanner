package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import budgetPlanner.app.Loger;
import budgetPlanner.core.WicketHttpsSession;
import budgetPlanner.model.Expenses;
import budgetPlanner.model.IncomePay;

public class Page4 extends WebPage {
	private static final long serialVersionUID = 1L;

	public Page4() {
		HomePage page4 = new HomePage("navomaticBorder");
		add(page4);
		page4.add(new Label("heder" , "Annual summary:"));
		boolean errorFlag = false;
		double incomePaySum = 0;
		double expensesSum = 0;
		if(WicketHttpsSession.get().isLogedIn()) {
			Integer userId = WicketHttpsSession.get().getAuthenticatedUserId();
			IncomePay myIncomePay = null;
			Expenses myExpenses = null;
			try {
				myIncomePay = new IncomePay(userId);
				myExpenses = new Expenses(userId);
			} catch (Exception e) {
				new Loger().LogExceptions(e);
				errorFlag = true;
			}
			incomePaySum = myIncomePay.getIncomePaySum();
			expensesSum = myExpenses.getExpensesSum();
		}
		page4.add(new Label("incomePaySumLabel" , "Total income:"));
		page4.add(new Label("expensesSumLabel" , "Total expenses"));
		page4.add(new Label("totalLabel" , "Surplus / shortfall:"));
		page4.add(new Label("incomePaySumValue" , incomePaySum));
		page4.add(new Label("expensesSumValue" , expensesSum));
		page4.add(new Label("totalValue" , incomePaySum + expensesSum));
		if(errorFlag) {
			setResponsePage(Error.class);
		}
    }
}
