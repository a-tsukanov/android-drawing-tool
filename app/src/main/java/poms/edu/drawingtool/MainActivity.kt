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

        val btnToFigure = mapOf(
            btnPen      to Figures.PEN,
            btnLine     to Figures.LINE,
            btnCircle   to Figures.CIRCLE,
            btnOval     to Figures.OVAL,
            btnRect     to Figures.RECTANGLE,
            btnTriangle to Figures.TRIANGLE
        )
        btnToFigure.forEach { btn, fig ->
            btn.setOnClickListener { ChosenAttributes.figure = fig }
        }
    }
}
