package budgetPlanner.ui.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.PatternValidator;

import budgetPlanner.app.Loger;
import budgetPlanner.app.Password;
import budgetPlanner.core.HibernateUtil;
import budgetPlanner.core.WicketHttpsSession;
import budgetPlanner.model.Users;

public class Registration extends WebPage {

	private static final long serialVersionUID = 1L;
	private final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
	private final String NAME_PATTERN = "((?=.*[a-z])(?=.*[A-Z]).{3,30})";
	private final TextField<String> valueName = new TextField<String>("NameInput",
			Model.of(""));
	private final TextField<String> valueSurname = new TextField<String>(
			"SurnameInput", new Model<String>());
	private final EmailTextField valueEmail = new EmailTextField("EmailInput",
			Model.of(""));
	private final TextField<String> valueUserName = new TextField<String>(
			"UserName", new Model<String>());
	private final PasswordTextField valuePassword = new PasswordTextField(
			"Password", Model.of(""));
	private final PasswordTextField valueConfirmPassword = new PasswordTextField(
			"ConfirmPassword", Model.of(""));

	public Registration(final PageParameters parameters) {
		HomePage registrationPage = new HomePage("navomaticBorder");
		add(registrationPage);
		registrationPage.add(new FeedbackPanel("feedback"));

		Form<Object> registrationForm = new Form<Object>("Form");
		registrationPage.add(registrationForm);

		valuePassword.add(new PatternValidator(PASSWORD_PATTERN));
		valueEmail.add(EmailAddressValidator.getInstance());
		valueName.add(new PatternValidator(NAME_PATTERN));
		valueSurname.add(new PatternValidator(NAME_PATTERN));
		valueUserName.add(new PatternValidator(NAME_PATTERN));

		Button loginButton = new Button("RegistrationBtn") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				boolean errorFlag = false;
				Users myUser = getUser();			
				String checkString = null;
				try {
					HibernateUtil hibernate = new HibernateUtil();
					checkString = hibernate.checkUsernameAndEmail(myUser.getUserName(), myUser.getEmail());
					if (checkString!= null) {
						myUser = null;
						info(checkString);
					}
				} catch (Exception e) {
					new Loger().LogExceptions(e);
					errorFlag = true;
					setResponsePage(Error.class);
				}
				if ((!errorFlag) && (myUser != null)) {
					try {
						HibernateUtil hibernate = new HibernateUtil();
						myUser.setUserId(hibernate.addUserToDb(myUser));				
					} catch (Exception e) {
						new Loger().LogExceptions(e);
						setResponsePage(Error.class);
					}
					if (myUser.getUserId() != null) {
						WicketHttpsSession currentSession = WicketHttpsSession.get();
						currentSession.setAuthenticatedUserId(myUser.getUserId());
						setResponsePage(Page2.class);
					}	
				}
			}
		};
		
		registrationForm.add(new Label("NameLabel", "Name:"));
		registrationForm.add(valueName);
		registrationForm.add(new Label("SurnameLabel", "Surname:"));
		registrationForm.add(valueSurname);
		registrationForm.add(new Label("Email", "e-mail:"));
		registrationForm.add(valueEmail);
		registrationForm.add(new Label("UserNameLabel", "user name:"));
		registrationForm.add(valueUserName);
		registrationForm.add(new Label("PasswordLabel", "password:"));
		registrationForm.add(valuePassword);
		registrationForm.add(new Label("ConfirmPasswordLabel",
				"confirm password:"));
		registrationForm.add(valueConfirmPassword);
		registrationForm.add(new EqualPasswordInputValidator(valuePassword,
				valueConfirmPassword));
		registrationForm.add(loginButton);
	}

	@SuppressWarnings("unused")
	private boolean EmptyValidator(TextField<String> valueName,
			TextField<String> valueSurname, TextField<String> valueUserName) {
		if (valueName.getValue().isEmpty()) {
			info("Name is required!");
			return true;
		}
		if (valueSurname.getValue().isEmpty()) {
			info("Surname is required!");
			return true;
		}
		if (valueUserName.getValue().isEmpty()) {
			info("User name is required!");
			return true;
		}
		return false;
	}
	
	private Users getUser() {
		Users myUser = new Users();
		myUser.setEmail(valueEmail.getValue());
		myUser.setUserName(valueUserName.getValue());
		myUser.setName(valueName.getValue());
		myUser.setSurname(valueSurname.getValue());
		myUser.setPassword(new Password().encrypt(valuePassword.getValue()));
		return myUser;
	}
}
