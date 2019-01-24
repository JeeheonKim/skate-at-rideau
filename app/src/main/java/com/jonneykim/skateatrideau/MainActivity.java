package com.jonneykim.skateatrideau;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;
    Button button;
    ArrayList<Long> arrayList = new ArrayList<>();
    ArrayList<CollectionItem> customCollection;
    ListView listView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ListViewAdapter();





        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());

        }

        sendRequest();
    }

    public void sendRequest(){
        String urlStr = "https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
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
            protected Map<String, String> getParams() throws AuthFailureError {
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
        CollectionList collectionList = gson.fromJson(response, CollectionList.class);

        if(collectionList!=null){
            customCollection = collectionList.custom_collections;
            int size = customCollection.size();
            //println("Number of Collections: "+size);
            for(int i=0; i<size; i++){
                adapter.addItem(new ListViewItem(customCollection.get(i).title, "Updated at :"+customCollection.get(i).updated_at));
                arrayList.add(customCollection.get(i).id); // is this needed?

            }

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListViewItem item = (com.jonneykim.skateatrideau.ListViewItem) adapter.getItem(position);
                    Toast.makeText(getApplicationContext(), "Selected: "+item.getTitle(), Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(getApplicationContext(), subActivity.class);
                    intent.putExtra("id", customCollection.get(position).id);


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
            view.setTitle(item.getTitle());
            view.setDate(item.getDate());

            return view;
        }
    }
}
