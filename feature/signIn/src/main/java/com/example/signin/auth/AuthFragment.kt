package com.example.signin.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.core.utils.navigate
import com.example.signin.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {
    private lateinit var composeAuthScreen: ComposeView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).also {
            composeAuthScreen = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeAuthScreen.setContent {
            Column {
                Toolbar()
                MainScreen()
                EditTexts()
                SpannableText()
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT,
        ).show()
    }

    @Composable
    fun Toolbar() {
        Box(
            modifier = Modifier
                .background(Color(0xff66a636))
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                contentDescription = "Image back",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 24.dp),
            )
            Text(
                text = "Авторизация",
                fontSize = 21.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(resId = R.font.officinasansextraboldscc)),
            )
        }
    }

    @Preview
    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Для участия в мероприятиях войдите\nв приложение через социальые сети",
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                color = Color(0xb3000000),
                textAlign = TextAlign.Center,
            )
            Row(modifier = Modifier.padding(start = 80.dp, end = 80.dp, top = 20.dp)) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.vk_icon),
                    contentDescription = "vkIcon",
                )
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.facebook_icon),
                    contentDescription = "facebookIcon",
                    modifier = Modifier.padding(start = 40.dp),
                )
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.classmate_icon),
                    contentDescription = "odIcon",
                    modifier = Modifier.padding(start = 40.dp),
                )
            }
            Text(
                text = "Или авторизуйтесь через приложение",
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                color = Color(0xb3000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 40.dp),
            )
        }

        SideEffect {
            Log.e("Maksim", "The side effect")
        }
    }

    @Composable
    fun EditTexts() {
        val authState = remember { mutableStateOf(AuthComposableState("", "", false)) }

        val emailObservable: Observable<String> = Observable.just(authState.value.inputEmail)
        val passwordObservable: Observable<String> = Observable.just(authState.value.inputPassword)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "E-mail",
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
                color = Color(0x61000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp)
                    .align(Alignment.Start),
            )
            BasicTextField(
                value = authState.value.inputEmail,
                onValueChange = { it ->
                    val inputEmailData = authState.value.copy(inputEmail = it)
                    authState.value = inputEmailData
                    emailObservable
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .map { it }
                },
                modifier = Modifier
                    .padding(top = 8.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                decorationBox = { innerTextField ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row {
                            if (authState.value.inputEmail.isEmpty()) {
                                Text(
                                    text = "Введите e-mail",
                                    color = Color(0x61000000),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Default,
                                )
                            }
                            innerTextField()
                        }
                        Divider(color = Color(0x1e000000), modifier = Modifier.padding(top = 8.dp))
                    }
                },
            )
            Text(
                text = "Пароль",
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
                color = Color(0x61000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp)
                    .align(Alignment.Start),
            )
            BasicTextField(
                value = authState.value.inputPassword,
                onValueChange = {
                    val inputPasswordData = authState.value.copy(inputPassword = it)
                    authState.value = inputPasswordData
                    passwordObservable
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .map { it }
                },
                modifier = Modifier
                    .padding(top = 8.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                decorationBox = { innerTextField ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row {
                            if (authState.value.inputPassword.isEmpty()) {
                                Text(
                                    text = "Введите пароль",
                                    color = Color(0x61000000),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Default,
                                )
                            }
                            innerTextField()
                        }
                        Divider(color = Color(0x1e000000), modifier = Modifier.padding(top = 8.dp))
                    }
                },
            )
            Button(
                onClick = {
                    navigate(R.id.action_authFragment_to_containerFragment)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 26.dp)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff66a636)),
                shape = RoundedCornerShape(2.dp),
                enabled = authState.value.btnState,
            ) {
                Text(text = "ВОЙТИ", fontSize = 16.sp)
            }
        }

        Observable.combineLatest(
            emailObservable,
            passwordObservable,
            BiFunction<String, String, Boolean> { login, password ->
                login.length >= 6 && password.length >= 6
            },
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { canLogin ->
                val btnData = authState.value.copy(btnState = canLogin)
                authState.value = btnData
            }
    }

    @Composable
    fun SpannableText() {
        val annotatedForgetPassword = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xff66a636),
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                ),
            ) {
                append("Забыли пароль?")
            }
        }
        val annotatedRegistration = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xff66a636),
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                ),
            ) {
                append("Регистрация")
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
        ) {
            ClickableText(
                modifier = Modifier.align(Alignment.CenterStart),
                text = annotatedForgetPassword,
                onClick = {
                    showToast("Забыли пароль")
                },
            )

            ClickableText(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = annotatedRegistration,
                onClick = {
                    showToast("Регистрация")
                },
            )
        }
    }
}
