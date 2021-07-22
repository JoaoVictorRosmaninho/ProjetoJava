/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import start.App;

/**
 * FXML Controller class
 *
 * @author jv
 */
public class PrincipalController implements Initializable {

    @FXML
    private TextField txtFNome;
    @FXML
    private TextField txtFEmail;
    @FXML
    private Button btnCadastrarUsuario;
    @FXML
    private Button btnLoginUsuario;
    @FXML
    private ImageView imgViewUsuario;
    @FXML
    private Text txtVerificarCadastro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCadastraUsuarioOnAction(ActionEvent event) {
        txtFNome.setText("");
        txtFEmail.setText("");
        txtVerificarCadastro.setText("Cadastro realizado com sucesso!");
    }

    @FXML
    private void btnLoginUsuarioOnAction(ActionEvent event) throws IOException {
        App.setRoot("principalLogado");
    }
    
}
