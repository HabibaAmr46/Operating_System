package schedueling;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Process {
	String name;// Process Name
	int pid; // Process ID
	int burst; // Burst Time
	int arrival; // Arrival Time

	public Process(int pid, int bt, int art, String name) {
		this.name = name;
		this.pid = pid;
		this.burst = bt;
		this.arrival = art;
	}
}

public class RoundRobi {

	public static void WaitingTime(Process process[], int wt_time[], int n, int quantum, int completion_time[],
			int context) {
		// copy the value of brusttime array into wt_time array.
		int rem_time[] = new int[n];

		for (int i = 0; i < n; i++) {
			rem_time[i] = process[i].burst;
		}
		int t = 0;
		Queue<Integer> ready = new LinkedList<Integer>();
		int previous = 0;
		ready.add(0);
		boolean remained = false;
		int r = 0;
		System.out.println("Processes Execution Order:");
		while (ready.size() > 0) {
			r = ready.peek();
			remained = false;
			if (rem_time[r] > 0) {
				if (t != 0 && r != previous)
					t += context;
				if ((rem_time[ready.peek()] > quantum)) {
					System.out.println((ready.peek()) + 1);
					t += quantum;
					rem_time[ready.peek()] -= quantum;
					remained = true;

				} else {
					System.out.println((ready.peek()) + 1);
					t += rem_time[ready.peek()];
					rem_time[ready.peek()] = 0;
					completion_time[ready.peek()] = t;
				}

				for (int i = 0; i < n; i++) {
					if (process[i].arrival <= t && !ready.contains(i)) {
						ready.add(i);
					}
				}
				if (remained)
					ready.add(ready.peek());

			}
			previous = ready.poll();
		}

	}

	public static void TurnAroundTime(Process process[], int wt_time[], int n, int tat_time[], int completion_time[]) {
		for (int i = 0; i < n; i++) {
			tat_time[i] = completion_time[i] - process[i].arrival;
			wt_time[i] = tat_time[i] - process[i].burst;

		}

	}

	public static void AvgTime(Process process[], int n, int quantum, int context) {
		int wt_time[] = new int[n];
		int tat_time[] = new int[n];
		int completion_time[] = new int[n];
		WaitingTime(process, wt_time, n, quantum, completion_time, context);
		TurnAroundTime(process, wt_time, n, tat_time, completion_time);
		int total_wt = 0, total_tat = 0;

		System.out.println("Processes " + " Arrival Time\t" + "  Burst time " + " completion time"
				+ " Turn Around Time " + " Waiting time");
		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt_time[i];
			total_tat = total_tat + tat_time[i];
			System.out.println(" " + (i + 1) + "\t\t" + process[i].arrival + "\t\t" + +process[i].burst + "\t "
					+ completion_time[i] + "\t\t" + tat_time[i] + "\t\t " + wt_time[i]);
		}

		System.out.println("Average waiting time = " + (float) total_wt / (float) n);
		System.out.println("Average turn around time = " + (float) total_tat / (float) n);
	}

	public static void main(String[] args) {
	// TODO Auto-generated method stub

		int size, b, a, quantum, context;
		String name = "";
		@SuppressWarnings("resource")
		Scanner n = new Scanner(System.in);
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		size = s.nextInt();
		Process[] proc = new Process[size];
		for (int i = 0; i < size; i++) {
			System.out.println("Enter the process " + (i + 1) + " name:");
			name = n.nextLine();
			System.out.println("Enter the process " + (i + 1) + " burst time:");
			b = s.nextInt();
			System.out.println("Enter the process " + (i + 1) + " arrival time:");
			a = s.nextInt();
			Process p = new Process(i + 1, b, a, name);
			proc[i] = p;
		}
		System.out.println("Enter the quantum switching");
		quantum = s.nextInt();
		System.out.println("Enter the context switching");
		context = s.nextInt();

		AvgTime(proc, proc.length, quantum, context);
	}

}
