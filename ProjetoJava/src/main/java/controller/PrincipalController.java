/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
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
import static javafx.scene.effect.BlendMode.RED;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Login;
import model.Senha;
import model.dao.LoginJDBC;
import model.dao.SenhaJDBC;
import start.App;

/**
 * FXML Controller class
 *
 * @author jv
 */
public class PrincipalController implements Initializable {
    private int qtdClickNoCadastrar;
    private String caminhoImagem ="";
    private static int idDoLogin;
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
    private PasswordField txtFSenha;
    @FXML
    private Button btnCheckImage;
    @FXML
    private Label labelEmail;
    @FXML
    private Text txtAlertaSenha;
    @FXML
    private AnchorPane archorpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFSenha.setAccessibleRole(AccessibleRole.PASSWORD_FIELD);
        
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
        
        if(qtdClickNoCadastrar >0 ){
            txtFEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 150){
                txtFEmail.setText(oldValue);
            }
            if(newValue.length()== 1){
                txtAlertaEmail.setVisible(false);
            }
            if(newValue.length()== 0){
                txtAlertaEmail.setVisible(true);
            }
            ;});
        }
        
        
        
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
    private void btnCadastraUsuarioOnAction(ActionEvent event)throws Exception{
        qtdClickNoCadastrar++;
        if(!(caminhoImagem == "" || txtFNome.getText()== "" || txtFEmail.getText()== "" || txtFSenha.getText()== "") && qtdClickNoCadastrar == 2){ 
            Login login = new Login(caminhoImagem, txtFSenha.getText(), txtFNome.getText(), txtFEmail.getText());
            try{
                LoginJDBC newUser = new LoginJDBC();
                newUser.incluir(login);
                txtVerificarCadastro.setText("Cadastro realizado com sucesso!");
                btnCadastrarUsuario.setText("Cadastrar Usuário");
                qtdClickNoCadastrar = 0;
                txtFNome.setText("");
                txtFEmail.setText("");
                txtFSenha.setText("");
                labelEmail.setVisible(false);
                txtFEmail.setVisible(false);
                btnCheckImage.setVisible(false);
                btnLoginUsuario.setVisible(true);
                imgViewUsuario.setVisible(false);
            } catch (SQLException ex){
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(qtdClickNoCadastrar == 2){
            if(txtFNome.getText()== ""){
                txtAlertaNome.setVisible(true);
            }
            if(txtFEmail.getText()== ""){
                txtAlertaEmail.setVisible(true);
            }
            if(txtFSenha.getText()== ""){
                txtAlertaSenha.setVisible(true);
            }
            if(qtdClickNoCadastrar == 2){
               qtdClickNoCadastrar = 1; 
            }
        }
        
        if(qtdClickNoCadastrar == 1){
            labelEmail.setVisible(true);
            txtFEmail.setVisible(true);
            btnCheckImage.setVisible(true);
            btnCadastrarUsuario.setText("Confimar Cadastro");
            btnLoginUsuario.setVisible(false);
        }
    }

    @FXML
    private void btnLoginUsuarioOnAction(ActionEvent event) throws Exception{
        if(!(txtFNome.getText()== "" || txtFSenha.getText()== "")){
            Login login = new Login(txtFNome.getText(),txtFSenha.getText());
            try{
                LoginJDBC newUser = new LoginJDBC();
                if(newUser.verificaLogin(login) != -1){
                    txtFNome.setText("");
                    txtFSenha.setText("");
                    idDoLogin = newUser.verificaLogin(login);
                    App.setRoot("principalLogado");   
                }else{
                    txtVerificarCadastro.setText("Senha/Usuário incorreto(s)!");
                }
            }catch (SQLException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btnCheckImageOnAction(ActionEvent event){
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Selecione uma Imagem");
            Stage stage = (Stage)archorpane.getScene().getWindow();
            File fileImage = filechooser.showOpenDialog(stage);
            Image image = new Image(fileImage.toURI().toString());
            imgViewUsuario.setImage(image);
            caminhoImagem = fileImage.toURI().toString();
            imgViewUsuario.setVisible(true);
    }

    public static int getIdDoLogin() {
        return idDoLogin;
    }

    public static void setIdDoLogin(int idDoLogin) {
        PrincipalController.idDoLogin = idDoLogin;
    }
    
    
}
