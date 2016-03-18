package uniandes.cupi2.ordenador;

import java.util.Comparator;
import java.util.List;

/**
 * Clase génerica que se encarga de ordenar colecciones de elementos de tipo T
 * @author alvar-go
 * @param <T> tipo de elementos que debe contener la colección a ordenar
 */
public class Ordenador<T> 
{
	/**
	 * Ordena la lista que llega por parámetro en el sentido que llega por parámetro usando el comparador que llega por parámetro
	 * post: la lista se encuentra ordenada por los cirterios ingresados
	 * @param ordenamiento el algoritmo de ordenamieto que se debe usar. ordenamiento pertenece a la enumeración Ordenamiento
	 * @param lista la lista que se desea ordenar. lista != null
	 * @param ascendente indica si se debe ordenar de manera ascendente, de lo contrario se debe ordenar descendentemente
	 * @param comparador comparador de elementos tipo T que se usará para ordenar la lista, define el criterio de orden. comparador != null.
	 */
	public void ordenar(AlgoritmoOrdenamiento ordenamiento, List<T> lista, boolean ascendente, Comparator<T> comparador)
	{
		if(ordenamiento.equals(AlgoritmoOrdenamiento.BURBUJA))
		{
			ordenarBurbuja(lista, ascendente, comparador);
		}
		else if(ordenamiento.equals(AlgoritmoOrdenamiento.SELECCION))
		{
			ordenarSeleccion(lista, ascendente, comparador);
		}
		else if(ordenamiento.equals(AlgoritmoOrdenamiento.INSERCION))
		{
			ordenarInsercion(lista, ascendente, comparador);
		}
		else if(ordenamiento.equals(AlgoritmoOrdenamiento.SHAKER))
		{
			ordenarShaker(lista, ascendente, comparador);
		}
		else if(ordenamiento.equals(AlgoritmoOrdenamiento.GNOME))
		{
			ordenarGnome(lista, ascendente, comparador);
		}
	}

	/**
	 * Ordena la lista usando el algoritmo de inscerción
	 * post: la lista se encuentra ordenada
	 * @param lista la lista que se desea ordenar. lista != null
	 * @param ascendnte indica si se debe ordenar de manera ascendente, de lo contrario se ordenará de manera descendente
	 * @param comparador comparador de elementos tipo T que se usará para ordenar la lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarInsercion(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
        // REVIEW Parte 4 Punto 1a Implemente según la documentación
        for (int i = 1; i < lista.size(); i++) {
            T value = lista.get(i);
            int j = i - 1;
            while (j >= 0 && (comparador.compare(lista.get(j), value) == 1)) {
                lista.set(j+1,lista.get(j));
                j = j - 1;
            }
            lista.set(j+1,value);
        }
        if(!ascendnte){
            for (int i = 0; i < lista.size() / 2; i++){
                T temp = lista.get(i);
                lista.set(i,lista.get(lista.size()- i - 1));
                lista.set(lista.size() - i - 1, temp);
            }
        }
    }
	/**
	 * Ordena la lista usando el algoritmo de selección
	 * post: la lista se encuentra ordenada
	 * @param lista la lista que se desea ordenar. lsita != null
	 * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenará de manera descendente
	 * @param comparador comparador de elementos tipo T que se usará para ordenar la lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarSeleccion(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
	  // REVIEW Parte 4 Punto 1b  Implemente según la documentación
		int N = lista.size();
        if (ascendnte){
            for (int i = 0; i < N; i++) {
                // Intercambie a[i] con la entrada mas pequeña en lista[i+1..,n).
                int min = i;
                for (int j = i+1; j < N; j++) {
                    if (comparador.compare(lista.get(j),lista.get(min)) == -1 ){
                        min = j;
                    }
                }
                T temp = lista.get(i);
                lista.set(i,lista.get(min));
                lista.set(min,temp);
            }
        }
        if (!ascendnte) {
            for (int i = 0; i < N; i++) {
                // Intercambie a[i] con la entrada mas pequeña en lista[i+1..,n).
                int min = i;
                for (int j = i+1; j < N; j++) {
                    if (comparador.compare(lista.get(j),lista.get(min)) == 1 ){
                        min = j;
                    }
                }
                T temp = lista.get(i);
                lista.set(i,lista.get(min));
                lista.set(min,temp);
            }
        }

    }

	/**
	 * Ordena la lista usando el algoritmo de burbuja
	 * post: la lista se encuentra ordenada
	 * @param lista la lista que se desea ordenar. lsita != null
	 * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenará de manera descendente
	 * @param comparador comparador de elementos tipo T que se usará para ordenar la lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarBurbuja(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
	  //REVIEW Parte 4 Punto 1c  Implemente según la documentación
		if (ascendnte){
            for (int i = 0; i < lista.size(); i++){
                for(int j = lista.size() - 1; j > i; j--){
                    if(comparador.compare(lista.get(j),lista.get(j-1)) == -1 ){
                        T temp = lista.get(j);
                        lista.set(j, lista.get(j-1));
                        lista.set(j-1, temp);
                   }
                }
            }
        }
        if (!ascendnte){
            for (int i = 0; i < lista.size(); i++){
                for(int j = lista.size() - 1; j > i; j--){
                    if(comparador.compare(lista.get(j),lista.get(j-1)) == 1 ){
                        T temp = lista.get(j);
                        lista.set(j, lista.get(j-1));
                        lista.set(j-1, temp);
                    }
                }
            }
        }
		
	}
	
	/**
	 * Ordena la lista usando el algoritmo de shake (burbuja bidireccional)
	 * post: la lista se encuentra ordenada
	 * @param lista la lista que se desea ordenar. lsita != null
	 * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenará de manera descendente
	 * @param comparador comparador de elementos tipo T que se usará para ordenar la lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarShaker(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
        int left =0;
        int right = lista.size() - 1;
        while (left < right){
            for (int pos = left; pos < right; pos++){
                if (comparador.compare(lista.get(pos),lista.get(pos + 1)) == 1){
                    T temp = lista.get(pos);
                    lista.set(pos,lista.get(pos + 1));
                    lista.set(pos + 1, temp);
                }
            }
            right--;

            for (int pos = right; pos > left; pos--){
                if (comparador.compare(lista.get(pos),lista.get(pos-1))==-1){
                    T temp = lista.get(pos);
                    lista.set(pos,lista.get(pos - 1));
                    lista.set(pos - 1, temp);
                }
            }
            left++;
        }
        if(!ascendnte){
            for (int i = 0; i < lista.size(); i++){
                for(int j = lista.size() - 1; j > i; j--){
                    if(comparador.compare(lista.get(j),lista.get(j-1)) == 1 ){
                        T temp = lista.get(j);
                        lista.set(j, lista.get(j-1));
                        lista.set(j-1, temp);
                    }
                }
            }
        }
    }
	
	
	/**
	 * Ordena la lista usando el algoritmo de Gnome
	 * post: la lista se encuentra ordenada
	 * @param lista la lista que se desea ordenar. lsita != null
	 * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenará de manera descendente
	 * @param comparador comparador de elementos tipo T que se usará para ordenar la lista, define el criterio de orden. comparador != null.
	 */
	private void ordenarGnome(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
	  //REVIEW Parte 4 Punto 1e  Implemente según la documentación
		if (ascendnte){
            int i = 1;
            int j =2;
            while (i < lista.size()){
                if (comparador.compare(lista.get(i-1),lista.get(i))==-1){
                    i = j;
                    j++;
                } else {
                    T temp = lista.get(i-1);
                    lista.set(i-1,lista.get(i));
                    lista.set(i--,temp);
                    i = (i==0) ? j++ : i;
                }
            }
        }
        if (!ascendnte){
            int i = 1;
            int j =2;
            while (i < lista.size()){
                if (comparador.compare(lista.get(i-1),lista.get(i))==1){
                    i = j;
                    j++;
                } else {
                    T temp = lista.get(i-1);
                    lista.set(i-1,lista.get(i));
                    lista.set(i--,temp);
                    i = (i==0) ? j++ : i;
                }
            }
        }
	}
}
