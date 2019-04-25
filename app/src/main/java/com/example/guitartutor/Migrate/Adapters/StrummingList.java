package com.example.guitartutor.Migrate.Adapters;

import java.util.ArrayList;

public class StrummingList {
    private String mImageName;
    private String mCaption;
    private String mCaptionTitle;

    public StrummingList(String image, String caption, String title){
        mImageName = image;
        mCaption = caption;
        mCaptionTitle = title;
    }

    public String getImageName() {
        return mImageName;
    }
    public String getCaption() {
        return mCaption;
    }
    public String getTitle() {
        return mCaptionTitle;
    }

    public static ArrayList<StrummingList> createStrummingList(){
        ArrayList<StrummingList> list = new ArrayList<>();
        list.add(new StrummingList("strum_tutor_01", "Strumming patterns involves strumming down and strumming up. Sometimes, players get overly focused by when their hand needs to go down or up. Don't forget: strumming a guitar is all about timing and creating a solid rhythm. You need to think like a drummer: your main job is to keep up the groove.\n" +
                "\n" +
                "That’s why it’s super important to always hear the groove you want to create in your head first. You need to know what you want the rhythm to sound like before you can create it. This one of the reasons I spent many hours looking for song examples to include: I want to make sure you know what you want to play. It’s a good idea to check if you really know the strumming groove by singing, humming, beatboxing or tapping it before you grab your guitar.", "LISTEN TO THE RHYTHM"));
        list.add(new StrummingList("strum_tutor_02", "So how do you strum a guitar correctly? Firstly, make sure you're not locking your wrist: you need to keep it nice and loose. Most of the movement will come from rotating your lower arm (you could also say: twisting your wrist) and letting your hand and wrist hang loose and follow that motion (Funk guitarist Ross Bolton calls this a ‘drunken wrist’. I like that.) You might be tempted to make the up and down movement with your lower arm, but though your lower arm will be moving up and down a little bit as well, but that’s only a small part of the movement.", "KEEP A LOOSE WRIST"));
        list.add(new StrummingList("strum_tutor_03", "You need to keep your strumming hand moving at all times, even when you’re not hitting any strings for a couple of beats. By doing this, you’ll no longer have to consciously think about when you need to move your strumming hand: it’s moving all the time. The constant motion also makes it much easier to keep time.", "NEVER STOP MOVING YOUR STRUMMING HAND"));
        list.add(new StrummingList("strum_tutor_04", "You might think you need to hit all the notes in the chord with every strum, but usually we hit just three or four strings. So how do you know which strings to aim for? That depends. Remember that your main job is to keep up the groove. To do that you want to emphasize certain parts of the beat. A very rough rule of thumb is that you’ll want to hit lower strings on the first and third beat, and high strings on the second and fourth beat.", "DON’T HIT ALL THE STRINGS WITH EVERY STRUM"));

        return list;
    }

    public static ArrayList<StrummingList> createStrummingList2(){
        ArrayList<StrummingList> list = new ArrayList<>();
        list.add(new StrummingList("strum_tutor_stroke_01", "When strumming a guitar, you’ll use both downstrokes (i.e. where your picking hand moves down) and upstrokes (where your picking hand moves back up). There’s a simple logic behind when you should use a downstroke and when up, which we’ll get to shortly.\n" +
                "\n" +
                "All the strum patterns we’ll be looking at in this section are in a 4/4 measure. In short, that means that every measure consists of four beats. For this first pattern, simply play a downstroke on every beat.", "DOWNSTROKES"));

        list.add(new StrummingList("strum_tutor_stroke_02","Now that you’ve gotten a basic feel for this strumming thing, the next step is to start adding some upstrokes. Here’s a pattern which is similar to the first pattern we saw, but with two upstrokes added in.", "ADDING UPSTROKES"));

        list.add(new StrummingList("strum_tutor_stroke_03", "Now, you might have noticed a certain logic in when we’re playing downstrokes and when we’re playing upstrokes: all the downstrokes are on the beat: on the 1, 2, 3 or 4. All the upstrokes are what we call ‘off the beat’: right between the 1, 2, 3 and 4. They’re always on the ‘&’. This next pattern shows this basic logic:", ""));

        list.add(new StrummingList("strum_tutor_stroke_04", "This is a pretty common pattern. ", ""));

        list.add(new StrummingList("strum_tutor_stroke_05","Once you’re comfortable playing down and upstrokes, there’s just one more thing you need to learn to complete your basic strumming technique. So far, you might've noticed two things:\n" +
                "\n" +
                "1. You’re strumming hand keeps moving up and down all the time.\n" +
                "2. You’re playing downstrokes on the beat (1, 2, 3, 4) and upstrokes off the beat (on the &).\n" +
                "\n" +
                "I chose strumming patterns up until now where it feels very natural to do this. But there are also patterns where you might be tempted to break these ‘rules’. Take a look at this next strum pattern for example:", "UPSTROKES FOLLOWING UPSTROKES"));

        list.add(new StrummingList("strum_tutor_stroke_06", "This is a very common strum pattern.", ""));

        list.add(new StrummingList("strum_tutor_stroke_07", "What makes this pattern difficult, is that the long pause makes it tempting to stop moving your hand. But keep your hand moving at all times! Even when you’re not hitting any strings, your hand needs to keep moving up and down. This is essential to keeping a steady rhythm.", ""));

        return list;
    }
}
