import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;


// ContainerPanel class for CE203 Assignment to use and modify if needed
// ID: 2203212
// Author: Jennifer Eleanor Warwick



public class ContainerPanel extends JPanel {

    ContainerFrame conFrame;

    public ContainerPanel(ContainerFrame cf) {
        conFrame = cf;   // reference to ContainerFrame object
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D comp = (Graphics2D) g;
        Dimension size = getSize();

        // clear the panel
        comp.clearRect(0, 0, size.width, size.height);

        // iteration over the list and draw each polygon
        for (RegPolygon polygon : conFrame.polygonList) {
            polygon.drawPolygon(comp, size);
        }

        // draw the last drawn polygon
        if (conFrame.lastDrawnPolygon != null) {
            conFrame.lastDrawnPolygon.drawPolygon(comp, size);
        }
    }

}

