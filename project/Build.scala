import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "entiendanet"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "commons-lang" %  "commons-lang" %  "2.5",
    "pdf" % "pdf_2.10" % "0.4.1"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here    
    resolvers += Resolver.url("My GitHub Play Repository", url("http://www.joergviola.de/releases/"))(Resolver.ivyStylePatterns)  
  )

}
