package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;

public class problemaDaMesa {

	public static void main(String[] args) {
		
		List<Pessoa> participante = new ArrayList<Pessoa>();
		
		Pessoa iorrane = new Pessoa("iorrane");
		Pessoa danilo = new Pessoa("danilo");
		Pessoa marco = new Pessoa("marco");
		Pessoa vinicius = new Pessoa("vinicius");
		Pessoa andre = new Pessoa("André");
		Pessoa victor = new Pessoa("Victor");
		Pessoa luan = new Pessoa("Luan");
		Pessoa sebastiao = new Pessoa("Sebastião");
	
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
		
		marco.adicionaInimigo(iorrane);
		
		danilo.adicionaInimigo(iorrane);
		danilo.adicionaInimigo(vinicius);
		
		vinicius.adicionaInimigo(danilo);
		
		andre.adicionaInimigo(luan);
		
		sebastiao.adicionaInimigo(victor);
		
		//AQUI COMEÇA DE FATO O ALGORITMO - SUBIDA DE ENCOSTA
		Mesa atual = new Mesa(participante);
		atual.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEATÓRIA NUM ESTADO INICIAL
		new PieChart(atual); //CHAMA GRÁFICO INICIAL
		System.out.println("Mesa inicial:" + atual); //EXIBE A COMPOSIÇÃO INICIAL DA MESA
		System.out.println("Número de conflintos da mesa inicial: "+atual.getNumConflitos()); //NÚMERO DE CONFLITOS INCICIAL
		int cont = 0; //INICIALIZA CONTADOR QUE CONTROLA O LAÇO
		while(cont<=100) {
			Mesa vizinho = new Mesa();
			vizinho = atual.sucessor();
			if(vizinho.getNumConflitos() < atual.getNumConflitos()) {
				atual = vizinho;
				break;
			}
			cont++;
		}
		System.out.println();
		System.out.println("Mesa FINAL:" + atual); //EXIBE A COMPOSIÇÃO FINAL DA MESA
		System.out.println("Número de conflintos da mesa FINAL: "+atual.getNumConflitos()); //NÚMERO DE CONFLITOS FINAL
		
		new PieChart2(atual); //CHAMA GRÁFICO FINAL
	}
	
}
