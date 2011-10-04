package com.tulskiy.sleekgit.commands

import org.apache.sshd.server.{CommandFactory}
import org.eclipse.jgit.lib.{RepositoryBuilder, Repository}
import java.io.{IOException, File}

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
  def createCommand(input: String) = {
    try {
      input match {
        case GitCommandFactory.GitUploadPackRE(path) => new UploadPackCommand(buildRepository(path))
        case GitCommandFactory.GitReceivePackRE(path) => new ReceivePackCommand(buildRepository(path))
      }
    } catch {
      case e: MatchError =>
        new OutputErrorCommand("Invalid git command: " + input)
      case e: InvalidRepositoryException =>
        new OutputErrorCommand(e.getMessage)
      case e: Exception =>
        new OutputErrorCommand("Unexpected error: " + e.getMessage)
    }
  }

  def buildRepository(path: String): Repository = {
    try {
      val gitDir = new File(path)

      val repository = new RepositoryBuilder()
        .setGitDir(gitDir)
        .setMustExist(true)
        .setBare()
        .build()

      repository
    } catch {
      case e: IOException =>
        throw new InvalidRepositoryException("'%s' does not appear to be a git repository".format(path), e)
    }
  }

  class InvalidRepositoryException(message: String, cause: Throwable) extends Exception(message, cause)

}