package budgetPlanner.core;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class WicketHttpsSession extends WebSession {

	private static final long serialVersionUID = 1L;
	
	private final int NOT_VALID_ID = -1; 
	private int userId = NOT_VALID_ID;

    public WicketHttpsSession(Request request) {
        super(request);
    }

    public static WicketHttpsSession get() {
        return (WicketHttpsSession) Session.get();
    }

    public int getAuthenticatedUserId() {
        return userId;
    }

    public void setAuthenticatedUserId(int userId) {
        this.userId = userId;
    }
    
    public boolean isLogedIn() {
    	if (getAuthenticatedUserId() > NOT_VALID_ID) {
    		return true;
    	}
    	return false;
    }

}
