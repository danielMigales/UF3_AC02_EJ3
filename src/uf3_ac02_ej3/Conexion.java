/**
 * @Daniel Migales
 */
package uf3_ac02_ej3;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conexion {

    private static Connection conection;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/prediccionmeteorologica";

    private static String nombreTabla = "prediccion";
    private static ArrayList<String> listaCampos;
    private static ArrayList<String> tipoCampos;

    public Conexion() {

        conection = null;
        try {
            Class.forName(driver);
            conection = (Connection) DriverManager.getConnection(url, user, password);
            if (conection != null) {
                System.out.println("Conexion a la base de datos correcta.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos." + e);
        }
    }

    public void desconectar() {

        conection = null;
    }

    public void getCamposTabla() throws SQLException {

        listaCampos = new ArrayList<>();
        tipoCampos = new ArrayList<>();

        Statement st = null;
        Statement st2 = null;
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + nombreTabla + "'";
        String sql2 = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + nombreTabla + "'";

        try {
            st = conection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                listaCampos.add(rs.getString(1));
            }
            st2 = conection.createStatement();
            ResultSet rs2 = st.executeQuery(sql2);
            while (rs2.next()) {
                tipoCampos.add(rs2.getString(1));
            }
        } finally {
            if (st != null) {
                st.close();
                st2.close();
            }
        }
    }

    public void consultarTiempo(String ciudad, String fecha) throws SQLException {

        getCamposTabla();
        String sql = "SELECT * FROM prediccion WHERE Ciudad = '" + ciudad + "'" + " AND Fecha = '" + fecha + "'";
        Statement st = null;

        try {
            st = conection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int contador = 0;
            System.out.println(" ");
            while (rs.next()) {
                for (int i = 0; i < listaCampos.size(); i++) {
                    String dato = rs.getString(listaCampos.get(i));
                    System.out.println(listaCampos.get(i) + ": " + dato);
                }
                System.out.println(" ");
                contador++;
            }
            if (contador == 0) {
                System.out.println("No hay datos en esta tabla.");
            }
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
}
