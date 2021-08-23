package br.edu.ufersa.si;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle {

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
		int mat[][] = { { 1, 2, 3 }, { 4, 0, 5 }, { 7, 8, 6 } };
		Estado inicio = new Estado();
		inicio.setState(mat);
		inicio.setPai(null);
		return inicio;
	}

	public static int[] buscaZero(Estado s) {
		int[] vet = new int[2];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (s.state[i][j] == 0) {
					vet[0] = i;
					vet[1] = j;
				}
			}
		}
		return vet;
	}

	public static void moveCima(Estado s, int vet[]) {
		if(vet[0]==0) {
			return;
		}else {
			s.state[vet[0]][vet[1]] = s.state[vet[0] - 1][vet[1]];
			s.state[vet[0] - 1][vet[1]] = 0;	
		}
	}

	public static void moveBaixo(Estado s, int vet[]) {
		if(vet[0]==2) {
			return;
		}else {
			s.state[vet[0]][vet[1]] = s.state[vet[0] + 1][vet[1]];
			s.state[vet[0] + 1][vet[1]] = 0;	
		}
	}

	public static void moveEsquerda(Estado s, int vet[]) {
		if(vet[1]==0) {
			return;
		}else {
			s.state[vet[0]][vet[1]] = s.state[vet[0]][vet[1] - 1];
			s.state[vet[0]][vet[1] - 1] = 0;
		}
	}

	public static void moveDireita(Estado s, int vet[]) {
		if(vet[1]==2) {
			return;
		}else {
			s.state[vet[0]][vet[1]] = s.state[vet[0]][vet[1] + 1];
			s.state[vet[0]][vet[1] + 1] = 0;	
		}
	}

	public static boolean objetivo(Estado s) {
		int objetivo[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		if (Arrays.deepEquals(s.state, objetivo)) {
			return true;
		}
		return false;
	}

	public static Queue<Estado> expansao(Estado s) {
		Queue<Estado> filhos = new LinkedList<Estado>();

		int vet[] = buscaZero(s);

		if (vet[0]>0 ) {
			Estado estadoCopia = s.clone();
			estadoCopia.setPai(s);
			moveCima(estadoCopia, vet);
			filhos.add(estadoCopia);
		}
		if (vet[0] < 2) {
			Estado estadoCopia = s.clone();
			estadoCopia.setPai(s);
			moveBaixo(estadoCopia, vet);
			filhos.add(estadoCopia);
		}
		if (vet[1] > 0) {
			Estado estadoCopia = s.clone();
			estadoCopia.setPai(s);
			moveEsquerda(estadoCopia, vet);
			filhos.add(estadoCopia);
		}
		if (vet[1] < 2) {
			Estado estadoCopia = s.clone();
			estadoCopia.setPai(s);
			moveDireita(estadoCopia, vet);
			filhos.add(estadoCopia);
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
