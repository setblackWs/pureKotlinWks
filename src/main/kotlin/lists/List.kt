package lists

sealed class KList<out T>

object Nil : KList<Nothing>() {
    override fun toString() = "[]"
}

data class Cons<T>(val head: T, val tail: KList<T>) : KList<T>() {
    override fun toString(): String = "$head : $tail"
}


tailrec fun sum(list: KList<Int>, partial: Int = 0): Int = when (list) {
    is Nil -> partial
    is Cons -> sum(list.tail, partial + list.head)
}

tailrec fun <T> show(list: KList<T>, partial: String = ""): String = when (list) {
    is Nil -> partial + ": []"
    is Cons -> show(list.tail, "$partial : ${list.head}")
}


tailrec fun range(first: Int, last: Int, partial: KList<Int> = Nil): KList<Int> =
    if (first >= last) {
        partial
    } else {
        range(first, last - 1, Cons(last - 1, partial))
    }

tailrec fun <T> KList<T>.reverse(partial: KList<T> = Nil): KList<T> = when (this) {
        is Nil -> partial
        is Cons -> this.tail.reverse(Cons(this.head, partial))
}

//tailrec fun<T> KList<T>.filter( predicate: partial: KList<Int> = Nil): KList<Int> =
//    if (first >= last) {
//        partial
//    } else {
//        range(first, last - 1, Cons(last - 1, partial))
//    }



fun main() {

    val x1 = Cons(1, Nil)
    val x2 = Cons(2, x1)
    val x3 = Cons(5, x2)
    println(x1)
    println(x2)
    println(x3)
    println(sum(x3))
    val longList = range(1, 10)
    println(show(longList))
//    println(sum(longList))
    val reversed = longList.reverse()
    print(show(reversed))
//    println(longList)
//    val b = Cons("Lublin", Nil)
//    println(b)

}
