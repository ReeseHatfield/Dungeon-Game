import java.util.ArrayList;
//Reese Hatfield
public class Character {
    private String name;
    private int health;
    private int row;
    private int col;
    private int damage;
    private int maxDamage;
    //space
    public int getDamage() {return damage;}
    public void setDamage(int damage) {this.damage = damage;}public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public Character(String name, int health, int row, int col, int maxDamage) {//constructor
        this.name = name;
        this.health = health;
        this.row = row;
        this.col = col;
        this.maxDamage = maxDamage;
        this.damage = damage;
    }
    public void move(String direction, int size) {
        health -= 2;//deals 2 damage to the player every time they move
        //all the if states in this switch check to make sure that if they moved, they would still be in
        //the correct bounds, if they would be, they move
        switch (direction) {
            case "north":
                if (this.getRow() - 1 != -1) {
                    this.setRow(this.getRow() - 1);
                    break;
                } else {
                    System.out.println("You can't move that way");
                    break;
                }
            case "south":
                if (this.getRow() != size - 1) {
                    this.setRow(this.getRow() + 1);
                    break;
                } else {
                    System.out.println("You can't move that way");
                    break;
                }
            case "east":
                if (this.getCol() != size - 1) {
                    this.setCol(this.getCol() + 1);
                    break;
                } else {
                    System.out.println("You can't move that way");
                    break;
                }
            case "west":
                if (this.getCol() - 1 != -1) {
                    this.setCol(this.getCol() - 1);
                    break;
                } else {
                    System.out.println("You can't move that way");
                    break;
                }
            default:
                System.out.println("You can't move that way");
                break;
        }
    }
    public void hit(Character other) {//this mehod hits the other person the character is fighting
        damage = (int) (Math.random() * maxDamage) + 1;
        other.setHealth(other.getHealth() - damage);
    }
    public void smell(char[][] dungeon, int size, ArrayList<Character> monsters) {
        int smellNumber = 0;
        for (Character m : monsters) {
           // System.out.println("player at " + this.getRow() + " , " + this.col);
            //System.out.println("monster at at " + m.getRow() + " , " + m.col);
            if (m.getCol() == this.getCol() && m.getRow() == this.getRow() - 1) {
                smellNumber++;
               // System.out.println("monster to the north");
            } else if (m.getCol() == this.getCol() && m.getRow() == this.getRow() + 1) {
                smellNumber++;
               // System.out.println("monster to the south");
            } else if (m.getRow() == this.getRow() && m.getCol() == this.getCol() + 1) {
                smellNumber++;
               // System.out.println("monster to the west");
            } else if (m.getRow() == this.getRow() && m.getCol() == this.getCol() - 1) {
                smellNumber++;
               // System.out.println("monster to the east");
            }
            //other.getRow() == this.row && other.getCol() == this.col - 1
        }
        System.out.println("You smell " + smellNumber + " monsters");
        //this part took the longest, the debug comments tell where any monster WoulD be
    }
}
