package com.pluspark.android_compose_step

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    CompositionLocals()
                }
            }
        }
    }
}

var color by mutableStateOf(Color.Red)

private var outsideStatic = 0
private var centerStatic = 0
private var insideStatic = 0

private var outsideDynamic = 0
private var centerDynamic = 0
private var insideDynamic = 0

/** 자주 변경되지 않는 상태를 저장할 때 사용하면 좋음  -> 상태 변경 시 할당된 노드를 모두 재구성 해야함*/
private val ColorComposableLocalStatic = staticCompositionLocalOf<Color> { error("기본값 없음") }
/** 현재 상태에서 접근하는 컴포저블에 대해서만 재구성을 수행 -< 변경이 잦은 상태에서만 사용해야 함 */
private val ColorComposableLocalDynamic = compositionLocalOf<Color> { error("기본값 없음") }

@Composable
fun CompositionLocals() {
    Column {
        Text("staticCompositionLocalOf")
        CompositionLocalProvider(ColorComposableLocalStatic provides color) {
            outsideStatic++
            MyBox(color = Color.Yellow, outsideStatic, centerStatic, insideStatic) {
                centerStatic++
                MyBox(color = ColorComposableLocalStatic.current, outsideStatic, centerStatic, insideStatic) {
                    insideStatic++
                    MyBox(color = Color.Yellow, outsideStatic, centerStatic, insideStatic) {
                    }
                }
            }
        }

        Text("compositionLocalOf")
        CompositionLocalProvider(ColorComposableLocalDynamic provides color) {
            outsideDynamic++
            MyBox(color = Color.Yellow, outsideDynamic, centerDynamic, insideDynamic) {
                centerDynamic++
                MyBox(color = ColorComposableLocalDynamic.current, outsideDynamic, centerDynamic, insideDynamic) {
                    insideDynamic++
                    MyBox(color = Color.Yellow, outsideDynamic, centerDynamic, insideDynamic) {
                    }
                }
            }
        }

        Button(onClick = {
            color = if (color == Color.Green) {
                Color.Red
            } else {
                Color.Green
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Click Me")
        }
    }

}

@Composable
fun MyBox(color: Color,
          outside: Int,
          center: Int,
          inside: Int,
          content: @Composable BoxScope.() -> Unit) {
    Column (Modifier.background(color)) {
        Text("outside = $outside, center = $center, inside = $inside")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            content = content
        )
    }
}

