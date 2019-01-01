package poms.edu.drawingtool

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Math.pow
import kotlin.math.sqrt

class DrawingView: View {

    constructor(context: Context):
        super(context)

    constructor(context: Context, attrs: AttributeSet):
        super(context, attrs)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawFigures(canvas)
    }

    private fun drawFigures(canvas: Canvas?) {

        fun drawFigure(figure: Figure) = canvas?.run {
            val paint = Paint().apply {
                style = Paint.Style.FILL
                color = figure.color
            }
            when (figure) {
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

    private var beginX: Float? = null
    private var beginY: Float? = null

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val (x, y) = Pair(event.x, event.y)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                beginX = x
                beginY = y
            }
            MotionEvent.ACTION_MOVE -> {
                if (ChosenAttributes.figure == Figures.PEN) {
                    FiguresCollection.figures.add(
                        Line(beginX!!, beginY!!, x, y, ChosenAttributes.penColor)
                    )
                    invalidate()
                    beginX = x
                    beginY = y
                }
            }
            MotionEvent.ACTION_UP -> {
                val (beginX, beginY) = Pair(this.beginX!!, this.beginY!!)
                val newFigure: Figure = when (ChosenAttributes.figure) {
                    Figures.PEN -> return true
                    Figures.LINE -> Line(beginX, beginY, x, y, ChosenAttributes.penColor)
                    Figures.CIRCLE -> {
                        val dx = (x - beginX).toDouble()
                        val dy = (y - beginY).toDouble()
                        val radius = sqrt(pow(dx, 2.0) + pow(dy, 2.0))
                        Circle(radius.toFloat(), beginX, beginY, ChosenAttributes.penColor)
                    }
                    Figures.OVAL -> Oval(beginX, x, beginY, y, ChosenAttributes.penColor)
                    Figures.RECTANGLE -> Rectangle(beginX, x, beginY, y, ChosenAttributes.penColor)
                    Figures.TRIANGLE -> Triangle(beginX, x, beginY, y, ChosenAttributes.penColor)
                }
                FiguresCollection.figures.add(newFigure)
                drawingView.invalidate()
            }
        }

        return true
    }
}