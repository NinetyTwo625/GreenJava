package project_1st_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private ClientGui gui;
	private String msg;
	private String name;

	public final void setGui(ClientGui gui) {
		this.gui = gui;
	}

	public void connect() {
		try {
			socket = new Socket("127.0.0.1", 9999);
			System.out.println("서버 연결됨.");

			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
			output.writeUTF(name);
			System.out.println("클라이언트:메시지 전송완료");

			while (input != null) {
				msg = input.readUTF();
				gui.appendMsg(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClientBackground clientBackground = new ClientBackground();
		clientBackground.connect();
	}

	public void sendMessage(String msg2) {
		try {
			output.writeUTF(msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setName(String name) {
		this.name = name;
	}

}
