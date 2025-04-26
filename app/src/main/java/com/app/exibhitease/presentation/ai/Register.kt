package com.app.exibhitease.presentation.ai

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.exibhitease.R
import com.app.exibhitease.common.compose.UiButton
import com.app.exibhitease.utils.AuthManager
import com.app.exibhitease.utils.AuthRes
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit

@Composable
fun Register(
    auth: AuthManager,
    user: FirebaseUser?,
    navigation : NavHostController
) {


    if (user == null) {
        val system_black = Color(0xFF000000)
        val system_white = Color(0xFFFFFFFF)
        val color_bottomSheet = Color(0xFF17171C)
        val shapphire_blue = Color(0xFF4425FF)
        val poppins_semiBold = FontFamily(Font(R.font.poppins_semi_bold))
        val poppins_Bold = FontFamily(Font(R.font.poppins_bold))
        val poppins_regular = FontFamily(Font(R.font.poppins_regular))
        val poppins_light = FontFamily(Font(R.font.poppins_light))
        val poppins_medium = FontFamily(Font(R.font.poppins_medium))

        val context = LocalContext.current

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = system_black,
                            fontSize = 28.sp,
                            fontFamily = poppins_regular,
                        )
                    ) {
                        append("Create an account to")
                    }
                    append("\n")
                    withStyle(
                        style = SpanStyle(
                            brush = Brush.linearGradient(
                                listOf(
                                    Color(0xFFFA00FF),
                                    Color(0xFF5505FF),
                                    Color(0xFF05C3FF),
                                    Color(0xFFFF05C8)
                                )
                            ),
                            fontSize = 28.sp,
                            fontFamily = poppins_regular
                        )
                    ) {
                        append("Generate")
                    }
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = system_black,
                            fontSize = 28.sp,
                            fontFamily = poppins_regular
                        )
                    ) {
                        append("Images!")
                    }
                },
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )

            Spacer(modifier = Modifier.height(25.dp))

            TextField(
                label = {
                    Text(text = "Email")
                },
                value = email,
                onValueChange = {
                    email = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = system_white,
                    unfocusedTextColor = system_white,
                    disabledTextColor = system_white.copy(alpha = 0.3f),
                    cursorColor = shapphire_blue,
                    focusedIndicatorColor = shapphire_blue,
                    unfocusedIndicatorColor = system_white.copy(alpha = 0.3f),
                    disabledIndicatorColor = system_white.copy(alpha = 0.1f),
                    focusedContainerColor = color_bottomSheet,
                    unfocusedContainerColor = color_bottomSheet,
                    disabledContainerColor = color_bottomSheet.copy(alpha = 0.3f),
                    focusedLabelColor = shapphire_blue,
                    unfocusedLabelColor = system_white.copy(alpha = 0.5f)
                ),
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                label = {
                    Text(text = "Password")
                },
                value = password,
                onValueChange = {
                    password = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = system_white,
                    unfocusedTextColor = system_white,
                    disabledTextColor = system_white.copy(alpha = 0.3f),
                    cursorColor = shapphire_blue,
                    focusedIndicatorColor = shapphire_blue,
                    unfocusedIndicatorColor = system_white.copy(alpha = 0.3f),
                    disabledIndicatorColor = system_white.copy(alpha = 0.1f),
                    focusedContainerColor = color_bottomSheet,
                    unfocusedContainerColor = color_bottomSheet,
                    disabledContainerColor = color_bottomSheet.copy(alpha = 0.3f),
                    focusedLabelColor = shapphire_blue,
                    unfocusedLabelColor = system_white.copy(alpha = 0.5f)
                ),
            )
            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .padding(start = 40.dp, top = 0.dp, end = 40.dp, bottom = 0.dp)
            ) {
                UiButton(
                    onClick = {
                        scope.launch {
                            signUp(
                                email = email,
                                password = password,
                                auth = auth,
                                context = context,
                                navigation = navigation
                            )
                        }
                    },
                    text = "Register \uD83D\uDC64"
                )

            }
            Spacer(modifier = Modifier.height(40.dp))
            ClickableText(
                text = AnnotatedString("Do you already have an account? Login"),
                onClick = {
                },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFFFFFFFF)
                )
            )
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.onboarding_animation)
            )
            val animationState = animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                isPlaying = true,
            )

            LottieAnimation(
                modifier = Modifier.size(150.dp),
                composition = composition,
                progress = { animationState.progress },
            )

            Spacer(Modifier.size(10.dp))

            IconButton(
                modifier = Modifier.size(50.dp),
                onClick = {
                    navigation.popBackStack()
                },
                colors = IconButtonColors(
                    contentColor = Color(0xFFFFFFFF),
                    containerColor = Color(0xFF625b71),
                    disabledContentColor = Color(0xFFFFFFFF),
                    disabledContainerColor = Color(0xFFFFFFFF)
                )
            ) {
                Icon(
                    painterResource(R.drawable.back),
                    null,
                )
            }
        }
    }


}


private suspend fun signUp(
    email: String,
    password: String,
    auth: AuthManager,
    context: Context,
    navigation: NavHostController
) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
        when (val result = auth.createUserWithEmailAndPassword(email, password)) {
            is AuthRes.Error -> {
                Toast.makeText(context, "Error SignUp: ${result.errorMessage}", Toast.LENGTH_LONG)
                    .show()

            }


            is AuthRes.Success -> {
                navigation.popBackStack()
                Toast.makeText(context, "Registration Successful", Toast.LENGTH_LONG).show()
            }
        }
    } else {
        Toast.makeText(context, "Empty fields exist", Toast.LENGTH_LONG).show()
    }
}

@Composable
fun PartyPopperScreen() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = listOf(
                Party(
                    emitter = Emitter(duration = 1, TimeUnit.SECONDS).perSecond(100),
                    spread = 360,
                    position = Position.Relative(0.5, 0.3),
                    colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                    speed = 4f,
                    maxSpeed = 30f,
                    damping = 0.9f,
                    size = listOf(Size.SMALL, Size.LARGE)
                )
            )
        )
    }
}