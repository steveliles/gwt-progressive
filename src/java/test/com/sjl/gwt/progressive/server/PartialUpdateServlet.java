package com.sjl.gwt.progressive.server;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class PartialUpdateServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest aReq, HttpServletResponse aRes) throws ServletException, IOException
    {
        sendPartialUpdate(aReq.getRequestURI().split("/")[2], aRes);
    }

    private void sendPartialUpdate(String aResourceName, HttpServletResponse aRes) throws IOException
    {
        aRes.setContentType("text/html");
        InputStream _in = getClass().getResourceAsStream(aResourceName);
        try
        {
            Streams.copy(_in, aRes.getOutputStream(), 8192);
        }
        finally
        {
            _in.close();
        }
    }
}
