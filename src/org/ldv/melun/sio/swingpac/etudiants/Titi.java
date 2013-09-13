package org.ldv.melun.sio.swingpac.etudiants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import org.ldv.melun.sio.swingpac.Bidule;

public class Titi extends Bidule {

  private int nbDeplacements;

  public Titi() {
    super("Adrien");
    setBackground(Color.YELLOW);
    nbDeplacements = 0;
  }
  
  public void paintComponent(Graphics g) {
	    Toolkit kit = Toolkit.getDefaultToolkit();
	    		//Image icone;
	    		if (isGoDown()&&isGoRight()){
	    			Image icone = kit.getImage("assets/adrienBasDroit.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoDown()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/adrienBasGauche.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoRight()){
	    			Image icone = kit.getImage("assets/adrienHautDroit.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/adrienHautGauche.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		
	    		
	    		
	    		/*else if (isGoRight()){
	    			Image icone = kit.getImage("assets/adrienDroite.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()){
	    			Image icone = kit.getImage("assets/adrienHaut.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoDown()){
	    			Image icone = kit.getImage("assets/adrienBas.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}*/
	    		
	           
	    		//System.out.println(icone);   
	}

  @Override
  public void doMove() {
    nbDeplacements++;
    
    //setIncX(0);
    super.doMove();
    
    // tous les 200 deplacements et si descente
    if (isGoDown() && nbDeplacements % 200 == 0) 
      if (isGoLeft())
        goOnRight();
      else
        goOnLeft();
  }

}
