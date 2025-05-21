package cz.cvut.fel.pjv.bukovja4.utils.maze;

import cz.cvut.fel.pjv.bukovja4.engine.elements.BaseElement;

/**
 * Represents a cell in a maze.
 * Each cell has a type and an associated element.
 */
public class MazeCell {
    /**
     * The type of the cell.
     */
    private CellTypes cellType;
    /**
     * The element associated with the cell.
     */
    private BaseElement<?> element;

    /**
     * Gets the type of the cell.
     *
     * @return The type of the cell.
     */
    public CellTypes getCellType() {
        return cellType;
    }

    /**
     * Sets the type of the cell.
     *
     * @param cellType The new type of the cell.
     */
    public void setCellType(CellTypes cellType) {
        this.cellType = cellType;
    }

    /**
     * Gets the element associated with the cell.
     *
     * @return The element associated with the cell.
     */
    public BaseElement<?> getElement() {
        return element;
    }

    /**
     * Sets the element associated with the cell.
     * @param element
     */
    public void setElement(BaseElement<?> element) {
        this.element = element;
    }

    /**
     * Sets the type of the cell.
     *
     * @param cellType The new type of the cell.
     * @param element  The new element associated with the cell.
     */
    public MazeCell(CellTypes cellType, BaseElement<?> element) {
        this.cellType = cellType;
        this.element = element;
    }
}
