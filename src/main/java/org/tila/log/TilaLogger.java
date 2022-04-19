package org.tila.log;

import java.util.logging.Logger;

public class TilaLogger {
    public static void ERROR(String msg) {
        System.out.println("[Tila] ERROR: " + msg);
        exit();
    }
    public static void INFO(String msg){
        System.out.println("[Tila] INFO: " + msg);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
//        LOG("test logger", true);
    }
}
