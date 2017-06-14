package com.central.nlp.stanford.ngram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NGramPositionExample {

    public static List<List<String>> getNGramsPositions(List<String> items, int minSize, int maxSize) {
        List<List<String>> ngrams = new ArrayList<List<String>>();
        int listSize = items.size();
        for (int i = 0; i < listSize; ++i) {
            for (int ngramSize = minSize; ngramSize <= maxSize; ++ngramSize) {
                if (i + ngramSize <= listSize) {
                    List<String> ngram = new ArrayList<String>();
                    for (int j = i; j < i + ngramSize; ++j) {
                        ngram.add(items.get(j));
                    }
                    ngram.add(Integer.toString(i));
                    ngrams.add(ngram);
                }
            }
        }
        return ngrams;
    }


    public static void main (String[] args) throws IOException {
        String testString = "I have the best car";
        List<String> tokens = Arrays.asList(testString.split(" "));
        List<List<String>> ngramsAndPositions = getNGramsPositions(tokens,1,2);
        for (List<String> np : ngramsAndPositions) {
            System.out.println(Arrays.toString(np.toArray()));
        }
    }
}
