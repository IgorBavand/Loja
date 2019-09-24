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
import model.Produto;

/**
 *
 * @author Igor
 */
public class ProdutoDAO {

    public void create(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produto (tipo,descricao,fornecedor,preco,precocusto,data) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getFornecedor());
          //  stmt.setInt(4, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());
            stmt.setDouble(5, p.getPrecocusto());
            stmt.setString(6, p.getData());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    }
    
    
    
    
    
    
    

    public List<Produto> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Produto> produto = new ArrayList<>();
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produto ORDER BY descricao");
           rs =  stmt.executeQuery();
           while(rs.next()){ 
               
               Produto p = new Produto();
               
               p.setId(rs.getInt("id"));
               p.setTipo(rs.getString("tipo"));
               p.setDescricao(rs.getString("descricao"));
               p.setFornecedor(rs.getString("fornecedor"));
             //  p.setQuantidade(rs.getInt("quantidade"));
               p.setPreco(rs.getDouble("preco"));
               p.setPrecocusto(rs.getDouble("precocusto"));
               p.setData(rs.getString("data"));
               produto.add(p);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return produto;
            
    }
    public List<Produto> readVenda() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Produto> produto = new ArrayList<>();
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
           rs =  stmt.executeQuery();
           while(rs.next()){ 
               
               Produto p = new Produto();
               
               p.setId(rs.getInt("id"));
               p.setTipo(rs.getString("tipo"));
               p.setDescricao(rs.getString("descricao"));
               p.setPreco(rs.getDouble("preco"));
         
               produto.add(p);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return produto;
            
    }
    public List<Produto> readForDesc(String desc) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Produto> produto = new ArrayList<>();
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%");
           rs =  stmt.executeQuery();
           
           while(rs.next()){ 
               
               Produto p = new Produto();
               
               p.setId(rs.getInt("id"));
               p.setTipo(rs.getString("tipo"));
               p.setDescricao(rs.getString("descricao"));
               p.setFornecedor(rs.getString("fornecedor"));
            //   p.setQuantidade(rs.getInt("quantidade"));
               p.setPreco(rs.getDouble("preco"));
               p.setPrecocusto(rs.getDouble("precocusto"));
               p.setData(rs.getString("data"));
               produto.add(p);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return produto;
            
    }
    
    public void update(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET tipo=?,descricao=?,fornecedor=?,preco=?,precocusto=?,data=? WHERE id = ?");
           stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getFornecedor());
          //  stmt.setInt(4, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());
            stmt.setDouble(5, p.getPrecocusto());
            stmt.setString(6, p.getData());
            stmt.setInt(7, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    }
    
     public void delete(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Exclído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    }
    

}
