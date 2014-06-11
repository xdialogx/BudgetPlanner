package budgetPlanner.ui;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import budgetPlanner.core.WicketHttpsSession;
import budgetPlanner.ui.page.Page1;

public class WicketApplication extends WebApplication {
    
	@Override
    public Session newSession(Request request, Response response) {
        return new WicketHttpsSession(request);
    }
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Class getHomePage() {
        return Page1.class;
    }	
}
