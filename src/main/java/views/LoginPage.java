package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controllor.Login;
import controllor.ReadAndWriteServer;

import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import java.awt.SystemColor;

public class LoginPage extends JFrame {

	public static String email = null;
	public static String password = null;
	public static ReadAndWriteServer readAndWriteServer = null;

	private JPanel contentPanel;
	private JTextField jtextEmail;
	private JTextField jtextPassword;
	private JButton jbLogin;
	private JLabel lblLoader;

	private String FontStyle = "行书";

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					// UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
					LoginPage frame = new LoginPage();
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
	public LoginPage() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitle("MyEmail");
		Image image = Toolkit.getDefaultToolkit().getImage("src\\img\\logo.png");
		setIconImage(image);

		setBounds(100, 100, 800, 650);
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE); // 设置contentpane背景颜色
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPanel);
		setResizable(false);// 设置窗口尺寸无法改变

		int windowWidth = getWidth(); // 获得窗口宽
		int windowHeight = getHeight();// 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示

		ImageIcon imageIcon = new ImageIcon("src\\img\\logo.png");
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		JLabel jlaLogo = new JLabel(imageIcon);
		jlaLogo.setBounds(349, 41, 100, 100);

		JLabel jlaWel = new JLabel("欢迎登录");
		jlaWel.setBounds(206, 178, 578, 58);
		jlaWel.setBackground(Color.WHITE);
		jlaWel.setForeground(Color.black);
		jlaWel.setFont(new Font("宋体", Font.PLAIN, 50));

		JLabel jlaemail = new JLabel("邮箱");
		jlaemail.setBounds(206, 242, 28, 15);
		jlaemail.setBackground(Color.WHITE);
		jlaemail.setForeground(Color.DARK_GRAY);
		jlaemail.setFont(new Font(FontStyle, Font.PLAIN, 14));

		jtextEmail = new JTextField();
		jtextEmail.setBounds(206, 271, 360, 36);
		jtextEmail.setFont(new Font(FontStyle, Font.PLAIN, 16));
		jtextEmail.setColumns(1);

		JLabel jlapassword = new JLabel("密码");
		jlapassword.setBounds(206, 327, 28, 15);
		jlapassword.setBackground(Color.WHITE);
		jlapassword.setForeground(Color.DARK_GRAY);
		jlapassword.setFont(new Font(FontStyle, Font.PLAIN, 14));

		jtextPassword = new JPasswordField();
		jtextPassword.setBounds(206, 352, 360, 36);
		jtextPassword.setFont(new Font(FontStyle, Font.PLAIN, 16));
		jtextPassword.setColumns(1);

		jbLogin = new JButton("登录");
		jbLogin.setBounds(206, 443, 361, 40);
		jbLogin.setForeground(Color.WHITE);
		jbLogin.setBorder(null); // 去掉边框
		jbLogin.setFocusable(false); // 去掉焦点框
		jbLogin.setFont(new Font(FontStyle, Font.PLAIN, 14));
		jbLogin.setBackground(new Color(0, 102, 255));
		getRootPane().setDefaultButton(jbLogin);// 设置默认按钮
		jbLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("点击了“登录”");
				canLogin(jtextEmail.getText().toString(), jtextPassword.getText().toString());
			}
		});

		JLabel jlaSetting = new JLabel("设置");
		jlaSetting.setBounds(206, 489, 28, 20);
		jlaSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("点击了“设置”");
				ServerSetting serverSetting = new ServerSetting();
				serverSetting.setVisible(true);
			}
		});
		jlaSetting.setBackground(Color.WHITE);
		jlaSetting.setForeground(Color.BLACK);
		jlaSetting.setFont(new Font(FontStyle, Font.PLAIN, 14));

		JLabel labHelp = new JLabel("帮助");
		labHelp.setBounds(539, 489, 28, 20);
		labHelp.setBackground(Color.WHITE);
		labHelp.setForeground(Color.BLACK);
		labHelp.setFont(new Font(FontStyle, Font.PLAIN, 14));

		JCheckBox cbSave = new JCheckBox("保存密码");
		cbSave.setBounds(206, 400, 81, 29);
		cbSave.setBackground(Color.WHITE);
		cbSave.setForeground(Color.BLACK);
		cbSave.setFont(new Font(FontStyle, Font.PLAIN, 14));

		JCheckBox cbAuto = new JCheckBox("自动登录");
		cbAuto.setBounds(486, 400, 81, 29);
		cbAuto.setBackground(Color.WHITE);
		cbAuto.setForeground(Color.BLACK);
		cbAuto.setFont(new Font(FontStyle, Font.PLAIN, 14));
		contentPanel.setLayout(null);
		contentPanel.add(jlaLogo);
		contentPanel.add(jlapassword);
		contentPanel.add(jlaemail);
		contentPanel.add(jlaWel);
		contentPanel.add(jbLogin);
		contentPanel.add(cbSave);
		contentPanel.add(cbAuto);
		contentPanel.add(jtextPassword);
		contentPanel.add(jtextEmail);
		contentPanel.add(jlaSetting);
		contentPanel.add(labHelp);

		lblLoader = new JLabel("等待： 连接服务器......");
		lblLoader.setBounds(40, 580, 720, 20);
		lblLoader.setVisible(false);
		contentPanel.add(lblLoader);
	}

	public void canLogin(String email, String password) {
		String server;
		if (email.isEmpty() || password.isEmpty()) {
			System.out.println("邮箱或密码为空");
			JOptionPane.showMessageDialog(null, "邮箱或密码不能为空！");
		} else if (email.indexOf('@') == -1 || email.lastIndexOf('@') != email.indexOf('@')) {
			System.out.println("邮箱格式非法");
			JOptionPane.showMessageDialog(null, "邮箱格式不正确");
		} else {
			server = email.substring(email.indexOf('@') + 1, email.length());
			System.out.println("email===>>>" + email);
			System.out.println("password===>>>" + password);
			System.out.println("server===>>>" + server);
			if (new ReadAndWriteServer().queryEmailServer(server)) {
				System.out.println("邮件服务器存在于json文件中");
				this.email = email;
				this.password = password;
				this.readAndWriteServer = new ReadAndWriteServer(server);
				// 连接邮件服务器验证
				System.out.println("等待服务器验证");
				
				lblLoader.setVisible(true);//有问题！！！！！！！！！！！！！！！！！！！！！！！！！！！
				
				Login login = new Login(email, password, readAndWriteServer);
				int a = 0;
				
				System.out.println("等待pop服务器连接");
				
				if (login.popConnect()) {
					System.out.println("pop服务器连接成功");
					System.out.println("等待smtp服务器连接");
					if (login.smtpConnect()) {
						System.out.println("smtp服务器连接成功");
					} else {
						System.out.println("smtp服务器连接失败");
						a++;
					}
				} else {
					System.out.println("pop服务器连接失败");
					a++;
				}
				if (a == 0) {
					System.out.println("服务器连接成功");
					MainPage newJframe=new MainPage();
					newJframe.setVisible(true);
					dispose();
				} else {
					System.out.println("服务器连接失败");
					lblLoader.setText("服务器连接失败......");
					JOptionPane.showMessageDialog(null, "服务器连接失败，请检查账号密码是否填写正确，以及服务器地址是否正确，查看服务器地址请点击设置！");
				}
			} else {
				this.email = email;
				ServerSetting serverSetting = new ServerSetting();
				serverSetting.setVisible(true);
			}
		}

		/*
		 * Session session; Store store; String popServer = "pop.126.com";
		 * 
		 * try { // 设置属性 Properties props = new Properties();
		 * props.put("mail.store.protocol", "pop3"); props.put("mail.imap.class",
		 * "com.sun.mail.imap.IMAPStore"); props.put("mail.pop3.class",
		 * "com.sun.mail.pop3.POP3Store");
		 * 
		 * // 创建Session对象 session = Session.getInstance(props, null);
		 * session.setDebug(false); // 输出跟踪日志
		 * 
		 * // 创建Store对象 store = session.getStore("pop3");
		 * 
		 * // 连接到收邮件服务器 store.connect(popServer, email.getText(), password.getText());
		 * 
		 * } catch (Exception e) {
		 * 
		 * }
		 */
	}
}
