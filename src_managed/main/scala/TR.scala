package akka.android
import android.app.Activity
import android.view.View

case class TypedResource[T](id: Int)
object TR {
  val hakker5left = TypedResource[android.widget.ImageView](R.id.hakker5left)
  val hakker2 = TypedResource[android.widget.ImageView](R.id.hakker2)
  val hakker5 = TypedResource[android.widget.ImageView](R.id.hakker5)
  val top = TypedResource[android.widget.LinearLayout](R.id.top)
  val hakker3left = TypedResource[android.widget.ImageView](R.id.hakker3left)
  val bottomright = TypedResource[android.widget.LinearLayout](R.id.bottomright)
  val hakker3 = TypedResource[android.widget.ImageView](R.id.hakker3)
  val middle = TypedResource[android.widget.LinearLayout](R.id.middle)
  val bottomleft = TypedResource[android.widget.LinearLayout](R.id.bottomleft)
  val midright = TypedResource[android.widget.LinearLayout](R.id.midright)
  val hakker4 = TypedResource[android.widget.ImageView](R.id.hakker4)
  val widget103 = TypedResource[android.widget.LinearLayout](R.id.widget103)
  val hakker1 = TypedResource[android.widget.ImageView](R.id.hakker1)
  val rel01 = TypedResource[android.widget.RelativeLayout](R.id.rel01)
  val hakker2right = TypedResource[android.widget.ImageView](R.id.hakker2right)
  val hakker1right = TypedResource[android.widget.ImageView](R.id.hakker1right)
  val hakker4left = TypedResource[android.widget.ImageView](R.id.hakker4left)
  val rel05 = TypedResource[android.widget.RelativeLayout](R.id.rel05)
  val rel02 = TypedResource[android.widget.RelativeLayout](R.id.rel02)
  val midleft = TypedResource[android.widget.LinearLayout](R.id.midleft)
  val hakker3right = TypedResource[android.widget.ImageView](R.id.hakker3right)
  val rel03 = TypedResource[android.widget.RelativeLayout](R.id.rel03)
  val hakker2left = TypedResource[android.widget.ImageView](R.id.hakker2left)
  val bottom = TypedResource[android.widget.LinearLayout](R.id.bottom)
  val hakker5right = TypedResource[android.widget.ImageView](R.id.hakker5right)
  val hakker1left = TypedResource[android.widget.ImageView](R.id.hakker1left)
  val hakker4right = TypedResource[android.widget.ImageView](R.id.hakker4right)
  val rel04 = TypedResource[android.widget.RelativeLayout](R.id.rel04)
}
trait TypedViewHolder {
  def view: View
  def findView[T](tr: TypedResource[T]) = view.findViewById(tr.id).asInstanceOf[T]  
}
trait TypedView extends View { def view = this }
trait TypedActivityHolder {
  def activity: Activity
  def findView[T](tr: TypedResource[T]) = activity.findViewById(tr.id).asInstanceOf[T]
}
trait TypedActivity extends Activity with TypedActivityHolder { def activity = this }
object TypedResource {
  implicit def view2typed(v: View) = new TypedViewHolder { def view = v }
  implicit def activity2typed(act: Activity) = new TypedActivityHolder { def activity = act }
}
