package Thread1;

public class ThreadTest01 {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println("thread");
			try {
				Thread.sleep(1000); //1초 동안 실행을 중지시킴 (2000은 2초)
			} catch (Exception e) {

			}
		}
	}

}
