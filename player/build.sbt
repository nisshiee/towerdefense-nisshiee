import AssemblyKeys._

name := "towerdefense-nisshiee"

organization := "org.nisshiee"

version := "1.0.0"

scalaVersion := "2.10.1"

resolvers += "Towerdefense Repo" at "http://towerdefense-repo.herokuapp.com/"

libraryDependencies ++= Seq(
   "jp.ac.nagoya-u.itc.mase" % "tower-defense" % "1.3.0"
  ,"org.nisshiee" %% "towerdefense-scala" % "1.0.2"
  ,"org.specs2" %% "specs2" % "1.14" % "test"
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


// ========== for scaladoc ==========

// scalacOptions in (Compile, doc) <++= (baseDirectory in LocalProject("core")).map {

scalacOptions in (Compile, doc) <++= baseDirectory.map {
  bd => Seq("-sourcepath", bd.getAbsolutePath,
            "-doc-source-url", "https://github.com/nisshiee/towerdefense-nisshiee/blob/master/player€{FILE_PATH}.scala")
}


// ========== for sbt-assembly ==========

seq(assemblySettings: _*)

jarName in assembly <<= (name, version) map { (name, version) => name + "-" + version + ".jar" }

// test in assembly := {}

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case "application.conf" => MergeStrategy.concat
    case x => old(x)
  }
}

excludedJars in assembly <<= (fullClasspath in assembly) map { cps => 
  val System = """^tower-defense[0-9\._-]+\.jar$""".r
  cps filter {
    _.data.getName match {
      case System() => true
      case _ => false
    }
  }
}
