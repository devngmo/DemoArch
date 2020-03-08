package tml.lab.demoarch.ma.entities

val ILT_UNDEF = ""
val ILT_RECENT = "recent"
val ILT_FAVORITE = "favorite"
val ILT_ALL = "all"

class Idea(var id :String, var title: String, var content: String, var authors: ArrayList<String>) {
    val cardDesc: String
     get() {
         return authors.joinToString(separator = ", ")
     }
}

typealias IdeaList = ArrayList<Idea>