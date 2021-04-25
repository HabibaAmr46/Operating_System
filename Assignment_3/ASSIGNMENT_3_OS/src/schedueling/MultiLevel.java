package schedueling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Processes 
{ 
	String name;//Process Name
	int priority;//process priority
    int pid; // Process ID 
    int burst; // Burst Time 
    int arrival; // Arrival Time 
      
    public Processes(int pid, int bt, int art,String name,int p) 
    { 
    	this.name=name;
        this.pid = pid; 
        this.burst = bt; 
        this.arrival = art; 
        this.priority=p;
    } 
} 
class Round {
	
	public static void WaitingTime(Processes process[],int wt_time[],int n ,int quantum,int completion_time[]){
	       // copy the value of brusttime array into wt_time array.
	      int rem_time[] = new int[n];
	       
	      for(int i=0;i<process.length;i++){
	          rem_time[i]= process[i].burst;
	      }
	      int t=0;
	     Queue<Integer>ready=new LinkedList<Integer>();   
	      ready.add(0);
	     boolean remained=false;
	      int r=0;
	     while(ready.size()>0) {
	    	 r=ready.peek();
	    	 remained=false;
	    	 if(rem_time[r]>0)
	    	 {
	    		 if((rem_time[ready.peek()]>quantum)) {
	    			 t+=quantum;
	    			 rem_time[ready.peek()]-=quantum;
	    			 remained=true; 
	    		 }
	    		 else
	    		 {
	    			 t+=rem_time[ready.peek()];
	   			  	rem_time[ready.peek()]=0;
	   			  	if(process.length>1)
	   			  	completion_time[ready.peek()]=t;
	   			  	else
	   			  	completion_time[3]=completion_time[2]+t;
	    		 }
	    		
	    		 for(int i=0;i<process.length;i++)
	    		 {
	    			 if(process[i].arrival<=t && !ready.contains(i))
	    			 {
	    				 ready.add(i);
	    			 }
	    		 }
	    		 if(remained)
	    			 ready.add(ready.peek());
	    	 }
	    	 ready.poll();
	      }
	     
	  }
	  public static void TurnAroundTime(Processes process[] ,int wt_time[],int n,int tat_time[],int completion_time[]){
	      for(int i=0;i<process.length;i++){
	    	  if(process.length>1)
	    	  { tat_time[i]= completion_time[i]-process[i].arrival;
	          wt_time[i] = tat_time[i]-process[i].burst;}
	          else
	          { tat_time[i+3]= completion_time[i+3]-process[i].arrival;
	          wt_time[i+3] = tat_time[i+3]-process[i].burst;}
	           
	           
	      }
	       
	  }
	  public static void AvgTime(Processes process[],int n,int quantum,int[]com,int[] tat,int[] wait){
	    
	    WaitingTime(process,wait,n,quantum,com);    
	    TurnAroundTime(process,wait,n,tat,com);
	   
	    
	   
	  }

}
 class FCFS {
    int n;
   
    static void Completion_Time(Processes processes[], int waiting_time[], int completion_time[]) {
       
        for (int i = 0; i < processes.length; i++) {
            completion_time[i+2] = completion_time[i+1] + processes[i].burst;
            
        }
    }
    static void TurnAround_Time(Processes processes[], int completionTime[], int tat[]) {

        for (int i = 0; i < processes.length; i++) {
        	System.out.println(completionTime[i+2]+": "+processes[i].arrival);
            tat[i+2]=completionTime[i+2]-processes[i].arrival;
            System.out.println(tat[i+2]);
        }
    }
    static void Waiting_Time(Processes processes[], int tat[],int waiting_time[]) {
        for (int i = 0; i < processes.length; i++)
        {
            waiting_time[i+2]=tat[i+2]-processes[i].burst;
        }
    }
    static void FCFS_Schedueling(Processes processes[], int n,int[]com,int[] tat,int[] wait) {
        
        Completion_Time(processes, wait, com);
        TurnAround_Time(processes, com, tat);
        Waiting_Time(processes, tat,wait);

    }
   
}
public class MultiLevel {
	static Queue<Processes> high=new LinkedList<>();
	static Queue<Processes> low=new LinkedList<>();
	static ArrayList<Processes> ready=new ArrayList<>();
	public static Queue<Processes> pastR=new LinkedList<>();
	static ArrayList<Processes> ready2=new ArrayList<>();
	public static int wt_time[] = new int[4];
	public static int tat_time[] = new int[4];
	public static int  completion_time[] = new int[4];
	public static int wt_time1[] = new int[4];
	public static int tat_time1[] = new int[4];
	public static int  completion_time1[] = new int[4];
	public static int remburst=0;


	public static void Input(Processes[] p, int n,int q) {
		
		Processes[]proc2=new Processes[1];
		
		for(int i=0;i<n;i++) {
			if(p[i].priority==1)
				high.add(p[i]);
			if(p[i].priority==2)
				low.add(p[i]);
		}
		int t=0;
		int f=0;
		
		
		while(high.size()!=0||low.size()!=0){
			boolean check=false;
			
			for(int i=0; i<n;i++) {
				if(p[i].arrival<=t && p[i].priority==1 && !(pastR.contains(p[i]))) {
					ready.add(p[i]);
					//System.out.println(past.contains(p[i]));
					for(int j=0;j<ready.size();j++)
					{ System.out.println(ready.get(j).burst);
					}
					pastR.add(p[i]);
					high.poll();
				}
				
				if(p[i].arrival<=t &&p[i].priority==2 ){
					
					ready2.add(p[i]);
				}
				
			}
			Processes[]proc=new Processes[ready.size()];
				
			for(int i=0;i<ready.size();i++) {
				proc[i]=ready.get(i);	
			}
			
	          if(ready.size()!=0) {
				Round.AvgTime(proc, n, q,completion_time, tat_time,wt_time);
				t+=completion_time[ready.size()-1];
				ready.clear();
				 System.out.println();
				
	          }
	          if(ready.size()==0 ) { 
	      		for(int i=0;i<ready2.size();i++) {
	      			proc2[i]=ready2.get(i);
	      			remburst=proc2[i].burst;
	      			if(i==ready2.size()-1)
	      			  ready2.clear();
	      		}
	      		while(f<=remburst)
	      		{ 

	      		 check=true;
	      		 remburst--;
	      		 f++;
	      		}
	      		if(check==true)
	            {
	      			
	          	  System.out.println("D5alll FCFS");
	          	  System.out.println(f);
	          	  System.out.println(remburst);
	          	  for(int k=0;k<proc2.length;k++)
	          	  { 
	          		  proc2[k].burst-=(f);
	          	  }
	          	  FCFS.FCFS_Schedueling(proc2, proc2.length,completion_time, tat_time,wt_time);
	            }
	          }
	          if(high.size()==0)
	        	  low.poll();
			t++;
		}
	}
	public static void AvgTime(Processes[] process,int n,int quantum){
	    Input( process, n,quantum);
	    int total_wt = 0, total_tat = 0; 
	     
	    System.out.println("Processes " +" Arrival Time\t"+ "  Burst time " +" completion time"+ 
	            " Turn Around Time " + " Waiting time");
	    for (int i=0; i<n; i++) 
	    { 
	        total_wt = total_wt + wt_time[i]; 
	        total_tat = total_tat + tat_time[i]; 
	        System.out.println(" " + (i+1) + "\t\t"+ process[i].arrival+"\t\t"+ + process[i].burst +"\t " +completion_time[i]+"\t\t"
	                          +tat_time[i] +"\t\t " + wt_time[i]); 
	    } 
	    
	    System.out.println("Average waiting time = " + 
	                      (float)total_wt / (float)n); 
	    System.out.println("Average turn around time = " + 
	                       (float)total_tat / (float)n); 
	  }
	public static void main(String []agrs){
	int size,b,pri,a,quantum;
		String name="";
		@SuppressWarnings("resource")
		Scanner n=new Scanner(System.in);
		@SuppressWarnings("resource")
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		size=s.nextInt();
		Processes[] proc=new Processes[size];
		for(int i=0;i<size;i++)
		{
			System.out.println("Enter the process "+(i+1)+" name:");
			name=n.nextLine();
			System.out.println("Enter the process "+(i+1)+" priority ");
			pri=s.nextInt();
			
			System.out.println("Enter the process "+(i+1)+" burst time:");
			b=s.nextInt();
			System.out.println("Enter the process "+(i+1)+" arrival time:");
			a=s.nextInt();
			Processes p=new Processes(i+1,b,a,name,pri);
			proc[i]=p;
		}
		System.out.println("Enter the quantum switching");
		quantum=s.nextInt();
		
		
	 AvgTime(proc,size,quantum);
	 

	     
	}
}
