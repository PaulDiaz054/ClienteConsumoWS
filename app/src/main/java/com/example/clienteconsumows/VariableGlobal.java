package com.example.clienteconsumows;

import android.app.Application;

public class VariableGlobal extends Application {
    private String IP = "192.168.101.11";

    public String getIP() {
        return IP;
    }
}
