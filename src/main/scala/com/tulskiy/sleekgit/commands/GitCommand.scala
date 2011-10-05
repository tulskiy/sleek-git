package com.tulskiy.sleekgit.commands

import org.eclipse.jgit.lib.Repository
import org.apache.sshd.server.{Environment, ExitCallback, Command}
import java.lang.Thread
import java.io._

/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

abstract class GitCommand(repository: Repository) extends Command {
  var exitCallback: ExitCallback = null;
  var err, out: OutputStream = null;
  var in: InputStream = null;
  var runner: Thread = null;

  def setExitCallback(callback: ExitCallback) {
    this.exitCallback = callback
  };


  def start(env: Environment) {
    runner = new Thread(new Runnable {
      def run() {
        try {
          start();
          exitCallback.onExit(0)
        }
        catch {
          case e: InterruptedException =>
            exitCallback.onExit(1, "Interrupted!")
          case e: Exception =>
            exitCallback.onExit(1, e.getMessage)
        }
      }
    })

    runner.start()
  }

  def start();


  def destroy() {
    if (runner != null)
      runner.interrupt();
  }

  def setErrorStream(err: OutputStream) {this.err = err}

  def setOutputStream(out: OutputStream) {this.out = new BufferedOutputStream(out)}

  def setInputStream(in: InputStream) {this.in = new BufferedInputStream(in)}
}