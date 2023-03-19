	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;

	public class Morpion extends JFrame implements ActionListener {
	    private JButton[][] cases = new JButton[3][3];
	    private int tour = 1;
	    private int reponse;
	    
	    public Morpion() {
	        setTitle("Morpion");
	        setSize(300, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new GridLayout(3, 3));
	        // CrÃ©ation de la barre de menu
	        JMenuBar menuBar = new JMenuBar();
	        JMenu menu = new JMenu("Menu");
	        JMenuItem menuItemRejouer = new JMenuItem("Rejouer");
	        JMenuItem menuItemAide = new JMenuItem("A propos");
	        JMenuItem menuItemQuitter = new JMenuItem("Quitter");
	        menuItemRejouer.addActionListener(this);
	        menuItemAide.addActionListener(this);
	        menuItemQuitter.addActionListener(this);
	        menu.add(menuItemRejouer);
	        menu.add(menuItemAide);
	        menu.add(menuItemQuitter);
	        menuBar.add(menu);
	        setJMenuBar(menuBar);
	        
	        
	        // Initialisation des boutons
	        for(int i=0; i<3; i++) {
	            for(int j=0; j<3; j++) {
	                cases[i][j] = new JButton("");
	                cases[i][j].addActionListener(this);
	                add(cases[i][j]);
	            }
	        }
	        setVisible(true);
	    }
	    
	    public void actionPerformed(ActionEvent e) {
	        // Vérifie si l'action provient d'un bouton ou d'un élément de menu
	    	Object source = e.getSource();
	        if(source instanceof JButton) {
	            JButton button = (JButton) source;
	            
	            
	        if(button.getText().equals("")) {
	            if(tour%2 == 1)
	                button.setText("X");
	            else
	                button.setText("O");
	            tour++;
	            if(verifierGagne() || tour>9)
	                finPartie();
	        }
	    } else if(source instanceof JMenuItem) {
            JMenuItem menuItem = (JMenuItem) source;
            
            if(menuItem.getText().equals("Rejouer")) {
                nouvellePartie();
            }
            else if(menuItem.getText().equals("A propos")) {
                afficherAide();
            }
            else if(menuItem.getText().equals("Quitter")) {
                quitterJeu();
            }
        }
	    }
	    private boolean verifierGagne() {
	        for(int i=0; i<3; i++) {
	            if(!cases[i][0].getText().equals("") && cases[i][0].getText().equals(cases[i][1].getText()) && cases[i][1].getText().equals(cases[i][2].getText()))
	                return true;
	            if(!cases[0][i].getText().equals("") && cases[0][i].getText().equals(cases[1][i].getText()) && cases[1][i].getText().equals(cases[2][i].getText()))
	                return true;
	        }
	        
	        if(!cases[0][0].getText().equals("") && cases[0][0].getText().equals(cases[1][1].getText()) && cases[1][1].getText().equals(cases[2][2].getText()))
	            return true;
	        if(!cases[0][2].getText().equals("") &&  cases[0][2].getText().equals(cases[1][1].getText()) && cases[1][1].getText().equals(cases[2][0].getText()))
	            return true;
	        return false;
	    }
	    
	    private void finPartie() {
	        for(int i=0; i<3; i++)
	            for(int j=0; j<3; j++)
	                cases[i][j].setEnabled(false);
	        
	        if (verifierGagne()) {
	        	String gagnant;
	        	if (tour % 2 == 0) {
	        	    gagnant = "X";
	        	    JOptionPane.showMessageDialog(this, "Le joueur " + gagnant + " a gagné !");
	        	} else {
	        	    gagnant = "O";
	        	    JOptionPane.showMessageDialog(this, "Le joueur " + gagnant + " a gagné !");
	        	}
	           
	        } else {
	            JOptionPane.showMessageDialog(this, "Match nul !");
	        }
	        
	        reponse =  JOptionPane.showConfirmDialog(this, "Match nul ! Voulez-vous rejouer ?");
            if (reponse == JOptionPane.YES_OPTION) {
            	 nouvellePartie();
            }else {
            	quitterJeu();
            }
	    }
	    
	    private void nouvellePartie() {
            tour = 1;           
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    cases[i][j].setText("");
                    cases[i][j].setEnabled(true);
                }
            }
        }
        
        private void afficherAide() {
            JOptionPane.showMessageDialog(this, "Le Morpion se joue à deux joueurs, l'un utilise X et l'autre O. Les joueurs doivent placer leurs symboles dans les cases d'une grille de 3x3. Le joueur qui parvient à aligner trois symboles horizontalement, verticalement ou en diagonale a gagné.");
        }
        
        private void quitterJeu() {
            System.exit(0);
        }
	    public static void main(String[] args) {
	        new Morpion();
	    }
	}

