package com.example.pijus.regitrosklausimynas;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity{

    private static final int STORAGE_CODE=1000;
    Integer Position=0;
    Button submit_button;
    ExpandableListView expandableListView;
    ExpandableListAdapter[] expandableListAdapter=new ExpandableListAdapter[3];
    ArrayList<List<String>> expandableListTitle=new ArrayList<List<String>>();
    ArrayList<LinkedHashMap<String, List<OneMistake>> > expandableListDetail=new ArrayList<LinkedHashMap<String, List<OneMistake>>>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView=(ExpandableListView)findViewById(R.id.Expandable);
        for (int i=0;i<3;i++){
            expandableListDetail.add(new LinkedHashMap<String,List<OneMistake>>(ExpandableListDataPump.getMistakes(i)));
            expandableListTitle.add(new ArrayList<String>(expandableListDetail.get(i).keySet()));
            expandableListAdapter[i]=new CustomExpandableListAdapter(this,expandableListTitle.get(i),expandableListDetail.get(i));
        }
        expandableListView.setAdapter(expandableListAdapter[0]);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener(){
                @Override
                public void onGroupExpand(int groupPosition){
                    // Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " List expanded.", Toast.LENGTH_SHORT).show();
                }
            });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener(){
                @Override
                public void onGroupCollapse(int groupPosition){
                    // Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
                }
            });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
                    for(int i=0;i<3;i++){
                        if(Position==i){
                            if(expandableListDetail.get(i).get(expandableListTitle.get(i).get(groupPosition)).get(childPosition).isSelected==false){
                                expandableListDetail.get(i).get(expandableListTitle.get(i).get(groupPosition)).get(childPosition).isSelected=true;
                                ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                                selected.setBackgroundResource(R.drawable.checked);
                            }
                            else{
                                expandableListDetail.get(i).get(expandableListTitle.get(i).get(groupPosition)).get(childPosition).isSelected=false;
                                ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                                selected.setBackgroundResource(R.drawable.check);
                            }
                        }
                    }
                    return false;
                }
            });
        Spinner DropDown=(Spinner) findViewById(R.id.Spinerris);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Nekritines klaidos");
        arrayList.add("Kritines klaidos");
        arrayList.add("Bendrosios klaidos");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DropDown.setAdapter(arrayAdapter);
        DropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                    Position=position;
                    for(int i=0;i<3;i++){
                        if(Position==i){
                            expandableListView.setAdapter(expandableListAdapter[i]);
                        }
                    }
                }
                @Override
                public void onNothingSelected(AdapterView <?> parent){
                }
            });
        submit_button=findViewById(R.id.Button1);
        submit_button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.save1)
                        .setTitle("Klaidų išsaugojimas")
                        .setMessage("Ar jūs tikrai norite išsaugoti šiuos duomenis?")
                        .setPositiveButton("Taip", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which){
                                    if(Build.VERSION.SDK_INT> Build.VERSION_CODES.M){
                                        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                                            String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                            requestPermissions(permissions, STORAGE_CODE);
                                        }
                                        else{
                                            savePdf();
                                        }
                                    }
                                    else{
                                        savePdf();
                                    }
                                }

                            })
                        .setNegativeButton("Ne", null)
                        .show();
                }
            });
    }
    private void savePdf(){
        Document mDoc=new Document();
        String File_name=new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(System.currentTimeMillis());
        String File_path=Environment.getExternalStorageDirectory() + "/" + File_name + ".pdf";
        try{
            PdfWriter.getInstance(mDoc,new FileOutputStream(File_path));
            mDoc.open();
            String Text_to_file="";
            for (int i=0;i<3;i++){
                for(int j=0;j<(expandableListDetail.get(i)).size();j++){
                    for(int k=0;k<(expandableListDetail.get(i)).get(expandableListTitle.get(i).get(j)).size();k++){
                        if((expandableListDetail.get(i)).get(expandableListTitle.get(i).get(j)).get(k).isSelected){
                            Text_to_file+=(expandableListDetail.get(i)).get(expandableListTitle.get(i).get(j)).get(k).oneMistake;
                            Text_to_file+="\n";
                        }
                    }
                }
            }
            mDoc.add(new Paragraph(deAccent(Text_to_file)));
            mDoc.close();
            Toast.makeText(this,"Saved succesfully"+File_name+".pdf \n saved to "+File_path,Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
        case  STORAGE_CODE:{
            if(grantResults.length >0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
            }
            else{
                Toast.makeText(this,"permission denied...!!!",Toast.LENGTH_SHORT).show();
            }
        }
        }
    }
    public String deAccent(String str){
        String nfdNormalizedString=Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern=Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
