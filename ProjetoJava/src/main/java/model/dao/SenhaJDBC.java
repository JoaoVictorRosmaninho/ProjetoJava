/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Senha;

/**
 *
 * @author jv
 */
public class SenhaJDBC implements InterfaceDao<Senha> {
    private Connection conn;
    

    public SenhaJDBC() throws SQLException {
        this.conn = ConnFactory.getConnection();
    }
    

    @Override
    public void incluir(Senha entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Senha(pass, descricao, desc_forca, login) VALUES(?, ?, ?, ?);");
        ps.setString(1, entidade.getPass());
        ps.setString(2, entidade.getDescricao());
        ps.setString(3, entidade.getDesc_senha());
        ps.setString(4, entidade.getLogin());
        ps.execute(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Senha entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Senha where id = ?");
        ps.setInt(1, entidade.getId());
        ps.execute(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Senha entidade) throws Exception {
        /*
        PreparedStatement ps = conn.prepareStatement("UPDATE Senha SET descricao = ?,login = ?, pass = ?, desc_forca = ?  WHERE id = ?");
        ps.setString(1, entidade.getDescricao());
        ps.setString(2, entidade.getLogin());
        ps.setString(3, entidade.getPass());
        ps.setString(4, entidade.getDesc_senha());
        ps.setInt(4, entidade.getId());
        ps.execute();   
        */
    }

    @Override
    public Senha pesquisarId(int id) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM senha WHERE id = ?)");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Senha s = null;
        if (rs.next()) {
            s = new Senha();
            s.setId(rs.getInt("id"));
            s.setDescricao(rs.getString("descricao"));
            s.setPass(rs.getString("pass"));
        }

        return s;    }

    @Override
    public List<Senha> listar() throws Exception {
        return null;
        /*
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM senha");
        ResultSet rs = ps.executeQuery();
        ArrayList<Senha> senhas = new ArrayList();
        while (rs.next()) {
            Senha s = new Senha();
            s.setId(rs.getInt("id"));
            s.setDescricao(rs.getString("descricao"));
            s.setPass(rs.getString("pass"));
            s.setDesc_senha(rs.getString("desc_forca"));
            s.setLogin(rs.getString("login"));
            senhas.add(s);
        }
        return senhas;
        */
    }
    
    public List<Senha> listarComFK(int id) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM senha WHERE loginID =?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Senha> senhas = new ArrayList();
        while (rs.next()) {
            Senha s = new Senha();
            s.setId(rs.getInt("id"));
            s.setDescricao(rs.getString("descricao"));
            s.setPass(rs.getString("pass"));
            s.setDesc_senha(rs.getString("desc_forca"));
            s.setLogin(rs.getString("login"));
            senhas.add(s);
        }
        return senhas;    
    }

    @Override
    public int verificaLogin(Senha entidade) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void incluirComFK(Senha entidade, int id) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Senha(pass, descricao, desc_forca, login,loginID) VALUES(?, ?, ?, ?, ?);");
        ps.setString(1, entidade.getPass());
        ps.setString(2, entidade.getDescricao());
        ps.setString(3, entidade.getDesc_senha());
        ps.setString(4, entidade.getLogin());
        ps.setInt(5, id);
        ps.execute(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void editarComFK(Senha entidade, int id) throws Exception {
        PreparedStatement ps = conn.prepareStatement("UPDATE Senha SET descricao = ?,login = ?, pass = ?, desc_forca = ?  WHERE id = ? and loginID = ?");
        ps.setString(1, entidade.getDescricao());
        ps.setString(2, entidade.getLogin());
        ps.setString(3, entidade.getPass());
        ps.setString(4, entidade.getDesc_senha());
        ps.setInt(5, entidade.getId());
        ps.setInt(6, id);
        ps.execute();   
    }
    
    public int countSenhas(String forca_senha,int id) throws SQLException{
        int qtd = 0;
        PreparedStatement ps = conn.prepareStatement("SELECT count(desc_forca) AS df FROM senha WHERE desc_forca =? and loginID =?");
        ps.setString(1, forca_senha);
        ps.setInt(2, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            qtd = rs.getInt("df");
        }
        return qtd;
    }
        
}
