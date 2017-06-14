package com.central.nlp.stanford.namedentity;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.util.StringUtils;

import java.util.Properties;

/**
 * Created by glerin on 1/6/17.
 */
public class ModelTraining {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String prop = "/home/glerin/workspace/projects/nlp/models/stanford/data/nerprop.prop";
        Properties props = StringUtils.propFileToProperties(prop);
//        String to = props.getProperty("serializeTo");
//        props.setProperty("serializeTo", "models/stanford/data/training_datamodel.ser.gz");
        SeqClassifierFlags flags = new SeqClassifierFlags(props);
        CRFClassifier<CoreLabel> crf = new CRFClassifier<CoreLabel>(flags);
        crf.train();
        crf.serializeClassifier("models/stanford/data/training_datamodel1110.ser.gz");

    }
}
