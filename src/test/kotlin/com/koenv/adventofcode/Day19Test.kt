package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class Day19Test {
    @Test
    fun lexerTest() {
        val lexer = Day19.Lexer(listOf("a", "b"))
        val result = lexer.parse("abacdaabc")

        assertEquals(6, result.filter { it.type == Day19.Lexer.TokenType.REPLACEMENT }.size)
        assertEquals(3, result.filter { it.type == Day19.Lexer.TokenType.TEXT }.size)

        assertEquals(
                listOf(
                        Day19.Lexer.Token(0, Day19.Lexer.TokenType.REPLACEMENT, "a"),
                        Day19.Lexer.Token(1, Day19.Lexer.TokenType.REPLACEMENT, "b"),
                        Day19.Lexer.Token(2, Day19.Lexer.TokenType.REPLACEMENT, "a"),
                        Day19.Lexer.Token(3, Day19.Lexer.TokenType.TEXT, "c"),
                        Day19.Lexer.Token(4, Day19.Lexer.TokenType.TEXT, "d"),
                        Day19.Lexer.Token(5, Day19.Lexer.TokenType.REPLACEMENT, "a"),
                        Day19.Lexer.Token(6, Day19.Lexer.TokenType.REPLACEMENT, "a"),
                        Day19.Lexer.Token(7, Day19.Lexer.TokenType.REPLACEMENT, "b"),
                        Day19.Lexer.Token(8, Day19.Lexer.TokenType.TEXT, "c")
                ),
                result
        )
    }

    @Test
    fun multipleCharacterLexerTest() {
        val lexer = Day19.Lexer(listOf("aa", "b"))
        val result = lexer.parse("abacdaabc")

        assertEquals(3, result.filter { it.type == Day19.Lexer.TokenType.REPLACEMENT }.size)
        assertEquals(5, result.filter { it.type == Day19.Lexer.TokenType.TEXT }.size)

        assertEquals(
                listOf(
                        Day19.Lexer.Token(0, Day19.Lexer.TokenType.TEXT, "a"),
                        Day19.Lexer.Token(1, Day19.Lexer.TokenType.REPLACEMENT, "b"),
                        Day19.Lexer.Token(2, Day19.Lexer.TokenType.TEXT, "a"),
                        Day19.Lexer.Token(3, Day19.Lexer.TokenType.TEXT, "c"),
                        Day19.Lexer.Token(4, Day19.Lexer.TokenType.TEXT, "d"),
                        Day19.Lexer.Token(5, Day19.Lexer.TokenType.REPLACEMENT, "aa"),
                        Day19.Lexer.Token(7, Day19.Lexer.TokenType.REPLACEMENT, "b"),
                        Day19.Lexer.Token(8, Day19.Lexer.TokenType.TEXT, "c")
                ),
                result
        )
    }

    @Test
    fun part1ExampleLexer() {
        val obj = Day19(
                """H => HO
H => OH
O => HH"""
        )
        val result = obj.parse("HOH")

        assertEquals(3, result.filter { it.type == Day19.Lexer.TokenType.REPLACEMENT }.size)
        assertEquals(0, result.filter { it.type == Day19.Lexer.TokenType.TEXT }.size)

        assertEquals(
                listOf(
                        Day19.Lexer.Token(0, Day19.Lexer.TokenType.REPLACEMENT, "H"),
                        Day19.Lexer.Token(1, Day19.Lexer.TokenType.REPLACEMENT, "O"),
                        Day19.Lexer.Token(2, Day19.Lexer.TokenType.REPLACEMENT, "H")
                ),
                result
        )
    }

    @Test
    fun part1ExamplePossibilities() {
        val obj = Day19(
                """H => HO
H => OH
O => HH"""
        )
        val result = obj.getPossibilities("HOH")

        assertEquals(4, result.size)

        assertEquals(
                listOf(
                        "HOOH",
                        "HOHO",
                        "OHOH",
                        "HHHH"
                ).sorted(),
                result.sorted()
        ) // sorted, because orders don't matter
    }

    @Test
    fun part1Real() {
        val replacements = StringBuilder()
        var input: String = ""

        getInput(19).lines().forEach {
            if (it.contains("=>")) {
                replacements.appendln(it)
            } else if (!it.isEmpty()) {
                input = it
            }
        }

        val obj = Day19(replacements.trim())

        assertFalse(input.isEmpty())

        val result = obj.getPossibilities(input)

        assertEquals(535, result.size)
    }

    @Test
    fun part2Real() {
        val replacements = StringBuilder()
        var input: String = ""

        getInput(19).lines().forEach {
            if (it.contains("=>")) {
                replacements.appendln(it)
            } else if (!it.isEmpty()) {
                input = it
            }
        }

        val obj = Day19(replacements.trim())

        assertFalse(input.isEmpty())

        val result = obj.fromElectronTo(input)

        assertEquals(212, result)
    }
}