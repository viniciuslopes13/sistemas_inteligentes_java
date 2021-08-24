package br.edu.ufersa.profundidade;

public class Estado {
	
	int[][] state = new int[2][2];
	Estado pai;
	
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
	
}

