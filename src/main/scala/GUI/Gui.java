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

import java.util.Arrays;


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

            //Metodo che disegna l'albero
            StackPane layout5=drawTree(result);

            layout5.getChildren().add(button2);
            Scene scene3 = new Scene(layout5,650,400);
            window.setScene(scene3);
        });

        button2.setOnAction((ActionEvent event) -> {
            window.setScene(scene);
        });


    }


    public StackPane drawTree(String input){
        /*VBox layout = new VBox(30);
        layout.setPadding(new Insets(20,20,20,20));*/
        String s[]=input.split("!"); //s[0] = "!" Crea il nodo root
        String m=input.substring(1);
        input=s[1];
        System.out.println("\\\\\\\\\\ " + s[0] + " ! " + s[1]);
        System.out.println("m "+m);
        TreeItem<String> rootItem = new TreeItem<> ("Program");
        rootItem.setExpanded(true);
/*
        {
            String s1[]= input.split("[\"$&!\"]");   //s1[0] = "$" Crea s1.length nodi dichiarazioni
         //   String s2[]= s1[1].split("[\"i\"][\",\"][\" \"][\"n\"][\",\"][\" \"][\"t\"][\",\"]");   // s2[0] = dichiarazioni s2[1] = simp  Crea s2.length nodi stmt
            //riempi i nodi dichiarazioni e stmt con i contenuti di s1 e s2
            for (String string: s1
                 ) {
                  exploreTree(string,rootItem);
            //    TreeItem<String> item = new TreeItem<> (string);
            //    rootItem.getChildren().add(item);
            //    String s2[]= string.split(">");   // s2[0] = dichiarazioni s2[1] = simp  Crea s2.length nodi stmt
            //    Arrays.stream(s2).map(TreeItem::new).forEach(item2 -> item.getChildren().add(item2));
            }
        }
*/
        //Mi serve una funzione ricorsiva che esplori l'albero
        exploreTree(input,rootItem);
        TreeView<String> tree = new TreeView<> (rootItem);
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        return root;
    }


    public void exploreTree(String input, TreeItem<String> treeItem){

       switch (treeItem.getValue()){

           case "program":{

               //Può essere dichiarazione o stmt
               String txt="";
               if(input.startsWith("$")) {
                   txt="Dichiarazione";
               }

               if(input.startsWith("&")) {
                    txt="Statement";
               }

               TreeItem<String> item = new TreeItem<>(txt);
               treeItem.getChildren().add(item);
               input=input.substring(1);
               exploreTree(input,item);

           }
           
           case "Statement":{
                //Può essere simp o ;
               String txt="";
               if(input.startsWith(";")) {
                   txt=";";
               }else{
                   txt=input;
               }
               TreeItem<String> item = new TreeItem<>(txt);
               treeItem.getChildren().add(item);
               input=input.substring(1);
               exploreTree(input,item);



           }
           
           case "simp":{
                //ident asop binop


           }
           
           case "Dichiarazione":{
                //int ident


           }
           
           case "exp":{
                //exp + term


           }
           
           case "term":{
               //term * factor



           }
           
           case "factor":{
                //intconst o ident o exp


           }
           
        //Vedo chi è il padre per scrivere il nome del nodo

       }

/*
        if(input.contains(">")){
            //Creo nodo con la parte di input fino a >
            //La aggiungo al padre
            //Chiamo ricorsivamente exploreTree
        }else{
            //Creo foglia
            //La aggiungo al padre
        }
*/
    }

}