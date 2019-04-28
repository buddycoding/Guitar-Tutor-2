/*
 * Copyright 2016 andryr
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.guitartutor;

import android.content.Context;

/**
 * Created by andry on 24/04/16.
 */
public class Tuning {
    public String name;
    public Pitch[] pitches;

    public Tuning(String name, Pitch[] pitches) {
        this.name = name;
        this.pitches = pitches;
    }

    public Pitch closestPitch(float freq) {
        Pitch closest = null;
        float dist = Float.MAX_VALUE;
        for (Pitch pitch : pitches) {
            float d = Math.abs(freq - pitch.frequency);
            if (d < dist) {
                closest = pitch;
                dist = d;
            }
        }
        return closest;
    }

    public int closestPitchIndex(float freq) {
        int index = -1;
        float dist = Float.MAX_VALUE;
        for (int i = 0; i < pitches.length; i++) {
            Pitch pitch = pitches[i];
            float d = Math.abs(freq - pitch.frequency);
            if (d < dist) {
                index = i;
                dist = d;
            }
        }
        return index;
    }

    public static Tuning getTuning(Context context, String name) {
        if (name.equals(context.getString(R.string.standard_tuning_val))) {
            return new Tuning(name,
                    new Pitch[]{
                            new Pitch(82.41F, "E"),
                            new Pitch(110.00F, "A"),
                            new Pitch(146.83F, "D"),
                            new Pitch(195.99F, "G"),
                            new Pitch(246.94F, "B"),
                            new Pitch(329.63F, "E"),
                    });
        }
        else if (name.equals(context.getString(R.string.open_a_tuning_val))) {
            return new Tuning(name,
                    new Pitch[]{
                            new Pitch(82.41F, "E"),
                            new Pitch(110.00F, "A"),
                            new Pitch(164.81F, "E"),
                            new Pitch(220.00F, "A"),
                            new Pitch(277.18F, "C"),
                            new Pitch(329.63F, "E"),
                    });
        }
        else if (name.equals(context.getString(R.string.open_g_tuning_val))) {
            return new Tuning(name,
                    new Pitch[]{
                            new Pitch(73.42F, "D"),
                            new Pitch(98.00F, "G"),
                            new Pitch(146.83F, "D"),
                            new Pitch(196.00F, "G"),
                            new Pitch(246.94F, "B"),
                            new Pitch(293.66F, "D"),
                    });
        }
        else if (name.equals(context.getString(R.string.open_d_tuning_val))) {
            return new Tuning(name,
                    new Pitch[]{
                            new Pitch(73.42F, "D"),
                            new Pitch(110.00F, "A"),
                            new Pitch(146.83F, "D"),
                            new Pitch(185.00F, "F"),
                            new Pitch(220.00F, "A"),
                            new Pitch(293.66F, "D"),
                    });
        }
        else if (name.equals(context.getString(R.string.drop_d_tuning_val))) {
            return new Tuning(name,
                    new Pitch[]{
                            new Pitch(73.42F, "D"),
                            new Pitch(110.00F, "A"),
                            new Pitch(146.83F, "D"),
                            new Pitch(196.00F, "G"),
                            new Pitch(246.94F, "B"),
                            new Pitch(329.63F, "E"),
                    });
        }
        else if (name.equals("Custom")) {
            return new Tuning(name,
                    new Pitch[]{
                            new Pitch(82.4069F,"E2"),
                            new Pitch(87.3071F,"F2"),
                            new Pitch(92.4986F,"F#2"),
                            new Pitch(97.9989F,"G2"),
                            new Pitch(103.826F,"G#2"),
                            new Pitch(110F,"A2"),
                            new Pitch(116.541F,"A#2"),
                            new Pitch(123.471F,"B2"),
                            new Pitch(130.813F,"C3"),
                            new Pitch(138.591F,"C#3"),
                            new Pitch(146.832F,"D3"),
                            new Pitch(155.563F,"D#3"),
                            new Pitch(164.814F,"E3"),
                            new Pitch(174.614F,"F3"),
                            new Pitch(184.997F,"F#3"),
                            new Pitch(195.998F,"G3"),
                            new Pitch(207.652F,"G#3"),
                            new Pitch(220F,"A3"),
                            new Pitch(233.082F,"A#3"),
                            new Pitch(246.942F,"B3"),
                            new Pitch(261.626F,"C4 "),
                            new Pitch(277.183F,"C#4"),
                            new Pitch(293.665F,"D4"),
                            new Pitch(311.127F,"D#4"),
                            new Pitch(329.628F,"E4"),
                            new Pitch(349.228F,"F4"),
                            new Pitch(369.994F,"F#4"),
                            new Pitch(391.995F,"G4"),
                            new Pitch(415.305F,"G#4"),
                    });
        }
        return null;
    }


}
