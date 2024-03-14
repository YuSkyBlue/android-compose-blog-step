package com.pluspark.android_compose_step

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pluspark.android_compose_step.ui.theme.AndroidcomposestepTheme

val ColorCompositionLocal = staticCompositionLocalOf {
    Color.Blue // 기본값을 정의
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidcomposestepTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                        Composable1()
//                        Composable2()
//                        Composable3()
//                        Composable4()
//                        Composable5()
//                        Composable6()


                }
            }
        }
    }
}

@Composable
fun Composable1() {
    Column(Modifier.fillMaxSize()){
        Text(
            modifier = Modifier.background(color = ColorCompositionLocal.current),
            text = "Composable1"
        )
        CompositionLocalProvider(ColorCompositionLocal.provides(Color.Cyan)) {
            Composable2()
        }
        CompositionLocalProvider(ColorCompositionLocal.provides(Color.Red)) {
            Composable4()
        }
    }

}

@Composable
fun Composable2() {
    Text(
        modifier = Modifier.background(color = ColorCompositionLocal.current),
        text = "Composable2"
    )
    Composable3()
}

@Composable
fun Composable3() {
    Text(
        modifier = Modifier.background(color = ColorCompositionLocal.current),
        text = "Composable3"
    )

}

@Composable
fun Composable4() {
    Text(
        modifier = Modifier.background(color = ColorCompositionLocal.current),
        text = "Composable4"
    )

    CompositionLocalProvider(ColorCompositionLocal.provides(Color.Green)) {
        Composable5()
    }
    Composable6()
}
@Composable
fun Composable5() {
    Text(
        modifier = Modifier.background(color = ColorCompositionLocal.current),
        text = "Composable5"
    )
}
@Composable
fun Composable6() {
    Text(
        modifier = Modifier.background(color = ColorCompositionLocal.current),
        text = "Composable6"
    )
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AndroidcomposestepTheme {
//    }
//}