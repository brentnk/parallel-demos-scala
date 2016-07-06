import scala.math._
import scala.collection.immutable._

object application {
  def main (args: Array[String]) {
    println("Hello")
    time { primes_sequential() }
    time { primes_parallel() }
  }

  def check_prime(num: Int): Boolean ={
    if (num % 2 == 0) {
      return false
    }
    val calc = math.sqrt(num.toDouble).toInt
    for (i <- (3 to calc by 2)) {
      if(num % i == 0) {
        return false
      }
    }
    true
  }

  def primes_parallel(): Unit = {
    val number_range = 100000000 to 101000000 by 1
    val primes = number_range.par.filter {
      check_prime
    }
    printf("Par: %d %s\n", primes.length, primes.take(10).mkString(" ") )
  }

  def primes_sequential(): Unit = {
    val number_range = 100000000 to 101000000 by 1
    val primes = number_range.filter {
      check_prime
    }

    printf("Seq: %d %s\n", primes.length, primes.take(10).mkString(" ") )
  }

  def time[R](block: => R): R = {
    val t0 = System.nanoTime().toDouble
    val result = block    // call-by-name
    val t1 = System.nanoTime().toDouble
    printf("Elapsed time: %.3f ms\n", (t1 - t0) / math.pow(10,6))
    result
  }

}
