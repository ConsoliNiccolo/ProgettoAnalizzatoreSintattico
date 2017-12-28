package rules

import scala.util.parsing.combinator.JavaTokenParsers

class Rules extends JavaTokenParsers{

  def program: Parser[Any]="{ "~rep(dichiarazione)~rep(stmt)~"}"

  def stmt: Parser[Any]= opt(simp)~";"

  def simp: Parser[Any]= identificatore~asop~exp

  def dichiarazione: Parser[Any]="int "~identificatore~";"

  //def exp: Parser[Any]="("~exp~")" | /*exp~binop~exp |*/ intconst /*|   exp~binop~exp*/ | identificatore | exp~binop~exp //causa uno stack overflow

  lazy val exp: Parser[Any] =
    term ~ rep("[+-]".r ~ term)

  lazy val term: Parser[Any] =
    factor ~ rep("[*/]".r ~ factor)

  lazy val factor: Parser[Any] =
    "(" ~> exp <~ ")" | floatingPointNumber | value~binop~value | identificatore

  def value: Parser[Any]=identificatore | factor

  def identificatore: Parser[Any]=rep("[A-Za-z ]".r)

  def intconst: Parser[Any]="[0-9]".r~rep("[0-9]".r)

  def asop: Parser[Any]= "="

  def binop: Parser[Any]= "+" | "*"
}