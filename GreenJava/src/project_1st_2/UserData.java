//User의 Data 만들기
//User의 Data에 외부에서 간접적으로 접근할 수 있도록 getter, setter 사용

package project_1st_2;

public class UserData {
	private String id;
	private String pw;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
