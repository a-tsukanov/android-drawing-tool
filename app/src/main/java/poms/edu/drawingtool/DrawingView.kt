package poms.edu.drawingtool

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class DrawingView: View {

    constructor(context: Context):
        super(context)

    constructor(context: Context, attrs: AttributeSet):
        super(context, attrs)


    private var paint = Paint().apply {
        style = Paint.Style.FILL
        color = ChosenColor.penColor
        minimumWidth = 20
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawFigures(canvas)
    }

    private fun drawFigures(canvas: Canvas?) {

        fun drawFigure(figure: Figure) = canvas?.run {
            when (figure) {
                is Point     -> drawPoint(figure.x, figure.y, paint)
                is Line      -> drawLine(figure.startX, figure.startY, figure.endX, figure.endY, paint)
                is Circle    -> drawCircle(figure.centerX, figure.centerY, figure.radius, paint)
                is Oval      -> drawOval(figure.left, figure.top, figure.right, figure.bottom, paint)
                is Rectangle -> drawRect(figure.left, figure.top, figure.right, figure.bottom, paint)
                is Triangle  -> {
                    val path = Path().apply {
                        moveTo(figure.left, figure.top)
                        lineTo(figure.left, figure.bottom)
                        lineTo(figure.right, figure.bottom)
                        lineTo(figure.left, figure.top)
                    }
                    drawPath(path, paint)
                }
            }
        }


        for (figure in FiguresCollection.figures) {
            drawFigure(figure)
        }
    }
}