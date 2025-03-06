package com.example.visualnovelstudio

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CreateScenarioScreen() {
    var selectedBackgroundUri by remember { mutableStateOf<Uri?>(null) }
    var selectedCharacterUri by remember { mutableStateOf<Uri?>(null) }
    var characterPosition by remember { mutableStateOf(Pair(0f, 0f)) }
    
    val backgroundPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedBackgroundUri = uri
    }

    val characterPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedCharacterUri = uri
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val configuration = LocalConfiguration.current
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                // Background Image
                                selectedBackgroundUri?.let { uri ->
                                    AsyncImage(
                                        model = uri,
                                        contentDescription = "Background image",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp),
                                        contentScale = ContentScale.FillWidth
                                    )
                                }
                                
                                // Character Image (Draggable)
                                selectedCharacterUri?.let { uri ->
                                    AsyncImage(
                                        model = uri,
                                        contentDescription = "Character image",
                                        modifier = Modifier
                                            .size(200.dp)
                                            .offset(characterPosition.first.dp, characterPosition.second.dp)
                                            .zIndex(1f)
                                            .pointerInput(Unit) {
                                                detectDragGestures { change, dragAmount ->
                                                    change.consume()
                                                    characterPosition = Pair(
                                                        characterPosition.first + dragAmount.x,
                                                        characterPosition.second + dragAmount.y
                                                    )
                                                }
                                            },
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            }
                            
                            // Controls Column
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .width(200.dp)
                            ) {
                                Button(onClick = { backgroundPicker.launch("image/*") }) {
                                    Text("Select Background Image")
                                }
                                Button(onClick = { characterPicker.launch("image/*") }) {
                                    Text("Select Character Image")
                                }
                            }
                        }
                    }
                    else -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                // Background Image
                                selectedBackgroundUri?.let { uri ->
                                    AsyncImage(
                                        model = uri,
                                        contentDescription = "Background image",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp),
                                        contentScale = ContentScale.FillWidth
                                    )
                                }
                                
                                // Character Image (Draggable)
                                selectedCharacterUri?.let { uri ->
                                    AsyncImage(
                                        model = uri,
                                        contentDescription = "Character image",
                                        modifier = Modifier
                                            .size(200.dp)
                                            .offset(characterPosition.first.dp, characterPosition.second.dp)
                                            .zIndex(1f)
                                            .pointerInput(Unit) {
                                                detectDragGestures { change, dragAmount ->
                                                    change.consume()
                                                    characterPosition = Pair(
                                                        characterPosition.first + dragAmount.x,
                                                        characterPosition.second + dragAmount.y
                                                    )
                                                }
                                            },
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            }
                            
                            // Controls Row
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.padding(bottom = 16.dp)
                            ) {
                                Button(onClick = { backgroundPicker.launch("image/*") }) {
                                    Text("Select Background Image")
                                }
                                Button(onClick = { characterPicker.launch("image/*") }) {
                                    Text("Select Character Image")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
} 