import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val android = "org.scala-tools.sbt" % "sbt-android-plugin" % "0.5.0"
  
  // akka
  val akka_repo = "akka_repo" at "http://akka.io/repository"
  val akka = "se.scalablesolutions.akka" % "akka-sbt-plugin" % "1.0"
}