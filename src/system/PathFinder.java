package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinder {

	private Node[][] board;
	private Node start;
	private Node end;
	private int rows;
	private int cols;
	
	public PathFinder(Node[][] board) {
		this.board = board;
		this.rows = board.length;
		this.cols = board[0].length;
		this.start = board[0][0];
		this.end = board[rows - 1][cols - 1];
	}

	public List<Node> findNeighbors(Node currentNode) {
		ArrayList<Node> allNeighbors = new ArrayList<Node>();
		if ((currentNode.getX() != 0)) { // left move
			allNeighbors.add(board[currentNode.getX() - 1][currentNode.getY()]);
		}
		if ((currentNode.getY() != 0)) { // move up
			allNeighbors.add(board[currentNode.getX()][currentNode.getY() - 1]);
		}

		if ((currentNode.getX() != rows - 1)) { // right move
			allNeighbors.add(board[currentNode.getX() + 1][currentNode.getY()]);
		}
		if ((currentNode.getY() != cols - 1)) { // down move
			allNeighbors.add(board[currentNode.getX()][currentNode.getY() + 1]);
		}

		if ((currentNode.getX() != 0) && (currentNode.getY() != 0)) {
			allNeighbors.add(board[currentNode.getX() - 1][currentNode.getY() - 1]); // diagonal left-up
		}
		if ((currentNode.getX() != rows - 1) && (currentNode.getY() != cols - 1)) {
			allNeighbors.add(board[currentNode.getX() + 1][currentNode.getY() + 1]); // diagonal right-down
		}

		if ((currentNode.getX() != rows - 1) && (currentNode.getY() != 0)) {
			allNeighbors.add(board[currentNode.getX() + 1][currentNode.getY() - 1]); // diagonal right-up
		}
		if ((currentNode.getX() != 0) && (currentNode.getY() != cols - 1)) {
			allNeighbors.add(board[currentNode.getX() - 1][currentNode.getY() + 1]); // diagonal left-down
		}
		List<Node> filtered = new ArrayList<>();
		for (Node node : allNeighbors) {
			if (!node.isWall()) {
				filtered.add(node);
			}
		}
		return filtered;
	}

	public int distanceManhatan(Node currentNode) {
		int distanceManhatan = Math.abs((currentNode.getX() - end.getX()))
				+ Math.abs((currentNode.getY() - end.getY())); // Manhatan formula
		return distanceManhatan;
	}

	public ArrayList<Node> findWay() {
		ArrayList<Node> way = new ArrayList<Node>();
		Queue<Node> open = new PriorityQueue<Node>();
		ArrayList<Node> close = new ArrayList<Node>();
		Map<Node, Node> parents = new HashMap<Node, Node>();
		open.add(start);

		// v prioritetna opashka slagame starta
		// obhojdame arraylits-a i go vkarvame v prioritetna opashka

		while (!open.isEmpty()) {
			Node current = open.poll();
			int theWay = current.step() + 1;
			if (current == end) {
				return construct(parents);
			}
			close.add(current);
			List<Node> neighbors = findNeighbors(current);
			for (Node neighbor : neighbors) {
				if (close.contains(neighbor)) {
					continue;
				}

				if (!open.contains(neighbor) || (theWay < neighbor.step())) {
					parents.put(neighbor, current);
					if (open.contains(neighbor)) {
						open.remove(neighbor);
					}
					neighbor.setStep(theWay);
					neighbor.setKey(neighbor.step() + distanceManhatan(neighbor));
					open.add(neighbor);
				}
			}
		}
		return way;
	}

	private ArrayList<Node> construct(Map<Node, Node> parents) { // 2 -> 1 ; 3 -> 2 ; 4 -> 3
		ArrayList<Node> way = new ArrayList<Node>();
		Node current = end;
		while (!current.equals(start)) {
			way.add(current);
			current = parents.get(current); // end=4 -> current=3
		}
		
		Collections.reverse(way);
		return way;
	}
}
