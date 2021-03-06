name := """stockAuction"""

version := "1.0-SNAPSHOT"

//lazy val root = (project in file(".")).enablePlugins(PlayJava)
//
//scalaVersion := "2.11.7"
//
//libraryDependencies ++= Seq(
//  javaJdbc,
//  cache,
//  javaWs
//)

lazy val `root` = (project in file(".")).enablePlugins(PlayJava, PlayEbean)


scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc, jdbc , cache , ws, specs2 % Test, javaWs, "mysql" % "mysql-connector-java" % "5.1.27", "org.avaje" % "ebean" % "2.7.3", "com.adrianhurt" %% "play-bootstrap" % "1.1.1-P25-B4-SNAPSHOT",
  "org.webjars" % "font-awesome" % "4.7.0",
  "org.webjars" % "bootstrap-datepicker" % "1.4.0",
  "com.typesafe.play" %% "play-mailer" % "5.0.0-M1",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.jason-goodwin" %% "authentikat-jwt" % "0.3.5",
  "io.jsonwebtoken" % "jjwt" % "0.6.0",
  "com.restfb" % "restfb" % "1.6.12")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/"
resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"


routesGenerator := InjectedRoutesGenerator


