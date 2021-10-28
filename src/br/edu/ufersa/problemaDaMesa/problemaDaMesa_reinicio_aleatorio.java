package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class problemaDaMesa_reinicio_aleatorio {

	public static void main(String[] args) {
		
		List<Pessoa> participante = Utils.listaConhecida();
		
		Utils.geradorDeConflitos(participante);
		
		Mesa melhor = new Mesa(Utils.copia(participante));
		melhor.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEATÓRIA NUM ESTADO INICIAL
		int conflitosMesaMelhor = melhor.getNumConflitos();
		PieChart gr1 = new PieChart(melhor); //CHAMA GRÁFICO INICIAL
		System.out.println("Mesa inicial:" + melhor); //EXIBE A COMPOSIÇÃO INICIAL DA MESA
		System.out.println("Número de conflintos da mesa inicial: "+melhor.getNumConflitos()); //NÚMERO DE CONFLITOS INCICIAL
		int cont = 0; //INICIALIZA CONTADOR QUE CONTROLA O LAÇO
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
		System.out.println("Mesa FINAL:" + melhor); //EXIBE A COMPOSIÇÃO FINAL DA MESA
		System.out.println("Número de conflintos da mesa FINAL: "+melhor.getNumConflitos()); //NÚMERO DE CONFLITOS FINAL
		System.out.println();
		System.out.println("A melhor composição foi encontrada na iteracao: "+contAux);
		PieChart gr2 = new PieChart(melhor); //CHAMA GRÁFICO FINAL
	}
	
}
