package edu.nbu.projectshop.operations;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GetReceiptsInfo {
    private final String receiptsFilesDir;
    private static final Logger logger = Logger.getLogger(GetReceiptsInfo.class);
    public GetReceiptsInfo(String receiptsFilesDir){
        this.receiptsFilesDir = receiptsFilesDir;
    }

    public void getInfoAllReceipts()throws IOException{
        File[] listDir = new File(receiptsFilesDir).listFiles();

        logger.info("Numbers of invoices are: "+ listDir.length);
        for(File invoice : listDir){
            logger.info("name of single invoice is: "+invoice.getName());
            logger.info("**********Content is: **********************");
            Path path = Paths.get(invoice.getPath());
            String readLine = Files.readAllLines(path).get(0);
            logger.info(readLine);
            logger.info("*************end invoice content************");
        }
    }
}
