package com.tulskiy.sleekgit.commands

import org.apache.sshd.server.{CommandFactory}
import java.io.{PrintWriter, File}
import org.eclipse.jgit.lib.{Repository, RepositoryBuilder}

/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

object GitCommandFactory {
  val GitRE = """^git[- ]"""
  val ArgsRE = """\s*'[/~]*(\w[\w@.-]*(?:/\w[\w@.-]*)*)'$"""

  val GitUploadPackRE = (GitRE + "upload-pack" + ArgsRE).r
  val GitReceivePackRE = (GitRE + "receive-pack" + ArgsRE).r
}

class GitCommandFactory extends CommandFactory {
  def create(command: String, args: String): GitCommand = {
    val builder = new RepositoryBuilder();
    val repository = builder.setGitDir(new File(args))
      .readEnvironment() // scan environment GIT_* variables
      .findGitDir() // scan up the file system tree
      .build();

    null
  }

  def createCommand(input: String) = {
    try {
      input match {
        case GitCommandFactory.GitUploadPackRE(path) => new UploadPackCommand(buildRepository(path))
        case GitCommandFactory.GitReceivePackRE(path) => new ReceivePackCommand(null)
      }
    } catch {
      case e: MatchError => {
        new OutputErrorCommand("Invalid git command: " + input)
      }
      case e: InvalidRepositoryException => {
        new OutputErrorCommand(e.getMessage)
      }
    }
  }

  def buildRepository(path: String): Repository = {
    val baseDir = new File(path)
    val gitDir = new File(baseDir, ".git")
    null
  }

  class InvalidRepositoryException(message: String) extends RuntimeException(message)

}