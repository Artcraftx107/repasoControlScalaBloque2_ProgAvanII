package Ejercicio2

//Apartado a
package object utils {
  def periodico(t: Long)(b: => Unit): Thread = {
    val hebra = new Thread{
      override def run(): Unit = {
        while(true){
          b
          Thread.sleep(t)
        }
      }
    }
    hebra.start()
    hebra
  }
}

//Apartado b
import utils._
object E2b extends App{
  periodico(1000){
    println("Hebra 1: cada 1 segundo")
  }
  periodico(3000){
    println("Hebra 2: cada 3 segundos")
  }
}