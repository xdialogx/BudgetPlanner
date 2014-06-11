package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import budgetPlanner.core.HibernateUtil;
import budgetPlanner.core.WicketHttpsSession;

public class HeaderPanel extends Panel {

	private static final long serialVersionUID = 1L;
	private String loginBtnText = "Sign in";
	private final int NOT_VALID_ID = -1;

	public HeaderPanel(String componentName, String Title) {
		super(componentName);
		add(new Label("Title", Title));
		if(WicketHttpsSession.get().isLogedIn()) {
			Integer userId = WicketHttpsSession.get().getAuthenticatedUserId();
			HibernateUtil hibernate = new HibernateUtil();
			String userName = hibernate.getUserName(userId);
			setLoginBtnText(userName + ", Sign out");
		}

		Link<Object> link = new Link<Object>("link") {
			private static final long serialVersionUID = 1L;

			public void onClick() {
				if(!WicketHttpsSession.get().isLogedIn()) {
					setResponsePage(Login.class);
				} else {
					setLoginBtnText("Sign in");
					WicketHttpsSession.get().setAuthenticatedUserId(NOT_VALID_ID);
					setResponsePage(Page1.class);
				}			
			}
		};
		add(link);
		link.add(new Label("login", getLoginBtnText()));
	}
	
	public void setLoginBtnText(String text) {
		this.loginBtnText = text;
	}
	
	public String getLoginBtnText() {
		return this.loginBtnText;
	}

}
