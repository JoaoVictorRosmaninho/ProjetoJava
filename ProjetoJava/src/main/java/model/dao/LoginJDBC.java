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
import model.Login;

/**
 *
 * @author jv
 */
public class LoginJDBC implements InterfaceDao<Login> {
    private Connection conn;

    public LoginJDBC() throws SQLException {
        this.conn = ConnFactory.getConnection();
    }
    

    @Override
    public void incluir(Login entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Login(login, pass, descricao, Email) VALUES(?, ?, ?, ?);");
        ps.setString(1, entidade.getLogin());
        ps.setString(2, entidade.getPass());
        ps.setString(3, entidade.getDescricao());
        ps.setString(4, entidade.getEmail());
        ps.execute(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Login entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Login where id = ?");
        ps.setInt(1, entidade.getId());
        ps.execute(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Login entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("UPDATE Login SET login = ?, pass = ?, desc = ?, Email = ?  WHERE id = ?");
        ps.setString(1, entidade.getDescricao());
        ps.setString(2, entidade.getPass());
        ps.setString(3, entidade.getPass());
        ps.setString(4, entidade.getEmail());
        ps.setInt(3, entidade.getId());
        ps.execute();   
    }

    @Override
    public Login pesquisarId(int id) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM login WHERE id = ?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Login s = null;
        if (rs.next()) {
            s = new Login();
            s.setId(rs.getInt("id"));
            s.setDescricao(rs.getString("descricao"));
            s.setPass(rs.getString("pass"));
            s.setEmail(rs.getString("Email"));
        }

        return s;    }

    @Override
    public List<Login> listar() throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Login");
        ResultSet rs = ps.executeQuery();
        ArrayList<Login> Logins = new ArrayList();
        while (rs.next()) {
            Login login = new Login();
            login.setId(rs.getInt("id"));
            login.setDescricao(rs.getString("descricao"));
            login.setPass(rs.getString("pass"));
            Logins.add(login);
        }
        return Logins;    
    }

    @Override
    public int verificaLogin(Login entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT id FROM Login WHERE login = ? and pass = ?;");
        ps.setString(1, entidade.getLogin());
        ps.setString(2, entidade.getPass());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            int i = rs.getInt("id");
            System.out.println(i);
            return i;
        }
        return -1;
    }

    @Override
    public void incluirComFK(Login entidade, int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
