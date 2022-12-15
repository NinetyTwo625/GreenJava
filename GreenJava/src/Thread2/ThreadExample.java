package Thread2;

public class ThreadExample {
	public static void main(String[] args) {
		Runnable beepTask = new BeepTask();
		Thread thread = new Thread(beepTask);
		thread.start();

		for (int i = 0; i < 5; i++) {
			System.out.println("잉");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}

}
