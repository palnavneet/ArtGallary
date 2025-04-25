package com.app.exibhitease.presentation.navigation


sealed class Route(
    val route : String
){
    object OnBoardingScreen : Route(route = "OnBoardingScreen")
    object HomeScreen : Route(route = "HomeScreen")
    object ArScreen : Route(route = "ArScreen")
    object CustomImage : Route(route = "CustomImage")
    object GenerateImage : Route(route = "GenerateImage")

}

