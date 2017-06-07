package com.central.nlp.opennlp;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by glerin on 26/5/17.
 */
public class Tokenizer {

    public static void main(String[] args) {
        final String sentence = " Hi. How are you? Welcome to Tutorialspoint. "
                + "We provide free tutorials on various technologies! Just look that";
        simpleTokenizer(sentence);
        whiteSpaceTokenizer(sentence);
        tokenizeUsingModel(sentence);
    }

    public static List simpleTokenizer(String sentence){
        System.out.println("###########   Simple tokenizer ###################");
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String tokens[] = tokenizer.tokenize(sentence);
        List tokenList = Arrays.asList(tokens);
        System.out.println(tokenList);
        return tokenList;
    }

    public static List whiteSpaceTokenizer(String sentence){
        System.out.println("###########   White Space Tokenizer tokenizer ###################");
        WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;
        String tokens[] = tokenizer.tokenize(sentence);
        List tokenList = Arrays.asList(tokens);
        System.out.println(tokenList);
        return tokenList;
    }


    public static String[] tokenizeUsingModel(String sentence){
      try {
          //Loading the Tokenizer model
          InputStream inputStream = new FileInputStream("models/opennlp/en-token.bin");
          TokenizerModel tokenModel = new TokenizerModel(inputStream);
          TokenizerME tokenizer = new TokenizerME(tokenModel);
          String tokens[] = tokenizer.tokenize(sentence);
          List tokenList = Arrays.asList(tokens);
          System.out.println(tokenList);
          return tokens;
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
    }
}
