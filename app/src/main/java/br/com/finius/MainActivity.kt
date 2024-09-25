package br.com.finius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.finius.features.bankAccounts.BankAccountsRoute
import br.com.finius.features.bankAccounts.BankAccountsScreen
import br.com.finius.features.bankAccounts.NewBankAccountRoute
import br.com.finius.features.bankAccounts.NewBankAccountScreen
import br.com.finius.features.cards.CardsRoute
import br.com.finius.features.cards.CardsScreen
import br.com.finius.features.cards.NewCardRoute
import br.com.finius.features.cards.NewCardScreen
import br.com.finius.features.home.HomeRoute
import br.com.finius.features.home.HomeScreen
import br.com.finius.features.transaction.NewTransactionRoute
import br.com.finius.features.transaction.NewTransactionScreen
import br.com.finius.features.transactionList.TransactionListRoute
import br.com.finius.features.transactionList.TransactionListScreen
import br.com.finius.ui.theme.FiniusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            FiniusTheme {
                NavHost(
                    navController = navController,
                    startDestination = HomeRoute,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start,
                            tween(700)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End,
                            tween(700)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End,
                            tween(700)
                        )
                    }
                ) {
                    composable<HomeRoute> {
                        HomeScreen(
                            onNavigateToNewTransaction = { navController.navigate(route = NewTransactionRoute) },
                            onNavigateToBankAccounts = { navController.navigate(route = BankAccountsRoute) },
                            onNavigateToCards = { navController.navigate(route = CardsRoute) },
                            onNavigateToTransactions = { navController.navigate(route = TransactionListRoute) }
                        )
                    }
                    composable<NewTransactionRoute> {
                        NewTransactionScreen(onNavigateBack = navController::popBackStack, onNavigateToNewCard = { navController.navigate(route = NewCardRoute) })
                    }
                    composable<BankAccountsRoute> {
                        BankAccountsScreen(
                            onNavigateBack = navController::popBackStack,
                            onNavigateToNewBankAccount = { navController.navigate(route = NewBankAccountRoute) }
                        )
                    }
                    composable<NewBankAccountRoute> {
                        NewBankAccountScreen(onNavigateBack = navController::popBackStack)
                    }
                    composable<CardsRoute> {
                        CardsScreen(
                            onNavigateBack = navController::popBackStack,
                            onNavigateToNewCard = { navController.navigate(route = NewCardRoute) }
                        )
                    }
                    composable<NewCardRoute> {
                        NewCardScreen(onNavigateBack = navController::popBackStack)
                    }
                    composable<TransactionListRoute> {
                        TransactionListScreen(
                            onNavigateBack = navController::popBackStack,
                            onNavigateToNewTransaction = { navController.navigate(route = NewTransactionRoute) }
                        )
                    }
                }
            }
        }
    }
}