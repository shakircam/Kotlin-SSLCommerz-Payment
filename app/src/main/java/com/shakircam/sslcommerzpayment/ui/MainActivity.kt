package com.shakircam.sslcommerzpayment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.awesomedialog.*
import com.shakircam.sslcommerzpayment.databinding.ActivityMainBinding
import com.shakircam.sslcommerzpayment.utils.Constants.PRODUCT_CATEGORY
import com.shakircam.sslcommerzpayment.utils.Constants.STORE_ID
import com.shakircam.sslcommerzpayment.utils.Constants.STORE_PASSWORD
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener

class MainActivity() : AppCompatActivity() , SSLCTransactionResponseListener {

    private lateinit var binding: ActivityMainBinding

    private var sslCommerzInitialization: SSLCommerzInitialization? = null
    var additionalInitializer: SSLCAdditionalInitializer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            val amount = binding.amountEtx.text.toString().trim()
            if (amount.isNotEmpty()){

             paymentBySslCommerz(amount)
            }
        }
    }

    private fun paymentBySslCommerz(amount: String) {

       val currentTime = System.currentTimeMillis()
        sslCommerzInitialization = SSLCommerzInitialization(
            STORE_ID,
            STORE_PASSWORD,
            amount.toDouble(),
            SSLCCurrencyType.BDT,
            "ID$currentTime",
            PRODUCT_CATEGORY,
            SSLCSdkType.TESTBOX
        )
        additionalInitializer = SSLCAdditionalInitializer()
        additionalInitializer!!.valueA = "Value Option 1"
        additionalInitializer!!.valueB = "Value Option 1"
        additionalInitializer!!.valueC = "Value Option 1"
        additionalInitializer!!.valueD = "Value Option 1"

       IntegrateSSLCommerz.getInstance(this)
           .addSSLCommerzInitialization(sslCommerzInitialization)
           .addAdditionalInitializer(additionalInitializer)
           .buildApiCall(this)
    }


    override fun transactionSuccess(success: SSLCTransactionInfoModel?) {

        if (success != null){
            AwesomeDialog
                .build(this)
                .position(AwesomeDialog.POSITIONS.CENTER)
                .title("Id:${success.tranId} \nAmount: ${success.amount} \nPayment Status:${success.apiConnect}")
                .icon(R.drawable.notification_icon_background)
                .onPositive("Continue") {

                }
        }
    }

    override fun transactionFail(fail: String?) {
        AwesomeDialog
            .build(this)
            .position(AwesomeDialog.POSITIONS.CENTER)
            .title("$fail")
            .icon(R.drawable.notification_template_icon_bg)
            .onPositive("Continue") {

            }
    }

    override fun merchantValidationError(error: String?) {
        AwesomeDialog
            .build(this)
            .position(AwesomeDialog.POSITIONS.CENTER)
            .title("$error")
            .icon(R.drawable.notification_template_icon_bg)
            .onPositive("Continue") {

            }
    }

}