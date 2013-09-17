package org.ldv.melun.sio.swingpac.etudiants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import org.ldv.melun.sio.swingpac.Bidule;

public class Lisik extends Bidule {

  
  private Bidule b;

public Lisik() {
    super("Lisik");
    setBackground(Color.BLACK);
  }
  
  public void paintComponent(Graphics s) {
	    Toolkit kit = Toolkit.getDefaultToolkit();
	    		if (isGoDown()&&isGoRight()){
	    			Image icone = kit.getImage("assets/Lisik/LisikBasDroit.png" );
	    			s.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoDown()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/Lisik/LisikBasGauche.png" );
	    			s.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoRight()){
	    			Image icone = kit.getImage("assets/Lisik/LisikHautDroit.png" );
	    			s.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/Lisik/LisikHautGauche.png" );
	    			s.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}

	}
  
  @Override
  public void doMove() {  
    super.doMove(); 
  }
  
  
  
  
 @Override
  protected void doAfterImpactByOther() {
    super.doAfterImpactByOther();
  }

  
}
