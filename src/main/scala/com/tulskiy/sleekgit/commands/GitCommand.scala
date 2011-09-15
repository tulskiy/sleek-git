package com.tulskiy.sleekgit.commands

import org.apache.sshd.server.{ExitCallback, Command}
import java.io.{InputStream, OutputStream}
import org.eclipse.jgit.lib.Repository

/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

abstract class GitCommand(repository: Repository) extends Command {
  var exitCallback: ExitCallback = null;
  var err, out: OutputStream = null;
  var in: InputStream = null;

  def setExitCallback(callback: ExitCallback) {
    this.exitCallback = callback
  };

  def setErrorStream(err: OutputStream) {this.err = err}

  def setOutputStream(out: OutputStream) {this.out = out}

  def setInputStream(in: InputStream) {this.in = in}
}