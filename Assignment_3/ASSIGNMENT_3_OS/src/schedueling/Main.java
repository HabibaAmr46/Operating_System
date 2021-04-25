package schedueling;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		int n;
       System.out.println("1- SJF \n 2-RR \n 3-Priority \n 4-Multilevel");
       n=input.nextInt();
       if(n==1)
    	   SJF.main(args);
       else if(n==2)
    	   RoundRobi.main(args);
       else if(n==3)
    	   Priority.main(args);
       else if(n==4)
    	   MultiLevel.main(args);
       
	}

}
