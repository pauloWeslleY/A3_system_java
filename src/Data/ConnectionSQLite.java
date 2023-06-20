package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSQLite {

    private Connection connect;

    // Método de conexão com o banco de dados - Lógica Booleana
    public boolean conectar() {

        try {
            //String de conexão com o banco "users.db"
            String jdbcURL = "jdbc:sqlite:db/users.db";
            String jdbcTable = "CREATE TABLE users (\n"
                    + "    id       INTEGER   PRIMARY KEY,\n"
                    + "    username TEXT (80),\n"
                    + "    password TEXT (80),\n"
                    + "    email    TEXT (80) \n"
                    + ");";

            //Estabelecendo conexão com o driver - SQLite 3.36.0.3
            this.connect = DriverManager.getConnection(jdbcURL);

            //Criando statament
            Statement stmt = connect.createStatement();

            //Adicionando pelo statament
            stmt.executeUpdate(jdbcTable);

        } catch (SQLException e) {
            // Relatando problemas com a conexão
            System.out.println("Erro! Não foi possível conectar ao banco de dados");
            System.err.println(e.getMessage());

            return false;
        }

        System.out.println("Conexão estabelecida com sucesso!");
        return true;

    }

    // Método para desconectar do banco - Lógica Booleana
    public boolean desconectar() {

        try {
            if (this.connect.isClosed() == false) {
                this.connect.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        System.out.println("Banco de dados desconectado com sucesso!");
        return true;
    }

}
