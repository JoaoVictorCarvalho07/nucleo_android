package com.pucpr.nucleo

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pucpr.nucleo.view.screens.*
import com.pucpr.nucleo.viewmodel.DashboardViewModel

@Composable
fun NucleoApp() {
    val navController = rememberNavController()
    val dashboardViewModel: DashboardViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("dashboard") {
            DashboardScreen(
                currentRoute = "dashboard",
                onNewTransaction = {
                    navController.navigate("transaction")
                },
                onNavigate = { route ->
                    navController.navigate(route)
                },
                onDeleteTransaction = { transactionId ->
                    dashboardViewModel.deleteTransaction(transactionId)
                }
            )
        }

        composable("transaction") {
            TransactionScreen(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { navController.popBackStack() }
            )
        }

        composable("transactions") {
            TransactionsListScreen(
                currentRoute = "transactions",
                onNewTransaction = {
                    navController.navigate("transaction")
                },
                onNavigate = { route ->
                    navController.navigate(route)
                },
                onDeleteTransaction = { transactionId ->
                    dashboardViewModel.deleteTransaction(transactionId)
                }
            )
        }

        composable("profile") {
            ProfileScreen(
                onBackClick = { navController.popBackStack() },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}