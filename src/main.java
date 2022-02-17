import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class main {
    public static Scanner input = new Scanner(System.in);

    //working dungeon map that is updated as the player moves
    public static void main(String[] args) throws Exception {
        printer("What is your name, heroic adventurer?");
        String name = input.nextLine();
        Character player = new Character(name, 100, 0, 0, 10);
        printer("How wide of a dungeon do you want to face (5-10)?");
        int size = input.nextInt();//this determines the size of the dungeon
        while (true){//validates input
            if (inputValidator(5, 10, size)) {
               //valid input
                break;
            } else {//this is the condition where it is not valid
                printer("That is not a valid dungeon size!");
                System.out.println();
                printer("How wide of a dungeon do you want to face (5-10)?");
                size = input.nextInt();
            }
        }
        ArrayList<Character> monsters = new ArrayList<>();//arraylist that contains all the monsters
        char[][] dungeon = dungeonCreator(size, monsters);//creates the dungeon
        System.out.println();
        dungeon[player.getRow()][player.getCol()] = player.getName().charAt(0);//puts the player at 0,0
        for (char[] x : dungeon)//this just prints the dungeon
        {
            for (char y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        input.nextLine();//clears input buffer
        while (true)//all game logic goes in this loop
        {
            System.out.println(player.getName() + " at " + player.getRow() + " , " + player.getCol() + " with " + player.getHealth() + " health");
            player.smell(dungeon, size, monsters);//smells for monsters
            printer("Which way to you want to go (north, south, east, west)? ");
            String direction = input.nextLine().toLowerCase();//grabs the direction the player wants to move it
            player.move(direction, size);//updates player coordinates
            for (int i = 0; i < dungeon.length; i++) {
                for (int j = 0; j < dungeon[i].length; j++) {
                    if (dungeon[i][j] == player.getName().charAt(0)) {
                        dungeon[i][j] = '0';
                    }
                }
            }//resets the previous room
            if (dungeon[player.getRow()][player.getCol()] == 'M') {//triggers a fight sequence
                fight(player, monsters);
            }
            dungeon[player.getRow()][player.getCol()] = player.getName().charAt(0);//moves the player on the map
            for (char[] x : dungeon)//this prints the dungeon again
            {
                for (char y : x) {
                    System.out.print(y + " ");
                }
                System.out.println();
            }
            if(player.getHealth() <= 0){//lose conditional
                printer("You Died!");
                System.exit(0);
            }
            if (dungeon[size - 1][size - 1] == player.getName().charAt(0)) {
                System.out.println("You have escaped the dungeon!");
                System.exit(0);
            }//win conditional
        }
    }
    public static void printer(String s) throws InterruptedException {
        for (int i = 0; i < s.length(); i++) {
            if (Math.random() * 10 < 1)
                Thread.sleep(200);// This is a cosmetic method that increases the authentic feeling.
            System.out.print(s.charAt(i));
        }
    }
    public static void fight(Character player, ArrayList<Character> monsters) {
        for (int i = 0; i <monsters.size(); i ++) {//loops through all monsters
            Character m = monsters.get(i);
            if (m.getRow() == player.getRow() && m.getCol() == player.getCol()) {
                while (player.getHealth() > 0 && m.getHealth() > 0) {//this while loop fights the monsters in the same room as the player
                    player.hit(m);
                    System.out.println("You hit for " + player.getDamage());
                    m.hit(player);
                    System.out.println("You get hit for " + m.getDamage());
                    if (m.getHealth() <= 0) {
                        System.out.println("Monster(s) was Killed");
                        monsters.remove(m);
                    }
                    if (player.getHealth() <= 0) {//lose conditional
                        System.out.println("You Died!");
                        System.exit(0);
                    }
                }
            }
        }
    }
    public static char[][] dungeonCreator(int size, ArrayList monsters) throws Exception//creates dungeon
    {
        char[][] dungeon = new char[size][size];
        for (char[] chars : dungeon) {
            Arrays.fill(chars, '0');//populates the Array
        }

        for (int i = 0; i < (size * size) / 6; i++) {//spawns in all the monsters
            int monsterRow = (int) (Math.random() * size);
            int monsterCol = (int) (Math.random() * size);
            if (monsterRow == 0 && monsterCol == 0) {
                monsters.add(new Character(("Monster " + i), 25, monsterRow + 1, monsterCol, 5));
                //moves the monster 1 room over if it spawns at (0,0)
                dungeon[monsterRow + 1][monsterCol] = 'M';
            } else {
                monsters.add(new Character(("Monster " + i), 25, monsterRow, monsterCol, 5));
                dungeon[monsterRow][monsterCol] = 'M';
                //spawns monster where its Coordinates tell it to be
            }
            //System.out.println(monsterRow + " , " + monsterCol);//debug
        }
        printer("The dungeon has been created!");
        return dungeon;
    }
    public static boolean inputValidator(int min, int max, int input) {
        return input >= min && input <= max;
    }
}
