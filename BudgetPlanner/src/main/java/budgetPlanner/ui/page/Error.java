package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.ContextRelativeResource;

public class Error extends WebPage {

	private static final long serialVersionUID = 1L;

	public Error() {
		HomePage errorPage = new HomePage("navomaticBorder");
		add(errorPage);
		errorPage.add(new Image("underConstruction",
				new ContextRelativeResource("/images/underConstruction.jpg")));
	}
}
