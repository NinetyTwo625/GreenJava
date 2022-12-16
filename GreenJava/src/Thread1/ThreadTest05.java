package Thread1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ThreadTest05 {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();

		set.add("Java");
		set.add("JDBC");
		set.add("Servle/JSP");
		set.add("Java"); // set <- 중복된 데이터는 출력X, 들어오는 순서는 상관X
		set.add("iBATIS");

		int size = set.size();
		System.out.println("총 객체수: " + size);

		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String element = iterator.next();
			System.out.println("\t" + element);
		}
		set.remove("JDBC");
		set.remove("iBATIS");

		System.out.println("총 객체수: " + set.size());

		iterator = set.iterator();
		while (iterator.hasNext()) {
			String element = iterator.next();
			System.out.println("\t" + element);
		}

		set.clear();
		if (set.isEmpty()) {
			System.out.println("비어 있음");
		}
	}

}
