package br.com.finius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.finius.bankAccounts.BankAccountsRoute
import br.com.finius.bankAccounts.BankAccountsScreen
import br.com.finius.bankAccounts.NewBankAccountRoute
import br.com.finius.bankAccounts.NewBankAccountScreen
import br.com.finius.cards.CardsRoute
import br.com.finius.cards.CardsScreen
import br.com.finius.cards.NewCardRoute
import br.com.finius.cards.NewCardScreen
import br.com.finius.home.HomeRoute
import br.com.finius.home.HomeScreen
import br.com.finius.transaction.NewTransactionRoute
import br.com.finius.transaction.NewTransactionScreen
import br.com.finius.transactionList.TransactionListRoute
import br.com.finius.transactionList.TransactionListScreen
import br.com.finius.ui.theme.FiniusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            onNavigateToTransactions = { navController.navigate(TransactionListRoute) }
                        )
                    }
                    composable<NewTransactionRoute> {
                        NewTransactionScreen(onNavigateBack = navController::popBackStack)
                    }
                    composable<BankAccountsRoute> {
                        BankAccountsScreen(
                            onNavigateBack = navController::popBackStack,
                            onNavigateToNewBankAccount = { navController.navigate(NewBankAccountRoute) }
                        )
                    }
                    composable<NewBankAccountRoute> {
                        NewBankAccountScreen(onNavigateBack = navController::popBackStack)
                    }
                    composable<CardsRoute> {
                        CardsScreen(
                            onNavigateBack = navController::popBackStack,
                            onNavigateToNewCard = { navController.navigate(NewCardRoute) }
                        )
                    }
                    composable<NewCardRoute> {
                        NewCardScreen(onNavigateBack = navController::popBackStack)
                    }
                    composable<TransactionListRoute> {
                        TransactionListScreen(
                            onNavigateBack = navController::popBackStack,
                            onNavigateToNewTransaction = { navController.navigate(NewTransactionRoute) }
                        )
                    }
                }
            }
        }
    }
}