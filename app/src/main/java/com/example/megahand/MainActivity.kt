package com.example.megahand

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.graphics.Color.alpha
import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil3.compose.AsyncImage
import com.example.megahand.ui.theme.MegaHandTheme
import androidx.compose.foundation.lazy.LazyRow as LazyRow


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MegaHandTheme {
                WorkScreen()
            }
        }
    }
}

// Компоненты


@Composable
fun Header(){
    Row(
        modifier = Modifier
            .height(62.dp)
            .fillMaxWidth()
            .background(color = Color.White)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {   //Главная
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 15.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.height(24.dp)
            )
            Box(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Главная",
                    color = Color(0xFF46423E),
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(listOf(Font(R.font.manrope_bold)))
                )
            }

        }
        // Строка бонусов
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFE7D52F),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(32.dp)
            ){
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.prize),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                )
            }
            Box(
                modifier = Modifier.align(Alignment.CenterVertically)
            ){
                Text(
                    text = "7,180 ₽ ",
                    color = Color.Black,
                    fontFamily = FontFamily(listOf(Font(R.font.manrope_bold))),
                    fontWeight = FontWeight.W600
                )
            }
        }
        // Строка иконок
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(end = 15.dp)
        ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.location),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.bell),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF046423E),
        elevation = 10.dp,
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        BottomNavigationItem (
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.home),""

                )
            },
            label = {
                Text(
                    text = "Главная",
                    fontSize = 10.sp
                )
            },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0

            })
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.card),
                    ""

                )
            },
            label = { Text(text = "Моя карта",fontSize = 10.sp) },
            selected = ( selectedIndex.value == 1),
            onClick = { selectedIndex.value = 1 }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.shop),
                    "",
                )
            },
            label = { Text(text = "Магазины",fontSize = 10.sp) },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            }
        )

        BottomNavigationItem(

            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.account),
                    "",
                )
            },
            label = { Text(text = "Профиль",fontSize = 10.sp) },
            selected = (selectedIndex.value == 3),
            onClick = {
                selectedIndex.value = 3
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.other),
                    "",
                )
            },
            label = { Text(text = "Другое",fontSize = 10.sp) },
            selected = (selectedIndex.value == 4),
            onClick = {
                selectedIndex.value = 4
            }
        )
    }
}
@Composable
fun Stories(){

    val story1 = rememberImagePainter(
        data = "https://s3-alpha-sig.figma.com/img/45c0/5ef9/36221f87dea04ad0c0420ac97e5fb1e3?Expires=1732492800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=hn2t0WYZ6UsFoC2q5BUxwfjWBEYI3i33k-17W36lXpc4I1j7XyJdV0MtmfBCSJkb9JG0hb6i6hqpdczewTbo2rHoW9ERfgLnBJFYHtmobS5IsRnQynyKtyjw3ag5qHC5xsmfqKZ1YcIYO5OPt-1u-Zv1khVbWvvLHTUNedtl1t~JVTa9pzVHSYu82qWwY-wmJwE0DdJ-UnC8Fd~cndNrdwgzSlyTrgiEJHIIjRjHFpj64wIHduPu1T0ANdiYOk3QCSrnZ0Y2s5qugiyuvKoG-eZ6Wtv1-UZ7NvmFeel1XtYuXkRd4c3Zee6CL-H88jFBc3DEFo9KWMOHyABBq4JXAA__",
        builder = {}
    )
    val story2 = rememberImagePainter(
        data = "https://s3-alpha-sig.figma.com/img/3a2c/4461/0e8d8d0e36b5cfe59efca342e9a4f35d?Expires=1732492800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=oOs0VzvoOlT6Zu3sCvFgQEDV~QLQe0g9jqGrqMA9dHjHR5bq6WoTjYBIq3CQ8VcjPYGi9AiHna-7ulcLcBRUBxj7P5TFglSPgl23MFC99tuwEqArNh0kD01-jNXoCPIHiopdcskGJy~p3Tsu9vjzo2IWLhIJnUu4fjBFXq6E~VH20rPKI9AIOFBR8OG0p-tWohz6v1N2lT5U3pjTFySIBPrBxNXceRgnF3HtbkVlr981vTD5jPPXCGCJ4TVSrXF7KDH1SIl3UrXKbTO2YKC-Kydq20CnLaSVPax07wXbIId8KZ7RRN0gYuNAA8dRx1UHHNSEsxAf~rkyPgWTKR4cZw__",
        builder = {}
    )
    val story3 = rememberImagePainter(
        data = "https://s3-alpha-sig.figma.com/img/cfbb/41bd/10acc06b68fd0bf9cefbbe9d87dd8d7b?Expires=1732492800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=jO-YpqGjYqsgqkPN53kA6qje7-3NbKw65~uM5avo0ZBIDAMA8R9xMZ3p44vFCs34IG1krv8f5XF0~UEHequ~d6Am-ijC2lA9NxuLsNPoPJtzYgA5Y4rW7TpmGqOuEztwngM9cKIiwNmfsHWmZNoHNUipETkuIsYRRoQObJidRSQcY9WoRLGLOsA-VtivRUPWacjGXzadpmO82lPPvluodWdJOJFU89UiWO~9Luf~MQhRAw6kPRmDc1hp0SkmEdwtM40v0LZJGYlPIa6XP1jFxV-bhmNc7rs3-rqdw5xm0Wff51SY9inQm4IBHAQ3KEdbI0ySTcCP4vM4eu90kaKuBQ__",
        builder = {}
    )
    val story4 = rememberImagePainter(
        data = "https://www.figma.com/file/3nc7iwpllameuJDuV5r4Ad/image/159f8d962114df75ea5c1e0a0b5b524d492cb623",
        builder = {}
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)

    ) {
        item {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(92.dp)

                ){
                    Image(painter = story1, contentDescription = null, contentScale = ContentScale.Crop)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = "О магазинах Волгограда",
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        )
                    }
                }
                //Жопа
                Box(
                    modifier = Modifier
                        .size(92.dp)

                ){
                    Image(painter = story2, contentDescription = null, contentScale = ContentScale.Crop)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = "Новые поступления",
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(88.dp)

                ){
                    Image(painter = story3, contentDescription = null, contentScale = ContentScale.Crop)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = "Одежда бренда Christian Dior",
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(92.dp)

                ){
                    Image(painter = story4, contentDescription = null, contentScale = ContentScale.Crop)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = "Купоны и скидки в приложении",
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun BonusCard(){
    val qr = rememberImagePainter(
        data = "https://s3-alpha-sig.figma.com/img/2719/c139/e208c90fb342991b1adb967066c3c2bb?Expires=1732492800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=dCYtZrh95-053e5JRCDAiPnlXdUL559CFCDI8mJ6bNUSsTZO-RkS2VLkPkvf1FNq7hYDKG4M2KWPS9OYW4t-mLSWKiLllDj6pFZzh3Hhvn~yZyGx-~YFMU~8VUxBrGy5RTlgv-JtTUKFxgsIHDYfyV6GI5YoLQ3lZuZTS0WqQSXB8ehbEOplpkBxxdi0zW2Gb08Wl3hKK9dLfqx1sm-uQR6qPlbz0V7oHicDh7AXXTb7a~TC6t2ytffoS021mC-CRv289XHholRzrcL7FVS3ZhHV-FghXTcG73VgfBLe4F7cIc6OnKCTPIyl3fCIQHJQtRN6MESdXuLIOg4lqVf25g__",
        builder = {}
    )
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .height(169.dp)
                .padding(
                    top = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(19.dp)
        ) {
            // Баллы
            Column(modifier = Modifier.padding(start = 15.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.prize),
                        contentDescription = null,
                        tint = Color(0xFFE7D52F),
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "7,180 ₽",
                        fontFamily = FontFamily(Font(R.font.manrope_bold)),
                        fontWeight = FontWeight.W700,
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        color = Color(0xff46423E)


                    )
                }
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        modifier = Modifier.alpha(0.4f),
                        text = "Накоплено баллов",
                        fontSize = 14.sp,
                        color = Color(0xFF46423E),
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily(Font(R.font.manrope_bold)),
                        lineHeight = 18.2.sp,
                    )
                }
            }
            Box(modifier = Modifier
                .height(1.dp)
                .width(212.dp)
                .background(Color(0xFF46423E)))

            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(start = 15.dp)
                ) {
                Column() {
                    Row() {
                        Text(
                            text = "4% кешбэка",
                            fontSize = 16.sp,
                            lineHeight = 19.2.sp,
                            fontFamily = FontFamily(Font(R.font.manrope_bold)),
                            color = Color(0xFF46423E),
                            fontWeight = FontWeight.W600
                        )
                    }
                    Row(verticalAlignment = Alignment.Top) {
                        Box(Modifier.width(157.dp)) {
                            Text(
                                modifier = Modifier.alpha(0.4f),
                                text = "Заполните профиль чтобы получить больше",
                                fontSize = 13.sp,
                                lineHeight = 18.2.sp,
                                fontFamily = FontFamily(Font(R.font.manrope_bold)),
                                color = Color(0xFF46423E),
                                fontWeight = FontWeight.W500,
                            )
                        }
                    }

                }
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.chevron),
                    contentDescription = null,
                    alignment = Alignment.TopEnd
                )
            }
        }
        // Qr
        Box(
            Modifier
                .background(Color(0xFFE7D52F))
                .width(150.dp)
                .height(169.dp),
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE7D52F),
                        shape = RoundedCornerShape(1.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                    Image(
                        painter = qr,
                        contentDescription = null,
                        modifier = Modifier.size(75.dp),
                        alignment = Alignment.Center
                    )
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.bb),
                    contentDescription = null
                )

            }

        }
    }
}
@Composable
fun Buttons(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
        
    ){
        // Помощь
        Row(
            modifier = Modifier
                .width(174.dp)
                .height(46.dp)
                .background(Color(0xFF46423E).copy(alpha = 0.05f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(61.dp)

            ) {

                    Text(
                        text = "Помощь",
                        fontFamily = FontFamily(Font(R.font.manrope_bold)),
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        color = Color(0xFF1C1C1C)
                    )

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.chevron),
                    contentDescription = null,
                    Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                )

            }
        }


        Row(
            modifier = Modifier
                .width(174.dp)
                .height(46.dp)
                .background(Color(0xFF46423E).copy(alpha = 0.05f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(45.dp)

            ) {

                Text(
                    text = "О сервисе",
                    fontFamily = FontFamily(Font(R.font.manrope_bold)),
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = Color(0xFF1C1C1C)
                )

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.chevron),
                    contentDescription = null,
                    Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                )

            }
        }
    }
}


// Экраны

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WorkScreen(){
    Scaffold(
        modifier = Modifier.padding(top = 20.dp),
        topBar = { Header()},
        bottomBar = {BottomBar()},
    ){
        MainScreen()
    }
}
@Composable
fun LoadingScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF46423E)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(169.dp)
        ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                contentDescription = null
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(28.dp),
                strokeWidth = 3.dp,
                color = Color(0xFFE7D52F)
            )
        }
    }
}
@Composable
fun MainScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 62.dp, start = 20.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        item{Stories()}
        item{BonusCard()}
        item{Buttons()}

    }
}


// Превью

@Preview(showBackground = true)
@Composable
fun ButPrev(){
    Buttons()
}


@Preview
@Composable
fun MenuPreview(){
    BottomBar()
}

@Preview
@Composable
fun HeaderPreview(){
    Header()

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingPreview(){
    LoadingScreen()
}

@Preview(showSystemUi = true)
@Composable
fun ScreenPreview(){
    WorkScreen()
}
