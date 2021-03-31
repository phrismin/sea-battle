package main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private String name;
    private FieldPlayer fieldPlayer;

    public Player(String name, FieldPlayer fieldPlayer) {
        this.name = name;
        this.fieldPlayer = fieldPlayer;
    }

    public String getValidHitCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", input coordinates (format: x,y)");
        String inputDate = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\d,\\d");
        Matcher matcher = pattern.matcher(inputDate);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid date");
        }
        return inputDate;
    }

    public String getName() {
        return name;
    }
}
