import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JTextField campoEntrada;
    private JTextArea areaResultado;
    private ArbolBinario<Integer> arbolNumeros;
    private ArbolBinario<String> arbolLetras;
    private JComboBox<String> comboTipoDato;
    private PanelDibujoArbol<Integer> panelArbolNumeros = new PanelDibujoArbol<>();
    private PanelDibujoArbol<String> panelArbolLetras = new PanelDibujoArbol<>();
    private JPanel panelDibujo = new JPanel(new CardLayout());

    public VentanaPrincipal() {
        super("Árbol Binario Visual");
        arbolNumeros = new ArbolBinario<>();
        arbolLetras = new ArbolBinario<>();
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelSuperior = new JPanel();
        campoEntrada = new JTextField(10);
        JButton botonInsertar = new JButton("Insertar");
        comboTipoDato = new JComboBox<>(new String[]{"Números", "Letras"});
        panelSuperior.add(new JLabel("Dato:"));
        panelSuperior.add(campoEntrada);
        panelSuperior.add(comboTipoDato);
        panelSuperior.add(botonInsertar);

        JPanel panelCentro = new JPanel(new BorderLayout());
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);
        panelCentro.add(scroll, BorderLayout.SOUTH);

        // Panel de dibujo
        panelDibujo.setPreferredSize(new Dimension(600, 300));
        panelDibujo.add(panelArbolNumeros, "numeros");
        panelDibujo.add(panelArbolLetras, "letras");
        panelCentro.add(panelDibujo, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

        // Acción del botón insertar
        botonInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dato = campoEntrada.getText().trim();
                if (dato.isEmpty()) return;

                boolean esNumerico = comboTipoDato.getSelectedItem().equals("Números");
                if (esNumerico) {
                    try {
                        int valor = Integer.parseInt(dato);
                        arbolNumeros.insertar(valor);
                        panelArbolNumeros.setRaiz(getRaiz(arbolNumeros));
                        panelArbolNumeros.revalidate();
                        panelArbolNumeros.repaint();
                        ((CardLayout) panelDibujo.getLayout()).show(panelDibujo, "numeros");
                        areaResultado.setText(arbolNumeros.obtenerRecorridos());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                    }
                } else {
                    arbolLetras.insertar(dato);
                    panelArbolLetras.setRaiz(getRaiz(arbolLetras));
                    panelArbolLetras.revalidate();
                    panelArbolLetras.repaint();
                    ((CardLayout) panelDibujo.getLayout()).show(panelDibujo, "letras");
                    areaResultado.setText(arbolLetras.obtenerRecorridos());
                }
                campoEntrada.setText("");
            }
        });
    }

    // Método auxiliar para acceder a la raíz usando reflexión
    private <T extends Comparable<T>> Nodo<T> getRaiz(ArbolBinario<T> arbol) {
        try {
            var campo = ArbolBinario.class.getDeclaredField("raiz");
            campo.setAccessible(true);
            return (Nodo<T>) campo.get(arbol);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
