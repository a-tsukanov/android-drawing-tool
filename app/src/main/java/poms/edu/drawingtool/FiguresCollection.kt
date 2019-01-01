package poms.edu.drawingtool

import android.graphics.Color

object FiguresCollection {
    val figures: MutableList<Figure> = mutableListOf(
        Circle(100f, 100f, 100f, Color.BLUE),
        Oval(200f, 500f, 300f, 330f, Color.RED),
        Rectangle(400f, 500f, 600f, 700f, Color.BLUE),
        Triangle(800f, 1000f, 1000f, 1200f, Color.BLUE)
    )
}