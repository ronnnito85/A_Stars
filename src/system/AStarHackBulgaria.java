package system;

import javax.swing.JFrame;

public class AStarHackBulgaria extends JFrame {

	private static final long serialVersionUID = -3485438573401217240L;

	public AStarHackBulgaria() {
		super.add(new BoardUIHackBulgaria());

		setResizable(false);
		super.pack();

		setTitle("A* vizualization");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		AStarHackBulgaria app = new AStarHackBulgaria();
		app.setVisible(true);
	}
}
