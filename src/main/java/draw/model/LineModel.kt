package draw.model

import java.awt.Color
import java.awt.Point

/**
 * Модель прямой линии
 */
class LineModel(
    firstPoint: Point,
    secondPoint: Point = Point(
        firstPoint.x + DEFAULT_SHAPE_SIZE,
        firstPoint.y + DEFAULT_SHAPE_SIZE,
    ),
    color: Color = Color(DEFAULT_SHAPE_COLOR),
    strokeWidth: Int = DEFAULT_SHAPE_STROKE_WIDTH,
    isSelected: Boolean = DEFAULT_IS_SELECTED,
) : ShapeModel(
    firstPoint = firstPoint,
    secondPoint = secondPoint,
    color = color,
    strokeWidth = strokeWidth,
    isSelected = isSelected,
) {

    override fun <T> applyVisitor(visitor: ShapesVisitor<T>): T {
        return visitor.visitLineModel(this)
    }
}