package org.ldv.melun.sio.swingpac.etudiants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import org.ldv.melun.sio.swingpac.Bidule;

public class Choudhry extends Bidule {

  private int nbDeplacements;

  public Choudhry() {
    super("Choudhry");
    setBackground(Color.YELLOW);
    nbDeplacements = 0;
    
  }
  
  /*public void paintComponent(Graphics g) {
	    Toolkit kit = Toolkit.getDefaultToolkit();
	    		if (isGoDown()&&isGoRight()){
	    			Image icone = kit.getImage("assets/Adrien/adrienBasDroit.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoDown()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/Adrien/adrienBasGauche.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoRight()){
	    			Image icone = kit.getImage("assets/Adrien/adrienHautDroit.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}
	    		else if (isGoUp()&&isGoLeft()){
	    			Image icone = kit.getImage("assets/Adrien/adrienHautGauche.png" );
	    			g.drawImage(icone ,0, 0,getHeight() ,getWidth(), null);}

	}*/
  public List<Bidule> getCollisions() {
        return getBidulesProches(0);
      }
  
  @Override
  public void doMove() {
    nbDeplacements++;
    
    
    
    super.doMove();
        List<Bidule> bidules = this.getCollisions();
        for (Bidule autreBidule : bidules) {
          if (this.isGoRight() && !autreBidule.isGoLeft()) {
        	  goOnTop();
          } else if (this.isGoLeft() && !autreBidule.isGoRight()) {
            goOnDown();
          } else if (this.isGoUp() && !autreBidule.isGoDown()) {
            goOnLeft();
          } else if (this.isGoDown() && !autreBidule.isGoUp()) {
            goOnLeft();
          } else { // collision frontale
            goOnTop();
          }
        }
      
  }
  
  
}
