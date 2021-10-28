package br.edu.ufersa.problemaDaMesa;

import java.util.concurrent.ThreadLocalRandom;

public class geradorDeNomes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// letras maisculas 65 - 90
		// letras minúsculas 97 - 122

		ThreadLocalRandom gerador = ThreadLocalRandom.current();
		
		int tamanhoNome = gerador.nextInt(3, 10);

		StringBuilder nome = new StringBuilder();

		for (int i = 1; i < tamanhoNome; i++) {
			char letra = (char) gerador.nextInt(97, 122);
			nome.append(letra);
		}

		System.out.println(nome);
	}

}
