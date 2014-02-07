package rripp.pocketmd.client.datahendler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

public class SendFile extends AsyncTask<File, Integer, Integer> {
		
	@Override
	protected Integer doInBackground(File... file) {
		try {
			Socket sock = new Socket("192.168.1.4", 5000);
			System.out.println("Connecting...");
            File myFile = file[0];//new File (selectedImagePath); 
            byte [] mybytearray  = new byte [(int)myFile.length()];
            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray,0,mybytearray.length);
            OutputStream os = sock.getOutputStream();
            System.out.println("Sending...");
            os.write(mybytearray,0,mybytearray.length);
            os.flush();
            sock.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//TODO progress bar 
	//TODO dialog then transition is finished
	/*
	protected void onPostExecute(Long result) {
        showDialog("Downloaded " + result + " bytes");
    }
    */
}
