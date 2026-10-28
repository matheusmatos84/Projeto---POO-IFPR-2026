/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;


import java.sql.PreparedStatement;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
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
}
