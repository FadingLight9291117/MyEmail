package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllor.ReadAndWriteServer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class ServerSetting extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField emailaddress;
	private JTextField popServer;
	private JTextField smtpServer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ServerSetting dialog = new ServerSetting();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ServerSetting() {
		setTitle("");
		Image image = Toolkit.getDefaultToolkit().getImage("src\\img\\logo.png");
		setIconImage(image);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.WHITE); // 设置contentpane背景颜色
		// setUndecorated(true);//去掉顶部栏

		int windowWidth = getWidth(); // 获得窗口宽
		int windowHeight = getHeight();// 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("添加邮箱服务器地址");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setForeground(Color.BLACK);
		label.setBounds(125, 22, 180, 24);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("邮箱名称:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(52, 80, 63, 15);
		contentPanel.add(label_1);

		JLabel lblPop = new JLabel("POP:");
		lblPop.setFont(new Font("宋体", Font.PLAIN, 14));
		lblPop.setBounds(87, 110, 28, 15);
		contentPanel.add(lblPop);

		JLabel lblSmtp = new JLabel("SMTP:");
		lblSmtp.setFont(new Font("宋体", Font.PLAIN, 14));
		lblSmtp.setBounds(80, 140, 35, 15);
		contentPanel.add(lblSmtp);

		emailaddress = new JTextField();
		emailaddress.setFont(new Font("宋体", Font.PLAIN, 12));
		emailaddress.setBounds(125, 77, 250, 21);
		contentPanel.add(emailaddress);
		emailaddress.setColumns(1);

		popServer = new JTextField();
		popServer.setFont(new Font("宋体", Font.PLAIN, 12));
		popServer.setBounds(125, 107, 250, 21);
		contentPanel.add(popServer);
		popServer.setColumns(1);

		smtpServer = new JTextField();
		smtpServer.setFont(new Font("宋体", Font.PLAIN, 12));
		smtpServer.setText("");
		smtpServer.setBounds(125, 135, 250, 21);
		contentPanel.add(smtpServer);
		smtpServer.setColumns(1);

		if (LoginPage.readAndWriteServer != null) {
			popServer.setText(LoginPage.readAndWriteServer.getPopServer());
			smtpServer.setText(LoginPage.readAndWriteServer.getSmtpServer());
		}

		if (LoginPage.email != null) {
			emailaddress.setText(LoginPage.email);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认");
				okButton.setFont(new Font("宋体", Font.PLAIN, 14));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (emailaddress.getText().toString().isEmpty() || popServer.getText().toString().isEmpty()
								|| smtpServer.getText().toString().isEmpty()) {
							JOptionPane.showMessageDialog(null, "请填写完整");
						} else {
							String server = emailaddress.getText().toString().substring(
									emailaddress.getText().toString().indexOf('@') + 1,
									emailaddress.getText().toString().length());
							if (new ReadAndWriteServer().queryEmailServer(server)) {
								new ReadAndWriteServer().deleteJson(server);
							}
							new ReadAndWriteServer().addJson(server, popServer.getText().toString(),
									smtpServer.getText().toString());
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK"); //设置代号，以便以后统一写按钮点击事件
				okButton.setFocusable(false); // 去掉焦点框
				okButton.setBackground(Color.white);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);// 设置默认按钮
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.setFont(new Font("宋体", Font.PLAIN, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				cancelButton.setFocusable(false); // 去掉焦点框
				cancelButton.setBackground(Color.white);
				buttonPane.add(cancelButton);
			}
		}
	}
}
