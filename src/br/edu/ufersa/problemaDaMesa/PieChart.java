package br.edu.ufersa.problemaDaMesa;

import java.awt.Component;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart extends JFrame {

	public PieChart(Mesa m) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Grafico");
		setSize(950, 700);
		setLocationRelativeTo(null);
		criaGrafico(m);
		setVisible(true);
	}

	public void criaGrafico(Mesa m) {
		DefaultPieDataset pizza = new DefaultPieDataset();
		for (Pessoa p : m.getAlocados()) {
			pizza.setValue(p.getNome()+"\nConflitos "+p.getListaInimigos()+"", 1);
		}
		JFreeChart grafico = ChartFactory.createPieChart(
				"Pessoas Alocadas na Mesa INICIAL\n Conflitos: " + m.getNumConflitos() + "", pizza, true, true, false);
		ChartPanel painel = new ChartPanel(grafico);
		add(painel);
	}

}
