package de.fabiexe.grammar

class RepeatingGrammar<T>(val grammar: Grammar<T>, val min: Int) : Grammar<List<T>> {
    init {
        require(min >= 0) { "min must not be negative" }
    }

    override fun parse(input: String): ParseResult<List<T>> {
        val tokens = ArrayList<T>()
        var position = 0

        while (true) {
            when (val result = grammar.parse(input.substring(position))) {
                is ParseResult.Token -> {
                    if (result.position == 0) {
                        break
                    } else {
                        tokens += result.token
                        position += result.position
                    }
                }
                is ParseResult.Failure -> {
                    if (tokens.size < min) {
                        return ParseResult.Failure(result.error, position + result.position)
                    } else {
                        break
                    }
                }
            }
        }

        return if (tokens.size < min) {
            ParseResult.Failure("Expected at least $min repetitions", position)
        } else {
            ParseResult.Token(tokens, position)
        }
    }
}