package apcs.economicsBonus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicalTest extends JPanel implements ActionListener 
{
	private static JFrame graphFrame;
	private static JLabel xAxis;
	private static JLabel yAxis;
	
	
	public static void main(String[] args) 
	{
		GraphicalTest graph = new GraphicalTest();
		graph.setLayout(null);
		graph.addLabels();
		graph.add(xAxis);
		graph.add(yAxis);
		graphFrame          = new JFrame("Curve Graph");
		graphFrame.add(graph);
		graphFrame.setSize(1000, 1000);
		
		//Sets Frame in center of screen
		graphFrame.setLocationRelativeTo(null);
		
		graphFrame.setVisible(true);
		graphFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void addLabels()
	{
		xAxis = new JLabel("Quantity");
		xAxis.setBounds(900, 700, 50, 50);
		
		yAxis = new JLabel("Price");
		yAxis.setBounds(100, 100, 50, 50);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}

}
