package project_1st_12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Delete extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField tf_UpdatePw;
	JLabel Id_Deleteld;
	DAO dao;

	public Delete(String id) {
		setTitle("회원탈퇴 창");
		setBounds(600, 400, 311, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Id_NewLabel = new JLabel("ID :");
		Id_NewLabel.setBounds(49, 48, 57, 15);
		contentPane.add(Id_NewLabel);

		JLabel Pw_NewLabel = new JLabel("PW :");
		Pw_NewLabel.setBounds(49, 98, 57, 15);
		contentPane.add(Pw_NewLabel);

		tf_UpdatePw = new JTextField();
		tf_UpdatePw.setBounds(95, 95, 116, 21);
		contentPane.add(tf_UpdatePw);
		tf_UpdatePw.setColumns(10);

		JButton btnDeleteOk = new JButton("탈퇴");
		btnDeleteOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int deleteOk = 0;
					deleteOk = DAO.getInstance().userDelete(Id_Deleteld.getText(), tf_UpdatePw.getText());
					if (deleteOk != 0) {
						JOptionPane.showMessageDialog(null, "삭제완료");
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(null, "삭제실패");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteOk.setBounds(49, 136, 97, 23);
		contentPane.add(btnDeleteOk);

		JButton btnUpdateBack = new JButton("취소");
		btnUpdateBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnUpdateBack.setBounds(158, 136, 97, 23);
		contentPane.add(btnUpdateBack);

		Id_Deleteld = new JLabel(id);
		Id_Deleteld.setBounds(95, 48, 57, 15);
		contentPane.add(Id_Deleteld);
	}

}