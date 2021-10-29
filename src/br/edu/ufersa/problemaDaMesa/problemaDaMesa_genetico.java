package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class problemaDaMesa_genetico {

	public static void main(String[] args) {
		
		List<Pessoa> participante = Utils.listaConhecidaComConfitos();
		
		Utils.geradorDeConflitos(participante);
		Utils.geradorDeConflitos(participante);
		
		int tamanhoPopulacao = 100;
		int numeroGeracoes = 100;
		List<Mesa> geracaoAtual = new ArrayList<Mesa>();
		for(int i=0;i<tamanhoPopulacao;i++) {
			Mesa mesa = new Mesa(Utils.copia(participante));
			mesa.populaMesaAleatorio();
			geracaoAtual.add(mesa);
		}
		//Mostra a melhor mesa da primeira geração
		Mesa melhor = melhorMesa(geracaoAtual);
		PieChart gr1 = new PieChart(melhor);
		//
		for(int i=0; i<numeroGeracoes; i++) {
			List<Mesa> proximaGeracao = new ArrayList<Mesa>();
			for(int j=0; j<tamanhoPopulacao; j++) {
				//Mesa x = escolhe(geracaoAtual);
				//Mesa y = escolhe(geracaoAtual);
				Mesa x = escolheElite(geracaoAtual);
				Mesa y = escolheElite2(geracaoAtual,x);
				Mesa filho = cruzamento(x,y);
				filho = mutacao(filho);
				proximaGeracao.add(filho);
			}
			System.out.println("i - "+i);
			geracaoAtual = proximaGeracao;
		}
		melhor = geracaoAtual.get(0);
		int cont=0;
		for (Mesa m1 : geracaoAtual) {
			if(m1.getNumConflitos()<melhor.getNumConflitos()) {
				melhor = m1;
				cont++;
			}
		}
		System.out.println("A melhor composição foi encontrada na iteracao: "+cont);
		PieChart gr2 = new PieChart(melhor);
		
	}
	
	private static Mesa melhorMesa(List<Mesa> geracaoAtual) {
		Mesa melhor = geracaoAtual.get(0);
		for(Mesa m1 : geracaoAtual) {
			if(m1.getNumConflitos()<melhor.getNumConflitos()) {
				melhor = m1;
			}
		}
		return melhor;
	}
		
	private static Mesa escolheElite(List<Mesa> geracaoAtual) {
		return melhorMesa(geracaoAtual);
	}
	
	private static Mesa escolheElite2(List<Mesa> geracaoAtual, Mesa m) {
		Mesa melhor_ = melhorMesa(geracaoAtual);
		Mesa melhor = geracaoAtual.get(0);
		for(Mesa m1 : geracaoAtual) {
			if(m1.equals(melhor_)) {
				continue;
			}
			if(m1.getNumConflitos()<melhor.getNumConflitos()) {
				melhor = m1;
			}
		}
		return melhor;
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

}
