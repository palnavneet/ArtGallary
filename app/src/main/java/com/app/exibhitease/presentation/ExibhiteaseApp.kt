package com.app.exibhitease.presentation

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.exibhitease.presentation.home_Screen.CustomImageViewModel
import com.app.exibhitease.presentation.home_Screen.components.AppBottomBar
import com.app.exibhitease.presentation.navigation.ExibhiteaseTabs
import com.app.exibhitease.presentation.navigation.NavGraph
import com.app.exibhitease.presentation.navigation.Route
import com.app.exibhitease.presentation.settings_screen.SettingsViewModel
import com.app.exibhitease.ui.theme.shapphire_blue
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult

@Composable
fun ExibhiteaseApp(
    context : Context,
    firstLaunch: Boolean,
    settingsViewModel: SettingsViewModel,
    finishActivity: () -> Unit
) {
    val navController = rememberNavController()
    val tabs = remember {
        ExibhiteaseTabs.values()
    }
    val backStackState = navController.currentBackStackEntryAsState().value

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == ExibhiteaseTabs.Home.route ||
                backStackState?.destination?.route == ExibhiteaseTabs.Favourites.route ||
                backStackState?.destination?.route == ExibhiteaseTabs.User.route
    }
    val isFloatingVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == ExibhiteaseTabs.Home.route
    }

    val customImageViewModel: CustomImageViewModel = viewModel()


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0xFFf6f6f6),
        bottomBar = {
            if (isBottomBarVisible) {
                AppBottomBar(navController = navController, tabs = tabs)
            }
        },
        floatingActionButton = {
            if (isBottomBarVisible && isFloatingVisible) {
                ImagePickerFab(navController, customImageViewModel)
            }
        },
        contentWindowInsets = WindowInsets(top = 0.dp)
    ) { paddingValues ->
        NavGraph(
            firstLaunch = firstLaunch,
            context = context,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .navigationBarsPadding(),
            navController = navController,
            startDestination = if (firstLaunch) Route.OnBoardingScreen.route else Route.HomeScreen.route,
            finishActivity = finishActivity,
            settingsViewModel = settingsViewModel
        )

    }


}

@Composable
fun ImagePickerFab(navController: NavHostController, viewModel: CustomImageViewModel) {
    val activity = LocalContext.current as Activity
    var showDialog by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf(Uri.EMPTY) }

    // Scanner setup
    val options = GmsDocumentScannerOptions.Builder()
        .setGalleryImportAllowed(false)
        .setResultFormats(RESULT_FORMAT_JPEG, RESULT_FORMAT_PDF)
        .setPageLimit(1)
        .setScannerMode(SCANNER_MODE_FULL)
        .build()

    val scanner = GmsDocumentScanning.getClient(options)

    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val scanningResult = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
            scanningResult?.pages?.firstOrNull()?.imageUri?.let {
                selectedImageUri = it
                viewModel.selectedUri(it)
                navController.navigate(Route.CustomImage.route)
            }
        }
    }

    // Gallery picker
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            viewModel.selectedUri(it)
            navController.navigate(Route.CustomImage.route)
        }
    }

    // FAB
    FloatingActionButton(
        onClick = { showDialog = true },
        modifier = Modifier.size(70.dp),
        shape = RoundedCornerShape(size = 20.dp),
        containerColor = shapphire_blue,
        contentColor = Color.White
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "add image")
    }

    // Dialog to choose source
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Select Image Source") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    scanner.getStartScanIntent(activity)
                        .addOnSuccessListener { intentSender ->
                            scannerLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
                        }
                }) {
                    Text("Camera")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    galleryLauncher.launch("image/*")
                }) {
                    Text("Gallery")
                }
            }
        )
    }
}