package draw.model

import java.awt.Point

/**
 * Модель холста
 * @param shapes Список фигур на холсте
 */
class CanvasModel(
    val shapes: MutableList<ShapeModel> = mutableListOf()
) : MutableCollection<ShapeModel> by shapes {

    /**
     * Получить самую верхнюю фигуру на указанной точке
     */
    fun getShapeAt(point: Point): ShapeModel? {
        return shapes.find { shape ->
            shape.includes(point)
        }
    }

    /**
     * Переместить фигуру на передний план на 1 шаг
     */
    fun moveToForeground(shape: ShapeModel) {
        val index = shapes.indexOf(shape)
        if (index in 0 until shapes.size - 1) {
            shapes[index] = shapes[index + 1].also { shapes[index + 1] = shapes[index] }
        }
    }

    /**
     * Переместить фигуру на задний план на 1 шаг
     */
    fun moveToBackground(shape: ShapeModel) {
        val index = shapes.indexOf(shape)
        if (index > 0) {
            shapes[index] = shapes[index - 1].also { shapes[index - 1] = shapes[index] }
        }
    }
}