package Ejercicio3

import scala.util.Random

object Ejericicio3 extends App {
  //Apartado a
  def parallel[A, B](a: => A, b: => B): (A, B) = {
    @volatile var resA: A = null.asInstanceOf[A]
    @volatile var resB: B = null.asInstanceOf[B]

    val t1 = new Thread{
      override def run(): Unit = {
        resA = a
      }}
    val t2 = new Thread{
      override def run(): Unit = {
        resB = b
      }}

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    (resA, resB)
  }

  val lista = List.fill(Random.nextInt(10))(Random.nextBoolean())
  println(s"Lista: $lista")

  def todosTrueIter(inic: Int, fin: Int, lista: List[Boolean]): Boolean = {
    var fard = true
    for (i <- inic until fin) {
      if (!lista(i)) {
        fard = false
      }
    }
    fard
  }

  private val mitad = lista.length / 2

  val (res1, res2) = parallel(
    todosTrueIter(0, mitad, lista),
    todosTrueIter(mitad, lista.length, lista)
  )

  println("All true?"+(res1&&res2))

  def todosTrueRec(inic: Int, fin: Int, lista: List[Boolean]): Boolean = {
    if (inic >= fin) {
      true
    } else if (!lista(inic)) {
      false
    } else {
      todosTrueRec(inic + 1, fin, lista)
    }
  }


}