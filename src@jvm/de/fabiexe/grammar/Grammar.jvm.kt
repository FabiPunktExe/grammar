@file:JvmName("GrammarJvm")

package de.fabiexe.grammar

import java.util.*
import java.util.function.Function
import java.util.function.Supplier

fun empty(): Grammar<Void?> = JvmEmptyGrammar

fun <T> map(grammar: Grammar<Void?>, transform: Supplier<T>): Grammar<T> {
    return JvmMappingGrammar(grammar, false) { _ -> transform.get() }
}

@Deprecated("Use map instead", ReplaceWith("map(grammar, transform)"))
fun <T> transform(grammar: Grammar<Void?>, transform: Supplier<T>): Grammar<T> {
    return JvmMappingGrammar(grammar, false) { _ -> transform.get() }
}

fun <T, U> map(grammar: Grammar<T>, transform: Function<T, U>): Grammar<U> {
    return JvmMappingGrammar(grammar, false, transform)
}

@Deprecated("Use map instead", ReplaceWith("map(grammar, transform)"))
fun <T, U> transform(grammar: Grammar<T>, transform: Function<T, U>): Grammar<U> {
    return JvmMappingGrammar(grammar, false, transform)
}

fun <T> mapCatching(grammar: Grammar<Void?>, transform: Supplier<T>): Grammar<T> {
    return JvmMappingGrammar(grammar, true) { _ -> transform.get() }
}

@Deprecated("Use mapCatching instead", ReplaceWith("mapCatching(grammar, transform)"))
fun <T> transformCatching(grammar: Grammar<Void?>, transform: Supplier<T>): Grammar<T> {
    return JvmMappingGrammar(grammar, true) { _ -> transform.get() }
}

fun <T, U> mapCatching(grammar: Grammar<T>, transform: Function<T, U>): Grammar<U> {
    return JvmMappingGrammar(grammar, true, transform)
}

@Deprecated("Use mapCatching instead", ReplaceWith("mapCatching(grammar, transform)"))
fun <T, U> transformCatching(grammar: Grammar<T>, transform: Function<T, U>): Grammar<U> {
    return JvmMappingGrammar(grammar, true, transform)
}

fun <T> recursive(definition: Function<Grammar<T>, Grammar<T>>): Grammar<T> {
    return JvmRecursiveGrammar(definition)
}

fun <T : Any> optional(grammar: Grammar<T>): Grammar<Optional<T>> {
    return OptionalGrammar(grammar)
}

fun <T : Any> optional(grammar: Grammar<T>, defaultValue: T): Grammar<T> {
    return OptionalGrammar(grammar).map { it.orElse(defaultValue)!! }
}