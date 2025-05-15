package Ejercicio5

import scala.util.Random
import Ejercicio3.Ejericicio3.parallel

object Ejercicio5 extends App{
  def mezclar(l1: List[Int], l2: List[Int]): List[Int] = {
    (l1, l2) match{
      case (Nil, _) => l2
      case (_, Nil) => l1
      case (x :: xs, y :: ys) =>
        if(x<y){
          x::mezclar(xs, l2)
        }else{
          y :: mezclar(l1, ys)
        }
    }
  }

  def ordenar(l: List[Int]): List[Int] = {
    if(l.length <= 1){
      l
    }else{
      val(izq, der) = l.splitAt(l.length/2)
      val(ordenarIzq, ordenarDer) = parallel(ordenar(izq), ordenar(der))
      mezclar(ordenarIzq, ordenarDer)
    }
  }

  val lista = List.fill(Random.nextInt(25)+5)(Random.nextInt(100))
  println(s"main: $lista")
  val ordenada = ordenar(lista)
  println(s"main: $ordenada")
}

