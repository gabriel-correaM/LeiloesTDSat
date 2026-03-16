import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public Connection connectDB(){

        Connection conn = null;

        try {

            String url = "jdbc:mysql://localhost:3306/uc11";
            String user = "root";
            String password = "root";

            conn = DriverManager.getConnection(url, user, password);

            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro ao conectar: " + erro.getMessage());

        }

        return conn;
    }
}
