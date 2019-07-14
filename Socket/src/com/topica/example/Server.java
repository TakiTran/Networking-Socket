package com.topica.example;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int PORT_SERVER = 2040;
	private static final int FILE_SIZE = 10 * 1024;
	private static final String SOURCE_FILE = "server-output/";
	private static final String MESSAGE_WRITE_FILE = "Writing file from clinet...";
	private static final String MESSAGE_WRITE_FILE_SUCCESS = "Writing file success!";
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT_SERVER);
		Socket socket = serverSocket.accept();
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		String fileName = "";
		while (fileName.equals("")) {
			fileName = dataInputStream.readUTF();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(SOURCE_FILE + fileName);

		byte[] buffer = new byte[FILE_SIZE];
		int bytesRead = 0;

		while ((bytesRead = dataInputStream.read(buffer)) != -1) {
			System.out.println(MESSAGE_WRITE_FILE);
			fileOutputStream.write(buffer, 0, bytesRead);
		}	
		System.out.println(MESSAGE_WRITE_FILE_SUCCESS);
		dataInputStream.close();
		socket.close();
		serverSocket.close();
		fileOutputStream.close();
	}
}
