package algoritmo_escalonamento;

import java.util.Scanner;

/*First come first Served - nao preemptiva, metodo onde o primeiro processo a ser executado sera o primeiro a ser terminado*/
public class FCFS {
	
	/*Funcao que calcula a diferenca entra o tempo de rotacao e o Burst Time*/
	public static int[] waiting_time(int processos[][]) {
		int tempo_servico[] = new int [1 * processos.length];
		tempo_servico[0] = 0;
		
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
	
	/*Funcao que calcula a diferenca entre a hora de conclusao e a hora de chegada dos processos*/
	public static int[] turn_around_time(int processos[][]) {
		int tat[] = new int [1 * processos.length];
		int[] wt = waiting_time(processos);
		for (int i = 0; i < processos.length; i++) {
			tat[i] = processos[i][1] + wt[i];
		}
		return tat;
	}
	
	/*Funcao que calcula a media do tempo de espera(Waiting time)*/
	public static float average_wt(int processos[][]) {
		float qnt_proc = processos.length;
		float wt = 0;
		for (float n : waiting_time(processos)) {
			wt += n;
		}
		return (wt/qnt_proc);
	}
	
	/*Funcao que calcula a media do tempo que os processos chegam ao cliente*/
	public static float average_tat(int processos[][]) {
		float qnt_proc = processos.length;
		float tat = 0;
		for (float n : turn_around_time(processos)) {
			tat += n;
		}
		return (tat/qnt_proc);
	}
	
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Qnt de processos: ");
		int qnt = entrada.nextInt();
		
		int processos[][] = new int[qnt][2];
		
		int somador = 0;
		
		for (int i = 0; i < qnt; i++) {
			System.out.print("Arrival time: ");
			int at = entrada.nextInt();
		
			System.out.println("Burst time: ");
			int bt = entrada.nextInt();		
		
			processos[somador][0] = at;
			processos[somador][1] = bt;
			somador += 1;
		}
		
		System.out.println("Process --- Burst Time --- Arrival Time --- Waiting Time --- Turn-Around Time --- Completion Time");
		
		int[] wt = waiting_time(processos);
		int[] tat = turn_around_time(processos);
		float avg_wt = average_wt(processos);
		float avg_tat = average_tat(processos);
		
		for (int proc = 0; proc < qnt; proc++) {
			System.out.println(proc + "---" + processos[proc][1] + "---" + processos[proc][0]+ "---" + wt[proc] + "---" + tat[proc] + "---" + (tat[proc]+processos[proc][0]));
		}
		System.out.println("Average Waiting Time: " + avg_wt);
		System.out.println("Average Turn-Around Time: " + avg_tat);
		
		entrada.close();
	}
}
/*Traduzido por: Ruan Christian Pontes dos Santos*/
