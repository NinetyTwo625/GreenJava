//채팅창 구현

package project_1st_2;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Messenger extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private InetAddress inetAddress; // 연결할 포트를 설정하기 위한 변수
	private int port;

	private MulticastSocket Socket; // UDP에서 사용하는 소켓
	private DatagramPacket Packet;
	private DatagramSocket dataSocket;

	String userId;
	String userName;
	private JTextField textField;
	private JTextArea textArea;

	public Messenger(String id, String name, InetAddress ip, int p) throws UnknownHostException, IOException {
		inetAddress = ip;
		port = p;
		userId = id;
		userName = name;

		setTitle("채팅방");
		setBounds(100, 100, 400, 280);

		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		textField = new JTextField();
		textField.setColumns(10);

		JPanel panel2 = new JPanel();
		getContentPane().add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel2.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JPanel panel3 = new JPanel();
		scrollPane.setColumnHeaderView(panel3);
		panel3.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnUpdate = new JButton("정보수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserUpdate update = new UserUpdate(userId); // 회원 정보 수정
				update.setVisible(true);
			}
		});
		panel3.add(btnUpdate);

		JButton btnDelete = new JButton("회원탈퇴");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDelete delete = new UserDelete(userId); // 회원탈퇴
				delete.setVisible(true);
			}
		});
		panel3.add(btnDelete);

		textField.addActionListener(this);
		panel.add(textField);
		this.setVisible(true);

		Runnable r = new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				byte[] buffer = new byte[512];
				try {
					Socket = new MulticastSocket(port);
					Socket.joinGroup(ip);
					dataSocket = new DatagramSocket();
					String connect = "[" + userName + "님이 접속하셨습니다. 종료는 exit ]";
					buffer = connect.getBytes();
					Packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
					Socket.send(Packet);

					while (true) {
						Packet = new DatagramPacket(buffer, buffer.length);
						Socket.receive(Packet);
						String msg = new String(Packet.getData(), 0, Packet.getLength());
						textArea.append(msg + "\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				} finally {
					Socket.close();
				}
			}
		};
		new Thread(r).start();
	}

	public void actionPerformed(ActionEvent evt) {
		String s = textField.getText();
		textField.setText(null);
		byte[] buffer = new byte[512];

		if (s == null || s == "" || s == "\n" || s.length() == 0) {
			return;
		} else if (s.equals("exit")) {
			String connect = "[" + userName + "님이 퇴장하셨습니다.]";
			buffer = connect.getBytes();
			Packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
			try {
				Socket.send(Packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Socket.close();
			System.exit(0);
		}
		try {
			dataSocket = new DatagramSocket();
			String msg = "[" + userName + "]" + s;
			buffer = msg.getBytes();
			Packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
			Socket.send(Packet);
		} catch (IOException e) {
			System.out.println("send 오류");
		} finally {
			dataSocket.close();
		}
	}

}
