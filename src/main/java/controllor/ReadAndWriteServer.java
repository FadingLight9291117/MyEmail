package controllor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import module.EmailServer;

public class ReadAndWriteServer {
	private File file = new File("emailserver.json"); // 需要加一个判定文件是否存在
	private String emailserver;
	private String popServer;
	private String smtpServer;
	private Gson gson;

	public ReadAndWriteServer() {
	}

	public ReadAndWriteServer(String emailserver) {
		setEmailserver(emailserver);
		gson = new Gson();
		try (FileReader jsonReader = new FileReader(file)) {
			List<EmailServer> servers = gson.fromJson(jsonReader, new TypeToken<List<EmailServer>>() {
			}.getType());
			System.out.println("读取服务器地址成功");
			for (EmailServer server : servers) {
				if (server.getEmailServer().equals(emailserver)) {
					setPopServer(server.getPopServer());
					setSmtpServer(server.getSmtpServer());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 写入函数，序列化json
	private void writeJson(List<EmailServer> list) {
		String newJson = gson.toJson(list);
		try (FileWriter jsonWriter = new FileWriter(file)) {
			jsonWriter.write(newJson);
			System.out.println("写入服务器地址成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 读取函数，反序列化json
	private List<EmailServer> readJson() {
		List<EmailServer> list = null;
		gson = new Gson();
		try (FileReader jsonReader = new FileReader(file)) {
			list = gson.fromJson(jsonReader, new TypeToken<List<EmailServer>>() {
			}.getType());
			System.out.println("读取服务器地址成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 向文件中添加一个新的json数据
	public void addJson(String emailServer, String popServer, String smtpServer) {
		EmailServer newServer = new EmailServer();
		List<EmailServer> newServerList = readJson();
		newServer.setEmailServer(emailServer);
		newServer.setPopServer(popServer);
		newServer.setSmtpServer(smtpServer);
		newServerList.add(newServer);
		writeJson(newServerList);
		System.out.println("服务器地址添加成功");

	}

	// 删除一个json数据
	public void deleteJson(String emailserver) {
		List<EmailServer> servers = readJson();
		boolean b = false;
		for (EmailServer server : servers) {
			if (server.getEmailServer().equals(emailserver)) {
				servers.remove(server);
				b = true;
				System.out.println("刪除成功");
				break;
			}
		}
		if (b == true) {
			writeJson(servers);
		} else {
			System.out.println("该地址不存在");
		}

	}

	// 根据邮件服务器名称，在文件中查询是否存在其服务器地址数据
	public boolean queryEmailServer(String server) {
		List<EmailServer> list = null;
		gson = new Gson();
		try (FileReader jsonReader = new FileReader(file)) {
			list = gson.fromJson(jsonReader, new TypeToken<List<EmailServer>>() {
			}.getType());
			System.out.println("读取服务器地址成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (EmailServer l : list) {
			if (l.getEmailServer().equals(server)) {
				return true;
			}
		}
		System.out.println("该地址不存在");
		return false;
	}

	// 查询pop服务器地址
	public String queryPop(String server) {
		List<EmailServer> list = null;
		gson = new Gson();
		try (FileReader jsonReader = new FileReader(file)) {
			list = gson.fromJson(jsonReader, new TypeToken<List<EmailServer>>() {
			}.getType());
			System.out.println("读取服务器地址成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (EmailServer l : list) {
			if (l.getEmailServer().equals(server)) {
				return l.getPopServer();
			}
		}
		System.out.println("该地址不存在");
		return null;
	}

	// 查询smtp服务器地址
	public String querySmtp(String server) {
		List<EmailServer> list = null;
		gson = new Gson();
		try (FileReader jsonReader = new FileReader(file)) {
			list = gson.fromJson(jsonReader, new TypeToken<List<EmailServer>>() {
			}.getType());
			System.out.println("读取服务器地址成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (EmailServer l : list) {
			if (l.getEmailServer().equals(server)) {
				return l.getSmtpServer();
			}
		}
		System.out.println("该地址不存在");
		return null;
	}

	public String getEmailserver() {
		return emailserver;
	}

	public void setEmailserver(String emailserver) {
		this.emailserver = emailserver;
	}

	public String getPopServer() {
		return popServer;
	}

	public void setPopServer(String popServer) {
		this.popServer = popServer;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

//	public static void main(String[] args) {
//		// new ReadAndWriteServer().addJson("test", "test", "test");
////		new ReadAndWriteServer().deleteJson("test");
//		System.out.println(new ReadAndWriteServer().queryPop("qq"));
//	}
}
