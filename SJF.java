package desafio;

import java.util.Scanner;

public class SJF {

	public static int[] waiting_time(int processos[][]) {
		int tempo_servico[] = new int [1 * processos.length];
		int wt[] = new int [1 * processos.length];
		
		for (int i = 1; i < processos.length; i++) {
			tempo_servico[i] = (tempo_servico[i-1] + processos[i-1][1]);
			wt[i] = tempo_servico[i] - processos[i][0];
			if (wt[i] < 0) {
				wt[i] = 0;
			}
		}
		return wt;
	}
	
	public static int[] turn_around_time(int processos[][]) {
		int tat[] = new int [1 * processos.length];
		int[] wt = waiting_time(processos);
		for (int i = 0; i < processos.length; i++) {
			tat[i] = processos[i][1] + wt[i];
		}
		return tat;
	}
	
	public static float average_wt(int processos[][]) {
		float qnt_proc = processos.length;
		float wt = 0;
		for (float n : waiting_time(processos)) {
			wt += n;
		}
		return (wt/qnt_proc);
	}
	
	public static float average_tat(int processos[][]) {
		float qnt_proc = processos.length;
		float tat = 0;
		for (float n : turn_around_time(processos)) {
			tat += n;
		}
		return (tat/qnt_proc);
	}
	
	public static int[][] sjf (int processos[][]) {
		for (int i = 0; i < processos.length; i++) {
			for (int l = 0; l < processos.length-1; l++) {
				if(processos[l][1] > processos[l+1][1]) {
					processos[l] = processos[l+1];
					processos[l+1] =  processos[l];
				}
			}
		}
		return processos;
	}
	
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		
		
		System.out.println("Quantidade de processos: ");
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
		
		int[] wt = waiting_time(processos);
		int[] tat = turn_around_time(processos);
		float avg_tat = average_tat(processos);
		float avg_wt = average_wt(processos);
		
		System.out.println("| Process | Burst Time | Arrival Time | Waiting Time | Turn-around Time");
		
		for (int proc = 0; proc < processos.length; proc++) {
			System.out.printf( proc + " --- " + processos[proc][1] + " --- " + processos[proc][0] + " --- " + wt[proc] + " --- " + tat[proc] + " --- " + (tat[proc]+processos[proc][0]));
			System.out.println("");
		}
		System.out.println("Average Waiting Time: " + avg_wt);
		System.out.println("Average Turn-Around Time: " + avg_tat);
		
		System.out.println("::::::::::::::::::::::::::DEPOIS::::::::::::::::::::::::::");
		
		processos = sjf(processos);
		int[] wt1 = waiting_time(processos);
		int[] tat1 = turn_around_time(processos);
		
		for (int proc = 0; proc < processos.length; proc++) {
			System.out.printf( proc + " --- " + processos[proc][1] + " --- " + processos[proc][0] + " --- " + wt1[proc] + " --- " + tat1[proc] + " --- " + (tat1[proc]+processos[proc][0]));
			System.out.println("");
		}
		System.out.println("Average Waiting Time: " + avg_wt);
		System.out.println("Average Turn-Around Time: " + avg_tat);
		
		entrada.close();
	}
}
