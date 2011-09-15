package com.tulskiy.sleekgit.commands

import org.eclipse.jgit.lib.RepositoryBuilder
import org.apache.sshd.server.{Environment, CommandFactory}
import java.io.{PrintWriter, File}

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

  def createCommand(input: String) = input match {
    case GitCommandFactory.GitUploadPackRE(path) => new UploadPackCommand(null)
    case GitCommandFactory.GitReceivePackRE(path) => new UploadPackCommand(null)
    case _ => new InvalidCommand(input)
  }

  class InvalidCommand(input: String) extends GitCommand(null) {
    def start(env: Environment) {
      err.write(String.format("Invalid command: %s. Only git commands are supported.", input).getBytes)
      err.flush()
      exitCallback.onExit(0)
    }

    def destroy() {
    }
  }
}