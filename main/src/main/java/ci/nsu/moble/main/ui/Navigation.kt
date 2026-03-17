package ci.nsu.moble.main.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ScreenOne : Screen("screen_one")
    object ScreenTwo : Screen("screen_two")
}