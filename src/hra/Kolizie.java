package hra;

import bytosti.GameObjects;

public class Kolizie {
    public Kolizie() {
    }

    public boolean kolizia(GameObjects objekt1, GameObjects objekt2) {
        if (objekt1.getPoziciaX() <= objekt2.getPoziciaX()
                && objekt2.getPoziciaX() <= objekt1.getPoziciaX() + 30
                && objekt1.getPoziciaY() <= objekt2.getPoziciaY()
                && objekt2.getPoziciaY() <= objekt1.getPoziciaY() + 30) {
            return true;
        }
        return false;
    }
}