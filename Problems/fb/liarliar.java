import java.io.*;
import java.util.*;

class liarliar{
	public static void main(String[] args){
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			long n = Long.parseLong(br.readLine());
			long c = 0;
			String[] para;
			HashSet<String> set1 = new HashSet<String>();
			HashSet<String> set2 = new HashSet<String>();
			String claimer;
			long numClaimees;
			HashSet<String> claimerSet = null;
			HashSet<String> claimeesSet = null;
			ArrayList<String> claimees = null;
			
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
					claimerSet.add(claimer);
				}
				
				if(set1.contains(claimer))
					claimerSet = set1;
				if(set2.contains(claimer))
					claimerSet = set2;
				
				if(claimerSet != null){
					claimeesSet = ((claimerSet==set1)?set2:set1);
				}
				
				claimees = new ArrayList<String>();
				String claimee;
				
				while(numClaimees > 0){
					claimee = br.readLine();
					if(claimeesSet  == null){
						if(set1.contains(claimee)){
							claimeesSet = set1;
							claimerSet = set2;
							claimerSet.add(claimer);
						}else if(set2.contains(claimee)){
							claimeesSet = set1;
							claimerSet = set2;
							claimerSet.add(claimer);
						}else{
							claimees.add(claimee);
						}
					}else{
						claimeesSet.add(claimee); 
					}
					--numClaimees;
				}
				
				//Add all claimee in claimees to claimeeSet
				for(int i=0;i<claimees.size();i++){
					claimeesSet.add(claimees.get(i));
				}
				++c;
			}
			
			int s1 = set1.size();
			int s2 = set2.size();
			System.out.print(((s1>s2)?(s1+" "+s2):(s2+" "+s1))+"\n");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
	}

}