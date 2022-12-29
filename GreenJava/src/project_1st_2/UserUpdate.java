//회원 정보 수정을 위한 클래스

package project_1st_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserUpdate extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField tf_UpdatePw;
	private JTextField tf_UpdateUserName;
	private JLabel Id_Updated;
	private JTextField tf_ChangePw;

	public UserUpdate(String id) {
		setTitle("회원 정보 수정 화면");
		setBounds(100, 100, 315, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Id_NewLabel = new JLabel("ID :");
		Id_NewLabel.setBounds(49, 28, 57, 15);
		contentPane.add(Id_NewLabel);

		JLabel Pw_NewLabel = new JLabel("PW :");
		Pw_NewLabel.setBounds(49, 53, 57, 15);
		contentPane.add(Pw_NewLabel);

		tf_UpdatePw = new JTextField();
		tf_UpdatePw.setBounds(130, 50, 116, 21);
		contentPane.add(tf_UpdatePw);
		tf_UpdatePw.setColumns(10);

		JButton btnUpdateOk = new JButton("수정"); //수정하기 버튼
		btnUpdateOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int udateOk = 0;
					udateOk = UserDao.getInstance().userUpdate(Id_Updated.getText(), tf_UpdatePw.getText(),
							tf_ChangePw.getText(), tf_UpdateUserName.getText());
					if (udateOk != 0) {
						JOptionPane.showMessageDialog(null, "수정완료\n(재실행 해주세요)");
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(null, "수정실패\n(비밀번호를 확인해주세요)");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdateOk.setBounds(49, 148, 97, 23);
		contentPane.add(btnUpdateOk);

		JButton btnUpdateBack = new JButton("취소"); //뒤로가기 버튼
		btnUpdateBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnUpdateBack.setBounds(158, 148, 97, 23);
		contentPane.add(btnUpdateBack);

		Id_Updated = new JLabel(id);
		Id_Updated.setBounds(130, 28, 113, 15);
		contentPane.add(Id_Updated);

		JLabel IdNewLabel2 = new JLabel("변경NAME :");
		IdNewLabel2.setBounds(49, 103, 65, 15);
		contentPane.add(IdNewLabel2);

		tf_UpdateUserName = new JTextField();
		tf_UpdateUserName.setBounds(130, 100, 116, 21);
		contentPane.add(tf_UpdateUserName);
		tf_UpdateUserName.setColumns(10);

		JLabel ChangePw = new JLabel("변경PW :");
		ChangePw.setBounds(49, 78, 57, 15);
		contentPane.add(ChangePw);

		tf_ChangePw = new JTextField();
		tf_ChangePw.setBounds(130, 75, 116, 21);
		contentPane.add(tf_ChangePw);
		tf_ChangePw.setColumns(10);
	}

}
