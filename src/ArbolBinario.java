import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinario<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) return new Nodo<>(dato);
        if (dato.compareTo(nodo.dato) < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        else
            nodo.derecho = insertarRec(nodo.derecho, dato);
        return nodo;
    }

    public String inOrden() {
        StringBuilder sb = new StringBuilder();
        inOrden(raiz, sb);
        return sb.toString();
    }

    private void inOrden(Nodo<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            inOrden(nodo.izquierdo, sb);
            sb.append(nodo.dato).append(" ");
            inOrden(nodo.derecho, sb);
        }
    }

    public String preOrden() {
        StringBuilder sb = new StringBuilder();
        preOrden(raiz, sb);
        return sb.toString();
    }

    private void preOrden(Nodo<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append(nodo.dato).append(" ");
            preOrden(nodo.izquierdo, sb);
            preOrden(nodo.derecho, sb);
        }
    }

    public String postOrden() {
        StringBuilder sb = new StringBuilder();
        postOrden(raiz, sb);
        return sb.toString();
    }

    private void postOrden(Nodo<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            postOrden(nodo.izquierdo, sb);
            postOrden(nodo.derecho, sb);
            sb.append(nodo.dato).append(" ");
        }
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    public int grado() {
        return grado(raiz);
    }

    private int grado(Nodo<T> nodo) {
        if (nodo == null) return 0;
        int grado = 0;
        if (nodo.izquierdo != null) grado++;
        if (nodo.derecho != null) grado++;
        return Math.max(grado, Math.max(grado(nodo.izquierdo), grado(nodo.derecho)));
    }

    public int nivel(T dato) {
        return nivel(raiz, dato, 1);
    }

    private int nivel(Nodo<T> nodo, T dato, int nivel) {
        if (nodo == null) return -1;
        if (nodo.dato.equals(dato)) return nivel;
        int abajo = nivel(nodo.izquierdo, dato, nivel + 1);
        if (abajo != -1) return abajo;
        return nivel(nodo.derecho, dato, nivel + 1);
    }

    public String recorridoAmplitud() {
        StringBuilder sb = new StringBuilder();
        if (raiz == null) return "";
        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll();
            sb.append(actual.dato).append(" ");
            if (actual.izquierdo != null) cola.add(actual.izquierdo);
            if (actual.derecho != null) cola.add(actual.derecho);
        }
        return sb.toString();
    }
    
    public String obtenerRecorridos() {
    return "InOrden: " + inOrden() +
           "\nPreOrden: " + preOrden() +
           "\nPostOrden: " + postOrden() +
           "\nAmplitud: " + recorridoAmplitud() +
           "\nAltura: " + altura() +
           "\nGrado: " + grado();
}

}
