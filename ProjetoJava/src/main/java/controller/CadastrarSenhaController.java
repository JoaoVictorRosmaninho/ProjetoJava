/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author wilse
 */
public class CadastrarSenhaController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtFDescricao;
    @FXML
    private TextField txtFLogin;
    @FXML
    private TextField txtFSenha;
    @FXML
    private Button btnCadastrarSenha;
    @FXML
    private Circle circleDificuldade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnVoltarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCadastrarSenhaOnAction(ActionEvent event) {
    }
    
}
