package de.fabiexe.grammar

sealed interface ParseResult<out T> {
    val position: Int

    data class Token<T>(val token: T, override val position: Int) : ParseResult<T>

    data class Failure(val error: String, override val position: Int) : ParseResult<Nothing>
}