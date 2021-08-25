package br.edu.ufersa.largura;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LightsOut_largura {

	static int contador_expandidos = 0;

	public static void main(String[] args) {
		int contador_visitados = 0;
		int tamanhoFila = 0;
		Estado estado = inicio(); // INICIALIZA ESTADO CONFORME ESTADO INICIAL
		Queue<Estado> fila = new LinkedList<Estado>(); // FILA DE ESTADOS
		enfileira(estado, fila);
		Queue<Estado> estadosExpandidos = new LinkedList<Estado>(); // FILA DOS ESTADOS EXPANDIDOS
		while (!filaVazia(fila)) {
			Estado estadoAtual = desenfileira(fila);
			if(estaNosExpandidos(estadoAtual,estadosExpandidos)) {
				continue;
			}
			contador_visitados++;
			if (objetivo(estadoAtual)) {
				System.out.println("Estado objetivo encontrado!");
				mostraCaminho(estadoAtual);
				System.out.println("Total de estados visitados: " + contador_visitados);
				System.out.println("Total de estados expandidos: " + estadosExpandidos.size());
				System.out.println("Total de estados expandidos (via contador): " + contador_expandidos);
				System.out.println(
						"Número máximo de estados na estrutura que guarda estados a serem expandidos:" + tamanhoFila);
				break;
			}
			exibeEstado(estadoAtual);
			Queue<Estado> estadosFilhos = expansao(estadoAtual);
			estadosExpandidos.add(estado); // ADICIONA ESTADO_ATUAL A FILA DOS EXPANDIDOS.
			for (Estado filho : estadosFilhos) {
				if((!estaNoCaminho(filho,estadoAtual))&&(!estadoAberto(filho,fila))) {
					enfileira(filho, fila);
				}
			}
			if (fila.size() > tamanhoFila) {
				tamanhoFila = fila.size();
			}
			System.out.println("tamanhoFila: " + tamanhoFila);
		}
	}

	public static void exibeEstado(Estado s) {
		int mat[][] = s.state;
		int tamanhoMatriz = s.getState().length;
		System.out.println("===========");
		for (int i = 0; i < tamanhoMatriz; i++) {
			for (int j = 0; j < tamanhoMatriz; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===========");
	}

	public static Estado inicio() {
		int mat[][] = { { 0, 0, 1 }, { 1, 0, 0 }, { 1, 1, 1 } };
		Estado inicio = new Estado();
		inicio.setState(mat);
		inicio.setPai(null);
		return inicio;
	}

	public static boolean objetivo(Estado s) {
		int objetivo[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		if (Arrays.deepEquals(s.state, objetivo)) {
			return true;
		}
		return false;
	}

	public static int inverteValor(int val) {
		if (val == 0) {
			return 1;
		}
		return 0;
	}

	public static void operacao(Estado s, int i, int j) {
		int valorPosicao = s.state[i][j];
		s.state[i][j] = inverteValor(valorPosicao);
	}

	public static Queue<Estado> expansao(Estado s) {
		/*
		 * (0,0) (0,1) (0,2) (1,0) (1,1) (1,2) (2,0) (2,1) (2,2)
		 * 
		 * (0,0) -> (0,1) e (1,0) (0,1) -> (0,0) e (0,2) e (1,1) (0,2) -> (0,1) e (1,2)
		 * 
		 * (1,0) -> (0,0) e (1,1) e (2,0) (1,1) -> (0,1) e (2,1) e (1,0) e (1,2) (1,2)
		 * -> (0,2) e (1,1) e (2,2)
		 * 
		 * (2,0) -> (1,0) e (2,1) (2,1) -> (2,0) e (1,1) e (2,2) (2,2) -> (2,1) e (1,2)
		 * 
		 * O número à esquerda está na posição (x - 1, y) O número à direita está na
		 * posição (x + 1, y) O número acima está na posição (x, y - 1) O número abaixo
		 * está na posição (x, y + 1)
		 * 
		 * Lembrando que: Na primeira coluna não haverá números à esquerda Na última
		 * coluna não haverá números à direita Na primeira linha não haverá números
		 * acima Na última linha não haverá números abaixo
		 */
		contador_expandidos++;
		int tamanhoMatriz = s.getState().length;
		Queue<Estado> filhos = new LinkedList<Estado>();
		for (int i = 0; i < tamanhoMatriz; i++) {
			for (int j = 0; j < tamanhoMatriz; j++) {
				Estado estadoCopia = s.clone();
				estadoCopia.setPai(s);
				operacao(estadoCopia, i, j);
				if (i < (tamanhoMatriz - 1)) {
					operacao(estadoCopia, i + 1, j);
				}
				if (j < (tamanhoMatriz - 1)) {
					operacao(estadoCopia, i, j + 1);
				}
				if (i > 0) {
					operacao(estadoCopia, i - 1, j);
				}
				if (j > 0) {
					operacao(estadoCopia, i, j - 1);
				}
				filhos.add(estadoCopia);
			}
		}
		return filhos;
	}

	public static boolean filaVazia(Queue<Estado> fila) {
		if (fila.isEmpty()) {
			return true;
		}
		return false;
	}

	public static void enfileira(Estado s, Queue<Estado> fila) {
		fila.add(s);
	}

	public static Estado desenfileira(Queue<Estado> fila) {
		return fila.remove();
	}

	public static void mostraCaminho(Estado s) {
		if (s == null) {
			return;
		}
		mostraCaminho(s.pai);
		exibeEstado(s);
	}
	
	public static boolean estaNoCaminho(Estado filho, Estado ancestral) {
		if(ancestral==null) {
			return false;
		}
		if(filho.equals(ancestral)) {
			return true;
		}
		return estaNoCaminho(filho, ancestral.getPai());
	}
	
	public static boolean estadoAberto(Estado filho,Queue<Estado> fila) {
		if(fila.contains(filho)) {
			return true;
		}
		return false;
	}
	
	private static boolean estaNosExpandidos(Estado estadoAtual, Queue<Estado> expandidos) {
		if(expandidos.contains(estadoAtual)) {
			return true;
		}
		return false;
	}
}
