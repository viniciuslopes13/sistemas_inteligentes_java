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
		
		participante.add(iorrane);
		participante.add(danilo);
		participante.add(marco);
		participante.add(vinicius);

		iorrane.adicionaInimigo(danilo);
		iorrane.adicionaInimigo(marco);
		
		marco.adicionaInimigo(iorrane);
		
		danilo.adicionaInimigo(iorrane);
		danilo.adicionaInimigo(vinicius);
		
		vinicius.adicionaInimigo(danilo);

		//System.out.println(iorrane.getListaInimigos());

		Mesa m2 = new Mesa(participante);
		//m2 = m2.popularMesa(participante);
		
		m2.populaMesaAleatorio();
		
		System.out.println(m2);
		System.out.println("Total de conflitos: "+m2.getNumConflitos());

		//System.out.println(iorrane.buscarVizinho(m2, iorrane.getListaInimigos(), iorrane));

	}

}
