package org.ldv.melun.sio.swingpac.etudiants;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import org.ldv.melun.sio.swingpac.Bidule;

public class Toto extends Bidule {

  public Toto(String name) {
    super(name);
  }

  public Toto() {
    super("Vurpillot");
  }
  
  public void paintComponent(Graphics g) {
	    Toolkit kit = Toolkit.getDefaultToolkit();
	    		if (isGoDown()&&isGoRight()){
	    			Image icone = kit.getImage("assets/Vurpillot/VurpillotBasDroit.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoDown()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/Vurpillot/VurpillotBasGauche.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoRight()){
	    			Image icone = kit.getImage("assets/Vurpillot/VurpillotHautDroit.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/Vurpillot/VurpillotHautGauche.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	}
  
  @Override
  public void doMove() {
    super.doMove();
  }

  @Override
  protected void doAfterImpactByOther() {
    super.doAfterImpactByOther();
    if (isGoDown())
      goOnTop();

  }

  
  
}
