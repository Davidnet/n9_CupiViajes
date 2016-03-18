package uniandes.cupi2.estructuras;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * Clase que unifica los elementos comunes de las listas encadendas
 * @author alvar-go
 * @param <T>
 */
public abstract class ListaEncadenadaAbstracta<T extends IdentificadoUnicamente> implements List<T>, Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Primer nodo de la lista
     */
    protected NodoListaSencilla<T> primero;
    
    /**
     * Devuelve los elementos de la lista en forma de arreglo de objetos
     * @return un arreglo con todos los elementos de la lista
     */
    public Object[] toArray( )
    {
        IdentificadoUnicamente[] array = new IdentificadoUnicamente[size( )];
        NodoListaSencilla<T> n  = primero;
        int pos = 0;
        while(n!= null)
        {
            array[pos] = n.darElemento( );
            n = n.darSiguiente( );
            pos++;
        }
        return array;
    }

    /**
     * Devuelve los elementos de la lista como arreglo de T
     * @param array el arreglo donde se deben guardar los elementos a menos que no quepan
     * @return un arreglo con todos los elementos de la lista
     */
    public <T> T[] toArray( T[] array )
    {
        if(array.length < size( ))
        {
            return (T[])toArray( );
        }
        else
        {
            NodoListaSencilla n  = primero;
            int pos = 0;
            while(n!= null)
            {
                array[pos] = (T)n.darElemento( );
                n = n.darSiguiente( );
            }
            if(array.length > size())
            {
                array[size( )] = null;
            }
            return array;
        }
    }
    
    /**
     * Indica el tamaño de la lista
     * @return cantidad de nodos en la lista
     */
    public int size( )
    {
        int size = 0;
        NodoListaSencilla<T> nodo = primero;
        
        while( nodo != null)
        {
            size++;
            nodo = nodo.darSiguiente( );
        }
        return size;
    }
    
    /**
     * Reemplaza el elemento de la posición por el elemento que llega por parámetro
     * @param pos la posición en la que se desea reeemplazar el elemento
     * @param elem el nuevo elemento que se desea poner
     * @return el elemento quitado de la posición
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public T set( int pos, T elem )
    {
     // REVIEW TODO Parte2: Completar según la documentación
        if (pos < 0 || pos >= size()){
            throw new IndexOutOfBoundsException("El argumento de posición " + pos + " no tiene sentido");
        }
        NodoListaSencilla<T> nodoamodificar = getNodo(pos);
        T viejoelemento = nodoamodificar.darElemento();
        nodoamodificar.cambiarElemento(elem);
        return viejoelemento;
    }
    
    /**
     * Borra de la lista todos los elementos en la colección por parámetro
     * @param coleccion la colección de elmentos que se desea eliminar. coleccion != null
     * @return true en caso que se elimine al menos un elemento o false en caso contrario
     */
     public boolean removeAll( Collection<?> coleccion )
     {
         boolean modificado = false;
         for( Object objeto : coleccion )
         {
             modificado |= remove( objeto );
         }
         return modificado;
     }
     
     /**
      * Indica la última posición donde aparece el objeto por parámetro en la lista
      * @param objeto el objeto buscado en la lista. objeto != null
      * @return la última posición del objeto en la lista o -1 en caso que no exista
      */
     public int lastIndexOf( Object objeto )
     {
         return indexOf( objeto );
     }
     
     /**
      * Devuelve un iterador sobre la lista
      * El iterador empieza en el primer elemento
      * @return un nuevo iterador sobre la lista
      */
     public Iterator<T> iterator( )
     {
         return new IteradorSencillo<T>( primero );
     }
     
     /**
      * Indica si la lista está vacia
      * @return true si la lista está vacia o false en caso contrario
      */
     public boolean isEmpty( )
     {
         return primero == null;
     }
     
     /**
      * Indica la posición del objeto que llega por parámetro en la lista
      * @param objeto el objeto que se desea buscar en la lista. objeto != null
      * @return la posición del objeto o -1 en caso que no se encuentre en la lista
      */
     public int indexOf( Object objeto )
     {
      // REVIEW TODO Parte2: Completar según la documentación
         NodoListaSencilla<T> n = primero;
         int posicion = 0;
         while ( n != null){
             if (n.darElemento().equals(objeto)){
                 return posicion;
             }
             posicion++;
             n = n.darSiguiente();
         }
         return -1;
     }
     
     /**
      * Devuelve el elemento de la posición dada
      * @param pos la posición  buscada
      * @return el elemento en la posición dada 
      * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
      */
     public T get( int pos )
     {
      // REVIEW TODO Parte2: Completar según la documentación
         NodoListaSencilla<T> nodo = primero;
         if (pos < 0 || pos >= size()){
             throw new IndexOutOfBoundsException("Posición" + pos + "no tiene sentido");
         }
         int i = 0;
         while (i!=pos){
             nodo = nodo.darSiguiente();
             i++;
         }
         return nodo.darElemento();
     }
     
     /**
      * Devuelve el nodo de la posición dada
      * @param pos la posición  buscada
      * @return el nodo en la posición dada 
      * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
      */
     public NodoListaSencilla<T> getNodo( int pos ) {
         // REVIEW TODO Parte2: Completar según la documentación
         NodoListaSencilla<T> nodo = primero;
         if (pos < 0 || pos >= size()){
             throw new IndexOutOfBoundsException("Posición" + pos + " no tiene sentido");
         }
         int i = 0;
         while (i!=pos){
             nodo = nodo.darSiguiente();
             i++;
         }
         return nodo;
     }
     
     /**
      * Indica si la lista contiene todos los objetos de la colección dada
      * @param coleccion la colección de objetos que se desea buscar. coleccion !=null
      * @return true en caso que todos los objetos estén en la lista o false en caso contrario
      */
     public boolean containsAll( Collection<?> coleccion )
     {
         boolean contiene = true;
         for( Object objeto : coleccion)
         {
             contiene &= contains( objeto );
         }
         return contiene;
     }
     
     /**
      * Indica si la lista contiene el objeto indicado
      * @param objeto el objeto que se desea buscar en la lista. objeto != null
      * @return true en caso que el objeto esté en la lista o false en caso contrario
      */
      public boolean contains( Object objeto )
      {
          boolean contiene = false;
          if( objeto instanceof IdentificadoUnicamente )
          {
              IdentificadoUnicamente u = (IdentificadoUnicamente)objeto;
              NodoListaSencilla<T> nodo = primero;
              while( nodo!= null && !contiene)
              {
                  if(nodo.darElemento( ).darIdentificador( ).equals( u.darIdentificador( ) ))
                  {
                      contiene = true;
                  }
                  
                  nodo = nodo.darSiguiente( );
              }
          }
          return contiene;
      }
      
      /**
       * Borra todos los elementos de la lista
       * post: el primer elemento es nulo
       */
      public void clear( )
      {
          primero = null;
          
      }
      
      /**
       * Agrega todos los elementos de la colección a la lista a partir de la posición indicada
       * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
       * @param pos la posición a partir de la cual se desean agregar los elementos
       * @param coleccion la colección de elementos que se desea agregar
       * @return true si al menos uno de los elementos se agrega o false en caso contrario
       * @throws NullPointerException Si alguno de los elementos que se quiere agregar es null
       * @throws IndexOutOfBoundsException si indice < 0 o indice > size()
       */
      public boolean addAll( int pos, Collection<? extends T> coleccion )
      {

          int size0 = size( );
          for( T t : coleccion )
          {
             add( pos, t );
             pos++;
          }
          return size( ) > size0;
      }
      
      /**
       * Agrega a la lista todos los elementos de la colección
       * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
       * @param coleccion la colección de elementos que se desea agregar
       * @return true en caso que se agregue al menos un elemento false en caso contrario
       * @throws NullPointerException si alguno de los elementos que se desea agregar es null
       */
      public boolean addAll( Collection<? extends T> coleccion )
      {
          boolean modificado = false;
          for( T t : coleccion )
          {
              modificado |= add(t);
          }
          return modificado;
      }
}
