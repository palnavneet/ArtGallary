package com.app.exibhitease.presentation.data.repository

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import com.app.exibhitease.R


fun getHArts(context: Context) : List<Art>{
    return listOf(
        Art(
            id = R.drawable.licensed_image,
            name = "Mona Lisa",
            fullName = "Mona Lisa",
            rating = "5",
            model = "street",
            uri =getResourceUri(R.drawable.licensed_image,context)
        ),
        Art(
            id = R.drawable.man_horse,
            name = "NCTA",
            fullName = "Napoleon Crossing the Alps",
            rating = "4.2",
            model = "street",
            uri =getResourceUri(R.drawable.man_horse,context)

        ),
        Art(
            id = R.drawable.vincent,
            name = "Café Terrace",
            fullName = "Cafe Terrace at Night ",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.vincent,context)
        ),
        Art(
            id = R.drawable.i1,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i1,context)
        ),
        Art(
            id = R.drawable.i2,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i2,context)
        ),
        Art(
            id = R.drawable.i3,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i3,context)
        ),
        Art(
            id = R.drawable.i4,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i4,context)
        ),
        Art(
            id = R.drawable.i5,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i5,context)
        ),
        Art(
            id = R.drawable.i6,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i6,context)
        ),
        Art(
            id = R.drawable.i7,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i7,context)
        ),
        Art(
            id = R.drawable.i8,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i8,context)
        ),
        Art(
            id = R.drawable.i9,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i9,context)
        ),
        Art(
            id = R.drawable.i10,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i10,context)
        ),
        Art(
            id = R.drawable.i11,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i11,context)
        ),
        Art(
            id = R.drawable.i12,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i12,context)
        ),
        Art(
            id = R.drawable.i13,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i13,context)
        ),
        Art(
            id = R.drawable.i14,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i14,context)
        ),
        Art(
            id = R.drawable.i15,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i15,context)
        ),
        Art(
            id = R.drawable.i16,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i16,context)
        ),
        Art(
            id = R.drawable.i17,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i17,context)
        ),
        Art(
            id = R.drawable.i18,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i18,context)
        ),
        Art(
            id = R.drawable.i19,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i19,context)
        ),
        Art(
            id = R.drawable.i20,
            name = "Café Terrace",
            fullName = "Popular",
            rating = "4.1",
            model = "street",
            uri =getResourceUri(R.drawable.i20,context)
        ),
        Art(
            id = R.drawable.i27,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i27,context)
        ),
        Art(
            id = R.drawable.i28,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i28,context)
        ),
        Art(
            id = R.drawable.i29,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i29,context)
        ),
        Art(
            id = R.drawable.i30,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i30,context)
        ),

    )

}

fun getVArts(context: Context) : List<Art>{
    return listOf(
        Art(
            id = R.drawable.street,
            name = "PSRD",
            fullName = "Paris Street, Rainy Day",
            rating = "4.6",
            model = "street",
            uri =getResourceUri(R.drawable.street,context)
        ),
        Art(
            id = R.drawable.water,
            name = "TGWK",
            fullName = "The Great Wave off Kanagawa",
            rating = "4.5",
            model = "street",
            uri =getResourceUri(R.drawable.water,context)
        ),
        Art(
            id = R.drawable.monet_impression_sunrise,
            name = "Sunrise",
            fullName = "Impression, Sunrise",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.monet_impression_sunrise,context)
        ),
        Art(
            id = R.drawable.i21,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i21,context)
        ),
        Art(
            id = R.drawable.i22,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i22,context)
        ),
        Art(
            id = R.drawable.i23,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i23,context)
        ),
        Art(
            id = R.drawable.i24,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i24,context)
        ),
        Art(
            id = R.drawable.i25,
            name = "Sunrise",
            fullName = "Recommended",
            rating = "4.0",
            model = "street",
            uri =getResourceUri(R.drawable.i25,context)
        ),

    )

}

data class Art(
    val id : Int,
    val name : String,
    val fullName : String,
    val rating : String,
    val model : String,
    val uri : Uri? = null
)

fun getResourceUri(@DrawableRes resId: Int, context: Context): Uri {
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(context.resources.getResourcePackageName(resId))
        .appendPath(context.resources.getResourceTypeName(resId))
        .appendPath(context.resources.getResourceEntryName(resId))
        .build()
}

