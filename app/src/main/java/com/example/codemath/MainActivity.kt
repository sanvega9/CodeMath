package com.example.codemath
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.slider.Slider
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var billAmountInput: EditText
    private lateinit var tipSlider: Slider
    private lateinit var tipLabel: TextView
    private lateinit var tipAmount: TextView
    private lateinit var totalAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views using findViewById
        billAmountInput = findViewById(R.id.billamountinput)
        tipSlider = findViewById(R.id.tipSlider)
        tipLabel = findViewById(R.id.tip)
        tipAmount = findViewById(R.id.tvTipAmount)
        totalAmount = findViewById(R.id.tvTotalAmount)

        // Tip % Slider change listener
        tipSlider.addOnChangeListener { _, value, _ ->
            val tipPercent = value.toInt()
            tipLabel.text = "$tipPercent%"
            calculateTip()
        }

        // Bill input text change listener
        billAmountInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateTip()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun calculateTip() {
        val billStr = billAmountInput.text.toString()
        val bill = billStr.toDoubleOrNull()
        val tipPercent = tipSlider.value

        if (bill != null) {
            val tip = bill * tipPercent / 100
            val total = bill + tip

            tipAmount.text = "Tip: $%.2f".format(tip)
            totalAmount.text = "Total: $%.2f".format(total)
        } else {
            tipAmount.text = "Tip: $0.00"
            totalAmount.text = "Total: $0.00"
        }
    }
}