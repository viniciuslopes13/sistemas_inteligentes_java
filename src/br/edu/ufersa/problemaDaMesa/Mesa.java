package br.edu.ufersa.problemaDaMesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
	
	public void populaMesaAleatorio() {
		Random random = new Random();
		int tamanhoNaoAlocados = this.naoAlocados.size();
		if(tamanhoNaoAlocados==0) {
			return;
		}
		int aleatorio = random.nextInt(tamanhoNaoAlocados);
		this.alocarPessoa(this.naoAlocados.get(aleatorio));
		this.removeNaoAlocados(this.naoAlocados.get(aleatorio));
		this.populaMesaAleatorio();
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
		
		numConflitos = 0;
		
		for(int i=0; i<this.alocados.size(); i++) {
			if(i==(this.alocados.size()-1)) {
				if(alocados.get(i).getListaInimigos().contains(alocados.get(0))){
					numConflitos++;
				}
			}else {
				if(alocados.get(i).getListaInimigos().contains(alocados.get(i+1))){
					numConflitos++;
				}
			}
		}
		
		return numConflitos;
	}
	public void setNumConflitos(int numConflitos) {
		this.numConflitos = numConflitos;
	}

	public Mesa clone() {
		List<Pessoa> list = new ArrayList<Pessoa>();
		for(Pessoa p : this.alocados) {
			list.add(p);
		}
		Mesa mesa = new Mesa(list);
		return mesa;
	}
	
	public Mesa sucessor() {
		Mesa m1;
		Mesa m2;
		while(true) {
			m1 = this.clone();
			m1.populaMesaAleatorio();
			m2 = this.clone();
			m2.populaMesaAleatorio();
			if(!m1.equals(m2)) {
				break;
			}
		}
		if(m2.equals(this) || m1.equals(this)) {
			this.sucessor();
		}
		if(m2.getNumConflitos() < m1.getNumConflitos()) {
			return m2;
		}
		return m1;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(alocados);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		return Objects.equals(alocados, other.alocados);
	}

	@Override
	public String toString() {
		return "Mesa [alocados=" + alocados + "]";
	}
	
}
