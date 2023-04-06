package edu.nbu.projectshop.tools;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class GenerateIdNumber {
    private static final int ID_BOUNDARY = 1_000_000;
    private static final Long LONG_ID_BOUNDARY = 1_000_000L;
    public  final static ArrayList<Integer> generatedId = new ArrayList<>();
    public  final static ArrayList<Long> generatedLongId = new ArrayList<>();
    public static @NotNull Integer generateId(){
        Random random = new Random();
        Integer genId = random.nextInt(ID_BOUNDARY);

        while(generatedId.contains(genId)){
            genId = random.nextInt(ID_BOUNDARY);
        }
        generatedId.add(genId);
        return genId;
    }
    public static @NotNull Long generateLongId(){
        Random random = new Random();
        Long genId = random.nextLong();

        while(generatedId.contains(genId)){
            genId = random.nextLong();
        }
        generatedLongId.add(genId);
        return genId;
    }
}
