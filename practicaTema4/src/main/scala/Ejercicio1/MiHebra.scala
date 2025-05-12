package Ejercicio1

//Ejercicio1A
class MiHebra(t: Int, c: Char) extends Thread{
  override def run(): Unit = {
    for(_<-1 to t) print(c)
  }
}

object E1a extends App{
  val hebra1 = new MiHebra(5, 'A')
  val hebra2 = new MiHebra(5, 'B')
  val hebra3 = new MiHebra(5, 'C')

  hebra1.start()
  hebra2.start()
  hebra3.start()
}
