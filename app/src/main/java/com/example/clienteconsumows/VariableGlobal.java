package com.example.clienteconsumows;

import android.app.Application;

public class VariableGlobal extends Application {
    private String IP = "10.10.12.254";

    public String getIP() {
        return IP;
    }
}
