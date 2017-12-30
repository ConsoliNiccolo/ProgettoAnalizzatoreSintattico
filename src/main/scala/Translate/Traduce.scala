package Translate

import java.util.regex.Pattern
import scala.collection.mutable.ListBuffer

class Traduce extends App {
  var check:Boolean=false;

  def Trasl(input: String): String = {
    println(input)
    var checking_ok=input.contains("parsed")
    checking_ok match{
      case true => {
        println("Parsed eseguito correttemente,operazione di traduzione in corso:")
        var str=input.slice(15,input.length)
        val res:String=startToConvert(str)
        println(res)
        return res
      }
      case _ => {
        val patRowCol=raw"([0-9]\.[0-9]{1,})".r
        var found=input.slice(14,35)
        val all=patRowCol.findAllIn(input)
        var errore=""
        for(x<-all) {
          errore=errore+x
        }
        return "-------------------------------------------------------"+"\n"+
               "Riga/Posizione errore: "+errore+"\nTipologia errore: "+found+
               " found"+"\n-------------------------------------------------------"
      }
    }

  }

  def startToConvert(input: String): String = {
    var some_split: Array[String]=input.split("Some")
    var risultato: String="!"
    for (x <- some_split) {
        if(x.contains("int")) {check=true}//Dichiarazione}
        else if(!x.contains("int")) {check=false}//Assegnazione}
            check match {
                  case true => {
                      val strings:Array[String]=x.split("~List")
                      for(x <-strings) {
                      if(x.contains("))~;)")) {
                        //Vera stringa di dichiarazione
                        val ind=x.indexOf(')')
                        risultato=risultato+"$"+">"+"int"+"<"+">"+onLookingForID(ind,x,check)+"<>;<<"
                      }
                    }
                  }
                  //Assegnazione
                  case false => {
                        val ind=x.indexOf(')')
                        //Identificatore Memorizzato.
                        risultato=risultato+"&"+">"+onLookingForID(ind,x,check)+"<>="
                        //Ricerca valori
                        val stri:Array[String]=x.split("~=\\)~\\(")
                          if(stri(1).contains("+") || stri(1).contains("*")){
                              val st:Array[String]=stri(1).split("List\\(\\)")
                              for(x<-st) {
                                //Matcha sia numeri che 1ID che multiID
                                val mypat=raw"([0-9]+)|(([a-z][,][ ]){1,}[a-zA-Z][)])|([(][a-zA-Z][)])|([+])|([*])".r
                                val all=mypat.findAllIn(x)
                                for(x <- all) {
                                  //Pulizia e risultato=risultato..
                                    val ns=x.replace(",","")
                                    val ns2=ns
                                    val ns3=ns2.replace(" ","")
                                    val ns4=ns3.replace(")","")
                                    val ns5=ns4.replace("(","")
                                    risultato=risultato+"<>"+ns5}
                              }
                            risultato=risultato+"<<"
                          }
                          else {
                            //Assegnazione singola.
                            val st:Array[String]=stri(1).split("List\\(\\)")
                            for(x<-st){
                              val mypat=raw"([0-9]+)|(([a-z][,][ ]){1,}[a-zA-Z][)])|([(][a-zA-Z][)])".r
                              val all=mypat.findAllIn(x)
                              for(x<-all){
                              //Pulizia e risultato=risultato..
                              val ns=x.replace(",","")
                              val ns2=ns
                              val ns3=ns2.replace(" ","")
                              val ns4=ns3.replace(")","")
                              val ns5=ns4.replace("(","")
                              risultato=risultato+"<>"+ns5}
                            }

                          }


                  }
            }

        if(x.contains("None~;")) {risultato=risultato+"&>;<<"}
    }

    return risultato
  }

  def onLookingForID(index: Int,str: String,b: Boolean): String = {
    var res: String=""
    var minimum_count: Int=0;
    if(b) {minimum_count=1}
    else {minimum_count=8}
    while(minimum_count<=index) {
      res=res+str.charAt(minimum_count)
      minimum_count=minimum_count+3;
    }
    return res;

  }

  def onLookingForValue(index: Int,str:String): String ={
    var res: String=""
    var count: Int=1;
    while(count!=index) {
      res=res+str.charAt(count)
      count=count+1
    }
    return res;
  }

}

