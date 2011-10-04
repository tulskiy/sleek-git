package com.tulskiy.sleekgit

import commands.{UploadPackCommand, GitCommandFactory}
import java.util.Scanner
import org.eclipse.jgit.lib.RepositoryBuilder
import java.io.File
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.revwalk.RevCommit

import scala.collection.JavaConversions._

/**
 * Author: Denis Tulskiy
 * Date: 9/5/11
 */
object Test {
  def main(args: Array[String]) {
//    val builder = new ProcessBuilder("git", "upload-pack", "tesafdgt.git");
////    val builder = new ProcessBuilder("c:\\Programs\\Git\\bin\\bash.exe", "-c", "\"git gui\"");
//
//    val process = builder.start
//    val is = process.getErrorStream
//
//    val scanner = new Scanner(is)
//    while (scanner.hasNextLine) {
//      println(scanner.nextLine())
//    }


//    "st" match {
//      case "wed" => println("")
//    }
//
    val repository = new GitCommandFactory().buildRepository("repo/musique.git")

    val command = new UploadPackCommand(repository)

    command.setInputStream(System.in)
    command.setOutputStream(System.out)
    command.setErrorStream(System.err)

    command.start(null)


  }
}