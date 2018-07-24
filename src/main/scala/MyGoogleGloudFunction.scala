package my.project.domain

import scala.scalajs.js
import scala.scalajs.js.annotation._

object MyGoogleGloudFunction {

  @JSExportTopLevel("myGoogleGcloudFunction")
  def function(req: js.Dynamic, res: js.Dynamic) = {
    res.send("ohi")
  }

}
