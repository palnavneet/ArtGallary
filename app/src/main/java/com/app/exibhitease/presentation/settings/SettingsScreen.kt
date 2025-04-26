package com.app.exibhitease.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.exibhitease.R
import com.app.exibhitease.common.compose.UiButton
import com.app.exibhitease.presentation.settings.components.AiPlannerSettings
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

@Composable
fun SettingScreen(
    statusBarPadding: Dp,
    user : FirebaseUser?,
    onClick : () -> Unit
) {

    val poppins_semiBold = FontFamily(Font(R.font.poppins_semi_bold))
    val poppins_Bold = FontFamily(Font(R.font.poppins_bold))
    val poppins_regular = FontFamily(Font(R.font.poppins_regular))
    val poppins_light = FontFamily(Font(R.font.poppins_light))
    val poppins_medium = FontFamily(Font(R.font.poppins_medium))
    var darkMode by remember { mutableStateOf(false) }
    var notification by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = "Profile",
                color = colorResource(id = R.color.element_onboarding_text_first_color),
                fontSize = 35.sp,
                fontFamily = poppins_medium
            )
        }

        //TODO
        Spacer(modifier = Modifier.height(30.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shadowElevation = 1.dp,
            color = colorResource(id = R.color.system_color_white),
            shape = RoundedCornerShape(CornerSize(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(R.drawable.login),
                        null,
                        Modifier
                            .size(100.dp)
                            .padding(all = 5.dp),
                        tint = colorResource(id = R.color.element_icon_settings)
                    )

                    if (user == null){
                        UiButton(
                            modifier = Modifier
                                .padding(horizontal = 15.dp),
                            onClick = onClick,
                            text = "Register \uD83D\uDE42"
                        )
                    }else{
                        Text(
                            text = user.email ?: "no email",
                            fontFamily = poppins_Bold,
                            fontSize = 12.sp
                        )
                        Spacer(Modifier.width(50.dp))
                    }

                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.element_divider)
                )
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(all = 5.dp),
                        text = "Version 2.3.1 (Build 231045)",
                        fontSize = 15.sp,
                        fontFamily = poppins_Bold,
                        color = colorResource(id = R.color.element_icon_settings)
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.element_divider)
                )
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(all = 5.dp),
                        text = "High Quality (Default)",
                        fontSize = 15.sp,
                        fontFamily = poppins_Bold,
                        color = colorResource(id = R.color.element_icon_settings)
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.element_divider)
                )
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(all = 5.dp),
                        text = "Ultra Fast (Experimental)",
                        fontSize = 15.sp,
                        fontFamily = poppins_Bold,
                        color = colorResource(id = R.color.element_icon_settings)
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.element_divider)
                )
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(all = 5.dp),
                        text = "Smart Scheduling (Adaptive)",
                        fontSize = 15.sp,
                        fontFamily = poppins_Bold,
                        color = colorResource(id = R.color.element_icon_settings)
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.element_divider)
                )
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(all = 5.dp),
                        text = "Balanced (Default)",
                        fontSize = 15.sp,
                        fontFamily = poppins_Bold,
                        color = colorResource(id = R.color.element_icon_settings)
                    )
                }

            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shadowElevation = 1.dp,
            color = colorResource(id = R.color.system_color_white),
            shape = RoundedCornerShape(CornerSize(15.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
                    .clip(RoundedCornerShape(CornerSize(15.dp)))
            )
        }
        Spacer(modifier = Modifier.height(35.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shadowElevation = 1.dp,
            color = colorResource(id = R.color.system_color_white),
            shape = RoundedCornerShape(CornerSize(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                AiPlannerSettings(
                    isChecked = darkMode,
                    displayText = "Optimise",
                    displayIcon = painterResource(id = R.drawable.darkmode),
                    onCheckChange = {
                        darkMode = it
                    }
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = colorResource(id = R.color.element_divider)
                )
                AiPlannerSettings(
                    isChecked = notification,
                    displayText = "Notification",
                    displayIcon = painterResource(id = R.drawable.notification),
                    onCheckChange = {
                        notification = it
                    }
                )
            }
        }

    }
}