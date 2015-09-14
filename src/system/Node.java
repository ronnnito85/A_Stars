package system;

import java.util.Objects;

public class Node implements Comparable<Node> {

	private int x;
	private int y;
	private int step;
	private int key;
	private boolean isWall;

	public Node(int x, int y, boolean isWall) {
		this.x = x;
		this.y = y;
		this.isWall = isWall;
		// System.out.println("isWall: " + isWall);
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public boolean isWall() {
		return isWall;
	}
	
	public int key() {
		return key;
	}
	
	public void setKey(int step){
		this.key=step;
	}


	public int step() {
		return step;
	}
	
	public void setStep(int step){
		this.step=step;
	}

	@Override
	public int compareTo(Node one) {
		return (key - one.key());
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Node)) {
			return false;
		}
		Node node = (Node) other;
		return x == node.x && y == node.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
