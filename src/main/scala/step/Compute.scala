package step

import java.io.{BufferedWriter, File, FileWriter}
import scala.io.Source

object Compute {

  def write(file: String, value: String): Unit = {
    val bw = new BufferedWriter(new FileWriter(new File(file)))
    bw.write(value)
    bw.close()
  }

  def exe(input: String, output: String): Int = {
    val total = Source.fromFile(input).getLines.mkString.split(" ").map(_.toInt).sum
    write(output, total.toString)
    total % 2
  }
}