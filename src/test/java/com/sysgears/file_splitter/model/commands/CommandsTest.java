package com.sysgears.file_splitter.model.commands;

/**
 * Test unit for <code>com.sysgears.file_splitter.model.commands.Commands</code>.
 */
public class CommandsTest {

    public static void main(String[] args) {
        System.out.println("Input: help; Result: " + Commands.parse("help"));
        System.out.println("Input: split; Result: " + Commands.parse("split"));
        System.out.println("Input: exit; Result: " + Commands.parse("exit"));
        System.out.println("Input: slgkjlhkfjh; Result: " + Commands.parse("slgkjlhkfjh"));
    }
}