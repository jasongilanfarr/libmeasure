import sbt._
import Keys._

object CookbookBuild extends Build {
   val baseSettings = Defaults.defaultSettings ++ Seq(
      version := "0.1",
      scalaVersion := "2.9.2",
      libraryDependencies += Dependencies.scalatest) 
   
   val libcookbookSettings = baseSettings ++ Seq(
      libraryDependencies ++= Dependencies.libcookbook
   )


   lazy val libcookbook = Project(id = "libcookbook",
                                  base = file("libcookbook"),
                                  settings = libcookbookSettings)

   lazy val libmeasure = Project(id = "libmeasure",
                                 base = file("libmeasure"),
                                 settings = baseSettings)

}

object Dependencies {
	val uomo = "org.eclipse.uomo" % "org.eclipse.uomo" % "0.6.0-SNAPSHOT"
	val scalatest = "org.scalatest" %% "scalatest" % "2.0.M4" % "test"
	val libcookbook = Seq()
}
