package Thread1;

public class ThreadTest02 {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println("뽀");
			try {
				Thread.sleep(500); //0.5초씩 쉬면서 뽀를 5번 출력
			} catch (Exception e) {
			}
		}
		for (int i = 0; i < 5; i++) {
			System.out.println("잉"); //0.5초씩 쉬면서 잉을 5번 출력
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}

}
