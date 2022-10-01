name         := "sample-project"
organization := "com.infosupport"
scalaVersion := "2.12.15"

// Running multiple test-cases against spark isn't supported. We need to disable parallel test execution.

Test/parallelExecution := false
IntegrationTest/parallelExecution := false

libraryDependencies ++= Seq(
  "io.delta"             %% "delta-core"       % "2.1.0",
  "com.github.mrpowers"  %% "spark-daria"      % "1.2.3",
  "org.apache.spark"     %% "spark-core"       % "3.2.2"  % "provided",
  "org.apache.spark"     %% "spark-sql"        % "3.2.2"  % "provided",
  "com.github.scopt"     %% "scopt"            % "4.1.0",
  "org.scalatest"        %% "scalatest"        % "3.2.13" % "it,test",
  "org.jmockit"           % "jmockit"          % "1.49"   % "it,test",
  "org.scalacheck"       %% "scalacheck"       % "1.14.1" % "test",
  "org.scalamock"        %% "scalamock"        % "5.1.0"  % "test",
  "io.github.etspaceman" %% "scalacheck-faker" % "7.0.0"  % "test"
)

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings
  )
