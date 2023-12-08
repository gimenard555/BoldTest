package com.jimenard.weatherapp.ui.screen

import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jimenard.weatherapp.R

@Composable
fun ContentWithLoader(
    modifier: Modifier,
    loaderState: MutableState<Int>,
    content: @Composable (modifier: Modifier) -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_weather_anim))
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (loaderRef, loader, contentRef) = createRefs()
        content(
            Modifier.constrainAs(contentRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
        )
        if (loaderState.value == View.VISIBLE) {
            Surface(
                color = Color.Gray.copy(0.5f),
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(loaderRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
            ) {}
            LottieAnimation(
                modifier = modifier
                    .constrainAs(loader) {
                        centerHorizontallyTo(parent)
                        centerVerticallyTo(parent)
                    },
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        }
    }
}
