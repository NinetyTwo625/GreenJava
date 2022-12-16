//ArrayList와 LinkedList의 처리속도 시간을 비교하는 코드

package Thread1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadTest04 {
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new LinkedList<String>();

		long timeStart;
		long timeEnd;

		timeStart = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			list1.add(0, String.valueOf(i));
		}
		timeEnd = System.nanoTime();
		System.out.println("ArrayList 걸린 시간 : " + (timeEnd - timeStart + "ns"));

		timeStart = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			list2.add(0, String.valueOf(i));
		}
		timeEnd = System.nanoTime();
		System.out.println("LinkedList 걸린 시간 : " + (timeEnd - timeStart + "ns"));
	}

}
