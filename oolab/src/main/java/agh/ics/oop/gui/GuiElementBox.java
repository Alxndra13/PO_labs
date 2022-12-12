package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;

import agh.ics.oop.Animal;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    VBox vBox = new VBox();
    Label label;

    public GuiElementBox(IMapElement element){
        try {
            Image image = new Image(new FileInputStream(element.getImageSrc()));
            ImageView imageView = new ImageView(image);

            //wymiary obrazka
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);

            //podpisy pod obrazkiem
            if (element instanceof Animal) label = new Label(element.getPosition().toString()); //dla zwierząt
            else { //dla trawy
                label = new Label("Grass");
                label.setTextFill(Color.rgb(72,155,0));
            }
            label.setFont(new Font("Arial", 10));

            vBox.getChildren().addAll(imageView, label);
            vBox.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException e) { //jeśli obrazek nie istnieje
            throw new RuntimeException("Source file not found");
        }



    }
}
