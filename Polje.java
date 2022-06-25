package v1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Polje extends Canvas{

	enum Status{
		SLOBODNO,
		IZABRANO
	}
	private Mreza owner;
	private int broj;
	private Status status = Status.SLOBODNO;
	
	
	public Polje(Mreza owner,int broj) {
		System.out.println("Usao1");
		this.owner = owner;
		this.broj = broj;
		setPreferredSize(new Dimension(75,75));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {	
				if (status==Status.SLOBODNO) {
				setStatus(Status.IZABRANO);
				owner.hashset.add(broj);
				}
				else if (status==Status.IZABRANO) {
				setStatus(Status.SLOBODNO);
				owner.hashset.remove(broj);
				}
				if (owner.hashset.size()>=1)
				owner.owner.igraj1.setEnabled(true);
				else
					owner.owner.igraj1.setEnabled(false);
				repaint();
				azuriraj();
			}	
		});
	}
	@Override
	public void paint(Graphics g) {		
		int offset;
		if (broj<=9) offset = 8;
		else offset = 15;
		if (this.status==Status.SLOBODNO) {				
			System.out.println("Usao2");
			g.setColor(Color.orange);
			g.fillRect(0,0, getWidth() , getHeight());
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.BLACK);
		}else if(this.status==Status.IZABRANO)
		{		
			System.out.println("Usao4");		
			g.setColor(Color.orange);
			g.fillRect(0,0, getWidth() , getHeight());
			g.setColor(Color.blue);
			g.fillOval(0,0, getWidth() , getHeight());
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.white);
		}
			g.drawString(String.valueOf(broj), getWidth()/2 - offset,getHeight()/2+10);	
	}
	
	private void azuriraj() {
		if (owner.hashset.size()==0) {
			owner.owner.kvota2.setText("");
			owner.owner.dobitak2.setText("");
			owner.owner.kvota.revalidate();	
			owner.owner.dobitak.revalidate();	
			return;
		}
		int dim = owner.vrste * owner.kolone;
		int izabrani = owner.hashset.size();
		double x =  Math.round(((double)dim/(double)izabrani)*100.0)/100.0;
		owner.owner.kvota2.setText(Double.toString(x));
		owner.owner.kvota.revalidate();		
		
		double kvota = x;
		double dobitak =Math.round((kvota * Integer.parseInt(owner.owner.ulog2.getText()))*100.0)/100.0;
		owner.owner.dobitak2.setText(Double.toString(dobitak));
		owner.owner.dobitak.revalidate();
		
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status s) {
		this.status = s;
	}
	
	public int getBroj() {
		return broj;
	}
	
	
}
