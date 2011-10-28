package com.tulskiy.sleekgit.server.auth

import org.apache.sshd.common.Session
import java.security.PublicKey

/**
 * Author: Denis Tulskiy
 * Date: 10/26/11
 */

class User(val name: String, val keys: List[PublicKey] = List())

object UserKey extends Session.AttributeKey[User]
