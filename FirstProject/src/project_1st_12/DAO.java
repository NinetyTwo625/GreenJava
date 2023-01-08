package project_1st_12;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	protected static final int yourport = 10001;
	protected static final int myport = 10002;
	private static DAO userDao = new DAO();
	private static DBConnect connect = DBConnect.getInstance();
	ChatMain cm = new ChatMain();
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	DTO user;
	ArrayList<DTO> userList;

	private DAO() {
	}

	public static DAO getInstance() {
		if (userDao == null) {
			userDao = new DAO();
		}
		return userDao;
	}

	public ArrayList<DTO> select() throws Exception {
		con = connect.getConnection();
		String sql = "SELECT * FROM MEMBER";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		userList = new ArrayList<>();

		while (rs.next()) {
			user = new DTO();
			user.setId(rs.getString("id"));
			user.setPw(rs.getString("pw"));
			user.setName(rs.getString("name"));
			userList.add(user);
		}
		return userList;
	}

	public int insert(String id, String pw, String name) { // 회원가입에 성공하면 발생
		con = connect.getConnection();
		int loginOk;
		try {
			String sql = "INSERT INTO MEMBER VALUES ('" + id + "','" + pw + "','" + name + "')";
			pstmt = con.prepareStatement(sql);
			loginOk = pstmt.executeUpdate();
		} catch (Exception e) {
			return loginOk = 0;
		}
		return loginOk;
	}

	public boolean loginCheck(String id, String pw) throws Exception { // 회원가입한 사람인지 확인
		con = connect.getConnection();
		userList = this.select();
		boolean loginOk = false;
		for (DTO user : userList) {
			if (id.equals(user.getId()) && pw.equals(user.getPw()))
				return loginOk = true;
		}
		return loginOk;
	}

	public void loginSuccess(String id) throws Exception { // 로그인이 성공했을때 발생
		con = connect.getConnection();

		for (DTO user : userList) {
			if (id.equals(user.getId())) {
				String loginName = user.getName();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InetAddress address = InetAddress.getByName("127.0.0.1");
							cm.getDAO(id, loginName, address, yourport, myport);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				break;
			}
		}
	}

	public int userUpdate(String id, String pw, String cPw, String name) throws Exception { // 회원 정보 수정하기
		con = connect.getConnection();
		int update = 0;
		for (DTO user : userList) {
			if (id.equals(user.getId()) && pw.equals(user.getPw())) {
				String sql = "UPDATE MEMBER SET PW = '" + cPw + "', NAME = '" + name + "' WHERE ID = '" + id + "'";
				pstmt = con.prepareStatement(sql);
				update = pstmt.executeUpdate();
				break;
			}
		}
		return update;
	}

	public int userDelete(String id, String pw) throws Exception { // 회원탈퇴 하기
		con = connect.getConnection();
		int delete = 0;
		for (DTO user : userList) {
			if (id.equals(user.getId()) && pw.equals(user.getPw())) {
				String sql = "DELETE FROM MEMBER WHERE ID = '" + id + "'";

				pstmt = con.prepareStatement(sql);
				delete = pstmt.executeUpdate();
				break;
			}
		}
		return delete;
	}

}