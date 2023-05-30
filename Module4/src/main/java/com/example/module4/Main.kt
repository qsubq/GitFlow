package com.example.module4

fun main() {
    val book1 = Book(100, 1500)
    val book2 = Book(2000, 20000)
    val magazine = Magazine(500, 400)

    println("book1: ${book1.getType()}, ${book1.wordCount}, ${book1.price}")
    println("book2: ${book2.getType()}, ${book2.wordCount}, ${book2.price}")
    println("magazine: ${magazine.getType()}, ${magazine.wordCount}, ${magazine.price}")

    println(book1 === book2)
    println(book1 == book2)

    val book3: Book? = null
    val book4: Book? = Book(150, 150)
    book3?.let { buy(it) }
    book4?.let { buy(it) }

    val sum = {x:Int,y:Int->
        x + y
    }
    println(sum(1,9))

}

private fun buy(publication: Publication) {
    println("The purchase is complete. The purchase amount was ${publication.price}")
}

class Book(override val price: Int, override val wordCount: Int) : Publication {
    override fun getType(): String {
        return if (wordCount < 1000) {
            "Flash Fiction"
        } else if (wordCount < 7500) {
            "Short Story"
        } else {
            "Novel"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Book)
            return false
        return price == other.price && wordCount == other.wordCount
    }

}

class Magazine(override val price: Int, override val wordCount: Int) : Publication {
    override fun getType(): String {
        return "Magazine"
    }
}

interface Publication {
    val price: Int
    val wordCount: Int

    fun getType(): String
}