import java.util.*;
import java.io.*;

class BotTrust{
	
	public static class Button{
		String bot;
		int pos;
		Button(){
			bot=" ";
			pos=0;
		}
	}
	
	public static void main(String[] args){
		ArrayList<Button> blueTar = new ArrayList<Button>();
		ArrayList<Button> oranTar = new ArrayList<Button>();
		ArrayList<Button> tarList = new ArrayList<Button>();
		
		try{
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			int T = Integer.parseInt(br.readLine());
			int t=1;
			int N=0;
			Button tempB;
			String[] temp;
			int i=0,j=0,k=0,tarB=0,tarO=0,posB=0,posO=0;
			boolean hitB=true;
			boolean hitO=true;
			int timer=0;
			boolean lock = false;
			
			while(t<=T){
				temp = br.readLine().split(" "); //Number of buttons
				i=0;
				blueTar = new ArrayList<Button>();
				oranTar = new ArrayList<Button>();
				tarList = new ArrayList<Button>();
				
				N = Integer.parseInt(temp[i++]);
				while(N>0){
					tempB = new Button();
					tempB.bot = temp[i++];
					tempB.pos = Integer.parseInt(temp[i++]);
					tarList.add(tempB);
					if(tempB.bot.equals("B")){
						blueTar.add(tempB);
					}else{
						oranTar.add(tempB);
					}	
					--N;
				}
				k=0;
				i=0;j=0;tarB=0;tarO=0;posB=1;posO=1;
				hitB=true;
				hitO=true;
				timer=0;
				lock = false;
				
				while(true){
					//if((i>=blueTar.size())&&(j>=oranTar.size())&&hitB&&hitO){
					if(k>=tarList.size()){	
						break;
					}else{
						++timer;
					}
					
					if((i<blueTar.size())&&hitB){
						tarB = blueTar.get(i).pos;
						hitB=false;
						++i;
					}
					if((j<oranTar.size())&&hitO){
						tarO = oranTar.get(j).pos;
						hitO = false;
						++j;
					}
					if(!hitB){
						if((posB==tarB)&&(tarList.get(k).bot.equals("B"))){
							//System.out.println(timer+": tarB hit");
							hitB=true;
							lock=true;
							++k;
						}else if(tarB<posB){
							//System.out.println(timer+": tarB decremented");
							--posB;
						}else if(tarB>posB){
							//System.out.println(timer+": tarB incremented");
							++posB;
						}
					}
					if(!hitO){
						if((posO==tarO)&&!lock&&(tarList.get(k).bot.equals("O"))){
							//System.out.println(timer+": tarO hit");
							hitO=true;
							++k;
						}else if(tarO<posO){
							//System.out.println(timer+": tarO decremented");
							--posO;
						}else if(tarO>posO){
							//System.out.println(timer+": tarO incremented");
							++posO;
						}
					}
					lock = false;
				}
				System.out.println("Case #"+t+": "+timer);
				++t;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}