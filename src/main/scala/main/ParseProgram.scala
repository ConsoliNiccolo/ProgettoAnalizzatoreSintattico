package main


import rules._
import Translate.Traduce

object ParseProgram extends Rules {

  def main(args:Array[String]): Unit = {
  }

    def parse(input :String): String ={
      println("input:"+input)
      var translate=new Traduce()
      // var stringa=translate.onTranslate2(parseAll(program,input).toString)
      // println(stringa)
      var res=translate.Trasl(parseAll(program,input).toString)
      res=parseAll(program,input).toString
      return res
      //gui.printGUI(parseAll(program,args(0)).toString)
     // println(parseAll(program,input))
     // return parseAll(program,input)
     //gui.printGUI(parseAll(program,args(0)).toString)

  }

}