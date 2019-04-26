package controllor;

import javax.mail.*;

import javax.mail.internet.*;

import javax.activation.*;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import com.sun.mail.handlers.multipart_mixed;
import com.sun.mail.handlers.text_html;

import module.Email;

public class MailClientRecv {
	private Session session;
	private Store store;
	private String username = "1364409394@qq.com";
	private String password = "powkwmkcvphgbaec";
	private String popServer = "pop.qq.com";

	public MailClientRecv() {
		// TODO Auto-generated constructor stub
	}

	public MailClientRecv(String username, String password, String popServer) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.popServer = popServer;
	}

	public void init() throws Exception {
		// 设置属性
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
		props.put("mail.pop3.class", "com.sun.mail.pop3.POP3Store");

		props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.pop3.port", "995");


		// 创建Session对象
		session = Session.getInstance(props, null);
		session.setDebug(false); // 输出跟踪日志

		// 创建Store对象
		store = session.getStore("pop3");

		// 连接到收邮件服务器
		store.connect(popServer, username, password);
	}

	public void receiveMessage() throws Exception {

		String folderName = "inbox";
		Folder folder = store.getFolder(folderName);
		if (folder == null) {
			throw new Exception(folderName + "邮件夹不存在");
		}
		// 打开信箱
		folder.open(Folder.READ_ONLY);
		// System.out.println("您的收件箱有" + folder.getMessageCount() + "封邮件.");
		// System.out.println("您的收件箱有" + folder.getUnreadMessageCount() + "封未读的邮件.");

		// 读邮件
		Message[] messages = folder.getMessages();

		List<Email> list = new ArrayList<>();

		for (int i = messages.length - 1; i >= messages.length - 5; i--) {
			// System.out.println("------第" + (i + 1) + "封邮件-------");
			// 打印邮件信息
			Message message = messages[i];
			Email email = new Email();

			// email.setFrom(message.getFrom());

			InternetAddress address[] = (InternetAddress[]) message.getFrom();
			String name = address[0].getPersonal(); // 获取发件人名称
			String from = address[0].getAddress(); // 获取发件人地址
			email.setName(name);
			email.setFrom(from);
			email.setSendDate(message.getSentDate());
			email.setSubject(message.getSubject());

			if (message.isMimeType("text/plain") || message.isMimeType("text/html")) {
				email.setContent((String) message.getContent());
				// System.out.println(message.getContent());
			} else if (message.isMimeType("multipart/*")) {
				email.setContent("此邮件为多附件邮件，暂时无法解析！");
			}

			list.add(email);
			// folder.getMessage(i).writeTo(System.out);

		}
		folder.close(false); // 关闭邮件夹

		File file = new File("src/main/resources/email.json");
		Gson gson = new Gson();
		String string = gson.toJson(list);
		try (FileWriter jsonWriter = new FileWriter(file)) {
			jsonWriter.write(string);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void close() throws Exception {
		store.close();
	}

	public static void main(String[] args) throws Exception {
		MailClientRecv client = new MailClientRecv();
		// 初始化
		client.init();
		// 接收邮件
		client.receiveMessage();
		// 关闭连接
		client.close();
	}

}
