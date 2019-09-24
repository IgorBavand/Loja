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
import model.Usuario;
import model.Usuario;

/**
 *
 * @author igor
 */
public class UsuarioDAO {
    
    public boolean validarLogin(String login, String senha) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
           rs =  stmt.executeQuery();
           if(rs.next()){ 
               check = true;
               
            
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return check;
            
    }
    public void create(Usuario u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO usuario (nome, telefone, endereco, cpf, funcao, data, login, senha) VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getTelefone());
            stmt.setString(3, u.getEndereco());
            stmt.setString(4, u.getCpf());
            stmt.setString(5, u.getFuncao());
            stmt.setString(6, u.getData());
            stmt.setString(7, u.getLogin());
            stmt.setString(8, u.getSenha());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    }
    
    public List<Usuario> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Usuario> usuario = new ArrayList<>();
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario ORDER BY nome");
           rs =  stmt.executeQuery();
           while(rs.next()){ 
               
               Usuario u = new Usuario();
               
               u.setId(rs.getInt("id"));
               u.setNome(rs.getString("nome"));
               u.setTelefone(rs.getString("telefone"));
               u.setEndereco(rs.getString("endereco"));
               u.setCpf(rs.getString("cpf"));
               u.setFuncao(rs.getString("funcao"));
               u.setData(rs.getString("data"));
               u.setLogin(rs.getString("login"));
               usuario.add(u);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return usuario;
            
    }
    public void update(Usuario u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET nome=?,telefone=?,endereco=?,cpf=?,funcao=?,data=?,login=? WHERE id = ?");
           stmt.setString(1, u.getNome());
            stmt.setString(2, u.getTelefone());
            stmt.setString(3, u.getEndereco());
            stmt.setString(4, u.getCpf());
            stmt.setString(5, u.getFuncao());
            stmt.setString(6, u.getData());
            stmt.setString(7, u.getLogin());
            stmt.setInt(8, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    }
    
     public void delete(Usuario u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            stmt.setInt(1, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Exclído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    }
     
     public List<Usuario> readForDesc(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        List<Usuario> usuario = new ArrayList<>();
        
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
           rs =  stmt.executeQuery();
           
           while(rs.next()){ 
               
               Usuario u = new Usuario();
               
                u.setId(rs.getInt("id"));
               u.setNome(rs.getString("nome"));
               u.setTelefone(rs.getString("telefone"));
               u.setEndereco(rs.getString("endereco"));
               u.setCpf(rs.getString("cpf"));
               u.setFuncao(rs.getString("funcao"));
               u.setData(rs.getString("data"));
               u.setLogin(rs.getString("login"));
               usuario.add(u);
               
           }
           
           
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.ColseConnection(con, stmt, rs);
        
        }
        return usuario;
            
    }
     public void attDados(Usuario usu) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET login=?, senha=? WHERE id = ?");
            stmt.setString(1, usu.getLogin());
            stmt.setString(2, usu.getSenha());
            stmt.setInt(3, usu.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
        } finally {
            ConnectionFactory.ColseConnection(con, stmt);
        }

    }
    
    
}
