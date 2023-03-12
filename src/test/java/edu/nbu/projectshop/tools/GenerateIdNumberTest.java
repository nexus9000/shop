package edu.nbu.projectshop.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GenerateIdNumberTest {
    @Test
    @DisplayName("test generation id")
    @Disabled
    void testId() {
        ArrayList<Integer> genId = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            int id = GenerateIdNumber.generateId();
            System.out.println(i + ". " + id);
            Assertions.assertDoesNotThrow(
                    () -> {
                        if (genId.contains(id)) {
                            System.err.println(id);
                            throw new RuntimeException("duplicated ID");
                        }
                    });
            genId.add(id);
        }
    }

}