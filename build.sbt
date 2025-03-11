val ScalaJackson = "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.18.3"

ThisBuild / scalaVersion := "3.6.4"
ThisBuild / crossPaths   := false

ThisBuild / scalacOptions ++= Seq(
   "-deprecation",    // Emit warning and location for usages of deprecated APIs.
   "-encoding:utf-8", // Specify character encoding used by source files.
   "-feature",        // Emit warning for usages of features that should be imported explicitly.
   "-unchecked",      // Enable detailed unchecked (erasure) warnings.
   "-Wunused:linted", // Check unused imports and variables.
)

lazy val demo = (project in file(".")).settings(
   libraryDependencies += ScalaJackson
)
