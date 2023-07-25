
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean conectar() { // método que vai fazer a conexão com o banco de dados 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?useSSL=false", "root", "novapasta.");
            System.out.println("Conectado");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    public void desconectar() { // método que vai desconectar do banco de dados 
        try {
            conn.close();
            System.out.println("Desconectado!");
        } catch (SQLException ex) {

        }
    }

    public void cadastrarProduto(ProdutosDTO produto) {
        
        try {
            prep = conn.prepareStatement("INSERT INTO produtos VALUES(0,?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Cadastrado");

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Produto Não Cadastrado");

        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}