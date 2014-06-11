package budgetPlanner;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import budgetPlanner.ui.WicketApplication;
import budgetPlanner.ui.page.Page1;
import budgetPlanner.ui.page.Page2;
import budgetPlanner.ui.page.Page3;
import budgetPlanner.ui.page.Error;

public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void page1RendersSuccessfully() {
		tester.startPage(Page1.class);
		tester.assertRenderedPage(Page1.class);
	}
	
	@Test
	public void page2RendersSuccessfully() {
		tester.startPage(Page2.class);
		tester.assertRenderedPage(Page2.class);
	}
	
	@Test
	public void page3RendersSuccessfully() {
		tester.startPage(Page3.class);
		tester.assertRenderedPage(Page3.class);
	}
	
	@Test
	public void ErrorPageRendersSuccessfully() {
		tester.startPage(Error.class);
		tester.assertRenderedPage(Error.class);
	}	
}
