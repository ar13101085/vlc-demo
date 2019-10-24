package com.aos.demo_vlc;

import android.app.Application;
import android.content.Context;

import com.aos.dynamiclib.PrintSomething;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import app.artyomd.injector.DexUtils;
import dalvik.system.DexClassLoader;
import dalvik.system.InMemoryDexClassLoader;

public class Base extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        File dexPath = new File(getFilesDir() + "/dex", "inject.zip");
        if (!dexPath.exists()) {
            DexUtils.prepareDex(getApplicationContext(), dexPath, "inject.zip");
        }
        DexUtils.prepareDex(getApplicationContext(), dexPath, "inject.zip");
        List<File> dexs = new ArrayList<>();
        dexs.add(dexPath);
        try {
            DexUtils.loadDex(getApplicationContext(), dexs);
            System.out.println("Dex loading succesfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
