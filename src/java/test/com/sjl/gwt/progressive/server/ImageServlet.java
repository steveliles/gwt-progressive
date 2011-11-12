package com.sjl.gwt.progressive.server;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author steve
 */
public class ImageServlet extends HttpServlet
{

    private static final long serialVersionUID = -2537964054441894348L;

    @Override
    protected void doGet(HttpServletRequest anReq, HttpServletResponse anRes) throws ServletException, IOException
    {
        sendImage(anReq.getRequestURI().split("/")[2], anRes);
    }

    private void sendImage(String aResourceName, HttpServletResponse aRes) throws IOException
    {
        aRes.setContentType("image/jpg");
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