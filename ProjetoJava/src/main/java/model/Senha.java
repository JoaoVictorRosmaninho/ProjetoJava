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

    public Senha(String descricao, String pass) {
        this.descricao = descricao;
        this.pass = pass;
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
}
