package com.tulskiy.sleekgit.commands

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.transport.ReceivePack
import org.eclipse.jgit.api.Git

/**
 * Author: Denis Tulskiy
 * Date: 9/14/11
 */

class ReceivePackCommand(repository: Git) extends GitCommand(repository) {
  val receivePack = new ReceivePack(repository.getRepository)

  def start() {
    receivePack.receive(in, out, err)
  }
}