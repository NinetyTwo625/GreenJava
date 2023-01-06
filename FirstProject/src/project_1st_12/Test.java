package project_1st_12;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Chatting {

	/* 화면 구성을 위한 변수들 선언 */
	private JPanel displayPanel; // 받는 패널
	private JPanel inputPanel; // 입력 패널
	private JPanel setupPanel;
	private JTextArea display;
	private JTextField input;

	private JTextField myName;
	private JTextField yourName;
	private JTextField yourIP;

	/* 통신을 위한 변수들 선언 */
	private DatagramSocket socket; // User Datagram Protocol
	private DatagramPacket packet; // 수신할 데이터 패킷을 위함
	private InetAddress address; // 상대방 주소

	private int myPort = 10002; // 내 포트번호
	private int yourPort = 10001; // 님 포트번호

	public Chatting() {
		new MyFrame();

		/* socket 설정 */
		try {
			address = InetAddress.getByName(yourIP.getText()); // 상대방의 IP
			socket = new DatagramSocket(myPort); // 수신할 포트설정
		} catch (Exception e) {
			display.append("ERROR:" + e.getMessage() + "\n");
		}

	} // Chatting Constructor

	public void receive() {
		while (true) { // 수신대기용 무한루프
			try { // 수신 중 에러가 생길 수 있기 때문에 예외처리로
				byte[] bf = new byte[1024]; // 2승수로 잡아서 바이트단위로 보내야함
				packet = new DatagramPacket(bf, bf.length);
				socket.receive(packet); // 소켓에서 수신하면

				display.append(yourName.getText() + "<<" + new String(bf) + "\n"); // 수신 데이터는 byte이므로 String으로 변환
			} catch (Exception e) {
				display.append("ERROR(receive):" + e.getMessage());
			}
		} // while
	}// receive Method

	class MyFrame extends JFrame implements ActionListener {

		public MyFrame() {
			this.setTitle("My GUI");
			this.setSize(500, 400);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			displayPanel = new JPanel();
			displayPanel.setLayout(new FlowLayout());
			display = new JTextArea(11, 30);
			Font displayfont = new Font("Serif", Font.BOLD, 20);
			display.setFont(displayfont);
			display.setEditable(false); // display에 임의로 값을 추가할 수 없도록 한다??

			/* textarea를 스크롤이 되도록 변경 */
			JScrollPane scroll = new JScrollPane(display);
			displayPanel.add(scroll);

			inputPanel = new JPanel();
			inputPanel.setLayout(new FlowLayout());
			input = new JTextField(30);
			Font inputFont = new Font("Serif", Font.BOLD, 20);
			input.setFont(inputFont);
			input.addActionListener(this);
			inputPanel.add(input);

			setupPanel = new JPanel();
			setupPanel.setLayout(new FlowLayout());

			setupPanel.add(new JLabel("MyName"));
			myName = new JTextField(5);
			setupPanel.add(myName);
			myName.setText("홍길동");

			setupPanel.add(new JLabel("YourName"));
			yourName = new JTextField(5);
			setupPanel.add(yourName);
			yourName.setText("이순신");

			setupPanel.add(new JLabel("YourIP"));
			yourIP = new JTextField(5);
			setupPanel.add(yourIP);
			yourIP.setText("127.0.0.1");

			/* Frame에 두 개의 panel을 붙이기 */

			this.setLayout(new BorderLayout()); // 판넬에 레이아웃을 세팅할 것이며, 그 레이아웃의 상태는 BorderLayout()가 된다
			this.add(setupPanel, BorderLayout.NORTH);
			this.add(displayPanel, BorderLayout.CENTER);
			this.add(inputPanel, BorderLayout.SOUTH);

			this.setVisible(true);

		}

		public void actionPerformed(ActionEvent evt) {

			if (evt.getSource() == input) {
				display.append(">>" + input.getText() + "\n");

				/* 네트워크로 데이터 전송 */
				// 데이터 전송시 바이트 단위로 전송
				byte[] sendBuffer = input.getText().getBytes(); // input에서 getText를 바이트 단위로 변환해서 배열

				// 전송할 데이터 패킷을 만듬
				DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, yourPort);
				// sendBuffer를 보낼거고, sendBuffer길이만큼 보내줄거고, 상대방 주소에, 상대방 포트(님 포트)로 보내줄거임

				try {
					socket.send(sendPacket); // send시 에러가 생길 수 있기 때문에 예외처리를 해줘야 함
				} catch (Exception e2) {
					display.append("ERROR(send):" + e2.getMessage() + "\n");
				}

				// input.selectAll();
				input.setText("");
			} // if
		} // actionPerformed

	} // MyFrame Class

} // Chatting Class

public class Test {

	public static void main(String[] args) {
		Chatting chat = new Chatting();
		chat.receive();
	} // main

} // Main Class
