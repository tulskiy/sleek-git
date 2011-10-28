package com.tulskiy.sleekgit.server

import org.eclipse.jgit.errors.RepositoryNotFoundException
import com.tulskiy.sleekgit.commands.RepositoryManager
import org.eclipse.jgit.api.Git

/**
 * Author: Denis Tulskiy
 * Date: 10/28/11
 */

object Config {
  private val adminRepoPath = "admin.git"
  private var adminRepo: Git = null;

  def initEmptyConfig(keyPath: String) {
    adminRepo = RepositoryManager.createRepository(adminRepoPath)
  }

  def init() {
    adminRepo = RepositoryManager.buildRepository(adminRepoPath)
  }
}