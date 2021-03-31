package main;

public class DemoSeaBattle {
    private static Player putler;
    private static Player biden;
    private static FieldPlayer bidenField;
    private static FieldPlayer putlerField;
    private static boolean isFinish;

    public static void main(String[] args) {

        FieldPlayer bidenField = new FieldPlayer();
        Player biden = new Player("Biden", bidenField);
        System.out.print(biden.getName() + ", please input coordinates!");
        bidenField.addShip();

        FieldPlayer putlerField = new FieldPlayer();
        Player putler = new Player("Putler", putlerField);
        System.out.print(putler.getName() + ", please input coordinates!");
        putlerField.addShip();

        int choose = (int) (Math.random() * 2);
        if (choose == 0) {
            hitBiden(biden, putlerField);
            if (!putlerField.existsShip()) {
                isFinish = true;
                System.out.println(biden.getName() + " is WIN!");
            }
        }

        while (!isFinish) {
            hitPutler(putler, bidenField);
            if (isFinish) {
                break;
            }
            hitBiden(biden, bidenField);
        }
    }

    private static void hitBiden(Player biden, FieldPlayer putlerField) {
        while (true) {
            String inputDate = biden.getValidHitCoordinates();
            String[] coordinatesArray = inputDate.split(",");
            int j = Integer.parseInt(coordinatesArray[1]); //y
            int i = Integer.parseInt(coordinatesArray[0]); //x
            if (putlerField.damage(i, j)) {
                if (!putlerField.existsShip()) {
                    isFinish = true;
                    System.out.println(biden.getName() + " is WIN!");
                    break;
                }
                continue;
            }
            break;
        }
    }

    private static void hitPutler(Player putler, FieldPlayer bidenField) {
        while (true) {
            String inputDate = putler.getValidHitCoordinates();
            String[] coordinatesArray = inputDate.split(",");
            int j = Integer.parseInt(coordinatesArray[1]); //y
            int i = Integer.parseInt(coordinatesArray[0]); //x
            if (bidenField.damage(i, j)) {
                if (!bidenField.existsShip()) {
                    isFinish = true;
                    System.out.println(putler.getName() + " is WIN!");
                    break;
                }
                continue;
            }
            break;
        }
    }

}
