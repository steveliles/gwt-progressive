package com.sjl.gwt.progressive.server;

import java.io.*;

public class Streams
{
    public static void copy(InputStream anInput, OutputStream anOutput, int aBufferSize) throws IOException
    {
        byte[] _buffer = new byte[aBufferSize];
        int _length;

        while ((_length = anInput.read(_buffer)) > -1)
        {
            anOutput.write(_buffer, 0, _length);
        }
        anOutput.flush();
    }
}
