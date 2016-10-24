package eu.siacs.conversations.voicemessage;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.util.Date;

import omrecorder.AudioChunk;
import omrecorder.AudioSource;
import omrecorder.OmRecorder;
import omrecorder.PullTransport;
import omrecorder.Recorder;

/**
 * Created by lababiba on 21/10/16.
 */

public class WaveRecording {
    public Recorder recorder;


    public void setupRecorder() {
        recorder = OmRecorder.wav(
                new PullTransport.Default(mic(), new PullTransport.OnAudioChunkPulledListener() {
                    @Override public void onAudioChunkPulled(AudioChunk audioChunk) {
                    }
                }), file());
    }

    public AudioSource mic() {
        return new AudioSource.Smart(MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT,
                AudioFormat.CHANNEL_IN_MONO, 44100);
    }

    @NonNull
    private File file() {
        String fileName = Long.toString(new Date(System.currentTimeMillis()).getTime());
        return new File(Environment.getExternalStorageDirectory() + "/ConversationsTemp/" + fileName + ".wav");
    }
}
