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
  "org.mindrot" % "jbcrypt" % "0.3m")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/"


routesGenerator := InjectedRoutesGenerator


