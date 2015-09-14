package system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.JPanel;

public class BoardUIHB extends JPanel {
	
	private static final long serialVersionUID = 8149420369375386504L;
	
	private static final int SQUARE_SIZE = 30;
	private static final int BORDER = 2;
	public static int WIDTH = 800;
	public static int HEIGHT = 800;

	private static char[][] createCharDefinition(String[] boardDefinition) {
		char[][] result = new char[boardDefinition.length][boardDefinition[0].length()];
		for (int row = 0; row < boardDefinition.length; row++) {
			for (int col = 0; col < boardDefinition[row].length(); col++) {
				result[row][col] = boardDefinition[row].charAt(col);
			}
		}
		return result;
	}

	private static Node[][] createBoard(char[][] boardDefinition) {
		Node[][] result = new Node[boardDefinition.length][boardDefinition[0].length];
		for (int row = 0; row < boardDefinition.length; row++) {
			for (int col = 0; col < boardDefinition[row].length; col++) {
				result[row][col] = new Node(row, col, boardDefinition[row][col] == '#');
			}
		}
		return result;
	}
	
	private char[][] definition;
	private void setBoard(char[][] boardDefinition) {
		this.definition = boardDefinition;
		Node[][] board = createBoard(boardDefinition);
		PathFinder finder = new PathFinder(board);
		List<Node> result = finder.findWay();
		
		System.out.println("The length of the shortest path is: " + (result.size() + 1));
		
		for (Node node : result) {
			this.definition[node.getX()][node.getY()] = 'p';
		}
		this.definition[0][0] = 's';
		this.definition[this.definition.length - 1][this.definition[0].length - 1] = 'e';
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());

		for (int row = 0; row < definition.length; row++) {
			for (int col = 0; col < definition[0].length; col++) {
				RoundRectangle2D.Double square = new RoundRectangle2D.Double(
						col * (SQUARE_SIZE + 2 * BORDER) + BORDER,
						row * (SQUARE_SIZE + 2 * BORDER) + BORDER,
						SQUARE_SIZE,
						SQUARE_SIZE,
						BORDER,
						BORDER);
				switch (definition[row][col]) {
				case '.':
					g2.setPaint(new Color(69, 65, 60));
					break;
				case '#':
					g2.setPaint(new Color(179, 177, 175));
					break;
				case 'p':
					g2.setPaint(new Color(255, 136, 0));
					break;
				case 's':
					g2.setPaint(Color.RED);
					break;
				case 'e':
					g2.setPaint(Color.GREEN);
					break;
				}
				g2.fill(square);
			}
		}
	}

	public BoardUIHB() {
		setBoard(createCharDefinition(Board.HACKBULGARIA));
		// setBoard(Board.SIMPLE);
		setPreferredSize(new Dimension(
				definition[0].length * (SQUARE_SIZE + 2 * BORDER),
				definition.length * (SQUARE_SIZE + 2 * BORDER)));
	}
}
