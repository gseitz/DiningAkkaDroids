import sbt._

trait Defaults {
  def androidPlatformName = "android-8"
}

class AkkaDroid(info: ProjectInfo) extends AndroidProject(info) 
    with Defaults with MarketPublish with TypedResources with AkkaProject{

  override def adbPath = androidSdkPath / "bin" / adbName
  override def shouldCheckOutputDirectories = false 

  override def proguardOption = """-keepclassmembers class * {
      ** MODULE$;
  }
  
  -keep class scala.Option
  -keep class scala.Function1
  -keep class scala.PartialFunction
  -keep class akka.**
  -keep class com.eaio.**
  -keepclassmembers class com.eaio.**
  -keepclassmembers class akka.**
  -keep class org.omg.**
  -keep class scala.Tuple2
  -dontskipnonpubliclibraryclassmembers
  -dontskipnonpubliclibraryclasses
  """
  //-dontwarn **$$anonfun$*
  //

  val keyalias  = "change-me"
}