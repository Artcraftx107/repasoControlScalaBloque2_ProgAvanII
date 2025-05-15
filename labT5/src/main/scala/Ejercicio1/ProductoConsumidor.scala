package Ejercicio1

object ProductoConsumidor extends App{
  val TAM = 20 //Tamaño buffer
  val TOTAL = 200 //Enteros a producir

  class BufferCircular(val size: Int) {
    private val elem = new Array[Int](size) //Array de elementos
    private var posConsume = 0 //Posicion del consumidor
    private var posProduccion = 0 //Posicion de la produccion
    private var nelem = 0 //Numero de elementos

    def producir(x: Int): Unit = synchronized {
      while(nelem==size){
        wait() //Esperar a que array se vacie ya que esta lleno
      }

      elem(posProduccion) = x
      posProduccion = (posProduccion+1)%size
      nelem+=1
      notifyAll() //Aviso al consumidor
    }

    def consumir(): Int = synchronized{
      while(nelem==0){
        wait() //Si array vacio, espera
      }
      val x = elem(posConsume)
      posConsume=(posConsume+1)%size
      nelem-=1
      notifyAll() //Avisar al productor
      x
    }
  }

  val bufferCircular = new BufferCircular(TAM)

  //Productor
  val productor = new Thread(new Runnable{
    def run(): Unit = {
      for(i<-0 until TOTAL){
        bufferCircular.producir(i)
        println(s"Se ha producido $i")
      }
    }
  })

  //Consumidor
  val consumidor = new Thread(new Runnable {
    def run(): Unit = {
      for(_<-0 until TOTAL){
        val x = bufferCircular.consumir()
        println(s"Se ha consumido $x")
      }
    }
  })

  productor.start()
  consumidor.start()
}
