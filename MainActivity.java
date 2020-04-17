package example.myapp.newapplication;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private WebView webView;
    //private android.webkit.WebView webView2;
    private String send_buff = null;
    Socket socket = null;
    String url=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        webView=(WebView)findViewById(R.id.webView2);

        ButtonListener buttonListener= new ButtonListener();
        button4.setOnClickListener(buttonListener);
        button5.setOnClickListener(buttonListener);
        button6.setOnClickListener(buttonListener);
        button7.setOnClickListener(buttonListener);
        button8.setOnClickListener(buttonListener);
        button9.setOnClickListener(buttonListener);


    }
        private void send()
        {
            new Thread(new Runnable()
            {
                @Override
                public void run() {
                    int PORT =8080;
                    InetAddress addr = null;
                    try
                    {
                        addr = InetAddress.getByName("192.168.43.84");
                    }
                    catch (UnknownHostException e)
                    {
                        e.printStackTrace();
                    }
                    Socket socket = new Socket();
                    try
                    {

                        socket.connect(new InetSocketAddress(addr, PORT), 30000);
                        socket.setSendBufferSize(100);
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        out.write(send_buff);
                        out.flush();

                    }
                    catch (SocketException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                        StringBuilder response = new StringBuilder();
                        String line="Iot";
                        response.append(line);
                        //showResponse(response.toString());

                    }
                    finally
                    {
                        try
                        {
                            socket.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }





        class ButtonListener implements View.OnClickListener{
            public void onClick(View v){

            if (v.getId() == R.id.button4)
            {
                send_buff= "image";
                url="http://192.168.43.84:8081";
               video();

                send();
            }
            if (v.getId() == R.id.button5)
            {
                send_buff="lie";
                //textView2.setText(send_buff);
                send();
            }
                if (v.getId() == R.id.button6)
                {
                    send_buff="up";
                    //textView2.setText(send_buff);
                    send();
                }
                if (v.getId() == R.id.button7)
                {
                    send_buff="left";
                    //textView2.setText(send_buff);
                    send();
                }
                if (v.getId() == R.id.button8)
                {
                    send_buff="right";
                    //textView2.setText(send_buff);
                    send();
                }
                if (v.getId() == R.id.button9)
                {
                    send_buff="ok";
                    url="";
                   video();
                    //textView2.setText(send_buff);
                    send();
                }
        }

        }

    private void video()
    {
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


}


