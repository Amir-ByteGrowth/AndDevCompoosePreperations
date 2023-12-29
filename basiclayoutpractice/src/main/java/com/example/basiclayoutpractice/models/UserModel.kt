package com.example.basiclayoutpractice.models

data class UserModel(val img: Int, val name: String, val lastSeen: String)


val listOfImages = listOf(
    "https://www.techsmith.com/blog/wp-content/uploads/2021/02/video-thumbnails-hero-1.png",
    "https://play-lh.googleusercontent.com/5EfQBHDb47tchiART6U6yk3yYS9qBYr6VUssB5wHE1AgavqV5E2SSuzyiNkc7UgVng",
    "https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_1280,h_720/https://blog.snappa.com/wp-content/uploads/2019/01/YouTube-Thumbnail-Dimensions.jpg",
    "https://play-lh.googleusercontent.com/Nob94ngx52WGcRJig23EfwGwY5U7mUkKy7SMJUsOSoDHcthJNdmOhBZoBYWlv0f9zY8",
    "https://play-lh.googleusercontent.com/AYbcmmhoi3MCClJCV-GfGCWszqjBc8gaUtKzLuK4G1OG2BUndwJz5Hwv4WrC-qoTLrlD=w240-h480-rw",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQOxIKcUJwlRydIPoKAUi0gmM-0IubDQ-77g&usqp=CAU"
)

data class TitleSubData(val title: String, val subTitle: String)

val listOfTitleSubTitle = listOf<TitleSubData>(
    TitleSubData("Title 1", "Subtitle 1"),
    TitleSubData("Title 2", "Subtitle 2"),
    TitleSubData("Title 3", "Subtitle 3"),
    TitleSubData("Title 4", "Subtitle 4"),
    TitleSubData("Title 5", "Subtitle 5"),
    TitleSubData("Title 6", "Subtitle 6"),
    TitleSubData("Title 7", "Subtitle 7")
)