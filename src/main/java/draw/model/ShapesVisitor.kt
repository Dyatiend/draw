package draw.model

/**
 * Посетитель для моделей фигур
 */
interface ShapesVisitor<T> {

    fun visitShapeModel(model: ShapeModel): T

    fun visitFillableShapeModel(model: FillableShapeModel): T

    fun visitCircleModel(model: CircleModel): T

    fun visitRectangleModel(model: RectangleModel): T

    fun visitLineModel(model: LineModel): T

    fun visitTextModel(model: TextModel): T
}
