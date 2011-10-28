package com.tulskiy.sleekgit.server

import auth.PublicKeyAuthenticator
import org.apache.sshd.SshServer
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider
import java.net.InetSocketAddress
import org.apache.sshd.server._
import java.security.PublicKey
import com.weiglewilczek.slf4s.Logging
import scala.collection.JavaConversions._
import session.ServerSession
import com.tulskiy.sleekgit.commands.GitCommandFactory
import org.apache.sshd.server.auth.UserAuthPublicKey
import org.apache.sshd.common.Session


/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

class Server extends Logging {
  val sshd = SshServer.setUpDefaultServer();

  sshd.setPort(2200)
  sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hotkey.ser"))

  sshd.setCommandFactory(new GitCommandFactory())

  sshd.setUserAuthFactories(List(new UserAuthPublicKey.Factory))

  sshd.setFileSystemFactory(new FileSystemFactory {
    def createFileSystemView(session: Session) = null
  })

  sshd.setForwardingFilter(new ForwardingFilter {
    def canForwardX11(session: ServerSession): Boolean = false

    def canListen(address: InetSocketAddress, session: ServerSession): Boolean = false

    def canConnect(address: InetSocketAddress, session: ServerSession): Boolean = false

    def canForwardAgent(session: ServerSession): Boolean = false
  })

  sshd.setPublickeyAuthenticator(new PublicKeyAuthenticator)

  def start() {
    logger.info("Starting server")
    sshd.start()
  }
}