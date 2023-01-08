//로그인을 위한 클래스

package project_1st_12;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField idInputField;
	private JPasswordField pwInputField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // awt에서 관리하는 특별한 스레드 처리 방식
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Login() { // 맨 처음 나오는 로그인 화면
		setTitle("로그인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 400, 250, 170);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel labelID = new JLabel("ID :");
		labelID.setBounds(24, 29, 27, 15);
		panel.add(labelID);

		idInputField = new JTextField();
		idInputField.setBounds(71, 26, 116, 21);
		panel.add(idInputField);
		idInputField.setColumns(10);

		JLabel labelPW = new JLabel("PW :");
		labelPW.setBounds(25, 60, 26, 15);
		panel.add(labelPW);

		pwInputField = new JPasswordField();
		pwInputField.setBounds(71, 57, 116, 21);
		panel.add(pwInputField);

		JButton login = new JButton("로그인"); // DB와 연결하여 로그인 성공여부를 확인
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean loginOk;
					loginOk = DAO.getInstance().loginCheck(idInputField.getText(),
							new String(pwInputField.getPassword()));

					if (loginOk) {
						DAO.getInstance().loginSuccess(idInputField.getText());
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "로그인 실패\n(올바르지 않은 입력입니다)");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		login.setBounds(12, 88, 96, 23);
		panel.add(login);

		JButton join = new JButton("회원가입");
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Join frame = new Join();
				frame.setVisible(true);
			}
		});
		join.setBounds(120, 88, 96, 23);
		panel.add(join);

	}

}