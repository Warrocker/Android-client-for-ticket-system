package warrocker.ticketsystem;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import warrocker.ticketsystem.hibernate.Category;
import warrocker.ticketsystem.hibernate.Group;
import warrocker.ticketsystem.hibernate.Priority;
import warrocker.ticketsystem.hibernate.Status;
import warrocker.ticketsystem.hibernate.Ticket;
import warrocker.ticketsystem.hibernate.Users;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int PORT = 4445;
    public static final String HOST = "192.168.1.102";
    ListView listView;
    ArrayList<Ticket> ticketArrayList = new ArrayList<>();
    ArrayList<Priority> priorityArrayList = new ArrayList<>();
    ArrayList<Users> performerArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        ArrayAdapter<Ticket> adapter = new TicketAdapter(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        new PerformerTask().execute();
        new PriorityTask().execute();
        new TicketTask().execute();

        listView.setAdapter(adapter);
//        listView.setOnItemClickListener();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class TicketAdapter extends ArrayAdapter<Ticket> {

        public TicketAdapter(Context context) {
            super(context, R.layout.list_item, ticketArrayList);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Ticket ticket = getItem(position);
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy", Locale.getDefault());
            String ticketId = "Заявка № " + ticket.getId();
            String st_dtime = "Назначено на: " + dateFormat.format(ticket.getSt_dtime());



            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.list_item, listView, false);
            }
            ((TextView) convertView.findViewById(R.id.ticketid))
                    .setText(ticketId);
            ((TextView) convertView.findViewById(R.id.title))
                    .setText(ticket.getTitle());
            ((TextView) convertView.findViewById(R.id.st_dtime))
                    .setText(st_dtime);
            for(Users usr : performerArrayList){
                if(usr.getId() == ticket.getPerformer()) {
                    String performer = "Исполнитель: " + usr.getCaption();
                    ((TextView) convertView.findViewById(R.id.performer))
                            .setText(performer);
                    break;
                }
            }for(Priority prior : priorityArrayList){
                if(prior.getId() == ticket.getPriority()) {
                    String priority = "Приоритет: "+ prior.getCaption();
                    ((TextView) convertView.findViewById(R.id.priorityView))
                            .setText(priority);
                    break;
                }
            }
            return convertView;
        }

    }



    class TicketTask extends AsyncTask<String, Void, ArrayList<Ticket>> {
        private Socket socket;
        ArrayList objectArrayList = new ArrayList<>();


        @Override
        protected void onPreExecute(){

        }
        @Override
        protected ArrayList<Ticket> doInBackground(String... params) {
            Object response;
            try {
                socket = new Socket(HOST, PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (socket != null) {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

                    objectOutputStream.writeObject(new RequestCode(RequestCode.ACTIVE));
                    response = objectInputStream.readObject();
                    if(response instanceof ArrayList){
                        objectArrayList = (ArrayList) response;
                        for(Object object : objectArrayList) {
                            Ticket ticket = (Ticket) object;

                            ticketArrayList.add(ticket);
                        }
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return ticketArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Ticket> ticketArrayList) {
            runOnUiThread(new Runnable() {
                public void run() {
                    ArrayAdapter<Ticket> adapter = new TicketAdapter(MainActivity.this);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }
            });

        }
    }
    class PerformerTask extends AsyncTask<Void, Void, ArrayList<Users>>{
        private Socket socket;
        ArrayList objectArrayList = new ArrayList<>();
        @Override
        protected ArrayList<Users> doInBackground(Void... voids) {
            Object response;
            try {
                socket = new Socket(HOST, PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (socket != null) {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

                    objectOutputStream.writeObject(new RequestCode(RequestCode.USERS));
                    response = objectInputStream.readObject();
                    if(response instanceof ArrayList){
                        objectArrayList = (ArrayList) response;
                        for(Object object : objectArrayList) {
                            Users user = (Users) object;

                            performerArrayList.add(user);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return performerArrayList;
        }
        @Override
        protected void onPostExecute(ArrayList<Users> performerArrayList) {
            runOnUiThread(new Runnable() {
                public void run() {
                    ArrayAdapter<Ticket> adapter = new TicketAdapter(MainActivity.this);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }
            });

        }
    }
    class PriorityTask extends AsyncTask<Void, Void, ArrayList<Priority>>{
        private Socket socket;
        ArrayList objectArrayList = new ArrayList<>();
        @Override
        protected ArrayList<Priority> doInBackground(Void... voids) {
            Object response;
            try {
                socket = new Socket(HOST, PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (socket != null) {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

                    objectOutputStream.writeObject(new RequestCode(RequestCode.PRIORITY));
                    response = objectInputStream.readObject();
                    if(response instanceof ArrayList){
                        objectArrayList = (ArrayList) response;
                        for(Object object : objectArrayList) {
                            Priority priority = (Priority) object;
                            priorityArrayList.add(priority);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return priorityArrayList;
        }
        @Override
        protected void onPostExecute(ArrayList<Priority> priorityArrayList) {
            runOnUiThread(new Runnable() {
                public void run() {
                    ArrayAdapter<Ticket> adapter = new TicketAdapter(MainActivity.this);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }
            });

        }
    }
}
