package com.central.nlp.stanford.ngram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class NgramTest2 {

    public static List<String> generateNgramsUpto(String str, int maxGramSize) {

        List<String> sentence = Arrays.asList(str.split("[\\W+]"));

        List<String> ngrams = new ArrayList<String>();
        int ngramSize = 0;
        StringBuilder sb = null;

        //sentence becomes ngrams
        for (ListIterator<String> it = sentence.listIterator(); it.hasNext();) {
            String word = (String) it.next();

            //1- add the word itself
            sb = new StringBuilder(word);
            ngrams.add(word);
            ngramSize=1;
            it.previous();

            //2- insert prevs of the word and add those too
            while(it.hasPrevious() && ngramSize<maxGramSize){
                sb.insert(0,' ');
                sb.insert(0,it.previous());
                ngrams.add(sb.toString());
                ngramSize++;
            }

            //go back to initial position
            while(ngramSize>0){
                ngramSize--;
                it.next();
            }
        }
        return ngrams;
    }

    public static void main(String[] args) {
        generateNgramsUpto("What do you expect in these cases?",3).forEach(a->System.out.print(a));
    }
}
