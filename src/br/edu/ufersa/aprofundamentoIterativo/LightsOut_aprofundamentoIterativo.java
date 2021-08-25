package br.edu.ufersa.aprofundamentoIterativo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class LightsOut_aprofundamentoIterativo {

	static int contador_expandidos = 0;

	public static void main(String[] args) {
		int contador_visitados = 0;
		int tamanhoPilha = 0;
		int limiteProfundidade = 0;
		int profundidadeMaximaAtual;
		Stack<Estado> pilha = new Stack<Estado>(); // PILHA DE ESTADOS
		Queue<Estado> estadosExpandidos = new LinkedList<Estado>(); // FILA DOS ESTADOS EXPANDIDOS
		while (true) {
			Estado estado = inicio(); // INICIALIZA ESTADO CONFORME ESTADO INICIAL
			empilha(estado, pilha);
			profundidadeMaximaAtual=0;
			while (!pilhaVazia(pilha)) {
				Estado estadoAtual = desempilha(pilha);
				if (estaNosExpandidos(estadoAtual, estadosExpandidos)) {
					continue;
				}
				contador_visitados++;
				if (objetivo(estadoAtual)) {
					System.out.println("Estado objetivo encontrado!");
					mostraCaminho(estadoAtual);
					System.out.println("Total de estados visitados: " + contador_visitados);
					System.out.println("Total de estados expandidos: " + estadosExpandidos.size());
					System.out.println("Total de estados expandidos (via contador): " + contador_expandidos);
					System.out.println("N�mero m�ximo de estados na estrutura que guarda estados a serem expandidos:"
							+ tamanhoPilha);
					break;
				}
				exibeEstado(estadoAtual);
//				if(estadoAtual.getProfundidade()>profundidadeMaximaAtual) {
//					profundidadeMaximaAtual = estadoAtual.getProfundidade();
//				}
//				if (estadoAtual.getProfundidade() >= limiteProfundidade) {
//					continue;
//				}
				List<Estado> estadosFilhos = expansao(estadoAtual);
				estadosExpandidos.add(estado); // ADICIONA ESTADO_ATUAL A FILA DOS EXPANDIDOS.
				for (Estado filho : estadosFilhos) {
					if ((!estaNoCaminho(filho, estadoAtual)) && (!estadoAberto(filho, pilha))) {
						if(estadoAtual.getProfundidade() < limiteProfundidade) {
							empilha(filho, pilha);
						}
						if(estadoAtual.getProfundidade()>profundidadeMaximaAtual) {
							profundidadeMaximaAtual = estadoAtual.getProfundidade();
						}
					}
				}
				if (pilha.size() > tamanhoPilha) {
					tamanhoPilha = pilha.size();
				}
				System.out.println("tamanhoPilha: " + tamanhoPilha);
			}
			if(profundidadeMaximaAtual<limiteProfundidade) {
				break;
			}
			limiteProfundidade=limiteProfundidade+1;
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
		int mat[][] = { { 1, 0, 0 }, { 0, 1, 1 }, { 0, 1, 0 } };
		Estado inicio = new Estado();
		inicio.setState(mat);
		inicio.setProfundidade(0);
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

	public static List<Estado> expansao(Estado s) {
		contador_expandidos++;
		int tamanhoMatriz = s.getState().length;
		List<Estado> filhos = new ArrayList<Estado>();
		for (int i = 0; i < tamanhoMatriz; i++) {
			for (int j = 0; j < tamanhoMatriz; j++) {
				Estado estadoCopia = s.clone();
				estadoCopia.setPai(s);
				estadoCopia.setProfundidade((estadoCopia.getProfundidade() + 1));
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

	public static boolean pilhaVazia(Stack<Estado> pilha) {
		if (pilha.isEmpty()) {
			return true;
		}
		return false;
	}

	public static void empilha(Estado s, Stack<Estado> pilha) {
		pilha.push(s);
	}

	public static Estado desempilha(Stack<Estado> pilha) {
		return pilha.pop();
	}

	public static void mostraCaminho(Estado s) {
		if (s == null) {
			return;
		}
		mostraCaminho(s.pai);
		exibeEstado(s);
	}

	public static boolean estaNoCaminho(Estado filho, Estado ancestral) {
		if (ancestral == null) {
			return false;
		}
		if (filho.equals(ancestral)) {
			return true;
		}
		return estaNoCaminho(filho, ancestral.getPai());
	}

	public static boolean estadoAberto(Estado filho, Stack<Estado> pilha) {
		if (pilha.contains(filho)) {
			return true;
		}
		return false;
	}

	private static boolean estaNosExpandidos(Estado estadoAtual, Queue<Estado> expandidos) {
		if (expandidos.contains(estadoAtual)) {
			return true;
		}
		return false;
	}

}
