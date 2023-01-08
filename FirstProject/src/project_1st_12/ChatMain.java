package project_1st_12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private DatagramSocket socket; // User Datagram Protocol
	private DatagramPacket packet; // 수신할 데이터 패킷을 위함

	Date now = new Date(System.currentTimeMillis());
	SimpleDateFormat simple = new SimpleDateFormat("(a hh:mm)");

	private InetAddress address; // 상대방 주소
	private int myPort; // 내 포트번호
	private int yourPort; // 님 포트번호

	String userId;
	String userName;

	/* 화면 구성을 위한 변수들 선언 */
	private JPanel displayPanel; // 받는 패널
	private JPanel inputPanel; // 입력 패널
	private JTextArea display;
	private JTextField input;

	public void getDAO(String id, String name, InetAddress IPaddress, int yourport, int myport) {

		Boolean userA = true;
		if(userA)
		{
			this.yourPort = yourport;
			this.myPort = myport;
		}
		else
		{
			this.yourPort = myport;
			this.myPort = yourport;
		}
		
		
		
		this.setTitle("My GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);

		this.setLayout(new BorderLayout());
		
		
		displayPanel = new JPanel();
		displayPanel.setLayout(new BorderLayout());		
		this.add(displayPanel, BorderLayout.CENTER);
		displayPanel.setBackground(Color.blue);
		
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		this.add(inputPanel, BorderLayout.SOUTH);		
		inputPanel.setBackground(Color.red);
		
		input = new JTextField();
		input.addActionListener(this);
		inputPanel.add(input,BorderLayout.CENTER);
		input.setText("this is input Area");	
		
		display = new JTextArea(11, 30);		
		display.setEditable(false);
		displayPanel.add(display,BorderLayout.CENTER);
		display.setText("this is display area");
		
		
		
		
		//코드 잘짰는데 패널 생성자 타이밍이 안좋았던것같습니다.  

		try {
//			address = InetAddress.getByName(yourIP.getText()); // 상대방의 IP
			address = InetAddress.getByName("127.0.0.1");
			socket = new DatagramSocket(myPort); // 수신할 포트설정

		} catch (Exception e) {
			display.append("ERROR:" + e.getMessage() + "\n");
			System.out.print("ERROR:" + e.getMessage() + "\n");
		}
		
		
		Thread rcv_thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				receive();
				
			}
		});
		rcv_thread.start();
		
		setVisible(true);
		
	}//getDao end
	
	

	public void receive() {
		while (true) { // 수신대기용 무한루프
			try { // 수신 중 에러가 생길 수 있기 때문에 예외처리로
				byte[] bf = new byte[1024]; // 2승수로 잡아서 바이트단위로 보내야함
				packet = new DatagramPacket(bf, bf.length);
				socket.receive(packet); // 소켓에서 수신하면

				display.append("<<" + simple.format(now) + new String(bf) + "\n"); // 수신 데이터는 byte이므로 String으로 변환
			} catch (Exception e) {
				display.append("ERROR(receive):" + e.getMessage());
			}
		} // while
	}// receive Method
	
	

	public void actionPerformed(ActionEvent evt) {

		if (evt.getSource() == input) {
			display.append(">>" + input.getText() + simple.format(now) + "\n");

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

}
