import java.awt.*;

// ID: 2203212
// Author: Jennifer Eleanor Warwick
public class RegPolygon implements Comparable<RegPolygon> {

    Color pColor; // Colour of the polygon, set to a Color object, default set to black
    int pId = 000000; // Polygon ID should be a six-digit non-negative integer
    int pSides; // Number of sides of the polygon, should be a non-negative value

    double pStarting_angle; // starting angle

    double pRadius; // radius of polygon

    int polyCenX; // x value of the center point (pixel) of the polygon when drawn on the panel
    int polyCenY; // y value of the center point (pixel of the polygon when drawn on the panel
    double[] pointsX; // int array containing x values of each vertex (corner point) of the polygon
    double[] pointsY; // int array containing y values of each vertex (corner point) of the polygon


    public RegPolygon(int p_sides, double st_angle, double rad, int pId, Color pColor) {
        this.pSides = p_sides;
        this.pStarting_angle = st_angle;
        this.pRadius = rad;
        this.pId = pId;
        this.pColor = pColor;
        pointsX = new double[pSides];
        pointsY = new double[pSides];
    }

    private Polygon getPolygonPoints(Dimension dim) {
        polyCenX = dim.width / 2;
        polyCenY = dim.height / 2;
        double angleIncrement = 2 * Math.PI / pSides;
        Polygon p = new Polygon();

        double currentAngle = pStarting_angle;

        for (int i = 0; i < pSides; i++) {
            pointsX[i] = polyCenX + pRadius * Math.cos(currentAngle);
            pointsY[i] = polyCenY + pRadius * Math.sin(currentAngle);

            p.addPoint((int) pointsX[i], (int) pointsY[i]);

            currentAngle += angleIncrement;
        }

        return p;
    }


    // You will need to modify this method to set the colour of the Polygon to be drawn
    // Remember that Graphics2D has a setColor() method available for this purpose
    public void drawPolygon(Graphics2D g, Dimension d) {
        // clear the panel for new drawings
        g.clearRect(0, 0, (int) d.getWidth(), (int) d.getHeight());

        // set the color for the new polygon
        g.setColor(pColor);

        // draw the new polygon
        Polygon polygon = getPolygonPoints(d);
        g.drawPolygon(polygon);
    }


    // gets a stored ID
    public int getID() {
        return pId;
    }


    @Override
    public int compareTo(RegPolygon o) {
        return Integer.compare(this.getID(), o.getID());


    }


    // outputs a string representation of the PolygonContainer object, you need to complete this to use for testing
    public String toString() {
        String sb = "Polygon ID: " + pId + "\n" +
                "Number of sides: " + pSides + "\n" +
                "Starting angle: " + pStarting_angle + "\n" +
                "Radius: " + pRadius + "\n" +
                "Colour: " + pColor + "\n";

        return sb;
    }
}
