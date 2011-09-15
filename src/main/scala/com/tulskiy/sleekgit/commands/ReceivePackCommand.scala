package com.tulskiy.sleekgit.commands

import org.eclipse.jgit.lib.Repository
import org.apache.sshd.server.Environment

/**
 * Author: Denis Tulskiy
 * Date: 9/14/11
 */

class ReceivePackCommand(repository: Repository) extends GitCommand(repository) {
  def start(env: Environment) {
    err.write("Not Implemented\n".getBytes)
    err.flush()
    exitCallback.onExit(0)
  }

  def destroy() {}
}