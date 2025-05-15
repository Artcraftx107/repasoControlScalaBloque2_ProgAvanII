package Ejercicio4

object Ejercicio4 extends App{
  def fibonacci(n: Int): Int = {
    if(n == 0){
      println(s"${Thread.currentThread().getName}: fib(0) = 0")
      0
    } else if(n == 1){
      println(s"${Thread.currentThread().getName}: fib(1) = 1")
      1
    } else {
      var res1 = 0
      var res2 = 0

      val t1 = new Thread(() => res1 = fibonacci(n-1))

      val t2 = new Thread(() => res2 = fibonacci(n-2))

      t1.start()
      t2.start()
      t1.join()
      t2.join()

      val fn = res1 + res2
      println(s"${Thread.currentThread().getName}: fib($n) = $fn")
      fn
    }
  }

  val n = 2
  println(s"main: fib(0) = 0")
  val res = fibonacci(n)
  println(s"main: fib($n) = $res")
  println("Fin del programa")
}