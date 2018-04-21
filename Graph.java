import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class Graph extends JFrame {
	public Graph(String eq) {
		add(new Grid(eq));
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {                
				JFrame graph = new Graph(args[0]);   
				graph.setVisible(true);            
			}
		});
	}
}