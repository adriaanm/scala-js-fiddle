import scala.scalajs.sbtplugin.ScalaJSPlugin._
import ScalaJSKeys._
import com.typesafe.sbt.SbtStartScript

import com.lihaoyi.workbench.Plugin._

scalaJSSettings

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.scala-lang.modules.scalajs" %% "scalajs-dom" % "0.3-SNAPSHOT",
  "com.scalatags" % "scalatags_2.10" % "0.2.3-JS",
  "com.scalarx" % "scalarx_2.10" % "0.2.2-JS",
  "org.scala-lang.modules.scalajs" %% "scalajs-jquery" % "0.1-SNAPSHOT",
  "org.scala-lang.modules" %% "scala-async" % "0.9.0-M4"
)

// Specify additional .js file to be passed to package-js and optimize-js
unmanagedSources in (Compile, ScalaJSKeys.packageJS) +=
  baseDirectory.value / "js" / "startup.js"

(SbtStartScript.stage in Compile) := (packageJS in Compile).value

workbenchSettings

bootSnippet := "ScalaJS.modules.fiddle_Client().main__AT__V();"

updateBrowsers <<= updateBrowsers.triggeredBy(packageJS in Compile)
