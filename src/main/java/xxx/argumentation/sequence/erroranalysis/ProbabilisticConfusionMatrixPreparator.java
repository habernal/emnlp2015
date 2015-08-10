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

package xxx.argumentation.sequence.erroranalysis;

import xxx.argumentation.sequence.evaluation.EvalHelper;

import java.io.File;
import java.io.IOException;

/**
 * @author xxx
 */
public class ProbabilisticConfusionMatrixPreparator
{
    public static void main(String[] args)
            throws IOException
    {
        // best system
        File csvPredictions = new File(args[0]);
        System.out.println(
                EvalHelper.createConfusionMatrixFromCSVPredictions(csvPredictions).toString());

    }
}
