package controllor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import module.Email;
import module.EmailServer;

public class EmailIO {
	private String email;
	private String password;
	private String popServer;

	public static void main(String[] args) {
		EmailIO emailIO = new EmailIO("fadinglight9291117@126.com", "m4a1421", "pop.126.com");
		emailIO.readEmail();
		try {
			emailIO.sync();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public EmailIO(String email, String password, String popServer) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
		this.popServer = popServer;
	}

	public void sync() throws Exception {
		MailClientRecv client = new MailClientRecv(email, password, popServer);
		client.init();
		// 接收邮件
		client.receiveMessage();
		// 关闭连接
		client.close();
	}

	public List<Email> readEmail() {
		File file = new File("src/main/resources/email.json");
		Gson gson = new Gson();

		try (FileReader jsonReader = new FileReader(file)) {
			List<Email> list = gson.fromJson(jsonReader, new TypeToken<List<Email>>() {
			}.getType());
//			for(Email e:list) {
//				System.out.println(e.getContent());
//			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
