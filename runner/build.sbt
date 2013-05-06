name := "towerdefense-nisshiee-runner"

organization := "org.nisshiee"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
  // "org.scalaz" %% "scalaz-core" % "7.0.0-RC2"
  //,"com.typesafe.akka" %% "akka-actor" % "2.1.2"
  //,"com.github.scala-incubator.io" %% "scala-io-core" % "0.4.2"
  //,"com.github.scala-incubator.io" %% "scala-io-file" % "0.4.2"
  //,"com.github.nscala-time" %% "nscala-time" % "0.4.0"
  //,"com.github.scopt" %% "scopt" % "2.1.0"
  //,"com.github.tototoshi" %% "scala-csv" % "0.7.0"
  //,"org.json4s" %% "json4s-jackson" % "3.2.4"
  //,"net.databinder.dispatch" %% "dispatch-core" % "0.10.0"
  ////typesafe config includes in akka dependencies
  ////,"com.typesafe" % "config" % "1.0.0"
  //,"org.specs2" %% "specs2" % "1.14" % "test"
  //,"org.typelevel" %% "scalaz-specs2" % "0.1.3" % "test"
  //,"org.mockito" % "mockito-all" % "1.9.5" % "test"
  //,"junit" % "junit" % "4.11" % "test"
  //,"org.pegdown" % "pegdown" % "1.2.1" % "test"
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

