package operation;

import java.util.List;

import entity.point;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName=args[0];
		List<point> ls=readData.getData(fileName);
		float T=(float) Math.sqrt(Float.parseFloat(args[1]));
		clustering c=new clustering(ls);
		c.cluster(T);
		c.printResult();
	}

}
