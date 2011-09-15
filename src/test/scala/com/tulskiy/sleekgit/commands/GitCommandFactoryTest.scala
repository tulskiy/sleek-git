package com.tulskiy.sleekgit.commands

import org.specs2.mutable._

/**
 * Author: Denis Tulskiy
 * Date: 9/13/11
 */

class GitCommandFactoryTest extends Specification {
  /*"The GitUploadPackRE" should {
    "match good input" in {
      "git-upload-pack 'test.git'" must matchUploadWithPath("test.git")
      "git upload-pack '/test.git'" must matchUploadWithPath("test.git")
      "git-upload-pack '~test.git'" must matchUploadWithPath("test.git")
      "git upload-pack 'user/test.git'" must matchUploadWithPath("user/test.git")
      "git-upload-pack '~user/test.git'" must matchUploadWithPath("user/test.git")
      "git upload-pack '/user/test.git'" must matchUploadWithPath("user/test.git")
      "git upload-pack '~/user/test.git'" must matchUploadWithPath("user/test.git")
      "git upload-pack '/~user/test.git'" must matchUploadWithPath("user/test.git")
    }

    "avoid bad input" in {
      "git-upload-pack 'test.git" must not(matchUpload)
      "git-upload-pack '/../test.git'" must not(matchUpload)
      "git-upload-pack '~/../user/test.git'" must not(matchUpload)
      "git-upload-pack test.git" must not(matchUpload)
      "git-upload-pack '~user/../test.git'" must not(matchUpload)
      "git-upload-pack '~user/test.git/./wdr'" must not(matchUpload)
      "git-upload-pack '~user/test.git/../wdr'" must not(matchUpload)
    }


  }

  def matchUpload = beMatching(GitCommandFactory.GitUploadPackRE.toString())
  def matchReceive = beMatching(GitCommandFactory.GitReceivePackRE.toString())

  def matchUploadWithPath(path: String) = find(GitCommandFactory.GitUploadPackRE.toString()).withGroup(path)
  def matchReceiveWithPath(path: String) = find(GitCommandFactory.GitReceivePackRE.toString()).withGroup(path)
*/
  "The 'Hello world' string" should {
        "contain 11 characters" in {
          "Hello world" must have size(11)
        }
        "start with 'Hello'" in {
          "Hello world" must startWith("Hello")
        }
        "end with 'world'" in {
          "Hello world" must endWith("world")
        }
      }
}