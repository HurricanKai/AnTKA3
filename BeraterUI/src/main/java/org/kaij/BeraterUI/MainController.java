package org.kaij.BeraterUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
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

        if (msg.contentEquals("ade")) {
            Platform.exit();
        }

        input.textProperty().setValue("");

        history.appendText("Du: " + msg + "\n");
        var result = questionMatcher.match(msg);

        if (result == null)
        {
            result = "Sorry, I did not understand, try phrasing that differently or ask me something else!";
        }

        history.appendText("Berater: " + result + "\n");
    }
}
