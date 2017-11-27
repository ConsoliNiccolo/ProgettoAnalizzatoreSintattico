package Translate

import java.util.Scanner
import java.util.regex.Pattern

import scala.collection.mutable.ListBuffer

class Traduce extends App {
  //Funzione refactorizzata
  def onTranslate2(input: String): String = {
    println(input)
    var stringa_ritorno=""
    var ident_position=0;
    val i: Int=0;
    if(input.contains("parsed")) {
     // println("Programma successed")
      stringa_ritorno=stringa_ritorno+"!" //Carattere inizio programma
      val splitted_string=input.split("Some");
      stringa_ritorno=stringa_ritorno+"$"
      //Some will split into <Dichiaration> <Stmt>
      for(stringa_singola <-splitted_string) {

        var splitted_string2=stringa_singola.split("~")
        //1)Parte con ident
        // 2)Parte con valore

        for(x <-splitted_string2) {
         // println("Ecco tutte le x" +x)
          // int + id
          if(x.contains("i, n, t")){
            //println("sono in un INT ciclo")
            stringa_ritorno=stringa_ritorno+">int<"
            val stringa_identificativo=x.split("t, ")
            for(y<-stringa_identificativo) {
            if (y.contains(')'))
              stringa_ritorno=stringa_ritorno+">"+y+"<;";
            }
          }else {
            if(Pattern.matches("[(0-9]+", x) == true) {
              //println("NUMERO IDENTIFICATIVO" + x)
              stringa_ritorno=stringa_ritorno+"<<&"+x+")"+"<"
            }

            //<SIMP>
          }
        }

      }

    return stringa_ritorno
    } else return "Error,something didn't go well"

  }
}

