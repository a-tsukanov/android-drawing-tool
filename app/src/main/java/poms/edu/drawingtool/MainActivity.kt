package poms.edu.drawingtool

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import yuku.ambilwarna.AmbilWarnaDialog

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnColor.setOnClickListener {
            val colorDialog = AmbilWarnaDialog(this, ChosenAttributes.penColor,
                object: AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onOk(dialog: AmbilWarnaDialog?, color: ColorId) {
                        ChosenAttributes.penColor = color
                    }

                    override fun onCancel(dialog: AmbilWarnaDialog?) {

                    }
                })
            colorDialog.show()
        }
    }
}
