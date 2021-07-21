/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jv
 */
public class contatoDaoJDBC implements InterfaceDao<Contato> {

    private Connection conn;

    public contatoDaoJDBC() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void incluir(Contato entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Contato(nome, email, telefone) VALUES(?, ?, ?);");
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getEmail());
        ps.setString(3, entidade.getTelefone());
        ps.execute();
    }

    @Override
    public void excluir(Contato entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Contato where id = ?");
        ps.setInt(1, entidade.getId());
        ps.execute();
    }

    @Override
    public void editar(Contato entidade) throws Exception {
  
        PreparedStatement ps = conn.prepareStatement("UPDATE Contato SET nome = ?, email = ?, telefone = ? WHERE id = ?");
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getEmail());
        ps.setString(3, entidade.getTelefone());
        ps.setInt(4, entidade.getId());
        ps.execute();
    }

    @Override
    public Contato pesquisarId(int id) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Contato WHERE id = ?)");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Contato c = null;
        if (rs.next()) {
            c = new Contato();
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone")); 
        }

        return c;
        
    }

    @Override
    public ArrayList<Contato> listar() throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Contato");
        ResultSet rs = ps.executeQuery();
        ArrayList<Contato> contatos = new ArrayList();
        while (rs.next()) {
            Contato c = new Contato();
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));
            contatos.add(c);
        }
        return contatos;
    }

}
