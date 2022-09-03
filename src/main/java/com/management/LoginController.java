package com.management;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.synedra.validatorfx.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField txtUser;
    public PasswordField txtPassword;
    public Button btnLogin;
    public VBox vbBtn;
    public PasswordField txtConfirm;

    private Validator validator = new Validator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        validator.createCheck()
                .dependsOn("username", txtUser.textProperty())
                .withMethod(c -> {
                    String username = c.get("username");
                    if (username.isBlank()) {
                        c.error("Username should not be empty");
                    }
                })
                .decorates(txtUser)
                .immediate();
        btnLogin.disableProperty().bind(validator.containsErrorsProperty());

        validator.createCheck()
                .withMethod(c -> {
                    if (!c.get("password").equals(c.get("passwordConfirmation"))) {
                        c.error("Passwords do not match");
                    }
                })
                .dependsOn("password", txtPassword.textProperty())
                .dependsOn("passwordConfirmation", txtConfirm.textProperty())
                .decorates(txtPassword)
                .decorates(txtConfirm)
                .immediate();

        validator.createCheck()
                .withMethod(c -> {
                    String pwd = c.get("password");
                    if (pwd.length() < 8) {
                        c.error("Password should be at least 8 characters long.");
                    }
                })
                .dependsOn("password", txtPassword.textProperty())
                .decorates(txtPassword)
                .decorates(txtConfirm)
                .immediate();

        TooltipWrapper<Button> signUpWrapper = new TooltipWrapper<>(
                btnLogin,
                validator.containsErrorsProperty(),
                Bindings.concat("Cannot sign up:\n", validator.createStringBinding())
        );
        vbBtn.getChildren().add(signUpWrapper);
    }

    public void login(ActionEvent actionEvent) {
    }
}