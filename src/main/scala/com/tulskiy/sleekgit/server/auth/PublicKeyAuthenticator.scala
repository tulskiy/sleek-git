package com.tulskiy.sleekgit.server.auth

import org.apache.sshd.server.PublickeyAuthenticator
import java.lang.String
import java.security.PublicKey
import org.apache.sshd.server.session.ServerSession

/**
 * Author: Denis Tulskiy
 * Date: 10/27/11
 */

class PublicKeyAuthenticator extends PublickeyAuthenticator {
  def authenticate(username: String, key: PublicKey, session: ServerSession) = {
    session.setAttribute(UserKey, new User(username, List(key)))
    true
  }
}