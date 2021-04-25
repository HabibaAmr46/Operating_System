package schedueling;

import java.util.Scanner;


public class Priority {
	   int pid;
	    int arrival_time;
	    int burst_time;
	    int priority;
	    int start_time;
	    int completion_time;
	    int turnaround_time;
	    int waiting_time;
	    int response_time;
	    static float avg_turnaround_time;
	    static float avg_waiting_time;
	    static int total_turnaround_time = 0;
	    static int total_waiting_time = 0;
	    static int total_idle_time = 0;
	    static int[] burst_remaining = new int[100];
	    static int[] is_completed = new int[100];
	    static int n;
	    static Priority[] p = new Priority[100];

	    Priority() {
	    }

	    Priority(int pid, int arrival_time, int priority, int burst_time) {
	        this.arrival_time = arrival_time;
	        this.burst_time = burst_time;
	        this.pid = pid;
	        this.priority = priority;
	    }
	 static void taking_inputs()
	    {
	    	for (int i = 0; i < is_completed.length; i++) {
	            is_completed[i] = 0;
	        }
	        for (int i = 0; i < burst_remaining.length; i++) {
	            burst_remaining[i] = 0;
	        }
	    	Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter the number of processes: ");
	        n = scanner.nextInt();
	        for (int i = 0; i < n; i++) {
	            Scanner s = new Scanner(System.in);
	            Priority x = new Priority();
	            System.out.println("Enter arrival time of process " + (i + 1) + ": ");
	            x.arrival_time = s.nextInt();
	            System.out.println("Enter burst time of process " + (i + 1) + ": ");
	            x.burst_time = s.nextInt();
	            System.out.println("Enter priority of the process " + (i + 1) + ": ");
	            x.priority = s.nextInt();
	            x.pid = i + 1;
	            burst_remaining[i] = x.burst_time;
	            System.out.println("\n");
	           p[i] = x;
	        }
	    }
	    static void priority_scheduling()
	    {
	        int current_time = 0;
	        int completed = 0;
	        int prev = 0;
	        while (completed != n) {
	            int idx = -1;
	            int mx = 100;
	            for (int i = 0; i < n; i++) {
	                if (p[i].arrival_time <= current_time && is_completed[i] == 0) {
	                    if (p[i].priority < mx) {
	                        mx = p[i].priority;
	                        idx = i;
	                    }
	                    if (p[i].priority == mx) {
	                        if (p[i].arrival_time < p[idx].arrival_time) {
	                            mx = p[i].priority;
	                            idx = i;
	                        }
	                    }
	                }
	            }
	            if (idx != -1) {
	                if (burst_remaining[idx] == p[idx].burst_time)
	                {
	                    p[idx].start_time = current_time;
	                    total_idle_time += p[idx].start_time - prev;
	                }
	                burst_remaining[idx] -= 1;
	                current_time++;
	                prev = current_time;
	                if (burst_remaining[idx] == 0)
	                {
	                    p[idx].completion_time = current_time;
	                    p[idx].turnaround_time = p[idx].completion_time - p[idx].arrival_time;
	                    p[idx].waiting_time = p[idx].turnaround_time - p[idx].burst_time;

	                    total_turnaround_time += p[idx].turnaround_time;
	                    total_waiting_time += p[idx].waiting_time;
	                    is_completed[idx] = 1;
	                    completed++;
	                }
	            } else {
	                current_time++;
	            }
	        }

	       
	    }
	    static void printing_table()
	    {
	    	 avg_turnaround_time = (float) total_turnaround_time / n;
	         avg_waiting_time = (float) total_waiting_time / n;

	         System.out.println("P\t\t\t" + "Arrival Time" + "\t\t" + "Burst Time\t\t" +
	                 "Priority\t\t" + "Start Time\t\t" + "Compination Time\t\t"
	                 + "Turn Around Time\t\t" + "Waiting Time\t\t\t" + "\n");

	         for (int i = 0; i <n; i++) {
	             System.out.println(p[i].pid + "\t\t\t\t" + p[i].arrival_time + "\t\t\t"
	                     + p[i].burst_time + "\t\t" + p[i].priority + "\t\t\t" +
	                     p[i].start_time + "\t\t\t\t" + p[i].completion_time + "\t\t\t\t"
	                     + p[i].turnaround_time + "\t\t\t\t"
	                     + p[i].waiting_time + "\t\t\t\t" + "\n");
	         }
	         System.out.println("Average Turnaround Time = " + avg_turnaround_time);
	         System.out.println("Average Waiting Time = " + avg_waiting_time);
	    }
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
		
	    	taking_inputs();
	    	priority_scheduling();
	    	printing_table();

		}
	
}
