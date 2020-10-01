package desafio;

public class EDF {
		
		public int hiper_periodo(int processos[][], int qnt){
			int temp = 0;
			for (int i = 1; i < (qnt+1); i ++) {
				if (processos [i][3] > temp) {
					temp = processos[i][3];
				}
			}
			return temp;
		}
		
		public static int escolher_menor_deadline(int processos[][], int qnt, int deadlines[]) {
			int menor_deadline = 10000;
			int escolhido = -1;
			for (int i = 0; i < qnt; i ++) {
				if (deadlines[i] < menor_deadline) {
					menor_deadline = deadlines[i];
					escolhido = i;
				}
			}
			return escolhido;
		}
		
		public static void edf(int processos[][], int qnt) {
			int relogio = 0;
			int deadlines[] = new int [1 * qnt];
			for (int i = 0; i < qnt; i ++) {
				deadlines[i] = processos[i][2];
			}
			int periodos[] = new int [1 * qnt];
			for (int i = 0; i < qnt; i ++) {
				periodos[i] = processos[i][3];
			}
			
			System.out.print("Processos: ");
			for (int i = 0; i < processos.length; i++) {
				for (int l = 0; l < processos[0].length; l++) {
					System.out.print(" {" + processos[i][l] + "}");
				}
			}
			System.out.println("");
			
			System.out.print("Deadlines: ");
			for (int i = 0; i < deadlines.length; i++) {
				System.out.print(" {" + deadlines[i]+ "}");
			}
			System.out.println("");
			
			System.out.print("Periodos");
			for (int i = 0; i < periodos.length; i++) {
				System.out.print(" {" + periodos[i]+ "}");				
			}
			System.out.println("");
			System.out.println("");
			
			int contador[] = new int [1 * qnt];
			
			while (true){
				int escolhido = escolher_menor_deadline(processos, qnt, deadlines);
				System.out.println("Processo escolhido: " + escolhido);
				
				if (periodos[escolhido] >= relogio){
					relogio += processos[escolhido][1];
					System.out.println("Processo: P" + escolhido + " executando...");
					System.out.println("Relogio: " + relogio);
					System.out.println("Burst Time do processo P" + escolhido + ":" + processos[escolhido][1]);
					
					System.out.println("Deadline ANTERIOR ao processo: " + deadlines[escolhido]);
					deadlines[escolhido] += processos[escolhido][3];
					System.out.println("Deadline do processo P" +escolhido+ " Atualzada: " +deadlines[escolhido]);
					
					System.out.println("Periodo ANTERIOR do Processo: " + periodos[escolhido]);
					periodos[escolhido] += processos[escolhido][3];
					System.out.println("Periodo do Processo P"+escolhido+" Atualizado: " +periodos[escolhido]);
					System.out.println();
					contador[escolhido] += 1;
				}
				if (relogio >= 20) {
					break;
				}
			}
			
			for (int i = 0; i < qnt; i ++) {
				System.out.println("O Processo P"+i+" Executou "+contador[i]+" vezes");
			}
		}
		
		public static void main (String[] args) {
		
			int processos[][] = 
				{
		             {0, 3, 7, 20},
		             {1, 2, 4, 5},
		             {2, 2, 8, 10}
				};
			
			int qnt = processos.length;
			
			edf(processos, qnt);
		}
}
