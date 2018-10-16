package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        GridPane layout = new GridPane();
        layout.setHgap(20);

        //Slider, którym kontrolujemy "czestotliwosc"
        //Trzeba zrobic z niego pokretlo
        Slider slider1 = new Slider(0,100,0);
        slider1.setMajorTickUnit(50);
        slider1.setSnapToTicks(true);
        slider1.setStyle(
                "-fx-opacity: 0.1" //set to 0 to hide it
        );

        layout.add(slider1,4,0,3,1);

        //Czestotliwosc, nie mozna jej bezposrednio kontrolowac, trzeba uzyc slider1
        Slider slider2 = new Slider(0,100,0);
        slider2.setDisable(true); //Blokujemy bezposrednia kontrole
        slider2.setMajorTickUnit(50);
        slider2.setSnapToTicks(true);
        layout.add(slider2,0,0,3,1);

        //Jesli zmieni sie slider1, to ustaw ta sama wartosc na slider2
        slider1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {

                slider2.adjustValue(slider1.getValue());
            }
        });

        //Wlacznik
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setMaxHeight(10);
        //Jesli jest wlaczone, to podswietl slider2
        toggleButton.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                if (toggleButton.isSelected())
                    slider2.setStyle("-fx-background-color: yellow");
                else
                    slider2.setStyle("");
            }
        });
        layout.add(toggleButton,0,2);

        layout.setPadding(new Insets(10));
        stage.setScene(new Scene(layout));
        stage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
