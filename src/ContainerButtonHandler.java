import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

// ContainerButtonHandler class for CE203 Assignment to use and modify if needed
// ID: 2203212
// Author: Jennifer Eleanor Warwick

class ContainerButtonHandler implements ActionListener {
    ContainerFrame theApp;

    ContainerButtonHandler(ContainerFrame app) {
        theApp = app;
    }
    private static Color validateColour(String colourInput) {
        // remove any leading "0x" or "0X" if present to ensure proper decoding of colour
        colourInput = colourInput.replaceAll("^(0x|0X)", "");

        // check if the input is a valid hexadecimal color representation
        if (isValidHexColour(colourInput)) {
            try {
                return Color.decode("0x" + colourInput);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid colour format. Defaulting to black. Please input a valid hexadecimal color code: E.G - FF5733: ");
            }
        } else {
            // print an error message for an invalid hexadecimal format
            System.out.println("Error: Invalid hexadecimal color format. Input this format > E.G - FF5733:");
        }

        return Color.BLACK;
    }

    private static boolean isValidHexColour(String s) {
        // this checks if the string is not empty and contains only valid hexadec characters by matching this pattern
        return !s.isEmpty() && s.matches("^[0-9A-Fa-f]+$");
    }
    private int validateId(String id, List<RegPolygon> polygonList) {
        // check if the id has exactly 6 digits
        if (id.matches("\\d{6}")) {
            try {
                int parsedId = Integer.parseInt(id);

                // check if the ID is unique
                if (isUniqueId(parsedId, polygonList)) {
                    return parsedId;
                } else {
                    System.out.println("Error: ID must be unique. Setting to default value: 000000.");
                    return 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid ID. Setting to default value: 000000.");
                return 0;
            }
        } else {
            System.out.println("Error: Invalid ID. Setting to default value: 000000.");
            return 0;
        }
    }


    private boolean isUniqueId(int id, List<RegPolygon> polygonList) {
        // check if the ID is unique in the list of polygons
        for (RegPolygon polygon : polygonList) {
            if (polygon.getID() == id) {
                return false; // is not unique
            }
        }
        return true; // the id is unique
    }

    private int validateUserInput(String value, String fieldName) {
        try {
            int parsedValue = Integer.parseInt(value);

            // check if the parsed value is within the valid integer range
            if (parsedValue >= 0 && parsedValue <= 999999) {
                return parsedValue;
            } else {
                System.out.println("Error: " + fieldName + " must be within the valid range. Setting to default value: 0");
                return 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid " + fieldName + ". Setting to default value: 0");
            return 0;
        }
    }



    public void actionPerformed(ActionEvent e) {
        System.out.println("Button pressed: " + e.getActionCommand() + "\n");

        //depending on what button is pressed, run the method
        if (e.getSource() == theApp.button1) {
            handleButton1();
            theApp.repaint();
        } else if (e.getSource() == theApp.button2) {
            handleButton2();
        } else if (e.getSource() == theApp.button3) {
            handleButton3();
        } else if (e.getSource() == theApp.button4) {
            handleButton4();
        }
    }


    private void handleButton1() {
        String regNo = theApp.textField1.getText();
        String colourInput = theApp.textField5.getText();
        String noOfSides = theApp.textField2.getText();
        String startingAngle = theApp.textField3.getText();
        String radius = theApp.textField4.getText();

        int sides = validateUserInput(noOfSides, "Number of Sides");
        int angle = validateUserInput(startingAngle, "Starting Angle");
        int r = validateUserInput(radius, "Radius");

        // check if sides, angle, and radius are valid
        if (sides == 0 || angle == 0 || r == 0) {
            System.out.println("Invalid input for sides, angle, or radius. Please enter valid values.");
            return;
        }

        // validation of the id
        int validatedId = validateId(regNo, theApp.polygonList);
        if (validatedId == 0) {
            return;
        }

        // validation of the colour
        Color pColour = validateColour(colourInput);

        //ensure that colour isnt null
        if (pColour != null) {
            //create a regpolygon object with user input
            RegPolygon polygon = new RegPolygon(sides, angle, r, Integer.parseInt(regNo), pColour);
            theApp.polygonList.add(polygon);
            theApp.setLastDrawnPolygon(polygon);

            System.out.println("\nDetails of the created polygon:");
            System.out.println("RegNo: " + regNo);
            System.out.println("No.Sides: " + noOfSides);
            System.out.println("Starting Angle: " + startingAngle);
            System.out.println("Radius: " + radius);
            System.out.println("Colour: " + colourInput);

            // repaint the central panel
            theApp.repaint();
        } else {
            System.out.println("Invalid colour input: " + colourInput);
        }
    }


    private void handleButton2() {
        // display the list
        for (RegPolygon polygon : theApp.polygonList) {
            System.out.println(polygon.toString());
        }
    }

    private void handleButton3() {
        // finding the polygon based on id and drawing it
        String regNo = theApp.textField1.getText();

        if (regNo.isEmpty()) {
            System.out.println("Invalid input: ID has no value. ");
            return;
        }

        try {
            int searchId = Integer.parseInt(regNo);
            boolean found = false;

            for (RegPolygon polygon : theApp.polygonList) {
                if (polygon.getID() == searchId) {
                    found = true;
                    theApp.setLastDrawnPolygon(polygon);
                    System.out.println("Polygon with ID " + searchId + " found.");
                    break;
                }
            }

            if (!found) {
                System.out.println("Polygon with ID " + searchId + " doesnt exist.");
            }

            // repaint the central panel to draw the searched polygon if found
            theApp.repaint();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: ID must be a valid integer and length of 6.");
        }
    }

    private void handleButton4() {
        // sort in ascending order polygon list
        Collections.sort(theApp.polygonList);
        System.out.println("Sorting the polygon list...");
    }
}
