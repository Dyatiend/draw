package draw.model

import java.awt.Color
import java.awt.Point
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Модель фигуры
 * @param firstPoint Первая точка фигуры
 * @param secondPoint Вторая точка фигуры
 * @param color Цвет фигуры
 * @param strokeWidth Ширина обводки фигуры
 * @param isSelected Выбрана ли фигура
 */
abstract class ShapeModel(
    var firstPoint: Point,
    var secondPoint: Point = Point(
        firstPoint.x + DEFAULT_SHAPE_SIZE,
        firstPoint.y + DEFAULT_SHAPE_SIZE,
    ),
    var color: Color = Color(DEFAULT_SHAPE_COLOR),
    var strokeWidth: Int = DEFAULT_SHAPE_STROKE_WIDTH,
    var isSelected: Boolean = DEFAULT_IS_SELECTED,
) {

    /**
     * Размеры фигуры (размер по X и Y осям)
     */
    val size: Point
        get() = Point(
            abs(firstPoint.x - secondPoint.x),
            abs(firstPoint.y - secondPoint.y),
        )

    /**
     * Левая верхняя точка прямоугольника, описывающего фигуру
     */
    val topLeftPoint: Point
        get() = Point(
            min(firstPoint.x, secondPoint.x),
            min(firstPoint.y, secondPoint.y),
        )

    /**
     * Правая нижняя точка прямоугольника, описывающего фигуру
     */
    val bottomRightPoint: Point
        get() = Point(
            max(firstPoint.x, secondPoint.x),
            max(firstPoint.y, secondPoint.y),
        )

    /**
     * Покрывает ли фигура указанную точку
     * @param point Точка
     * @param margin Отступ от фигуры, в рамках которой точка все еще считается относящейся к фигуре
     */
    fun includes(point: Point, margin: Int = DEFAULT_MARGIN): Boolean {
        return point.x in topLeftPoint.x - margin..bottomRightPoint.x + margin
                && point.y in topLeftPoint.y - margin..bottomRightPoint.y + margin
    }

    /**
     * Переместить фигуру
     * @param dx Перемещение по оси X
     * @param dy Перемещение по оси Y
     */
    fun move(dx: Int, dy: Int) {
        firstPoint.x += dx
        firstPoint.y += dy
        secondPoint.x += dx
        secondPoint.y += dy
    }

    /**
     * Применить visitor
     * @param visitor Visitor для фигур
     */
    open fun <T> applyVisitor(visitor: ShapesVisitor<T>): T {
        return visitor.visitShapeModel(this)
    }

    companion object {

        /**
         * Размер фигуры по умолчанию, если не указана вторая точка
         */
        const val DEFAULT_SHAPE_SIZE = 25

        /**
         * Цвет фигуры по умолчанию, если не указан иной
         */
        const val DEFAULT_SHAPE_COLOR = 0x000000

        /**
         * Ширина обводки фигуры по умолчанию, если не указана иная
         */
        const val DEFAULT_SHAPE_STROKE_WIDTH = 2

        /**
         * Флаг выбора фигуры по умолчанию, если не указан иной
         */
        const val DEFAULT_IS_SELECTED = false

        /**
         * Отступ от фигуры по умолчанию, в рамках которой точка все еще считается относящейся к фигуре
         */
        const val DEFAULT_MARGIN = 2
    }
}
