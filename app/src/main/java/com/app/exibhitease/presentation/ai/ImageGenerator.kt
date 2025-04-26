package com.app.exibhitease.presentation.ai

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.app.exibhitease.ui.theme.poppins_Bold
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.vertexai.type.PublicPreviewAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File

@OptIn(PublicPreviewAPI::class)
@Composable
fun ImageGenerator(
    user: FirebaseUser?,
    onClick: (Uri) -> Unit,
    prompt: String
) {
    var prompt by remember { mutableStateOf(prompt) }
    var imageUrls by rememberSaveable { mutableStateOf(listOf<String>()) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("https://image.pollinations.ai/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
    val api = remember { retrofit.create(PollinationsApi::class.java) }

    LaunchedEffect(prompt) {
        try {
            val response = api.getImageUrl(prompt)
            if (response.isSuccessful) {
                imageUrls = imageUrls + response.raw().request.url.toString()
            }
        } catch (e: Exception) {
            Log.e("PollinationScreen", "Failed to load image", e)
        }
    }


    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(user) {
        if (user == null) {
            while (true) {
                snackbarHostState.showSnackbar(
                    message = "Register to generate images!",
                    duration = SnackbarDuration.Indefinite
                )
                delay(5000) // Reshow every 5 seconds
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Text(
                text = "Crafting your image... good things take a little time!\" ⏳",
                color = Color(0xFFFCFCFC),
                fontSize = 25.sp,
                textAlign = TextAlign.Start,
                fontFamily = poppins_Bold,
                modifier = Modifier.padding(start = 10.dp).statusBarsPadding()
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        bottomBar = {
            BottomPanel(
                onSend = {
                    prompt = it
                },
                user = user
            )
        },
        containerColor = Color(0xFF17171C)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            items(imageUrls.toList()) { image ->
                Column(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                ) {
                    Image(

                        painter = rememberAsyncImagePainter(image),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                scope.launch {
                                    val bitmap = loadBitmapFromUrl(context, image)
                                    bitmap?.let {
                                        val uri = bitmapToUri(context, it)
                                        onClick(uri)
                                    }

                                }
                            }
                    )
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }

}

suspend fun loadBitmapFromUrl(context: Context, imageUrl: String): Bitmap? {
    return try {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false)  // Disabling hardware bitmaps for easier manipulation
            .build()

        val result = loader.execute(request) as? SuccessResult
        val drawable = result?.drawable

        if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else {
            Log.e("loadBitmapFromUrl", "Failed to load Bitmap, drawable is not a BitmapDrawable")
            null
        }
    } catch (e: Exception) {
        Log.e("loadBitmapFromUrl", "Error loading image from URL", e)
        null
    }
}

suspend fun bitmapToUri(context: Context, bitmap: Bitmap): Uri = withContext(Dispatchers.IO) {
    // Create a temporary file in the cache directory
    val file = File(context.cacheDir, "image_${System.currentTimeMillis()}.png")

    // Write the bitmap to the file
    file.outputStream().use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }

    // Return a content URI using FileProvider
    return@withContext FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",  // authority must match manifest
        file
    )
}

@Composable
private fun BottomPanel(
    onSend: (String) -> Unit,
    user: FirebaseUser?
) {

    var text by remember {
        mutableStateOf("")
    }
    Row(Modifier.padding(8.dp)) {
        TextField(
            value = text,
            label = { Text("Prompt") },
            onValueChange = { text = it },
            modifier = Modifier
                .weight(0.8f)
                .padding(end = 16.dp)
                .align(Alignment.CenterVertically)
        )
        Button(
            onClick = {
                onSend(text)
                text = ""
            },
            shape = RoundedCornerShape(10.dp),
            enabled = user != null,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4425FF))
        ) {
            Text(text = "Send", color = Color(0xFFFCFCFC))
        }
    }
}

interface PollinationsApi {
    @GET("prompt/{prompt}")
    suspend fun getImageUrl(@Path("prompt") prompt: String): Response<ResponseBody>
}