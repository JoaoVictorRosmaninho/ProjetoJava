/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 *
 * @author jv
 */
public class daoFactory {
    public static SenhaJDBC novaSenhaDao() throws Exception {
        return new SenhaJDBC();
    }
    
    public static LoginJDBC novoLoginDao() throws Exception {
        return new LoginJDBC();
    }
}
