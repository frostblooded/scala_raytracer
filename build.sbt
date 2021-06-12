name := "scala_raytracer"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "com.sksamuel.scrimage" % "scrimage-core" % "4.0.16"
libraryDependencies += "com.sksamuel.scrimage" %% "scrimage-scala" % "4.0.16"

libraryDependencies += "org.scalanlp" %% "breeze" % "1.1"
libraryDependencies += "org.scalanlp" %% "breeze-natives" % "1.1"
libraryDependencies += "org.scalanlp" %% "breeze-viz" % "1.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
