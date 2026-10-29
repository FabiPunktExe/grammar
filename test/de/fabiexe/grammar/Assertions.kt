package de.fabiexe.grammar

import kotlin.test.assertEquals

fun <T> assertToken(expectedToken: T, expectedPosition: Int, actual: ParseResult<T>) {
    assertEquals(ParseResult.Token(expectedToken, expectedPosition), actual)
}

fun <T> assertFailure(expectedError: String, expectedPosition: Int, actual: ParseResult<T>) {
    assertEquals(ParseResult.Failure(expectedError, expectedPosition), actual)
}