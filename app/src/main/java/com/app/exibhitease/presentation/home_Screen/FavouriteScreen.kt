package com.app.exibhitease.presentation.home_Screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.packInts
import com.app.exibhitease.R
import com.app.exibhitease.presentation.data.repository.getHArts
import com.app.exibhitease.presentation.data.repository.getVArts
import com.app.exibhitease.ui.theme.poppins_Bold
import com.app.exibhitease.ui.theme.poppins_medium
import com.app.exibhitease.ui.theme.poppins_regular
import com.app.exibhitease.ui.theme.poppins_semiBold
import com.app.exibhitease.ui.theme.shapphire_blue
import com.app.exibhitease.ui.theme.system_black
import kotlin.math.absoluteValue

@Composable
fun FavouriteScreen(
    onClick : (Uri) -> Unit
){

    val lts = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
        R.drawable.image9
    )
    val pagerState = rememberPagerState(initialPage = 0){
        lts.size
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            Text(
                text = "Favourites",
                fontSize = 25.sp,
                fontFamily = poppins_regular,
                color = system_black
            )
            Icon(
                painter = painterResource(id = R.drawable.heart_filled),
                contentDescription = "null",
                tint = Color(0xFFD30303),
                modifier = Modifier
                    .size(30.dp)
            )

        }

        HorizontalPager(state = pagerState, contentPadding = PaddingValues(50.dp)) { index : Int ->
            ImageContent(lts,index,pagerState){
                onClick(it)
            }
        }

    }
}

@Composable
fun ImageContent(lts : List<Int>, index: Int, pagerState: PagerState,onClick : (Uri) -> Unit,) {
    val context = LocalContext.current
    val uri = Uri.parse("android.resource://${context.packageName}/${lts[index]}")

    val pagerOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .clickable{
                onClick(uri)
            }
            .padding(2.dp)
            .graphicsLayer {
                lerp(
                    start = 0.85f.dp,
                    stop = 1f.dp,
                    fraction = 1f - pagerOffset.absoluteValue.coerceIn(0f, 1f)
                ).also {
                    scaleX = it.value
                    scaleY = it.value
                }
            }
    ){
        Image(
            painter = painterResource(id = lts[index]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
