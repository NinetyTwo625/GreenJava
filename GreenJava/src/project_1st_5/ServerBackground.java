package project_1st_5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServerBackground {

	private ServerSocket serverSocket;
	private Socket socket;
	private ServerGui gui;
	private String msg;

	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();

	public final void setGui(ServerGui gui) {
		this.gui = gui;
	}

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap);
		serverSocket = new ServerSocket(9999);

		while (true) {
			System.out.println("서버 대기중...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
			Receiver receiver = new Receiver(socket);
			receiver.start();
		}
	}

	public static void main(String[] args) throws IOException {
		ServerBackground serverBackground = new ServerBackground();
		serverBackground.setting();
	}

	public void addClient(String name, DataOutputStream output) throws IOException {
		sendMessage(name + "님이 접속하셨습니다.");
		clientsMap.put(name, output);
	}

	public void removeClient(String name) {
		sendMessage(name + "님이 나가셨습니다.");
		clientsMap.remove(name);
	}

	public void sendMessage(String msg) {
		Iterator<String> it = clientsMap.keySet().iterator();
		String key = "";

		while (it.hasNext()) {
			key = it.next();
			try {
				clientsMap.get(key).writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Receiver extends Thread {
		private DataInputStream input;
		private DataOutputStream output;
		private String name;

		public Receiver(Socket socket) throws IOException {
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
			name = input.readUTF();
			addClient(name, output);
		}

		public void run() {
			try {
				while (input != null) {
					msg = input.readUTF();
					sendMessage(msg);
					gui.appendMsg(msg);
				}
			} catch (IOException e) {
				removeClient(name);
			}
		}

	}
}
