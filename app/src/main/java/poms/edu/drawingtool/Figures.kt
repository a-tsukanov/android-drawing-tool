package poms.edu.drawingtool

data class Point(val x: Float, val y: Float): Figure


data class Line(
    val startX: Float,
    val startY: Float,
    val endX: Float,
    val endY: Float
): Figure


class Circle(
    val radius: Float,
    val centerX: Float,
    val centerY: Float
): Figure


class Oval(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float
): Figure


class Rectangle(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float
): Figure


class Triangle(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float
): Figure