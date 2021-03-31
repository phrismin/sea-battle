package main;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldPlayer {
    private static final int MIN_BORDER_FIELD = -1;
    private static final int MAX_BORDER_FIELD = 10;
    private static final String EMPTY = "🌊";
    private static final String BARRIER = "⬜";
    private static final String FIRE = "\uD83D\uDD25";
    private static final String SHIP = "\uD83D\uDEE5";
    private final String[][] FIELD = new String[10][10];

    public FieldPlayer() {
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[i].length; j++) {
                FIELD[i][j] = EMPTY;
            }
        }
    }

    public void addShip() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
//                System.out.println("Input coordinates TorpedoBoat1 (format: x,y)");
//                String coordinatesTorpedoBoat1 = scanner.nextLine();
//                validInputDateTorpedoBoat(coordinatesTorpedoBoat1);
//                System.out.println("Input coordinates TorpedoBoat2 (format: x,y)");
//                String coordinatesTorpedoBoat2 = scanner.nextLine();
//                validInputDateTorpedoBoat(coordinatesTorpedoBoat2);
//                System.out.println("Input coordinates TorpedoBoat3 (format: x,y)");
//                String coordinatesTorpedoBoat3 = scanner.nextLine();
//                setCoordinatesShip(coordinatesTorpedoBoat3);
                //        System.out.println("Input coordinates TorpedoBoat4 (format: x,y)");
                //        String coordinatesTorpedoBoat4 = scanner.nextLine();
                //        setCoordinatesShip(coordinatesTorpedoBoat4)

                System.out.println("Input coordinates Destroyer1 (format: x,y;x,y)");
                String coordinatesDestroyer1 = scanner.nextLine();
                validCoordinatesDestroyer(coordinatesDestroyer1);
                //        System.out.println("Input coordinates Destroyer2 (format: x,y;x,y)");
                //        String coordinatesDestroyer2 = scanner.nextLine();
                //        setCoordinatesShip(coordinatesDestroyer2);
                //        System.out.println("Input coordinates Destroyer3 (format: x,y;x,y)");
                //        String coordinatesDestroyer3 = scanner.nextLine();
                //        setCoordinatesShip(coordinatesDestroyer3);
                //
                //        System.out.println("Input coordinates Cruiser1 (format: x,y;x,y;x,y)");
                //        String coordinatesCruiser1 = scanner.nextLine();
                //        setCoordinatesCruiser(coordinatesCruiser1);
                //        System.out.println("Input coordinates Cruiser2 (format: x,y;x,y;x,y)");
                //        String coordinatesCruiser2 = scanner.nextLine();
                //        validInputDateCruiser(coordinatesCruiser2);

                //        System.out.println("Input coordinates Battleship (format: x,y;x,y;x,y;x,y;x,y)");
                //        String coordinatesBattleship = scanner.nextLine();
                //        setCoordinatesShip(coordinatesBattleship);
                //        } catch (IllegalArgumentException e) {
                //            System.err.println(e.getMessage());
                //        }
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                deleteShips();
            }
        }
    }

    private void validCoordinatesBattleship(String coordinates) throws IllegalArgumentException {
        Pattern pattern= Pattern.compile("\\d,\\d;\\d,\\d;\\d,\\d;\\d,\\d");
        Matcher matcher = pattern.matcher(coordinates);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
        setCoordinatesBigShip(coordinates);
    }

    private void validInputDateCruiser(String coordinates) throws IllegalArgumentException {
        Pattern pattern= Pattern.compile("\\d,\\d;\\d,\\d;\\d,\\d");
        Matcher matcher = pattern.matcher(coordinates);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid date");
        }
        setCoordinatesBigShip(coordinates);
    }

    private void validCoordinatesDestroyer(String coordinates) throws IllegalArgumentException {
        Pattern patternDestroyer = Pattern.compile("\\d,\\d;\\d,\\d");
        Matcher matcherDestroyer = patternDestroyer.matcher(coordinates);
        if (!matcherDestroyer.matches()) {
            throw new IllegalArgumentException("Invalid date");
        }
        setCoordinatesBigShip(coordinates);
    }

    private void setCoordinatesBigShip(String coordinates) {
        String[] coordinatesShipArray = coordinates.split(";");
        for (String s : coordinatesShipArray) {
            String[] coordinatesArray = s.split(",");
            int i = Integer.parseInt(coordinatesArray[1]);
            int j = Integer.parseInt(coordinatesArray[0]);
            if (FIELD[i][j] == SHIP) {
                throw new IllegalArgumentException("Set at a ship");
            }
            FIELD[i][j] = SHIP;
        }
        validPosition();
        setBarrier();
        printField();
    }

    private void validInputDateTorpedoBoat(String coordinates) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("\\d,\\d");
        Matcher matcher = pattern.matcher(coordinates);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid date");
        }
        setCoordinatesSmallShip(coordinates);
    }

    private void setCoordinatesSmallShip(String coordinates) {
        String[] coordinatesArray = coordinates.split(",");
        int j = Integer.parseInt(coordinatesArray[1]);
        int i = Integer.parseInt(coordinatesArray[0]);
        if (FIELD[j][i] == SHIP) {
            throw new IllegalArgumentException("Set at a ship or near for SHIP or invalid ship");
        }
        FIELD[j][i] = SHIP;
        setBarrier();
        printField();
    }


    private void validPosition() throws IllegalArgumentException {
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[i].length; j++) {
                if (FIELD[i][j].equals(SHIP)) {
                    if (j - 1 > MIN_BORDER_FIELD && i - 1 > MIN_BORDER_FIELD && FIELD[i-1][j-1] == SHIP) {
                        throw new IllegalArgumentException("Near for SHIP or invalid ship");
                    }
                    if (i - 1 > MIN_BORDER_FIELD && j + 1 < MAX_BORDER_FIELD && FIELD[i-1][j+1] == SHIP) {
                        throw new IllegalArgumentException("Near for SHIP or invalid ship");
                    }
                    if (i + 1 < MAX_BORDER_FIELD && j - 1 > MIN_BORDER_FIELD && FIELD[i+1][j-1] == SHIP) {
                        throw new IllegalArgumentException("Near for SHIP or invalid ship");
                    }
                    if (i + 1 < MAX_BORDER_FIELD && j + 1 < MAX_BORDER_FIELD && FIELD[i+1][j+1] == SHIP) {
                        throw new IllegalArgumentException("Near for SHIP or invalid ship");
                    }
                }
            }
        }
    }

    private void setBarrier() {
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[i].length; j++) {
                if (FIELD[i][j].equals(SHIP)) {
                    if (i - 1 > MIN_BORDER_FIELD && j - 1 > MIN_BORDER_FIELD && FIELD[i - 1][j - 1] != SHIP) {
                        FIELD[i - 1][j - 1] = BARRIER;
                    }
                    if (i - 1 > MIN_BORDER_FIELD && FIELD[i - 1][j] != SHIP) {
                        FIELD[i - 1][j] = BARRIER;
                    }
                    if (i - 1 > MIN_BORDER_FIELD && j + 1 < MAX_BORDER_FIELD && FIELD[i - 1][j + 1] != SHIP) {
                        FIELD[i - 1][j + 1] = BARRIER;
                    }
                    if (j - 1 > MIN_BORDER_FIELD && FIELD[i][j - 1] != SHIP) {
                        FIELD[i][j - 1] = BARRIER;
                    }
                    if (j + 1 < MAX_BORDER_FIELD && FIELD[i][j + 1] != SHIP) {
                        FIELD[i][j + 1] = BARRIER;
                    }
                    if (i + 1 < MAX_BORDER_FIELD && j - 1 > MIN_BORDER_FIELD && FIELD[i + 1][j - 1] != SHIP) {
                        FIELD[i + 1][j - 1] = BARRIER;
                    }
                    if (i + 1 < MAX_BORDER_FIELD && FIELD[i + 1][j] != SHIP) {
                        FIELD[i + 1][j] = BARRIER;
                    }
                    if (i + 1 < MAX_BORDER_FIELD && j + 1 < MAX_BORDER_FIELD && FIELD[i + 1][j + 1] != SHIP) {
                        FIELD[i + 1][j + 1] = BARRIER;
                    }
                }
            }
        }
    }

    public boolean existsShip() {
        for (String[] strings : FIELD) {
            for (int j = 0; j < strings.length; j++) {
                if (strings[j] == SHIP) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean damage(int x, int y) {
        if (FIELD[y][x] == SHIP) {
            FIELD[y][x] = FIRE;
            if (y - 1 > MIN_BORDER_FIELD && FIELD[x][y-1] == SHIP || x - 1 > MIN_BORDER_FIELD && FIELD[x-1][y] == SHIP
                    || y + 1 < MAX_BORDER_FIELD && FIELD[x][y+1] == SHIP || x + 1 < MAX_BORDER_FIELD && FIELD[x+1][y] == SHIP) {
                System.out.println("Fire!");
                printField();
                return true;
            }
            System.out.println("Bang!");
            printField();

            return true;

        }
        System.out.println("Miss!");
        return false;
    }

    private void deleteShips() {
        for (String[] strings : FIELD) {
            Arrays.fill(strings, EMPTY);
        }
    }

    private void printField() {
        for (String[] strings : FIELD) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }
}