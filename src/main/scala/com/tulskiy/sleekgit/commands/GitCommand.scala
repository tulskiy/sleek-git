package com.tulskiy.sleekgit.commands

import org.apache.sshd.server.{ExitCallback, Command}
import org.eclipse.jgit.lib.Repository
import java.io.{BufferedOutputStream, BufferedInputStream, InputStream, OutputStream}

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

  def setOutputStream(out: OutputStream) {this.out = new BufferedOutputStream(out)}

  def setInputStream(in: InputStream) {this.in = new BufferedInputStream(in)}
}