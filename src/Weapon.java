package src;

public class Weapon {
   
    private String nameWeapon;
    private int baseDmg;
    private float ScalingDmg;
    private int maxdmg;

    Weapon() {

    }

    Weapon(String nameWeapon, int baseDmg, int ScalingDmg) {
        this.nameWeapon = nameWeapon;
        this.baseDmg = baseDmg;
        this.ScalingDmg = ScalingDmg;
    }

    public String getNameWeapon() {
        return nameWeapon;
    }

    public int getBaseWeapon() {
        return baseDmg;
    }

    public float getScalingDmg() {
        return ScalingDmg;
    }

    public void setNameWeapon(String nameWeapon) {
        this.nameWeapon = nameWeapon;
    }

    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
    }

    public void setScalingDmg(int scalingDmg) {
        this.ScalingDmg = scalingDmg;
    }

    public int maxdmg(Weapon w, Player p){
        int maxdmg = w.baseDmg + p.dexterity + p.strength;
        return maxdmg;
    }

    @Override
    public String toString() {
        //return "Nom: " + nameWeapon + "\nbDmg: " + baseDmg + "\nsDmg: " + ScalingDmg;
        return "Arme: " + nameWeapon + "\ndmg: " + maxdmg +"-"+ baseDmg;
    }

}