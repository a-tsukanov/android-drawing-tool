package poms.edu.drawingtool


class Line(
    val startX: Float,
    val startY: Float,
    val endX: Float,
    val endY: Float,
    color: ColorId
): Figure(color)


class Circle(
    val radius: Float,
    val centerX: Float,
    val centerY: Float,
    color: ColorId
): Figure(color)


class Oval(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float,
    color: ColorId
): Figure(color)


class Rectangle(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float,
    color: ColorId
): Figure(color)


class Triangle(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float,
    color: ColorId
): Figure(color)


enum class Figures {
    PEN,
    LINE,
    CIRCLE,
    OVAL,
    RECTANGLE,
    TRIANGLE,
}