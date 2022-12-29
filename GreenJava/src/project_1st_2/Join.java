//UDP 기반1
//회원가입을 위한 클래스

package project_1st_2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Join extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane; // 내용(버튼 텍스트 필드 등을 담을 수 있는 가장 간단한 컨테이너 클래스)
	private JTextField tf_JoinID; // 아이디
	private JTextField tf_JoinName; // 이름
	private JPasswordField pf_JoinPw; // 패스워드

	public Join() {
		setTitle("회원가입 화면");
		setBounds(100, 100, 300, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel labelID = new JLabel("ID :");
		labelID.setBounds(39, 28, 27, 15);
		panel.add(labelID);

		tf_JoinID = new JTextField();
		tf_JoinID.setBounds(96, 25, 116, 21);
		panel.add(tf_JoinID);
		tf_JoinID.setColumns(10);

		JLabel labelPW = new JLabel("PW :");
		labelPW.setBounds(39, 59, 26, 15);
		panel.add(labelPW);

		JLabel labelNAME = new JLabel("NAME :");
		labelNAME.setBounds(39, 90, 45, 15);
		panel.add(labelNAME);

		tf_JoinName = new JTextField();
		tf_JoinName.setBounds(96, 87, 116, 21);
		panel.add(tf_JoinName);
		tf_JoinName.setColumns(10);

		JButton btnJoinOk = new JButton("가입");
		btnJoinOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int loginOk;
				loginOk = UserDao.getInstance().insert(tf_JoinID.getText(), new String(pf_JoinPw.getPassword()),
						tf_JoinName.getText());
				if (loginOk != 0) {
					JOptionPane.showMessageDialog(null, "회원가입 성공");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다");
				}
			}
		});
		btnJoinOk.setBounds(42, 118, 97, 23);
		panel.add(btnJoinOk);

		JButton btnJoinBack = new JButton("취소"); //뒤로가기 버튼
		btnJoinBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnJoinBack.setBounds(148, 118, 97, 23);
		panel.add(btnJoinBack);
		
		pf_JoinPw = new JPasswordField();
		pf_JoinPw.setBounds(96, 56, 116, 21);
		panel.add(pf_JoinPw);

	}

}
