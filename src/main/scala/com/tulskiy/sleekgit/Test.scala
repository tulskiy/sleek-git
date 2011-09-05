package com.tulskiy.sleekgit

import java.util.Scanner
import org.eclipse.jgit.api.Git

/**
 * Author: Denis Tulskiy
 * Date: 9/5/11
 */
object Test {
  def main(args: Array[String]) {
//    val builder = new ProcessBuilder("cmd", "/c", "dir");
    val builder = new ProcessBuilder("c:\\Programs\\Git\\bin\\bash.exe", "-c", "\"git gui\"");

    val process = builder.start
    val is = process.getInputStream

    val scanner = new Scanner(is)
    while (scanner.hasNextLine) {
      println(scanner.nextLine())
    }
  }
}