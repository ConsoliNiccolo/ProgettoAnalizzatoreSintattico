package main


import rules.Rules
import Translate.Traduce

object ParseProgram extends Rules{

  def main(args:Array[String]): Unit = {
  }


  def parse(input :String): ParseProgram.ParseResult[Any] ={
    var translate=new Traduce()
    var stringa=translate.onTranslate2(parseAll(program,input).toString)
    println(stringa)
    return parseAll(program,input)
    //gui.printGUI(parseAll(program,args(0)).toString)


  }

}