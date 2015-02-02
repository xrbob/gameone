package com.hartleydigital.framework;

public interface Image {

    public int getWidth();

    public int getHeight();

    public Graphics.ImageFormat getFormat();

    public void dispose();
    
}
