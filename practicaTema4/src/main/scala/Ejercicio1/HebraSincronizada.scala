package Ejercicio1

//Ejercicio1B
class HebraSincronizada(t: Int, c: Char, miId: Int, turno: scala.collection.mutable.ArrayBuffer[Int]) extends Thread{
  override def run(): Unit = {
    for(_<- 1 to t){
      while(turno(0) != miId) Thread.sleep(0)
      print(c)
      turno(0)=(turno(0)%3)+1 //pasa turno
    }
  }
}

object E1b extends App{
  val t = 3
  val turno = scala.collection.mutable.ArrayBuffer(1)

  val hebra1 = new HebraSincronizada(t, 'A', 1, turno)
  val hebra2 = new HebraSincronizada(2*t, 'B', 2, turno)
  val hebra3 = new HebraSincronizada(3*t, 'C', 3, turno)
  
  hebra1.start()
  hebra2.start()
  hebra3.start()
}
