/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Senha;
import model.dao.SenhaJDBC;
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
    private static Senha senhaSelecionada;

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtFDescricao;
    @FXML
    private TextField txtFLogin;
    @FXML
    private PasswordField txtFSenha;
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
        txtFSenha.setAccessibleRole(AccessibleRole.PASSWORD_FIELD);
        
        
        if(senhaSelecionada != null){
            txtFDescricao.setText(senhaSelecionada.getDescricao());
            txtFLogin.setText(senhaSelecionada.getLogin());
            txtFSenha.setText(senhaSelecionada.getPass());
            btnVoltar.setText("Cancelar");
            btnCadastrarSenha.setText("Editar");
            forcaSenha = calculaForcaSenha();
        }
        
        
        labelAlertaLogin.setTextFill(Color.RED);
        labelAlertaSenha.setTextFill(Color.RED);
        
        txtFSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 26){
                txtFSenha.setText(oldValue);
            }
            if(newValue.length()> 1){
                labelAlertaSenha.setVisible(false);
                circleDificuldade.setVisible(true);
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
    private void btnCadastrarSenhaOnAction(ActionEvent event) throws Exception {
        if(!(txtFLogin.getText()== "" || txtFSenha.getText()== "")){ 
            String forca;
            if(forcaSenha<11){
                forca = "FRACA";
            }else if (forcaSenha<19){
                forca = "MEDIANA";
            }else if(forcaSenha<28){
                forca = "BOA";
            }else{
                forca = "EXCELENTE";
            }
            Senha senha = new Senha(txtFLogin.getText(),txtFSenha.getText(),forca,txtFDescricao.getText());    
            try {
                SenhaJDBC newUser = new SenhaJDBC();
                if(senhaSelecionada == null){
                    newUser.incluirComFK(senha,PrincipalController.getIdDoLogin());
                }else{
                    senha.setId(senhaSelecionada.getId());
                    newUser.editarComFK(senha, PrincipalController.getIdDoLogin());
                    btnVoltar.setText("Voltar");
                    btnCadastrarSenha.setText("Cadastrar nova senha");
                    circleDificuldade.setVisible(false);
                    txtFDescricao.setText("");
                    txtFLogin.setText("");
                    txtFSenha.setText("");
                    forcaSenha = 0;
                    circleDificuldade.setFill(Color.RED);
                    zerarForcaSenhaUltima();
                    App.setRoot("principalLogado");
                }
                txtStatusSenhaCadastrada.setText("Senha criada com sucesso!");
                circleDificuldade.setVisible(false);
                txtFDescricao.setText("");
                txtFLogin.setText("");
                txtFSenha.setText("");
                forcaSenha = 0;
                circleDificuldade.setFill(Color.RED);
                zerarForcaSenhaUltima();
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarSenhaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }

    @FXML
    private void txtFSenhaOnTextChanged(KeyEvent event) {
        
        //circleDificuldade.setVisible(true);
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

    public static Senha getSenhaSelecionada() {
        return senhaSelecionada;
    }

    public static void setSenhaSelecionada(Senha senhaSelecionada) {
        CadastrarSenhaController.senhaSelecionada = senhaSelecionada;
    }
    
    private int calculaForcaSenha(){
        String auxString = txtFSenha.getText();
        int auxToIF = 0;
        int i = 0;
        while(i<auxString.length()){
            auxToIF = auxString.codePointAt(i);
            
            if(auxToIF > 96 && auxToIF < 123){
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
            i++;
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
        circleDificuldade.setVisible(true);
        return forcaSenha;
    }
    
    
    private void zerarForcaSenhaUltima(){
        int i = forcaSenhaUltima.length-1;
        while(i>=0){
            forcaSenhaUltima[i] = 0;
            i--;
        }
    }
}


