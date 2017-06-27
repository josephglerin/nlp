package com.central.nlp.stanford.tfidf;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.stats.ClassicCounter;
import edu.stanford.nlp.stats.Counter;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

public class TearmFrequency {

    float totalNoOfTerms = 0;
    Counter<String> counter = new ClassicCounter<String>();

    public void init() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = new Annotation("I'm so happy about my marks. Thank you so much. Happy to see you");
        pipeline.annotate(annotation);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                word = word.toLowerCase();
                counter.incrementCount(word);
                ++totalNoOfTerms;
            }
        }
        System.out.println(counter);
    }

    public float termFrequency(String word){
        float wordCount =(float) counter.getCount(word);
      return wordCount / totalNoOfTerms;
    }
    public float inverseDocumentFrequency(float documentCounts,float noOfDocumentsWithTerm){
        return (float)Math.log10(documentCounts / noOfDocumentsWithTerm);
    }

    public float findTfIdf(float termFrequency,float idf){
       return termFrequency * idf;
    }

    public static void main(String[] args) {
        TearmFrequency tfs = new TearmFrequency();
        tfs.init();

        //find tf of word happy
        float tf = tfs.termFrequency("happy");
        System.out.println(tf);
        float idf = tfs.inverseDocumentFrequency(10000000, 1000);
        System.out.println(idf);
        float tfidf = tfs.findTfIdf(tf,idf);
        System.out.println(tfidf);
    }
}
