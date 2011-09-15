package com.tulskiy.sleekgit.commands

import org.apache.sshd.server.Environment
import org.eclipse.jgit.lib.Repository

/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

class UploadPackCommand(repository: Repository) extends GitCommand(repository) {
  def start(env: Environment) {
    err.write("Not Implemented\n".getBytes)
    err.flush()
    exitCallback.onExit(0)
  }

  def destroy() {}
}