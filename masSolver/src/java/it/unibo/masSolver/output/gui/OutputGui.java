package it.unibo.masSolver.output.gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.*;

import it.unibo.masSolver.output.ShowRoutesButtonHandler;;

public class OutputGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton showRoutes;
	private JScrollPane jScrollPane1;
	private JTextArea textArea;
	private ActionListener buttonHandler;

	public OutputGui(ActionListener buttonHandler) {
		super("Moana Routes Output");
		this.buttonHandler = buttonHandler;
		initComponents();
	}

	public JTextArea getArea(){
		return textArea;
	}
	
	private void initComponents() {
		showRoutes = new JButton("Show Routes");
		textArea = new JTextArea();
		
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		textArea.setColumns(20);
		textArea.setLineWrap(true);
		textArea.setRows(5);
		textArea.setWrapStyleWord(true);

		jScrollPane1 = new JScrollPane(textArea);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		getContentPane().setPreferredSize(new Dimension(600, 600));
		// Create a parallel group for the horizontal axis
		ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		// Create a sequential and a parallel groups
		SequentialGroup h1 = layout.createSequentialGroup();
		ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
		// Add a scroll panel and a label to the parallel group h2
		h2.addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);
		h2.addComponent(showRoutes, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);

		// Add a container gap to the sequential group h1
		h1.addContainerGap();
		// Add the group h2 to the group h1
		h1.addGroup(h2);
		h1.addContainerGap();
		// Add the group h1 to hGroup
		hGroup.addGroup(Alignment.TRAILING, h1);
		// Create the horizontal group
		layout.setHorizontalGroup(hGroup);

		// Create a parallel group for the vertical axis
		ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		// Create a sequential group
		SequentialGroup v1 = layout.createSequentialGroup();
		// Add a container gap to the sequential group v1
		v1.addContainerGap();
		// Add a label to the sequential group v1
		v1.addComponent(showRoutes);
		v1.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		// Add scroll panel to the sequential group v1
		v1.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
		v1.addContainerGap();
		// Add the group v1 to vGroup
		vGroup.addGroup(v1);
		// Create the vertical group
		layout.setVerticalGroup(vGroup);
		
		showRoutes.addActionListener(buttonHandler);
		pack();
	}
}
