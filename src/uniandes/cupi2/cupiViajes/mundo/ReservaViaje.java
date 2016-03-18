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

import java.util.Date;

import uniandes.cupi2.estructuras.IdentificadoUnicamente;

/**
 * Clase que representa una reserva de un viaje. <br>
 * <b> inv: </b> <br>
 * nombreCliente != null && nombreCliente != "". <br>
 * cantidadAdultos >= 1. <br>
 * nochesEstadia >= 1. <br>
 * costoTotal > 0. <br>
 * aerolinea !=null. <br>
 * hotel != null. <br>
 * fechaLlegada != null. <br>
 */
public class ReservaViaje implements IdentificadoUnicamente
{
    
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    
    /**
     * Cedula del cliente responsable de la reserva del viaje.
     */
    private String cedula;
    
    
    /**
     * Nombre del cliente responsable de la reserva del viaje.
     */
    private String nombreCliente;

    /**
     * Cantidad de adultos por los que se va a reservar el viaje.
     */
    private int cantidadAdultos;

    /**
     * Cantidad de niños por los que se va a reservar el viaje.
     */
    private int cantidadNinios;

    /**
     * Cantidad de noches de estadía en el hotel que se va a reservar.
     */
    private int cantidadNochesEstadia;

    /**
     * Costo total de la reserva.
     */
    private double costoTotal;

    /**
     * Aerolínea que se va a reservar.
     */
    private Aerolinea aerolinea;

    /**
     * Hotel que se va a reservar.
     */
    private Hotel hotel;

    /**
     * Fecha de llegada al hotel que se va a reservar.
     */
    private Date fechaLlegada;
    
    /**
     * Estado de la reserva
     */
    private Estado estado;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Inicializa la reserva del viaje con los valores dados por parámetro. <br>
     * <b> post: </b> Los atributos cliente, cantidadAdultos, cantidadNinios, cantidadNochesEstadia, aerolinea, hotel y fechaLlegada fueron inicializados con los valores dados por parámetro. <br>
     * El costo total se inicializó como el costo por noche de estadía en el hotel multiplicado por la cantidad de noches de la reserva.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     * @param pNombreCliente Nombre del cliente responsable de la reserva. pNombreCliente != null && pNombreCliente != "".
     * @param pCantidadAdultos Cantidad de adultos que van a viajar. pCantidadAdultos >= 1 && pCantidadAdultos <= 6.
     * @param pCantidadNinios Cantidad de niños que van a viajar. pCantidadNinios >= 0 && pCantidadNinios <= 4.
     * @param pNochesEstadia Cantidad de noches de estadía en el hotel que se va a reservar. pNochesEstadia >= 1.
     * @param pAerolinea Aerolínea que se va a reservar. pAerolinea != null.
     * @param pFechaLlegada Fecha de llegada al hotel que se va a reservar. pFecha != null.
     */
    public ReservaViaje( String pCedula, String pNombreCliente, int pCantidadAdultos, int pCantidadNinios, int pNochesEstadia, Aerolinea pAerolinea, Hotel pHotel, Date pFechaLlegada )
    {
        cedula = pCedula;
        nombreCliente = pNombreCliente;
        cantidadAdultos = pCantidadAdultos;
        cantidadNinios = pCantidadNinios;
        cantidadNochesEstadia = pNochesEstadia;
        aerolinea = pAerolinea;
        hotel = pHotel;
        fechaLlegada = pFechaLlegada;
        costoTotal = hotel.darCostoNoche( ) * cantidadNochesEstadia;
        estado = Estado.SIN_PAGO;
        verificarInvariante( );
    }

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Retorna la cedula del cliente responsable de la reserva.
     * @return Cedula del cliente responsable de la reserva.
     */
    public String darCedula( )
    {
        return cedula;
    }
    
    /**
     * Retorna el nombre del cliente responsable de la reserva.
     * @return Nombre del cliente responsable de la reserva.
     */
    public String darNombreCliente( )
    {
        return nombreCliente;
    }

    /**
     * Retorna la cantidad de adultos por los que se va reservar el viaje.
     * @return Cantidad de adultos por los que se va reservar el viaje.
     */
    public int darCantidadAdultos( )
    {
        return cantidadAdultos;
    }

    /**
     * Retorna la cantidad de niños por los que se va reservar el viaje.
     * @return Cantidad de niños por los que se va reservar el viaje.
     */
    public int darCantidadNinios( )
    {
        return cantidadNinios;
    }

    /**
     * Retorna la cantidad de noches de estadía en el hotel que se va a reservar.
     * @return Cantidad de noches de estadía en el hotel que se va a reservar.
     */
    public int darCantidadNochesEstadia( )
    {
        return cantidadNochesEstadia;
    }

    /**
     * Retorna el costo total de la reserva.
     * @return Costo total de la reserva.
     */
    public double darCostoTotal( )
    {
        return costoTotal;
    }

    /**
     * Retorna la aerolínea que se va a reservar.
     * @return Aerolínea que se va a reservar.
     */
    public Aerolinea darAerolinea( )
    {
        return aerolinea;
    }

    /**
     * Retorna el hotel que se va a reservar.
     * @return Hotel del viaje que se va a reservar.
     */
    public Hotel darHotel( )
    {
        return hotel;
    }

    /**
     * Retorna la fecha de llegada al hotel que se va a reservar.
     * @return Fecha de llegada al hotel que se va a reservar.
     */
    public Date darFechaLlegada( )
    {
        return fechaLlegada;
    }
    
    /**
     * Devuelve el estado de la reserva
     * @return Estado de la reserva
     */
    public Estado darEstado( )
    {
        return estado;
    }
    
    /**
     * Modifica el estado de la reserva
     * @param nEstado el nuevo estado de la reserva. nEstado != null
     */
    public void cambiarEstado(Estado nEstado)
    {
        estado = nEstado;
    }

    
    /**
     * Retorna una cadena con el nombre del cliente y el hotel de la reserva.
     * @return Representación de la reserva en una cadena de caracteres con el siguiente formato: <cliente> (<hotel>).
     */
    public String toString( )
    {
        
        return cedula + "-" +nombreCliente + " (" + hotel + ")";
    }

    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * nombreCliente != null && nombreCliente != "". <br>
     * cantidadAdultos >= 1. <br>
     * nochesEstadia >= 1. <br>
     * costoTotal > 0. <br>
     * aerolinea !=null <br>
     * hotel != null. <br>
     * fechaLlegada != null. <br>
     */
    private void verificarInvariante( )
    {
        assert ( nombreCliente != null && !nombreCliente.equals( "" ) ) : "El nombre del cliente responsable de la reserva debe existir.";
        assert ( cantidadAdultos >= 1 ) : "La reserva debe tener por lo menos un adulto en el viaje.";
        assert ( cantidadNochesEstadia >= 1 ) : "Se debe hacer la reserva por mínimo una noche.";
        assert ( costoTotal > 0 ) : "El costo total de la reserva debe ser mayor a 0.";
        assert ( aerolinea != null) : "La aerolínea debe pertenecer a: Avianca, Lan, Viva Colombia, Satena, jetBlue, Iberia o Air France.";
        assert hotel != null : "La reserva debe tener un hotel asociado.";
        assert fechaLlegada != null : "La reserva debe tener una fecha de llegada.";
        assert estado != null : "La reserva debe tener un estado.";
    }

    @Override
    public String darIdentificador( )
    {
        //  Auto-generated method stub
        return cedula;
    }

   

}
