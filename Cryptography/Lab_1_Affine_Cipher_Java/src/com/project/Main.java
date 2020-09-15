package com.project;

import java.io.*;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        while(true){
            int key = 0;
            System.out.println("1. Encode\n" +
                    "2. Decode\n" +
                    "3. Exit\n" +
                    "Select action: ");
            key = in.nextInt();
            if(key == 1){
                String file_path = in.next();
                encryption_decryption_file(key, file_path);
            }
            else if(key == 2){
                String file_path = in.next();
                encryption_decryption_file(key, file_path);
            }
            else if(key == 3){
                break;
            }
        }
    }

    public static void encryption_decryption_file(int key, String file_path){
        byte[] buffer = new byte[0];

        try(FileInputStream fin = new FileInputStream(file_path);)
        {
            buffer = new byte[fin.available()];
            fin.read(buffer, 0, buffer.length);
            if(key == 1) {
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) ((49 * (int) buffer[i] + 59) % 256);
                }
            }
            else if(key == 2){
                for(int i = 0; i < buffer.length; i++){
                    buffer[i] = (byte)((209 * ((int) buffer[i] - 59)) % 256);
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        try(FileOutputStream fos = new FileOutputStream(file_path)){
            fos.write(buffer, 0, buffer.length);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

