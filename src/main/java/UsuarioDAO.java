/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author Aluno
 */
import java.sql.PreparedStatement;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Usuario;
/**
 *
 * @author Aluno
 */
public class UsuarioDAO {
    public void create(Usuario u) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "INSERT INTO Usuario(nomeUsuario, emailUsuario, senhaUsuario, perfilUsuario) VALUES(?, ?, ?, ?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, u.getNomeUsuario());
            stmt.setString(2, u.getEmailUsuario());
            stmt.setString(3, u.getSenhaUsuario());
            stmt.setString(4, u.getPerfilUsuario());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Usuario. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Usuario> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList();
        
        try {
            String query = "SELECT * FROM Usuario";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNomeUsuario(rs.getString("nome"));
                u.setEmailUsuario(rs.getString("email"));
                u.setSenhaUsuario(rs.getString("senha"));
                u.setPerfilUsuario(rs.getString("perfil_usuario"));
                
                listaUsuarios.add(u);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Usuarios. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return listaUsuarios;
    }
    
    public Usuario read(int id){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM Usuario WHERE idUsuario = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            if (rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNomeUsuario(rs.getString("nome"));
                u.setEmailUsuario(rs.getString("email"));
                u.setSenhaUsuario(rs.getString("senha"));
                u.setPerfilUsuario(rs.getString("perfil_usuario"));
                
                return u;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Usuarios. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
    
    public void update(Usuario u){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String query = "UPDATE Usuario SET nomeUsuario = ?, emailUsuario = ?, senhaUsuario = ?, perfilUsuario = ? WHERE idUsuario = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, u.getNomeUsuario());
            stmt.setString(2, u.getEmailUsuario());
            stmt.setString(3, u.getSenhaUsuario());
            stmt.setString(4, u.getPerfilUsuario());
            stmt.setInt(5, u.getIdUsuario());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar Usuario. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void destroy(Usuario u){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try{
            String query = "DELETE FROM Usuario WHERE idUsuario = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, u.getIdUsuario());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario destruido com sucesso!");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao destruir Usuario. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}

