import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date d1 = new Date();
		String str1 = d.format(d1);
		System.out.println(str1);
	}

}
