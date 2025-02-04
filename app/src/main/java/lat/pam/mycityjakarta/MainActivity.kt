package lat.pam.mycityjakarta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import lat.pam.mycityjakarta.ui.theme.AppUiState
import lat.pam.mycityjakarta.ui.theme.AppViewModel
import lat.pam.mycityjakarta.ui.theme.MyCityTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.model.Details

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCityTheme {
                MyApp()
            }
        }
    }
}

enum class Screen {
    Home,
    Shop,
    Detail
}

@Composable
fun MyApp(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Home.name
    )

    Scaffold(
        topBar = {
            AppBar(
                title = uiState.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.name) {
                viewModel.updateTitle(R.string.app_name)
                CategoryList(
                    uiState = uiState,
                    viewModel = viewModel,
                    onClick = { navController.navigate(Screen.Shop.name) }
                )
            }
            composable(Screen.Shop.name) {
                viewModel.updateTitle(uiState.category)
                ShopList(
                    shopList = uiState.placeList,
                    viewModel = viewModel,
                    onClick = { navController.navigate(Screen.Detail.name) }
                )
            }
            composable(Screen.Detail.name) {
                viewModel.updateTitle(uiState.place.titleResourceId)
                DetailScreen(
                    details = uiState.place
                )
            }
        }
    }
}

@Composable
fun CategoryList(
    uiState: AppUiState,
    viewModel: AppViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(uiState.categoryList, key = { category -> category }) { category ->
            CategoryCard(
                title = category,
                onClick = {
                    onClick()
                    viewModel.updateCategory(category)
                }
            )
        }
    }
}

@Composable
fun ShopList(
    shopList: List<Details>,
    viewModel: AppViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(shopList, key = { details -> details.id }) { detail ->
            ShopCard(
                details = detail,
                onClick = {
                    onClick()
                    viewModel.updatePlace(detail)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    title: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ShopCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    details: Details
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(92.dp)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.shapes.medium)
    ) {
        Image(
            painter = painterResource(id = details.logoResourceId),
            contentDescription = stringResource(id = details.titleResourceId),
            modifier = Modifier
                .padding(12.dp)
                .size(72.dp)
                .clip(MaterialTheme.shapes.small)
                .height(72.dp)
                .align(Alignment.CenterVertically)
        )
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = stringResource(id = details.titleResourceId),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(id = details.descriptionResourceId),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: Int = R.string.app_name,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize().padding(top = 12.dp)
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        modifier = Modifier.fillMaxHeight(0.15f)
    )
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    details: Details,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = details.bannerResourceId),
            contentDescription = null,
            modifier = modifier.clip(MaterialTheme.shapes.extraSmall)
        )
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.displayMedium,
            fontSize = 24.sp,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = details.descriptionResourceId),
            modifier = Modifier.verticalScroll(rememberScrollState()),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MyCityTheme {
        MyApp()
    }
}
