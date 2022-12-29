package project_1st_5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea(40, 25);
	private JTextField textField = new JTextField(25);
	private ServerBackground server = new ServerBackground();

	public ServerGui() throws IOException {

		add(textArea, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		textField.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200, 100, 400, 600);
		setTitle("서버부분");

		server.setGui(this);
		server.setting();

	}

	public static void main(String[] args) throws IOException {
		new ServerGui();
	}

	public void actionPerformed(ActionEvent e) {
		String msg = "서버:" + textField.getText() + "\n";
		System.out.print(msg);
		server.sendMessage(msg);
		textField.setText("");
	}

	public void appendMsg(String msg) {
		textArea.append(msg);
	}

}
