package operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.category;
import entity.point;

public class clustering {

	private List<point> ls;
	private List<category> c;
	private float[][] distance;
	
	public clustering(List<point> ls) {
		this.ls=ls;
		c=new ArrayList<category>();
	}
	
	public void initial() {
		for(int i=0;i<ls.size();i++) {
			point a=ls.get(i);
			category ca=new category();
			ca.add(a);
			c.add(ca);
		}
	}
	
    public float Euclidean(point a,point b) {
		
		int[] aValue=a.getValue();
		int[] bValue=b.getValue();
		
		float sum=0;
		for(int i=0;i<aValue.length;i++) {
			sum+=Math.pow((aValue[i]-bValue[i]), 2);
		}
		
		return (float) Math.sqrt(sum);
		
	}
    //计算类和类之间的最小距离
    public float calMin(category a,category b) {
    	
    	float min=Float.MAX_VALUE;
    	
    	List<point> p1=a.getList();
    	List<point> p2=b.getList();
    	
    	for(int i=0;i<p1.size();i++) {
    		for(int j=0;j<p2.size();j++) {
    			if(Euclidean(p1.get(i),p2.get(j))<=min) {
    				min=Euclidean(p1.get(i),p2.get(j));
    			}
    		}
    	}
    	
    	return min;
    }
    
    //选出要合并的两个类
    public float[] choose(){
    	
    	float min=Float.MAX_VALUE;
    	float[] choose=new float[3];
    	for(int i=0;i<c.size();i++) {
    		for(int j=i+1;j<c.size();j++) {
    			float distance=calMin(c.get(i),c.get(j));
    			if(distance<=min) {
    				min=distance;
    				choose[0]=i;
    				choose[1]=j;
    				choose[2]=min;
    			}
    		}
    	}
		return choose;
    }
    
    //迭代
    public void cluster(float T) {
    	initial();
    	float[] tomerge=choose();
    	
    	while(tomerge[2]<T) {
    		int num1=(int) tomerge[0];
    		int num2=(int) tomerge[1];
    	category a=c.get(num1);
    	category b=c.get(num2);
    	List<point> p=b.getList();
    	
    	for(int i=0;i<p.size();i++) {
    		point c=p.get(i);
    		a.add(c);
    	}
    	
    	c.remove(b);
   
    	tomerge=choose();
    	}
    }
    
    public void printResult() {
		for(int i=0;i<c.size();i++) {
			String name="Z"+(i+1);
			c.get(i).setName(name);
			System.out.println(c.get(i).getName());
			List<point> l=c.get(i).getList();
			for(int j=0;j<l.size();j++) {
				point p=l.get(j);
				System.out.println(Arrays.toString(p.getValue()));
			}

		}
	}
}
