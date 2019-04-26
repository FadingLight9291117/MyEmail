package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllor.MailClientSend;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class writeMailPage extends JFrame {

	private String sender;
	private String password;
	private String smtpServer;

	private JPanel contentPane;
	private JButton btnSend;
	private JButton btnSender;
	private JButton btnReciver;
	private JLabel lblSubject;
	private JTextField textSender;
	private JTextField textReciver;
	private JTextField textSubject;
	private JEditorPane jEditorPanecontent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					writeMailPage frame = new writeMailPage("freeman9291117@163.com", "m4a14213230552", "smtp.163.com");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public writeMailPage(String sender, String password, String smtpServer) {

		this.sender = sender;
		this.password = password;
		this.smtpServer = smtpServer;

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
		north.setPreferredSize(new Dimension(0, 150));
		north.setBackground(Color.white);
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(null);

		ImageIcon imageIcon = new ImageIcon("src\\img\\send.png");
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		btnSend = new JButton("发送", imageIcon);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("点击了发送");
				String reciver = textReciver.getText();
				String subject = textSubject.getText();
				String content = jEditorPanecontent.getText();
				if (reciver.isEmpty() || subject.isEmpty() || content.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入完整！");
				} else if (reciver.indexOf('@') == -1 || reciver.lastIndexOf('@') != reciver.indexOf('@')) {
					System.out.println("邮箱格式非法");
					JOptionPane.showMessageDialog(null, "收件人邮箱格式不正确");
				} else {
					MailClientSend client = new MailClientSend(sender, password, smtpServer);
					try {
						// 初始化
						client.init();
						System.out.println("发送邮件初始化成功");
						// 发送邮件
						client.sendMessage(reciver, subject, content);
						System.out.println("邮件发送成功");
						// 关闭连接
						client.close();
						System.out.println("关闭与smtp服务器连接");
						JOptionPane.showMessageDialog(null, "发送成功！");
						dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("发送出错");
						JOptionPane.showMessageDialog(null, "未知错误！可能邮件包含敏感信息！");
						e1.printStackTrace();
					}
				}
			}
		});
		// 设置button文字在下图标在上
		btnSend.setVerticalTextPosition(JButton.BOTTOM);
		btnSend.setHorizontalTextPosition(JButton.CENTER);
		btnSend.setFont(new Font("宋体", Font.PLAIN, 14));
		btnSend.setBackground(Color.white);
		btnSend.setFocusable(false); // 去掉焦点框
		btnSend.setBounds(10, 10, 70, 80);
		north.add(btnSend);

		btnSender = new JButton("发件人");
		btnSender.setBounds(90, 10, 90, 30);
		btnSender.setFont(new Font("宋体", Font.PLAIN, 14));
		btnSender.setBackground(Color.white);
		btnSender.setFocusable(false); // 去掉焦点框
		north.add(btnSender);

		btnReciver = new JButton("收件人");
		btnReciver.setBounds(90, 60, 90, 30);
		btnReciver.setFont(new Font("宋体", Font.PLAIN, 14));
		btnReciver.setBackground(Color.white);
		btnReciver.setFocusable(false); // 去掉焦点框
		north.add(btnReciver);

		lblSubject = new JLabel("主题", JLabel.CENTER);
		lblSubject.setFont(new Font("宋体", Font.PLAIN, 14));
		lblSubject.setBounds(90, 110, 90, 30);
		north.add(lblSubject);

		textSender = new JTextField(sender);
		textSender.setEditable(false);				//设置为只读
		textSender.setFont(new Font("宋体", Font.PLAIN, 14));
		textSender.setBounds(200, 10, 563, 30);
		north.add(textSender);
		textSender.setColumns(1);

		textReciver = new JTextField();
		textReciver.setFont(new Font("宋体", Font.PLAIN, 14));
		textReciver.setBounds(200, 60, 563, 30);
		north.add(textReciver);
		textReciver.setColumns(1);

		textSubject = new JTextField();
		textSubject.setFont(new Font("宋体", Font.PLAIN, 14));
		textSubject.setBounds(200, 110, 563, 30);
		north.add(textSubject);
		textSubject.setColumns(1);

		JScrollPane center = new JScrollPane();
		contentPane.add(center, BorderLayout.CENTER);

		jEditorPanecontent = new JEditorPane();
		jEditorPanecontent.setFont(new Font("宋体", Font.PLAIN, 20));
		center.setViewportView(jEditorPanecontent);
	}
}
