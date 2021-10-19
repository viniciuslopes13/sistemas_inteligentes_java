package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pessoa {

	private String nome;
	private List<Pessoa> listaInimigos;
	private int posicaoNaMesa;
	
	public Pessoa(String nome) {
		this.nome = nome;
		listaInimigos = new ArrayList<Pessoa>();
		posicaoNaMesa = 0;
	}

	public Pessoa buscarVizinho(Mesa m, List<Pessoa> listaInimigos, Pessoa euProprio) {
		Random random = new Random();
		List<Pessoa> possiveisAlocacoes = new ArrayList<Pessoa>();
		possiveisAlocacoes.addAll(m.getNaoAlocados());
		possiveisAlocacoes.remove(euProprio);
		if(!m.getNaoAlocados().equals(listaInimigos)) {
			possiveisAlocacoes.removeAll(listaInimigos);
		}
		if(possiveisAlocacoes.isEmpty()) {
			if(m.getNaoAlocados().isEmpty()) {
				return null;
			}
			return m.getNaoAlocados().get(random.nextInt(m.getNaoAlocados().size()));
		}
		Pessoa p = possiveisAlocacoes.get(random.nextInt(possiveisAlocacoes.size()));
		return p;
	}
	
	public void adicionaInimigo(Pessoa p) {
		this.listaInimigos.add(p);
	}
	
	public List<Pessoa> getListaInimigos() {
		return listaInimigos;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setListaInimigos(List<Pessoa> listaInimigos) {
		this.listaInimigos = listaInimigos;
	}

	public int getPosicaoNaMesa() {
		return posicaoNaMesa;
	}

	public void setPosicaoNaMesa(int posicaoNaMesa) {
		this.posicaoNaMesa = posicaoNaMesa;
	}

	@Override
	public String toString() {
		return nome;
	}

}
