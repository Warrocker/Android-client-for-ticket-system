package warrocker.ticketsystem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import warrocker.ticketsystem.hibernate.Users;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем текст в TextView (tvOut)
                new SocketTask().execute();
            }
        });
    }
    class SocketTask extends AsyncTask<String, Void, Users> {
        Object response;
        Users user;
        String login;
        String pass;
        private Socket socket;
        Users sendingUser;
        @Override
        protected void onPreExecute(){
           login = String.valueOf(mEmailView.getText());
            pass = String.valueOf(mPasswordView.getText());
            sendingUser = new Users(login, pass);
        }
        @Override
        protected Users doInBackground(String... params) {
            try {
                socket = new Socket("192.168.1.102", 4445);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (socket != null) {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
                    objectOutputStream.writeObject(sendingUser);
                    response = objectInputStream.readObject();
                    if(response instanceof Users){
                        user =  (Users) response;
                    }else{
                        return null;
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return user;
        }

        @Override
        protected void onPostExecute(Users user) {
            if(user == null){
                Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();

            }else {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        }
    }
}

