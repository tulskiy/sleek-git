package com.tulskiy.sleekgit

import org.apache.sshd.common.Factory
import org.apache.sshd.server.Command
import java.io.File
import java.util.Map
import org.apache.sshd.server.shell.{InvertedShellWrapper, InvertedShell}

/**
 * Author: Denis Tulskiy
 * Date: 9/5/11
 */
class GitProcessFactory(commands: String*) extends Factory[Command] {
  val gitExecPath = "c:/Programs/Git/bin/git.exe"
  val workingDir = new File("c:/Programs/sleek-git")

  def create() = new InvertedShellWrapper(new GitRunner)

  private class GitRunner extends InvertedShell {
    var process: Process = null;

    def start(env: Map[String, String]) {
      val builder = new ProcessBuilder((gitExecPath :: commands.toList): _*)

      if (env != null) {
        builder.environment.putAll(env)
      }

      builder.directory(workingDir)

      process = builder.start
    }

    def destroy() {
      process.destroy()
    }

    def exitValue() = process.exitValue()

    def isAlive = {
      try {
        exitValue()
        false
      } catch {
        case e: IllegalThreadStateException =>
          true
      }
    }

    def getErrorStream = process.getErrorStream

    def getOutputStream = process.getInputStream

    def getInputStream = process.getOutputStream
  }

}