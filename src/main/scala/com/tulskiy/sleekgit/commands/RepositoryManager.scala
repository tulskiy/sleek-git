package com.tulskiy.sleekgit.commands

import java.io.File
import org.eclipse.jgit.lib.{RepositoryBuilder, Repository}

/**
 * Author: Denis Tulskiy
 * Date: 10/28/11
 */

object RepositoryManager {
  def buildRepository(path: String): Repository = {
    val gitDir = new File(path)

    val repository = new RepositoryBuilder()
      .setGitDir(gitDir)
      .setMustExist(true)
      .setBare()
      .build()

    repository
  }
}