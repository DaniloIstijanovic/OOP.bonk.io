package com.github.daniloistijanovic.bonk.utils;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.File;
import java.util.Objects;

import static com.github.daniloistijanovic.bonk.utils.DebugUtil.debugger;

public class MusicUtil {
    private static String rootPath = "music/";
    private static int numberOfSongs;
    private static String playingFile;

    //staticka klasa
    private MusicUtil() {
    }

    @SuppressWarnings("unused")
    public static String getCurrentPlayingFile() {
        return playingFile;
    }

    public static void init() {

    }

    public static void loadFromDirectory(String dir) {
        File f = new File(dir);
        if (f.exists()) {
            rootPath = dir;
            numberOfSongs = Objects.requireNonNull(f.listFiles()).length;
            debugger.file(numberOfSongs + " song" + (numberOfSongs != 1 ? "s" : "") + " found in " + dir);
        } else {
            debugger.file("Folder " + dir + " does not exist");
        }
    }

    public static void playInternal(String filePath) {
        try {
            AdvancedPlayer playMP3 = new AdvancedPlayer(MiscUtil.getResource(rootPath + filePath));
            debugger.info("Now playing song: " + playingFile);
            playMP3.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void playLoop(String fileName) {
        debugger.info("Repeat mode: one");
        String[] pathNames = new File(rootPath).list();
        do {
            playingFile = fileName;
            playInternal(playingFile);
        } while (true);
    }

    // NONE NONE, RANDOM NONE, CHOSEN NONE, RANDOM ONE, RANDOM RANDOM, CHOSEN ONE, CHOSEN RANDOM

    public static void playRandomLoop() {
        if (numberOfSongs > 0) {
            debugger.info("Repeat mode: random");
            String[] pathNames = new File(rootPath).list();
            if (pathNames != null) {
                do {
                    playingFile = pathNames[(int) (Math.random() * numberOfSongs)];
                    playInternal(playingFile);
                } while (true);
            }
        }
    }

    public static void stop() {

    }

    @SuppressWarnings("unused")
    private enum PlayMode {
        NONE, RANDOM, CHOSEN
    }

    @SuppressWarnings("unused")
    private enum RepeatMode {
        NONE, ONE, RANDOM
    }
}
