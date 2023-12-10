package com.example.happibee.Presentation.Apiarios.Views

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import com.example.happibee.R
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationClickListener
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import dagger.hilt.android.qualifiers.ApplicationContext


@OptIn(MapboxExperimental::class)
@Composable
fun MapBoxScreen(navController: NavHostController) {
    lateinit var mapView: MapView

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapInitOptionsFactory = { context ->
            MapInitOptions(
                context = context,
                styleUri = Style.OUTDOORS,
                cameraOptions = CameraOptions.Builder()
                    .center(Point.fromLngLat(-8.1699, 38.9384))
                    .zoom(2.0)
                    .build()
            )
        }
    ){
        AddPointer(Point.fromLngLat(-8.1699, 38.9384))
    }
}

@OptIn(MapboxExperimental::class)
@Composable
fun AddPointer(point:Point){
    val drawable = ResourcesCompat.getDrawable(
        LocalContext.current.resources,
        R.drawable.ic_launcher_foreground,
        null
    )
    val bitmap = drawable!!.toBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    PointAnnotation(
        iconImageBitmap = bitmap,
        iconSize = 0.5,
        point = point,
        onClick = {
            true
        }
    )
}