package com.tulskiy.sleekgit.commands

import java.io.File
import org.eclipse.jgit.lib.{RepositoryBuilder, Repository}
import org.eclipse.jgit.api.{Git, InitCommand}

/**
 * Author: Denis Tulskiy
 * Date: 10/28/11
 */

object RepositoryManager {
  var repoPrefix = "repositories"
  
  def buildRepository(path: String): Git = {
    val gitDir = new File(repoPrefix,  path)

    val repository = new RepositoryBuilder()
      .setGitDir(gitDir)
      .setMustExist(true)
      .setBare()
      .build()

    new Git(repository)
  }
  
  def createRepository(path: String): Git = {
    val repoDir = new File(repoPrefix, path)
    require(!repoDir.exists, "Repository already exists")

    new InitCommand().setDirectory(repoDir).setBare(true).call()
  }
}