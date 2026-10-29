package de.fabiexe.grammar

object JvmEmptyGrammar : Grammar<Void?> {
    override fun parse(input: String): ParseResult<Void?> {
        return ParseResult.Token(null, 0)
    }
}