package com.central.nlp.opennlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by glerin on 26/5/17.
 */
public class NamedEntityRecognizer {

    public static void main(String[] args) {
         String sentence = "Mike is senior programming manager " +
                "and Rama is a clerk both are working at Tutorialspoint";
        recognizeNameEntity(sentence);
        sentence = "Tutorialspoint is located in Hyderabad";
        recognizeLocationEntity(sentence);
        sentence = "Apache , Google is good search engine." ;
        recognizeOrganizationEntity(sentence);
    }

    static void recognizeNameEntity(String sentence){
        try {

            //tokenise
            String[] tokens = Tokenizer.tokenizeUsingModel(sentence);
            //Loading the NER-person model
            InputStream inputStreamNameFinder = new FileInputStream("models/opennlp/en-ner-person.bin");
            TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
            //Instantiating the NameFinderME class
            NameFinderME nameFinder = new NameFinderME(model);
            //Finding the names in the sentence
            Span spans[] = nameFinder.find(tokens);
            for (Span span : spans)
                System.out.println(span.toString()+"  "+tokens[span.getStart()] );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void recognizeLocationEntity(String sentence){
        try {

            //tokenise
            String[] tokens = Tokenizer.tokenizeUsingModel(sentence);
            //Loading the NER-person model
            InputStream inputStreamNameFinder = new FileInputStream("models/opennlp/en-ner-location.bin");
            TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
            //Instantiating the NameFinderME class
            NameFinderME nameFinder = new NameFinderME(model);
            //Finding the names of a location
            Span nameSpans[] = nameFinder.find(tokens);
            //Printing the spans of the locations in the sentence
            for(Span s: nameSpans)
                System.out.println(s.toString()+"  "+tokens[s.getStart()]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void recognizeOrganizationEntity(String sentence){
        try {

            //tokenise
            String[] tokens = Tokenizer.tokenizeUsingModel(sentence);
            //Loading the NER-person model
            InputStream inputStreamNameFinder = new FileInputStream("models/opennlp/en-ner-organization.bin");
            TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
            //Instantiating the NameFinderME class
            NameFinderME nameFinder = new NameFinderME(model);
            //Finding the names in the sentence
            Span spans[] = nameFinder.find(tokens);
            for (Span span : spans)
                System.out.println(span.toString()+"  "+tokens[span.getStart()] );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void recognizeNameAndOrganization(String sentence){
        try {
        //tokenise
        String[] tokens = Tokenizer.tokenizeUsingModel(sentence);
        //Loading the NER-person model
        InputStream inputStreamNameFinder = new FileInputStream("models/opennlp/en-ner-person.bin");
            InputStream inputStreamOrgFinder = new FileInputStream("models/opennlp/en-ner-organization.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        //Instantiating the NameFinderME class
        NameFinderME nameFinder = new NameFinderME(model);
        //Finding the names in the sentence
        Span spans[] = nameFinder.find(tokens);
        for (Span span : spans)
            System.out.println(span.toString()+"  "+tokens[span.getStart()] );

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

}
