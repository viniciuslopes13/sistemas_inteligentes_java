package br.edu.ufersa.aprofundamentoIterativo;

import java.util.Arrays;

public class Estado {
	
	int[][] state = new int[3][3];
	Estado pai;
	int profundidade;
	
	Estado(){
	}

	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public Estado getPai() {
		return pai;
	}

	public void setPai(Estado pai) {
		this.pai = pai;
	}
	
	public int getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}

	public Estado clone() {
		int tamanhoMatriz = this.state.length;
		int[][] mat = new int[tamanhoMatriz][tamanhoMatriz];
		for(int i=0;i<tamanhoMatriz;i++) {
			for(int j=0;j<tamanhoMatriz;j++) {
				mat[i][j] = this.state[i][j];
			}
		}
		Estado outro = new Estado();
		outro.setState(mat);
		return outro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(state);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		return Arrays.deepEquals(state, other.state);
	}
	
}

