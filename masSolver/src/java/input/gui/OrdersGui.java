package input.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.moana.core.infrastructure.domainEvents.IBus;
import it.unibo.moana.messages.factories.UpdateOrderCommandFactory;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;

public class OrdersGui extends JPanel {

  private static JTextField[] fields;

  // Create a form with the specified labels, tooltips, and sizes.
  public OrdersGui(String[] labels, char[] mnemonics, int[] widths, String[] tips) {
    super(new BorderLayout());
    JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
    JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
    add(labelPanel, BorderLayout.WEST);
    add(fieldPanel, BorderLayout.CENTER);
    fields = new JTextField[labels.length];

    for (int i = 0; i < labels.length; i += 1) {
      fields[i] = new JTextField();
      if (i < tips.length)
        fields[i].setToolTipText(tips[i]);
      if (i < widths.length)
        fields[i].setColumns(widths[i]);

      JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
      lab.setLabelFor(fields[i]);
      if (i < mnemonics.length)
        lab.setDisplayedMnemonic(mnemonics[i]);

      labelPanel.add(lab);
      JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
      p.add(fields[i]);
      fieldPanel.add(p);
    }
  }

  public String getText(int i) {
    return (fields[i].getText());
  }
  
  /**
	 * Launch the application.
	 */
	public static void LaunchGUI(final IBus eventBus) {
		String[] labels = { "Description:", "Client Description:", "Client Id:", "Demand:" ,"Longitude:", "Latitude:" };
		char[] mnemonics = { 'C', 'D', 'I', 'Q','O', 'A' };
		int[] widths = { 15, 15, 3, 3, 3, 3 };
		String[] descs = { "Description:", "Client Description:", "Client Id:", "Demand:" ,"Longitude:", "Latitude:" };

		final OrdersGui form = new OrdersGui(labels, mnemonics, widths, descs);

		JButton submit = new JButton("Submit Order");

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateOrderCommand command = UpdateOrderCommandFactory.getInstance(
						fields[0].getText(),
						Double.valueOf(fields[3].getText()),
						fields[2].getText(),
						fields[1].getText(),
						Double.valueOf(fields[4].getText()),
						Double.valueOf(fields[5].getText()));
				try {
					eventBus.Send(command);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});

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

