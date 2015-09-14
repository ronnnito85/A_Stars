package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinder {

	private Node currentNode;
	private final int size = 30;
	private Node start;
	private Node end;
	private Node[][] Desk = new Node[size][size];

	public void setEnd(Node end) {
		this.end = end;
	}

	public Node getEnd() {
		return end;
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public ArrayList<Node> neighbor(Node currentNode) {

		ArrayList<Node> findNeighbor = new ArrayList<Node>();
		if ((currentNode.getX() != 0)) { // left move
			findNeighbor.add(Desk[currentNode.getX() - 1][currentNode.getY()]);
		}
		if ((currentNode.getY() != 0)) { // left up
			findNeighbor.add(Desk[currentNode.getY()][currentNode.getY() - 1]);
		}

		if ((currentNode.getX() != size - 1)) { // right move
			findNeighbor.add(Desk[currentNode.getX() + 1][currentNode.getY()]);
		}
		if ((currentNode.getY() != size - 1)) { // down move
			findNeighbor.add(Desk[currentNode.getX()][currentNode.getY() + 1]);
		}

		if ((currentNode.getX() != 0) && (currentNode.getY() != 0)) { // diagonal
																		// left-up
			findNeighbor
					.add(Desk[currentNode.getX() - 1][currentNode.getY() - 1]);
		}
		if ((currentNode.getX() != size - 1)
				&& (currentNode.getY() != size - 1)) { // diagonal left-down
			findNeighbor
					.add(Desk[currentNode.getX() + 1][currentNode.getY() + 1]);
		}

		if ((currentNode.getX() != size - 1) && (currentNode.getY() != 0)) { // diagonal
																				// right-up
			findNeighbor
					.add(Desk[currentNode.getX() + 1][currentNode.getY() - 1]);
		}
		if ((currentNode.getX() != 0) && (currentNode.getY() != size - 1)) { // diagonal
																				// right-down
			findNeighbor
					.add(Desk[currentNode.getX() - 1][currentNode.getY() + 1]);
		}
		return findNeighbor;
	}

	public int distanceManhatan(Node currentNode) {

		int distanceManhatan = Math.abs((currentNode.getX() - end.getX()))
				+ Math.abs((currentNode.getY() - end.getY())); // manhatan
																// formula

		return distanceManhatan;
	}

	public ArrayList<Node> findWay() {
ArrayList<Node>way = new ArrayList<Node>();
		Queue<Node> open = new PriorityQueue<Node>();
		ArrayList<Node> close = new ArrayList<Node>();
		Map<Node, Node> parents = new HashMap<Node, Node>();
		open.add(start);

		// v prioritetna opashka slagam starta
		// obhogdam arraylits-a i go vkarvam v prioritetna opashka
		// class prioritetna opashka, elementite interpretirat interface

		while (!open.isEmpty()) {
			Node current = open.poll();
			int theWay = current.step()+1;
			if(current == end){
				return construct(parents);
			}
			close.add(current);
			ArrayList<Node> neighbors = neighbor(current);
			for (Node neighbour : neighbors) {
				if(close.contains(neighbour)){
					continue;
				}
				
				if(!open.contains(neighbour)||(theWay<neighbour.step())){
					parents.put(neighbour, current);
					if(open.contains(neighbour)){
						open.remove(neighbour);
					}
					neighbour.setStep(theWay);
					neighbour.setKey(neighbour.step() + distanceManhatan(neighbour));
					open.add(neighbour);
				}
			}

		}

		return way;
	}

	private ArrayList<Node> construct(Map<Node, Node> parents) { // 2 -> 1 ; 3 -> 2 ; 4 -> 3
		ArrayList<Node> way = new ArrayList<Node>();
		Node current = end;
		while(!parents.isEmpty()){
			way.add(current);
			current = parents.remove(current); // end=4 -> current=3
		}
		
		Collections.reverse(way);
		return way;
	}
}
