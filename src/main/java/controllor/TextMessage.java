package controllor;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.FileOutputStream;

public class TextMessage {
	public static MimeMessage generate(String from, String to, String subject, String body) throws Exception {
//		String from = "fadinglight9291117@126.com"; // 发件人地址
//		String to = "fadinglight9291117@126.com"; // 收件人地址

//		String subject = "你好";
//		String body = "fuck you";

		// 创建Session实例对象
		Session session = Session.getDefaultInstance(new Properties());
		// 创建MimeMessage实例对象
		MimeMessage message = new MimeMessage(session);
		// 设置发件人
		message.setFrom(new InternetAddress(from));
		// 设置收件人
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		// 设置发送日期
		message.setSentDate(new Date());
		// 设置邮件主题
		message.setSubject(subject);
		// 设置纯文本内容的邮件正文
		message.setText(body);
		// 保存并生成最终的邮件内容
		message.saveChanges();

		// 把MimeMessage对象中的内容写入到文件中
		// msg.writeTo(new FileOutputStream("e:/test.eml"));
		return message;
	}
}
