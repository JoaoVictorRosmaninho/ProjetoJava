/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static javafx.scene.effect.BlendMode.RED;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
    @FXML
    private Text txtAlertaNome;
    @FXML
    private Text txtAlertaEmail;
    @FXML
    private TextField txtFSenha;
    @FXML
    private Text txtAlertaSenha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtAlertaNome.setFill(Color.RED);
        txtAlertaEmail.setFill(Color.RED);
        txtAlertaSenha.setFill(Color.RED);
        
        txtFNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 18){
                txtFNome.setText(oldValue);
            }
            
            if(newValue.length()== 1){
                txtAlertaNome.setVisible(false);
            }
            if(newValue.length()== 0){
                txtAlertaNome.setVisible(true);
            }
            ;});
        
        
        txtFEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 40){
                txtFEmail.setText(oldValue);
            }
            if(newValue.length()== 1){
                txtAlertaEmail.setVisible(false);
            }
            if(newValue.length()== 0){
                txtAlertaEmail.setVisible(true);
            }
            ;});
        
        
        txtFSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 26){
                txtFSenha.setText(oldValue);
            }
            
            if(newValue.length()== 1){
                txtAlertaSenha.setVisible(false);
            }
            if(newValue.length()== 0){
                txtAlertaSenha.setVisible(true);
            }
            ;});
    }    

    @FXML
    private void btnCadastraUsuarioOnAction(ActionEvent event) {
        if(!(txtFNome.getText()== "" || txtFEmail.getText()== "" || txtFSenha.getText()== "")){ 
            txtVerificarCadastro.setText("Cadastro realizado com sucesso!");
        }else{
            if(txtFNome.getText()== ""){
                txtAlertaNome.setVisible(true);
            }
            
            if(txtFEmail.getText()== ""){
                txtAlertaEmail.setVisible(true);
            }
            if(txtFSenha.getText()== ""){
                txtAlertaSenha.setVisible(true);
            }
        }
        txtFNome.setText("");
        txtFEmail.setText("");
        txtFSenha.setText("");
        
    }

    @FXML
    private void btnLoginUsuarioOnAction(ActionEvent event) throws IOException{
        if(!(txtFNome.getText()== "" || txtFEmail.getText()== "" || txtFSenha.getText()== "")){ 
            App.setRoot("principalLogado");
        }
        
    }
    
}
