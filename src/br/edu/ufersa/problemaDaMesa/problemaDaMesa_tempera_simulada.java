package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class problemaDaMesa_tempera_simulada {

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
		iorrane.adicionaInimigo(andre);
		iorrane.adicionaInimigo(luan);
		danilo.adicionaInimigo(vinicius);
		danilo.adicionaInimigo(andre);
		andre.adicionaInimigo(luan);
		sebastiao.adicionaInimigo(victor);
		sebastiao.adicionaInimigo(vinicius);
		andre.adicionaInimigo(victor);
		
		Mesa atual = new Mesa(participante);
		atual.populaMesaAleatorio(); //INICIALIZA A MESA DE FORMA ALEATÓRIA NUM ESTADO INICIAL
		PieChart gr1 = new PieChart(atual); //CHAMA GRÁFICO INICIAL
		System.out.println("Mesa inicial:" + atual); //EXIBE A COMPOSIÇÃO INICIAL DA MESA
		System.out.println("Número de conflintos da mesa inicial: "+atual.getNumConflitos()); //NÚMERO DE CONFLITOS INCICIAL
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
			
			delta = atual.getNumConflitos() - vizinho.getNumConflitos(); //MINIMIZAÇÃO
			
			if(delta>0) {
				atual = vizinho;
			}else if(random.nextDouble() < Math.exp(delta/T)) {
				atual = vizinho;
			}

		}
		System.out.println();
		System.out.println("Mesa FINAL:" + atual); //EXIBE A COMPOSIÇÃO FINAL DA MESA
		System.out.println("Número de conflintos da mesa FINAL: "+atual.getNumConflitos()); //NÚMERO DE CONFLITOS FINAL
		PieChart gr2 = new PieChart(atual); //CHAMA GRÁFICO FINAL
	}
	
}
