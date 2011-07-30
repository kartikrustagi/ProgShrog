import java.io.*;

class dancebattle{
	static int movePos(int x, int y,int n){
		if(!(x<=y)){
			int temp=x;
			x=y;
			y=x;
		}
		return (((x*(2*n-x+1))/2)+(y-x));
	}
	
	static boolean isMove(int[] moveCheck,int prevMove,int n,int player){
		//For x<prevMove
		int i=0;
		boolean flag = false;
		boolean found=false;
		for(i=0;((i<prevMove)&&(!found));i++){
			if(moveCheck[movePos(i,prevMove,n)]==0){
				//make copy of array. Also how will recursive function impact it?
				moveCheck[movePos(i,prevMove,n)]=1;
				flag=true;
				if(isMove(moveCheck,i,n,~player)){
					if(player==1){
						found=true;
						return true;
					}
				}
			}
			moveCheck[movePos(i,prevMove,n)]=0;
		}
		
		for(i=prevMove;((i<n)&&(!found));i++){
			if(moveCheck[movePos(prevMove,i,n)]==0){
				//make copy of array. Also how will recursive function impact it?
				moveCheck[movePos(prevMove,i,n)]=1;
				flag=true;
				if(isMove(moveCheck,i,n,~player)){
					if(player==1){
						found=true;
						return true;
					}
				}
			}
			moveCheck[movePos(prevMove,i,n)]=0;
		}
		
		if(flag&&!found){
			//All cases were win for player 0
			return false;
		}
		
		//No case found => flag==false;
		if(player==0)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n=0;
			n = Integer.parseInt(br.readLine());
			int m = 0;
			m = Integer.parseInt(br.readLine());
			
			//Generate required arrays
			int[] moveCheck = new int[((n*(n+1))/2)];
			//Initialize
			for(int i=0;i<n;i++){
				moveCheck[i]=0; //1/0: Done/NotDone
			}
			//<x><y> (such that x<=y) => (((x*(2*n-x+1))/2)+(y-x)) position in the array
			
			//set already done moves
			String[] para;
			int x=0,y=0;
			while(m>0){
				para = br.readLine().split(" ");
				x=Integer.parseInt(para[0]);
				y=Integer.parseInt(para[2]);
				moveCheck[movePos(x,y,n)]=1;
				m--;
			}
			//1: me, 0: opponent
			if(isMove(moveCheck,y,n,1))
				System.out.print("Win\n");
			else
				System.out.print("Lose\n");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}