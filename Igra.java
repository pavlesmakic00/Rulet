package v1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {

	private Mreza mreza = new Mreza(this,4,5);
	
	Button igraj1 = new Button ("Igraj");
	
	Panel kvota = new Panel();
	Label kvota2 = new Label("");
	
	Panel dobitak = new Panel();
	Label dobitak2 = new Label("");
	
	TextField ulog2 = new TextField("100");
	
	
	private void populateWindow() {
		Panel statusnaTraka = new Panel();
		Label status = new Label();
		
		statusnaTraka.add(status);
		statusnaTraka.setBackground(Color.darkGray);
		
		Panel upravljanje = new Panel();	
		upravljanje.setLayout(new GridLayout(5,1,0,0));
		upravljanje.setBackground(Color.LIGHT_GRAY);
		
		Panel balans = new Panel();
		Label balans1 = new Label("Balans: ");	
		Label balans2 = new Label("0");
		balans.add(balans1);
		balans.add(balans2);
		
		
		Panel ulog = new Panel();
		Label ulog1 = new Label("Ulog: ");
		
		ulog.add(ulog1);
		ulog.add(ulog2);
		
		
		Label kvota1 = new Label("Kvota: ");
		
		kvota.add(kvota1);
		kvota.add(kvota2);	
		
		
		Label dobitak1 = new Label("Dobitak: ");
		
		dobitak.add(dobitak1);
		dobitak.add(dobitak2);	
		
		
		Panel igraj = new Panel(new FlowLayout(FlowLayout.RIGHT));
		
		igraj1.setEnabled(false);
		igraj.add(igraj1);
		
		igraj1.addActionListener((ae)->{
			Generator g = new Generator();
			int meta = g.generisi(0,mreza.kolone*mreza.vrste - 1);
			status.setText(Integer.toString(meta));
			statusnaTraka.revalidate();
			
			double trenbalans = Double.parseDouble(balans2.getText());
			double trendobitak = Double.parseDouble(dobitak2.getText());
			
			if (mreza.hashset.contains(meta)) {
				
				statusnaTraka.setBackground(Color.GREEN);
				status.setBackground(Color.GREEN);						
				trenbalans+=trendobitak;
										
			}else {
				statusnaTraka.setBackground(Color.RED);	
				status.setBackground(Color.RED);
				trenbalans-= Double.parseDouble(ulog2.getText());;
			}
			balans2.setText(Double.toString(trenbalans));
			balans.revalidate();
			
		});
			
		upravljanje.add(balans);
		upravljanje.add(ulog);
		upravljanje.add(kvota);
		upravljanje.add(dobitak);
		upravljanje.add(igraj);
		
		
	    add(mreza,BorderLayout.CENTER);
		add(statusnaTraka,BorderLayout.SOUTH);
		add(upravljanje,BorderLayout.EAST);
	}
	public Igra() {
		setBounds(700,200,800,800);
		setTitle("Igra");
		setResizable(false);
		
		
		populateWindow();
		pack();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
			
		});	
		setVisible(true);
	}
	
	

	public Mreza getMreza() {
		return mreza;
	}
	public static void main(String[] args) {
		new Igra();
	}
}
