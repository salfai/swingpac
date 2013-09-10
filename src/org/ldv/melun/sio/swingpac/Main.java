package org.ldv.melun.sio.swingpac;

import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * 
 * Lanceur du l'application
 * @author lycée Léonard de Vinci - Melun - SIO-SLAM
 *
 */
public class Main {
  public static void main(String[] args) {
    JFrame f = new FenetreMain();
    //f.setPreferredSize(new Dimension(100,100));
    f.setVisible(true);
    f.setTitle("Jeux");
  }
}
