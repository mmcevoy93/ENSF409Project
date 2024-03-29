package edu.ucalgary.oop;

import java.util.*;


public class AnimalCounter {
    private static final Map<Class<? extends Animal>, Integer> feedTimeMap = new HashMap<>();
    static {
        feedTimeMap.put(Beaver.class, Beaver.getFeedTime());
        feedTimeMap.put(Coyote.class, Coyote.getFeedTime());
        feedTimeMap.put(Fox.class, Fox.getFeedTime());
        feedTimeMap.put(Porcupine.class, Porcupine.getFeedTime());
        feedTimeMap.put(Raccoon.class, Raccoon.getFeedTime());

    }


  public static int countAnimals(List<Animal> animals, Class<? extends Animal> animalType) {
        int count = 0;
        for (Animal animal : animals) {
            if (animalType.isInstance(animal) && !animal.getOrphaned()) {
                count++;
            }
        }
        return count;
    }

    public static int getTotalFeedTime(List<Animal> animals, Class<? extends Animal> animalType) {
        int feedTimePer = feedTimeMap.getOrDefault(animalType, 0);
        return feedTimePer * countAnimals(animals, animalType);
    }

    public static String getAnimalNames(List<Animal> animals, Class<? extends Animal> animalType) {
        StringBuilder namesBuilder = new StringBuilder();
        for (Animal animal : animals) {
            if (animalType.isInstance(animal) && !animal.getOrphaned()) {
                if (namesBuilder.length() > 0) {
                    namesBuilder.append(", ");
                }
                namesBuilder.append(animal.getName());
            }
        }
        return namesBuilder.toString();
    }

}
