package budgetPlanner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import budgetPlanner.core.HibernateUtil;
import budgetPlanner.model.Expenses;
import budgetPlanner.model.IncomePay;

import com.mchange.util.AssertException;

public class LogicTest {
	
	private HibernateUtil hibernate;
	private final int TESTID = 8;
	
	@Before
	public void setUp() {
		hibernate = new HibernateUtil();
	}
	
	@Test
	public void HibernateReadUser() {		
		assertEquals("Test", hibernate.getUserName(TESTID));
	}
	
	@Test 
	public void HibernateReadIncomePay() {
		IncomePay incomePay = null;
		try {
			incomePay = new IncomePay(TESTID);
		} catch (Exception e) {
			throw new AssertException(e.toString());
		} 
		assertEquals("2.0", Double.toString(incomePay.getOther()));
	}
	
	@Test
	public void HibernateReadExpenses() {
		Expenses expenses = null;
		try {
			expenses = new Expenses(TESTID);
		} catch (Exception e) {
			throw new AssertException(e.toString());
		}
		assertEquals("1.0", Double.toString(expenses.getEducationExpenses()));
	}
	
	@Test
	public void TetsMathLogic() {
		IncomePay incomePay = null;
		Expenses expenses = null;
		try {
			incomePay = new IncomePay(TESTID);
			expenses = new Expenses(TESTID);
		} catch (Exception e) {
			throw new AssertException(e.toString());
		}
		assertEquals("0.0", Double.toString(incomePay.getIncomePaySum() + expenses.getExpensesSum())); 
	}
}
