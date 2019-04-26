package controllor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import module.EmailServer;

public class Gsontest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		File file = new File("emailserver.json");
		File file2=new File("testJson.json");
		try (FileReader jsonReader = new FileReader(file)) {
			List<EmailServer> servers = gson.fromJson(jsonReader, new TypeToken<List<EmailServer>>() {
			}.getType());
			for (EmailServer server : servers) {
				System.out.println(gson.toJson(server));
				System.out.println(server.getEmailServer());
			}
			String string=gson.toJson(servers);
			System.out.println(string);
			try (FileWriter jsonWriter=new FileWriter(file)){
				jsonWriter.write(string);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
