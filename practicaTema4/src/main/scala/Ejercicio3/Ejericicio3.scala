package Ejercicio3

//apartado a
package object utils {
  def parallel[A, B](a: => A, b: => B): (A, B) = {
    var va: A = null.asInstanceOf[A]
    var vb: B = null.asInstanceOf[B]

    val hebraA = new Thread{
      override def run(): Unit = {
        va = a
      }
    }

    val hebraB = new Thread{
      override def run(): Unit = {
        vb = b
      }
    }

    hebraA.start(); hebraB.start()
    hebraA.join(); hebraB.join()

    (va, vb)
  }
}

import scala.util.Random
import utils._

object E3b extends App{
  val lista = List.fill(Random.nextInt(10))(Random.nextBoolean())

  def todosTrue(inic: Int, fin: Int): Boolean = {
    (inic until fin).forall(lista(_))
  }

  def todosTrueRec(inic: Int, fin: Int): Boolean = {
    if(inic >= fin) true
    else lista(inic) && todosTrueRec(inic+1, fin)
  }

  val mitad = lista.length / 2
  val(r1, r2) = parallel(todosTrue(0, mitad), todosTrue(mitad, lista.length))

  println(s"Lista: $lista")
  println(s"Todas True? ${r1 && r2}")
}