package project_1st_5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea(40, 25);
	private JTextField textField = new JTextField(25);
	private ClientBackground client = new ClientBackground();
	private static String name;

	public ClientGui() {
		add(textArea, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		textField.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200, 100, 400, 600);
		setTitle("클라이언트 부분");

		client.setGui(this);
		client.setName(name);
		client.connect();

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("당신의 닉네임을 설정하세요:");
		name = sc.nextLine();
		sc.close();

		new ClientGui();
	}

	public void actionPerformed(ActionEvent e) {
		String msg = name + ":" + textField.getText() + "\n";
		client.sendMessage(msg);
		textField.setText("");
	}

	public void appendMsg(String msg) {
		textArea.append(msg);
	}

}
