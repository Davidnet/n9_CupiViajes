package uniandes.cupi2.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import uniandes.cupi2.cupiViajes.excepciones.PersistenciaException;


/**
 * Clase encargada de serializar y deserializar objetos
 * @author alvar-go
 */
public class Serializador {

    /**
     * Método encargado de serializar objetos
     * @param ruta ruta donde se creará el archivo con el objeto serializado. ruta != null. Si no existe el archivo con la ruta se crea
     * @param objeto el objeto que se desea serializar
     * @throws PersistenciaException Se lanza en caso que no se pueda escribir el archivo
     */
    public static void guardar(String ruta, Serializable objeto)throws PersistenciaException
    {
        //  TOREVIEW Parte 3.B complete el método según la documentación
        //	En caso de que no exista el archivo lo debe crear
        // 	En caso que no se pueda escribir el archivo debe generar PersistenciaException
        try {
            File archivo = new File(ruta);
            if (!archivo.exists()){
                archivo.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(objeto);
            oos.close();
        }
        catch (Exception e){
            throw new PersistenciaException("No se pudo guardar la información: " + e.getMessage(), ruta );
        }

    }

    /**
     * Carga un objeto desde un archivo serializado
     * pre: El archivo solo tiene un objeto
     * @param ruta la ruta donde se encuentra el archivo que se desea cargar
     * @param objeto el objeto donde se guadará la información cargada. objeto != null
     * @return en caso que se pueda cargar la información se guarda en el objeto y se devuelve, de lo contrario solo se devuelve el objeto
     * @throws PersistenciaException Se lanza en caso que no se pueda leer el archivo o se genere algún tipo de error al leerlo
     */
    public static <T> T cargar(String ruta, T objeto)throws PersistenciaException
    {
        try {
            File archivo = new File(ruta);
            if(archivo.exists())
            {
                // REVIEW Parte 3.C Complete el método de acuerdo a la documentación
                // IMPORTANTE El objeto cargado se debe guardar en el ojeto que llega por parámetro
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                objeto = (T)ois.readObject();
                ois.close();
            }

        } catch (Exception e) {
            // 7.B Complete el método de acuerdo a la documentación
            throw new PersistenciaException("No se pudo cargar la información: " + e.getMessage(), ruta);
        }
        return objeto;

    }


}
