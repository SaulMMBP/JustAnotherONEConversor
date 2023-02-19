/**
 * 
 */
package com.github.saulmmbp.main;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
import com.github.saulmmbp.main.ui.Window;

/**
 * Conversor de:
 * - Monedas
 * @author SAUL MALAGON MARTINEZ
 *
 */
public class JustAnotherONEConversor {
    
    private static Window window;

	/**
	 * Método main
	 * @param args
	 */
	public static void main(String[] args) {
	    init();
	}
	
	/**
	 * Inicializa la ventana principal
	 */
	private static void init() {
	    try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("No se pudo cargar el Look and Feel, se usará el Look And Feel por defecto");
            e.printStackTrace();
        }
	    
	    
	    window = new Window();
	    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    window.setSize(480, 320);
	    window.setResizable(false);
	    window.setTitle("Just Another ONE Conversor");
	    window.setVisible(true);
	}

}
