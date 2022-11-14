package ru.netology.nmedia.counter

object Counter {

    fun count(c: Long): String {
        return when (c) {
            in 1000..9999 -> (c / 1000).toString() + if (c % 1000 / 100 < 1) "К" else "." + (c % 1000 / 100).toString() + "К"
            in 10000..999_999 -> (c / 1000).toString() + "К"
            in 1_000_000..99_000_000 -> (c / 1_000_000).toString() + if (c%1_000_000/100_000 < 1) "М" else "." + (c%1_000_000/100_000).toString() + "М"
            else -> c.toString()
        }

    }


}
