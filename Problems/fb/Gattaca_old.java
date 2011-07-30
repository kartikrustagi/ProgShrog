import java.io.*;
import java.util.*;

class gattaca{

	public static class Gene{
		int s;
		int f;
		int score;
		Gene(){
			s = 0;
			f = 0;
			score = 0;
		}
	}
	
	public static class GeneCompare implements Comparator <Gene>{
		public int compare(Gene a, Gene b){
			return (a.f-b.f);
		}
	}
	
	public static int recSol(gattaca.Gene[] geneSet, int l, int u){
		if(l>=u)
			return 0;
		int max = 0;
		int temp = 0;
		for(int k=(l+1);k<u;k++){
			if((geneSet[k].s > geneSet[l].f)&&(geneSet[k].f<geneSet[u].s)){
				temp = recSol(geneSet,l,k)+geneSet[k].score+recSol(geneSet,k,u);
			}else{
				continue;
			}
			if(temp>max)
				max = temp;
		}
		return max;
	}
	
	
	public static int dynamicSol(gattaca.Gene[] geneSet, int T){
		//Open a table
		System.out.println("T: "+T);
		int[][] geneScore = new int[T+2][T+2];
		
		int i=0, j=0;
		for(i=0;i<=(T+1);i++){
			for(j=0;j<=(T+1);j++){
				geneScore[i][j]=-1;
			}
		}
		//Filling in the i>=j with os
		int k = 0;
		for(i=(T+1);i>=0;i--){
			j=0;
			k = i;
			//We start with [k,j] to [T+1,?]
			while(k<=(T+1)){
				if(k<j)
					System.out.println("Paap hui gava");
				geneScore[k][j]=0;
				k++;
				j++;
			}
		}
		int max,temp;
		//Now start the calculation part
		for(i=0;i<=T;i++){
			j=(i+1);
			k=0;
			//We start with [k,j] to [?,T+1]
			while(j<=(T+1)){
				max = 0;
				temp = 0;
				for(int l=(k+1);l<j;l++){
					if((geneSet[l].s > geneSet[k].f)&&(geneSet[l].f<geneSet[j].s)){
						if((geneScore[k][l]==-1)||(geneScore[l][j]==-1))
							System.out.println("Paap hui gava: 2");
						temp = geneScore[k][l]+geneSet[l].score+geneScore[l][j];
					}else{
						continue;
					}
					if(temp>max){
						max = temp;
					}
				}
			
				geneScore[k][j]=max;
				j++;
				k++;
			}
		}
		return geneScore[0][T+1];
	}
	
	public static void main(String[] args){
		try{
			/* 
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int n;
			n = Integer.parseInt(br.readLine()); //Length of DNA string
			String dnaStr = "";
			while(dnaStr.length()!=n){
				dnaStr += br.readLine().split("\n")[0];
			}
			int g = Integer.parseInt(br.readLine());//number of gene patterns
			*/
			
			
		
			BufferedReader in = new BufferedReader (new FileReader(args[0]));
			long skip = Long.parseLong(in.readLine());
			skip += Math.ceil(skip/80);
			in.skip(skip);
			StreamTokenizer st = new StreamTokenizer(in);
			st.parseNumbers();


			while(st.ttype != StreamTokenizer.TT_NUMBER) st.nextToken();
			int g = (int)st.nval;
			System.out.println("g: "+g);
			
			
			String[] genePara;
			gattaca.Gene[] geneSet = new gattaca.Gene[g+2]; //Two dummy genes
			geneSet[0] = new gattaca.Gene();
			geneSet[0].s = -20;
			geneSet[0].f = -10;
			geneSet[g+1] = new gattaca.Gene();
			geneSet[g+1].s = 99999998;
			geneSet[g+1].f = 99999999;
			int i=1;
			String temp;
		
			for (i = 1; i <= g; i++) {
			  geneSet[i] = new gattaca.Gene();
			  st.nextToken();
			  geneSet[i].s = ((int)st.nval);
			  st.nextToken();
			  geneSet[i].f = (int)st.nval;
			  st.nextToken();
			  geneSet[i].score = (int)st.nval;
			}
			in.close();
			
			
			
			
			
			/*
			while(i<=g){
				genePara = br.readLine().split("\\s+");
				geneSet[i] = new gattaca.Gene();
				geneSet[i].s = Integer.parseInt(genePara[0]);
				//System.out.println(genePara[0]);
				geneSet[i].f = Integer.parseInt(genePara[1]);
				geneSet[i].score = Integer.parseInt(genePara[2]);
				i++;
			}
			*/
			
	
			//We now have the activity set
			Arrays.sort(geneSet,new GeneCompare());
			//Array of genes sorted on the basis of finish poition
			//dynamicSol(geneSet,T);
			System.out.print(dynamicSol(geneSet,g)+"\n");
			//System.out.print(recSol(geneSet,0,g+1)+"\n");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}