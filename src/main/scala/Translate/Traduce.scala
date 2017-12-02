package Translate

import java.util.regex.Pattern
import scala.collection.mutable.ListBuffer

class Traduce extends App {

  def Trasl(input: String): String = {
    println(input)
    var checking_ok=input.contains("parsed")
    checking_ok match{
      case true => {
        println("Parsed done successfully with trasl2!")
        var str=input.slice(15,input.length)
        val res:String=startToConvert(str)
        println(res)
        return res
      }
      case false => return "Parsed did not go well! Check it next time!"
    }

  }

  def startToConvert(input: String): String = {
    var some_split: Array[String]=input.split("Some")
    var risultato: String="!"
    for (x <- some_split) {
      if(x.contains("i, n, t")) {risultato=risultato+"$"+">"+"int"+"<";
        val ex:Array[String]=x.split("i, n, t, ")
        val ind=ex(1).indexOf(')')
        risultato=risultato+">"+onLookingForID(ind,ex(1))+"<>;<<"
        val stri:Array[String]=ex(1).split("~=\\)~\\(")
        val ind2=stri(1).indexOf(')')
        ind2 match {
          case 7 => risultato=risultato+"&>>"+onLookingForID(ind,ex(1))+"<>=<>"+stri(1).charAt(0)+"<<"
          case 8 => risultato=risultato+"&>>"+onLookingForID(ind,ex(1))+"<>=<>"+stri(1).charAt(0)+stri(1).charAt(ind2-1)+"<<"
          case x if(ind2>8) =>
          {risultato=risultato+"&>>"+onLookingForID(ind,ex(1))+"<>=<>"+onLookingForValue(ind2,stri(1))}
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
      case x if index > 1 => {
        var contatore=(index-1)/3
        res=res+str.charAt(0)+onLookingForID2(index,str,contatore)
      }
      case _ => return("No ID?")
    }
    return res

  }

  def onLookingForID2(index: Int,str: String,count: Int): String ={
    var res:String=""
    var conto: Int=count
    var help: Int=3
    while(conto>0){
      res=res+str.charAt(help)
      help=help+3
      conto=conto-1
    }
    return res
  }

  def onLookingForValue(index: Int,str:String): String ={
    var res: String=""+str.charAt(0)+str.charAt(7)
    var count: Int=index;
    while(count>8) {
      res=res+str.charAt(count-1)
      count=count-3
    }
    return res;
  }

}
