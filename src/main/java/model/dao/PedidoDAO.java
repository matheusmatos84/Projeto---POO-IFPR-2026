/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Pedido;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.Usuario;

/**
 *
 * @author Aluno
 */
public class PedidoDAO {
    public void create(Pedido p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        try{
            String query = "INSERT INTO Pedido (statusPedido, dataHoraRetirada, observacoes, Usuario_idUsuario, Cliente_id_cliente) VALUES(?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, p.getStatusPedido());
            stmt.setTimestamp(2, Timestamp.valueOf(p.getDataHoraRetirada()));
            stmt.setString(3, p.getObservacoes());
            stmt.setInt(4, p.getUsuario().getIdUsuario()); // Associação com a classe usuario
            stmt.setInt(5, p.getCliente().getIdCliente()); // Associação com a classe cliente
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Pedido Retirado com sucesso!");
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao retirar Pedido. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Pedido> read(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pedido> listaPedidos = new ArrayList();
        
        try {
            String query = "SELECT * FROM Pedido";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("idPedido"));
                p.setStatusPedido(rs.getString("statusPedido"));
                p.setDataHoraRetirada(rs.getTimestamp("dataHoraRetirada").toLocalDateTime());
                p.setObservacoes(rs.getString("observacoes"));
                
                // Definir os atributos da associação(fk)
                UsuarioDAO daoUsuario = new UsuarioDAO();
                Usuario u = daoUsuario.read(rs.getInt("idUsuario"));
                p.setUsuario(u);
                
                ClienteDAO daoCliente = new ClienteDAO();
                Cliente c = daoCliente.read(rs.getInt("id_cliente"));
                p.setCliente(c);
                
                listaPedidos.add(p);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Pedidos. ERRO: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return listaPedidos;
    }
}
