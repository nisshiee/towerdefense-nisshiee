import AssemblyKeys._

name := "towerdefense-nisshiee"

organization := "org.nisshiee"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
   "org.scalaz" %% "scalaz-core" % "7.0.0-RC2"
  ,"com.typesafe.akka" %% "akka-actor" % "2.1.2"
  ,"com.github.scala-incubator.io" %% "scala-io-core" % "0.4.2"
  ,"com.github.scala-incubator.io" %% "scala-io-file" % "0.4.2"
  ,"com.github.nscala-time" %% "nscala-time" % "0.4.0"
  ,"com.github.scopt" %% "scopt" % "2.1.0"
  ,"com.github.tototoshi" %% "scala-csv" % "0.7.0"
  ,"org.json4s" %% "json4s-jackson" % "3.2.4"
  ,"net.databinder.dispatch" %% "dispatch-core" % "0.10.0"
  //typesafe config includes in akka dependencies
  //,"com.typesafe" % "config" % "1.0.0"
  ,"org.specs2" %% "specs2" % "1.14" % "test"
  ,"org.typelevel" %% "scalaz-specs2" % "0.1.3" % "test"
  ,"org.mockito" % "mockito-all" % "1.9.5" % "test"
  ,"junit" % "junit" % "4.11" % "test"
  ,"org.pegdown" % "pegdown" % "1.2.1" % "test"
)

scalacOptions <++= scalaVersion.map { sv =>
  if (sv.startsWith("2.10")) {
    Seq(
      "-deprecation",
      "-language:dynamics",
      "-language:postfixOps",
      "-language:reflectiveCalls",
      "-language:implicitConversions",
      "-language:higherKinds",
      "-language:existentials",
      "-language:reflectiveCalls",
      "-language:experimental.macros",
      "-Xfatal-warnings"
    )
  } else {
    Seq("-deprecation")
  }
}

testOptions in (Test, test) += Tests.Argument("console", "html", "junitxml")

initialCommands := """
import scalaz._
import Scalaz._
import scalax.io._
import scalax.file._
import ImplicitConversions._
import com.github.nscala_time.time.Imports._
"""


// ========== for sonatype oss publish ==========

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/nisshiee/towerdefense-nisshiee</url>
  <licenses>
    <license>
      <name>The MIT License (MIT)</name>
      <url>http://opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:nisshiee/towerdefense-nisshiee.git</url>
    <connection>scm:git:git@github.com:nisshiee/towerdefense-nisshiee.git</connection>
  </scm>
  <developers>
    <developer>
      <id>nisshiee</id>
      <name>Hirokazu Nishioka</name>
      <url>http://nisshiee.github.com/</url>
    </developer>
  </developers>)


// ========== for scaladoc ==========

// scalacOptions in (Compile, doc) <++= (baseDirectory in LocalProject("core")).map {

scalacOptions in (Compile, doc) <++= baseDirectory.map {
  bd => Seq("-sourcepath", bd.getAbsolutePath,
            "-doc-source-url", "https://github.com/nisshiee/towerdefense-nisshiee/blob/master€{FILE_PATH}.scala")
}


// ========== for sbt-assembly ==========

seq(assemblySettings: _*)

jarName in assembly <<= (name, version) map { (name, version) => name + "-" + version + ".jar" }

// test in assembly := {}

mainClass in assembly := Some("App")

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case "application.conf" => MergeStrategy.concat
    case x => old(x)
  }
}
