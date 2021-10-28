package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
		if (!m.getNaoAlocados().equals(listaInimigos)) {
			possiveisAlocacoes.removeAll(listaInimigos);
		}
		if (possiveisAlocacoes.isEmpty()) {
			if (m.getNaoAlocados().isEmpty()) {
				return null;
			}
			return m.getNaoAlocados().get(random.nextInt(m.getNaoAlocados().size()));
		}
		Pessoa p = possiveisAlocacoes.get(random.nextInt(possiveisAlocacoes.size()));
		return p;
	}

	public void adicionaInimigo(Pessoa p) {
		this.listaInimigos.add(p);
		p.listaInimigos.add(this);
	}

	public List<Pessoa> getListaInimigos() {
		return listaInimigos;
	}

	public Boolean verificaConflitosVizinhanca(Mesa m) {

		int contador = 0;
		int ultimaPosicao = m.getAlocados().size() - 1;
		posicaoNaMesa = m.getAlocados().indexOf(this);

		if (posicaoNaMesa == 0) {
			if (this.getListaInimigos().contains(m.getAlocados().get(posicaoNaMesa + 1))
					|| this.getListaInimigos().contains(m.getAlocados().get(ultimaPosicao)))
				contador++;
		} else if (posicaoNaMesa == ultimaPosicao) {
			if (this.getListaInimigos().contains(m.getAlocados().get(0))
					|| this.getListaInimigos().contains(m.getAlocados().get(posicaoNaMesa - 1)))
				contador++;
		} else {
			if (this.getListaInimigos().contains(m.getAlocados().get(posicaoNaMesa + 1))
					|| this.getListaInimigos().contains(m.getAlocados().get(posicaoNaMesa - 1)))
				contador++;
		}

		if (contador > 0)
			return true;

		return false;
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
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return nome;
	}

}
