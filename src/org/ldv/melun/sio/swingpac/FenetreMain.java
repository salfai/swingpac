package org.ldv.melun.sio.swingpac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import org.ldv.melun.sio.swingpac.utils.PackageUtil;

/**
 * D�finition de la scene du jeu et instanciation des objets.
 * 
 * @author lyc�e L�onard de Vinci - Melun - SIO-SLAM
 */
public class FenetreMain extends JFrame implements ActionListener,
		MouseListener {
	// une constante (mot cl� final)
	// c'est un moyen tr�s pratique d'associer un �couteur d'�v�nement
	// � un g�n�rateur d'�v�nement.
	static final String ACTION_QUITTER = "Quitter";

	static final String ACTION_GO = "Go";

	private static final String PACKAGE_BIDULES = "org.ldv.melun.sio.swingpac.etudiants";

	private static final String ACTION_CHALLENGE = "Challenge";

	private final String ACTION_PAUSE = "Pause";

	private JMenuItem mnPause;

	private int nbParties;

	/**
	 * lieu o� se mouvent les bidules
	 */
	private JPanel laScene;

	/**
	 * zone pr�sentant des informations textuelles � l'utilisateur
	 */
	private JLabel infos;

	/**
	 * pour stocker les bidules sortis de la scene lors d'une partie
	 */
	private List<Bidule> deadBidules;

	/**
	 * pour connaitre le score d'une classe de bidules au cours de plusieurs
	 * parties, cl� = le nom de la classe, valeur associ�e = nombre de fois que
	 * la classe � gagner une partie
	 */
	private HashMap<String, Integer> winerClasseBidules;

	private Bidule currentBidule;

	private boolean challenge;

	// constructeur
	public FenetreMain() {
		// appel un constructeur de son parent
		super("SwingPac");
		// effet : donne un titre � la fen�tre

		this.deadBidules = new ArrayList<Bidule>();
		this.winerClasseBidules = new HashMap<String, Integer>();

		// l'application s'arr�te lorsque cette fen�tre sera ferm�e.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// pas de gestionnaire de positionnement
		setLayout(new BorderLayout());

		laScene = new JPanel(true);
		// pas de gestionnaire de positionnement pour la sence
		laScene.setLayout(null);

		infos = new JLabel();
		infos.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		infos.setText("test");

		this.add(laScene, BorderLayout.CENTER);
		this.add(infos, BorderLayout.SOUTH);

		// initialisation de la fen�tre
		init();
	}

	private void init() {
		// on a besoin de cr�er une barre de menus
		JMenuBar menuBar;
		// et un menu
		JMenu menuFichier;

		// cr�ation dela barre de menus
		menuBar = new JMenuBar();
		// construisons le premier menu
		menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic(KeyEvent.VK_F);
		menuFichier.getAccessibleContext().setAccessibleDescription(
				"Menu permettant d'acc�der � une commande pour quitter");

		// cr�ation de la commande "quitter"
		JMenuItem mnItemQuitter = new JMenuItem(ACTION_QUITTER, KeyEvent.VK_Q);
		mnItemQuitter.getAccessibleContext().setAccessibleDescription(
				"Quitter le programme");

		// mnItemQuitter.setActionCommand(ACTION_QUITTER);

		// le menu Fichier contient la commande Quitter
		menuFichier.add(mnItemQuitter);
		// menu.addSeparator();
		// la barre de menus contient le menu Fichier
		menuBar.add(menuFichier);
		JMenu jeu = new JMenu("Jeu");
		jeu.setMnemonic(KeyEvent.VK_J);
		JMenuItem mn = new JMenuItem("Jouer une partie", KeyEvent.VK_G);
		mn.setActionCommand(ACTION_GO);
		// l'instance de cette fen�tre est � l'�coute d'une action sur ce menu
		mn.addActionListener(this);
		jeu.add(mn);

		mn = new JMenuItem("Challenge", KeyEvent.VK_H);
		mn.setActionCommand(ACTION_CHALLENGE);
		// l'instance de cette fen�tre est � l'�coute d'une action sur ce menu
		mn.addActionListener(this);
		jeu.add(mn);

		jeu.addSeparator();

		mnPause = new JMenuItem("Start/Stop", KeyEvent.VK_P);
		mnPause.setActionCommand(ACTION_PAUSE);
		// l'instance de cette fen�tre est � l'�coute d'une action sur ce menu
		mnPause.addActionListener(this);
		jeu.add(mnPause);

		menuBar.add(jeu);

		// TODO : ajouter une commande Pause qui stoppe le timer de tous les
		// objets
		// Bidule.

		// on ajoute la barre de menu � la fen�tre
		setJMenuBar(menuBar);

		// l'instance de cette fen�tre est � l'�coute d'une action sur ce menu
		mnItemQuitter.addActionListener(this);

		laScene.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		laScene.setBackground(Color.WHITE);

		// TODO : d�finir une taille en fonction de la taille de l'�cran
		// par exemple le 1/4 de l'�cran pour des grands �crans, ou 1/2 ...
		setSize(500, 500);
	}

	/**
	 * Injecte des objets Bidule dans cette instance de fen�tre
	 */
	private void go() {
		// r�cup�re la liste des classes du package en question
		String[] classes = PackageUtil.getClasses(PACKAGE_BIDULES);
		//
		// List<String> test = new ArrayList<String>();
		// for (int i = 0; i < classes.length; i++) {
		// test.add(classes[i]);
		// test.add(classes[i]);
		// test.add(classes[i]);
		// test.add(classes[i]);
		// }

		List<String> classesShuffles = /* test; */Arrays.asList(classes);

		// change l'ordre des �l�ments dans le tableau
		Collections.shuffle(classesShuffles);
		System.out.println(classesShuffles);

		// on instancie les classes (un objet par class)
		// et l'ajoute � la scene (fenetre)
		String erreurs = "";

		int margeBidule = 4;
		int largeurCadreBidulle = Bidule.TAILLE_BIDULE + margeBidule;

		// mettre les bidules dans le cadre en tentant d'�viter les
		// chevauchements...
		int xDansScene = 2;
		int yDansScene = 2;

		System.out.println("Largeur de la fen�tre = " + getWidth());

		for (int i = 0; i < classesShuffles.size(); i++) {
			try {
				Bidule bidule = (Bidule) Class.forName(
						PACKAGE_BIDULES + "." + classesShuffles.get(i))
						.newInstance();
				bidule.stop();

				// faut-il initialier le dictionnaire ?
				if (!winerClasseBidules.containsKey(PACKAGE_BIDULES + "."
						+ classesShuffles.get(i)))
					// oui
					winerClasseBidules.put(PACKAGE_BIDULES + "."
							+ classesShuffles.get(i), 0);

				bidule.addMouseListener(this);

				if (xDansScene + Bidule.TAILLE_BIDULE > laScene.getWidth()) {
					xDansScene = 0;
					yDansScene += largeurCadreBidulle;
				}

				bidule.setLocation(xDansScene, yDansScene);

				xDansScene += largeurCadreBidulle;

				// ajout l'objet � la fen�tre
				laScene.add(bidule);
			} catch (Exception e) {
				erreurs = e.getMessage();
			}
		}
		if (!"".equals(erreurs))
			JOptionPane.showMessageDialog(null, erreurs);
		nbParties++;
		this.getContentPane().invalidate();
		this.repaint();
	}

	/**
	 * Appel� par les commandes du menu
	 */
	public void actionPerformed(ActionEvent evt) {
		String action = evt.getActionCommand();
		if (action.equals(ACTION_QUITTER)) {
			System.exit(0);
		} else if (action.equals(ACTION_GO)) {
			go();
		} else if (action.equals(ACTION_CHALLENGE)) {
			challenge = true;
			go();
		} else if (action.equals(ACTION_PAUSE)) {
			startStopFlip();
		}
	}

	private void startStopFlip() {
		System.out.println("nb compos : " + this.laScene.getComponentCount());
		Bidule b = null;
		for (Component obj : this.laScene.getComponents()) {
			if (obj instanceof Bidule) {
				b = (Bidule) obj;
				if (b.isRunning()) {
					b.stop();
				} else {
					b.start();
				}
			}
		}
		if (b != null) {
			if (b.isRunning())
				mnPause.setText("Stop");
			else
				mnPause.setText("Start");
		}
	}

	public void addDeadBidule(Bidule biduleQuiMeurt) {
		deadBidules.add(biduleQuiMeurt);
		laScene.remove(biduleQuiMeurt);
		System.out.println("Mort de " + biduleQuiMeurt.getName());
		infos.setText("Mort de " + biduleQuiMeurt.getName());
		if (winerClasseBidules.size() - 1 == deadBidules.size()) {
			// fin de la partie, il ne reste qu'un bidule dans la scene...
			for (Component o : this.laScene.getComponents()) {
				if (o instanceof Bidule) {
					infos.setText("GAGNE : " + o.toString());
					laScene.remove(o);
					deadBidules.clear();
					mnPause.setText("Start");
					if (!challenge) {
						JOptionPane.showMessageDialog(null,
								"PARTIE GAGN�E PAR : " + o.getName());
					} else {
						deadBidules.clear();
						// ajoute 1 � la classe concern�e
						winerClasseBidules
								.put(o.getClass().getName(), winerClasseBidules
										.get(o.getClass().getName()) + 1);
						String winer = getWiner();
						if (null == winer) {
							go(); // again
							startStopFlip();
						} else {
							// fin, affiche le r�sultat
							List<String> resultat = new ArrayList<String>();
							for (String classe : winerClasseBidules.keySet()) {
								resultat.add(winerClasseBidules.get(classe)
										+ " : " + classe);
							}
							Collections.sort(resultat);
							StringBuffer res = new StringBuffer();
							for (int i = resultat.size() - 1; i >= 0; i--) {
								res.append(resultat.get(i) + "\n");
							}
							System.out.println("(nb parties = " + nbParties
									+ ") \nGAGNANT = " + winer + "\n"
									+ res.toString());
							System.out.println("challenge CLASSE GAGNANTE : "
									+ winer);
							JOptionPane.showMessageDialog(null,
									"(nb parties = " + nbParties
											+ ") \nGAGNANT = " + winer + "\n"
											+ res.toString());
							infos.setText("CHALLENGE CLASSE GAGNANTE : "
									+ winer);
							challenge = false;
						}
						break;
					}
				}
			}
		}
	}

	/**
	 * Recherche la classe ayant obtenu 3 vainqueurs
	 * 
	 * @return le vainqueur ou null
	 */
	private String getWiner() {
		for (String classe : winerClasseBidules.keySet()) {
			if (winerClasseBidules.get(classe) == 3)
				return classe;
		}
		return null;
	}

	// ///////////////////////////////////////// m�thodes MouseMListener
	/**
	 * Les bidules sont �cout�s par this
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (currentBidule != e.getSource()) {
			currentBidule = (Bidule) e.getSource();
			infos.setText(e.getSource().toString());
			currentBidule.setSelected(true);
			currentBidule.revalidate();
			currentBidule.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (currentBidule == null)
			return;

		currentBidule.setSelected(false);
		currentBidule.revalidate();
		currentBidule.repaint();
		currentBidule = null;
		infos.setText(" ");
	}

}// FentreMain