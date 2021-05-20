package oop.bonk.io.utils;

import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

public class MusicUtil {

    private static final String[] paths = {"music/", "resources/music/", "src/main/resources/music/"};
    private static String playingFile;

    //staticka klasa
    private MusicUtil() {
    }


    public static void playMusic() {

    }

    public static void playMusicaaaaa() {
        new Thread(() -> {
            try {
                for (String path : paths) {
                    File f = new File(path);
                    if (f.exists()) {
                        int numberOfSongs = Objects.requireNonNull(f.listFiles()).length;

                        DebugUtil.debug(DebugUtil.DebugReason.INFO, numberOfSongs + " song" + (numberOfSongs != 1 ? "s" : "") + " found in " + path);
                        if (numberOfSongs > 0) {

                            DebugUtil.debug(DebugUtil.DebugReason.INFO, "Repeat mode: random");

                            String[] pathNames = f.list();
                            if (pathNames != null) {
                                do {
                                    playingFile = pathNames[(int) (Math.random() * numberOfSongs)];
                                    FileInputStream fis = new FileInputStream(path + playingFile);
                                    AdvancedPlayer playMP3 = new AdvancedPlayer(fis);
                                    DebugUtil.debug(DebugUtil.DebugReason.INFO, "Now playing song: " + playingFile);
                                    playMP3.play();
                                } while (true);
                            }
                        }
                    } else {
                        DebugUtil.debug(DebugUtil.DebugReason.ERROR, "Folder " + path + " does not exist");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                DebugUtil.debug(DebugUtil.DebugReason.ERROR, "Failed to play the file.");
            }
        }).start();
    }

    // NONE NONE, RANDOM NONE, CHOSEN NONE, RANDOM ONE, RANDOM RANDOM, CHOSEN ONE,
    // CHOSEN RANDOM

    @SuppressWarnings("unused")
    public static String getCurrentPlayingFile() {
        return playingFile;
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
