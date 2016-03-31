package com.example.manuel.tarea3.base_de_datos;

/**
 * Created by Manuel on 22/01/2016.#
 *
 */
public class EstructuraBd {

    public  static String NOMBRE_DB = "tarea3.db";
    public  static int VERSION_DB = 1;


    //Estamos describiendo todos los elementos de la tabla
    public static class TABLA_COCHES{

        public  static String TABLA_NOMBRE = "coche";

        public  static String ID = "id";
        public  static String NOMBRE = "nombre";
        public  static String COLOR = "color";
        public  static String ASIENTOS = "asientos";


    }

    public static class TABLA_CONDUCTORES{

        public  static String TABLA_NOMBRE = "conductor";

        public  static String ID = "id";
        public  static String DNI = "dni";
        public  static String NOMBRE = "nombre";
        public  static String DIRECCION = "direccion";
        public  static String EMAIL = "email";
        public  static String TELEFONO = "telefono";
        public  static String ID_COCHE = "coche_id";


    }

    public static class TABLA_CONDU_COCHES{

        public  static String TABLA_NOMBRE = "conductor_coche";

        public  static String ID = "id";
        public  static String ID_CCONDUCTOR = "id_conductor";
        public  static String ID_COCHE = "id_coche";


    }







}
