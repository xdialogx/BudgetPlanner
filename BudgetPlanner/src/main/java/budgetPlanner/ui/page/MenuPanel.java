package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import budgetPlanner.core.WicketHttpsSession;

public class MenuPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public MenuPanel(final String componentName) {
		super(componentName);

		Link<Object> btn1Link = new Link<Object>("button1") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
					setResponsePage(Page1.class);
			}
		};
		add(btn1Link);
		
		btn1Link.add(new Label("page1", "About"));

		Link<Object> btn2Link = new Link<Object>("button2") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				if (WicketHttpsSession.get().isLogedIn()) {
					setResponsePage(Page2.class);
				} else {
					setResponsePage(Page1.class);
				}
			}
		};
		add(btn2Link);
		btn2Link.add(new Label("page2", "Income"));

		Link<Object> btn3Link = new Link<Object>("button3") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				if (WicketHttpsSession.get().isLogedIn()) {
					setResponsePage(Page3.class);
				} else {
					setResponsePage(Page1.class);
				}
			}
		};
		add(btn3Link);
		btn3Link.add(new Label("page3", "Expenses"));
		
		Link<Object> btn4Link = new Link<Object>("button4") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				if (WicketHttpsSession.get().isLogedIn()) {
					setResponsePage(Page4.class);
				} else {
					setResponsePage(Page1.class);
				}
			}
		};
		add(btn4Link);
		btn4Link.add(new Label("page4", "Summary"));
	}
}
