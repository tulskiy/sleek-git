package com.tulskiy.sleekgit.commands

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.transport.ReceivePack

/**
 * Author: Denis Tulskiy
 * Date: 9/14/11
 */

class ReceivePackCommand(repository: Repository) extends GitCommand(repository) {
  val receivePack = new ReceivePack(repository)

  def start() {
    receivePack.receive(in, out, err)
  }
}