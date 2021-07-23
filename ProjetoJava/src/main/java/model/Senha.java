/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jv
 */
public class Senha {
    private String descricao;
    private String pass;
    private String desc_senha;
    private String login;
    
    public String getDesc_senha() {
        return desc_senha;
    }

    public void setDesc_senha(String desc_senha) {
        this.desc_senha = desc_senha;
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Senha( String login, String pass, String desc_senha, String descricao) {
        this.desc_senha = desc_senha;
        this.descricao = descricao;
        this.pass = pass;
        this.login = login;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Senha() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
}
