package edu.ucalgary.oop;

import java.util.List;


public class AnimalCounter {
    public static int countBeavers(List<Animal> animals) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Beaver) {
                count++;
            }
        }
        return count;
    }

    public static int countCoyotes(List<Animal> animals) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Coyote) {
                count++;
            }
        }
        return count;
    }

    public static int countFoxes(List<Animal> animals) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Fox) {
                count++;
            }
        }
        return count;
    }
    public static int countPorcupines(List<Animal> animals) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Porcupine) {
                count++;
            }
        }
        return count;
    }
    public static int countRaccoons(List<Animal> animals) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Raccoon) {
                count++;
            }
        }
        return count;
    }
    
    public static int countDiurnal(List<Animal> animals) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Diurnal) {
                count++;
            }
        }
        return count;
    }
    public static int countCrepusculars(List<Animal> animals) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Crepuscular) {
                count++;
            }
        }
        return count;
    }
    public static int countNocturnals(List<Animal> animals) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Nocturnal) {
                count++;
            }
        }
        return count;
    }
}
