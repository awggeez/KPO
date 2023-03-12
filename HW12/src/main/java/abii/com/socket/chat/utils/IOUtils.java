package abii.com.socket.chat.utils;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;

public final class IOUtils {

  private IOUtils() {
    throw new UnsupportedOperationException();
  }

  /**
   * Writes data in given BufferedWriter with calling newLine() and flush() functions.
   *
   * @param bufferedWriter given client's BufferedWriter
   * @param message message to send
   * @throws IOException If an I/O error occurs
   */
  public static void writeAndFlush(BufferedWriter bufferedWriter, String message) throws IOException {
    bufferedWriter.write(message);
    bufferedWriter.newLine();
    bufferedWriter.flush();
  }

  /**
   * Closes current closeable object and catches an exception.
   *
   * @param source closeable object
   */
  public static void close(Closeable source) {
    try {
      if (source != null) {
        source.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
