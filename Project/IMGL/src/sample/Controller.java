package sample;

/**
 * Created by pridybailo-n on 02.12.2016.
 */

import filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private String w,h;
    private MyImage currentImage;
    private Desktop desktop = Desktop.getDesktop();

    @FXML
    private javafx.scene.control.TextField width = new TextField();

    @FXML
    private javafx.scene.control.TextField height = new TextField();

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
        width.setDisable(true);
        height.setDisable(true);
        slider.setDisable(true);
    }

    public void getSelectedInstr() throws IOException {
        if(currentImage == null || instrList.getSelectionModel().getSelectedItem() == null) return;
        BufferedImage result = null;
        switch(instrList.getSelectionModel().getSelectedItem()) {
            case "Brightness":
                slider.setDisable(false);
                result = new ImageBuilder(currentImage).add(new BrightnessChange((int)slider.getValue()))
                        .build().getBufferedImage();
                break;
            case "Binary":
                slider.setDisable(false);
                result = new ImageBuilder(currentImage).add(new Binarization((int)slider.getValue())).
                        build().getBufferedImage();
                break;
            case "Slice":
                slider.setDisable(false);
                result = new ImageBuilder(currentImage).add(new Slice((int)slider.getValue()/20))
                        .build().getBufferedImage();
                break;
            case "Negative":
                slider.setDisable(true);
                result = new ImageBuilder(currentImage).add(new Negative()).build().getBufferedImage();
                break;
            case "Equalization":
                slider.setDisable(true);
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
        w = new String(String.valueOf(currentImage.getBufferedImage().getWidth()));
        h = new String(String.valueOf(currentImage.getBufferedImage().getHeight()));
        width.setText(w);
        height.setText(h);

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
        System.out.println(file.length());
        if (file != null) {
            loadNewImage(file);
        }
    }

    public void saveImage() throws IOException {
        if (currentImage != null) {
            ImageIO.write(currentImage.getBufferedImage(),
                    "jpg", new File(".\\save\\" + new SimpleDateFormat("HHmmss").
                            format(Calendar.getInstance().getTime()) + ".jpg"));
        }
    }

}
