package br.edu.ufersa.si;

public class Estado {
	
	int[][] state = new int[3][3];
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
		int[][] mat = new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				mat[i][j] = this.state[i][j];
			}
		}
		Estado outro = new Estado();
		outro.setState(mat);
		return outro;
	}
	
}
