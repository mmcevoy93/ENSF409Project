package edu.ucalgary.oop;

import java.util.*;

// Unused Class!!!!!
// TODO - Please get rid of this class
public class AnimalCounter {

    public static int countAnimals(List<Animal> animals, Class<? extends Animal> animalType) {
        int count = 0;
        for (Animal animal : animals) {
            if (animalType.isInstance(animal) && !animal.isOrphaned()) {
                count++;
            }
        }
        return count;
    }

    public static String getAnimalNames(List<Animal> animals, Class<? extends Animal> animalType) {
        StringBuilder namesBuilder = new StringBuilder();
        namesBuilder.append(String.format("%d: ", countAnimals(animals, animalType)));
        for (Animal animal : animals) {
            if (animalType.isInstance(animal) && !animal.isOrphaned()) {
                if (namesBuilder.length() > 3) {
                    namesBuilder.append(", ");
                }
//                namesBuilder.append(animal.getNickname());
            }
        }
        return namesBuilder.toString();
    }

}
