package com.example.majkomulti.screen.MainTab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.screen.profile.ProfileScreen
import io.github.skeptick.libres.compose.painterResource

object ProfileTab: Tab {

    @Composable
    override fun Content() {
        Navigator(ProfileScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Профиль",
            icon = painterResource(MajkoResourceImages.icon_profile)
        )
}