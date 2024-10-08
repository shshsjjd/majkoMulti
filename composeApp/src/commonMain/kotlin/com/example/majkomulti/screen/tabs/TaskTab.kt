package com.example.majkomulti.screen.tabs

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.majkomulti.images.MajkoResourceImages
import io.github.skeptick.libres.compose.painterResource
import com.example.majkomulti.screen.task.TaskScreen

object TaskTab: Tab {

    @Composable
    override fun Content() {
        Navigator(TaskScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Задачи",
            icon = painterResource(MajkoResourceImages.icon_task)
        )
}