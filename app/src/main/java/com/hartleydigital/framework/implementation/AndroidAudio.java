package com.hartleydigital.framework.implementation;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.hartleydigital.framework.Audio;
import com.hartleydigital.framework.Music;
import com.hartleydigital.framework.Sound;

import java.io.IOException;

public class AndroidAudio implements Audio {

    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {

        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);

    }

    @Override
    public Music createMusic(String file) {

        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(file);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music: '" + file + "'");
        }

    }

    @Override
    public Sound createSound(String file) {

        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(file);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound: '" + file + "'");
        }

    }
}
