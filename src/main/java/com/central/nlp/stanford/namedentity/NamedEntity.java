package com.central.nlp.stanford.namedentity;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by glerin on 1/6/17.
 */
public class NamedEntity {

    public static LinkedHashMap<String,LinkedHashSet<String>> identifyNER(String text, String model)
    {
        LinkedHashMap <String,LinkedHashSet<String>> map=new <String,LinkedHashSet<String>>LinkedHashMap();
        String serializedClassifier = model;
        System.out.println(serializedClassifier);
        CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
        List<List<CoreLabel>> classify = classifier.classify(text);
        for (List<CoreLabel> coreLabels : classify)
        {
            for (CoreLabel coreLabel : coreLabels)
            {
                String word = coreLabel.word();
                String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
                if(!"O".equals(category))
                {
                    if(map.containsKey(category))
                    {
                        // key is already their just insert in arraylist
                        map.get(category).add(word);
                    }
                    else
                    {
                        LinkedHashSet<String> temp=new LinkedHashSet<String>();
                        temp.add(word);
                        map.put(category,temp);
                    }
                    System.out.println(word+":"+category);
                }

            }

        }
        return map;
    }

    public static void main(String args[])
    {
        String content="Sachin Ramesh Tendulkar (Listeni/ˌsətʃɪn tɛnˈduːlkər/; Marathi: "
                + " सचिन रमेश तेंडुलकर; born 24 April 1973) is an Indian former cricketer widely "
                + " acknowledged as the greatest batsman of the modern generation, popularly holds the title \"God of Cricket\" among his fans [2] He is also acknowledged as the greatest cricketer of all time.[6][7][8][9] He took up cricket at the age of eleven, made his Test debut against Pakistan at the age of sixteen, and went on to represent Mumbai domestically and India internationally for close to twenty-four years. He is the only player to have scored one hundred international centuries, the first batsman to score a Double Century in a One Day International, and the only player to complete more than 30,000 runs in international cricket.[10] In October 2013, he became the 16th player and first Indian to aggregate "
                + " 50,000 runs in all recognized cricket "
                + " First-class, List A and Twenty20 combined peaceful interesting and informative)";

//        System.out.println(identifyNER(content, "models/stanford/english.conll.4class.distsim.crf.ser.gz").toString());
        System.out.println(identifyNER(content, "/home/glerin/workspace/projects/nlp/models/stanford/data/training_datamodel1110.ser.gz").toString());
    }

}
