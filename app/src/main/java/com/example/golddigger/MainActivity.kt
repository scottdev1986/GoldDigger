package com.example.golddigger

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.golddigger.ui.theme.GoldDiggerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Access UI elements
        val numberOfShipsInput = findViewById<EditText>(R.id.number_of_ships)
        val numberOfDaysInput = findViewById<EditText>(R.id.number_of_days)
        val calculateButton = findViewById<Button>(R.id.button_calculate)
        val outputText = findViewById<TextView>(R.id.output_text)

        // Set onClickListener for the calculate button
        calculateButton.setOnClickListener {
            // Get input values as strings
            val numberOfShips = numberOfShipsInput.text.toString()
            val numberOfDays = numberOfDaysInput.text.toString()

            // Convert input values to integers
            val numberOfShipsInt = numberOfShips.toInt()
            val numberOfDaysInt = numberOfDays.toInt()

            // Calculate profit
            val profit = calculateProfit(numberOfShipsInt, numberOfDaysInt)

            // Prepare output text
            val output = "Profit: $profit pp"

            // Set output to TextView
            outputText.text = output
        }
    }

    // private D6 roll function
    private fun rollD6(): Int {
        return (1..6).random()
    }

    // private D8 roll function
    private fun rollD8(): Int {
        return (1..8).random()
    }

    // private calculateProfit function
    private fun calculateProfit(numberOfShips: Int, numberOfDays: Int): Int {
        var profit = 0
        var repairCost = 0
        for (i in 1..numberOfShips) {
            for (j in 1..numberOfDays) {
                val rollD6 = rollD6()
                val rollD8 = rollD8()
                profit += 1000 + 800 * (rollD6 - 1)
                if (rollD8 == 1) {
                    repairCost += 1250
                }
                if (rollD8 == 2) {
                    repairCost += 1188
                }
                if (rollD8 == 3) {
                    repairCost += 1188
                }
                if (rollD8 == 4) {
                    repairCost += 1068
                }
                if (rollD8 == 5) {
                    repairCost += 625
                }
                if (rollD8 == 6) {
                    repairCost += 436
                }
                if (rollD8 == 7) {
                    repairCost += 188
                }
                if (rollD8 == 8) {
                    repairCost += 0
                }
            }
        }
        return profit - repairCost
    }

}