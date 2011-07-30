import java.io.*;
import java.util.*;

class QuickSort{

	public static void quickSort(int[] arr, int l, int h){
		//pivot is l
		if(l>=h)
			return;
			
		int x = arr[l];
		int i = l;
		for(int j = (i+1);j<=h;j++){
			if(arr[j]<=x){
				i+=1;
				int temp = arr[j];
				arr[j]=arr[i];
				arr[i]=temp;
			}
		}
		int temp = arr[i];
		arr[i]=x;
		arr[l]=temp;
		quickSort(arr,l,i-1);
		quickSort(arr,i+1,h);
	}
	
	public static void display(int[] arr, int l, int h){
		for(int i=l;i<=h;i++)
			System.out.println(arr[i]);
	}
		
	public static void main(String[] args){
		int[] arr = {2,5,1,7,3,6,4,9,1,6,9,3};
		quickSort(arr,0,(arr.length-1));
		display(arr,0,(arr.length-1));
	}
}