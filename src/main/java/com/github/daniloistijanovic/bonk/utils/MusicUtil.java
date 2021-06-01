package com.github.daniloistijanovic.bonk.utils;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.File;
import java.util.Objects;

import static com.github.daniloistijanovic.bonk.utils.DebugUtil.debugger;

public class MusicUtil {
    public static Thread currentlyPlaying;
    private static String rootPath = "music/";
    private static int numberOfSongs;
    private static String playingFile;

    //staticka klasa
    private MusicUtil() {
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
            AdvancedPlayer ap = new AdvancedPlayer(MiscUtil.getResource(rootPath + filePath));
            debugger.info("Now playing song: " + playingFile);
            ap.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void playLoopInternal(String fileName) {
        stopThis();
        debugger.info("Repeat mode: one");
        currentlyPlaying = new Thread(() -> {
            do {
                playingFile = fileName;
                playInternal(playingFile);
            } while (true);
        });
        currentlyPlaying.start();
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

    public static void stopThis() {
        // ako radi bas me briga nemam volje da se cimam sa ovim
        if (currentlyPlaying != null && currentlyPlaying.isAlive()) {
            currentlyPlaying.stop();
            debugger.info("Stopped music");
        }
    }
}
