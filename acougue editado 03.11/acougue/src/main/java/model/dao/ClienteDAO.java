/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;


import java.sql.PreparedStatement;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Cliente;
/**
 *
 * @author Aluno
 */
public class ClienteDAO {
    public void create(Cliente c) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "INSERT INTO cliente(nome, telefone) VALUES(?, ?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Cliente. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Cliente> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Cliente> listaClientes = new ArrayList();
        
        try {
            String query = "SELECT * FROM cliente";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                
                listaClientes.add(c);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Clientes. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return listaClientes;
    }
    
    public Cliente read(int id){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM cliente WHERE id_cliente = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            if (rs.next()){
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                
                return c;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Clientes. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
    
    public void update(Cliente c){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String query = "UPDATE cliente SET nome = ?, telefone = ? WHERE id_cliente = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setInt(3, c.getIdCliente());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar Cliente. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void destroy(Cliente c){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String query = "DELETE FROM cliente WHERE id_cliente = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, c.getIdCliente());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente destruido com sucesso!");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao destruir Cliente. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
