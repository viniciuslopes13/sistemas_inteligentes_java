package br.edu.ufersa.problemaDaMesa;

import java.awt.Component;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart extends JFrame {

	 static int contador = 0;
	 private String mesa;
	
	public PieChart(Mesa m) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Grafico");
		setSize(950, 700);
		setLocationRelativeTo(null);
		criaGrafico(m);
		setVisible(true);
	}

	public void criaGrafico(Mesa m) {
		if(contador==0)
			mesa = "INICIAL";
		else
			mesa = "FINAL";
		DefaultPieDataset pizza = new DefaultPieDataset();
		for (Pessoa p : m.getAlocados()) {
			if(p.verificaConflitosVizinhanca(m)) {
				pizza.setValue(p.getNome()+"**"+"\nConflitos "+p.getListaInimigos()+"", 1);
			}else {
				pizza.setValue(p.getNome()+"\nConflitos "+p.getListaInimigos()+"", 1);
			}
		}
		JFreeChart grafico = ChartFactory.createPieChart(
				"Pessoas Alocadas na Mesa "+mesa+"\n Conflitos: " + m.getNumConflitos() + "", pizza, false, true, false);
		ChartPanel painel = new ChartPanel(grafico);
		add(painel);
		contador++;
	}

}
