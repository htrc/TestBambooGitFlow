object Main extends App {
  import buildinfo.BuildInfo._

  println(s"$name $version")
  println(s"SBT : $sbtVersion")
  println(s"Scala: $scalaVersion")
  println(s"SHA $gitSha on branch $gitBranch with version $gitVersion")
  println(s"Built on: $builtAtString")
}

