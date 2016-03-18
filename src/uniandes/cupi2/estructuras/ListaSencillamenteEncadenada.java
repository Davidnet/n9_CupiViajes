package uniandes.cupi2.estructuras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Clase que representa una lista sencillamente encadenada
 * @author alvar-go
 * @param <T>
 */
public class ListaSencillamenteEncadenada<T extends IdentificadoUnicamente> extends ListaEncadenadaAbstracta<T>
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construye una lista vacia
     * post: se ha inicializado el primer nodo en null
     */
    public ListaSencillamenteEncadenada()
    {
        primero = null;
    }
    
    /**
     * Se construye una nueva lista cuyo primer nodo  guardará al elemento que llega por parámentro
     * @param nPrimero el elemento a guardar en el primer nodo
     * @throws NullPointerException si el elemento recibido es nulo
     */
    public ListaSencillamenteEncadenada(T nPrimero)
    {
        if(nPrimero == null)
        {
            throw new NullPointerException();
        }
        primero = new NodoListaSencilla<>(nPrimero);
    }

    /**
     * Agrega un elemento al final de la lista
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param elem el elemento que se desea agregar.
     * @return true en caso que se agregue el elemento o false en caso contrario. 
     * @throws NullPointerException si el elemento es nulo
     */
    public boolean add( T elem )throws NullPointerException
    {
        if(elem == null)
        {
            throw new NullPointerException( );
        }
        
        boolean agregado = false;
        if(primero == null)
        {
            primero = new NodoListaSencilla<>(elem);
            agregado = true;
        }
        else
        {
            NodoListaSencilla<T> n = primero;
            boolean existe = false;
            while(  n.darSiguiente( ) != null && !existe)
            {
                if(n.darElemento( ).darIdentificador( ).equals( elem.darIdentificador( ) ))
                {
                    existe = true;
                }
                n = n.darSiguiente( );
            }
            if(!n.darElemento( ).darIdentificador( ).equals( elem.darIdentificador( ) ))
            {
                n.cambiarSiguiente(new NodoListaSencilla<>(elem) );
            }
            
        }
        
        return agregado;
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
        if(elem == null)
        {
            throw new NullPointerException( );
        }
        NodoListaSencilla<T> nuevo = new NodoListaSencilla<>(elem);
        if(!contains( elem ))
        {
            
            if(pos == 0)
            {
                nuevo.cambiarSiguiente( primero );
                primero = nuevo;
            }
            else
            {
                NodoListaSencilla<T> n = primero;
                int posActual = 0;
                while( posActual < (pos-1) && n != null )
                {
                    posActual++;
                    n = n.darSiguiente( );
                }
                if(posActual != (pos-1))
                {
                    throw new IndexOutOfBoundsException( );
                }
                nuevo.cambiarSiguiente( n.darSiguiente( ) );
                n.cambiarSiguiente( nuevo );
            }
        }
        
    }

    
    public ListIterator<T> listIterator( )
    {
        throw new UnsupportedOperationException ();
    }

    
    public ListIterator<T> listIterator( int pos )
    {
        throw new UnsupportedOperationException ();
    }

    /**
     * Elimina el nodo que contiene al objeto que llega por parámetro
     * @param objeto el objeto que se desea eliminar. objeto != null
     * @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
     */
    public boolean remove( Object objeto )
    {
     // REVIEW TODO Parte 3.A: Completar según la documentación
        if (contains(objeto)){
            int posicion = indexOf(objeto);
            if (posicion == 0){
                primero = primero.darSiguiente();
                return true;
            }
            NodoListaSencilla<T> nodoAnterior = getNodo(posicion - 1);
            if (posicion == (size()-1)){
                nodoAnterior.cambiarSiguiente(null);
                return true;
            }
            else {
                nodoAnterior.cambiarSiguiente(getNodo(posicion + 1));
                return true;
            }
        }
        return  false;
    }

    /**
     * Elimina el nodo en la posición por parámetro
     * @param pos la posición que se desea eliminar
     * @return el elemento eliminado
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public T remove( int pos )
    {
     // REVIEW TODO Parte 3.A: Completar según la documentación
        if (pos < 0 || pos >= size()){
            throw new IndexOutOfBoundsException("Posición" + pos + "no tiene sentido");
        }
        T elementoaEliminar = getNodo(pos).darElemento();
        if (pos == 0){
            primero = primero.darSiguiente();
            return elementoaEliminar;
        }
        else {
            if (pos == size() - 1){
                getNodo(pos - 1).cambiarSiguiente(null);
                return elementoaEliminar;
            }
            else {
                getNodo(pos - 1).cambiarSiguiente(getNodo(pos + 1));
                return elementoaEliminar;
            }
        }


    }

    /**
     * Deja en la lista solo los elementos que están en la colección que llega por parámetro
     * @param coleccion la colección de elementos a mantener. coleccion != null
     * @return true en caso que se modifique (eliminación) la lista o false en caso contrario
     */
    public boolean retainAll( Collection<?> coleccion )
    {
     // REVIEW TODO Parte 3.A: Completar según la documentación
        NodoListaSencilla<T> nodoaProcesar = primero;
        boolean modificado = false;
        while (nodoaProcesar!=null){
            if (!coleccion.contains(nodoaProcesar.darElemento())){
                remove(nodoaProcesar.darElemento());
                modificado = true;
            }
            nodoaProcesar = nodoaProcesar.darSiguiente();
        }
        return modificado;
    }
    
    /**
     * Crea una lista con los elementos de la lista entre las posiciones dadas
     * @param inicio la posición del primer elemento de la sublista. Se incluye en la sublista
     * @param fin la posición del útlimo elemento de la sublista. Se excluye en la sublista
     * @return una lista con los elementos entre las posiciones dadas
     * @throws IndexOutOfBoundsException Si inicio < 0 o fin >= size() o fin < inicio
     */
    public List<T> subList( int inicio, int fin )
    {
     // REVIEW TODO Parte 3.A: Completar según la documentación
        if (inicio < 0 || fin >= size() || fin < inicio){
            throw new IndexOutOfBoundsException("Revisar argumentos inicio " + inicio + " final " + fin + " con tamaño " + size());
        }
        NodoListaSencilla<T> nodoAProcesar = primero;
        List<T> listaARetonar = new ArrayList<>();
        int counter = 0;
        boolean procesado = false;
        while (!procesado){
            if (counter >= inicio && counter <= fin){
                listaARetonar.add(nodoAProcesar.darElemento());
                if (counter == fin){
                    procesado = true;
                }
            }
            nodoAProcesar = nodoAProcesar.darSiguiente();
        }
        return (List<T>) listaARetonar;
    }
}
