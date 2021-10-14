package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mesa {

	private List<Pessoa> alocados;
	private List<Pessoa> naoAlocados;
	private int numConflitos;
	
	public Mesa(List<Pessoa> pessoas) {
		alocados = new ArrayList<Pessoa>();
		naoAlocados = pessoas;
		numConflitos = 0;
	}
	
	public Mesa() {
		alocados = new ArrayList<Pessoa>();
		naoAlocados =  new ArrayList<Pessoa>();
		numConflitos = 0;
	}
	
	public Mesa popularMesa(List<Pessoa> pessoas) {
		if(pessoas.isEmpty()) {
			return this;
		}
		if(alocados.isEmpty()) {
			Random random = new Random();
			int aleatorio = random.nextInt(pessoas.size());
			this.alocarPessoa(pessoas.get(aleatorio));
			this.removeNaoAlocados(pessoas.get(aleatorio));
			this.popularMesa(naoAlocados);
		}else {
			Pessoa ultimoAlocado = alocados.get((alocados.size()-1));
			Pessoa pessoaParaAlocar = ultimoAlocado.buscarVizinho(this,ultimoAlocado.getListaInimigos(),ultimoAlocado);
			if(pessoaParaAlocar.equals(null)) {
				return this;
			}
			this.alocarPessoa(pessoaParaAlocar);
			this.removeNaoAlocados(pessoaParaAlocar);
			this.popularMesa(naoAlocados);
		}
		return this;
	}
	
	public List<Pessoa> getAlocados() {
		return alocados;
	}
	
	public void adicionarNaoAlocados(Pessoa p) {
		this.naoAlocados.add(p);
	}
	
	public void removeNaoAlocados(Pessoa p) {
		this.naoAlocados.remove(p);
	}
	
	public void alocarPessoa(Pessoa p) {
		this.alocados.add(p);
	}
	
	public void removeAlocados(Pessoa p) {
		this.alocados.remove(p);
	}
	
	public void setAlocados(List<Pessoa> alocados) {
		this.alocados = alocados;
	}
	public List<Pessoa> getNaoAlocados() {
		return naoAlocados;
	}
	public void setNaoAlocados(List<Pessoa> naoAlocados) {
		this.naoAlocados = naoAlocados;
	}
	public int getNumConflitos() {
		return numConflitos;
	}
	public void setNumConflitos(int numConflitos) {
		this.numConflitos = numConflitos;
	}

	@Override
	public String toString() {
		return "Mesa [alocados=" + alocados + "]";
	}
	
}
