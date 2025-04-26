package com.app.exibhitease.presentation.home_Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.exibhitease.R
import com.app.exibhitease.presentation.data.repository.Art
import com.app.exibhitease.presentation.data.repository.getHArts
import com.app.exibhitease.presentation.data.repository.getVArts
import com.app.exibhitease.ui.theme.poppins_light
import com.app.exibhitease.ui.theme.poppins_medium
import com.app.exibhitease.ui.theme.poppins_regular
import com.app.exibhitease.ui.theme.poppins_semiBold
import com.app.exibhitease.ui.theme.shapphire_blue
import com.app.exibhitease.ui.theme.system_black
import com.app.exibhitease.ui.theme.system_white
import kotlinx.coroutines.delay
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit


@Composable
fun HomeScreen(
    firstLaunch: Boolean,
    onclick: (art: Art) -> Unit,
    onGenerateClick: () -> Unit,
    onPromptClick: (String) -> Unit
) {
    if (firstLaunch) {
        PartyPopperScreen()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopComponent(onGenerateClick = onGenerateClick){
            onPromptClick(it)
        }
        BottomComponent(
            onClick = {
                onclick(it)
            }
        )
    }
}

@Composable
fun BottomComponent(
    modifier: Modifier = Modifier,
    onClick: (art: Art) -> Unit
) {
    val context = LocalContext.current
    val hArts = getHArts(context)
    val vArts = getVArts(context)
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight()
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.95f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Popular",
                    fontSize = 20.sp,
                    color = system_black,
                    fontFamily = poppins_semiBold
                )
                Text(
                    text = "See all",
                    fontSize = 14.sp,
                    color = shapphire_blue,
                    fontFamily = poppins_regular,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                hArts.forEach {
                    HImageCard(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        image = it.id,
                        name = it.name,
                        rating = it.rating,
                        onClick = {
                            onClick(it)
                        }
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.95f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Recommended",
                    fontSize = 20.sp,
                    color = system_black,
                    fontFamily = poppins_semiBold
                )
            }
        }
        items(vArts) {
            VImageCard(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                image = it.id,
                name = it.name,
                rating = it.rating,
                onClick = {
                    onClick(it)
                }
            )
        }
    }
}


@Composable
fun HImageCard(
    modifier: Modifier = Modifier,
    image: Int,
    name: String,
    rating: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(180.dp)
            .height(240.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }


}

@Composable
fun VImageCard(
    modifier: Modifier = Modifier,
    image: Int,
    name: String,
    rating: String,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }

}


@Composable
fun TopComponent(
    modifier: Modifier = Modifier,
    onGenerateClick: () -> Unit,
    onPromptClick : (String) -> Unit
) {
    val prompts = listOf(
        "Sunset over a calm lake and mountains",
        "Flying cars in a neon-lit city",
        "Snowy cabin with smoke rising",
        "Dragon soaring above a forest",
        "Medieval market full of merchants",
        "Glowing mushrooms in a magical forest",
        "Ancient books in a grand library",
        "Coffee steaming on a wooden table",
        "Colorful coral reefs under water",
        "Kitten playing with yarn in sunlight",
        "Castle atop a misty mountain",
        "Futuristic lab with advanced equipment",
        "Palm trees on a serene beach",
        "Exotic animals in a dense jungle",
        "Robot casting a shadow over a city",
        "Stars swirling in a deep galaxy",
        "Superhero soaring with sparks behind",
        "Autumn leaves drifting in a park",
        "Unicorn-pegasus hybrid in a meadow",
        "Golden gears on an old clock",
        "Waterfall into a crystal-clear lake",
        "Butterflies fluttering around flowers",
        "Fog in an eerie forest",
        "Spaceship passing through asteroids",
        "Floating rocks in a desert",
        "Astronaut cat floating in space",
        "Cherry blossoms at a Japanese temple",
        "Steampunk airship over Victorian streets",
        "Sports car speeding down a highway",
        "Mountain landscape with a river",
        "Wizard casting a spell in darkness",
        "Carnival with bright lights and costumes",
        "Owl perched under the stars in snow",
        "Cactus in a vast desert",
        "Cave entrance behind a waterfall",
        "Quiet park with families and children",
        "Flower petals glistening with dew",
        "Moon glowing over a sleepy town",
        "Pirate ships battling a stormy sea",
        "Zen garden with stones and sand",
        "Hot air balloons over a valley",
        "Majestic castle with shimmering spires",
        "Northern lights over a snowy forest",
        "Potion lab with bubbling cauldrons",
        "Treasure chest overflowing with gold",
        "Stone cottages in a countryside village",
        "Robot walking through a cyberpunk alley",
        "Knight standing guard at a fortress",
        "Dragon curled around a treasure hoard",
        "Magical portal opening in a forest",
        "Haunted mansion with cracked windows",
        "Peacock displaying its vibrant feathers",
        "Octopus rising from the ocean",
        "Space station orbiting a distant planet",
        "Families strolling through a park",
        "Mermaid resting on a sea rock",
        "Storm clouds over a golden field",
        "Tiger lurking in tall jungle grass",
        "Lanterns floating on a river at night",
        "Train passing through snowy mountains",
        "Alien forest with glowing trees",
        "Children chasing fireflies at dusk",
        "Wolves howling under a full moon",
        "Ancient ruins covered in vines",
        "Swan gliding on a tranquil lake",
        "Fireworks over a bustling city",
        "Tiny fairy sleeping on a leaf",
        "Penguins sliding on icy slopes",
        "Crystal cave illuminated with light",
        "Cybernetic hawk flying above skyscrapers",
        "Street musician playing under moonlight",
        "Lighthouse standing tall in a storm",
        "Tornado spinning over flatlands",
        "Balloons tied to a cozy house",
        "Samurai walking through cherry blossoms",
        "Fox watching snowfall from a hill",
        "Floating lantern festival in a village",
        "Knight riding a horse through fog",
        "Space whale drifting in cosmic ocean",
        "Windmill turning in a breezy field",
        "Skiers on a snow-covered slope",
        "Robot hand planting a tree",
        "Butterfly wings made of stained glass",
        "Market square lit by lanterns",
        "Dinosaur walking through a futuristic city",
        "Girl reading under a tree of light",
        "Ghost ship glowing in the dark sea",
        "Astronaut dancing on the moon",
        "Deer drinking from a glowing pond",
        "Crystals growing out of a canyon",
        "Bridge connecting two floating islands",
        "Warrior meditating under a waterfall",
        "City skyline during golden hour",
        "Vintage airplane flying over farmland",
        "Old library with candles and scrolls",
        "Robot painting a landscape",
        "Squirrel with armor on a battlefield",
        "Sailing ship caught in a whirlpool",
        "Boy and robot under a starry sky",
        "Sunflowers swaying in a summer breeze",
        "Fire dragon wrapped around a tower",
        "Bamboo forest with beams of sunlight",
        "Wolf pup in a flower meadow",
        "Floating temple in the sky"
    ).shuffled()



    Column(
        modifier = modifier
            .fillMaxWidth(0.95f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Explore",
                fontSize = 18.sp,
                fontFamily = poppins_regular,
                color = system_black
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.place),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp),
                    tint = shapphire_blue
                )
                Text(
                    text = "India, Kanpur",
                    fontSize = 12.sp,
                    fontFamily = poppins_light,
                    color = system_black
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp),
                    tint = shapphire_blue
                )
            }
        }
        Text(
            text = "ArtVerse",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 30.sp,
            fontFamily = poppins_medium,
            color = system_black
        )
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable{
                    onGenerateClick()
                }
                .fillMaxWidth()
                .background(color = Color(0xFFD7D9DC), RoundedCornerShape(20.dp))
                .shadow(elevation = 0.dp, shape = RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ai),
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp),
                contentDescription = null,
                tint = Color(0xFF9CA1A7)
            )
            Text(
                text = "Generate Images...",
                modifier = Modifier
                    .padding(vertical = 18.dp),
                fontSize = 20.sp,
                fontFamily = poppins_medium,
                color = Color(0xFF9CA1A7),
                maxLines = 1
            )
        }
        LoopingLazyRow(prompts){
            onPromptClick(it)
        }
    }

}
@Composable
fun LoopingLazyRow(
    list: List<String>,
    onPromptClick: (String) -> Unit
) {
    val listState = rememberLazyListState()

    // Start the LaunchedEffect to handle looping scroll
    LaunchedEffect(Unit) {
        while (true) {
            // Scroll to the next item every 2 seconds
            delay(2000) // Delay between each scroll

            // Get the current index of the first visible item
            val currentIndex = listState.firstVisibleItemIndex

            // Calculate the next index to scroll to (wrap around when reaching the end)
            val nextIndex = if (currentIndex < list.size - 1) currentIndex + 1 else 0

            // Smoothly scroll to the next item
            listState.animateScrollToItem(nextIndex)
        }
    }

    // LazyRow setup
    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(
                orientation = Orientation.Horizontal,
                state = ScrollableState { it }
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(list) {
            ActionChips(text = it){
                onPromptClick(it)
            }
        }
    }
}


@Composable
fun ActionChips(
    text: String,
    onClick: (String) -> Unit
) {
    ElevatedFilterChip(
        selected = false,
        onClick = {
            onClick(text)
        },
        label = {
            Text(
                text = text,
                modifier = Modifier
                    .padding(vertical = 10.dp),
                fontSize = 15.sp,
                fontFamily = poppins_semiBold,
            )
        },
        colors = SelectableChipColors(
            containerColor = system_white,
            labelColor = shapphire_blue,
            leadingIconColor = system_white,
            trailingIconColor = system_white,
            disabledContainerColor = system_white,
            disabledLabelColor = system_white,
            disabledLeadingIconColor = system_white,
            disabledTrailingIconColor = system_white,
            selectedContainerColor = system_white,
            disabledSelectedContainerColor = system_white,
            selectedLabelColor = system_white,
            selectedLeadingIconColor = system_white,
            selectedTrailingIconColor = system_white
        ),
        shape = CircleShape
    )
}

@Composable
fun PartyPopperScreen() {
    KonfettiView(
        modifier = Modifier.fillMaxSize(),
        parties = listOf(
            Party(
                emitter = Emitter(duration = 1, TimeUnit.SECONDS).perSecond(100),
                spread = 360,
                position = Position.Relative(0.5, 0.3),
                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                speed = 4f,
                maxSpeed = 30f,
                damping = 0.9f,
                size = listOf(Size.SMALL, Size.LARGE)
            )
        )
    )
}