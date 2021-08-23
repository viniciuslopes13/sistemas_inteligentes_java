package br.edu.ufersa.trabalho;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LightsOut {

	public static void main(String[] args) {
		Estado estado = inicio();
		Queue<Estado> fila = new LinkedList<Estado>();
		enfileira(estado, fila);
		while (!filaVazia(fila)) {
			Estado estadoAtual = desenfileira(fila);
			if (objetivo(estadoAtual)) {
				System.out.println("Estado objetivo encontrado!");
				mostraCaminho(estadoAtual);
				break;
			}
			exibeEstado(estadoAtual);
			Queue<Estado> estadosFilhos = expansao(estadoAtual);
			for (Estado filho : estadosFilhos) {
				enfileira(filho, fila);
			}
		}
	}

	public static void exibeEstado(Estado s) {
		int mat[][] = s.state;
		System.out.println("===========");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===========");
	}

	public static Estado inicio() {
		int mat[][] = { { 1, 0, 0 }, { 0, 1, 1 }, { 0, 1, 0 } };
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

	public static int[] posicao(int linha, int coluna) {
		int[] vet = new int[2];
		vet[0] = linha;
		vet[1] = coluna;
		return vet;
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
		 * (0,0) (0,1) (0,2) 
		 * (1,0) (1,1) (1,2) 
		 * (2,0) (2,1) (2,2)
		 * 
		 * (0,0) -> (0,1) e (1,0) 
		 * (0,1) -> (0,0) e (0,2) e (1,1) 
		 * (0,2) -> (0,1) e (1,2)
		 * 
		 * (1,0) -> (0,0) e (1,1) e (2,0) 
		 * (1,1) -> (0,1) e (2,1) e (1,0) e (1,2) 
		 * (1,2) -> (0,2) e (1,1) e (2,2)
		 * 
		 * (2,0) -> (1,0) e (2,1) 
		 * (2,1) -> (2,0) e (1,1) e (2,2) 
		 * (2,2) -> (2,1) e (1,2)
		 * 
		 * O número à esquerda está na posição (x - 1, y) 
		 * O número à direita está na posição (x + 1, y) 
		 * O número acima está na posição (x, y - 1) 
		 * O número abaixo está na posição (x, y + 1)
		 * 
		 * Lembrando que: 
		 * Na primeira coluna não haverá números à esquerda 
		 * Na última coluna não haverá números à direita 
		 * Na primeira linha não haverá números acima 
		 * Na última linha não haverá números abaixo
		 */
		Queue<Estado> filhos = new LinkedList<Estado>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Estado estadoCopia = s.clone();
				estadoCopia.setPai(s);
				operacao(estadoCopia, i, j);
				if (i < 2) {
					operacao(estadoCopia, i + 1, j);
				}
				if (j < 2) {
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
}
