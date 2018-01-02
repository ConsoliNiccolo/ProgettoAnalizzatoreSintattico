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

            //Inserire funzione che elimina i commenti

            String result = (ParseProgram.parse(textArea.getText())).toString();

            //Metodo che disegna l'albero
            StackPane layout5=drawTree(result);

            layout5.getChildren().add(button2);
            Scene scene3 = new Scene(layout5,350,500);
            window.setScene(scene3);
        });

        button2.setOnAction((ActionEvent event) -> {
            window.setScene(scene);
        });
    }


    public StackPane drawTree(String input){
        String m=input.substring(1);
        System.out.println("\\\\\\\\\\ ");
        System.out.println("m "+m);
        System.out.println(input);
        TreeItem<String> rootItem = new TreeItem<> ("Program");
        rootItem.setExpanded(true);
        //Funzione ricorsiva che esplora l'albero
         exploreTree(m,rootItem,true);
        TreeView<String> tree = new TreeView<> (rootItem);
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        return root;
    }


    public void exploreTree(String input, TreeItem<String> treeItem, boolean isRoot){

        switch (treeItem.getValue()){

            case "Program":{
                //Può essere dichiarazione o stmt
                String txt="£";
                if(input.startsWith("$")) {
                    txt="Dichiarazione";
                }
                if(input.startsWith("&")) {
                    txt="Statement";
                }
                if(txt.equals("£")){
                    System.out.println("Sono in program e ho fatto " + input);
                    if(input.length()>0) {
                        input = input.substring(1);
                       // exploreTree(input, treeItem, false);
                    }
                }else {
                    TreeItem<String> item = new TreeItem<>(txt);
                    treeItem.getChildren().add(item);
                    input = input.substring(1);
                    System.out.println("Sono in program e ho fatto " + input);
                    System.out.println(input + " " + item.getValue());
                    exploreTree(input, item, false);
                }
                break;
            }

            case "Statement":{
                //Può essere simp o ;
                String txt="Simp";

                    TreeItem<String> item = new TreeItem<>(txt);
                    treeItem.getChildren().add(item);

                    String pippo[] = input.split(">");
                    for (String aPippo : pippo) {
                        System.out.println("Pippo " + aPippo);
                    }
                    exploreTree(input, item, false);
                    TreeItem<String> itemq = new TreeItem<>(";");
                    treeItem.getChildren().add(itemq);

                break;
            }

            case "Simp":{
                //ident asop binop
                TreeItem<String> item = new TreeItem<>("Ident");
                treeItem.getChildren().add(item);
                            String txt=input.substring(1,input.indexOf("<"));
                            TreeItem<String> id = new TreeItem<>(txt);
                            item.getChildren().add(id);

                TreeItem<String> item2 = new TreeItem<>("Asop");
                treeItem.getChildren().add(item2);
                    TreeItem<String> itemChild = new TreeItem<>("=");
                    item2.getChildren().add(itemChild);
                TreeItem<String> item3 = new TreeItem<>("Exp");
                treeItem.getChildren().add(item3);
                exploreTree(input,item3,false);

                break;
            }

            case "Dichiarazione":{
                //int ident ;

                //Fare la gerarchia?


                String txt=input.substring(input.indexOf(">") + 6, input.indexOf(";"));
                txt=txt.substring(0, txt.indexOf("<"));
                txt="int "+txt+" ;";
                TreeItem<String> item = new TreeItem<>(txt);
                treeItem.getChildren().add(item);
                int startIndex=input.indexOf(txt);
                input= input.substring(startIndex+txt.length()+1);
                System.out.println(txt+" "+input);

               /*   INT A;         QUESTO CODICE ERA PER INT A=0;
               String input2= input.substring(startIndex+txt.length()+1);
               System.out.println(txt+" "+input2);

               String txt2=input2.substring(input2.indexOf(">") + 1, input2.indexOf("<"));
               TreeItem<String> item2 = new TreeItem<>(txt2);
               treeItem.getChildren().add(item2);
               int startIndex2=input2.indexOf(txt2);
               String input3= input2.substring(startIndex2+txt2.length()+1);

               String txt3=input3.substring(input3.indexOf(">") + 1, input3.indexOf("<"));
               TreeItem<String> item3 = new TreeItem<>(txt3);
               treeItem.getChildren().add(item3);
               int startIndex3=input3.indexOf(txt3);
               input= input3.substring(startIndex3+txt3.length()+2);
               */
                System.out.println("Sono in dichiarazione e ho fatto " + input);
                break;
            }

            case "Exp":{
                //exp + term
                String txt;
                if(input.contains("&")) {
                     txt = input.substring(input.indexOf("="), input.indexOf("&"));

                }else {
                    txt=input.substring(input.indexOf("="), input.length());

                }
                //Pulire TXT


                TreeItem<String> item = new TreeItem<>(txt);
                treeItem.getChildren().add(item);
                break;
            }

            case "term":{
                //term * factor
                break;
            }

            case "factor":{
                //intconst o ident o exp

                break;
            }

            //Vedo chi è il padre per scrivere il nome del nodo

        }
        if (input.length()>0&&isRoot)
            exploreTree(input, treeItem,true);
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