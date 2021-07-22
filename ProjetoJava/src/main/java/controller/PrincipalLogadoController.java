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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Senha;
import start.App;

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
    private TableView<?> tableSenhas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCadastrarSenhaOnAction(ActionEvent event) throws IOException {
        App.setRoot("cadastrarSenha");
    }

    @FXML
    private void btnVizualiarSenhasOnAction(ActionEvent event) {
    }

    @FXML
    private void tableSenhasOnSort(SortEvent<Senha> event) {
    }
    
}
