package com.jonneykim.skateatrideau;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

/*

{
  ....
  "features": [
    {
      "type": "Feature",
      "id": 14,
      "geometry": null,
      "properties": {
        "OBJECTID": 14,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Mackenzie King Bridge",
        "From_": "Rideau Locks (NAC)",
        "De_": "Écluses Rideau (CNA)",
        "A_": "Pont Mackenzie-King",
        "ID": 1,
        "Length_KM": "0.2 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.2 km",
        "Shape__Area": 5930.35620117188,
        "Shape__Length": 526.7536499575
      }
    },
    {
      "type": "Feature",
      "id": 15,
      "geometry": null,
      "properties": {
        "OBJECTID": 15,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Laurier Bridge",
        "From_": "Mackenzie King Bridge",
        "De_": "Pont Mackenzie-King",
        "A_": "Pont Laurier",
        "ID": 2,
        "Length_KM": "0.2 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.2 km",
        "Shape__Area": 5160.05749511719,
        "Shape__Length": 440.104318699364
      }
    },
    {
      "type": "Feature",
      "id": 17,
      "geometry": null,
      "properties": {
        "OBJECTID": 17,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Corktown Footbridge / Somerset",
        "From_": "Laurier Bridge",
        "De_": "Pont Laurier",
        "A_": "Passerelle Corktown / Somerset",
        "ID": 3,
        "Length_KM": "0.4 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.4 km",
        "Shape__Area": 9814.42260742188,
        "Shape__Length": 900.492216589125
      }
    },
    {
      "type": "Feature",
      "id": 18,
      "geometry": null,
      "properties": {
        "OBJECTID": 18,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Concord Street",
        "From_": "Corktown Footbridge / Somerset",
        "De_": "Passerelle Corktown / Somerset",
        "A_": "Rue Concord",
        "ID": 4,
        "Length_KM": "0.6 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.6 km",
        "Shape__Area": 20236.9404296875,
        "Shape__Length": 1230.71983476397
      }
    },
    {
      "type": "Feature",
      "id": 19,
      "geometry": null,
      "properties": {
        "OBJECTID": 19,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Pretoria Bridge",
        "From_": "Concord Street",
        "De_": "Rue Concord",
        "A_": "Pont Prétoria",
        "ID": 5,
        "Length_KM": "0.65 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.65 km",
        "Shape__Area": 28561.2824707031,
        "Shape__Length": 1399.50053405191
      }
    },
    {
      "type": "Feature",
      "id": 20,
      "geometry": null,
      "properties": {
        "OBJECTID": 20,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Fifth Ave",
        "From_": "Pretoria Bridge",
        "De_": "Pont Prétoria",
        "A_": "L'avenue Fifth",
        "ID": 6,
        "Length_KM": "0.8 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.8 km",
        "Shape__Area": 43548.42578125,
        "Shape__Length": 1810.56310976367
      }
    },
    {
      "type": "Feature",
      "id": 21,
      "geometry": null,
      "properties": {
        "OBJECTID": 21,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Patterson Creek",
        "From_": "Patterson Creek",
        "De_": "Ruisseau Patterson",
        "A_": "Ruisseau Patterson",
        "ID": 13,
        "Length_KM": "0.3 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.3 km",
        "Shape__Area": 3535.86572265625,
        "Shape__Length": 403.991208814948
      }
    },
    {
      "type": "Feature",
      "id": 22,
      "geometry": null,
      "properties": {
        "OBJECTID": 22,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Pig Island",
        "From_": "Fifth Ave",
        "De_": "L'avenue Fifth",
        "A_": "L'île Pig",
        "ID": 7,
        "Length_KM": "0.7 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.7 km",
        "Shape__Area": 68286.7999267578,
        "Shape__Length": 1616.39121179068
      }
    },
    {
      "type": "Feature",
      "id": 23,
      "geometry": null,
      "properties": {
        "OBJECTID": 23,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Bank Street Bridge",
        "From_": "Pig Island",
        "De_": "L'île Pig",
        "A_": "Pont de la rue Bank",
        "ID": 8,
        "Length_KM": "0.45 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.45 km",
        "Shape__Area": 24556.0665283203,
        "Shape__Length": 1063.09770148755
      }
    },
    {
      "type": "Feature",
      "id": 24,
      "geometry": null,
      "properties": {
        "OBJECTID": 24,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Bronson Bridge",
        "From_": "Bank Street Bridge",
        "De_": "Pont de la rue Bank",
        "A_": "Pont de l'avenue Bronson",
        "ID": 9,
        "Length_KM": "1.0 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "1.0 km",
        "Shape__Area": 24543.1015625,
        "Shape__Length": 2036.44131207994
      }
    },
    {
      "type": "Feature",
      "id": 25,
      "geometry": null,
      "properties": {
        "OBJECTID": 25,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Dows Lake",
        "From_": "Bronson Bridge",
        "De_": "Pont de l'avenue Bronson",
        "A_": "Lac Dows",
        "ID": 10,
        "Length_KM": "0.2 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.2 km",
        "Shape__Area": 18976.9705810547,
        "Shape__Length": 753.734688329493
      }
    },
    {
      "type": "Feature",
      "id": 26,
      "geometry": null,
      "properties": {
        "OBJECTID": 26,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Dows Lake",
        "From_": "Dows Lake",
        "De_": "Lac Dows",
        "A_": "Lac Dows",
        "ID": 11,
        "Length_KM": "1.7 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "1.7 km",
        "Shape__Area": 223043.314086914,
        "Shape__Length": 2487.0685659925
      }
    },
    {
      "type": "Feature",
      "id": 27,
      "geometry": null,
      "properties": {
        "OBJECTID": 27,
        "Status": "Fair",
        "Status_FR": "Passable",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Snow removal",
        "Maint_Type_FR": "Déneigement",
        "To_": "Hartwells Locks",
        "From_": "Dows Lake",
        "De_": "Lac Dows",
        "A_": "Écluses Hartwells",
        "ID": 12,
        "Length_KM": "0.6 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.6 km",
        "Shape__Area": 35161.9029541016,
        "Shape__Length": 1505.0777045262
      }
    }
  ]

}

*/

// TODO tells the user that about the internet connection
// Updat
// TODO where is my nearest stair?

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;
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
        listView = findViewById(R.id.listView);
        adapter = new ListViewAdapter();


        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());

        }

        sendRequest();
    }

    public void sendRequest(){
        String urlStr = "https://services2.arcgis.com/WLyMuW006nKOfa5Z/arcgis/rest/services/RCS_Status_PUBLIC/FeatureServer/0/query?where=1%3D1%20&outFields=*%20&returnGeometry=false&f=pgeojson";
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
