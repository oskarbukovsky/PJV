package cz.cvut.fel.pjv.bukovja4.cv03.Bicycles;

public enum Colors {
    White(0, "#F0F0F0"), Orange(1, "#F2B233"), Magenta(2, "#E57FD8"), LightBlue(3, "#99B2F2"), Yellow(4, "#DEDE6C"),
    Lime(5, "#7FCC19"), Pink(6, "#F2B2CC"), Gray(7, "#4C4C4C"), LightGray(8, "#999999"), Cyan(9, "#4C99B2"),
    Purple(10, "#B266E5"), Blue(11, "#3366CC"), Brown(12, "#7F664C"), Green(13, "#57A64E"), Red(14, "#CC4C4C"),
    Black(15, "#191919");

    private final int id;
    private final String colorCode;

    Colors(int id, String colorCode) {
        this.id = id;
        this.colorCode = colorCode;
    }

    public int getId() {
        return id;
    }

    public String getColorCode() {
        return colorCode;
    }
}