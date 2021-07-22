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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import start.App;

/**
 * FXML Controller class
 *
 * @author wilse
 */
public class CadastrarSenhaController implements Initializable {
    private int forcaSenha = 0;
    private final int[] forcaSenhaUltima = new int[25] ;
    private int indice = 0;

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
    @FXML
    private Label txtStatusSenhaCadastrada;
    @FXML
    private Label labelAlertaLogin;
    @FXML
    private Label labelAlertaSenha;

    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelAlertaLogin.setTextFill(Color.RED);
        labelAlertaSenha.setTextFill(Color.RED);
        
        txtFSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 26){
                txtFSenha.setText(oldValue);
            }
            if(newValue.length()== 1){
                labelAlertaSenha.setVisible(false);
            }
            if(newValue.length()== 0){
                circleDificuldade.setVisible(false);
                labelAlertaSenha.setVisible(true);
            }
            ;});
        
        
        txtFLogin.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 40){
                txtFLogin.setText(oldValue);
            }
            if(newValue.length()== 1){
                labelAlertaLogin.setVisible(false);
            }
            if(newValue.length()== 0){
                labelAlertaLogin.setVisible(true);
            }
            ;});
        
        
        txtFDescricao.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 100){
                txtFDescricao.setText(oldValue);
            }
            ;});
    }    

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws IOException {
        App.setRoot("principalLogado");
    }

    @FXML
    private void btnCadastrarSenhaOnAction(ActionEvent event) {
        if(!(txtFLogin.getText()== "" || txtFSenha.getText()== "")){ 
            txtStatusSenhaCadastrada.setText("Senha criada com sucesso!");
        }
        
        
        circleDificuldade.setVisible(false);
        txtFDescricao.setText("");
        txtFLogin.setText("");
        txtFSenha.setText("");
       
        forcaSenha = 0;
    }

    @FXML
    private void txtFSenhaOnTextChanged(KeyEvent event) {
        
        circleDificuldade.setVisible(true);
        String auxString = txtFSenha.getText();
        int auxToIF = 0;
        if(auxString.length()>=0 && auxString.length()<26){
            if(auxString.length() != 0){
                auxToIF = auxString.codePointAt(auxString.length()-1);
            }else{ 
                return;
            }
            if(event.getCode() == KeyCode.BACK_SPACE){
                if(indice > 0 && indice<25){
                    forcaSenha -= forcaSenhaUltima[indice-1];
                    indice--;
                }
            }else if(auxToIF > 96 && auxToIF < 123){
                if(indice < 25 && indice>=0){
                    forcaSenha += 1;
                    forcaSenhaUltima[indice] = 1;
                    indice++;
                }
            }else if(auxToIF > 64 && auxToIF < 91){
                if(indice < 25 && indice>=0){
                    forcaSenhaUltima[indice] = 2;
                    forcaSenha += 2;
                    indice++;
                }
            }else if(auxToIF > 47 && auxToIF < 58){
                if(indice < 25 && indice>=0){
                    forcaSenhaUltima[indice] = 3;
                    forcaSenha += 3;
                    indice++;
                }
            }else{
                if(indice < 25 && indice>=0){
                    forcaSenhaUltima[indice] = 4;
                    forcaSenha += 4;
                    indice++;
                }
            }
            
            
            if(forcaSenha<11){
                circleDificuldade.setFill(Color.RED);
            }else if (forcaSenha<19){
                circleDificuldade.setFill(Color.BROWN);
            }else if(forcaSenha<28){
                circleDificuldade.setFill(Color.BLUE);
            }else{
                circleDificuldade.setFill(Color.GREEN);
            }
        }
    }
}
