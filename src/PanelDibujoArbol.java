import javax.swing.*;
import java.awt.*;

public class PanelDibujoArbol<T extends Comparable<T>> extends JPanel {
    private Nodo<T> raiz;
    private final int RADIO = 20;        // radio del nodo
    private final int VERTICAL = 50;     // espacio vertical entre niveles

    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
        repaint(); // redibuja cuando se actualiza la raíz
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            drawNodo(g, raiz, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawNodo(Graphics g, Nodo<T> nodo, int x, int y, int xOffset) {
        g.setColor(Color.BLACK);
        g.fillOval(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2);
        g.setColor(Color.WHITE);
        String dato = nodo.dato.toString();
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(dato);
        int textHeight = fm.getAscent();
        g.drawString(dato, x - textWidth / 2, y + textHeight / 4);

        g.setColor(Color.BLACK);
        if (nodo.izquierdo != null) {
            int xIzq = x - xOffset;
            int yHijo = y + VERTICAL;
            g.drawLine(x, y, xIzq, yHijo);
            drawNodo(g, nodo.izquierdo, xIzq, yHijo, xOffset / 2);
        }
        if (nodo.derecho != null) {
            int xDer = x + xOffset;
            int yHijo = y + VERTICAL;
            g.drawLine(x, y, xDer, yHijo);
            drawNodo(g, nodo.derecho, xDer, yHijo, xOffset / 2);
        }
    }
}
