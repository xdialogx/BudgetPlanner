package budgetPlanner.ui.page;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.border.*;
import org.apache.wicket.request.resource.CssResourceReference;

public class HomePage extends Border {
	
	private static final long serialVersionUID = 1L;

	@Override
	 public void renderHead(IHeaderResponse response){
		response.render(CssHeaderItem.forReference(new CssResourceReference(HomePage.class, "HomePage.css")));
	 }

	@SuppressWarnings("deprecation")
	public HomePage(final String componentName) {
		super(componentName);
		
		addToBorder(new HeaderPanel("headerpanel", "Budget Planner"));
		addToBorder(new MenuPanel("menupanel"));
				
		addToBorder(new BoxBorder("bodyBorder"));
	}
}
