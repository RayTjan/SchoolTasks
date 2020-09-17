package com.uc.try2b4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DisplayUser extends AppCompatActivity {
    TextView dname,dage,daddress;
    ArrayList<items>  itemlist;
    Button deleteUser,UpdateUser;
    int position;
    items object;
    ArrayBox itemlist1;
    Toolbar back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);
         dname = findViewById(R.id.DisplayName);
         dage = findViewById(R.id.DisplayAge);
         daddress = findViewById(R.id.DisplayAddress);
         deleteUser = findViewById(R.id.DeleteUserButton);
         UpdateUser = findViewById(R.id.EditUserButton);
         back = findViewById(R.id.add_toolbar);

        itemlist1 = new ArrayBox();
        itemlist = getIntent().getParcelableArrayListExtra("what");
        if (itemlist == null){
            Log.d("NIIININIINI", "WUUUT");
        }
        position = getIntent().getIntExtra("pos",0);
        Log.d("LEPOSITIOINNINN", Integer.toString(position));
        object = itemlist.get(position);

        dname.setText(object.getMtext1());
        dage.setText(object.getMtext2());
        daddress.setText(object.getMtext3());


        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(DisplayUser.this, MainActivity.class);
                itemlist.remove(position);
                home.putExtra("what2",itemlist);
                startActivity(home);
            }
        });


        UpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(DisplayUser.this,AddActivity.class);
                update.putExtra("what2",itemlist);
                update.putExtra("pos",position);
                startActivity(update);
            }
        });

        back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previous = new Intent(DisplayUser.this, MainActivity.class);
                previous.putExtra("what2",itemlist);
                previous.putExtra("pos",position);
                startActivity(previous);
                finish();
            }
        });
    }
}