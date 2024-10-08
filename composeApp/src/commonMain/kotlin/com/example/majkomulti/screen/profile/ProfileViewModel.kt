package com.example.majkomulti.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.majkomulti.data.models.User.UserUpdateEmail
import com.example.majkomulti.data.models.User.UserUpdateName
import com.example.majkomulti.domain.manager.AuthManager
import com.example.majkomulti.domain.repository.UserRepository
import com.example.majkomulti.platform.BaseScreenModel
import com.sun.jndi.toolkit.url.Uri
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File
import com.example.majkomulti.data.models.User.UserUpdateImage
import kotlinx.coroutines.delay
import java.io.FileOutputStream
import javax.swing.JFileChooser

internal class ProfileViewModel : BaseScreenModel<ProfileState, Unit>(ProfileState.InitState) {
    private val authManager: AuthManager by inject()
    private val userRepository: UserRepository by inject()

    fun updateUserName(username: String) = blockingIntent {
        reduce { state.copy(userName = username) }
    }

    fun updateUserEmail(email: String) = blockingIntent {
        reduce { state.copy(userEmail = email) }
    }

    fun changePasswordScreen(){

    }

    fun forgetAccount(){
        authManager.token = null
    }

    fun loadData() = intent {
        launchOperation(
            operation = {
                userRepository.currentUser()
            },
            success = { response ->
                reduceLocal {
                    state.copy( currentUser = response,
                        avatar = response.image,
                        userName = response.name,
                        userEmail = response.email,)
                }
            },
            failure = {
            }
        )
    }

    fun updateEmailData(name: String, email: String) = intent{
        launchOperation(
            operation = {
                userRepository.updateUserEmail(
                    UserUpdateEmail(name, email)
                )
            }
        )
    }

    fun updateNameData(name: String)= intent{
        launchOperation(
            operation = {
                userRepository.updateUserName(
                    UserUpdateName(name)
                )
            }
        )
    }

    fun openFile() {
        val fileChooser = JFileChooser()
        val returnValue = fileChooser.showOpenDialog(null)

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            println("!!!!!!!!!!!!!!!!!!!имя выбранного файла: " + fileChooser.selectedFile.name)
            addFile(fileChooser.selectedFile.toURI().toString())
        }
    }

    fun addFile(file: String) = intent {
        launchOperation(
            operation = {
                userRepository.updateUserImage(state.userName, file)
            },
            success = {loadData()}
        )
    }

    fun updateUserProfile(image: String) = intent {
        launchOperation(
            operation = {
                userRepository.updateUserImage(
                    name = state.userName,
                    image = image,
                )
            },
            success = {loadData()}
        )
    }

}