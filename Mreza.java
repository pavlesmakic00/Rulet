package v1;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashSet;

import v1.Polje.Status;

public class Mreza extends Panel{

	Igra owner;
	private ArrayList<Polje> polja=new ArrayList<>();
	HashSet<Integer> hashset=new HashSet<>();
	int vrste = 4;
	int kolone = 5;
	public Mreza(Igra owner,int x,int y) {
		this.owner = owner;
		this.vrste = x;
		this.kolone=y;
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(vrste,kolone,3,3));
		int br = 0;
		for (int i = 0;i<vrste;i++) {
		for (int j = 0;j<kolone;j++) {					
			Polje p= new Polje(this,br);
			polja.add(p);			
			add(p);
			br++;	
		}	
		}
		inicijalizuj();
	}
	void inicijalizuj() {
		for (Polje p:polja) {
			if (p.getStatus()==Status.IZABRANO) {
				hashset.add(p.getBroj());
			}
		}
	}
	
	
}
