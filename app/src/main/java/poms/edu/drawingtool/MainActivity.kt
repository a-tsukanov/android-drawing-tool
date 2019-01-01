package poms.edu.drawingtool

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
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

        btnClear.setOnClickListener {
            FiguresCollection.figures.clear()
            drawingView.invalidate()
        }

        btnUndo.setOnClickListener {
            FiguresCollection.undo()
            drawingView.invalidate()
        }

        btnPhoto.setOnClickListener {
            val dialog = AlertDialog.Builder(this).apply {
                setPositiveButton("Gallery") { _, _ ->
                    val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" }
                    startActivityForResult(intent, REQUEST_PICK_IMAGE)
                }
                setNeutralButton("Camera") { _, _ ->
                    val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, REQUEST_CAMERA)
                }
            }
            dialog.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        fun retrieveImage(): Bitmap {
            val inputStream = contentResolver.openInputStream(data!!.data!!)
            return BitmapFactory.decodeStream(inputStream)
        }

        fun updateWithBitmap(bitmap: Bitmap) {

            val drawable = BitmapDrawable(resources, bitmap)
            drawingView.background = drawable

            FiguresCollection.figures.clear()
            drawingView.invalidate()
        }



        if (resultCode != Activity.RESULT_OK)
            return

        when (requestCode) {
            REQUEST_PICK_IMAGE -> {
                val bitmap = retrieveImage()
                updateWithBitmap(bitmap)
            }
            REQUEST_CAMERA -> data?.run {
                val photo = data.extras?.get("data") as Bitmap
                updateWithBitmap(photo)

            } ?: return
        }


    }


    companion object {
        const val REQUEST_PICK_IMAGE = 1
        const val REQUEST_CAMERA = 2
    }
}
