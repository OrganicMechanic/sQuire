package squire.controllers;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import squire.FileList;


import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by MattDaniel on 3/31/16.
 */
public class EditorController implements Initializable
{
    @FXML private ImageView avatarImageView;
    @FXML private Button homeButton;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        avatarImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onAvatarImageViewClick());

    }


    private void onAvatarImageViewClick()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("User Profile");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(avatarImageView.getScene().getWindow());
        dialogStage.setResizable(false);
        try
        {
            Parent root = loader.load(getClass().getResource("/fxml/Preferences2.fxml"));
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML private void onHomeButtonClick(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/fxml/Home.fxml"));
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setHeight(400);
            stage.setWidth(600);
            stage.setTitle("sQuire Home");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

public void getFileList(FileList fileList)
{
   // fl.copy(fileList);
   // fl.print();
}

//    @FXML private Button nextButton;
//    @FXML private Button backButton;
//    @FXML private Button finishButton;
//    @FXML private Button cancelButton1;
//    @FXML private Button cancelButton2;
//    @FXML private Button importFilesButton;
//    @FXML public TabPane tabPane;
//    @FXML public Tab projectDetailsTab;
//    @FXML public Tab projectSettingsTab;
//    @FXML public TextField productName;
//    @FXML Parent root;
//
//
//    // Opens file chooser, currently not functional
//    @FXML
//    private void importFilesButtonClicked(ActionEvent event)
//    {
//        Stage stage = null;
//        stage = (Stage) importFilesButton.getScene().getWindow();
//        //System.out.println(event.getSource());
//        if (event.getSource() == importFilesButton) {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Import File(s)");
//           File selectedFile = fileChooser.showOpenDialog(stage);
//            System.out.println(selectedFile.getName());
//        }
//    }
//
//    // Sends to second tab
//    @FXML
//    private void nextButtonClicked(ActionEvent event)
//    {
//        //System.out.println(event.getSource());
//        if (event.getSource() == nextButton) {
//            tabPane.getSelectionModel().select(projectSettingsTab);
//        }
//    }
//
//
//    //Sends back to first tab
//    @FXML private void backButtonClicked(ActionEvent event)
//    {
//       // System.out.println(event.getSource());
//        if (event.getSource() == backButton)
//        {
//            if (event.getSource() == backButton) {
//                tabPane.getSelectionModel().select(projectDetailsTab);
//            }
//        }
//    }
//
//    // Sends to editor
//    @FXML private void finishButtonClicked(ActionEvent event)
//    {
//        Stage stage = null;
//        Parent root = null;
//
//        if (event.getSource() == finishButton)
//        {
//            FXMLLoader loader = new FXMLLoader();
//            stage = (Stage) finishButton.getScene().getWindow();
//            stage.setResizable(true);
//            try
//            {
//                // Create the project
//                String filename;
//                filename = productName.getText();
//                System.out.println(filename);
//
//                File testdir = new File(filename);
//                if(!testdir.exists())
//                {
//                    testdir.mkdir();
//                    testdir.delete();
//                }
//
//                root = loader.load(getClass().getResource("/fxml/Editor.fxml"));
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//                System.out.println(event.getSource());
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    //Sends back to home screen
//    @FXML private void cancelButtonClicked(ActionEvent event)
//    {
//        Stage stage = null;
//        Parent root = null;
//        if (event.getSource() == cancelButton1 || event.getSource() == cancelButton2)
//        {
//            FXMLLoader loader = new FXMLLoader();
//            stage = (Stage) cancelButton1.getScene().getWindow();
//            try
//            {
//                root = loader.load(getClass().getResource("/fxml/Home.fxml"));
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//
//        if (root != null)
//        {
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//        else
//        {
//            System.out.println("Null scene.");
//        }
//
//    }
}