package edu.nbu.projectshop.tools;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class GenerateIdNumber {
    public final static ArrayList<Integer> generatedId = new ArrayList<>();
    public static @NotNull Integer generateId(){
        Random random = new Random();
        Integer genId = random.nextInt(1_000_000);

        while(generatedId.contains(genId)){
            genId = random.nextInt(1_000_000);
        }
        generatedId.add(genId);
        return genId;
    }
}
