package com.tulskiy.sleekgit.commands

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.transport.UploadPack
/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

class UploadPackCommand(repository: Repository) extends GitCommand(repository) {
  val uploadPack = new UploadPack(repository)

  def start() {
    uploadPack.upload(in, out, err)
  }
}