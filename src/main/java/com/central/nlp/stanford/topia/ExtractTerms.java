package com.central.nlp.stanford.topia;

import com.sree.textbytes.jtopia.Configuration;
import com.sree.textbytes.jtopia.TermDocument;
import com.sree.textbytes.jtopia.TermsExtractor;
import java.io.*;


public class ExtractTerms {

    private static final String INPUT_FILE = "input/example.txt";

    public static void extractTermsDefault(){
        //for default lexicon POS tags
        Configuration.setTaggerType("default");
        Configuration.setSingleStrength(3);
        Configuration.setNoLimitStrength(2);
        // if tagger type is "default" then give the default POS lexicon file
        Configuration.setModelFileLocation("models/default/english-lexicon.txt");
        TermsExtractor termExtractor = new TermsExtractor();
        TermDocument topiaDoc = new TermDocument();
        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream("data/example.txt");
        } catch (FileNotFoundException e) {
        }
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line+"\n");
            }
        } catch (IOException e) {

        }
        topiaDoc = termExtractor.extractTerms(stringBuffer.toString());
        System.out.println("Extracted terms : "+topiaDoc.getExtractedTerms());
        System.out.println("Final Filtered Terms : "+topiaDoc.getFinalFilteredTerms());
    }

    public static void extractTermsStanfordNLP(){

        // for openNLP POS tagger
        Configuration.setTaggerType("openNLP");
        Configuration.setSingleStrength(3);
        Configuration.setNoLimitStrength(2);
        // if tagger type is "openNLP" then give the openNLP POS tagger path
        Configuration.setModelFileLocation("models/opennlp/en-pos-maxent.bin");
        TermsExtractor termExtractor = new TermsExtractor();
        TermDocument topiaDoc = new TermDocument();

        StringBuffer stringBuffer = new StringBuffer();

        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream("data/example.txt");
        } catch (FileNotFoundException e) {
        }

        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line+"\n");
            }
        } catch (IOException e) {

        }

        topiaDoc = termExtractor.extractTerms(stringBuffer.toString());
        System.out.println("Extracted terms : "+topiaDoc.getExtractedTerms());
        System.out.println("Final Filtered Terms : "+topiaDoc.getFinalFilteredTerms());
    }

    public static void extractTermsOpenNLP(){
        //for default lexicon POS tags
        //Configuration.setTaggerType("default");
        // for openNLP POS tagger
        //Configuration.setTaggerType("openNLP");
        //for Stanford POS tagger
        Configuration.setTaggerType("stanford");
        Configuration.setSingleStrength(3);
        Configuration.setNoLimitStrength(2);
        // if tagger type is "openNLP" then give the openNLP POS tagger path
        //Configuration.setModelFileLocation("model/openNLP/en-pos-maxent.bin");
        // if tagger type is "default" then give the default POS lexicon file
        //Configuration.setModelFileLocation("model/default/english-lexicon.txt");
        // if tagger type is "stanford "
        Configuration.setModelFileLocation("models/stanford/data/tagger/english-left3words-distsim.tagger");

        TermsExtractor termExtractor = new TermsExtractor();
        TermDocument topiaDoc = new TermDocument();

        StringBuffer stringBuffer = new StringBuffer();

        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream("data/example.txt");
        } catch (FileNotFoundException e) {
        }

        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line+"\n");
            }
        } catch (IOException e) {

        }

        topiaDoc = termExtractor.extractTerms(stringBuffer.toString());
        System.out.println("Extracted terms : "+topiaDoc.getExtractedTerms());
        System.out.println("Final Filtered Terms : "+topiaDoc.getFinalFilteredTerms());
    }

    public static void test(){
        //for default lexicon POS tags
        //Configuration.setTaggerType("default");
        // for openNLP POS tagger
        //Configuration.setTaggerType("openNLP");
        //for Stanford POS tagger
        Configuration.setTaggerType("stanford");
        Configuration.setSingleStrength(3);
        Configuration.setNoLimitStrength(2);
        // if tagger type is "openNLP" then give the openNLP POS tagger path
        //Configuration.setModelFileLocation("model/openNLP/en-pos-maxent.bin");
        // if tagger type is "default" then give the default POS lexicon file
        //Configuration.setModelFileLocation("model/default/english-lexicon.txt");
        // if tagger type is "stanford "
        Configuration.setModelFileLocation("models/stanford/data/tagger/english-left3words-distsim.tagger");

        TermsExtractor termExtractor = new TermsExtractor();
        TermDocument topiaDoc = new TermDocument();

        StringBuffer stringBuffer = new StringBuffer();

        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream("data/example.txt");
        } catch (FileNotFoundException e) {
        }

        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line+"\n");
            }
        } catch (IOException e) {

        }

        topiaDoc = termExtractor.extractTerms(stringBuffer.toString());
        System.out.println("Extracted terms : "+topiaDoc.getExtractedTerms());
        System.out.println("Final Filtered Terms : "+topiaDoc.getFinalFilteredTerms());
    }

    public static void main(String[] args) {
//        test();
//        extractTermsDefault();
        extractTermsOpenNLP();
    }
}
