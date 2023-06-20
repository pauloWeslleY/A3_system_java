package Src;

import Data.ConnectionSQLite;

public class Main {

    public static void main(String[] args) {

        ConnectionSQLite database = new ConnectionSQLite();

        //Conectar banco
        database.conectar();

        // Desconectar banco
        //database.desconectar();
    }

}
