package com.topica.example;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static final String HOST_NAME = "localhost";
	private static final int PORT_SERVER = 2040;
	private static final int FILE_SIZE = 10 * 1024;
	private static final String FILE_NAME = "video.mp4";
	private static final String MESSAGE_SEND_FILE = "Sending file to server...";
	private static final String MESSAGE_SEND_FILE_SUCCESS = "Sending file success!";
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket(HOST_NAME, PORT_SERVER);
		DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
		FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
		dataOutput.writeUTF(FILE_NAME);
		dataOutput.flush();
		byte[] buffer = new byte[FILE_SIZE];
		int bytesRead = 0;
		while ((bytesRead = fileInputStream.read(buffer)) != -1) {
			if (bytesRead > 0) {
				System.out.println(MESSAGE_SEND_FILE);
				dataOutput.write(buffer, 0, bytesRead);
			}
		}
		System.out.println(MESSAGE_SEND_FILE_SUCCESS);
		dataOutput.close();
		socket.close();
	}
}
