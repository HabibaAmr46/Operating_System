package schedueling;

import java.util.Scanner;
import java.util.Stack;

public class SJF {
	public static Stack<Integer> s=new Stack<Integer>();
	// Method to calculate turn around time 
	public static void TurnAroundTime(Process proc[], int n,  int wt[], int tat[]) 
	{ 
	// calculating TurnAround time by adding 
	// bt[i] + wt[i] 
	for (int i = 0; i < n; i++) 
	tat[i] = proc[i].burst + wt[i]; 
	} 
	//===================================================================
public static void WaitingTime(Process proc[], int n,int wt[],int context) 
{ 
int temp[] = new int[n]; 

// Copy the burst time into temp[] 
for (int i = 0; i < n; i++) 
temp[i] = proc[i].burst; 

int complete = 0, t = 0, min = Integer.MAX_VALUE; 
int shortest = 0, finish_time;
boolean check = false,switched=false; 
// Process until all processes gets 
// completed 
s.push(90);
while (complete != n) { 

// Find process with minimum 
// remaining time among the 
// processes that arrives till the 
// current time` 
	
for (int j = 0; j < n; j++)  
{ 
if ((proc[j].arrival <= t) && (temp[j] < min) && temp[j] > 0) {
min = temp[j]; 
shortest = j;
check = true; 
} 
}
switched=false; 
if(check==false)
{
	t++;
	continue;
}
//System.out.println(proc[shortest].pid);
if(s.peek()!=proc[shortest].pid)
{
	if(t!=0) {
	switched=true;
	}
	s.push(proc[shortest].pid);
	
}

// Reduce remaining time by one 
temp[shortest]--; 

// Update minimum 

min = temp[shortest]; 
if (min == 0) 
min= Integer.MAX_VALUE; 

// If a process gets completely 
// executed 
if(switched)
{
	t++;
	t+=context;
}
else
	t++;

 
if (temp[shortest] == 0) { 
// Increment complete 
complete++; 
check = false; 

// Find finish time of current 
// process 

finish_time=t;

// Calculate waiting time 
wt[shortest] = finish_time- 
    proc[shortest].burst - 
    proc[shortest].arrival; 

if (wt[shortest] < 0) 
wt[shortest] = 0; 
} 
}

s.remove(0);


} 
	//============================================================================
	// Method to calculate average time 
	public static void AvgTime(Process proc[], int n,int context) 
	{ 
	int wt[] = new int[n], tat[] = new int[n]; 
	int  total_wt = 0, total_tat = 0; 

	// Function to find waiting time of all 
	// processes 
	WaitingTime(proc, n, wt,context); 

	// Function to find turn around time for 
	// all processes 
	TurnAroundTime(proc, n, wt, tat); 

	// Display processes along with all 
	// details 
	System.out.println("Execution Order: ");
	for(int i=0;i<s.size();i++)
		System.out.println("P"+s.get(i));
	System.out.println(" ");
	System.out.println("Processes " +
	  " Burst time " + 
	  " Waiting time " + 
	  " Turn around time"); 

	// Calculate total waiting time and 
	// total TurnAround time
	for (int i = 0; i < n; i++) { 
	total_wt = total_wt + wt[i]; 
	total_tat = total_tat + tat[i]; 
	System.out.println(" " + proc[i].name + "\t\t"+
	    + proc[i].burst + "\t\t " + wt[i] 
	    + "\t\t" + tat[i]); 
	} 

	System.out.println("Average waiting time = " + 
	 (float)total_wt / (float)n); 
	System.out.println("Average turn around time = " + 
	  (float)total_tat / (float)n); 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size,b,a,context;
		String name="";
		@SuppressWarnings("resource")
		Scanner n=new Scanner(System.in);
		@SuppressWarnings("resource")
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		size=s.nextInt();
		Process[] proc=new Process[size];
		for(int i=0;i<size;i++)
		{
			System.out.println("Enter the process "+(i+1)+" name:");
			name=n.nextLine();
			System.out.println("Enter the process "+(i+1)+" burst time:");
			b=s.nextInt();
			System.out.println("Enter the process "+(i+1)+" arrival time:");
			a=s.nextInt();
			Process p=new Process(i+1,b,a,name);
			proc[i]=p;
		}
		System.out.println("Enter the context switching");
		context=s.nextInt();

		AvgTime(proc, proc.length,context); 
	}

}
