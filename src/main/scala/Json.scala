import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.scala.{ ClassTagExtensions, DefaultScalaModule, JavaTypeable }

import scala.io.Codec

object Json:
   given Codec = Codec.UTF8

   private val mapper =
      JsonMapper.builder().addModule(DefaultScalaModule).build() :: ClassTagExtensions

   def write(obj: Any): String = mapper.writeValueAsString(obj)

   def parse[A: JavaTypeable](str: String): A = mapper.readValue[A](str)
end Json
