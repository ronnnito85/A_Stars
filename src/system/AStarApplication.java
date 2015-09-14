package system;

import javax.swing.JFrame;

public class AStarApplication extends JFrame {

	private static final long serialVersionUID = -3485438573401217240L;

	public AStarApplication() {
		super.add(new BoardUI());  // kazvame na app-a da raboti s BoardUI

		setResizable(false);
		super.pack();

		setTitle("A* vizualization");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		AStarApplication app = new AStarApplication();
		app.setVisible(true);
	}
}
