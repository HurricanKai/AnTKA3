package org.kaij.BeraterUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private TextField input;
    @FXML
    private TextArea history;

    private IQuestionMatcher questionMatcher;

    public MainController()
    {
        questionMatcher = new LocalQuestionMatcher();
    }

    @FXML
    private void handleInput()
    {
        String msg = input.textProperty().getValue();
        input.textProperty().setValue("");

        history.appendText("Du: " + msg + "\n");
        var result = questionMatcher.match(msg);
        history.appendText("Berater: " + result + "\n");
    }
}
