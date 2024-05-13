package com.example.parkirapp.presentation.screens.on_boarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parkirapp.presentation.navigation.Destination
import com.example.parkirapp.presentation.screens.on_boarding.components.OnBoardingItem
import com.example.parkirapp.presentation.screens.on_boarding.components.PageIndicator
import com.example.parkirapp.presentation.shared.CustomButton
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        val pagerState = rememberPagerState(
            initialPage = 0
        ) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    pages.size - 1 -> listOf("Back", "Get Started")
                    else -> listOf("Back", "Next")
                }
            }
        }

        HorizontalPager(
            state = pagerState,
        ) { page ->
            OnBoardingItem(
                page = pages[page]
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    CustomButton(
                        text = buttonState.value[0],
                        cornersRound = 100.dp,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth(),
                        padding = 10.dp,
                        background = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        textColor = MaterialTheme.colorScheme.primary
                    ) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(5.dp),
                    )
                }

                CustomButton(
                    text = buttonState.value[1],
                    cornersRound = 100.dp,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    padding = 10.dp,
                ) {
                    scope.launch {
                        if (pagerState.currentPage < pages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            navController.popBackStack(Destination.OnBoarding.route, inclusive = true)
                            navController.navigate(Destination.Login.route)
                        }
                    }
                }
            }
        }
    }
}