package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		Pessoa thiago = new Pessoa("thiago");
		Pessoa bruno = new Pessoa("bruno");
		Pessoa breno = new Pessoa("breno");
		Pessoa lamark = new Pessoa("lamark");
		Pessoa alef = new Pessoa("alef");
		Pessoa joao = new Pessoa("joao");
	
		participante.add(iorrane);
		participante.add(danilo);
		participante.add(marco);
		participante.add(vinicius);
		participante.add(andre);
		participante.add(victor);
		participante.add(luan);
		participante.add(sebastiao);
		participante.add(alef);
		participante.add(joao);
		participante.add(lamark);
		participante.add(breno);
		participante.add(bruno);
		participante.add(thiago);
		
		iorrane.adicionaInimigo(danilo);
		iorrane.adicionaInimigo(marco);
		iorrane.adicionaInimigo(andre);
		iorrane.adicionaInimigo(luan);
		danilo.adicionaInimigo(vinicius);
		danilo.adicionaInimigo(andre);
		andre.adicionaInimigo(luan);
		sebastiao.adicionaInimigo(victor);
		sebastiao.adicionaInimigo(vinicius);
		andre.adicionaInimigo(victor);
		breno.adicionaInimigo(joao);
		joao.adicionaInimigo(lamark);
		lamark.adicionaInimigo(alef);
		alef.adicionaInimigo(thiago);
		thiago.adicionaInimigo(bruno);
		bruno.adicionaInimigo(sebastiao);
		
		//AQUI COMEÇA O ALGORITMO - SUBIDA DE ENCOSTA
		Mesa atual = new Mesa(participante);
		atual.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEATÓRIA NUM ESTADO INICIAL
		PieChart gr1 = new PieChart(atual); //CHAMA GRÁFICO INICIAL
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
		PieChart gr2 = new PieChart(atual); //CHAMA GRÁFICO FINAL
	}
	
	public static List<Pessoa> listaMesa(){
		List<Pessoa> list = new ArrayList<Pessoa>();
		for(int i=0;i<20;i++) {
			list.add(new Pessoa("P"+i));
		}
		for(int i=0;i<20;i++) {
			Random random = new  Random();
			list.get(i).adicionaInimigo(list.get(random.nextInt(20)));
			list.get(i).adicionaInimigo(list.get(random.nextInt(20)));
			list.get(i).adicionaInimigo(list.get(random.nextInt(20)));
			list.get(i).adicionaInimigo(list.get(random.nextInt(20)));
		}
		return list;
	}
}
