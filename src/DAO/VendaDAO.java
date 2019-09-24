/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import model.Venda;

/**
 *
 * @author Igor
 */
public class VendaDAO {
    public void create(Venda v) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO venda (cliente, telefone, vendedor, data, valor, forma) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, v.getCliente());
            stmt.setString(2, v.getCel());
            stmt.setString(3, v.getVendedor());
            stmt.setString(4, v.getData());
            stmt.setDouble(5, v.getValor());
            stmt.setString(6, v.getForma());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Venda Realizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar venda!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    }
    
    public List<Venda> readForDesc(String forma) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Venda> venda = new ArrayList<>();
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM venda WHERE forma LIKE ?");
            stmt.setString(1, "%"+"Pendente"+"%");
           rs =  stmt.executeQuery();
           
           while(rs.next()){ 
               
               Venda u = new Venda();
               
               u.setId(rs.getInt("id"));
               u.setCliente(rs.getString("cliente"));
               u.setCel(rs.getString("telefone"));
               u.setVendedor(rs.getString("vendedor"));
               u.setValor(rs.getDouble("valor"));
               u.setData(rs.getString("data"));
               u.setForma(rs.getString("forma"));
               venda.add(u);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return venda;
            
    }
    
    public void darBaixa(Venda u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE venda SET forma=? WHERE id = ?");
           stmt.setString(1, u.getForma());
            
            stmt.setInt(2, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Pagameto Registrado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    } 
    public List<Venda> Pesquisa(String pesquisa) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Venda> venda = new ArrayList<>();
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM venda WHERE cliente LIKE ? ORDER BY cliente, data");
            
            stmt.setString(1, "%"+pesquisa+"%");
           rs =  stmt.executeQuery();
           
           while(rs.next()){ 
               
               Venda u = new Venda();
               
               u.setId(rs.getInt("id"));
               u.setCliente(rs.getString("cliente"));
               u.setCel(rs.getString("telefone"));
               u.setVendedor(rs.getString("vendedor"));
               u.setValor(rs.getDouble("valor"));
               u.setData(rs.getString("data"));
               u.setForma(rs.getString("forma"));
               venda.add(u);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return venda;
            
    }
    public List<Venda> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Venda> venda = new ArrayList<>();
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM venda");
           rs =  stmt.executeQuery();
           while(rs.next()){ 
               
               Venda u = new Venda();
               
               u.setId(rs.getInt("id"));
               u.setCliente(rs.getString("cliente"));
               u.setCel(rs.getString("telefone"));
               u.setVendedor(rs.getString("vendedor"));
               u.setValor(rs.getDouble("valor"));
               u.setData(rs.getString("data"));
               u.setForma(rs.getString("forma"));
               venda.add(u);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return venda;
            
    }
}
