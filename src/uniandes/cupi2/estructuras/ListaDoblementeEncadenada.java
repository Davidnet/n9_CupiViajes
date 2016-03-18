package uniandes.cupi2.estructuras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class ListaDoblementeEncadenada <T extends IdentificadoUnicamente> extends ListaEncadenadaAbstracta<T> implements List<T>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construye una lista vacia
     * post: se ha inicializado el primer nodo en null
     */
    public ListaDoblementeEncadenada()
    {
        primero = null;
    }
    
    /**
     * Se construye una nueva lista cuyo primer nodo  guardará al elemento que llega por parámentro
     * @param nPrimero el elemento a guardar en el primer nodo
     * @throws NullPointerException si el elemento recibido es nulo
     */
    public ListaDoblementeEncadenada(T nPrimero)
    {
        if(nPrimero == null)
        {
            throw new NullPointerException();
        }
        primero = new NodoListaDoble<>( nPrimero );
    }

    /**
     * Agrega un elemento al final de la lista
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param elem el elemento que se desea agregar.
     * @return true en caso que se agregue el elemento o false en caso contrario. 
     * @throws NullPointerException si el elemento es nulo
     */
    public boolean add( T elem )
    {
        // REVIEW TODO Parte 3.B: Completar según la documentación
        boolean existe = contains(elem);
        if (!existe){
            if (primero == null){
                primero = new NodoListaDoble<>( elem );
            }
            else {
                NodoListaDoble<T> nuevo = new NodoListaDoble(elem);
                getNodo(size() - 1).cambiarSiguiente(nuevo);
                nuevo.cambiarAnterior((NodoListaDoble) getNodo(size()-1));
            }
        }
        return !existe;
    }

    
    /**
     * Agrega un elemento en la posición dada de la lista. Todos los elementos siguientes se desplazan
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param pos la posición donde se desea agregar. Si pos es igual al tamaño de la lista se agrega al final
     * @param elem el elemento que se desea agregar
     * @throws IndexOutOfBoundsException si el inidice es < 0 o > size()
     * @throws NullPointerException Si el elemento que se quiere agregar es null.
     */
    public void add( int pos, T elem )
    {
     // REVIEW TODO Parte 3.B: Completar según la documentación
        if(elem == null)
        {
            throw new NullPointerException( );
        }
        NodoListaDoble<T> nuevo = new NodoListaDoble<>(elem);
        if (!contains( elem )){
            if (pos == 0){
                nuevo.cambiarSiguiente( primero );
                primero = nuevo;
            }
            else {
                NodoListaDoble<T> n = (NodoListaDoble<T>) primero;
                int posActual = 0;
                while (posActual < (pos-1) && n != null ){
                    posActual++;
                    n = (NodoListaDoble<T>) n.darSiguiente();
                }
                if (posActual != (pos-1)){
                    throw new IndexOutOfBoundsException("Ojo revisar index");
                }
                nuevo.cambiarAnterior(n);
                nuevo.cambiarSiguiente(n.darSiguiente());
                n.cambiarSiguiente(nuevo);

            }
        }
        
    }

    
    /**
     * Devuelve un nuevo iterador de lista que inicie en el primer nodo
     * @return iterador
     */
    public ListIterator<T> listIterator( )
    {
        return new IteradorDeLista<T>( (NodoListaDoble<T>)primero );
    }

    /**
     * Devuelve un nuevo iterador de lista que inicie en nodo de la posición que llega por parámetro
     * @return iterador 
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public ListIterator<T> listIterator( int pos )
    {
        return new IteradorDeLista<T>( (NodoListaDoble<T>)(getNodo(pos)) );
    }

    /**
     * Elimina el nodo que contiene al objeto que llega por parámetro
     * @param arg0 el objeto que se desea eliminar. objeto != null
     * @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
     */
    public boolean remove( Object arg0 )
    {
     // REVIEW TODO Completar según la documentación
        if (contains(arg0)){
            int posicion = indexOf(arg0);
            if (posicion == 0){
                primero = primero.darSiguiente();
                return true;
            }
            NodoListaDoble<T> nodoAnterior = (NodoListaDoble<T>) getNodo(posicion - 1);
            if (posicion == (size() - 1)){
                nodoAnterior.cambiarSiguiente(null);
                return true;
            }
            else {
                NodoListaDoble<T> nodoPost = (NodoListaDoble<T>) getNodo(posicion + 1);
                nodoPost.cambiarAnterior(nodoAnterior);
                nodoAnterior.cambiarSiguiente(getNodo(posicion + 1));
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina el elemento de la posición dada
     * @param pos la posición que se desea eliminar
     * @return el elemento eliminado o null en caso que no exista la posición que se desea eliminar
     */
    public T remove( int pos )
    {
     // REVIEW TODO Parte 3.B: Completar según la documentación
        if (pos < 0 || pos >= size()){
            throw new IndexOutOfBoundsException("Posición" + pos + "no tiene sentido");
        }
        NodoListaDoble<T> nodoaprocesar = (NodoListaDoble<T>) getNodo(pos);
        T elementoAEliminar = nodoaprocesar.darElemento();
        boolean resultado = remove(elementoAEliminar);
        return (resultado ? elementoAEliminar : null); // Some people just want to watch the world burn.
    }

    /**
     * Deja en la lista solo los elementos que están en la colección que llega por parámetro
     * @param arg0 la colección de elementos a mantener. coleccion != null
     * @return true en caso que se modifique (eliminación) la lista o false en caso contrario
     */
    public boolean retainAll( Collection<?> arg0 )
    {
     // REVIEW TODO Parte 3.B: Completar según la documentación
        NodoListaDoble<T> n = ( NodoListaDoble<T> )primero;
        boolean modificado= false;
        while (n != null)
        {
            if(!arg0.contains( n.darElemento() ))
            {
                remove(n.darElemento( ));
                modificado= true;
            }

            n= ( NodoListaDoble<T> )n.darSiguiente();

        }
        return modificado;
    }

    /**
     * Crea una lista con los elementos de la lista entre las posiciones dadas
     * @param arg0 la posición del primer elemento de la sublista. Se incluye en la sublista
     * @param arg1 la posición del útlimo elemento de la sublista. Se excluye en la sublista
     * @return una lista con los elementos entre las posiciones dadas
     * @throws IndexOutOfBoundsException Si inicio < 0 o fin >= size() o fin < inicio
     */
    public List<T> subList( int arg0, int arg1 )
    {
     // REVIEW TODO Parte 3.B: Completar según la documentación
        List<T> lista= new ArrayList<>();
        NodoListaSencilla<T> n = primero;
        if (arg0<0 || arg1 >= size())
        {
            throw new IndexOutOfBoundsException ();
        }
        for(int i= arg0; i<= arg1; i++)
        {
            lista.add( n.darElemento() );
            n= n.darSiguiente( );
        }
        return lista;
    }
}
