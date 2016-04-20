package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import squire.FileList;

import java.net.URL;
import java.nio.file.CopyOption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

/**
 * Created by MattDaniel on 3/31/16.
 */
public class NewProjectController2 implements Initializable
{
    @FXML private Button browseButton;
    @FXML private Button backButton;
    @FXML private Button cancelButton;
    @FXML private Button finishButton;
    @FXML private Button importFilesButton;
    @FXML public TextField projectTitle;
    @FXML public TextField browseDisplay;
    @FXML Parent root;

    FileList fl = new FileList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       // Context.getInstance().currentFileList();
    }

    // Opens file chooser, currently not functional
    @FXML
    private void importFilesButtonClicked(ActionEvent event)
    {
        Stage stage = null;
        stage = (Stage) importFilesButton.getScene().getWindow();

        if (event.getSource() == importFilesButton) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Import File(s)");
            File selectedFile = fileChooser.showOpenDialog(stage);

            //Adds file to list
            if (selectedFile != null)
            {
                fl.addFile(selectedFile.toString());
            }

            //TODO: make multiple files selectable, copy files over
          //  System.out.println(selectedFile.getName());
        }
    }

    // Sends to editor
    @FXML private void finishButtonClicked(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;

        if (event.getSource() == finishButton)
        {

            try
            {
                if (projectTitle.getText().isEmpty())
                {
                    //TODO: Display an error dialog.
                }

                else
                {
                    // Create the project
                    String directoryName;
                    String projectName;
                    directoryName = browseDisplay.getText();
                    projectName = projectTitle.getText();
                    String fullPath = directoryName + "/" + projectName;
                   // System.out.println(fullPath);

                    //Make the directory for the project at specified path and add
                    File testdir = new File(fullPath);
                    if (!testdir.exists()) {

                        //Make the directory if it doesn't exist
                        testdir.mkdir();

                        //Find the dummy Main.java file and copy it over
                        URL url = this.getClass().getResource("/Test_Files/Main.java");
                        File mainFile = new File(url.getPath());
                        Path from = mainFile.toPath();
                        Path to = Paths.get(fullPath + "/main.java");
                        CopyOption[] options = new CopyOption[]{
                                StandardCopyOption.REPLACE_EXISTING,
                                StandardCopyOption.COPY_ATTRIBUTES
                        };
                        Files.copy(from, to, options);


                        fl.addFile(to.toString());

                      //  fl.print();

                     //   testdir.delete();
                    }

                    //TODO: handle the case if the directory exists
                }
                //root = loader.load(getClass().getResource("/fxml/Editor.fxml"));

                // Set the next scene and pass the file object
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Editor.fxml"));
                root = loader.load();
                EditorController controller = loader.<EditorController>getController();
                //controller.getFileList(fl);
                stage = (Stage) finishButton.getScene().getWindow();
                stage.setResizable(true);

                Scene scene = new Scene(root);
                stage.setScene(scene);

                //TODO: set more 'proper' dimensions
                stage.setWidth(1920);
                stage.setHeight(1080);

                stage.show();
             //   System.out.println(event.getSource());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    //Sends back to home screen
    @FXML private void onBackButtonClick(ActionEvent event)
    {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == backButton || event.getSource() == cancelButton)
        {
            FXMLLoader loader = new FXMLLoader();
            stage = (Stage) backButton.getScene().getWindow();
            try
            {
                root = loader.load(getClass().getResource("/fxml/Home.fxml"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (root != null)
        {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(600);
            stage.setTitle("sQuire Home");
            stage.show();
        }
        else
        {
            System.out.println("Null scene.");
        }
    }

    @FXML private void browseButtonClicked(ActionEvent event)
    {
        if (event.getSource() == browseButton)
        {
            Stage stage = null;
            stage = (Stage) browseButton.getScene().getWindow();
            DirectoryChooser dirChoose = new DirectoryChooser();
            dirChoose.setTitle("Import File(s)");
            File selectedDir = dirChoose.showDialog(stage);
            if (selectedDir != null)
            {
                browseDisplay.setText(selectedDir.getPath());
            }
        }
    }
}