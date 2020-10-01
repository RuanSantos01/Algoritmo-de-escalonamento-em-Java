package desafio;

import java.util.Scanner;

public class RR {

	public static int[] round_robin(int processos[][], int quantum, int qnt_processos) {

		int bt_restante[] = new int[1 * qnt_processos];
		int wt[] = new int[1 * qnt_processos];

		for (int i = 0; i < qnt_processos; i++) {
			bt_restante[i] = processos[i][1];
		}
		int tempo = 0;
		int overhead = 1;

		while (true) {
			boolean finalizados = true;

			for (int i = 0; i < qnt_processos; i++) {
				tempo += overhead;
				
				
				if (bt_restante[i] > 0) {
					finalizados = false;
					if (bt_restante[i] > quantum) {
						tempo += quantum;
						bt_restante[i] -= quantum;
					} else {
						tempo += bt_restante[i];
						wt[i] = tempo - processos[i][1];
						bt_restante[i] = 0;
					}
				}
			}
			if (finalizados == false) {
				break;
			}

		}
		return wt;
	}

	public static int[] turn_around_time(int processos[][], int wt[], int qnt_processos) {
		int tat[] = new int[1 * qnt_processos];
		for (int i = 0; i < qnt_processos; i++) {
			tat[i] = processos[i][1] + wt[i];
		}
		return tat;
	}
	
	public static float average_tat(int tat[], float qnt_processos) {
		float turnaround_time = 0;
		for (float i: tat) {
			turnaround_time+=i;
		}
		return (turnaround_time/qnt_processos);
	}
	
	public static float average_wt(int wt[], float qnt_processos) {
		float waiting_time = 0;
		for (float i : wt) {
			waiting_time += i;
		}
		return(waiting_time/qnt_processos);
	}
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Algoritmo Round Robin");
		System.out.print("Quantidade de processos: ");
		int qnt_processos = entrada.nextInt();
		
		int processos[][] = new int [qnt_processos][2];
		
		int somador = 0;
		
		for (int i = 0; i < qnt_processos; i++) {
			System.out.printf("Arrival Time: ");
			int at = entrada.nextInt();
			
			System.out.printf("Burst Time: ");
			int bt = entrada.nextInt();
			
			processos[somador][0] = at;
			processos[somador][1] = bt;
			
			somador += 1;
		}
		
		
		System.out.print("Informe o Quantum: ");
		int quantum = entrada.nextInt();		
				
		int[] wt = round_robin(processos, quantum, qnt_processos);
		int[] tat = turn_around_time(processos, wt, qnt_processos);
		float avg_tat = average_tat(tat, qnt_processos);
		float avg_wt = average_wt(wt, qnt_processos);
		
		for (int i = 0; i < wt.length; i++) {
			System.out.println("WT: " + wt[i]);
		}
		
		for (int l = 0; l < tat.length; l++) {
			System.out.println("TAT: " + tat[l]);
		}
		
		System.out.println("AVG_TAT: " + avg_tat);
		System.out.println("AVG_WT: " + avg_wt);
		
		System.out.println("| Process | Burst Time | Arrival Time | Waiting Time | Turn-around Time");
		
		for (int proc = 0; proc < processos.length; proc++) {
			System.out.printf( proc + " --- " + processos[proc][1] + " --- " + processos[proc][0] + " --- " + wt[proc] + " --- " + tat[proc]);
			System.out.println("");
		}
		
		System.out.println("Average Waiting Time: " + avg_wt);
		System.out.println("Average Turn-around Time: " + avg_tat);
		
		entrada.close();
	}
}
