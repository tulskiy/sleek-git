package com.tulskiy.sleekgit.commands

import org.eclipse.jgit.lib.Repository
import org.apache.sshd.server.Environment
import org.eclipse.jgit.transport.ReceivePack

/**
 * Author: Denis Tulskiy
 * Date: 9/14/11
 */

class ReceivePackCommand(repository: Repository) extends GitCommand(repository) {
  val receivePack = new ReceivePack(repository)

  def start(env: Environment) {
    new Thread(new Runnable {
      def run() {
        receivePack.receive(in, out, err)
        exitCallback.onExit(0)
      }
    }).start()
  }

  def destroy() {}
}