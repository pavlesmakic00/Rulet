package v1;

public class Generator {

	public int generisi(int x,int y) {
		
		return (int)(x+Math.random()*(y-x+1));
	}
	public static void main(String[] args) {
		Generator g=new Generator();
		for (int i  = 0;i<100;i++)
		System.out.println(g.generisi(2,6));
	}
}
