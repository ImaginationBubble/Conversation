package eu.siacs.conversations.voicemessage;


import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import eu.siacs.conversations.ui.ConversationActivity;


public class AudioOutputBase64 {

    private String path = "/ConversationsTemp/";
//    File outFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path + ".mp3");
    private MediaRecorder recorder = null;
    private AudioOutputDelegate delegate = null;
    private static final int SAMPLING_RATE = 48000;


    //call for u need start record

    public void startRecording(int count_of_mess) {
        releaseRecorder();
//        File outFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+path);
//        if (outFile.exists()) {
//            outFile.delete();
//        }
//
//        try {
//            outFile.createNewFile();
//        } catch (IOException e) {
//            Log.e("NSD AudioOutputBase64 ", "system down with exc " + e.getLocalizedMessage());

        try {
            String FileName = Long.toString(new Date(System.currentTimeMillis()).getTime());
            File outFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path + FileName + ".mp3");
            Log.e("TAG",path);
            recorder = new MediaRecorder();
            recorder.reset();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setAudioSamplingRate(44100);
            recorder.setAudioEncodingBitRate(160 * 1024);
            recorder.setAudioChannels(2);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
            recorder.setOutputFile(outFile.getAbsolutePath());
            recorder.prepare();
            recorder.start();
            Log.v("Record", "Started");
            // Recording is now started
        } catch (IOException exc) {
            Log.e("NSD AudioOutputBase64 ", "system down with exc " + exc.getLocalizedMessage());
        }
        Log.e("Start Recording", "Recording started!");

    }




    // called if tap stop button or etc event

    public void stopRecording() {
//        File outFile;
//        if (recorder != null) {
    try {
        recorder.stop();
        recorder.reset();
    }
    catch(Exception e){

        }
//            outFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+path);
            }

//        }
//        File outFile = new File(Environment.getExternalStorageDirectory() + path);
//        if(!outFile.exists()) { return null; }
//
//        byte[] data = new byte[(int)outFile.length()];
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream(outFile);
//            fileInputStream.read(data);
//            fileInputStream.close();
//        }
//        catch (IOException exc){
//
//            Log.e("NSD",exc.getLocalizedMessage());
//            return null;
//        }
//
//        outFile.delete();
//
//        if(delegate!=null) delegate.finish(NSDBase64Tool.encode(data));
//        //finally u has base64 string
//        Log.e("Stop Recording", "Recording stopped!");
//        return data;
//        return outFile;



    public void setDelegate(AudioOutputDelegate delegate){
        this.delegate = delegate;
    }

    //private methods Zone

    private void releaseRecorder(){
        if(recorder!=null){
            recorder.release();
            recorder = null;
        }

    }

}
