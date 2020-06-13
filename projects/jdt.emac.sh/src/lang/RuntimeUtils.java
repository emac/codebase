/*
 * Created on 2011-5-19 Copyright (c) eBay, Inc. 2011 All rights reserved.
 */

package lang;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author bishen
 */
public class RuntimeUtils {

    /**
     * There's a known trap in the default implementation of {@code Runtime#exec(String[])}, as specified in JDK's
     * Javadoc��
     * <p>
     * Because some native platforms only provide limited buffer size for standard input and output streams, failure to
     * promptly write the input stream or read the output stream of the subprocess may cause the subprocess to block,
     * and even deadlock.
     * </p>
     * To work around, this method consumes the output/error stream of the new process in separate threads.
     *
     * @param cmds
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static int exec(String[] cmds)
            throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(cmds);
        consume(process.getInputStream());
        consume(process.getErrorStream());

        return process.waitFor();
    }

    private static void consume(final InputStream inputStream) {
        // consume input stream in a new thread to avoid deadlock
        new Thread() {
            @Override
            public void run() {
                try {
                    while (inputStream.read() != -1) ;
                } catch (IOException e) {
                    // ignore
                }
            }
        }.start();
    }

}
