import java.io.*;
import java.util.*;

class gattaca{
	
	public static class Pos{
		int x;
		int y;
		int w;

		Pos(){
			x = 0;
			y = 0;
			w = 0;
		}
	}
	
	public static class PosCompare implements Comparator <Pos>{
		public int compare(Pos a, Pos b){
			int temp =(a.x-b.x);
			if(temp==0)
				return (a.y-b.y);
			else
				return temp;
		}
	}
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int n;
			n = Integer.parseInt(br.readLine()); //Length of DNA string
			String dnaStr = "";
			int i=(n/80);
			i=((n%80==0)?i:(i+1));
			while(i>0){
				br.readLine();
				i--;
			}
			int g = Integer.parseInt(br.readLine());//number of gene patterns
			//At max 2*g unique positions
			int[] posScore = new int[2*g];
			//Initialize
			for(i=0;i<2*g;i++){
				posScore[i]=0;
			}
			//Start filling in posSet
			i=1;
			int x=0,y=0,j=-1,w=0;
			String[] genePara;
			gattaca.Pos[] posSet = new gattaca.Pos[2*g];
			while(i<=g){
				genePara = br.readLine().split("\\s+");
				x=Integer.parseInt(genePara[0]);
				y=Integer.parseInt(genePara[1]);
				w=Integer.parseInt(genePara[2]);
				posSet[++j] = new gattaca.Pos();
				posSet[j].x=x;
				posSet[j].y=y;
				posSet[j].w=w;
				posSet[++j] = new gattaca.Pos();
				posSet[j].x=y;
				posSet[j].y=x;
				posSet[j].w=w;
				i++;
			}
			//Sort genePos on the basis of x
			
			Arrays.sort(posSet,new PosCompare());
			
			//Calculate
			for(i=1;i<2*g;i++){
				if(posSet[i].y<=posSet[i].x){
					//Right end of the gene interval
					//Find the position of posSet[i].y in posSet(x's)
					Pos temp = new Pos();
					temp.x = posSet[i].y;
					temp.y = posSet[i].x;
					j = Arrays.binarySearch(posSet,temp,new PosCompare());
					w=posSet[i].w;
					//System.out.println("i:"+(i+1)+" j:"+(j+1));
					posScore[i]=(((posScore[j]+w)>posScore[i-1])?(posScore[j]+w):posScore[i-1]);
				}else{
					posScore[i]=posScore[i-1];
				}
			}
		
			System.out.println(posScore[(2*g)-1]);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}