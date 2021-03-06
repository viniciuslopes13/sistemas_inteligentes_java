package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class problemaDaMesa {

	public static void main(String[] args) {
		
		List<Pessoa> participante = Utils.mesaAleatoria();
		Utils.geradorDeConflitos(participante);
		
		//AQUI COME?A O ALGORITMO - SUBIDA DE ENCOSTA
		Mesa atual = new Mesa(participante);
		atual.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEAT?RIA NUM ESTADO INICIAL
		PieChart gr1 = new PieChart(atual); //CHAMA GR?FICO INICIAL
		System.out.println("Mesa inicial:" + atual); //EXIBE A COMPOSI??O INICIAL DA MESA
		System.out.println("N?mero de conflintos da mesa inicial: "+atual.getNumConflitos()); //N?MERO DE CONFLITOS INCICIAL
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
		System.out.println("Mesa FINAL:" + atual); //EXIBE A COMPOSI??O FINAL DA MESA
		System.out.println("N?mero de conflintos da mesa FINAL: "+atual.getNumConflitos()); //N?MERO DE CONFLITOS FINAL
		System.out.println();
		System.out.println("A melhor composi??o foi encontrada na iteracao: "+cont);
		PieChart gr2 = new PieChart(atual); //CHAMA GR?FICO FINAL
	}
	
}
