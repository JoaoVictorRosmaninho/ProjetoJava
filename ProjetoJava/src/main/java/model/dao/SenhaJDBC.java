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
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Senha(pass, desc) VALUES(?, ?);");
        ps.setString(1, entidade.getDescricao());
        ps.setString(2, entidade.getPass());
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
        PreparedStatement ps = conn.prepareStatement("UPDATE Senha SET descricao = ?, pass = ?, desc_forca = ?  WHERE id = ?");
        ps.setString(1, entidade.getDescricao());
        ps.setString(2, entidade.getPass());
        ps.setInt(3, entidade.getId());
        ps.execute();   
    }

    @Override
    public Senha pesquisarId(int id) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Contato WHERE id = ?)");
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Senhas");
        ResultSet rs = ps.executeQuery();
        ArrayList<Senha> contatos = new ArrayList();
        while (rs.next()) {
            Senha s = new Senha();
            s.setId(rs.getInt("id"));
            s.setDescricao(rs.getString("descricao"));
            s.setPass(rs.getString("pass"));
            contatos.add(s);
        }
        return contatos;    
    }
        
}
