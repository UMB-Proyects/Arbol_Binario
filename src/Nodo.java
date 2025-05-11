
public class Nodo<T extends Comparable<T>> {
    T dato;
    Nodo<T> izquierdo, derecho;

    public Nodo(T dato) {
        this.dato = dato;
        izquierdo = derecho = null;
    }
}
