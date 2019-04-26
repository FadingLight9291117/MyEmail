package module;

import java.util.Date;

public class Email {
	private String name;
	private String from;
	private Date SendDate;
	private String subject;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public java.util.Date getSendDate() {
		return SendDate;
	}

	public void setSendDate(Date sendDate) {
		SendDate = sendDate;
	}

}
