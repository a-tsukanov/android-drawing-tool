package poms.edu.drawingtool

object FiguresCollection {

    val figures: MutableList<Figure> = mutableListOf()
    fun undo() {
        val size = figures.size
        if (size == 0) return
        figures.removeAt(figures.size - 1)
    }

}