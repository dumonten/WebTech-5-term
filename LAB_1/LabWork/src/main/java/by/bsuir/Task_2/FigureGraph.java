package by.bsuir.Task_2;

public class FigureGraph {
    private static final int topRectLeft = -4;
    private static final int topRectRight = 4;
    private static final int topRectTop = 5;
    private static final int topRectBottom = 0;
    private static final int bottomRectLeft = -6;
    private static final int bottomRectRight = 6;
    private static final int bottomRectTop = 0;
    private static final int bottomRectBottom = -3;


    public static boolean checkIfIn(double x, double y) {
        return checkIfInBottomRectArea(x, y) || checkIfInTopRectArea(x, y);
    }

    public static boolean checkIfInTopRectArea(double x, double y){
        return (x >= FigureGraph.topRectLeft && x <= FigureGraph.topRectRight)
                && (y >= FigureGraph.topRectBottom && y <= FigureGraph.topRectTop);
    }

    public static boolean checkIfInBottomRectArea(double x, double y){
        return (x >= FigureGraph.bottomRectLeft && x <= FigureGraph.bottomRectRight)
                && (y >= FigureGraph.bottomRectBottom && y <= FigureGraph.bottomRectTop);
    }
}
