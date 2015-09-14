package system;

import javax.swing.JFrame;

public class TheStarC extends JFrame {

	private static final long serialVersionUID = -3485438573401217240L;

	public TheStarC() {
		super.add(new BoardUIHB());  // kazvame na app-a da raboti s BoardUI

		setResizable(false);
		super.pack();

		setTitle("A* vizualization");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		TheStarC app = new TheStarC();
		app.setVisible(true);
	}
}
