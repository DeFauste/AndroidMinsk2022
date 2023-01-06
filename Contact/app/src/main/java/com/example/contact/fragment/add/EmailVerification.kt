package com.example.contact.fragment.add

import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.checkEditEmail(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}