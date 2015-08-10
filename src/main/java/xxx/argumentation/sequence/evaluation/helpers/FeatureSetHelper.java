/*
 * Copyright 2015 XXX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xxx.argumentation.sequence.evaluation.helpers;

import de.tudarmstadt.ukp.dkpro.tc.svmhmm.util.OriginalTextHolderFeatureExtractor;
import xxx.argumentation.sequence.feature.clustering.ArgumentSpaceFeatureExtractor;
import xxx.argumentation.sequence.feature.coreference.CoreferenceFeatures;
import xxx.argumentation.sequence.feature.deeplearning.EmbeddingFeatures;
import xxx.argumentation.sequence.feature.discourse.PDTBDiscourseFeatures;
import xxx.argumentation.sequence.feature.lda.LDATopicsFeature;
import xxx.argumentation.sequence.feature.lexical.LemmaLuceneNGramUFE;
import xxx.argumentation.sequence.feature.meta.OrigBIOTokenSequenceMetaDataFeatureGenerator;
import xxx.argumentation.sequence.feature.meta.OrigTokenSequenceMetaDataFeatureGenerator;
import xxx.argumentation.sequence.feature.morpho.POSNgram;
import xxx.argumentation.sequence.feature.semantic.SemanticFrameFeatures;
import xxx.argumentation.sequence.feature.sentiment.StanfordSentimentUFE;
import xxx.argumentation.sequence.feature.structural.FirstAndLastNLemmas;
import xxx.argumentation.sequence.feature.structural.PositionInDocument;
import xxx.argumentation.sequence.feature.structural.PositionInParagraph;
import xxx.argumentation.sequence.feature.syntactic.DependencyTreeDepth;
import xxx.argumentation.sequence.feature.syntactic.ProductionRules;
import xxx.argumentation.sequence.feature.syntactic.SubClausesCount;

import java.util.*;

/**
 * (c) 2015 Ivan Habernal
 */
public class FeatureSetHelper
{
    public static Set<String> getRequiredMetaFeatures()
    {
        return new HashSet<>(Arrays.asList(OriginalTextHolderFeatureExtractor.class.getName(),
                OrigBIOTokenSequenceMetaDataFeatureGenerator.class.getName(),
                OrigTokenSequenceMetaDataFeatureGenerator.class.getName()));
    }

    public static Set<String> getBaselineFeatures()
    {
        Set<String> result = new HashSet<>();
        result.addAll(getRequiredMetaFeatures());

        result.addAll(Collections.singletonList(LemmaLuceneNGramUFE.class.getName()));

        return result;
    }

    public static Set<String> getFS1()
    {
        Set<String> result = new HashSet<>();
        result.addAll(getRequiredMetaFeatures());

        result.addAll(Arrays.asList(
                // FS1
                POSNgram.class.getName(), FirstAndLastNLemmas.class.getName(),
                PositionInDocument.class.getName(), PositionInParagraph.class.getName(),
                DependencyTreeDepth.class.getName(), ProductionRules.class.getName(),
                SubClausesCount.class.getName()));

        return result;
    }

    public static Set<String> getFS2()
    {
        Set<String> result = new HashSet<>();
        result.addAll(getRequiredMetaFeatures());

        result.addAll(Arrays.asList(
                // FS2
                LDATopicsFeature.class.getName(), StanfordSentimentUFE.class.getName()));

        return result;
    }

    public static Set<String> getFS3()
    {
        Set<String> result = new HashSet<>();
        result.addAll(getRequiredMetaFeatures());

        result.addAll(Arrays.asList(
                // FS3
                SemanticFrameFeatures.class.getName(), CoreferenceFeatures.class.getName(),
                PDTBDiscourseFeatures.class.getName()));

        return result;
    }

    public static Set<String> getFS4()
    {
        Set<String> result = new HashSet<>();
        result.addAll(getRequiredMetaFeatures());

        result.addAll(Arrays.asList(
                        // FS4
                        EmbeddingFeatures.class.getName(),
                        ArgumentSpaceFeatureExtractor.class.getName())
        );

        return result;
    }

    public static List<String> getFeatureSet(String featureSet)
    {
        Set<String> result = new HashSet<>();

        if (featureSet.contains("fs0")) {
            result.addAll(getBaselineFeatures());
        }

        if (featureSet.contains("fs1")) {
            result.addAll(getFS1());
        }

        if (featureSet.contains("fs2")) {
            result.addAll(getFS2());
        }

        if (featureSet.contains("fs3")) {
            result.addAll(getFS3());
        }

        if (featureSet.contains("fs4")) {
            result.addAll(getFS4());
        }

        return new ArrayList<>(result);
    }
}
