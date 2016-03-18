/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiViajes
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiViajes.mundo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import uniandes.cupi2.cupiViajes.excepciones.ClienteTieneReservaException;
import uniandes.cupi2.estructuras.ListaDoblementeEncadenada;
import uniandes.cupi2.estructuras.ListaSencillamenteEncadenada;

/**
 * Clase que representa CupiViajes. <br>
 * <b> inv: </b> <br>
 * hoteles != null. <br>
 * reservas != null. <br>
 * No pueden existir dos reservas con el mismo cliente.
 */
public class CupiViajes implements Serializable
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Lista de hoteles disponibles en el sistema de viajes.
     */
    private List<Hotel> hoteles;

    /**
     * Lista de reservas.
     */
    private List<ReservaViaje> reservas;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Construye un nuevo sistema de viajes sin hoteles y sin reservas.<br>
     * <b> post: </b> Las listas de hoteles y de viajes han sido inicializadas.
     */
    public CupiViajes( )
    {
        hoteles = new ListaDoblementeEncadenada<Hotel>( );
        reservas = new ListaSencillamenteEncadenada<ReservaViaje>( );
        verificarInvariante( );
    }

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Retorna la lista de hoteles disponibles en el sistema.
     * @return Lista de hoteles disponibles.
     */
    public List<Hotel> darHoteles( )
    {
        return hoteles;
    }

    /**
     * Retorna la lista de reservas.
     * @return Lista de reservas.
     */
    public List<ReservaViaje> darReservas( )
    {
        return reservas;
    }

    /**
     * Agrega una nueva reserva a la lista si no existe una reserva con el cliente dado. <br>
     * <b> pre </b>: El hotel con el nombre dado ya existe.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     * @param pFechaLLegada fecha de llegada al hotel que se va a reservar. pDiaLlegada !=null.
     * @param pNombreCliente Nombre del cliente responsable de la reserva. pCliente != null && pCliente != "".
     * @param pCantidadAdultos Cantidad de adultos que van a viajar al hotel que se va a reservar. pCantidadAdultos >= 1 && pCantidadAdultos <= 6.
     * @param pCantidadNinios Cantidad de niños que van a viajar al hotel que se va a reservar. pCantidadNinios >= 0 && pCantidadNinios <= 4.
     * @param pNochesEstadia Cantidad de noches de estadía en el hotel que se va a reservar. pNochesEstadia >= 1.
     * @param pAerolinea Aerolínea que se va a reservar. pAerolinea != null
     * @return True si la reserva se agregó correctamente. False de lo contrario.
     */
    public void agregarReserva( Hotel pHotel, Date pFechaLLegada, String pCedula, String pNombreCliente, int pCantidadAdultos, int pCantidadNinios, int pNochesEstadia, Aerolinea pAerolinea ) throws ClienteTieneReservaException
    {
        ReservaViaje buscado = buscarReserva( pCedula );
        if( buscado == null )
        {
            ReservaViaje reservaAAgregar = new ReservaViaje( pCedula, pNombreCliente, pCantidadAdultos, pCantidadNinios, pNochesEstadia, pAerolinea, pHotel, pFechaLLegada );
            reservas.add( reservaAAgregar );
            verificarInvariante( );
        }
        else
        {
           throw new ClienteTieneReservaException( "El cliente ya tiene una reserva", pCedula );
        }

    }
    
    
    /**
     * Agrega un nuevo hotel a la lista de hoteles. <br>
     * @param pNombre Nombre del hotel. pNombre != null && pNombre != "".
     * @param pCiudad Ciudad en la que se encuentra el hotel. pCiudad != null && pCiudad != "".
     * @param pEstrellas Cantidad de estrellas que tiene el hotel. pEstrellas >= 1 && pEstrellas <=5.
     * @param pCostoNoche Costo por noche en el hotel. pCostoNoche >= 1.
     * @param pRutaImagen Ruta de la imagen del hotel. pRutaImagen != null && pRutaImagen != "".
     */
    public void agregarHotel( String pNombre, String pCiudad, int pEstrellas, double pCostoNoche, String pRutaImagen )
    {
        Hotel hotel = new Hotel( pNombre, pCiudad, pEstrellas, pCostoNoche, pRutaImagen );
        hoteles.add( hotel );
    }
    
    /**
     * Busca los hoteles que están en la ciudad dada por parámetro.
     * @param pCiudad Ciudad  de los hoteles que se están buscando. pCiudad != null && pCiudad != "".
     * @return Lista con los hoteles que están en la ciudad dada.
     */
    public List<Hotel> buscarHotelesCiudad( String pCiudad )
    {
        // REVIEW TODO Parte 4.A: completar usando iteradores
        ListaSencillamenteEncadenada<Hotel> hotelesbuscados = hoteles.stream().filter(hotel -> hotel.darCiudad().compareTo(pCiudad) == 0).collect(Collectors.toCollection(ListaSencillamenteEncadenada::new));
        return hotelesbuscados;

    }
    
    /**
     * Busca la reserva del cliente dado por parámetro. <br>
     * @param pCliente Cliente se la reserva que se va a buscar. pCliente != null && pCliente != "".
     * @return Reserva de viaje con el cliente dado, null en caso de no encontrarlo.
     */
    public ReservaViaje buscarReserva( String pCliente )
    {
        // REVIEW TODO completar usando iteradores
        ReservaViaje resultado;
        for (ReservaViaje viaje : reservas){ // For eachh usa el iterador dereservas implicitamente
            String clientetemp = viaje.darNombreCliente();
            if (clientetemp.equals(pCliente)){
                resultado = viaje;
                return resultado;
            }
        }
        return null;
    }

    /**
     * Busca la reserva del cliente dado por parámetro utilizando una búsqueda binaria. <br>
     * <b> pre: </b> La lista de reservas se encuentra ordenada por clientes.
     * @param pNombreCliente Nombre del cliente de la reserva que se va a buscar. pNombreCliente != null && pNombreCliente != "".
     * @return Reserva de viaje del cliente dado, null en caso de no encontrarlo.
     */
    public ReservaViaje buscarReservaPorClienteBinario( String pNombreCliente )
    {
        ReservaViaje reservaBuscada = null;
        Comparator<ReservaViaje> comparador = new ComparadorReservaNombreCliente( );
        int inicio = 0;
        int fin = reservas.size( ) - 1;
        ReservaViaje reservaABuscar = new ReservaViaje( "", pNombreCliente, 1, 0, 1, Aerolinea.AVIANCA, new Hotel( "nombre", "ciudad", 1, 1, "imagen" ), new Date() );
        if( reservas.size( ) > 0 )
        {
            while( inicio <= fin && reservaBuscada == null )
            {
                int medio = ( inicio + fin ) / 2;
                ReservaViaje reserva = reservas.get( medio );
                int comparacion = comparador.compare( reserva, reservaABuscar );
                if( comparacion == 0 )
                {
                    reservaBuscada = reserva;
                }
                else if( comparacion > 0 )
                {
                    fin = medio - 1;
                }
                else
                {
                    inicio = medio + 1;
                }
            }
        }

        return reservaBuscada;
    }

    /**
     * Busca la primera reserva del viaje a la ciudad dada por parámetro.
     * @param pCiudad Ciudad del hotel de la reserva que se va a buscar. pCiudad != null && pCiudad != "";
     * @return Reserva del viaje a la ciudad dada por parámetro.
     */
    public ReservaViaje buscarReservaPorCiudad( String pCiudad )
    {
        // REVIEW TODO Parte 4.A: completar usando iteradores
        ReservaViaje buscado = null;
        ReservaViaje reservaTemp = null;
        boolean encontrado = false;

        Iterator itr = reservas.iterator();

        while (itr.hasNext() && !encontrado){
            reservaTemp = (ReservaViaje) itr.next();
            if( reservaTemp.darHotel( ).darCiudad( ).equals( pCiudad ) )
            {
                buscado = reservaTemp;
                encontrado = true;
            }
        }
        return buscado;
    }

    /**
     * Busca la reserva con la mayor cantidad de personas que van a viajar.
     * @return Reserva con la mayor cantidad de personas que van a viajar. <br>
     *         Si CupiViajes no tiene reservas, retorna null. Si existen varias reservas con la mayor cantidad de personas que van a viajar, se retorna la primera reserva
     *         encontrada.
     */
    public ReservaViaje buscarReservaMasPersonas( )
    {
        // REVIEW TODO Parte 4.A: completar usando iteradores
        ReservaViaje buscado = null;
        ReservaViaje reservaTemp;
        boolean encontrado = false;
        if (reservas != null){
            Iterator itr = reservas.iterator();
            int max = reservas.get(0).darCantidadAdultos() + reservas.get(0).darCantidadNinios();
            while (itr.hasNext() && !encontrado){
                int capacidad = 0;
                reservaTemp = (ReservaViaje) itr.next();
                capacidad = reservaTemp.darCantidadAdultos() + reservaTemp.darCantidadNinios();
                if (capacidad > max){
                    buscado = reservaTemp;
                    max = capacidad;
                    encontrado = true;
                }
            }
        }
        return buscado;
    }

    /**
     * Busca la reserva con la menor cantidad de personas que van a viajar.
     * @return Reserva con la menor cantidad de personas que van a viajar. <br>
     *         Si CupiViajes no tiene reservas, retorna null. Si existen varias reservas con la menor cantidad de personas que van a viajar, se retorna la primera reserva
     *         encontrada.
     */
    public ReservaViaje buscarReservaMenosPersonas( ) {
        // REVIEW TODO Parte 4.A: completar usando iteradores
        ReservaViaje buscado = null;
        ReservaViaje reservaTemp = null;
        boolean encontrado = false;
        if (reservas != null) {
            buscado = reservas.get(0);
            int min = reservas.get(0).darCantidadAdultos() + reservas.get(0).darCantidadNinios();
            Iterator itr = reservas.iterator();
            while (itr.hasNext() && !encontrado) {
                int capacidad = 0;
                reservaTemp = (ReservaViaje) itr.next();
                capacidad = reservaTemp.darCantidadAdultos() + reservaTemp.darCantidadNinios();
                if (capacidad < min) {
                    buscado = reservaTemp;
                    min = capacidad;
                    encontrado = true;
                }
            }
        }
        return buscado;
    }
    /**
     * Busca las reservas con aerolínea dada por parámetro.
     * @param pAerolinea Aerolínea de las reservas que se están buscando. pAerolinea != "" && pAerolinea pertenece a {ReservaViaje.AVIANCA, ReservaViaje.LAN,
     *        ReservaViaje.VIVA_COLOMBIA, ReservaViaje.SATENA, ReservaViaje.JETBLUE, ReservaViaje.IBERIA, ReservaViaje.AIR_FRANCE}.
     * @return Lista de reservan con aerolínea dada.
     */
    public List<ReservaViaje> buscarReservasAerolinea( Aerolinea pAerolinea )
    {
        // REVIEW TODO Parte 4.A: completar usando iteradores

        ListaSencillamenteEncadenada<ReservaViaje> respuesta = new ListaSencillamenteEncadenada<>( );

        for (ReservaViaje viaje : reservas) { // For eachh usa el iterador dereservas implicitamente
            Aerolinea aerolinaaProcesar = viaje.darAerolinea();
            if (pAerolinea.darNombre().equals(aerolinaaProcesar.darNombre())) {
                respuesta.add(viaje);
            }
        }
        return respuesta;
    }
    /**
     * Cambia el estado de la reserva con cedula indicada
     * @param cedula la cédula del cliente responsable de la reserva. Existe una reserva con la cédula dada
     * @param estado el nuevo estado de la reserva. estado != null
     */
    public void cambiarEstadoReserva( String cedula, Estado estado )
    {
        buscarReserva( cedula ).cambiarEstado( estado );
        
    }

    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * hoteles != null. <br>
     * reservas != null. <br>
     * No pueden existir dos reservas con el mismo cliente.
     */
    private void verificarInvariante( )
    {
        assert ( hoteles != null ) : "La lista de hoteles no puede ser nula.";
        assert ( reservas != null ) : "La lista de reservas no puede ser nula.";
        assert ( !buscarReservasMismoCliente( ) ) : "No pueden existir reservas con el mismo cliente.";
    }

    /**
     * Verifica si hay dos reservas con el mismo cliente.
     * @return True si hay dos reservas con el mismo cliente. False de lo contrario.
     */
    private boolean buscarReservasMismoCliente( )
    {
        boolean hay = false;
        for( int i = 0; i < reservas.size( ) && !hay; i++ )
        {
            ReservaViaje reservaI = ( ReservaViaje )reservas.get( i );
            for( int j = i + 1; j < reservas.size( ) && !hay; j++ )
            {
                ReservaViaje reservaJ = ( ReservaViaje )reservas.get( j );
                if( reservaI.darNombreCliente( ).equals( reservaJ.darNombreCliente( ) ) )
                {
                    hay = true;
                }
            }
        }
        return hay;
    }
    

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * Método para la extensión 2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }
}
