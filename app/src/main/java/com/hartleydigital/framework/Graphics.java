package com.hartleydigital.framework;

import android.graphics.Paint;

public interface Graphics {

    public static enum ImageFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public Image newImage(String fileName, ImageFormat format);

    public void clearScreen(int color);

    public void drawLine(int xStart, int yStart, int xEnd, int yEnd, int color);

    public void drawRect(int x, int y, int width, int height, int color);

    public void drawImage(Image image, int x, int y, int sourceX, int sourceY, int sourceWidth, int sourceHeight);

    public void drawImage(Image image, int x, int y);

    void drawString(String text, int x, int y, Paint paint);

    public int getWidth();

    public int getHeight();

    public void drawARGB(int alpha, int red, int green, int blue);

}

