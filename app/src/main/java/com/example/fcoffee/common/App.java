package com.example.fcoffee.common;

import androidx.appcompat.app.AppCompatActivity;

public class App {
    public static void finishApp(AppCompatActivity appCompatActivity) {
        appCompatActivity.finish();
    }

    public static void refreshApp(AppCompatActivity appCompatActivity) {
        appCompatActivity.recreate();
    }
}
