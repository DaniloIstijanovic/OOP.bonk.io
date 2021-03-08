package oop.bonk.io.utils;

import java.io.FileWriter;
import java.io.IOException;

public class DebugUtil {

    public enum DebugMethod {
        NONE, CONSOLE, FILE
    }

    public enum DebugReason {
        INFO, WARNING, ERROR, CRITICAL, MEMORY, FILE
    }

    public static DebugMethod debugMethod = DebugMethod.CONSOLE;
    public static boolean[] debugReasonsIListenTo = { true, true, true, true, true, true };
    public static FileWriter fw;

    private static boolean iAmListeningTo(DebugReason reason) {
        return debugReasonsIListenTo[reason.ordinal()];
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

    // ovo je u potpunosti staticka klasa
    private DebugUtil() {
    }

}
