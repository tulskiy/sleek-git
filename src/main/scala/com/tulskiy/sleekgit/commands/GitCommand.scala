package com.tulskiy.sleekgit.commands

import java.lang.Thread
import java.io._
import com.weiglewilczek.slf4s.Logging
import org.apache.sshd.server.{SessionAware, Environment, ExitCallback, Command}
import org.apache.sshd.server.session.ServerSession
import com.tulskiy.sleekgit.server.auth.{UserKey, User}
import org.eclipse.jgit.api.Git

/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

abstract class GitCommand(repository: Git) extends Command with Logging with SessionAware {
  var exitCallback: ExitCallback = null;
  var err, out: OutputStream = null;
  var in: InputStream = null;
  var runner: Thread = null;
  var user: User = null;

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
    logger.warn("received destory command")
    if (runner != null)
      runner.interrupt();
  }

  def setErrorStream(err: OutputStream) {
    this.err = err
  }

  def setOutputStream(out: OutputStream) {
    this.out = new BufferedOutputStream(out)
  }

  def setInputStream(in: InputStream) {
    this.in = new BufferedInputStream(in)
  }

  def setSession(session: ServerSession) {
    user = session.getAttribute(UserKey);
  }
}