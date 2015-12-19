package com.koenv.adventofcode

class Day19(input: CharSequence) {
    private val replacements: Map<String, List<String>>
    private val lexer: Lexer

    init {
        this.replacements = input.lines().map {
            val parts = it.split(" => ")
            parts[0] to parts[1]
        }.groupBy { it.first }.mapValues {
            it.value.map {
                it.second
            }
        }

        this.lexer = Lexer(replacements.keys.distinct().toList())
    }

    public fun parse(input: String): List<Lexer.Token> {
        return lexer.parse(input)
    }

    public fun getPossibilities(input: String): List<String> {
        val tokens = parse(input)

        val possibilities = arrayListOf<String>()

        tokens.filter { it.type == Lexer.TokenType.REPLACEMENT }.forEach { token ->
            replacements[token.text]!!.forEach { replacement ->
                val text = StringBuilder()
                tokens.forEach {
                    if (it == token) {
                        text.append(replacement)
                    } else {
                        text.append(it.text)
                    }
                }
                possibilities.add(text.toString())
            }
        }

        return possibilities.distinct()
    }

    public fun fromElectronTo(input: String): Int {
        var target = input

        var steps = 0
        while (target != "e") {
            replacements.forEach { entry ->
                entry.value.forEach {
                    if (target.contains(it)) {
                        target = target.substring(0, target.lastIndexOf(it)) + entry.key + target.substring(target.lastIndexOf(it) + it.length)
                        steps++
                    }
                }
            }
        }

        return steps
    }

    class Lexer(private val replacementPossibilities: List<String>) {
        private var replacementMaxLength: Int

        init {
            this.replacementMaxLength = replacementPossibilities.map { it.length }.max()!!
        }

        public fun parse(input: String): List<Token> {
            val tokens = arrayListOf<Token>()
            var position = 0

            while (position < input.length) {
                var nowPosition = position
                val it = StringBuilder()
                it.append(input[position])
                nowPosition++
                while (!replacementPossibilities.contains(it.toString()) && it.length < replacementMaxLength && nowPosition < input.length) {
                    it.append(input[nowPosition])
                    nowPosition++
                }
                if (replacementPossibilities.contains(it.toString())) {
                    tokens.add(Token(position, TokenType.REPLACEMENT, it.toString()))
                    position = nowPosition
                } else {
                    tokens.add(Token(position, TokenType.TEXT, input[position].toString()))
                    position++
                }
            }

            return tokens
        }

        data class Token(val position: Int, val type: TokenType, val text: String)

        enum class TokenType {
            TEXT,
            REPLACEMENT
        }
    }
}