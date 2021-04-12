package step

import io.cucumber.scala.{EN, ScalaDsl}

import scala.io.Source

class Steps extends ScalaDsl with EN {

  var input: String = _
  var output: String = _
  var exitCode: Int = _

  ParameterType("operation", "less than|equal to|greater than") { (mode: String) =>
    mode match {
      case "equal to" => (t: (Int, Int)) => t._1 == t._2
      case "less than" => (t: (Int, Int)) => t._1 < t._2
      case "greater than" => (t: (Int, Int)) => t._1 > t._2
    }
  }

  ParameterType("should", "should be|should not be") { (mode: String) =>
    mode match {
      case "should be" => (x: Boolean) => x
      case "should not be" => (x: Boolean) => !x
    }
  }

  Given("""the input path {string}""") {
    (path: String) => input = path
  }

  Given("""the output path {string}""") {
    (path: String) => output = path
  }

  When("""^execute the program$""") {
    exitCode = Compute.exe(input, output)
  }

  Then("""the exitcode should be {int}""") {
    (exitCode: Int) => assert(exitCode == 0)
  }

  Then("""the excepted output {should} {operation} {int}""") {
    (should: Boolean => Boolean, operation: ((Int, Int)) => Boolean, value: Int) => {
      val expected = Source.fromFile(output).getLines.mkString.toInt
      val f = operation andThen should

      assert(f(expected, value))
    }
  }
}
