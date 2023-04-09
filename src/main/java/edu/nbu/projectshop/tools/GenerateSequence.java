package edu.nbu.projectshop.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class GenerateSequence {
    private GenerateSequence() {}

    public static Optional<Long> generateSeq(String propsName) throws IOException {
        Properties props = new Properties();
        long seqN;
        try (FileInputStream fis = new FileInputStream(propsName)) {
            props.load(fis);
            seqN = Long.parseLong(props.getProperty("sequence").trim());

        }
        try (FileOutputStream fos = new FileOutputStream(propsName)) {
            long seq = seqN;
            props.setProperty("sequence", String.valueOf(++seq));
            props.store(fos, "Updated sequence");
        }
        return Optional.ofNullable(Optional.of(seqN)
                .orElseThrow(() -> new RuntimeException("Problem with sequence")));
    }
}
