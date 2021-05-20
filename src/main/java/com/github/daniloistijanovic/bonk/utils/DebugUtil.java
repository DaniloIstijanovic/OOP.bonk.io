package com.github.daniloistijanovic.bonk.utils;

import java.io.FileWriter;
import java.io.IOException;

public class DebugUtil {

    public static final boolean[] debugReasonsIListenTo = {true, true, true, true, true, true};
    public static DebugMethod debugMethod = DebugMethod.CONSOLE;
    public static FileWriter fw;

    // ovo je u potpunosti staticka klasa
    private DebugUtil() {
    }

    public static void debug(DebugReason reason, String string) {
        if (iAmListeningTo(reason)) {
            String generated = "[" + reason + "] " + string;
            switch (debugMethod) {
                case NONE:
                    return;
                case CONSOLE:
                    System.out.println(generated);
                    break;
                case FILE:
                    try {
                        fw.write(generated + "\n");
                        fw.flush();
                    } catch (IOException e) {
                        debugMethod = DebugMethod.CONSOLE;
                        e.printStackTrace();
                        debug(DebugReason.FILE, "CRKO JE DEBUG U FILE :(");
                    }
                    break;
            }
        }
    }

    private static boolean iAmListeningTo(DebugReason reason) {
        return debugReasonsIListenTo[reason.ordinal()];
    }

    public enum DebugMethod {
        NONE, CONSOLE, FILE
    }

    public enum DebugReason {
        INFO, WARNING, ERROR, CRITICAL, MEMORY, FILE
    }

}
