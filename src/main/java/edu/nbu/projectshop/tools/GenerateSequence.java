package edu.nbu.projectshop.tools;


import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;


public class GenerateSequence {
    private GenerateSequence() {}
    private final static Logger logger = Logger.getLogger(GenerateSequence.class);
    public static Optional<Long> generateSeq(String propsName) throws IOException {
        Properties props = new Properties();
        long seqN;
        try (InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsName) ){
            props.load(fis);
            seqN = Long.parseLong(props.getProperty("sequence").trim());

        }
        logger.info(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                .getResource(propsName)).getPath());
        try (FileOutputStream fos = new FileOutputStream
                (Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                        .getResource(propsName)).getPath())) {
            long seq = seqN;
            props.setProperty("sequence", String.valueOf(++seq));
            props.store(fos, "Updated sequence");
        }
        return Optional.ofNullable(Optional.of(seqN)
                .orElseThrow(() -> new RuntimeException("Problem with sequence")));
    }

}
