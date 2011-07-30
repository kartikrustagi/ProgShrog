import java.io.*;
import java.util.*;

class ActivitySelect{

	public static class Activity{
		int s;
		int f;
		Activity(){
			s = 0;
			f = 0;
		}
	}
	
	public static class ActivityCompare implements Comparator <Activity>{
		public int compare(Activity a, Activity b){
			return (a.f-b.f);
		}
	}
	
	public static int recSol(ActivitySelect.Activity[] activitySet, int l, int u){
		if(l>=u)
			return 0;
		int max = 0;
		int temp = 0;
		for(int k=(l+1);k<u;k++){
			if((activitySet[k].s >= activitySet[l].f)&&(activitySet[k].f<=activitySet[u].s)){
				temp = recSol(activitySet,l,k)+1+recSol(activitySet,k,u);
			}else{
				continue;
			}
			if(temp>max)
				max = temp;
		}
		return max;
	}
	
	public static int dynamicSol(ActivitySelect.Activity[] activitySet, int T){
		//Open a table
		int[][] actCount = new int[T+2][T+2];
		int[][] solTable  = new int[T+2][T+2];
		int i=0, j=0;
		for(i=0;i<=(T+1);i++){
			for(j=0;j<=(T+1);j++){
				actCount[i][j]=-1;
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
				actCount[k][j]=0;
				k++;
				j++;
			}
		}
		int max,temp,maxL;
		//Now start the calculation part
		for(i=0;i<=T;i++){
			j=(i+1);
			k=0;
			//We start with [k,j] to [?,T+1]
			while(j<=(T+1)){
				max = 0;
				maxL = 0;
				temp = 0;
				for(int l=(k+1);l<j;l++){
					if((activitySet[l].s >= activitySet[k].f)&&(activitySet[l].f<=activitySet[j].s)){
						if((actCount[k][l]==-1)||(actCount[l][j]==-1))
							System.out.println("Paap hui gava: 2");
						temp = actCount[k][l]+1+actCount[l][j];
					}else{
						continue;
					}
					if(temp>max){
						max = temp;
						maxL = l;
					}
				}
			
				actCount[k][j]=max;
				solTable[k][j]=maxL;
				j++;
				k++;
			}
		}
		System.out.println("Activities are: ");
		/*
		for(i=0;i<=(T+1);i++){
			for(j=0;j<=(T+1);j++){
				System.out.print(solTable[i][j]+" ");
			}
			System.out.println();
		}*/
		recSolCreater(actCount,solTable,0,T+1);
		return actCount[0][T+1];
	}
	
	public static void recSolCreater(int[][] actCount,int[][] solTable,int i, int j){
		if(actCount[i][j]==0)
			return;
		System.out.print(solTable[i][j]+" ");
		recSolCreater(actCount,solTable,i,solTable[i][j]);
		recSolCreater(actCount,solTable,solTable[i][j],j);
	}
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int T;
			T = Integer.parseInt(br.readLine()); //Number of activities
			String[] act;
			ActivitySelect.Activity[] activitySet = new ActivitySelect.Activity[T+2]; //Two dummy activities
			activitySet[0] = new ActivitySelect.Activity();
			activitySet[0].s = -20;
			activitySet[0].f = -10;
			activitySet[T+1] = new ActivitySelect.Activity();
			activitySet[T+1].s = 1000;
			activitySet[T+1].f = 1500;
			int i=1;
			while(i<=T){
				act = br.readLine().split(" ");
				activitySet[i] = new ActivitySelect.Activity();
				activitySet[i].s = Integer.parseInt(act[0]);
				activitySet[i].f = Integer.parseInt(act[1]);
				i++;
			}
			//We now have the activity set
			Arrays.sort(activitySet,new ActivityCompare());
			//Array of activities sorted on the basis of finish time
			dynamicSol(activitySet,T);
			//System.out.println(dynamicSol(activitySet,T));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}