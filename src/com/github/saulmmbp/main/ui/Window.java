/**
 * 
 */
package com.github.saulmmbp.main.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import com.formdev.flatlaf.*;
import com.github.saulmmbp.main.controller.Controller;
import com.github.saulmmbp.main.conversion.types.*;

/**
 * Ventana de la aplicación
 * 
 * @author SAUL
 *
 */
public class Window extends JFrame {

    private static final long serialVersionUID = 2223138964903609919L;

    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JPanel pnlC;

    public Window() {
        initComponents();
    }

    /*
     * Inicializa y configura componentes
     */
    private void initComponents() {

        /* INITIALIZE */
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        pnlC = new JPanel();
        JMenuBar mnBar = new JMenuBar();
        JMenu mnAbout = new JMenu("About");
        JMenu mnOptions = new JMenu("Options");
        JMenuItem mnItmAbout = new JMenuItem("About");
        JCheckBoxMenuItem mnItmDarkMode = new JCheckBoxMenuItem("Modo Oscuro");
        JLabel lblConversor = new JLabel("Tipo de conversor");
        JLabel lblFrom = new JLabel("De:");
        JLabel lblTo = new JLabel("A:");
        JLabel lblConversion = new JLabel("0.0");
        JTextField tfFrom = new JTextField();
        JComboBox<ConversionType> cbConversor = new JComboBox<>();
        JComboBox<Convertible> cbFrom = new JComboBox<>();
        JComboBox<Convertible> cbTo = new JComboBox<>();
        JButton btnConvert = new JButton("Convertir");
        Controller controller = new Controller();

        /* CONFIGS */
        mnAbout.setMnemonic('A');
        mnOptions.setMnemonic('O');
        mnItmAbout.setMnemonic('A');
        mnAbout.add(mnItmAbout);
        mnOptions.add(mnItmDarkMode);
        mnBar.add(mnAbout);
        mnBar.add(mnOptions);

        setJMenuBar(mnBar);
        
        pnlC.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        pnlC.setLayout(layout);

        lblConversion.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        addItemsToComboBox(ConversionType.values(), cbConversor);
        addItemsToComboBox(Currency.values(), cbFrom);
        addItemsToComboBox(Currency.values(), cbTo);

        /* CONFIGS LISTENERS */
        mnItmAbout.addActionListener((event) -> {
            JOptionPane.showMessageDialog(this, "Este es un proyecto desarrollado para el programa"
                    + "\nONE de Oracle y Alura Latam.\n\nGracias a todos los que colaboran en este programa"
                    + "\nque nos impulsan a ser mejores."
                    + "\n\nLos quiero mucho <3", "About", JOptionPane.INFORMATION_MESSAGE);
        });
        
        mnItmDarkMode.addActionListener((event) -> {
            String laf = UIManager.getLookAndFeel().getName();
            try {
                if(laf.equals("FlatLaf Light")) {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                } else if(laf.equals("FlatLaf Dark")) {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                }
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(this, "No se pudo activar el modo oscuro, lo siento :C", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
        
        cbConversor.addActionListener((event) -> { // Cambia el tipo de conversión
            if (cbConversor.getSelectedItem() == ConversionType.TEMPERATURE) {
                cbFrom.removeAllItems();
                cbTo.removeAllItems();
                addItemsToComboBox(Temperature.values(), cbFrom);
                addItemsToComboBox(Temperature.values(), cbTo);
                
                tfFrom.setText("");
                lblConversion.setText("");
            } else if (cbConversor.getSelectedItem() == ConversionType.CURRENCY) {
                cbFrom.removeAllItems();
                cbTo.removeAllItems();
                addItemsToComboBox(Currency.values(), cbFrom);
                addItemsToComboBox(Currency.values(), cbTo);
                
                tfFrom.setText("");
                lblConversion.setText("");
            }
        });

        btnConvert.addActionListener((event) -> {
            Convertible from = (Convertible) cbFrom.getSelectedItem();
            Convertible to = (Convertible) cbTo.getSelectedItem();

            try {
                Float value = Float.parseFloat(tfFrom.getText());
                String valueConverted = controller.convert(value, from, to);
                lblConversion.setText(valueConverted);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingresa solo números por favor", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        /* ADD COMPONENTS TO WINDOW */
        /* NORTH */

        /* SOUTH */

        /* CENTER */
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.fill = GridBagConstraints.NONE;
        addComponentToPnlC(lblConversor, 0, 0, 2, 1);
        addComponentToPnlC(cbConversor, 1, 0, 2, 1);
        addComponentToPnlC(cbFrom, 3, 1, 1, 1);
        addComponentToPnlC(cbTo, 6, 1, 1, 1);
        addComponentToPnlC(btnConvert, 4, 0, 2, 1);
        constraints.fill = GridBagConstraints.BOTH;
        addComponentToPnlC(lblFrom, 2, 0, 2, 1);
        addComponentToPnlC(lblTo, 5, 0, 2, 1);
        constraints.weightx = 1;
        addComponentToPnlC(tfFrom, 3, 0, 1, 1);
        addComponentToPnlC(lblConversion, 6, 0, 1, 1);

        add(pnlC, BorderLayout.CENTER);
        /* WEST */

        /* EAST */

    }

    /**
     * Agrega elementos a la lista desplegable comboBox
     * @param <T>
     * @param items
     * @param comboBox
     */
    private <T> void addItemsToComboBox(T[] items, JComboBox<T> comboBox) {
        for (T item : items) {
            comboBox.addItem(item);
        }
    }

    /**
     * Agrega el component al panel central PnlC en la fila row y columna column, con un ancho width y 
     * alto height.
     * @param component
     * @param row
     * @param column
     * @param width
     * @param height
     */
    private void addComponentToPnlC(Component component, int row, int column, int width, int height) {
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        pnlC.add(component);
    }

}
