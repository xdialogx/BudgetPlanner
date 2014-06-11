package budgetPlanner.app;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	
	private String adminEmail = "javabudgetplanner@gmail.com"; //javabudgetplanner@gmail.com
	private String password = "ZAQ!xsw2";  //ZAQ!xsw2

	public boolean sendMail(String userEmail, String newPassword) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(adminEmail, password);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(adminEmail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(userEmail));
			message.setSubject("New Password");
			message.setText("Dear user your new password is, " + newPassword);

			Transport.send(message);

			return true;
		} catch (MessagingException e) {
			new Loger().LogExceptions(e);
		}
		return false;
	}
}