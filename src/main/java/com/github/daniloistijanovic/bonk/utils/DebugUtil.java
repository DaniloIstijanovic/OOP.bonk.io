package com.github.daniloistijanovic.bonk.utils;

import java.io.FileWriter;
import java.io.IOException;

public class DebugUtil {

    public static final DebugUtil debugger = new DebugUtil();
    public DebugMethod debugMethod = DebugMethod.CONSOLE;
    public FileWriter fw;

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

    public void warning(String s) {
        debug(DebugReason.WARNING, s);
    }

    private void debug(DebugReason reason, String string) {
        if (reason.yes) {
            final String generated = "[" + reason + "] " + string;
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
                        file("CRKO JE DEBUG U FILE :( " + e.getMessage());
                    }
                    break;
            }
        }
    }

    public enum DebugMethod {
        NONE, CONSOLE, FILE
    }

    public enum DebugReason {
        INFO(true),
        WARNING(true),
        ERROR(true),
        CRITICAL(true),
        FILE(true);

        public final boolean yes;

        DebugReason(boolean yes) {
            this.yes = yes;
        }
    }

}
