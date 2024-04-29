package me.liolin.app_badge_plus.badge

class BadgeException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}