package com.tulskiy.sleekgit.commands

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.transport.UploadPack
import org.eclipse.jgit.api.Git

/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

class UploadPackCommand(repository: Git) extends GitCommand(repository) {
  val uploadPack = new UploadPack(repository.getRepository)

  def start() {
    uploadPack.upload(in, out, err)
  }
}