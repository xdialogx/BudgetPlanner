package budgetPlanner.ui.page;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import budgetPlanner.app.Loger;
import budgetPlanner.app.Mail;
import budgetPlanner.app.Password;
import budgetPlanner.core.HibernateUtil;
import budgetPlanner.core.WicketHttpsSession;
import budgetPlanner.model.Users;

public class AdminPage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	private HibernateUtil hibernate = new HibernateUtil();
	private boolean errorFlag = false;

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public AdminPage() {
		final HomePage adminPage = new HomePage("navomaticBorder");
		add(adminPage);
		Integer userId = WicketHttpsSession.get().getAuthenticatedUserId();		
		boolean isAdmin = false;
		try {			
			isAdmin = hibernate.isAdmin(userId);
		} catch (Exception e) {
			errorFlag = true;
			new Loger().LogExceptions(e);
		}
		if ((WicketHttpsSession.get().isLogedIn()) && (!errorFlag) && (isAdmin)) {
			List<?> users = hibernate.getUserList();		
			adminPage.add(new ListView("listview", users) {
				@Override
			    protected void populateItem(ListItem item) {
			        final Users user = (Users) item.getModelObject();
			        item.add(new Label("userIdLabel", user.getUserId().toString()));
			        item.add(new Label("nameLabel", user.getName()));
			        item.add(new Label("surnameLabel", user.getSurname()));
			        item.add(new Label("emailLabel", user.getEmail()));
			        item.add(new Label("userNameLabel", user.getUserName()));
			        Form<Object> form = new Form<Object>("Form");
			        item.add(form);
			        
			        Button deleteButton = new Button("deleteButton") {
			        	@Override
						public void onSubmit() {
			        		try {
								hibernate.delteUser(user.getUserId());
							} catch (Exception e) {
								errorFlag = true;
								new Loger().LogExceptions(e);
							}
			        		if (!errorFlag) {
			        			setResponsePage(AdminPage.class);
			        		}
			        	}
			        };
			        form.add(deleteButton);
			        
			        Button resetButton = new Button("resetButton") {
			        	@Override
						public void onSubmit() {
			        		Password password = new Password();
			        		String str = password.getGeneratePassword();
			        		user.setPassword(password.encrypt(str));
			        		try {
								hibernate.updateUser(user);
							} catch (Exception e) {
								errorFlag = true;
								new Loger().LogExceptions(e);
							}
			        		new Mail().sendMail(user.getEmail(), str);
			        		if (!errorFlag) {
			        			setResponsePage(AdminPage.class);
			        		}
			        	}
			        };
			        form.add(resetButton);
			    }
			});
		}	
		if (errorFlag) {
			setResponsePage(Error.class);
		}
	}
}
