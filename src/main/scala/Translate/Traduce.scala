package Translate

import java.util.regex.Pattern
import scala.collection.mutable.ListBuffer

class Traduce() extends App {
  var check:Boolean=false
  var myBuffer = List[String]()
  var cleanBuff=List[String]()

  def Trasl(input: String): String = {
    cleanBuff= List(" ", ",", "(", "=","~",")")
    println(input)
    //Controllo generale
    var checking_ok=input.contains("parsed")
    checking_ok match{
      case true => {
        var str=input.slice(15,input.length)
        val res:String=startToConvert(str)
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
    myBuffer = List[String]()
    var some_split: Array[String]=input.split("Some")
    var risultato: String="!"
    for (x <- some_split) {
      if(x.contains("int")) {check=true}//Dichiarazione}
      else if(!x.contains("int")) {check=false}//Assegnazione}
      check match {
        case true => {
          val strings:Array[String]=x.split("~List")
          for(x <-strings) {
            //Controllo semantico
            var check_semant=onSearchingSemanticErrorsINT(x)
            if(check_semant!="OK") return "-------------------------------------------------------"+"\n"+
              "Errore: "+check_semant+"\n-------------------------------------------------------"
            if(x.contains("))~;)")) {
              //Vera stringa di dichiarazione
              val ind=x.indexOf(')')
              risultato=risultato+"$"+">"+"int"+"<"+">"+onLookingForID(ind,x,check)+"<>;<<"
            }
          }
        }
        //Assegnazione
        case false => {
          var check_semant_ass=onSearchingSemanticErrorsASS(x)
          if(check_semant_ass!="OK") return "-------------------------------------------------------"+"\n"+
            "Errore: "+check_semant_ass+"\n-------------------------------------------------------"
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
                var ns5=cleanString(x,cleanBuff)
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
                var ns5=cleanString(x,cleanBuff)
                risultato=risultato+"<>"+ns5}
            }
            risultato=risultato+"<<"
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

  def onSearchingSemanticErrorsINT(s:String):String={
    val pat=raw"(([a-z][,][ ]){1,}[a-zA-Z][)][)])|([(][a-zA-Z][)][)])".r
    val str=pat.findAllIn(s)
    for(x<-str) {
      val ns=x.replaceFirst("\\)","")
      if (!myBuffer.contains(ns) || myBuffer.isEmpty) {
        myBuffer = ns :: myBuffer
      }
      else {
        return "Molteplice dichiarazione delle stessa variabile ->"+cleanString(ns,cleanBuff);
      }
    }
    return "OK"
  }

  def onSearchingSemanticErrorsASS(str: String): String ={
    val pat2=raw"(([a-z][,][ ]){1,}[a-zA-Z][)][~][=])|([(][a-zA-Z][)][~][=])".r
    val str2=pat2.findAllIn(str)
    for(x<-str2) {
      var ns3=cleanString(x,cleanBuff.slice(3,5))
      if(!myBuffer.contains(ns3)) {
        return "Identificatore non dichiarato ->"+cleanString(ns3,cleanBuff)
      }

    }
    return "OK"
  }

  def cleanString(main_str:String,arr:List[String]):String= {
    if(arr.isEmpty) return main_str;
    else {
      var s=main_str.replace(arr.head,"")
      cleanString(s,arr.slice(1,arr.length))
    }
  }

}
