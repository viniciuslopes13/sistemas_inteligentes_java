package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class problemaDaMesa_genetico {

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
		
		
		List<Pessoa> p = listaMesa();
		int tamanhoPopulacao = 100;
		int numeroGeracoes = 100;
		List<Mesa> geracaoAtual = new ArrayList<Mesa>();
		for(int i=0;i<tamanhoPopulacao;i++) {
			Mesa mesa = new Mesa(copia(p));
			mesa.populaMesaAleatorio();
			geracaoAtual.add(mesa);
		}
		for(int i=0; i<numeroGeracoes; i++) {
			List<Mesa> proximaGeracao = new ArrayList<Mesa>();
			for(int j=0; j<tamanhoPopulacao; j++) {
				Mesa x = escolhe(geracaoAtual);
				Mesa y = escolhe(geracaoAtual);
				Mesa filho = cruzamento(x,y);
				filho = mutacao(filho);
				proximaGeracao.add(filho);
			}
			System.out.println("i - "+i);
			geracaoAtual = proximaGeracao;
		}
		Mesa melhor = geracaoAtual.get(0);
		for (Mesa m1 : geracaoAtual) {
			if(m1.getNumConflitos()<melhor.getNumConflitos()) {
				melhor = m1;
			}
		}
		PieChart gr1 = new PieChart(melhor);
		
	}

	private static List<Pessoa> copia(List<Pessoa> participante) {
		// TODO Auto-generated method stub
		List<Pessoa> list = new ArrayList<Pessoa>();
		for(Pessoa p: participante) {
			list.add(p);
		}
		return list;
	}

	private static Mesa escolhe(List<Mesa> geracaoAtual) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int tamanhoLista = geracaoAtual.size();
		Mesa x = geracaoAtual.get(random.nextInt(tamanhoLista));
		Mesa y = geracaoAtual.get(random.nextInt(tamanhoLista));
		if(x.getNumConflitos()<y.getNumConflitos()) {
			return x;
		}
		return y;
	}

	private static Mesa cruzamento(Mesa x, Mesa y) {
		// TODO Auto-generated method stub
		Mesa cruzada = new Mesa();
		int tamanhoMesa = x.getAlocados().size();
		for(int i=0; i<(tamanhoMesa/2);i++) {
			cruzada.getAlocados().add(x.getAlocados().get(i));
		}
		for(Pessoa p : y.getAlocados()) {
			if(!cruzada.getAlocados().contains(p)) {
				cruzada.getAlocados().add(p);
			}
		}
		return cruzada;
	}

	private static Mesa mutacao(Mesa filho) {
		// TODO Auto-generated method stub
		Random random = new Random();
		if(random.nextDouble()<0.8)
			return filho.sucessor();
		return filho;
	}

	public static List<Pessoa> listaMesa(){
		List<Pessoa> list = new ArrayList<Pessoa>();
		for(int i=0;i<50;i++) {
			list.add(new Pessoa(geradorDeNomes()));
		}
		for(int i=0;i<50;i++) {
			Random random = new  Random();
			list.get(i).adicionaInimigo(list.get(random.nextInt(50)));
			list.get(i).adicionaInimigo(list.get(random.nextInt(50)));
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
