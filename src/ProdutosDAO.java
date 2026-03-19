/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    private conectaDAO conexao;
    private Connection conn;
    private PreparedStatement prep;
    
    public ProdutosDAO(){
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
        }
    
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
         int status;
        try{
            prep = this.conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)");
            
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            status = prep.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Falha ao cadastrar produto: "+ e.getMessage());
        }  
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
         ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try{
            prep = this.conn.prepareStatement("SELECT * FROM produtos");
            
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                listagem.add(produto);
            }
            
        }catch(SQLException e){
            System.out.println("Falha ao listar produtos: "+ e.getMessage());
        }
        return listagem;
    } 
    
    
       
    public int venderProduto(int id){
        try{
            prep = this.conn.prepareStatement("UPDATE produtos SET status =? WHERE id =?");
            
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            
            int status = prep.executeUpdate();
            return status;
        }catch(SQLException e){
            System.out.println("Falha ao vender produto: "+ e.getMessage());
            return e.getErrorCode();
        }
    }
    
    // Listagem dos produtos vendidos:
    private int teste;
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try{
            prep = this.conn.prepareStatement("SELECT * FROM produtos WHERE status ='vendido'");
            
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                listagem.add(produto);
            }
            
        }catch(SQLException e){
           
             System.out.println("Falha ao listar estes produtos: "+ e.getMessage());
        }
        return listagem;
        
    }
    
    
    
    
    
    
    
    
        
}

