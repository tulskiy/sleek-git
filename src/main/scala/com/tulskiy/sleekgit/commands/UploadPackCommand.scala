package com.tulskiy.sleekgit.commands

import org.apache.sshd.server.Environment
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.transport.{UploadPackLogger, UploadPack}
import org.eclipse.jgit.storage.pack.PackWriter.Statistics
import org.slf4j.LoggerFactory
import java.util.logging.{Level, Logger}
import java.lang.Thread

/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

class UploadPackCommand(repository: Repository) extends GitCommand(repository) {
  val uploadPack = new UploadPack(repository)

  def start(env: Environment) {
    new Thread(new Runnable {
      def run() {
        uploadPack.upload(in, out, err)
        exitCallback.onExit(0)
      }
    }).start()
  }

  def destroy() {}
}