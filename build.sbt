name := "NeuralNetworksPlayground"

organization := "pl.marpiec"

version := "0.0.1"

scalaVersion := "2.12.4"

scalacOptions ++= Seq(
  "-feature")

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

publishLocal := {}

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  "commons-io" % "commons-io" % "2.5")