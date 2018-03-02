package iwh.empowher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jyoti Joshi on 22-02-2018.
 */

public class EmpowHer_Levents extends Activity {

    ListView listSEvents;
    ProgressBar proSEventList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.empowher_s_events);

        listSEvents = (ListView)findViewById(R.id.lISTSEvents);
        proSEventList = (ProgressBar)findViewById(R.id.pROSEventList);

        new GetHttpResponse(this).execute();

        listSEvents.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub


                S_Events ListViewClickItem = (S_Events) parent.getItemAtPosition(position);


                Intent intent = new Intent(EmpowHer_Levents.this, ViewSRecords.class);
                String clickedId = ListViewClickItem.get_s_events_id();
                String clickedTitle = ListViewClickItem.get_s_events_title();
                String clickedDesc = ListViewClickItem.get_s_events_desc();
                String clickedLink = ListViewClickItem.get_s_events_link();
                String clickedCat = ListViewClickItem.get_s_events_cat();
                String clickedOrg = ListViewClickItem.get_s_events_org();
                String clickedDeadline = ListViewClickItem.get_s_events_deadline();
                String clickedEligible = ListViewClickItem.get_s_events_eligible();
                Bundle extras = new Bundle();
                extras.putString("clickedId", clickedId );
                extras.putString("clickedTitle", clickedTitle );
                extras.putString("clickedDesc", clickedDesc );
                extras.putString("clickedLink", clickedLink );
                extras.putString("clickedOrg", clickedOrg );
                extras.putString("clickedDeadline", clickedDeadline );
                extras.putString("clickedEligible", clickedEligible );
                extras.putString("clickedCat", clickedCat );
                intent.putExtras(extras);
                startActivity(intent);

                //Toast.makeText(MainActivity.this, ListViewClickItem.getCources_name(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        private Context context;
        String result;
        List<S_Events> s_event_List;
        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpService httpService = new HttpService(Constants.BASE_URL +"db_files/l_events.php");
            try
            {
                httpService.ExecutePostRequest();

                if(httpService.getResponseCode() == 200)
                {
                    result = httpService.getResponse();
                    Log.d("Result", result);
                    if(result != null)
                    {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(result);

                            JSONObject object;
                            JSONArray array;
                            S_Events eventobject;
                            s_event_List = new ArrayList<S_Events>();
                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                eventobject = new S_Events();
                                object = jsonArray.getJSONObject(i);

                                eventobject.s_title = object.getString("l_name");
                                eventobject.s_desc = object.getString("l_description");
                                eventobject.s_id = object.getString("l_id");
                                eventobject.s_link = object.getString("llinkforclick");
                                eventobject.s_org = object.getString("lorganizer");
                                eventobject.s_cat = object.getString("lcategory");
                                eventobject.s_eligible = object.getString("leligible");
                                eventobject.s_deadline = object.getString("ldeadline");
                                s_event_List.add(eventobject);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpService.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            proSEventList.setVisibility(View.GONE);
            listSEvents.setVisibility(View.VISIBLE);
            if(s_event_List != null)
            {
                ListAdapter adapter = new ListAdapter(s_event_List, context);
                listSEvents.setAdapter(adapter);
            }
        }
    }
    public class HttpService
    {
        private ArrayList <NameValuePair> params;
        private ArrayList <NameValuePair> headers;

        private String url;
        private int responseCode;
        private String message;
        private String response;

        public String getResponse()
        {
            return response;
        }

        public String getErrorMessage()
        {
            return message;
        }

        public int getResponseCode()
        {
            return responseCode;
        }

        public HttpService(String url)
        {
            this.url = url;
            params = new ArrayList<NameValuePair>();
            headers = new ArrayList<NameValuePair>();
        }

        public void AddParam(String name, String value)
        {
            params.add(new BasicNameValuePair(name, value));
        }

        public void AddHeader(String name, String value)
        {
            headers.add(new BasicNameValuePair(name, value));
        }

        public void ExecuteGetRequest() throws Exception
        {
            String combinedParams = "";
            if(!params.isEmpty())
            {
                combinedParams += "?";
                for(NameValuePair p : params)
                {
                    String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(),"UTF-8");
                    if(combinedParams.length() > 1)
                    {
                        combinedParams += "&" + paramString;
                    }
                    else
                    {
                        combinedParams += paramString;
                    }
                }
            }

            HttpGet request = new HttpGet(url + combinedParams);
            for(NameValuePair h : headers)
            {
                request.addHeader(h.getName(), h.getValue());
            }

            executeRequest(request, url);
        }

        public void ExecutePostRequest() throws Exception
        {
            HttpPost request = new HttpPost(url);
            for(NameValuePair h : headers)
            {
                request.addHeader(h.getName(), h.getValue());
            }

            if(!params.isEmpty())
            {
                request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            }

            executeRequest(request, url);
        }

        private void executeRequest(HttpUriRequest request, String url)
        {
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 10000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            int timeoutSocket = 10000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

            HttpClient client = new DefaultHttpClient(httpParameters);
            HttpResponse httpResponse;
            try
            {
                httpResponse = client.execute(request);
                responseCode = httpResponse.getStatusLine().getStatusCode();
                message = httpResponse.getStatusLine().getReasonPhrase();

                HttpEntity entity = httpResponse.getEntity();
                if (entity != null)
                {
                    InputStream instream = entity.getContent();
                    response = convertStreamToString(instream);
                    instream.close();
                }
            }
            catch (ClientProtocolException e)
            {
                client.getConnectionManager().shutdown();
                e.printStackTrace();
            }
            catch (IOException e)
            {
                client.getConnectionManager().shutdown();
                e.printStackTrace();
            }
        }

        private String convertStreamToString(InputStream is)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try
            {
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }



}
