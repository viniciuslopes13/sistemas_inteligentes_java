package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;

public class problemaDaMesa_reinicio_aleatorio {

	public static void main(String[] args) {
		
		List<Pessoa> participante = new ArrayList<Pessoa>();
		
		Pessoa iorrane = new Pessoa("iorrane");
		Pessoa danilo = new Pessoa("danilo");
		Pessoa marco = new Pessoa("marco");
		Pessoa vinicius = new Pessoa("vinicius");
		Pessoa andre = new Pessoa("Andr�");
		Pessoa victor = new Pessoa("Victor");
		Pessoa luan = new Pessoa("Luan");
		Pessoa sebastiao = new Pessoa("Sebasti�o");
	
		participante.add(iorrane);
		participante.add(danilo);
		participante.add(marco);
		participante.add(vinicius);
		participante.add(andre);
		participante.add(victor);
		participante.add(luan);
		participante.add(sebastiao);
		
		iorrane.adicionaInimigo(danilo);
		iorrane.adicionaInimigo(marco);
		iorrane.adicionaInimigo(andre);
		danilo.adicionaInimigo(vinicius);
		andre.adicionaInimigo(luan);
		sebastiao.adicionaInimigo(victor);
		sebastiao.adicionaInimigo(vinicius);
		andre.adicionaInimigo(victor);
		
		//AQUI COME�A O ALGORITMO - SUBIDA DE ENCOSTA
		Mesa melhor = new Mesa(participante);
		melhor.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEAT�RIA NUM ESTADO INICIAL
		PieChart gr1 = new PieChart(melhor); //CHAMA GR�FICO INICIAL
		System.out.println("Mesa inicial:" + melhor); //EXIBE A COMPOSI��O INICIAL DA MESA
		System.out.println("N�mero de conflintos da mesa inicial: "+melhor.getNumConflitos()); //N�MERO DE CONFLITOS INCICIAL
		int cont = 0; //INICIALIZA CONTADOR QUE CONTROLA O LA�O
		int cont2 = 0;
		while(cont<=100) {
			Mesa atual = new Mesa(participante);
			atual.populaMesaAleatorio();
			while(cont2<=100) {
				cont2++;
				Mesa vizinho = new Mesa();
				vizinho = atual.sucessor();
				if(vizinho.getNumConflitos() <= atual.getNumConflitos()) {
					atual = vizinho;
					break;
				}
			}
			if(atual.getNumConflitos() < melhor.getNumConflitos())
				melhor = atual;
			cont++;
		}
		System.out.println();
		System.out.println("Mesa FINAL:" + melhor); //EXIBE A COMPOSI��O FINAL DA MESA
		System.out.println("N�mero de conflintos da mesa FINAL: "+melhor.getNumConflitos()); //N�MERO DE CONFLITOS FINAL
		PieChart gr2 = new PieChart(melhor); //CHAMA GR�FICO FINAL
	}
	
}
