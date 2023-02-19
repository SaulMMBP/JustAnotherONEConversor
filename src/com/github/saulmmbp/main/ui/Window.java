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

    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private JPanel pnlRoot;
    private JLabel lblConversion;
    private JTextField tfFrom;

    private Controller controller;

    public Window() {
        initComponents();
        controller = new Controller();
    }

    /*
     * Inicializa y configura componentes
     */
    private void initComponents() {

        /* LAYOUT CONFIGS */
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        pnlRoot = new JPanel();

        pnlRoot.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        pnlRoot.setLayout(layout);

        /* MENUBAR CONFIGS */
        JMenuBar mnBar = new JMenuBar();
        JMenu mnAbout = new JMenu("About");
        JMenu mnOptions = new JMenu("Options");
        JMenuItem mnItmAbout = new JMenuItem("About");
        JCheckBoxMenuItem mnItmDarkMode = new JCheckBoxMenuItem("Modo Oscuro");

        mnAbout.setMnemonic('A');
        mnOptions.setMnemonic('O');
        mnItmAbout.setMnemonic('A');

        mnItmAbout.addActionListener((event) -> { // MenuItem About listener
            JOptionPane.showMessageDialog(this,
                    "Este es un proyecto desarrollado para el programa\nONE de Oracle "
                            + "y Alura Latam.\n\nGracias a todos los que colaboran en este "
                            + "programa\nque nos impulsan a ser mejores.\n\nLos quiero mucho <3",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        });

        mnItmDarkMode.addActionListener((event) -> { // MenuItem dark mode listener
            String laf = UIManager.getLookAndFeel().getName();
            try {
                if (laf.equals("FlatLaf Light")) {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                } else if (laf.equals("FlatLaf Dark")) {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                }
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(this, "No se pudo activar el modo oscuro, " + "lo siento :C", "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });

        mnAbout.add(mnItmAbout);
        mnOptions.add(mnItmDarkMode);

        mnBar.add(mnAbout);
        mnBar.add(mnOptions);

        /* LABELS CONFIG */
        JLabel lblConversor = new JLabel("Tipo de conversor");
        JLabel lblFrom = new JLabel("De:");
        JLabel lblTo = new JLabel("A:");

        /* INPUT - OUTPUT CONFIGS */
        lblConversion = new JLabel("0.0");
        tfFrom = new JTextField();

        lblConversion.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        /* COMBOBOX CONFIGS */
        JComboBox<ConversionType> cbConversor = new JComboBox<>();
        JComboBox<Convertible> cbFrom = new JComboBox<>();
        JComboBox<Convertible> cbTo = new JComboBox<>();

        cbConversor.addActionListener((event) -> { // Cambia el tipo de conversión
            ConversionType conType = (ConversionType) cbConversor.getSelectedItem();
            switch(conType) {
                case TEMPERATURE -> changeConversionType(cbFrom, cbTo, Temperature.values());
                case CURRENCY -> changeConversionType(cbFrom, cbTo, Currency.values());
                case LENGTH -> changeConversionType(cbFrom, cbTo, Length.values());
            }
        });

        addItemsToComboBox(ConversionType.values(), cbConversor);
        addItemsToComboBox(Currency.values(), cbFrom);
        addItemsToComboBox(Currency.values(), cbTo);

        /* BUTTONS CONFIGS */
        JButton btnConvert = new JButton("Convertir");

        btnConvert.addActionListener((event) -> {
            Convertible from = (Convertible) cbFrom.getSelectedItem();
            Convertible to = (Convertible) cbTo.getSelectedItem();
            ConversionType conversionType = (ConversionType) cbConversor.getSelectedItem();

            try {
                Float value = Float.parseFloat(tfFrom.getText());
                String valueConverted = controller.convert(value, from, to, conversionType);
                lblConversion.setText(valueConverted);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingresa solo números por favor", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        /* ADD COMPONENTS TO WINDOW */
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.fill = GridBagConstraints.NONE;
        addComponentToPnlRoot(lblConversor, 0, 0, 2, 1);
        addComponentToPnlRoot(cbConversor, 1, 0, 2, 1);
        addComponentToPnlRoot(btnConvert, 4, 0, 2, 1);
        constraints.fill = GridBagConstraints.BOTH;
        addComponentToPnlRoot(lblFrom, 2, 0, 2, 1);
        addComponentToPnlRoot(lblTo, 5, 0, 2, 1);
        constraints.weightx = 1;
        addComponentToPnlRoot(cbFrom, 3, 1, 1, 1);
        addComponentToPnlRoot(cbTo, 6, 1, 1, 1);
        addComponentToPnlRoot(tfFrom, 3, 0, 1, 1);
        addComponentToPnlRoot(lblConversion, 6, 0, 1, 1);

        setJMenuBar(mnBar);
        add(pnlRoot);
    }

    /**
     * Cambia las unidades de conversión en las listas desplegables
     * @param <T>
     * @param cbFrom
     * @param cbTo
     * @param values
     */
    private <T extends Convertible > void changeConversionType(JComboBox<Convertible> cbFrom, JComboBox<Convertible> cbTo, T[] values) {
        cbFrom.removeAllItems();
        cbTo.removeAllItems();
        addItemsToComboBox(values, cbFrom);
        addItemsToComboBox(values, cbTo);
        
        tfFrom.setText("");
        lblConversion.setText("");
    }

    /**
     * Agrega elementos a la lista desplegable comboBox
     * 
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
     * Agrega el component al panel central PnlC en la fila row y columna column,
     * con un ancho width y alto height.
     * 
     * @param component
     * @param row
     * @param column
     * @param width
     * @param height
     */
    private void addComponentToPnlRoot(Component component, int row, int column, int width, int height) {
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        pnlRoot.add(component);
    }

}
