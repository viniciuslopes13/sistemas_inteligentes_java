package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class problemaDaMesa_tempera_simulada {

	public static void main(String[] args) {

		List<Pessoa> participante = Utils.listaConhecidaComConfitos();

		//Utils.geradorDeConflitos(participante);

		Mesa atual = new Mesa(participante);
		atual.populaMesaAleatorio(); // INICIALIZA A MESA DE FORMA ALEATÓRIA NUM ESTADO INICIAL
		PieChart gr1 = new PieChart(atual); // CHAMA GRÁFICO INICIAL
		System.out.println("Mesa inicial:" + atual); // EXIBE A COMPOSIÇÃO INICIAL DA MESA
		System.out.println("Número de conflintos da mesa inicial: " + atual.getNumConflitos()); // NÚMERO DE CONFLITOS
																								// INCICIAL
		double T = 1000.0;
		int delta;
		Random random = new Random();
		int cont = 0;
		while (true) {

			T = T * 0.999;

			if (T < 0.01) {
				break;
			}

			Mesa vizinho = new Mesa();
			vizinho = atual.sucessor();

			delta = atual.getNumConflitos() - vizinho.getNumConflitos(); // MINIMIZAÇÃO

			if (delta > 0) {
				atual = vizinho;
				cont++;
			} else if (random.nextDouble() < Math.exp(delta / T)) {
				atual = vizinho;
				cont++;
			}

		}
		System.out.println();
		System.out.println("Mesa FINAL:" + atual); // EXIBE A COMPOSIÇÃO FINAL DA MESA
		System.out.println("Número de conflintos da mesa FINAL: " + atual.getNumConflitos()); // NÚMERO DE CONFLITOS
		System.out.println();
		System.out.println("A melhor composição foi encontrada na iteracao: "+cont);																						// FINAL
		PieChart gr2 = new PieChart(atual); // CHAMA GRÁFICO FINAL
	}

}
