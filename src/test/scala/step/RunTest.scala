package step

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features"),
  glue = Array("classpath:step"),
  tags = "@no-docker and not @docker",
  plugin = Array("pretty")
)
class RunTest

