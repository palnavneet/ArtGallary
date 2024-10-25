package com.app.exibhitease.presentation.data.repository

import com.app.exibhitease.R


fun getHArts() : List<Art>{
    return listOf(
        Art(
            id = R.drawable.licensed_image,
            name = "Mona Lisa",
            fullName = "Mona Lisa",
            rating = "5",
            model = "licensed"
        ),
        Art(
            id = R.drawable.man_horse,
            name = "NCTA",
            fullName = "Napoleon Crossing the Alps",
            rating = "4.2",
            model = "man_horse"
        ),
        Art(
            id = R.drawable.vincent,
            name = "Café Terrace",
            fullName = "Cafe Terrace at Night ",
            rating = "4.1",
            model = "vincent"
        ),
    )

}

fun getVArts() : List<Art>{
    return listOf(
        Art(
            id = R.drawable.street,
            name = "PSRD",
            fullName = "Paris Street, Rainy Day",
            rating = "4.6",
            model = "street"
        ),
        Art(
            id = R.drawable.water,
            name = "TGWK",
            fullName = "The Great Wave off Kanagawa",
            rating = "4.5",
            model = "water"
        ),
        Art(
            id = R.drawable.monet_impression_sunrise,
            name = "Sunrise",
            fullName = "Impression, Sunrise",
            rating = "4.0",
            model = "monet_impression_sunrise"
        ),
    )

}

data class Art(
    val id : Int,
    val name : String,
    val fullName : String,
    val rating : String,
    val model : String
)

