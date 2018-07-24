organization := "my.project.domain"
name := "my-google-gcloud-function"
scalaVersion := "2.12.1"

enablePlugins(ScalaJSPlugin)
scalaJSModuleKind := ModuleKind.CommonJSModule

InputKey[Unit]("gcDeploy") := {
  val args = sbt.complete.DefaultParsers.spaceDelimited("gcDeploy <project-id> [<pubsub-topic>]").parsed

  val projectId = args.head
  val trigger = args.tail match {
    case Nil => "--trigger-http"
    case pubSubTopic :: Nil => s"--trigger-topic $pubSubTopic"
  }

  val gcTarget = target.value / "gcloud"
  val function = gcTarget / "function.js"
  val functionName = "myGoogleGcloudFunction"
  sbt.IO.copyFile((fastOptJS in Compile).value.data, function)
  val command = s"gcloud beta functions deploy $functionName --source ${gcTarget.getAbsolutePath} --stage-bucket debtest3 $trigger --project $projectId --region us-central1"
  println(command)
  command!
}
