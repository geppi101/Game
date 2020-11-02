package com.geppi.other;

import java.io.File;

public class FileHandler {
    String path = "plugins/GameCore";

    public void Setup() {
        File mainDirectory = new File(this.path);
        if (!mainDirectory.exists())
            mainDirectory.mkdir();
        File playerData = new File(this.path + "/PlayerData");
        if (!playerData.exists())
            playerData.mkdir();
    }
}