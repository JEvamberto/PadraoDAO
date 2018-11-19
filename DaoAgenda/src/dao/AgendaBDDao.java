/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Agenda;

/**
 *
 * @author jose
 */
public class AgendaBDDao implements Dao{
    
    private Connection conexao;
    
    public AgendaBDDao(){
        conexao= ConnectionFactory.getConnection();
    }

    @Override
    public boolean inserir(Object object) {
        
        this.conexao=ConnectionFactory.getConnection();
         Agenda agenda = (Agenda) object;
        
        String sql="INSERT INTO agenda (nome,email,telefone) VALUES (?, ?, ?)";
        
        PreparedStatement stmt=null;
        
        try {
            
            stmt=this.conexao.prepareStatement(sql);
            
            stmt.setString(1,agenda.getNome());
            stmt.setString(2, agenda.getEmail());
            stmt.setString(3, agenda.getTelefone());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            
            System.err.println("Erro:"+ex);
            
            return false;
        }finally{
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        
    }

    @Override
    public boolean deletar(Object object) {
        
        this.conexao=ConnectionFactory.getConnection();
        Agenda agenda=(Agenda) object;
        System.out.println(agenda.getId());
        
        String sql="DELETE FROM agenda WHERE id=?";
        PreparedStatement stmt=null;
        try {
            stmt=this.conexao.prepareStatement(sql);
            stmt.setInt(1, agenda.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            
            System.out.println("Erro:"+ex);
            return false;
        }finally{
           ConnectionFactory.closeConnection(conexao, stmt);
        }
                
        
    }

    @Override
    public List buscar() {
        
        this.conexao=ConnectionFactory.getConnection();
        List <Agenda> lista= new ArrayList<>();
        
        String sql ="SELECT * FROM agenda ";
        
        PreparedStatement stmt=null;
        ResultSet rs=null;
        
        try {
            stmt=this.conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                
                Agenda agenda1 = new Agenda ();
                
                agenda1.setEmail(rs.getString("email"));
                agenda1.setId(rs.getInt("id"));
                agenda1.setNome(rs.getString("nome"));
                agenda1.setTelefone(rs.getString("telefone"));
                
                lista.add(agenda1);
                
            }
            
            
            
        } catch (SQLException ex) {
            System.err.println("Erro:"+ex);
        }finally{
            ConnectionFactory.closeConnection(conexao,stmt,rs);
        }
        
        
        
        return lista;
    }

    @Override
    public boolean atualizar(Object object) {
        this.conexao=ConnectionFactory.getConnection();
        Agenda agenda = (Agenda)object;
        
        
        String sql="UPDATE agenda SET nome=?,email=?,telefone=? WHERE id=?";
        PreparedStatement stmt=null;
        
        try {
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1,agenda.getNome() );
            stmt.setString(2, agenda.getEmail());
            stmt.setString(3, agenda.getTelefone());
            stmt.setInt(4,agenda.getId());
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Erro:"+ex);
            return false;
        }finally{
            
            ConnectionFactory.closeConnection(conexao,stmt);
            
        }
        
        
       
    }
    
    
    
    
    
}
