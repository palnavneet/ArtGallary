package com.app.exibhitease.presentation.ar_screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.exibhitease.R
import com.app.exibhitease.presentation.ai.Summarize
import com.app.exibhitease.presentation.data.repository.Art
import com.app.exibhitease.presentation.viewmodel.SharedViewModel
import com.app.exibhitease.ui.theme.poppins_semiBold
import com.app.exibhitease.ui.theme.system_black
import com.google.android.filament.Texture
import com.google.android.filament.TextureSampler
import com.google.android.filament.android.TextureHelper
import com.google.ar.core.Config
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import dev.romainguy.kotlin.math.Float3
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.model.model
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ArScreen(
    viewModel: SharedViewModel
) {

    val art by viewModel.selectedArt.collectAsState()
    art?.let {
        ArDetailScreen(it)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArDetailScreen(
    art: Art
) {

    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(skipPartiallyExpanded = true, density = Density(density = 1f))
    )
    val isDimVisible by remember {
        derivedStateOf {
            sheetState.bottomSheetState.targetValue == SheetValue.Expanded
        }
    }

    var isVisible by remember { mutableStateOf(true) }
    val nodes = remember {
        mutableListOf<ArNode>()
    }
    val modelNode = remember {
        mutableStateOf<ArModelNode?>(null)
    }
    val placeModelButton = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val uri = art.uri

    Summarize(
        state = sheetState,
        onClick = {

        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                ARScene(
                    modifier = Modifier.fillMaxSize(),
                    nodes = nodes,
                    planeRenderer = true,
                    onCreate = { arSceneView ->
                        arSceneView.lightEstimationMode =  Config.LightEstimationMode.ENVIRONMENTAL_HDR

                        arSceneView.planeRenderer.isShadowReceiver = false
                        modelNode.value = ArModelNode(arSceneView.engine, PlacementMode.INSTANT).apply {

                            loadModelGlbAsync(
                                glbFileLocation = "models/${art.model}.glb",
                                scaleToUnits = 0.8f
                            ) { modelInstance ->



                                uriToBitmap(context, uri)?.let { textureBitmap ->
                                    val texture = Texture.Builder()
                                        .width(textureBitmap.width)
                                        .height(textureBitmap.height)
                                        .sampler(Texture.Sampler.SAMPLER_2D)
                                        .format(Texture.InternalFormat.RGBA8)
                                        .build(arSceneView.engine)
                                    TextureHelper.setBitmap(arSceneView.engine, texture, 0, textureBitmap)
                                    val sampler = TextureSampler(
                                        TextureSampler.MinFilter.LINEAR,
                                        TextureSampler.MagFilter.LINEAR,
                                        TextureSampler.WrapMode.REPEAT
                                    )
                                    modelInstance.materialInstances.forEach { matInstance ->
                                        Log.d("Pokemon", matInstance.name)
                                        matInstance.setParameter("baseColorMap", texture, sampler)

                                    }
                                }

                            }
                            onAnchorChanged = {
                                placeModelButton.value = !isAnchored
                            }
                            onHitResult = { node, hitResult ->
                                placeModelButton.value = node.isTracking
                            }

                        }
                        nodes.add(modelNode.value!!)
                    },
                    onSessionCreate = {
                        planeRenderer.isVisible = false
                    }
                )

                if (isVisible) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp)
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Column {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        sheetState.bottomSheetState.show()
                                    }
                                },
                                modifier = Modifier
                                    .size(30.dp),
                                colors = IconButtonColors(
                                    contentColor = Color.White,
                                    containerColor = Color.DarkGray,
                                    disabledContainerColor = Color.DarkGray,
                                    disabledContentColor = Color.White
                                )
                            ) {
                                Icon(
                                    painterResource(R.drawable.ai), null
                                )
                            }
                            Spacer(modifier = Modifier.size(10.dp))
                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier
                                    .size(30.dp),
                                colors = IconButtonColors(
                                    contentColor = Color.White,
                                    containerColor = Color.DarkGray,
                                    disabledContainerColor = Color.DarkGray,
                                    disabledContentColor = Color.White
                                )
                            ) {
                                Icon(
                                    painterResource(R.drawable.settings), null
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(15.dp))
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .background(Color.White, shape = RoundedCornerShape(15.dp))
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = art.fullName,
                                fontSize = 18.sp,
                                fontFamily = poppins_semiBold,
                                color = system_black,
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            IconButton(
                                onClick = {
                                    isVisible = false
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_circle),
                                    contentDescription = null,
                                    tint = Color(0xFF9CA1A7)
                                )
                            }
                        }

                    }
                }

            }
        },
        art = art
    )

}

fun uriToBitmap(context: Context, uri: Uri?): Bitmap? {
    return uri?.let {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}





