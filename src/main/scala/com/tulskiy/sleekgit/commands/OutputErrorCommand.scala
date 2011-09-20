package com.tulskiy.sleekgit.commands

import org.apache.sshd.server.{ExitCallback, Environment, Command}
import java.io.{PrintWriter, InputStream, OutputStream}

/**
 * Author: Denis Tulskiy
 * Date: 9/15/11
 */

class OutputErrorCommand(error: String) extends Command {
  var err: PrintWriter = null;
  var exitCallback: ExitCallback = null;

  def setInputStream(in: InputStream) {}

  def setOutputStream(out: OutputStream) {}

  def setErrorStream(err: OutputStream) {
    this.err = new PrintWriter(err)
  }

  def setExitCallback(callback: ExitCallback) {
    this.exitCallback = callback
  }

  def start(env: Environment) {
    err.println(error)
    err.flush()

    exitCallback.onExit(1)
  }

  def destroy() {}
}