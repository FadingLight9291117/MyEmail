package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.dnd.Autoscroll;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllor.EmailIO;
import controllor.ReadAndWriteServer;
import module.Email;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;

public class MainPage extends JFrame {

	public static String email = null;
	public static String password = null;
	public static ReadAndWriteServer readAndWriteServer = null;

	private JPanel east;
	private JPanel west;
	private String FontStyle = "宋体";
	private JTextField txtSearch;
	private JButton btnAdd;
	private JButton btnSearch;
	private JButton btnSyn;
	private JButton btnAccount;
	private JScrollPane scrollPane;
	private JPanel center;
	private JLabel lblHine;
	private JLabel userAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			private String plafName;

			public void run() {

				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					// UIManager.setLookAndFeel(com.sun.java.swing.plaf.windows.WindowsLookAndFeel.class.getName());
					MainPage frame = new MainPage();
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
	public MainPage() {

		email = LoginPage.email;
		password = LoginPage.password;
		readAndWriteServer = LoginPage.readAndWriteServer;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);

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

		east = new JPanel();
		east.setBackground(Color.white); // 设置contentpane背景颜色
		east.setBorder(new EmptyBorder(0, 0, 0, 0));
		east.setPreferredSize(new Dimension(0, 0));
		east.setLayout(null);
		east.setVisible(true);

		west = new JPanel();
		west.setBackground(new Color(30, 144, 255));
		west.setBorder(new EmptyBorder(0, 0, 0, 0));
		west.setPreferredSize(new Dimension(250, 0));
		west.setLayout(null);
		west.setVisible(true);

		getContentPane().add(east, BorderLayout.CENTER);
		getContentPane().add(west, BorderLayout.WEST);

		ImageIcon backGround = new ImageIcon("src\\img\\backEast.jpg");

		txtSearch = new JTextField();
		txtSearch.setBounds(50, 30, 280, 30);
		txtSearch.setColumns(1);

		btnSearch = new JButton("搜索");
		btnSearch.setBounds(340, 30, 60, 30);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("点击了搜索");
			}
		});
		btnSearch.setFont(new Font(FontStyle, Font.PLAIN, 12));
		btnSearch.setBackground(Color.white);
		btnSearch.setFocusable(false);// 除去焦点的框
		// btnSearch.setBorder(null);// 除去边框
		// btnSearch.setContentAreaFilled(false);// 除去默认的背景填充

		ImageIcon imageIcon3 = new ImageIcon("src\\img\\syn.png");
		imageIcon3.setImage(imageIcon3.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		btnSyn = new JButton(imageIcon3);
		btnSyn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("点击了同步");
				EmailIO emailIO = new EmailIO(email, password, readAndWriteServer.getPopServer());
				try {
					emailIO.sync();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("已同步");
				List<Email> list = emailIO.readEmail();
				center.removeAll();
				int i = 0;
				for (Email emaildata : list) {
					String string = (emaildata.getName() == null) ? emaildata.getFrom() : emaildata.getName();
					String lblContent = "<html><br><div><font size='5'> &nbsp;" + string
							+ "</font></div><br><div><font>&nbsp;&nbsp;" + emaildata.getSubject()
							+ "</font></div><br><hr width='1000px'></div></html>";
					JLabel label = new JLabel(lblContent);
					label.setBounds(2, 90 * i, getWidth() * 2 / 3 - 4, 90);
					i++;

					// label.setOpaque(true); // 设置为不透明
					// label.setBackground(Color.WHITE);
					label.addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent e) {
							label.setOpaque(true); // 设置为不透明
							label.setBackground(SystemColor.controlHighlight);
						}

						@Override
						public void mousePressed(MouseEvent e) {
							label.setOpaque(true); // 设置为不透明
							label.setBackground(Color.LIGHT_GRAY);

						}

						@Override
						public void mouseExited(MouseEvent e) {
							label.setOpaque(false); // 设置为透明
							label.setBackground(null);
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							label.setOpaque(true); // 设置为不透明
							label.setBackground(SystemColor.controlHighlight);
						}

						@Override
						public void mouseClicked(MouseEvent e) {

							System.out.println("点击了一封邮件");
							try {
								ReadMailPage read = new ReadMailPage(emaildata.getSubject(), emaildata.getName(),
										emaildata.getFrom(), emaildata.getSendDate(), emaildata.getContent());
								read.setVisible(true);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					center.add(label);
					center.updateUI();
				}
//				lblHine.setVisible(false);
			}
		});
		btnSyn.setBounds(440, 29, 35, 35);
		btnSyn.setBorder(null);
		btnSyn.setFont(new Font(FontStyle, Font.PLAIN, 12));
		btnSyn.setBackground(Color.white);
		btnSyn.setFocusable(false);

		JLabel lblLine = new JLabel(
				"=======================================================================" + "=====");

		lblLine.setBounds(0, 80, getWidth() * 2 / 3 + 2, 10);
		east.add(txtSearch);
		east.add(btnSearch);
		east.add(btnSyn);
		east.add(lblLine);

		JLabel lblSyc = new JLabel("同步");
		lblSyc.setBounds(485, 28, 30, 30);
		lblSyc.setFont(new Font("宋体", Font.PLAIN, 12));
		east.add(lblSyc);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 90, 534, 521);
		scrollPane.setBorder(null);
		east.add(scrollPane);

		center = new JPanel();
		center.setBackground(Color.WHITE);
		scrollPane.setViewportView(center);
		center.setLayout(null);

		ImageIcon imageIcon4 = new ImageIcon("src\\img\\syn.png");
		imageIcon4.setImage(imageIcon4.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		lblHine = new JLabel(" 请同步。。。", imageIcon4, SwingConstants.CENTER);
		lblHine.setFont(new Font(FontStyle, Font.PLAIN, 30));
		lblHine.setBounds(100, 100, 350, 350);
		center.add(lblHine);

		ImageIcon imageIcon = new ImageIcon("src\\img\\plus.png");
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		btnAdd = new JButton(" 新邮件", imageIcon);
		btnAdd.setBounds(0, 10, 250, 50);
		btnAdd.setHorizontalAlignment(JButton.LEFT); // 设置文字左对齐
		btnAdd.setBorder(new EmptyBorder(0, 10, 0, 0)); // 设置文字左间距
		btnAdd.setFocusPainted(false); // 去掉焦点框
		// btnAdd.setBorder(null); //去掉边框
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(30, 144, 255));
		btnAdd.setFont(new Font(FontStyle, Font.CENTER_BASELINE, 20));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("点击了添加邮件按钮");
				writeMailPage writeMail = new writeMailPage(email, password, readAndWriteServer.getSmtpServer());
				writeMail.setVisible(true);
			}
		});

		ImageIcon imageIcon2 = new ImageIcon("src\\img\\account.png");
		imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		btnAccount = new JButton(" 我的账户", imageIcon2);
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("点击了我的账户");
			}
		});
		btnAccount.setBounds(0, 66, 250, 50);
		btnAccount.setHorizontalAlignment(JButton.LEFT); // 设置文字左对齐
		btnAccount.setBorder(new EmptyBorder(0, 10, 0, 0));
		btnAccount.setForeground(Color.white);
		btnAccount.setBackground(new Color(30, 144, 255));
		btnAccount.setFont(new Font(FontStyle, Font.CENTER_BASELINE, 20));
		west.add(btnAccount);
		west.add(btnAdd);

		userAccount = new JLabel(email);
		userAccount.setBounds(20, 130, 210, 15);
		userAccount.setForeground(Color.WHITE);
		userAccount.setFont(new Font(FontStyle, Font.CENTER_BASELINE, 16));
		west.add(userAccount);

	}
}
