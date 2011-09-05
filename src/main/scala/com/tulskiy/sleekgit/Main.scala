package com.tulskiy.sleekgit

import org.apache.sshd.SshServer
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider
import org.apache.sshd.server.session.ServerSession
import org.apache.sshd.server.shell.ProcessShellFactory
import org.apache.sshd.server.command.ScpCommandFactory
import org.apache.sshd.server.{PublickeyAuthenticator, Command, CommandFactory, PasswordAuthenticator}
import java.security.PublicKey

/**
 * Author: Denis Tulskiy
 * Date: 9/4/11
 */

object Main {
  val GitCommandRE = """^git-((?:receive|upload)-pack) '(.*?)'$""".r

  def main(args: Array[String]) {
    val sshd = SshServer.setUpDefaultServer();

    sshd.setPort(22)
    sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hotkey.ser"))
    //    sshd.setShellFactory(new ProcessShellFactory(Array[String]("c:\\Programs\\Git\\bin\\bash.exe", "-i", "-l")))

    sshd.setCommandFactory(new ScpCommandFactory(new CommandFactory {
      def createCommand(command: String): Command = {
        command match {
          case GitCommandRE(com, arg) => {
            new GitProcessFactory(com, arg).create()
          }
        }
      }
    }))

    sshd.setPublickeyAuthenticator(new PublickeyAuthenticator {
      def authenticate(username: String, key: PublicKey, session: ServerSession): Boolean = {
        true
      }
    })

    sshd.start()
  }
}