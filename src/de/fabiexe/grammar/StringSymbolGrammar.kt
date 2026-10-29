package de.fabiexe.grammar

class StringSymbolGrammar(val string: String) : Grammar<String> {
    override fun parse(input: String): ParseResult<String> {
        var position = 0
        while (position < string.length) {
            if (position < input.length && input[position] == string[position]) {
                position++
            } else {
                return ParseResult.Failure("Expected '$string'", 0)
            }
        }
        return ParseResult.Token(string, position)
    }
}