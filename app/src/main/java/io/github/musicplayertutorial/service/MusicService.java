package io.github.musicplayertutorial.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    MediaPlayer player = new MediaPlayer();

    PlayerInterface.Stub binder = new PlayerInterface.Stub() {

        @Override
        public void playMusic(int i) throws RemoteException {
            try {

                if (player == null)
                    player = new MediaPlayer();

                player.reset();
                player.setDataSource(getApplicationContext(), Uri.parse("android.resource://io.github.musicplayertutorial/" + i));
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void stopMusic() throws RemoteException {
            if (player.isPlaying()) {
                player.stop();
                player.release();
                player = null;

            }
        }

    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


}
