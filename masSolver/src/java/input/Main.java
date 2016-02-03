package input;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import input.gui.OrdersGui;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String[] labels = { "Description:", "Client Description:", "Client Id:", "Capacity:" ,"Longitude:", "Latitude:" };
		char[] mnemonics = { 'C', 'D', 'I', 'Q','O', 'A' };
		int[] widths = { 15, 15, 3, 3, 3, 3 };
		String[] descs = { "Description:", "Client Description:", "Client Id:", "Capacity:" ,"Longitude:", "Latitude:" };

		final OrdersGui form = new OrdersGui(labels, mnemonics, widths, descs);

		JButton submit = new JButton("Submit Order");

//		submit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(
//						form.getText(0) + " " + form.getText(1) + ". " + form.getText(2) + ", age " + form.getText(3));
//			}
//		});

		JFrame f = new JFrame("Order Submission");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(form, BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.add(submit);
		f.getContentPane().add(p, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}
}
