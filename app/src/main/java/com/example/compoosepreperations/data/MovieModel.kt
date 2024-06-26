package com.example.compoosepreperations.data

data class MovieModel(val id: Int, val name: String,val url:String,)

var moviesList = listOf(
    MovieModel(1, "Movie","https://placebear.com/g/200/200"),
    MovieModel(2, "Movie","https://source.unsplash.com/user/c_v_r/1900×800"),
    MovieModel(3, "Movie","https://placebear.com/g/200/200"),
    MovieModel(4, "Movie","https://www.gstatic.com/webp/gallery3/1.sm.png"),
    MovieModel(5, "Movie","https://www.gstatic.com/webp/gallery3/1.sm.png")
)