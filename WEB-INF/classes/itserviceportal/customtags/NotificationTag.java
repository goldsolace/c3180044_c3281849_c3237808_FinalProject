package itserviceportal.customtags;

import itserviceportal.model.beans.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class NotificationTag extends SimpleTagSupport {

	private Notification notification;

	public NotificationTag() {
	}

	public void setNotification(Notification notificaiton) {
		this.notification = notification;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if (notification == null) {
			return;
		}

		String notifStr = "";
		switch (notification.getAction()) {
			case STARTWORK : notifStr+= "Support Ticket " + notification.getTicketID(); break;
			case SUBMITSOLUTION : notifStr+= "Support Ticket " + notification.getTicketID(); break;
			case ADDKOWLEDGE : notifStr+= "Support Ticket " + notification.getTicketID(); break;
			case REMOVEKNOWLEDGE : notifStr+= "Support Ticket " + notification.getTicketID(); break;
			case COMMENT : notifStr+= "Support Ticket " + notification.getTicketID(); break;
			default : return;
		}

		// Output to jsp
		getJspContext().getOut().write(notifStr);
	}
}