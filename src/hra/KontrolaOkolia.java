package hra;

import bytosti.GameObjects;

public class KontrolaOkolia {
    private final int vzdialenost = 120;

    public KontrolaOkolia() {
    }
    public boolean kontrolaOkolia(GameObjects objekt1, GameObjects objekt2) {
        double deltaX = objekt1.getPoziciaX() - objekt2.getPoziciaX();
        double deltaY = objekt1.getPoziciaY() - objekt2.getPoziciaY();
        double vzdialenostHraca = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (vzdialenostHraca < this.vzdialenost) {
            return true;
        }
        return false;

    }
}
