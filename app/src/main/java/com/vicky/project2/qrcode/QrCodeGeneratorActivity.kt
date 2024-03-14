package com.vicky.project2.qrcode

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.vicky.project2.R

class QrCodeGeneratorActivity : AppCompatActivity() {

    companion object {
        const val RESULT = "RESULT"
    }

    private lateinit var ivQrCode: ImageView
    private lateinit var btnQrCode: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_generator)

        ivQrCode = findViewById(R.id.ivQrCode)
        btnQrCode = findViewById(R.id.btnQrCode)

        btnQrCode.setOnClickListener {
            val eventDetails = "Event Description"
            val writer = QRCodeWriter()
            try {
                val bitMatrix = writer.encode("eventDetails", BarcodeFormat.QR_CODE, 512, 512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }
                ivQrCode.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }
            //put intent on to scan xml
            val intent = Intent(applicationContext, QrCodeScannerActivity::class.java)
            startActivity(intent)
        }

            val result = intent.getStringExtra(RESULT)
            if (result != null) {
                val textResult = result

                val textView = findViewById<TextView>(R.id.tv_event_description)
                textView.text = textResult
        }
    }
}