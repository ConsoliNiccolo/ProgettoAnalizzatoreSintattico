package main


import rules.Rules
import Translate.Traduce

object ParseProgram extends Rules{

  def main(args:Array[String]): Unit = {

    //Inserire il traduttore.

  }


  def parse(input :String): ParseProgram.ParseResult[Any] ={
    println("input:"+input)
    println(parseAll(program,input))
    return parseAll(program,input)
    //gui.printGUI(parseAll(program,args(0)).toString)

  }

}