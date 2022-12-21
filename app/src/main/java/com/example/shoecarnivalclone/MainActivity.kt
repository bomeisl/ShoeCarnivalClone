package com.example.shoecarnivalclone

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*
import com.example.shoecarnivalclone.ui.theme.ShoeCarnivalCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoeCarnivalCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenHost()
                }
            }
        }
    }
}


val ad_ids: List<Int> = listOf(
    R.drawable.topad, R.drawable.ad1, R.drawable.ad2,
    R.drawable.email300, R.drawable.email300,
    R.drawable.shoeperk_top
)

@Composable
fun Advert(ad_id: Int) {
    Image(
        painter = painterResource(id = ad_id),
        contentDescription = ad_id.toString()
    )
}

@Composable
fun Adverts(items: List<Int>) {
      LazyColumn(modifier = Modifier
          .padding(
              top = 50.dp, start = 20.dp, end = 20.dp,
              bottom = 50.dp
          )
          .fillMaxSize(),
          horizontalAlignment = Alignment.CenterHorizontally) {

          item{Spacer(modifier = Modifier.height(200.dp))}

            item{
                ad_ids.forEach { item ->
                    Row() {
                        Advert(ad_id = item)
                    }
                    Row() {
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }

          item {

          }


        }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHost() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier
            .padding(0.dp)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column() {
                Row {
                    UpperTopBar()
                }
                Row() {
                    Divider(Modifier.height(0.5.dp))
                }
                Row() {
                    LowerTopBar()
                }
                Row() {
                    Divider(Modifier.height(0.5.dp))
                }
                Row() {
                    LowerLowerTopBar(scrollBehavior)
                }
                Row() {
                    Column() {
                        Divider(
                            Modifier
                                .height(1.dp)
                                .width(100.dp), color = Color.Red)
                    }
                    Column() {
                        Divider(
                            Modifier
                                .height(1.dp)
                                .width(100.dp), color = Color.Yellow)
                    }
                    Column() {
                        Divider(
                            Modifier
                                .height(1.dp)
                                .width(100.dp), color = Color.Green)
                    }
                    Column() {
                        Divider(
                            Modifier
                                .height(1.dp)
                                .width(100.dp), color = Color.Blue)
                    }
                }
            }

        },
        content = {
            Box{
                Adverts(ad_ids)
            }

        },
        bottomBar = {
            Box() {
                Row() {
                    BottomAppBar()
                }

            }

        },
        floatingActionButton = {
            NeedHelp()
        }

    )
}

@Composable
fun NeedHelp() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .background(Color.Blue, shape = RoundedCornerShape(40.dp))
            .width(150.dp),
        containerColor = Color.Blue
    ) {
        Row() {
            Column() {
                Image(painter = painterResource(id = R.drawable.ic_baseline_chat_bubble_outline_24),
                    contentDescription = "chat",
                    colorFilter = ColorFilter.tint(Color.White))
            }
            Spacer(Modifier.width(15.dp))
            Column() {
                Text(text = "Need Help?", color = Color.White)

            }
        }
    }
}

data class IconContainer(
    val id: Int,
    val contentDes: String,
    val color: ColorFilter = ColorFilter.tint(Color.Black),
    val size: Dp = 35.dp
)

val topIcons = listOf<IconContainer>(
    IconContainer(R.drawable.menu, "menu", ColorFilter.tint(Color.Black)),
    IconContainer(R.drawable.search, "search", ColorFilter.tint(Color.Red)),
    IconContainer(R.drawable.pngkey_com_carnival_logo_png_3588299, "logo", size = 100.dp),
    IconContainer(R.drawable.user, "user", ColorFilter.tint(Color.Blue)),
    IconContainer(R.drawable.shop, "shop", ColorFilter.tint(Color.Red)),
    )

@Composable
fun TopIcon(id: Int, contentDes: String, color: ColorFilter, size: Dp) {
    Image(
        painterResource(id = id),
        contentDescription = contentDes,
        Modifier.size(size),
        colorFilter = color,
    )
}

data class CarouselSlide(
    val text: String,
    val size: TextUnit = 13.sp,
    val color: Color = Color.Black,
    val style: TextDecoration = TextDecoration.None,
    val image_id: Int,
    val fontWeight: FontWeight = FontWeight.Light
)

val CarouselSlides: List<CarouselSlide> = listOf(
    CarouselSlide("Join Shoe Perks Today!", color = Color.Blue,
                style = TextDecoration.Underline, image_id = R.drawable.menu),
    CarouselSlide("Shoe Perks Members get ", image_id = R.drawable.home),
    CarouselSlide("Free Shipping!", image_id = R.drawable.home,
                  fontWeight = FontWeight.Bold),
    CarouselSlide("Buy Now, Pay Later with ", image_id= R.drawable.cart),
    CarouselSlide("Klarna!", image_id= R.drawable.cart, color = Color.Blue,
                style = TextDecoration.Underline)
)


@Composable
fun FirstSlide() {
    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Column() {
            Image(painter = painterResource(id = CarouselSlides[0].image_id),
            contentDescription = "logo")
        }
        Column() {
            Text(text = CarouselSlides[0].text, fontSize = CarouselSlides[0].size,
                color = CarouselSlides[0].color, textDecoration = CarouselSlides[0].style)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LowerLowerTopBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = { FirstSlide() },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun LowerTopBar() {
    NavigationBar{
        topIcons.forEach { item ->
            NavigationBarItem(modifier = Modifier
                .fillMaxHeight(),
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        TopIcon(item.id, item.contentDes, item.color, item.size)
                    }
                }
            )
        }
    }
}

@Composable
fun UpperTopBar() {
    NavigationBar{
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Image(
                    painterResource(id = topIcons[2].id),
                    contentDescription = topIcons[2].contentDes,
                    Modifier.size(175.dp)
                )
            }
        )
    }
}

val bottomIcons: List<IconContainer> = listOf(
    IconContainer(R.drawable.home, contentDes = "Home"),
    IconContainer(R.drawable.loc, contentDes = "Stores"),
    IconContainer(R.drawable.cart, contentDes = "Shop"),
    IconContainer(R.drawable.price, contentDes = "Coupons"),
    IconContainer(R.drawable.card, contentDes = "Perks")
)

@Composable
fun BottomIcons(id: Int, contentDes: String, size: Dp) {
    var tint: Color by remember { mutableStateOf(Color.Black) }
    Column() {
        Row() {
            Icon(
                painter = painterResource(id = id),
                contentDescription = contentDes,
                modifier = Modifier
                    .clickable {
                        if (tint == Color.Black) {
                            tint = Color.Blue
                        } else {
                            tint = Color.Black
                        }
                    }
                    .size(size)
                    .padding(vertical = 0.dp),
                tint = tint
            )
        }
        Row() {
            Text(text = contentDes,
                color = tint,
                fontSize = 14.sp)
        }

    }


}



@Composable
fun BottomAppBar() {
    NavigationBar(modifier = Modifier
        .padding(0.dp)
        .height(100.dp)) {
        bottomIcons.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { /* TODO */ },
                    icon = {
                        val interactionSource = remember { MutableInteractionSource() }
                        //We need the whole UI element to be a ToggleButton for when
                        //we implement navigation between screens
                            //The color change animation will be handled inside this composable
                            // on click events
                            BottomIcons(item.id, item.contentDes, item.size)
                    }
            )
        }
    }
}




