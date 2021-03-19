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
    List<One_mistake> Mistakes = new ArrayList<>();
    final List<One_mistake> KK_Mistakes = new ArrayList<>();
    final List<One_mistake> BK_Mistakes = new ArrayList<>();
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListAdapter expandableListAdapter_kk;
    ExpandableListAdapter expandableListAdapter_bkk;
    List<String> expandableListTitle;
    List<String> expandableListTitle_kk;
    List<String> expandableListTitle_bkk;
    LinkedHashMap<String, List<One_mistake>> expandableListDetail;
    LinkedHashMap<String, List<One_mistake>> expandableListDetail_kk;
    LinkedHashMap<String, List<One_mistake>> expandableListDetail_bkk;
    public int Spinner_id;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.Expandable);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListDetail_kk = ExpandableListDataPump.getData1();
        expandableListDetail_bkk = ExpandableListDataPump.getData2();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListTitle_kk = new ArrayList<String>(expandableListDetail_kk.keySet());
        expandableListTitle_bkk = new ArrayList<String>(expandableListDetail_bkk.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle,expandableListDetail);
        expandableListAdapter_kk = new CustomExpandableListAdapter(this, expandableListTitle_kk,expandableListDetail_kk);
        expandableListAdapter_bkk = new CustomExpandableListAdapter(this, expandableListTitle_bkk,expandableListDetail_bkk);
        expandableListView.setAdapter(expandableListAdapter);
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
                    if(Position==0){
                        //Toast.makeText(getApplicationContext(),expandableListTitle.get(groupPosition) + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).getOne_Mistake(), Toast.LENGTH_SHORT).show();
                        if(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).isSelected==false){
                            expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).isSelected = true;
                            ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                            selected.setBackgroundResource(R.drawable.checked);
                        }
                        else{
                            expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).isSelected=false;
                            ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                            selected.setBackgroundResource(R.drawable.check);
                        }
                    }

                    if(Position==1){
                        //Toast.makeText(getApplicationContext(),expandableListTitle_kk.get(groupPosition) + " -> " + expandableListDetail_kk.get(expandableListTitle_kk.get(groupPosition)).get(childPosition).getOne_Mistake(), Toast.LENGTH_SHORT).show();
                        if(expandableListDetail_kk.get(expandableListTitle_kk.get(groupPosition)).get(childPosition).isSelected==false){
                            expandableListDetail_kk.get(expandableListTitle_kk.get(groupPosition)).get(childPosition).isSelected = true;
                            ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                            selected.setBackgroundResource(R.drawable.checked);
                        }
                        else{
                            expandableListDetail_kk.get(expandableListTitle_kk.get(groupPosition)).get(childPosition).isSelected=false;
                            ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                            selected.setBackgroundResource(R.drawable.check);
                        }
                    }
                    if(Position==2){
                        //Toast.makeText(getApplicationContext(),expandableListTitle_bkk.get(groupPosition) + " -> " + expandableListDetail_bkk.get(expandableListTitle_bkk.get(groupPosition)).get(childPosition).getOne_Mistake(), Toast.LENGTH_SHORT).show();
                        if(expandableListDetail_bkk.get(expandableListTitle_bkk.get(groupPosition)).get(childPosition).isSelected==false){
                            expandableListDetail_bkk.get(expandableListTitle_bkk.get(groupPosition)).get(childPosition).isSelected = true;
                            ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                            selected.setBackgroundResource(R.drawable.checked);
                        }
                        else{
                            expandableListDetail_bkk.get(expandableListTitle_bkk.get(groupPosition)).get(childPosition).isSelected=false;
                            ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                            selected.setBackgroundResource(R.drawable.check);
                        }
                    }
                    return false;
                }
            });

        Spinner DropDown=(Spinner) findViewById(R.id.Spinerris);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Nekritines klaidos");
        arrayList.add("Kritines klaidos");
        arrayList.add("Bendrosios klaidos");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DropDown.setAdapter(arrayAdapter);
        DropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                    Spinner_id=position;
                    Position=position;
                    if(Spinner_id==0){
                        expandableListView.setAdapter(expandableListAdapter);
                    }
                    if(Spinner_id==1){
                        expandableListView.setAdapter(expandableListAdapter_kk);
                    }
                    if(Spinner_id==2){
                        expandableListView.setAdapter(expandableListAdapter_bkk);
                    }
                    //Spinner_name = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(parent.getContext(), "Selected: " + Spinner_name+" "+String.valueOf(Spinner_id), Toast.LENGTH_LONG).show();
                }
                @Override
                public void onNothingSelected(AdapterView <?> parent){
                }
            });
        //        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        //            @Override
        //            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        //                if(Spinner_id==0){
        //                    One_mistake Selected_mistake = Mistakes.get(i);
        //                    if (Selected_mistake.isSelected()){
        //                        Selected_mistake.setSelected(false);
        //                    } else{
        //                        Selected_mistake.setSelected(true);
        //                    }
        //                    Mistakes.set(i, Selected_mistake);
        //                    //now update adapter
        //                    adapter.updateRecords(Mistakes);
        //                }
        //               else if(Spinner_id==1){
        //                    One_mistake Selected_mistake = KK_Mistakes.get(i);
        //                    if (Selected_mistake.isSelected()){
        //                        Selected_mistake.setSelected(false);
        //                    }
        //                    else{
        //                        Selected_mistake.setSelected(true);
        //                    }
        //                    KK_Mistakes.set(i, Selected_mistake);
        //                    //now update adapter
        //                    adapter1.updateRecords(KK_Mistakes);
        //                }
        //               else if(Spinner_id==2){
        //                    One_mistake Selected_mistake = BK_Mistakes.get(i);
        //                    if (Selected_mistake.isSelected()){
        //                        Selected_mistake.setSelected(false);
        //                    }
        //                    else{
        //                        Selected_mistake.setSelected(true);
        //                    }
        //                    BK_Mistakes.set(i, Selected_mistake);
        //                    //now update adapter
        //                    adapter2.updateRecords(BK_Mistakes);
        //                }
        //            }
        //        });
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
        Document mDoc = new Document();
        String File_name = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(System.currentTimeMillis());
        String File_path = Environment.getExternalStorageDirectory() + "/" + File_name + ".pdf";
        try{
            PdfWriter.getInstance(mDoc,new FileOutputStream(File_path));
            mDoc.open();
            String Text_to_file="";
            for(int i=0;i<expandableListDetail.size();i++){
                for(int j=0;j<expandableListDetail.get(expandableListTitle.get(i)).size();j++){
                    if(expandableListDetail.get(expandableListTitle.get(i)).get(j).isSelected){
                        Text_to_file+=expandableListDetail.get(expandableListTitle.get(i)).get(j).One_Mistake;
                        Text_to_file+="\n";
                    }
                }
            }
            for(int i=0;i<expandableListDetail_kk.size();i++){
                for(int j=0;j<expandableListDetail_kk.get(expandableListTitle_kk.get(i)).size();j++){
                    if(expandableListDetail_kk.get(expandableListTitle_kk.get(i)).get(j).isSelected){
                        Text_to_file+=expandableListDetail_kk.get(expandableListTitle_kk.get(i)).get(j).One_Mistake;
                        Text_to_file+="\n";
                    }
                }
            }
            for(int i=0;i<expandableListDetail_bkk.size();i++){
                for(int j=0;j<expandableListDetail_bkk.get(expandableListTitle_bkk.get(i)).size();j++){
                    if(expandableListDetail_bkk.get(expandableListTitle_bkk.get(i)).get(j).isSelected){
                        Text_to_file+=expandableListDetail_bkk.get(expandableListTitle_bkk.get(i)).get(j).One_Mistake;
                        Text_to_file+="\n";
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
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
