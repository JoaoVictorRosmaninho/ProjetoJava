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
public class Login {
    private int id;
    private String login;
    private String Email;
    private String pass;
    private String descricao;

    public Login() {
    }

    public Login(int id, String login, String pass, String descricao,String email) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.descricao = descricao;
        this.Email = email;
    }
    
    public Login(String descricao, String pass, String login, String email){
        this.descricao = descricao;
        this.pass = pass;
        this.login = login;
        this.Email = email;
    }
    
    public Login(String login, String pass){
        this.login = login;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
