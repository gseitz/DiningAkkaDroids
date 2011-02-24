package akka.android

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.widget.TextView
import _root_.android.content.Context
import java.io._
import TypedResource._

case class Hakker(name: String, activity: MainActivity, left: TypedResource[android.widget.ImageView], right: TypedResource[android.widget.ImageView]) {
  import android.view.View.{INVISIBLE, VISIBLE}
  private def runOnUi(action: Runnable) = activity.runOnUiThread(action)

  implicit def funToRunnable(fun: () => Unit): Runnable = new Runnable {def run() = fun()}


  def showLeft = runOnUi(() => activity.findView(left).setVisibility(VISIBLE))
  def showRight = runOnUi(() => activity.findView(right).setVisibility(VISIBLE))
  def hideLeft = runOnUi(() => activity.findView(left).setVisibility(INVISIBLE))
  def hideRight = runOnUi(() => activity.findView(right).setVisibility(INVISIBLE))
  def showBoth = runOnUi(() => {
    activity.findView(right).setVisibility(VISIBLE)
    activity.findView(left).setVisibility(VISIBLE)
  })
  def hideBoth = runOnUi(() => {
    activity.findView(right).setVisibility(INVISIBLE)
    activity.findView(left).setVisibility(INVISIBLE)
  })
}

class MainActivity extends Activity {
  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.mainlayout)

    val akka_conf = """
    akka {
      enable-jmx = off
    }
    """
    val stream = openFileOutput("akka.conf", Context.MODE_PRIVATE)
    stream.write(akka_conf.getBytes)
    stream.close
    
    System.setProperty("akka.config", new File(getFilesDir, "akka.conf").getAbsolutePath)
    
    val imgHandles = List((TR.hakker1left, TR.hakker1right), (TR.hakker2left, TR.hakker2right), (TR.hakker5left, TR.hakker5right), (TR.hakker3left, TR.hakker3right), (TR.hakker4left, TR.hakker4right))
    val hakkers = for (
      (name, i) <- List("Jonas", "Viktor", "Debasish", "Martin", "Irmo").zipWithIndex
    ) yield Hakker(name, this, imgHandles(i)._1, imgHandles(i)._2)

    DiningHakkersOnFsm.run(hakkers)
  }
  
  override def onStop = {
    akka.actor.Actor.registry.shutdownAll
    super.onStop
  }
}