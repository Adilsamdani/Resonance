package com.example.resonance;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText noteEdit;
    private Button increaseSizebtn,decreaseSizeBtn,boldBtn,italicBtn,underlineBtn;
    private TextView sizeTv;
    StickyNote note =new StickyNote();
    float currentSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEdit = findViewById(R.id.idEdit);
        increaseSizebtn =findViewById(R.id.idBtnincreaseSize);
        decreaseSizeBtn= findViewById(R.id.idBtnReduceSize);
        boldBtn = findViewById(R.id.idbtnBold);
        underlineBtn = findViewById(R.id.idbtnunderline);
        italicBtn = findViewById(R.id.idbtnitalic);
        sizeTv = findViewById(R.id.idTVSize);
        currentSize =noteEdit.getTextSize();
        sizeTv.setText(""+currentSize);
        increaseSizebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sizeTv.setText(""+currentSize);
                currentSize++;
                noteEdit.setTextSize(currentSize);
            }

        });

        decreaseSizeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sizeTv.setText(""+currentSize);
                currentSize--;
                noteEdit.setTextSize(currentSize);
            }

        });
        boldBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                italicBtn.setTextColor(getResources().getColor(R.color.white));
                italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteEdit.getTypeface().isBold()){
                    noteEdit.setTypeface(Typeface.DEFAULT);
                    boldBtn.setTextColor(getResources().getColor(R.color.white));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }else{
                    boldBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdit.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }

            }
        });

        italicBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boldBtn.setTextColor(getResources().getColor(R.color.white));
                boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if (noteEdit.getTypeface().isBold()) {
                    noteEdit.setTypeface(Typeface.DEFAULT);
                    italicBtn.setTextColor(getResources().getColor(R.color.white));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                } else {
                    italicBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdit.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

                }
            }
        });
        underlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noteEdit.getPaintFlags()==8){
                    underlineBtn.setTextColor(getResources().getColor(R.color.white));
                    underlineBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteEdit.setPaintFlags(noteEdit.getPaintFlags()& (~Paint.UNDERLINE_TEXT_FLAG));
                }else{
                    underlineBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    underlineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdit.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });
    }

    public void SaveButton(View view) {
        note.setStick(noteEdit.toString(),this);
        updateWidget();
        Toast.makeText(this, "Note has been updated", Toast.LENGTH_SHORT).show();

    }
    private void updateWidget(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews= new RemoteViews(this.getPackageName(), R.layout.widget_layout);
        ComponentName thsWidget = new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.idTVWidget, noteEdit.getText().toString());
        appWidgetManager.updateAppWidget(thsWidget,remoteViews);

    }
}
