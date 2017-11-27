package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.ParseProgram;


public class Gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        Stage window = primaryStage;
        window.setTitle("Analizzatore Sintattico");

        Button button = new Button("OK");
        Button button2 = new Button("Indietro");
        Label label = new Label("Inserisci il Programma");
        Label risultato= new Label();

        TextArea textArea = new TextArea();
        VBox layout3 = new VBox(30);
        HBox layout2 = new HBox(15);
        VBox layout = new VBox(10);
        HBox layout4 = new HBox(40);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(textArea, button);
        layout2.setPadding(new Insets(20,20,20,20));
        layout2.getChildren().addAll(label);
        layout3.getChildren().addAll(layout2,layout);
        layout4.getChildren().addAll(button2,risultato);
        Scene scene = new Scene(layout3,355,250);
        Scene scene2 = new Scene(layout4,500,300);

        window.setScene(scene);
        window.show();

        button.setOnAction((ActionEvent event) -> {
            String result = (ParseProgram.parse(textArea.getText())).toString();
            //Metodo che sistema result


            //Metodo che disegna l'albero
            StackPane layout5=drawTree(result);

            layout5.getChildren().add(button2);
            Scene scene3 = new Scene(layout5,500,400);
            window.setScene(scene3);
        });

        button2.setOnAction((ActionEvent event) -> {
            window.setScene(scene);
        });


    }


    public StackPane drawTree(String input){
        /*VBox layout = new VBox(30);
        layout.setPadding(new Insets(20,20,20,20));*/


        TreeItem<String> rootItem = new TreeItem<> ("Program");
        rootItem.setExpanded(true);
        {
         //   String s[]=input.split("!"); //s[0] = "!" Crea il nodo root
            String s1[]= input.split("$");   //s1[0] = "$" Crea s1.length nodi dichiarazioni
            String s2[]= s1[1].split("&");   // s2[0] = dichiarazioni s2[1] = simp  Crea s2.length nodi stmt
            //riempi i nodi dichiarazioni e stmt con i contenuti di s1 e s2
            for (String string: s1
                 ) {
                TreeItem<String> item = new TreeItem<String> (string);
                rootItem.getChildren().add(item);
            }
            for (String string: s2
                 ) {
                TreeItem<String> item = new TreeItem<String> (string);
                rootItem.getChildren().add(item);
            }
        }


        //Mi serve una funzione ricorsiva che esplori l'albero

        TreeView<String> tree = new TreeView<> (rootItem);
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        return root;
    }
}