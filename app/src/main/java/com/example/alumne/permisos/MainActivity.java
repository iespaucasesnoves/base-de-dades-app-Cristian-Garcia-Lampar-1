package com.example.alumne.permisos;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int CALLBACK_NUMERO = 100;

    Button bot1;
    Button bot2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bot1 = findViewById(R.id.botCamara);
        bot2 = findViewById(R.id.botContactos);

 /*       bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }

            public void onRequestPermissionsResult(int requestCode,
                                                   String permissions[], int[] grantResults) {
                switch (requestCode) {
                    case 1: {

                        // If request is cancelled, the result arrays are empty.
                        if (grantResults.length > 0
                                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                            // permission was granted, yay! Do the
                            // contacts-related task you need to do.
                        } else {

                            // permission denied, boo! Disable the
                            // functionality that depends on this permission.
                            Toast.makeText(MainActivity.this, "DENEGADO", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }

                    // other 'case' lines to check for other
                    // permissions this app might request
                }
            }
        });



}*/

}
