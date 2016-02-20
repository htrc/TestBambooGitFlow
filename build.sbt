showCurrentGitBranch

git.useGitDescribe := false

lazy val testBambooGitFlow = (project in file(".")).
  enablePlugins(GitVersioning, BuildInfoPlugin, JavaAppPackaging).
  settings(
    scalaVersion := "2.11.7",
    resolvers ++= Seq(
      "I3 Repository" at "http://nexus.htrc.illinois.edu/content/groups/public",
      Resolver.mavenLocal
    ),
    buildInfoOptions ++= Seq(BuildInfoOption.BuildTime),
    buildInfoKeys ++= Seq[BuildInfoKey](
      "gitSha" -> git.gitHeadCommit.value.getOrElse("N/A"),
      "gitBranch" -> git.gitCurrentBranch.value,
      "dirty" -> git.gitUncommittedChanges.value,
      "gitVersion" -> git.gitDescribedVersion.value.getOrElse("N/A")
    ),
    packageOptions in (Compile, packageBin) += Package.ManifestAttributes(
      ("Git-Version", git.gitDescribedVersion.value.getOrElse("N/A")),
      ("Git-Dirty", git.gitUncommittedChanges.value.toString),
      ("Git-Sha", git.gitHeadCommit.value.getOrElse("N/A")),
      ("Build-Date", new java.util.Date().toString),
      ("Git-Branch", git.gitCurrentBranch.value)
    )
  )
