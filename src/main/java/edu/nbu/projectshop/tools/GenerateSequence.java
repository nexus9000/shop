package edu.nbu.projectshop.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class GenerateSequence {

    public static Optional<Integer> generateSeq(String propsName)throws IOException{
        FileInputStream fis = new FileInputStream(propsName);
        Properties props = new Properties();
        props.load(fis);
        Integer seqN = Integer.parseInt(props.getProperty("sequence"));
        fis.close();
        props.setProperty("sequence", (seqN++).toString());
        return Optional.ofNullable(Optional.ofNullable(seqN)
                .orElseThrow(() -> new RuntimeException("Problem with sequence")));
    }
}
