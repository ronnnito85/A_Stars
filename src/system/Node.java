package system;


public class Node implements Comparable<Node> {

	private int x;
	private int y;
	private int step, key;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
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

}
