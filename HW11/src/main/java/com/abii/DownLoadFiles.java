package com.abii;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.*;

public class DownLoadFiles {

    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newCachedThreadPool();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String destPath = "";

        System.out.println("Type /help to see available commands");
        while (true) {
            String line = br.readLine();
            if (line.equals("/help")) {
                System.out.println("Available commands:");
                System.out.println("/load URL1 URL2 URL3 - starts downloading URLs");
                System.out.println("/dest PATH - sets the destination path for the downloaded files");
                System.out.println("/exit - exits the program");
                System.out.println("Type /help to see available commands");
            } else if (line.startsWith("/load")) {
                String[] urls = line.replace("/load ", "").split("\\s+");
                System.out.println("Starting download of " + urls.length + " files...");
                for (String url : urls) {
                    Runnable task = new DownloadTask(url, destPath);
                    executor.execute(task);
                }
            } else if (line.startsWith("/dest")) {
                destPath = line.replace("/dest ", "");
                System.out.println("Destination path set to '" + destPath + "'");
            } else if (line.equals("/exit")) {
                break;
            }
            else {
                System.out.println("Unknown command: " + line);
                System.out.println("Type /help to see available commands");
            }
        }
        executor.shutdown();
    }

    private static class DownloadTask implements Runnable {
        private static final int BUFFER_SIZE = 4096;
        private final URL url;
        private final String destPath;

        public DownloadTask(String url, String destPath) throws MalformedURLException {
            this.url = new URL(url);
            this.destPath = destPath;
        }

        @Override
        public void run() {
            try {
                System.out.println("Downloading " + url + " to " + destPath);
                URLConnection conn = url.openConnection();
                int contentLength = conn.getContentLength();
                if (contentLength <= 0) {
                    throw new Exception("Invalid content length");
                }

                InputStream stream = conn.getInputStream();
                BufferedInputStream bufStream = new BufferedInputStream(stream);
                FileOutputStream fos = new FileOutputStream(destPath);

                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = bufStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.close();
                bufStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}