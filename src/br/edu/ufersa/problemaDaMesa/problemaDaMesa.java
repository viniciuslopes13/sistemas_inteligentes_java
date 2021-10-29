package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class problemaDaMesa {

	public static void main(String[] args) {
		
		List<Pessoa> participante = Utils.listaConhecidaComConfitos();
		//Utils.geradorDeConflitos(participante);
		
		//AQUI COMEÇA O ALGORITMO - SUBIDA DE ENCOSTA
		Mesa atual = new Mesa(participante);
		atual.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEATÓRIA NUM ESTADO INICIAL
		PieChart gr1 = new PieChart(atual); //CHAMA GRÁFICO INICIAL
		System.out.println("Mesa inicial:" + atual); //EXIBE A COMPOSIÇÃO INICIAL DA MESA
		System.out.println("Número de conflintos da mesa inicial: "+atual.getNumConflitos()); //NÚMERO DE CONFLITOS INCICIAL
		int cont = 0; 
		while(true) {
			Mesa vizinho = new Mesa();
			vizinho = atual.sucessor();
			cont++;
			if(vizinho.getNumConflitos() < atual.getNumConflitos()) {
				atual = vizinho;
				break;
			}
		}
		System.out.println();
		System.out.println("Mesa FINAL:" + atual); //EXIBE A COMPOSIÇÃO FINAL DA MESA
		System.out.println("Número de conflintos da mesa FINAL: "+atual.getNumConflitos()); //NÚMERO DE CONFLITOS FINAL
		System.out.println();
		System.out.println("A melhor composição foi encontrada na iteracao: "+cont);
		PieChart gr2 = new PieChart(atual); //CHAMA GRÁFICO FINAL
	}
	
}
