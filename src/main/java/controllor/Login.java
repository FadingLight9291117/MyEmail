package controllor;

import java.util.Properties;

import javax.mail.*;

public class Login {
	private Session session;
	private Store store;
	private Transport transport;
	private String email;
	private String password;
	private ReadAndWriteServer readAndWriteServer;
	private String popServer;
	private String smtpServer;

	public Login(String email, String password, ReadAndWriteServer readAndWriteServer) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
		this.readAndWriteServer = readAndWriteServer;
		this.popServer = readAndWriteServer.getPopServer();
		this.smtpServer = readAndWriteServer.getSmtpServer();
	}

	public boolean popConnect() {
		// 设置属性
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
		props.put("mail.pop3.class", "com.sun.mail.pop3.POP3Store");

		// 创建Session对象
		session = Session.getInstance(props, null);
		// session.setDebug(false); // 输出跟踪日志

		// 创建Store对象
		try {
			store = session.getStore("pop3");
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		// 连接到收邮件服务器
		try {
			store.connect(popServer, email, password);
			store.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean smtpConnect() {
		// 设置属性
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
		props.put("mail.smtp.host", smtpServer); // 设置发送邮件服务器
		// props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true"); // SMTP服务器需要身份验证

		// 创建Session对象
		session = Session.getInstance(props, new Authenticator() { // 验账账户
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});
		//session.setDebug(true); // 输出跟踪日志

		// 创建Transport对象
		try {
			transport = session.getTransport();
			try {
				transport.connect(smtpServer, email, password);
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
