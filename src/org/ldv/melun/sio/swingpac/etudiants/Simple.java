package org.ldv.melun.sio.swingpac.etudiants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import org.ldv.melun.sio.swingpac.Bidule;

public class Simple extends Bidule {

  
  public Simple() {
    super("Greg");
    setBackground(Color.GREEN);    
  }
  
  public void paintComponent(Graphics g) {
	    Toolkit kit = Toolkit.getDefaultToolkit();
	    		if (isGoDown()&&isGoRight()){
	    			Image icone = kit.getImage("assets/Greg/GregBasDroit.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoDown()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/Greg/GregBasGauche.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoRight()){
	    			Image icone = kit.getImage("assets/Greg/GregHautDroit.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/Greg/GregHautGauche.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
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
