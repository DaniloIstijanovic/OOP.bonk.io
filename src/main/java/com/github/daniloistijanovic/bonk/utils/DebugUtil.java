package com.github.daniloistijanovic.bonk.utils;

import java.io.FileWriter;
import java.io.IOException;

public class DebugUtil {

    public static DebugUtil debugger = new DebugUtil();
    public final boolean[] debugReasonsIListenTo = {true, true, true, true, true, true};
    public DebugMethod debugMethod = DebugMethod.CONSOLE;
    public FileWriter fw;

    // ovo je u potpunosti staticka klasa
    private DebugUtil() {
    }

    public void critical(String s) {
        debug(DebugReason.CRITICAL, s);
    }

    public void error(String s) {
        debug(DebugReason.ERROR, s);
    }

    public void file(String s) {
        debug(DebugReason.FILE, s);
    }

    public void info(String s) {
        debug(DebugReason.INFO, s);
    }

    public void memory(String s) {
        debug(DebugReason.MEMORY, s);
    }

    public void warning(String s) {
        debug(DebugReason.WARNING, s);
    }

    private void debug(DebugReason reason, String string) {
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

    private boolean iAmListeningTo(DebugReason reason) {
        return debugReasonsIListenTo[reason.ordinal()];
    }

    public enum DebugMethod {
        NONE, CONSOLE, FILE
    }

    public enum DebugReason {
        INFO, WARNING, ERROR, CRITICAL, MEMORY, FILE
    }

}
