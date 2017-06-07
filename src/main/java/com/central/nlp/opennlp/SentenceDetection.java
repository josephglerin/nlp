package com.central.nlp.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by glerin on 25/5/17.
 */
public class SentenceDetection {
    public static void main(String args[]){
        findSentence();
    }

    public static void findSentence(){
        String sentence = " Hi. How are you? Welcome to Tutorialspoint. "
                + "We provide free tutorials on various technologies! Just look that";
        //Loading sentence detector model
        try {
            InputStream inputStream = new FileInputStream("models/opennlp/en-sent.bin");
            SentenceModel model = new SentenceModel(inputStream);
            SentenceDetectorME detector = new SentenceDetectorME(model);
            String sentences[] = detector.sentDetect(sentence);
            List sentenceList = Arrays.asList(sentences);
            System.out.println(sentenceList.size());
           sentenceList.forEach(linses->{
               System.out.println(linses);
           });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
