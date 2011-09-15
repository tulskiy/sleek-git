package com.tulskiy.sleekgit

import commands.{GitCommandFactory, GitProcessFactory}
import org.apache.sshd.SshServer
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider
import org.apache.sshd.server.command.ScpCommandFactory
import org.apache.sshd.server.shell.ProcessShellFactory
import org.apache.sshd.server.session.{ServerSession}
import java.net.InetSocketAddress
import org.apache.sshd.server._
import auth.{UserAuthNone, UserAuthPublicKey}
import java.security.PublicKey
import scala.collection.JavaConversions._


/**
 * Author: Denis Tulskiy
 * Date: 9/12/11
 */

class GitServer {
  val sshd = SshServer.setUpDefaultServer();

  sshd.setPort(2200)
  sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hotkey.ser"))

  sshd.setCommandFactory(new GitCommandFactory())

  sshd.setUserAuthFactories(List(new UserAuthNone.Factory, new UserAuthPublicKey.Factory))

  sshd.setFileSystemFactory(new FileSystemFactory {
    def createFileSystemView(userName: String) = new FileSystemView {
      def getFile(file: String) = null
    }
  })

  sshd.setForwardingFilter(new ForwardingFilter {
    def canForwardX11(session: ServerSession): Boolean = false

    def canListen(address: InetSocketAddress, session: ServerSession): Boolean = false

    def canConnect(address: InetSocketAddress, session: ServerSession): Boolean = false

    def canForwardAgent(session: ServerSession): Boolean = false
  })

  sshd.setPublickeyAuthenticator(new PublickeyAuthenticator {
    def authenticate(username: String, key: PublicKey, session: ServerSession): Boolean = {
      true
    }
  })

  def start() {
    sshd.start()
  }
}