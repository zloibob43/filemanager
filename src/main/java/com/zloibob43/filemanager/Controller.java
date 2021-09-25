package com.zloibob43.filemanager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {

    @FXML
    VBox leftPanel, rightPanel;

    @FXML
    protected void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void copyBtnAction(ActionEvent actionEvent) {
        PanelController leftPanelController = (PanelController) leftPanel.getProperties().get("ctrl");
        PanelController rightPanelController = (PanelController) rightPanel.getProperties().get("ctrl");

        if (leftPanelController.getSelectedFileName() == null && rightPanelController.getSelectedFileName() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ни один файл не был выбран", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        PanelController source = null, destination = null;

        if (leftPanelController.getSelectedFileName() != null) {
            source = leftPanelController;
            destination = rightPanelController;
        }

        if (rightPanelController.getSelectedFileName() != null) {
            source = rightPanelController;
            destination = leftPanelController;
        }

        Path fileToCopySource = Paths.get(source.getCurrentPath(), source.getSelectedFileName());
        Path fileToCopyDestination = Paths.get(destination.getCurrentPath()).resolve(fileToCopySource.getFileName().toString());

        try {
            Files.copy(fileToCopySource, fileToCopyDestination);
            destination.updateList(Paths.get(destination.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось скопировать указанный файл", ButtonType.OK);
            alert.showAndWait();
        }
    }
}