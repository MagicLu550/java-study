package com.a.eye.by.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileReader {

    public static void readFile(String fileName) {

        RandomAccessFile randomAccessFile = null;

        FileChannel fileChannel = null;

        try {

            randomAccessFile = new RandomAccessFile(fileName, "wr");

            fileChannel = randomAccessFile.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(48);

            int size = fileChannel.read(byteBuffer);

            while (size > 0) {

                byteBuffer.flip();

                Charset charset = Charset.forName("UTF-8");

                System.out.println(charset.decode(byteBuffer).toString());

                byteBuffer.clear();

                size = fileChannel.read(byteBuffer);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileChannel) {
                    fileChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != randomAccessFile) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FileReader.readFile("/User/test/a.txt");
    }
}
