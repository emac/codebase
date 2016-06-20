package cn.emac

import sbt._
import Keys._

object XParent extends AutoPlugin {

  override def trigger = allRequirements

  lazy val xtask = taskKey[String]("xtask")
  lazy val xparam = settingKey[String]("xparam")

  override lazy val projectSettings = Seq(
    organization := "cn.emac",
    scalaVersion := "2.11.6",
    scalacOptions ++= Seq("-feature", "-language:postfixOps"),
    resolvers := {Resolver.url("typesafe-ivy-releasez", new URL("http://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns) +: resolvers.value},
    resolvers := {{"oschina-public" at "http://maven.oschina.net/content/groups/public/"} +: resolvers.value},
    resolvers ++= Seq("releases", "snapshots").map(Resolver.sonatypeRepo),
    xtask := {
      XTask(xparam.value)
    },
    xparam := "foo"
  )

  object XTask {
    def apply(param: String): String = {
      println("=======================")
      println(param)
      println("=======================")
      param
    }
  }
}