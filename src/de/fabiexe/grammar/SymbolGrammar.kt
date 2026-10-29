package de.fabiexe.grammar

class SymbolGrammar(val symbols: Set<Char>) : Grammar<Char> {
    override fun parse(input: String): ParseResult<Char> {
        return if (input.isNotEmpty()) {
            val first = input[0]
            if (first in symbols) {
                ParseResult.Token(first, 1)
            } else {
                ParseResult.Failure("Expected a symbol", 0)
            }
        } else {
            ParseResult.Failure("Expected a symbol", 0)
        }
    }
}