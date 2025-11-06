package edu.ucne.darvynlavandier_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.ucne.darvynlavandier_ap2_p2.navigation.GastoNavHost
import edu.ucne.darvynlavandier_ap2_p2.ui.theme.DarvynLavandier_AP2_P2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DarvynLavandier_AP2_P2Theme {
                GastoNavHost()
            }
        }
    }
}