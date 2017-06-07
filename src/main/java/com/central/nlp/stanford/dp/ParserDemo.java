package com.central.nlp.stanford.dp;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import java.util.List;

public class ParserDemo {


    public static void main(String[] args) {
        LexicalizedParser lp = LexicalizedParser
                .loadModel("/home/dxuser/workspace/works/nlp/nlp/models/stanford/englishPCFG.ser.gz");
        lp.setOptionFlags(new String[] { "-maxLength", "80",
                "-retainTmpSubcategories" });
        String[] sent = { "This", "is", "an", "easy", "sentence", "." };
        List<CoreLabel> rawWords = SentenceUtils.toCoreLabelList(sent);
        Tree parse = lp.apply(rawWords);
        parse.pennPrint();
        System.out.println();

        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
        System.out.println(tdl);
        TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
        tp.printTree(parse);
    }
}
