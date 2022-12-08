//메모장 구현1

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Memo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextArea area = new JTextArea();
	JFileChooser chooser = new JFileChooser();
	JMenuBar bar = new JMenuBar();

	public Memo() {
		this.setTitle("Memo");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] s = { "파일", "편집", "서식", "보기", "도움말" };
		JMenu[] m = new JMenu[10];
		for (int i = 0; i < s.length; i++) {
			m[i] = new JMenu(s[i]);
			bar.add(m[i]);
		}
		String[] s1 = { "새파일", "열기", "저장", "다른이름으로저장", "종료" };
		JMenuItem[] m1 = new JMenuItem[5];
		for (int i = 0; i < s1.length; i++) {
			m1[i] = new JMenuItem(s1[i]);
			this.setJMenuBar(bar);
			m[0].add(m1[i]);
		}
		
		this.add(area);

		this.setVisible(true);

	}

	public static void main(String[] args) {
		new Memo();
	}

}
