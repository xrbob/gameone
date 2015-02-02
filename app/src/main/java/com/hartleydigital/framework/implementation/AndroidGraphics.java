package com.hartleydigital.framework.implementation;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.hartleydigital.framework.Graphics;
import com.hartleydigital.framework.Image;

import java.io.IOException;
import java.io.InputStream;

public class AndroidGraphics implements Graphics {

    AssetManager assets;
    Bitmap       frameBuffer;
    Canvas       canvas;
    Paint        paint;

    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {

        this.assets      = assets;
        this.frameBuffer = frameBuffer;
        this.canvas      = new Canvas(frameBuffer);
        this.paint       = new Paint();

    }

    @Override
    public Image newImage(String fileName, ImageFormat format) {

        Config config = null;

        if(format == ImageFormat.RGB565)
            config = Config.RGB_565;
        else if(format == ImageFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        Options options           = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap  = null;

        try {
            in     = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);

            if(bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset: '" + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset: '" + fileName + "'");
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);

    }

    @Override
    public void clearScreen(int color) {

        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));

    }

    @Override
    public void drawLine(int xStart, int yStart, int xEnd, int yEnd, int color) {

        paint.setColor(color);
        canvas.drawLine(xStart, yStart, xEnd, yEnd, paint);

    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {

        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);

    }

    @Override
    public void drawImage(Image image, int x, int y, int sourceX, int sourceY, int sourceWidth, int sourceHeight) {

        srcRect.left   = sourceX;
        srcRect.top    = sourceY;
        srcRect.right  = sourceX + sourceWidth;
        srcRect.bottom = sourceY + sourceHeight;

        dstRect.left   = x;
        dstRect.top    = y;
        dstRect.right  = x + sourceWidth;
        dstRect.bottom = y + sourceHeight;

        canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect,
                null);

    }

    @Override
    public void drawImage(Image image, int x, int y) {

        canvas.drawBitmap(((AndroidImage) image).bitmap, x, y, null);

    }

    public void drawScaledImage(Image image, int x, int y, int width, int height, int sourceX, int sourceY, int sourceWidth, int sourceHeight) {

        srcRect.left   = sourceX;
        srcRect.top    = sourceY;
        srcRect.right  = sourceX + sourceWidth;
        srcRect.bottom = sourceY + sourceHeight;

        dstRect.left   = x;
        dstRect.top    = y;
        dstRect.right  = x + width;
        dstRect.bottom = y + height;

        canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect, null);

    }

    @Override
    public void drawString(String text, int x, int y, Paint paint) {

        canvas.drawText(text, x, y, paint);

    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }

    @Override
    public void drawARGB(int alpha, int red, int green, int blue) {

        paint.setStyle(Style.FILL);
        canvas.drawARGB(alpha, red, green, blue);

    }
}
