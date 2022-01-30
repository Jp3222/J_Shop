package Modelo;

import Vistas.Vista_Login;
import com.jsql.conexion.Conexion;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jp
 */
public class Sistema {

    private static final Sistema Nodo = new Sistema();

    public static Sistema getNodo() {
        return Nodo;
    }

    private Conexion cn;
    private Operaciones op;
    private Calendar cl;
    private Tareas t;
    private Vista_Login login;
    private Cache cache;

    private Sistema() {
        constructor();
    }

    private void constructor() {
        //inicia la conexio a la base de datos
        cn = Conexion.getNodo("root", "12345", Conexion.getLOCAL_URL("jshop"));
        cn.conectar();
        //Carga datos en memoria para el acceso rapido a ellos
        cache = Cache.getNodo();
        cache.initProveedores();
        cache.initProductos();
        //Inicia la clase de operaciones con distintos objetos
        op = Operaciones.getNodo();
        login = new Vista_Login();
    }

    public void run() {
        login.setVisible(true);
    }

    private class Tareas extends TimerTask {

        @Override
        public void run() {

        }

    }

    public class Funciones {

        public boolean cierre() {
            return inicio() && fin();
        }

        private boolean inicio() {
            return cl.get(Calendar.HOUR_OF_DAY) > 6;
        }

        private boolean fin() {
            return cl.get(Calendar.HOUR_OF_DAY) < 10;
        }
    }

}
