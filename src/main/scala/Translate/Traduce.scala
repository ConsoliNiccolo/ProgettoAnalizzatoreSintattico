package Translate

import java.util.Scanner
import java.util.regex.Pattern

import scala.collection.mutable.ListBuffer

class Traduce extends App {
  //Funzione da cancellare dopo!
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

  //FUNZIONE PULITA TRANSLATE
  ////////////////////////

  def Trasl(input: String): String = {

    //Considerazioni : segnale_inizio(0) mi da la riga di errore. checking_ok ==true if success
    println(input)
    var checking_ok=input.contains("parsed")
    checking_ok match{
      case true => {
        println("Parsed done successfully!")
        var str=input.slice(15,input.length)
        val res:String=startToConvert(str)
        println(res)
        return res
      }
      case false => return "Parsed did not go well! Check it next time!"
    }

  }


  def startToConvert(input: String): String = {
    //Ho già checkato
    var some_split: Array[String]=input.split("Some")
    var risultato: String="!"
      for (x <- some_split) {
       if(x.contains("i, n, t")) {risultato=risultato+"$"+">"+"int"+"<";
                                  val ex:Array[String]=x.split("t, ")
                                  val ind=ex(1).indexOf(')')

                                  ind match {
                                    case 1 => risultato=risultato+">"+ex(1).charAt(0)+"<>;<<"
                                    case 4 => risultato=risultato+">"+ex(1).charAt(0)+ex(1).charAt(3)+"<>;<<"
                                    case 7 => risultato=risultato+">"+ex(1).charAt(0)+ex(1).charAt(3)+ex(1).charAt(6)+"<>;<<"
                                    case 10 =>risultato=risultato+">"+ex(1).charAt(0)+ex(1).charAt(3)+ex(1).charAt(6)+ex(1).charAt(9)+"<>;<<"
                                    case 13 =>risultato=risultato+">"+ex(1).charAt(0)+ex(1).charAt(3)+ex(1).charAt(6)+ex(1).charAt(9)+ex(1).charAt(12)+"<>;<<"
                                    case 16 =>risultato=risultato+">"+ex(1).charAt(0)+ex(1).charAt(3)+ex(1).charAt(6)+ex(1).charAt(9)+ex(1).charAt(12)+ex(1).charAt(15)+"<>;<<"
                                    case 19 =>risultato=risultato+">"+ex(1).charAt(0)+ex(1).charAt(3)+ex(1).charAt(6)+ex(1).charAt(9)+ex(1).charAt(12)+ex(1).charAt(15)+ex(1).charAt(18)+"<>;<<"

                                  }
                                  val stri:Array[String]=ex(1).split("~=\\)~\\(")
                                  val ind2=stri(1).indexOf(')')
                                   //println("THis is str1:"+stri(1)+ "This is index("+ ind2)
                                   ind2 match {
                                     case 7 => risultato=risultato+"&>>"+stri(1).charAt(0)+"<<"
                                     case 8 => risultato=risultato+"&>>"+stri(1).charAt(0)+stri(1).charAt(ind2-1)+"<<"
                                     case 11 => risultato=risultato+"&>>"+stri(1).charAt(0)+stri(1).charAt(7)+stri(1).charAt(ind2-1)+"<<"
                                     case 14 => risultato=risultato+"&>>"+stri(1).charAt(0)+stri(1).charAt(7)+stri(1).charAt(11)+stri(1).charAt(ind2-1)+"<<"
                                   }

                                  }
        if(x.contains("None~;")) {risultato=risultato+"&>;<<"}
      }

    return risultato
  }
}

