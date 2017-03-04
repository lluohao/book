package com.code.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private static class MyAutherticator extends Authenticator {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("amoursttm@163.com", "sttm123457");
		}
	}

	public void sendMail(String subject, String text, String mailAdress)
			throws MessagingException {
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.163.com");
		p.put("mail.smtp.port", "25");
		p.put("mail.smtp.auth", "true");
		MyAutherticator auth = new MyAutherticator();
		Session session = Session.getDefaultInstance(p, auth);
		Message message = new MimeMessage(session);
		message.setSubject(subject);
		message.setText(text);
		message.setFrom(new InternetAddress("amoursttm@163.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				mailAdress));
		Transport.send(message);
	}
	public static void main(String[] args) {
		MailSender sender = new MailSender();
		try {
			sender.sendMail("激活您的皓叶电子书邮箱", "<a href='http://hy/code/'>点这里</a>", "840230057@qq.com");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
