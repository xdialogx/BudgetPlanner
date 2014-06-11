package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@SuppressWarnings("serial")
public class Page1 extends WebPage {
	
    public Page1(final PageParameters parameters) {
    	  	
    	HomePage myPage = new HomePage("navomaticBorder");
    	add(myPage);		
    }
    
}
