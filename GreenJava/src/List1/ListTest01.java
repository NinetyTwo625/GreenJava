package List1;

import java.util.ArrayList;
import java.util.List;

public class ListTest01 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>(); //list인스턴르를 생성.

		list.add("Java");
		list.add("JDBC");
		list.add("Servlet/JSP");
		list.add(2, "Database"); //2번 인텍스에 Database문자열을 넣음
		list.add("iBATTIS");

		int size = list.size();
		System.out.println("총 객체수 : " + size);

		String skill = list.get(2); //2번 인덱스에 있는 값을 꺼내온다
		System.out.println("2:" + skill);
		System.out.println();

		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			System.out.println(i + ":" + str);
		}
		System.out.println();

		list.remove(2); //2번 인덱스에 있는 값을 삭제
		list.remove("iBATTIS");
		list.remove(2);

		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			System.out.println(i + ":" + str);
		}
		System.out.println();
	}

}
