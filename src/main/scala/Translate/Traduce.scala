package Translate

import java.util.Scanner
import java.util.regex.Pattern

import scala.collection.mutable.ListBuffer

class Traduce extends App {

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
                                  risultato=risultato+">"+onLookingForID(ind,ex(1))+"<>;<<"
                                  val stri:Array[String]=ex(1).split("~=\\)~\\(")
                                  val ind2=stri(1).indexOf(')')
                                   ind2 match {
                                     case 7 => risultato=risultato+"&>>"+onLookingForID(ind,ex(1))+"<>=<>"+stri(1).charAt(0)+"<<"
                                     case 8 => risultato=risultato+"&>>"+onLookingForID(ind,ex(1))+"<>=<>"+stri(1).charAt(0)+stri(1).charAt(ind2-1)+"<<"
                                     case 11 => risultato=risultato+"&>>"+onLookingForID(ind,ex(1))+"<>=<>"+stri(1).charAt(0)+stri(1).charAt(7)+stri(1).charAt(ind2-1)+"<<"
                                     case 14 => risultato=risultato+"&>>"+onLookingForID(ind,ex(1))+"<>=<>"+stri(1).charAt(0)+stri(1).charAt(7)+stri(1).charAt(11)+stri(1).charAt(ind2-1)+"<<"
                                   }

                                  }
        if(x.contains("None~;")) {risultato=risultato+"&>;<<"}
      }

    return risultato
  }

  def onLookingForID(index: Int,str: String): String = {
    var res: String=""
    index match {
      case 1 => res=""+str.charAt(0)
      case 4 => res=""+str.charAt(0)+str.charAt(3)
      case 7 => res=""+str.charAt(0)+str.charAt(3)+str.charAt(6)
      case 10 =>res=""+str.charAt(0)+str.charAt(3)+str.charAt(6)+str.charAt(9)
      case 13 =>res=""+str.charAt(0)+str.charAt(3)+str.charAt(6)+str.charAt(9)+str.charAt(12)
      case 16 =>res=""+str.charAt(0)+str.charAt(3)+str.charAt(6)+str.charAt(9)+str.charAt(12)+str.charAt(15)
      case 19 =>res=""+str.charAt(0)+str.charAt(3)+str.charAt(6)+str.charAt(9)+str.charAt(12)+str.charAt(15)+str.charAt(18)
    }
    return res

  }
}

