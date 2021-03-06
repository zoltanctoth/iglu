/*
 * Copyright (c) 2012-2016 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0, and
 * you may not use this file except in compliance with the Apache License
 * Version 2.0.  You may obtain a copy of the Apache License Version 2.0 at
 * http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Apache License Version 2.0 is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the Apache License Version 2.0 for the specific language
 * governing permissions and limitations there under.
 */
import sbt._
import Keys._
import com.typesafe.sbt.packager.archetypes._

object IgluctlBuild extends Build {

  import Dependencies._
  import BuildSettings._

  // Configure prompt to show current project.
  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  // Define our project, with basic project information and library
  // dependencies.
  lazy val project = Project("igluctl", file("."))
    .settings(buildSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        // Java
        Libraries.jsonValidator,
        // Scala
        Libraries.igluCoreJson4s,
        Libraries.schemaddl,
        Libraries.scopt,
        Libraries.scalaz7,
        Libraries.json4sJackson,
        Libraries.scalajHttp,
        Libraries.awscala,
        // Scala (test only)
        Libraries.specs2,
        Libraries.scalazSpecs2,
        Libraries.scalaCheck
      )
    )
    .enablePlugins(JavaAppPackaging)
}
