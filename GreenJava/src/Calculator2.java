import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//강사님 풀이
public class Calculator2 extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField tf;
	private Panel panel;
	private Button[] btnNum;
	private Button[] btnOp;
	private int operand1, operand2;
	private char op;

	public Calculator2() {
		operand1 = 0;
		operand2 = 0;
		op = '\0';

		frame = new Frame("calculator");
		tf = new TextField("0");
		tf.setEditable(false);
		tf.setFocusable(false);
		panel = new Panel();
		panel.setLayout(new GridLayout(3, 0));
		panel.setBackground(Color.PINK);

		btnNum = new Button[10];
		for (int i = 0; i < btnNum.length; i++) {
			Integer intVar = i;
			btnNum[i] = new Button(intVar.toString());
			panel.add(btnNum[i]);
		}

		char arOp[] = { '+', '-', '*', '/', '=' };
		btnOp = new Button[arOp.length];
		for (int i = 0; i < arOp.length; i++) {
			btnOp[i] = new Button(arOp[i] + "");
			panel.add(btnOp[i]);
		}

		frame.add(tf, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);

		frame.addWindowListener(this);
		for (int i = 0; i < btnNum.length; i++) {
			btnNum[i].addActionListener(this);
		}
		for (int i = 0; i < btnOp.length; i++) {
			btnOp[i].addActionListener(this);
		}

		frame.setSize(400, 400);
		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public static void main(String[] args) {
		new Calculator2();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String act = e.getActionCommand();
		char c = tf.getText().charAt(0);
		if (Character.isDigit(act.charAt(0)) == true) {
			if (c == '0') {
				tf.setText(act);
			} else {
				String s = tf.getText() + act;
				tf.setText(s);
			}
		} else {
			if (act.charAt(0) == '=') {
				operand2 = Integer.parseInt(tf.getText());
				int result = 0;
				switch (op) {
				case '+':
					result = operand1 + operand2;
					tf.setText(result + "");
					break;
				case '-':
					result = operand1 - operand2;
					tf.setText(result + "");
					break;
				case '*':
					result = operand1 * operand2;
					tf.setText(result + "");
					break;
				case '/':
					result = operand1 / operand2;
					tf.setText(result + "");
					break;

				}
			} else {
				operand1 = Integer.parseInt(tf.getText());
				op = act.charAt(0);
				tf.setText("0");
			}
			System.out.println(op + " " + operand1);
		}
	}
}