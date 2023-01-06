package com.example.contact.fragment.add

import java.util.regex.Pattern

fun String.checkEditNumber(): Boolean {
    var check = false
    if (!Pattern.matches("[a-zA-Z]+", this)) {
        check = !(this.length < 6 || this.length > 13)
    } else {
        check = false
    }
    return check
}