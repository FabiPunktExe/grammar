package de.fabiexe.grammar

class AnyCharSymbolGrammar(val excludedSymbols: Collection<Char>) : Grammar<Char> {
    override fun parse(input: String): ParseResult<Char> {
        return if (input.isNotEmpty()) {
            val first = input[0]
            if (first in excludedSymbols) {
                ParseResult.Failure("Expected any symbol except ${excludedSymbols.map { "'$it'" }.sorted()}", 0)
            } else {
                ParseResult.Token(first, 1)
            }
        } else {
            ParseResult.Failure("Expected a symbol", 0)
        }
    }
}