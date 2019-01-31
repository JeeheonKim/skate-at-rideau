package com.jonneykim.skateatrideau;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* GENERAL JSON
{
  "type": "FeatureCollection",
  "crs": {
    "type": "name",
    "properties": {
      "name": "EPSG:32189"
    }
  },
  "features": [
    {
      "type": "Feature",
      "id": 1,
      "geometry": null,
      "properties": {
        "OBJECTID": 1,
        "General_Notice": "<B></B><br> The entire length of the Rideau Canal Skateway is open, however the surface of the ice is snow covered. Snow removal operations are ongoing therefore, please use caution as maintenance equipment will be present on the ice. Flooding was not possible last night due to intermittent snowfall and high winds.</br><span style=\"font-size:0.8em;\">Last updated : January 29, 2019 at 1:57 pm </span>",
        "Avis_General": "<B></B><br>La patinoire du canal Rideau est ouverte sur toute sa longueur. Toutefois, la surface de la glace est couverte de neige. Des travaux de déneigement se poursuivent donc, soyez prudents car il y aura de l’équipement d’entretien présent sur la patinoire. En raison de la neige intermittente et des vents forts durant la nuit, la patinoire n’a pas pu être arrosée. <br /><span style=\"font-size:0.8em;\">Dernière mise à jour : le 29 janvier 2019 à 13 h 57</span>",
        "Notice_Status": "Entirely open",
        "Notice_Status_FR": "Entièrement ouverte"
      }
    }
  ]
}
 */




// TODO General  Notice JSON
// TODO tells the user that about the internet connection
// Updat
// TODO where is my nearest stair?

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2, textView3;
    Button button;
    ArrayList<Long> arrayList = new ArrayList<>();
    ArrayList<FeatureItem> customFeatures;
    ListView listView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        listView = findViewById(R.id.listView);
        adapter = new ListViewAdapter();


        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());

        }

        sendRequest();
    }

    public void sendRequest(){
        // Specific Status
        String urlStr = "https://services2.arcgis.com/WLyMuW006nKOfa5Z/arcgis/rest/services/RCS_Status_PUBLIC/FeatureServer/0/query?where=1%3D1%20&outFields=*%20&returnGeometry=false&f=pgeojson";
        //General Notice
        String urlStr1="https://services2.arcgis.com/WLyMuW006nKOfa5Z/arcgis/rest/services/RCS_General_Notice_PUBLIC/FeatureServer/0/query?where=1%3D1%20&outFields=*%20&returnGeometry=false&f=pgeojson";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlStr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //println("Error ->"+ error.getMessage());
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() {
                Map <String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

        request = new StringRequest(
                Request.Method.GET,
                urlStr1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //println("Error ->"+ error.getMessage());
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() {
                Map <String, String> params = new HashMap<String, String>();

                return params;
            }
        };


        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){

        }
    }

    public void processResponse(String response){
        Gson gson = new Gson();
        FeatureList featureList = gson.fromJson(response, FeatureList.class);

        if(featureList !=null){
            customFeatures = featureList.features;
            int size = customFeatures.size();
            //println("Number of Collections: "+size);
            for(int i=0; i<size; i++){
                if(customFeatures.get(i).properties.From_!=null)
                    adapter.addItem(new ListViewItem(customFeatures.get(i).properties.From_+"-"+customFeatures.get(i).properties.To_, customFeatures.get(i).properties.Status, customFeatures.get(i).properties.Maint_Time, customFeatures.get(i).properties.Maint_Type));
            }

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListViewItem item = (com.jonneykim.skateatrideau.ListViewItem) adapter.getItem(position);
                    Toast.makeText(getApplicationContext(), "Selected: "+item.fromTo, Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(getApplicationContext(), subActivity.class);
                    intent.putExtra("id", customFeatures.get(position).id);


                    startActivityForResult(intent,101);
                }
            });

        }

        gson = new Gson();

        if(featureList !=null){
            customFeatures = featureList.features;
            String generalNotice = featureList.features.get(0).properties.General_Notice;
            if (generalNotice!=null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    textView3.setText(Html.fromHtml(generalNotice, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    textView3.setText(Html.fromHtml(generalNotice));
                }
            }
            //println("Number of Collections: "+size);


        }

    }
    class ListViewAdapter extends BaseAdapter {
        ArrayList<Object> items = new ArrayList<>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ListViewItem item){
            items.add(item);
        }
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            com.jonneykim.skateatrideau.ListView view = new com.jonneykim.skateatrideau.ListView(getApplicationContext());

            ListViewItem item = (ListViewItem) items.get(position);
            view.setTitle(item.getFromTo());
            view.setDate(item.getStatus());

            return view;
        }
    }
}
