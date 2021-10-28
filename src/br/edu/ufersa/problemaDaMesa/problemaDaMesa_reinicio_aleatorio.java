package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class problemaDaMesa_reinicio_aleatorio {

	public static void main(String[] args) {
		
		List<Pessoa> participante = Utils.listaConhecida();
		
		Utils.geradorDeConflitos(participante);
		
		Mesa melhor = new Mesa(Utils.copia(participante));
		melhor.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEAT�RIA NUM ESTADO INICIAL
		int conflitosMesaMelhor = melhor.getNumConflitos();
		PieChart gr1 = new PieChart(melhor); //CHAMA GR�FICO INICIAL
		System.out.println("Mesa inicial:" + melhor); //EXIBE A COMPOSI��O INICIAL DA MESA
		System.out.println("N�mero de conflintos da mesa inicial: "+melhor.getNumConflitos()); //N�MERO DE CONFLITOS INCICIAL
		int cont = 0; //INICIALIZA CONTADOR QUE CONTROLA O LA�O
		int contAux = 0;
		while(cont<=100) {
			Mesa atual = new Mesa(Utils.copia(participante));
			atual.populaMesaAleatorio();
			while(true) {
				Mesa vizinho = new Mesa();
				vizinho = atual.sucessor();
				if(vizinho.getNumConflitos() >= atual.getNumConflitos()) {
					break;
				}
				atual=vizinho;
			}
			if(atual.getNumConflitos() < conflitosMesaMelhor) {
				melhor = atual.CloneIdentico();
				conflitosMesaMelhor = atual.getNumConflitos();
				contAux=cont;
			}
			cont++;
		}
		System.out.println();
		System.out.println("Mesa FINAL:" + melhor); //EXIBE A COMPOSI��O FINAL DA MESA
		System.out.println("N�mero de conflintos da mesa FINAL: "+melhor.getNumConflitos()); //N�MERO DE CONFLITOS FINAL
		System.out.println();
		System.out.println("A melhor composi��o foi encontrada na iteracao: "+contAux);
		PieChart gr2 = new PieChart(melhor); //CHAMA GR�FICO FINAL
	}
	
}
