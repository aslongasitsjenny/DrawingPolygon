import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// ID: 2203212
// Author: Jennifer Eleanor Warwick
public class ContainerFrame extends JFrame {

    ArrayList<RegPolygon> polygonList = new ArrayList<>();
    ContainerPanel centralPanel;
    public RegPolygon lastDrawnPolygon;

    JLabel label1 = new JLabel("RegNo: ");
    JLabel label2 = new JLabel("No.Sides: ");
    JLabel label3 = new JLabel("Starting Angle: ");
    JLabel label4 = new JLabel("Radius: ");
    JLabel label5 = new JLabel("Colour: ");

    JButton button1 = new JButton("Add");
    JButton button2 = new JButton("Display");
    JButton button3 = new JButton("Search");
    JButton button4 = new JButton("Sort");
    JTextField textField1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();
    JTextField textField5 = new JTextField();


    public void setLastDrawnPolygon(RegPolygon polygon) {
        this.lastDrawnPolygon = polygon;
    }


    public void createComponents() {


        ContainerButtonHandler cbHandler = new ContainerButtonHandler(this);
        button1.addActionListener(cbHandler);
        button2.addActionListener(cbHandler);
        button3.addActionListener(cbHandler);
        button4.addActionListener(cbHandler);


        centralPanel = new ContainerPanel(this);  // initialise the centralpanel to container panel for drawing

        JPanel topPanel = new JPanel(new GridLayout(1, 4));
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));

        // associate labels with their corresponding input components
        label1.setLabelFor(textField1);
        label2.setLabelFor(textField2);
        label3.setLabelFor(textField3);
        label4.setLabelFor(textField4);
        label5.setLabelFor(textField5);

        topPanel.add(label1);
        topPanel.add(textField1);

        topPanel.add(label2);
        topPanel.add(textField2);

        topPanel.add(label3);
        topPanel.add(textField3);

        topPanel.add(label4);
        topPanel.add(textField4);

        topPanel.add(label5);
        topPanel.add(textField5);


        bottomPanel.add(button1);
        bottomPanel.add(button2);
        bottomPanel.add(button3);
        bottomPanel.add(button4);

        // adding the panels to the frame using border layout positions
        add(topPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public static void main(String[] args) {
        ContainerFrame cFrame = new ContainerFrame();
        cFrame.createComponents();

    }
}

