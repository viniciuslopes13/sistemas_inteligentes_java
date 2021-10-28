package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class problemaDaMesa_tempera_simulada {

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
		
		Mesa atual = new Mesa(listaMesa());
		atual.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEAT�RIA NUM ESTADO INICIAL
		PieChart gr1 = new PieChart(atual); //CHAMA GR�FICO INICIAL
		System.out.println("Mesa inicial:" + atual); //EXIBE A COMPOSI��O INICIAL DA MESA
		System.out.println("N�mero de conflintos da mesa inicial: "+atual.getNumConflitos()); //N�MERO DE CONFLITOS INCICIAL
		double T = 1000.0;
		int delta;
		Random random = new Random();
		while(true) {
			
			T = T*0.999;
			
			if(T<0.01) {
				break;
			}
			
			Mesa vizinho = new Mesa();
			vizinho = atual.sucessor();
			
			delta = atual.getNumConflitos() - vizinho.getNumConflitos(); //MINIMIZA��O
			
			if(delta>0) {
				atual = vizinho;
			}else if(random.nextDouble() < Math.exp(delta/T)) {
				atual = vizinho;
			}

		}
		System.out.println();
		System.out.println("Mesa FINAL:" + atual); //EXIBE A COMPOSI��O FINAL DA MESA
		System.out.println("N�mero de conflintos da mesa FINAL: "+atual.getNumConflitos()); //N�MERO DE CONFLITOS FINAL
		PieChart gr2 = new PieChart(atual); //CHAMA GR�FICO FINAL
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
	
	public static String geradorDeNomes() {
		ThreadLocalRandom gerador = ThreadLocalRandom.current();
		
		int tamanhoNome = gerador.nextInt(3, 10);

		StringBuilder nome = new StringBuilder();

		for (int i = 1; i < tamanhoNome; i++) {
			char letra = (char) gerador.nextInt(97, 122);
			nome.append(letra);
		}

		return nome.toString();
	}
	
}
