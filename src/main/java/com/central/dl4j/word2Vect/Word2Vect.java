package com.central.dl4j.word2Vect;

import lombok.extern.slf4j.Slf4j;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.InMemoryLookupCache;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.FileNotFoundException;
import java.util.Collection;

@Slf4j
public class Word2Vect {


    public static void main(String[] args) throws Exception{

        System.out.println("Load and vectorize sentences");
        final String inputFilePath = new ClassPathResource("raw_sentences.txt").getFile().getAbsolutePath();
        // Strip white space before and after for each line
        SentenceIterator sentenceIterator = new BasicLineIterator(inputFilePath);
        // Split on white spaces in the line to get words
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
             /*
            CommonPreprocessor will apply the following regex to each token: [\d\.:,"'\(\)\[\]|/?!;]+
            So, effectively all numbers, punctuation symbols and some special symbols are stripped off.
            Additionally it forces lower case for all tokens.
         */

        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

        // manual creation of VocabCache and WeightLookupTable usually isn't necessary
        // but in this case we'll need them
        InMemoryLookupCache cache = new InMemoryLookupCache();
        WeightLookupTable<VocabWord> table = new InMemoryLookupTable.Builder<VocabWord>()
                .vectorLength(100)
                .useAdaGrad(false)
                .cache(cache)
                .lr(0.025f).build();


        System.out.println("Building model");
        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(sentenceIterator)
                .tokenizerFactory(tokenizerFactory)
                .build();

        System.out.println("Fitting Word2Vec model....");
        vec.fit();

        System.out.println("Writing word vectors to text file....");

        // Write word vectors to file
        WordVectorSerializer.writeWordVectors(vec, "vectorResult.txt");

        // Prints out the closest 10 words to "day". An example on what to do with these Word Vectors.
        System.out.println("Closest Words:");
        Collection<String> list = vec.wordsNearest("day",10);
        System.out.println("10 Words closest to 'day': " + list);

//        upTrain();

//        final String inputFilePath = new ClassPathResource("raw_sentences.txt").getFile().getAbsolutePath();
         /*
            Let's assume that some time passed, and now we have new corpus to be used to weights update.
            Instead of building new model over joint corpus, we can use weights update mode.
         */
        Word2Vec word2Vec = WordVectorSerializer.loadFullModel("/home/dxuser/workspace/works/nlp/nlp/" +
                "vectorResult.txt");

        /*
            PLEASE NOTE: after model is restored, it's still required to set SentenceIterator and TokenizerFactory, if you're going to train this model
         */
        SentenceIterator iterator = new BasicLineIterator("/home/dxuser/workspace/works/nlp/nlp/vectorResult.txt");
        TokenizerFactory tokenizerFactory1 = new DefaultTokenizerFactory();
        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

        word2Vec.setSentenceIterator(iterator);
        word2Vec.setTokenizerFactory(tokenizerFactory1);

        log.info("Word2vec uptraining...");
        word2Vec.fit();

        Collection<String> list1 = word2Vec.wordsNearest("day", 10);
        log.info("Closest words to 'day' on 2nd run: " + list1);

        /*
            Model can be saved for future use now
         */


    }

    public static void upTrain() throws FileNotFoundException {
        final String inputFilePath = new ClassPathResource("raw_sentences.txt").getFile().getAbsolutePath();
         /*
            Let's assume that some time passed, and now we have new corpus to be used to weights update.
            Instead of building new model over joint corpus, we can use weights update mode.
         */
        Word2Vec word2Vec = WordVectorSerializer.loadFullModel("vectorResult.txt");

        /*
            PLEASE NOTE: after model is restored, it's still required to set SentenceIterator and TokenizerFactory, if you're going to train this model
         */
        SentenceIterator iterator = new BasicLineIterator(inputFilePath);
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

        word2Vec.setSentenceIterator(iterator);
        word2Vec.setTokenizerFactory(tokenizerFactory);

        log.info("Word2vec uptraining...");
        word2Vec.fit();

        Collection<String> list = word2Vec.wordsNearest("day", 10);
        log.info("Closest words to 'day' on 2nd run: " + list);

        /*
            Model can be saved for future use now
         */




    }

}
