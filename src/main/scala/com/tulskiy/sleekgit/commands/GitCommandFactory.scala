package com.tulskiy.sleekgit.commands

import org.apache.sshd.server.CommandFactory
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
    import GitCommandFactory._;
    import RepositoryManager._
    try {
      input match {
        case GitUploadPackRE(path) => new UploadPackCommand(buildRepository(path))
        case GitReceivePackRE(path) => new ReceivePackCommand(buildRepository(path))
      }
    } catch {
      case e: MatchError =>
        new OutputErrorCommand("Invalid git command: " + input)
      case e: Exception =>
        new OutputErrorCommand("fatal: " + e.getMessage)
    }
  }
}