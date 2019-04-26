package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkListener;

import java.awt.Component;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JEditorPane;

public class ReadMailPage extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadMailPage frame = new ReadMailPage("主题", "发件人", "发件地址", new Date(), "内容");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public ReadMailPage(String subject, String name, String from, java.util.Date senddate, String content)
			throws IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 仅关闭当前窗体
		setBounds(100, 100, 450, 300);

		setTitle("MyEmail");
		Image image = Toolkit.getDefaultToolkit().getImage("src\\img\\logo.png");
		setIconImage(image);
		setBounds(100, 100, 800, 650);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);

		int windowWidth = getWidth(); // 获得窗口宽
		int windowHeight = getHeight();// 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
		setResizable(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);

		JPanel north = new JPanel();
		north.setBackground(Color.WHITE);
		north.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 10));

		SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String title = "<html>\r\n" + "<div><font  size=6>" + subject + "</font></div><br>" + "<div><font size=4>"
				+ name + "&lt;" + from + "&gt;</font></div>\r\n" + "<div><font size=3>&nbsp;" + d.format(senddate)
				+ "</font></div>\r\n" + "</html>";
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 12));
		north.add(lblNewLabel);

		JScrollPane center = new JScrollPane();
		contentPane.add(center, BorderLayout.CENTER);
		
		
		JEditorPane editorPane = new JEditorPane();
//		String path = "file:src/main/resources/emailFiles/test.html";
//		editorPane.setPage(path);
		editorPane.setText(content);
		editorPane.setEditable(false); // 请把editorPane设置为只读，不然显示就不整齐
		center.setViewportView(editorPane);

	}
}
