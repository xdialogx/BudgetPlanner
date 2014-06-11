package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;

import budgetPlanner.app.Loger;
import budgetPlanner.app.Password;
import budgetPlanner.core.HibernateUtil;
import budgetPlanner.core.WicketHttpsSession;
import budgetPlanner.model.Users;

public class Login extends WebPage {

	private static final long serialVersionUID = 1L;

	public Login() {

		HomePage loginPage = new HomePage("navomaticBorder");
		add(loginPage);

		Form<Object> loginForm = new Form<Object>("loginForm");
		loginPage.add(loginForm);

		final TextField<String> valueName = new TextField<String>("userName",
				new Model<String>());
		final PasswordTextField valuePassword = new PasswordTextField(
				"userPassword", Model.of(""));

		Button loginButton = new Button("loginButton") {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public void onSubmit() {
				boolean errorFlag = false;
				Users user = null;
				try {
					HibernateUtil hibernate = new HibernateUtil();
					user = hibernate.checkUsernameAndPassword(valueName
							.getValue(), new Password()
							.encrypt(valuePassword.getValue()));
					if (user == null) {
						info("Username or password is incorrect!");
					}
				} catch (Exception e) {
					errorFlag = true;
					new Loger().LogExceptions(e);
					setResponsePage(Error.class);
				}
				if ((!errorFlag) && (user != null)) {
					WicketHttpsSession currentSession = WicketHttpsSession
							.get();
					currentSession.setAuthenticatedUserId(user.getUserId());
					setResponsePage(getPageClass(user));
				}
			}
		};

		loginForm.add(new Label("userNameLabel", "User Name:"));
		loginForm.add(valueName);
		loginForm.add(new Label("userPasswordLabel", "User Password:"));
		loginForm.add(valuePassword);
		loginForm.add(loginButton);
		loginForm.add(new BookmarkablePageLink<Object>("link",
				Registration.class));
	}

	@SuppressWarnings("rawtypes")
	private Class getPageClass(Users user) {
		if (user.getType().equals("admin")) {
			return AdminPage.class;
		}
		return Page2.class;
	}
}
