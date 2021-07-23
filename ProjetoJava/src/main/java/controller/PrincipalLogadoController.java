/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import static java.sql.Types.NULL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Senha;
import model.dao.SenhaJDBC;
import model.dao.daoFactory;
import start.App;
import controller.PrincipalController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import model.Login;
import model.dao.LoginJDBC;

/**
 * FXML Controller class
 *
 * @author wilse
 */
public class PrincipalLogadoController implements Initializable {

    @FXML
    private Button btnCadastrarSenha;
    @FXML
    private Button btnVizualiarSenhas;
    @FXML
    private ImageView imgViewLogado;
    @FXML
    private VBox vboxSenhas;
    @FXML
    private TableView<Senha> tableSenhas;
    @FXML
    private TableColumn<Senha, String> columnLogin;
    @FXML
    private TableColumn<Senha, String> columnSenha;

    private List<Senha> listasenhas;
    private ObservableList<Senha> observableListSenhas;
    @FXML
    private TableColumn<Senha, String> columnForca;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnLoggout;
    @FXML
    private Label labelSenhasExcelentes;
    @FXML
    private Label labelSenhasFracas;
    @FXML
    private Label labelSenhasFortes;
    @FXML
    private Text txtSenhasExcelentes;
    @FXML
    private Label labelSenhasMedianas;
    @FXML
    private Text txtSenhasFortes;
    @FXML
    private Text txtSenhasFracas;
    @FXML
    private Text txtSenhasMedianas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setVisibleAux(false);
        setDadosDecriSenhas();
        LoginJDBC dao;
        try {
            dao = daoFactory.novoLoginDao();
            Login login = new Login();
            login = dao.pesquisarId(PrincipalController.getIdDoLogin());
            Image image = new Image(login.getDescricao());
            imgViewLogado.setImage(image);
            imgViewLogado.setVisible(true);
            
        } catch (Exception ex) {
            Logger.getLogger(PrincipalLogadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableSenhas.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if(tableSenhas.selectionModelProperty().getValue().getSelectedItem() != null){
                    btnEditar.setVisible(true);
                    btnExcluir.setVisible(true);    
                }
            }
        });
    }

    @FXML
    private void btnCadastrarSenhaOnAction(ActionEvent event) throws IOException {
        App.setRoot("cadastrarSenha");
    }

    @FXML
    private void btnVizualiarSenhasOnAction(ActionEvent event) {
        carregarDados();
        setDadosDecriSenhas();
        setVisibleAux(true);
    }

    public void setVisibleAux(boolean aux){
        labelSenhasFracas.setVisible(aux);
        labelSenhasMedianas.setVisible(aux);
        labelSenhasFortes.setVisible(aux);
        labelSenhasExcelentes.setVisible(aux);
        txtSenhasFracas.setVisible(aux);
        txtSenhasMedianas.setVisible(aux);
        txtSenhasFortes.setVisible(aux);
        txtSenhasExcelentes.setVisible(aux);
        vboxSenhas.setVisible(aux);
    }
    public void carregarDados(){
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnSenha.setCellValueFactory(new PropertyValueFactory<>("pass"));
        columnForca.setCellValueFactory(new PropertyValueFactory<>("desc_senha"));
        
        try{
            SenhaJDBC dao = daoFactory.novaSenhaDao();
            listasenhas = dao.listarComFK(PrincipalController.getIdDoLogin());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        observableListSenhas = FXCollections.observableArrayList(listasenhas);
        tableSenhas.setItems(observableListSenhas);
    }


    @FXML
    private void btnLoggout(ActionEvent event) throws IOException {
        App.setRoot("principal");
    }


    @FXML
    private void btnEditarOnAction(ActionEvent event) throws IOException {
        Senha senhaSelecionada = tableSenhas.selectionModelProperty().getValue().getSelectedItem();
        CadastrarSenhaController.setSenhaSelecionada(senhaSelecionada);
        App.setRoot("cadastrarSenha");
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Senha senhaSelecionada = tableSenhas.selectionModelProperty().getValue().getSelectedItem();
            if(!(senhaSelecionada.equals(NULL))){
            SenhaJDBC dao = daoFactory.novaSenhaDao();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("AVISO");
            alert.setContentText("Confirmar exlusão de "+senhaSelecionada.getPass()+"?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                dao.excluir(senhaSelecionada);
                carregarDados();
                setVisibleAux(true);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setContentText("Selecione uma senaha, para somente depois, apagá-la!");
        }
        
        
    }
    
    
    private void setDadosDecriSenhas(){
        SenhaJDBC dao;
        try {
            dao = daoFactory.novaSenhaDao();
            int aux =0;
            String auxDao = "";
            
            aux = dao.countSenhas("FRACA",PrincipalController.getIdDoLogin());
            auxDao = Integer.toString(aux);
            txtSenhasFracas.setText(auxDao);
            
            
            aux = dao.countSenhas("MEDIANA",PrincipalController.getIdDoLogin());
            auxDao = Integer.toString(aux);
            txtSenhasMedianas.setText(auxDao);
            
            
            aux = dao.countSenhas("BOA",PrincipalController.getIdDoLogin());
            auxDao = Integer.toString(aux);
            txtSenhasFortes.setText(auxDao);
            
            
            aux = dao.countSenhas("EXCELENTE",PrincipalController.getIdDoLogin());
            auxDao = Integer.toString(aux);
            txtSenhasExcelentes.setText(auxDao);
            
        } catch (Exception ex) {
            Logger.getLogger(PrincipalLogadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
