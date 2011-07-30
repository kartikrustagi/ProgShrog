import java.io.*;
import java.util.*;

class liarliar{

	public static class BackLog{
		String claimer;
		ArrayList<String> claimees;
		BackLog(String x, ArrayList<String> y){
			claimer = x;
			claimees = y;
		}
	}
	
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			long n = Long.parseLong(br.readLine());
			System.out.println("Total: "+n);
			long c = 0;
			String[] para;
			HashSet<String> set1 = new HashSet<String>();
			HashSet<String> set2 = new HashSet<String>();
			String claimer;
			long numClaimees;
			HashSet<String> claimerSet = null;
			HashSet<String> claimeesSet = null;
			ArrayList<String> claimees = null;
			ArrayList<BackLog> backLog = new ArrayList<BackLog>();
			
			while (c<n){
				para = br.readLine().split("\\s+"); 
				claimer = para[0];
				numClaimees = Long.parseLong(para[1]);
				
				//Can we identify set of Claimees?
				claimerSet = null;
				claimeesSet = null;
				if(c==0){
					//1st run
					claimerSet = set1;
					claimeesSet = set2;
					claimerSet.add(claimer);
				}else{
					if(set1.contains(claimer))
						claimerSet = set1;
					if(set2.contains(claimer))
						claimerSet = set2;
					if(claimerSet != null){
						claimeesSet = ((claimerSet==set1)?set2:set1);
					}
				}
				
				
				//System.out.println(numClaimees);
				claimees = new ArrayList<String>();
				String claimee;
				
				while(numClaimees > 0){
					claimee = br.readLine();
					//System.out.println(claimee);
					if(claimeesSet  == null){
						if(set1.contains(claimee)){
							claimeesSet = set1;
							claimerSet = set2;
							//System.out.println("crashed 1");
							claimerSet.add(claimer);
						}else if(set2.contains(claimee)){
							claimeesSet = set2;
							claimerSet = set1;
							//System.out.println("crashed 2");
							claimerSet.add(claimer);
						}else{
							//System.out.println("crashed 3");
							claimees.add(claimee);
						}
					}else{
						//System.out.println("crashed 4");
						claimeesSet.add(claimee); 
					}
					--numClaimees;
				}
				
				//Add all claimee in claimees to claimeeSet
				//WHAT IF BOTH SETS ARE STILL UNKNOWN: STORE FOR FUTURE
				if(claimerSet == null){
					//Both will be null
					backLog.add(new BackLog(claimer, claimees));
				}else{
					for(int i=0;i<claimees.size();i++){
					//System.out.println("crashed 4: "+c);
					//if(claimerSet == null)
						//System.out.println("Cause 4");
					claimeesSet.add(claimees.get(i));
					//System.out.println("crashed 4: complete");
					}
				}
				
				//System.out.println("crashed 5");
				++c;
			}
			BackLog temp;
			for(int i=0;i<backLog.size();i++){
				temp = backLog.get(i);
				claimer = temp.claimer;
				if(set1.contains(claimer))
					claimerSet = set1;
				if(set2.contains(claimer))
					claimerSet = set2;
				if(claimerSet != null)
					claimeesSet = ((claimerSet==set1)?set2:set1);
				claimees = temp.claimees;
				claimerSet.add(claimer);
				if(claimees != null){
					for(int j=0;j<claimees.size();j++){
						claimeesSet.add(claimees.get(j));
					}
				}
			}
			int s1 = set1.size();
			int s2 = set2.size();
			//System.out.println("crashed 6");
			System.out.print(((s1>s2)?(s1+" "+s2):(s2+" "+s1))+"\n");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
	}

}