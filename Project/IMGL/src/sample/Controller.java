package sample;

import filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import models.MyImage;
import processor.ImageBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private MyImage currentImage;
    private Desktop desktop = Desktop.getDesktop();

    @FXML
    public static GridPane gPane = new GridPane();

    @FXML
    private ImageView currentView = new ImageView();

    @FXML
    private Slider slider = new Slider();

    @FXML
    ListView<String> instrList = new ListView<String>();
    ObservableList<String> data = FXCollections.observableArrayList(
            "Brightness", "Binary", "Slice", "Negative", "Equalization");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instrList.setItems(data);
    }

    public void getSelectedInstr() throws IOException {
        if(currentImage == null) return;
        BufferedImage result = null;
        switch(instrList.getSelectionModel().getSelectedItem()) {
            case "Brightness":
                result = new ImageBuilder(currentImage).add(new BrightnessChange((int)slider.getValue()))
                        .build().getBufferedImage();
                break;
            case "Binary":
                result = new ImageBuilder(currentImage).add(new Binarization((int)slider.getValue())).
                        build().getBufferedImage();
                break;
            case "Slice":
                result = new ImageBuilder(currentImage).add(new Slice((int)slider.getValue()/20))
                        .build().getBufferedImage();
                break;
            case "Negative":
                result = new ImageBuilder(currentImage).add(new Negative()).build().getBufferedImage();
                break;
            case "Equalization":
                result = new ImageBuilder(currentImage).add(new Equalization()).build().getBufferedImage();
                break;
            default:
                return;
        }
        ImageIO.write(result, "jpg", new File(".\\tmp\\TMP.jpg")); //.\tmp\TMP.jpg
        File file = new File(".\\tmp\\TMP.jpg");
        currentView.setImage(new Image(file.toURI().toString()));
    }


    private void loadNewImage(File file){
        currentImage = new MyImage(file);
        currentView.setImage(new Image(file.toURI().toString()));
    }


    public void openNewImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Looking for image...");
        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file =  fileChooser.showOpenDialog(Main.getMainStage());
        if (file != null) {
            loadNewImage(file);
        }
    }

}
