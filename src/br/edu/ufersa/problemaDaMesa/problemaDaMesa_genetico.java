package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class problemaDaMesa_genetico {

	public static void main(String[] args) {
		
		List<Pessoa> participante = Utils.listaConhecida();
		
		Utils.geradorDeConflitos(participante);
		
		int tamanhoPopulacao = 100;
		int numeroGeracoes = 100;
		List<Mesa> geracaoAtual = new ArrayList<Mesa>();
		for(int i=0;i<tamanhoPopulacao;i++) {
			Mesa mesa = new Mesa(copia(participante));
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

}
