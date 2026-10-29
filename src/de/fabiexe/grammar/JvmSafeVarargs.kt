package de.fabiexe.grammar

@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal expect annotation class JvmSafeVarargs()