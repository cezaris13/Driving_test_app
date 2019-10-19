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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static  final int STORAGE_CODE=1000;
    Button submit_button;
    List<UserModel> Mistakes = new ArrayList<>();
    final List<UserModel> KK_Mistakes = new ArrayList<>();
    final List<UserModel> BK_Mistakes = new ArrayList<>();
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<UserModel>> expandableListDetail;
    //public String Spinner_name;
    public int Spinner_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.Expandable);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle,expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " List expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               Toast.makeText(getApplicationContext(),expandableListTitle.get(groupPosition) + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).getUserName(), Toast.LENGTH_SHORT).show();
               if(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).isSelected==false) {
                   expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).isSelected = true;
                   ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                   selected.setBackgroundResource(R.drawable.checked);
               }
               else{
                   expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).isSelected=false;
                   ImageView selected=(ImageView) v.findViewById(R.id.checkbox);
                   selected.setBackgroundResource(R.drawable.check);
               }
               return false;
            }
        });
//        final ListView listView = (ListView) findViewById(R.id.List_View);
//        Mistakes.add(new com.multiselect.UserModel(false, "13. Patikrinimas prieš važiavimą","13. Patikrinimas prieš važiavimą",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "NK – nesugeba surasti egzaminuotojo nurodytų transporto priemonės valdymo įtaisų ar įrangos; pademonstruoti savo žinių ar sugebėjimų, kaip juos valdyti, sureguliuoti arba patikrinti jų būklės; pasirengti važiuoti saugiai.","NK – nesugeba surasti egzaminuotojo nurodytų transporto priemonės valdymo įtaisų ar įrangos; pademonstruoti savo žinių ar sugebėjimų, kaip juos valdyti, sureguliuoti arba patikrinti jų būklės; pasirengti važiuoti saugiai.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "14. Papildomi valdymo ir saugos įtaisai. Šios NK vertinamos tik po dviejų egzaminuotojo įspėjimų: „Prašau patikrinti valdymo įtaisus ir įrangą“. Turi būti skirta pakankamai laiko egzaminuojamajam pasitaisyti po kiekvieno įspėjimo:","14. Papildomi valdymo ir saugos įtaisai. Šios NK vertinamos tik po dviejų egzaminuotojo įspėjimų: „Prašau patikrinti valdymo įtaisus ir įrangą“. Turi būti skirta pakankamai laiko egzaminuojamajam pasitaisyti po kiekvieno įspėjimo:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;","14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;","14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;","14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.","14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15. Sankabos valdymas:","15. Sankabos valdymas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);","15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;","15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.3. NK – per greitai atleidžia sankabos paminą;","15.3. NK – per greitai atleidžia sankabos paminą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;","15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);","15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;","15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);","15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.8. SPK – šešis kartus užgesina variklį;","15.8. SPK – šešis kartus užgesina variklį;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "15.9. SPK – šešis kartus važiuoja išjungta sankaba.","15.9. SPK – šešis kartus važiuoja išjungta sankaba.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "16. Akceleratoriaus valdymas:","16. Akceleratoriaus valdymas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "16.1. NK – netinkamai valdo akceleratoriaus paminą arba motociklo degalų padavimo reguliatorių (droselį);","16.1. NK – netinkamai valdo akceleratoriaus paminą arba motociklo degalų padavimo reguliatorių (droselį);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "16.2. NK – per daug nuspaudžia akceleratoriaus paminą arba motociklo degalų padavimo reguliatorių (droselį), kai sankaba išjungta ar nevisiškai įjungta;","16.2. NK – per daug nuspaudžia akceleratoriaus paminą arba motociklo degalų padavimo reguliatorių (droselį), kai sankaba išjungta ar nevisiškai įjungta;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "16.3. NK – spaudžia akceleratoriaus paminą ir neleidžia transporto priemonei judėti iš inercijos prieš sustojant iš anksto numatytoje vietoje (sankryžoje, kurioje dega draudžiamas šviesoforo signalas, stovi „Stop“ ženklas ir pan.) ar kitose situacijose.","16.3. NK – spaudžia akceleratoriaus paminą ir neleidžia transporto priemonei judėti iš inercijos prieš sustojant iš anksto numatytoje vietoje (sankryžoje, kurioje dega draudžiamas šviesoforo signalas, stovi „Stop“ ženklas ir pan.) ar kitose situacijose.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17. Pavaros pasirinkimas:","17. Pavaros pasirinkimas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.1. NK – pasirenka pavarą, neatitinkančią: situacijos, transporto priemonės greičio, techninių duomenų ir važiavimo sąlygų, bet nesudaro potencialiai pavojingos situacijos;","17.1. NK – pasirenka pavarą, neatitinkančią: situacijos, transporto priemonės greičio, techninių duomenų ir važiavimo sąlygų, bet nesudaro potencialiai pavojingos situacijos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.2. NK – važiuoja įjungęs neutralią pavarą išlanka (posūkiu ar vingiu) arba nuokalne;","17.2. NK – važiuoja įjungęs neutralią pavarą išlanka (posūkiu ar vingiu) arba nuokalne;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.3. NK - jungia pavarą, kai važiuodamas sankryžoje ar išlankoje suka vairą, ir (arba) pavaros perjungimas trukdo transporto priemonei judėti sklandžiai ir tolygiai;","17.3. NK - jungia pavarą, kai važiuodamas sankryžoje ar išlankoje suka vairą, ir (arba) pavaros perjungimas trukdo transporto priemonei judėti sklandžiai ir tolygiai;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.4.NK (B kategorija) – aukštesnę pavarą jungia varikliui pasiekus 2300 ar daugiau sūkių per minutę, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","17.4.NK (B kategorija) – aukštesnę pavarą jungia varikliui pasiekus 2300 ar daugiau sūkių per minutę, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.5. NK (B kategorija) – pirma pavara nuvažiuoja didesnį kaip dviejų automobilių ilgio atstumą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","17.5. NK (B kategorija) – pirma pavara nuvažiuoja didesnį kaip dviejų automobilių ilgio atstumą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.6. NK (B kategorija) – tolygiai važiuoja 30 km/h ar didesniu greičiu, įjungęs žemesnę nei trečią pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","17.6. NK (B kategorija) – tolygiai važiuoja 30 km/h ar didesniu greičiu, įjungęs žemesnę nei trečią pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.7. NK (B kategorija) – tolygiai važiuoja 50 km/h ar didesniu greičiu, įjungęs žemesnę nei ketvirtą pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","17.7. NK (B kategorija) – tolygiai važiuoja 50 km/h ar didesniu greičiu, įjungęs žemesnę nei ketvirtą pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.8. NK (B kategorija) – tolygiai važiuoja 70 km/h ar didesniu greičiu, įjungęs žemesnę nei penktą pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","17.8. NK (B kategorija) – tolygiai važiuoja 70 km/h ar didesniu greičiu, įjungęs žemesnę nei penktą pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.9. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – spaudžia automatinės pavarų dėžės selektoriaus mygtuką, kai iš žemesnių pavarų diapazono (1, 2, 3 pozicijos) persijungia į važiavimo pavarą („Drive“ pozicija);","17.9. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – spaudžia automatinės pavarų dėžės selektoriaus mygtuką, kai iš žemesnių pavarų diapazono (1, 2, 3 pozicijos) persijungia į važiavimo pavarą („Drive“ pozicija);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.10. NK – bando jungti arba jungia pavarą neišjungęs sankabos.","17.10. NK – bando jungti arba jungia pavarą neišjungęs sankabos.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.11. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – per daug triukšmingai jungia pavaras;","17.11. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – per daug triukšmingai jungia pavaras;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.12. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – neperjungia pavaros iš antro karto;","17.12. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – neperjungia pavaros iš antro karto;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.13. NK (A1, A2 ir A kategorijos) – atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą, nesugeba sklandžiai perjungti pavaros;","17.13. NK (A1, A2 ir A kategorijos) – atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą, nesugeba sklandžiai perjungti pavaros;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.14. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – netinkamai naudojasi pavaromis, kad sumažintų degalų naudojimą ir išmetamųjų teršalų kiekį;","17.14. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – netinkamai naudojasi pavaromis, kad sumažintų degalų naudojimą ir išmetamųjų teršalų kiekį;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "17.15. SPK – šešios pasikartojančios pavaros pasirinkimo NK.","17.15. SPK – šešios pasikartojančios pavaros pasirinkimo NK.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18. Vairo valdymas:","18. Vairo valdymas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.1. NK – neišlaiko pasirinktos trajektorijos: nukrypsta nuo trajektorijos, trūkčiodamas sukinėja vairą; yra neatidus netinkamai vairuojamųjų ratų padėčiai, palikdamas stovėti transporto priemonę ar pradėdamas važiuoti;","18.1. NK – neišlaiko pasirinktos trajektorijos: nukrypsta nuo trajektorijos, trūkčiodamas sukinėja vairą; yra neatidus netinkamai vairuojamųjų ratų padėčiai, palikdamas stovėti transporto priemonę ar pradėdamas važiuoti;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.2. NK – bent vienu ratu įvažiuoja į kelkraštį arba atsiremia į bortelį ar šaligatvio kraštą, bet neužvažiuoja ant jo. Ši NK nevertinama, jei transporto priemonės ratas saugiai ir sklandžiai įvažiuoja į kelkraštį arba paliečia bortelį, ar šaligatvio kraštą, atliekant specialųjį važiavimo manevrą;","18.2. NK – bent vienu ratu įvažiuoja į kelkraštį arba atsiremia į bortelį ar šaligatvio kraštą, bet neužvažiuoja ant jo. Ši NK nevertinama, jei transporto priemonės ratas saugiai ir sklandžiai įvažiuoja į kelkraštį arba paliečia bortelį, ar šaligatvio kraštą, atliekant specialųjį važiavimo manevrą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.3. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – suka vairą netinkamu būdu:","18.3. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – suka vairą netinkamu būdu:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.3.1. deda rankas ant vairo iš vidaus;","18.3.1. deda rankas ant vairo iš vidaus;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.3.2. suka vairą tik viena ranka, išskyrus važiavimą atbuline eiga ir transporto priemonės valdymo įtaisų naudojimą;","18.3.2. suka vairą tik viena ranka, išskyrus važiavimą atbuline eiga ir transporto priemonės valdymo įtaisų naudojimą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.3.3. suka vairą vienos rankos delnu;","18.3.3. suka vairą vienos rankos delnu;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.3.4. per daug suka vairą, kai transporto priemonė nejuda;","18.3.4. per daug suka vairą, kai transporto priemonė nejuda;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.3.5. laiko vairą sukryžiavęs rankas ar kitokiu būdu, kuris riboja judesius vairu.","18.3.5. laiko vairą sukryžiavęs rankas ar kitokiu būdu, kuris riboja judesius vairu.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "18.4. SPK (visos kategorijos, išskyrus A1, A2 ir A) – šešios pasikartojančios 18.1 arba 18.2 papunkčiuose nurodytos vairo valdymo NK.","18.4. SPK (visos kategorijos, išskyrus A1, A2 ir A) – šešios pasikartojančios 18.1 arba 18.2 papunkčiuose nurodytos vairo valdymo NK.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19. Stabdymas:","19. Stabdymas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.1. NK – netinkamai stabdydamas leidžia transporto priemonei pajudėti į priekį;","19.1. NK – netinkamai stabdydamas leidžia transporto priemonei pajudėti į priekį;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.2. NK – neišlaiko transporto priemonės vietoje nei stovėjimo, nei darbiniu stabdžiu, kai ji stovi. Ši NK nevertinama, kai koja perkeliama nuo stabdžių ant akceleratoriaus paminos;","19.2. NK – neišlaiko transporto priemonės vietoje nei stovėjimo, nei darbiniu stabdžiu, kai ji stovi. Ši NK nevertinama, kai koja perkeliama nuo stabdžių ant akceleratoriaus paminos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.3. NK – valdo stabdžių paminą netinkamu būdu;","19.3. NK – valdo stabdžių paminą netinkamu būdu;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.4. NK – neišlaiko vietoje transporto priemonės, sustabdęs įkalnėje, t. y. leidžia jai pariedėti atgal, bet greitai pasitaiso ir nesudaro potencialiai pavojingos situacijos;","19.4. NK – neišlaiko vietoje transporto priemonės, sustabdęs įkalnėje, t. y. leidžia jai pariedėti atgal, bet greitai pasitaiso ir nesudaro potencialiai pavojingos situacijos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.5. NK – (visos kategorijos, išskyrus A1, A2, A) blokuoja ratus (taip pat atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą), bet greitai pasitaiso;","19.5. NK – (visos kategorijos, išskyrus A1, A2, A) blokuoja ratus (taip pat atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą), bet greitai pasitaiso;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.6. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – stipriai spaudžia darbinio stabdžio paminą važiuodamas posūkiu;","19.6. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – stipriai spaudžia darbinio stabdžio paminą važiuodamas posūkiu;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.7. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – važiuoja nevisiškai išjungęs stovėjimo stabdį arba naudoja jį be reikalo, bet tai netrukdo transporto priemonei judėti;","19.7. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – važiuoja nevisiškai išjungęs stovėjimo stabdį arba naudoja jį be reikalo, bet tai netrukdo transporto priemonei judėti;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.8. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – stabdo kaire koja;","19.8. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – stabdo kaire koja;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.9. NK (A1, A2 ir A kategorijos) – nenaudoja užpakalinio rato stabdžio lėtindamas greitį ar sustodamas;","19.9. NK (A1, A2 ir A kategorijos) – nenaudoja užpakalinio rato stabdžio lėtindamas greitį ar sustodamas;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.10. NK (A1, A2 ir A kategorijos) – blokuoja užpakalinį ratą (taip pat atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą), bet greitai pasitaiso;","19.10. NK (A1, A2 ir A kategorijos) – blokuoja užpakalinį ratą (taip pat atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą), bet greitai pasitaiso;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "19.11. NK (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos) – netinkamai naudoja stabdžius važiuodamas posūkiu, apvažiuodamas nukreipiamuosius kūgius.","19.11. NK (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos) – netinkamai naudoja stabdžius važiuodamas posūkiu, apvažiuodamas nukreipiamuosius kūgius.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "20. Padėties pasirinkimas važiuojamojoje dalyje:","20. Padėties pasirinkimas važiuojamojoje dalyje:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "20.1. NK – nesilaiko eismo juostos ribų, išskyrus atvejus, kai egzaminuojamasis privalo keisti padėtį važiuojamojoje dalyje dėl susidariusios eismo situacijos;","20.1. NK – nesilaiko eismo juostos ribų, išskyrus atvejus, kai egzaminuojamasis privalo keisti padėtį važiuojamojoje dalyje dėl susidariusios eismo situacijos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "20.2. NK – važiuojamojoje dalyje užima tokią padėtį, kuri neatitinka eismo situacijos, t. y. nemato kelio priekyje ir nekoreguoja pasirinktos trajektorijos, taip pat nepersirikiuoja arba be reikalo persirikiuoja iš vienos eismo juostos į kitą ir pan.;","20.2. NK – važiuojamojoje dalyje užima tokią padėtį, kuri neatitinka eismo situacijos, t. y. nemato kelio priekyje ir nekoreguoja pasirinktos trajektorijos, taip pat nepersirikiuoja arba be reikalo persirikiuoja iš vienos eismo juostos į kitą ir pan.;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "20.3. NK – nepasitraukia dešiniau, kur tai įmanoma, važiuodamas keliu, kuriame yra po vieną kiekvienos krypties eismo juostą. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;","20.3. NK – nepasitraukia dešiniau, kur tai įmanoma, važiuodamas keliu, kuriame yra po vieną kiekvienos krypties eismo juostą. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "20.4. NK – be reikalo važiuoja kairiąja eismo juosta. Ši NK nevertinama, jei važiuojant dešiniąja juosta reikia dažnai persirikiuoti dėl joje stovinčių transporto priemonių arba jei reikia važiuoti tiesiai, o iš dešiniosios juostos sankryžoje galima sukti tik į dešinę, arba eismas kitoje juostoje intensyvus;","20.4. NK – be reikalo važiuoja kairiąja eismo juosta. Ši NK nevertinama, jei važiuojant dešiniąja juosta reikia dažnai persirikiuoti dėl joje stovinčių transporto priemonių arba jei reikia važiuoti tiesiai, o iš dešiniosios juostos sankryžoje galima sukti tik į dešinę, arba eismas kitoje juostoje intensyvus;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "20.5. NK – sustabdo transporto priemonę netinkamoje vietoje ( neprivažiavęs „Stop“ linijos, važiuojamųjų dalių sankirtos ir pan.);","20.5. NK – sustabdo transporto priemonę netinkamoje vietoje ( neprivažiavęs „Stop“ linijos, važiuojamųjų dalių sankirtos ir pan.);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "20.6. NK (A1, A2, A kategorija) – važiuoja horizontaliuoju kelių ženklinimu, nešvaria kelio dalimi (tepalo dėmės, smėlis ir pan.), kai to galima išvengti;","20.6. NK (A1, A2, A kategorija) – važiuoja horizontaliuoju kelių ženklinimu, nešvaria kelio dalimi (tepalo dėmės, smėlis ir pan.), kai to galima išvengti;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "20.7. SPK – šešios pasikartojančios padėties pasirinkimo važiuojamojoje dalyje NK.","20.7. SPK – šešios pasikartojančios padėties pasirinkimo važiuojamojoje dalyje NK.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21. Padėties pasirinkimas manevruojant:","21. Padėties pasirinkimas manevruojant:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.1. NK (B1 ir B kategorija) – pasirinko netinkamą padėtį, pradėdamas transporto priemonės pastatymo į laikiną stovėjimo vietą lygiagrečiai, įstrižai arba statmenai važiuojamosios dalies kraštui manevrą, t.y. arčiau kaip 50 cm arba toliau kaip 1 m atstumu iš šono nuo stovinčios transporto priemonės;","21.1. NK (B1 ir B kategorija) – pasirinko netinkamą padėtį, pradėdamas transporto priemonės pastatymo į laikiną stovėjimo vietą lygiagrečiai, įstrižai arba statmenai važiuojamosios dalies kraštui manevrą, t.y. arčiau kaip 50 cm arba toliau kaip 1 m atstumu iš šono nuo stovinčios transporto priemonės;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.2. NK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą lygiagrečiai važiuojamosios dalies kraštui manevrą:","21.2. NK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą lygiagrečiai važiuojamosios dalies kraštui manevrą:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.2.1. transporto priemonė stovi nelygiagrečiai važiuojamosios dalies (šaligatvio) kraštui;","21.2.1. transporto priemonė stovi nelygiagrečiai važiuojamosios dalies (šaligatvio) kraštui;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.2.2. bent vienas arčiau važiuojamosios dalies (šaligatvio) krašto esantis transporto priemonės ratas yra toliau kaip 40 cm nuo važiuojamosios dalies (šaligatvio) krašto;","21.2.2. bent vienas arčiau važiuojamosios dalies (šaligatvio) krašto esantis transporto priemonės ratas yra toliau kaip 40 cm nuo važiuojamosios dalies (šaligatvio) krašto;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.2.3. transporto priemonė yra arčiau kaip 1 m arba toliau kaip 2 m atstumu nuo priekyje stovinčios transporto priemonės.","21.2.3. transporto priemonė yra arčiau kaip 1 m arba toliau kaip 2 m atstumu nuo priekyje stovinčios transporto priemonės.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.3. NK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą įstrižai arba statmenai važiuojamosios dalies kraštui manevrą transporto priemonė stovi:","21.3. NK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą įstrižai arba statmenai važiuojamosios dalies kraštui manevrą transporto priemonė stovi:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.3.1. nelygiagrečiai greta stovinčiai transporto priemonei ar stovėjimo vietą žyminčioms linijoms;","21.3.1. nelygiagrečiai greta stovinčiai transporto priemonei ar stovėjimo vietą žyminčioms linijoms;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.3.2. arčiau kaip 50 cm arba toliau kaip 1 m atstumu nuo greta stovinčios transporto priemonės;","21.3.2. arčiau kaip 50 cm arba toliau kaip 1 m atstumu nuo greta stovinčios transporto priemonės;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.3.3. toliau kaip 40 cm atstumu nuo stovėjimo vietos pabaigą žyminčios linijos.","21.3.3. toliau kaip 40 cm atstumu nuo stovėjimo vietos pabaigą žyminčios linijos.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.4. NK (B1 ir B kategorija) – atlikdamas transporto priemonės pastatymo lygiagrečiai važiuojamosios dalies kraštui manevrą, atbuline eiga nuvažiavo daugiau kaip 3 m nuo priekyje stovinčios transporto priemonės (tarpas tarp egzaminuojamojo vairuojamos transporto priemonės priekio ir priekyje stovinčios transporto priemonės galo);","21.4. NK (B1 ir B kategorija) – atlikdamas transporto priemonės pastatymo lygiagrečiai važiuojamosios dalies kraštui manevrą, atbuline eiga nuvažiavo daugiau kaip 3 m nuo priekyje stovinčios transporto priemonės (tarpas tarp egzaminuojamojo vairuojamos transporto priemonės priekio ir priekyje stovinčios transporto priemonės galo);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.5. NK (visos kategorijos, išskyrus A1, A2 ir A) – atlikdamas specialiuosius važiavimo manevrus, aprašytus 2 priede, tris ir daugiau kartų naudojo atbulinės eigos pavarą. Ši NK vertinama vieną kartą atliekant kiekvieną specialųjį važiavimo manevrą;","21.5. NK (visos kategorijos, išskyrus A1, A2 ir A) – atlikdamas specialiuosius važiavimo manevrus, aprašytus 2 priede, tris ir daugiau kartų naudojo atbulinės eigos pavarą. Ši NK vertinama vieną kartą atliekant kiekvieną specialųjį važiavimo manevrą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.6. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – sustojo daugiau kaip 1 m atstumu prieš eismo juostos pabaigą, atlikdamas 2 priedo 9.1 papunktyje aprašytą specialųjį važiavimo manevrą;","21.6. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – sustojo daugiau kaip 1 m atstumu prieš eismo juostos pabaigą, atlikdamas 2 priedo 9.1 papunktyje aprašytą specialųjį važiavimo manevrą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.7. NK (B1 ir B kategorijos) – važiuodamas atbuline eiga su posūkiu į dešinę nuvažiavo daugiau kaip 1 m nuo dešiniojo važiuojamosios dalies (šaligatvio) krašto;","21.7. NK (B1 ir B kategorijos) – važiuodamas atbuline eiga su posūkiu į dešinę nuvažiavo daugiau kaip 1 m nuo dešiniojo važiuojamosios dalies (šaligatvio) krašto;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.8. SPK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – šešis ar daugiau kartų sustojo daugiau kaip 1 m prieš eismo juostos pabaigą, atlikdamas 2 priedo 9.1 papunktyje aprašytą specialųjį važiavimo manevrą;","21.8. SPK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – šešis ar daugiau kartų sustojo daugiau kaip 1 m prieš eismo juostos pabaigą, atlikdamas 2 priedo 9.1 papunktyje aprašytą specialųjį važiavimo manevrą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "21.9. NK (D1, D, ir T kategorijos) – pabaigus transporto priemonės pastatymo šonu prie pakylos manevrą, bent vienos keleivių įlaipinimo arba išlaipinimo durys yra toliau kaip 30 cm nuo pakylos krašto.","21.9. NK (D1, D, ir T kategorijos) – pabaigus transporto priemonės pastatymo šonu prie pakylos manevrą, bent vienos keleivių įlaipinimo arba išlaipinimo durys yra toliau kaip 30 cm nuo pakylos krašto.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "22. Padėties pasirinkimas, sukant į dešinę:","22. Padėties pasirinkimas, sukant į dešinę:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "22.1. NK – prieš posūkį transporto priemonė yra per toli nuo dešiniojo važiuojamosios dalies krašto;","22.1. NK – prieš posūkį transporto priemonė yra per toli nuo dešiniojo važiuojamosios dalies krašto;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "22.2. NK – pradėjus sukti, transporto priemonė yra per toli nuo dešiniojo važiuojamosios dalies krašto, sukama per plačiai ir posūkis užbaigiamas per arti priešingos krypties eismo juostos;","22.2. NK – pradėjus sukti, transporto priemonė yra per toli nuo dešiniojo važiuojamosios dalies krašto, sukama per plačiai ir posūkis užbaigiamas per arti priešingos krypties eismo juostos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "22.3. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – baigdamas posūkį į dešinę bet kuria transporto priemonės dalimi be reikalo įvažiuoja į priešingos krypties eismo juostą.","22.3. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – baigdamas posūkį į dešinę bet kuria transporto priemonės dalimi be reikalo įvažiuoja į priešingos krypties eismo juostą.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "23. Padėties pasirinkimas, sukant į kairę:","23. Padėties pasirinkimas, sukant į kairę:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "23.1. NK – prieš posūkį (apsisukimą) transporto priemonė yra ne eismo juostoje, nelygiagreti ženklinimui, toliau nei būtina nuo priešingų krypčių eismo srautus skiriančios (pažymėtos ar nepažymėtos) linijos arba skiriamosios juostos;","23.1. NK – prieš posūkį (apsisukimą) transporto priemonė yra ne eismo juostoje, nelygiagreti ženklinimui, toliau nei būtina nuo priešingų krypčių eismo srautus skiriančios (pažymėtos ar nepažymėtos) linijos arba skiriamosios juostos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "23.2. NK – suka būdamas per toli nuo kairiojo važiuojamosios dalies krašto vienpusio eismo kelyje;","23.2. NK – suka būdamas per toli nuo kairiojo važiuojamosios dalies krašto vienpusio eismo kelyje;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "23.3. NK – pasuka priekinius ratus į kairę laukdamas posūkio, išskyrus situacijas, kai tai yra būtina;","23.3. NK – pasuka priekinius ratus į kairę laukdamas posūkio, išskyrus situacijas, kai tai yra būtina;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "23.4. NK – suka plačiai, važiuoja per daug į dešinę nuo važiuojamųjų dalių sankirtos centro, kur eismo sąlygos to nereikalauja;","23.4. NK – suka plačiai, važiuoja per daug į dešinę nuo važiuojamųjų dalių sankirtos centro, kur eismo sąlygos to nereikalauja;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "23.5. NK (visos kategorijos, išskyrus A1, A2 ir A) – įvažiuodamas į važiuojamųjų dalių sankirtą arba išvažiuodamas iš jos nežymiai išvažiuoja į priešingos krypties eismo juostą, bet nesudaro potencialiai pavojingos situacijos.","23.5. NK (visos kategorijos, išskyrus A1, A2 ir A) – įvažiuodamas į važiuojamųjų dalių sankirtą arba išvažiuodamas iš jos nežymiai išvažiuoja į priešingos krypties eismo juostą, bet nesudaro potencialiai pavojingos situacijos.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "24. Situacijos stebėjimas ir žvalgymasis:","24. Situacijos stebėjimas ir žvalgymasis:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "24.1. NK – nesižvalgo ir nestebi viso kelio priekyje, iš šono ir už transporto priemonės;","24.1. NK – nesižvalgo ir nestebi viso kelio priekyje, iš šono ir už transporto priemonės;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "24.2. NK – pasukdamas galvą per atitinkamą petį netikrina per užpakalinio vaizdo veidrodžius nematomos kelio dalies kiekvieną kartą apvažiuodamas kliūtį, persirikiuodamas į kitą eismo juostą arba kelių išsiskyrimo ar jungimosi vietoje. Ši NK ne visada gali būti vertinama, jei egzaminuojamasis vairuoja C, CE, D ar DE kategorijos transporto priemonę;","24.2. NK – pasukdamas galvą per atitinkamą petį netikrina per užpakalinio vaizdo veidrodžius nematomos kelio dalies kiekvieną kartą apvažiuodamas kliūtį, persirikiuodamas į kitą eismo juostą arba kelių išsiskyrimo ar jungimosi vietoje. Ši NK ne visada gali būti vertinama, jei egzaminuojamasis vairuoja C, CE, D ar DE kategorijos transporto priemonę;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "24.3. NK – nepasižiūri atgal prieš pradėdamas važiuoti atbuline eiga. Ši NK taip pat vertinama, kai egzaminuojamasis vairuoja B1 arba B kategorijos transporto priemonę ir pradėdamas važiuoti atbuline eiga pasižiūri atgal tik per užpakalinio vaizdo veidrodžius;","24.3. NK – nepasižiūri atgal prieš pradėdamas važiuoti atbuline eiga. Ši NK taip pat vertinama, kai egzaminuojamasis vairuoja B1 arba B kategorijos transporto priemonę ir pradėdamas važiuoti atbuline eiga pasižiūri atgal tik per užpakalinio vaizdo veidrodžius;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "24.4. NK – per ilgai atitraukia dėmesį nuo važiavimo krypties, bet nesudaro potencialiai pavojingos situacijos;","24.4. NK – per ilgai atitraukia dėmesį nuo važiavimo krypties, bet nesudaro potencialiai pavojingos situacijos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "24.5. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – atlikdamas 2 priedo 5 arba 9.2.3 papunktyje aprašytus specialiuosius važiavimo manevrus išlipo apsižvalgyti daugiau kaip vieną kartą;","24.5. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – atlikdamas 2 priedo 5 arba 9.2.3 papunktyje aprašytus specialiuosius važiavimo manevrus išlipo apsižvalgyti daugiau kaip vieną kartą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "24.6. SPK – šešios situacijos ir daugiau, kai reikia patikrinti per užpakalinio vaizdo veidrodžius nematomą kelio dalį, bet per petį nepasižiūrima.","24.6. SPK – šešios situacijos ir daugiau, kai reikia patikrinti per užpakalinio vaizdo veidrodžius nematomą kelio dalį, bet per petį nepasižiūrima.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "25. Veidrodžių naudojimas:","25. Veidrodžių naudojimas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "25.1. NK – nežiūri į užpakalinio vaizdo veidrodžius prieš sulėtindamas, sustabdydamas transporto priemonę, įvažiuodamas į posūkį, persirikiuodamas, išvažiuodamas iš kelio ar įvažiuodamas į kelią, artėdamas prie bet kokios kliūties;","25.1. NK – nežiūri į užpakalinio vaizdo veidrodžius prieš sulėtindamas, sustabdydamas transporto priemonę, įvažiuodamas į posūkį, persirikiuodamas, išvažiuodamas iš kelio ar įvažiuodamas į kelią, artėdamas prie bet kokios kliūties;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "25.2. NK – per ilgai (pagal situaciją) žiūri į užpakalinio vaizdo veidrodį;","25.2. NK – per ilgai (pagal situaciją) žiūri į užpakalinio vaizdo veidrodį;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "25.3. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – nesinaudoja išoriniais užpakalinio vaizdo veidrodžiais, kai žiūri atgal važiavimo atbuline eiga manevro metu, t. y. žiūri per langą, kad galėtų valdyti transporto priemonę. Atsitiktiniai žvilgsniai leistini.","25.3. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – nesinaudoja išoriniais užpakalinio vaizdo veidrodžiais, kai žiūri atgal važiavimo atbuline eiga manevro metu, t. y. žiūri per langą, kad galėtų valdyti transporto priemonę. Atsitiktiniai žvilgsniai leistini.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "26. Įspėjamųjų signalų naudojimas:","26. Įspėjamųjų signalų naudojimas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "26.1. NK – tinkamai neparodo posūkio signalo, kad įspėtų apie savo ketinimus, kai tai būtina. Ši NK nevertinama, kai dėl tam tikrų aplinkybių (mažas atstumas tarp artimiausio kertamo kelio arba įvažiavimo į šalia kelio esančią teritoriją ir numatyto posūkio vietos ir pan.) tai daryti netikslinga;","26.1. NK – tinkamai neparodo posūkio signalo, kad įspėtų apie savo ketinimus, kai tai būtina. Ši NK nevertinama, kai dėl tam tikrų aplinkybių (mažas atstumas tarp artimiausio kertamo kelio arba įvažiavimo į šalia kelio esančią teritoriją ir numatyto posūkio vietos ir pan.) tai daryti netikslinga;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "26.2. NK – nerodo posūkio į dešinę signalo prieš išvažiuodamas iš žiedinės sankryžos;","26.2. NK – nerodo posūkio į dešinę signalo prieš išvažiuodamas iš žiedinės sankryžos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "26.3. NK – rodo klaidingos krypties posūkio signalą. Ši NK vertinama, jei egzaminuojamasis suka į priešingą pusę, nei rodo signalą;","26.3. NK – rodo klaidingos krypties posūkio signalą. Ši NK vertinama, jei egzaminuojamasis suka į priešingą pusę, nei rodo signalą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "26.4. NK – neišjungia posūkio signalo per penkias sekundes po to, kai posūkis ar persirikiavimas buvo baigtas;","26.4. NK – neišjungia posūkio signalo per penkias sekundes po to, kai posūkis ar persirikiavimas buvo baigtas;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "26.5. NK – išjungia posūkio signalą per anksti, t. y. anksčiau nei posūkis ar persirikiavimas pradėtas ar baigtas, ir daugiau jo nebeįjungia;","26.5. NK – išjungia posūkio signalą per anksti, t. y. anksčiau nei posūkis ar persirikiavimas pradėtas ar baigtas, ir daugiau jo nebeįjungia;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "26.6. NK – rodo posūkio signalą be reikalo ir gali suklaidinti kitus eismo dalyvius;","26.6. NK – rodo posūkio signalą be reikalo ir gali suklaidinti kitus eismo dalyvius;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "26.7. SPK – šešios pasikartojančios įspėjamųjų signalų naudojimo NK.","26.7. SPK – šešios pasikartojančios įspėjamųjų signalų naudojimo NK.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "27. Eismo pavojų įvertinimas","27. Eismo pavojų įvertinimas",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "NK – netinkamai ar nepakankamai greitai reaguoja į eismo pavojų.","NK – netinkamai ar nepakankamai greitai reaguoja į eismo pavojų.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "28. Sprendimų priėmimas:","28. Sprendimų priėmimas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "28.1. NK – tinkamai neįvertina greičio ir atstumo iki kitų transporto priemonių pradėdamas važiuoti, važiuodamas per sankryžą, persirikiuodamas iš vienos eismo juostos į kitą, įvažiuodamas į kelią, prieš pradėdamas atlikti arba atlikdamas specialųjį važiavimo manevrą, aprašytą 2 priede;","28.1. NK – tinkamai neįvertina greičio ir atstumo iki kitų transporto priemonių pradėdamas važiuoti, važiuodamas per sankryžą, persirikiuodamas iš vienos eismo juostos į kitą, įvažiuodamas į kelią, prieš pradėdamas atlikti arba atlikdamas specialųjį važiavimo manevrą, aprašytą 2 priede;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "28.2. NK – sustoja tinkamai, bet esant saugiam tarpui transporto sraute nesiryžta važiuoti;","28.2. NK – sustoja tinkamai, bet esant saugiam tarpui transporto sraute nesiryžta važiuoti;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "28.3. NK – be reikalo sustoja (delsia) ir nevažiuoja toliau, kai nėra transporto priemonių ar pėsčiųjų, kuriems galėtų trukdyti, o kelias yra laisvas;","28.3. NK – be reikalo sustoja (delsia) ir nevažiuoja toliau, kai nėra transporto priemonių ar pėsčiųjų, kuriems galėtų trukdyti, o kelias yra laisvas;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "28.4. NK – be reikalo duoda kelią kitam eismo dalyviui, t. y. netinkamai taiko važiavimo pirmumo taisykles.","28.4. NK – be reikalo duoda kelią kitam eismo dalyviui, t. y. netinkamai taiko važiavimo pirmumo taisykles.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "29. Saugaus atstumo pasirinkimas:","29. Saugaus atstumo pasirinkimas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "29.1. NK – laikosi mažesnio kaip per dvi sekundes, bet didesnio kaip per vieną sekundę nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės;","29.1. NK – laikosi mažesnio kaip per dvi sekundes, bet didesnio kaip per vieną sekundę nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "29.2. NK – laikosi mažesnio kaip per tris sekundes, bet didesnio kaip per dvi sekundes nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės kelyje, kuriame yra dvi ar daugiau eismo juostų, skirtų važiuoti viena kryptimi, kai eismo situacija to nereikalauja.","29.2. NK – laikosi mažesnio kaip per tris sekundes, bet didesnio kaip per dvi sekundes nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės kelyje, kuriame yra dvi ar daugiau eismo juostų, skirtų važiuoti viena kryptimi, kai eismo situacija to nereikalauja.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "29.3. NK – nepalankiomis eismo sąlygomis laikosi mažesnio kaip per keturias sekundes, bet didesnio kaip per dvi sekundes nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės. Egzaminuotojas privalo įvertinti papildomus (didesnius) atstumus, kurių turi laikytis C1, C, C1E, CE, D1, D, D1E, DE ir T kategorijų transporto priemonių vairuotojai;","29.3. NK – nepalankiomis eismo sąlygomis laikosi mažesnio kaip per keturias sekundes, bet didesnio kaip per dvi sekundes nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės. Egzaminuotojas privalo įvertinti papildomus (didesnius) atstumus, kurių turi laikytis C1, C, C1E, CE, D1, D, D1E, DE ir T kategorijų transporto priemonių vairuotojai;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "29.4. NK – laikosi per mažo šoninio atstumo iki stovinčių, priešpriešiais ar ta pačia kryptimi važiuojančių transporto priemonių, dviratininkų, pėsčiųjų, einančių važiuojamąja kelio dalimi, kliūčių važiuojamosios dalies pakraštyje, kelkraščio arba šaligatvio, tačiau nesudaro potencialiai pavojingos situacijos;","29.4. NK – laikosi per mažo šoninio atstumo iki stovinčių, priešpriešiais ar ta pačia kryptimi važiuojančių transporto priemonių, dviratininkų, pėsčiųjų, einančių važiuojamąja kelio dalimi, kliūčių važiuojamosios dalies pakraštyje, kelkraščio arba šaligatvio, tačiau nesudaro potencialiai pavojingos situacijos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "29.5. NK – sustabdo transporto priemonę, palikdamas nepakankamą tarpą iki priešais sustojusios transporto priemonės, kad prireikus galėtų apvažiuoti priešais sustojusią transporto priemonę, nevažiuodamas atbuline eiga.","29.5. NK – sustabdo transporto priemonę, palikdamas nepakankamą tarpą iki priešais sustojusios transporto priemonės, kad prireikus galėtų apvažiuoti priešais sustojusią transporto priemonę, nevažiuodamas atbuline eiga.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "30. Greičio pasirinkimas:","30. Greičio pasirinkimas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "30.1. NK – įsibėgėja per greitai arba per lėtai, neatsižvelgdamas į eismo sąlygas;","30.1. NK – įsibėgėja per greitai arba per lėtai, neatsižvelgdamas į eismo sąlygas;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "30.2. NK – važiuoja per greitai konkrečioje situacijoje, bet neviršija leistino greičio. Ši NK vertinama, kai egzaminuojamasis važiuoja didesniu nei tinkama greičiu;","30.2. NK – važiuoja per greitai konkrečioje situacijoje, bet neviršija leistino greičio. Ši NK vertinama, kai egzaminuojamasis važiuoja didesniu nei tinkama greičiu;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "30.3. NK – važiuoja per lėtai konkrečioje situacijoje, t. y. 10–15 km/h mažesniu, nei leistina, greičiu, kai eismo sąlygos to nereikalauja. Ši NK taip pat vertinama kai egzaminuojamasis nepasiekia reikiamo greičio, atlikdamas specialiuosius važiavimo manevrus, aprašytus 2 priede;","30.3. NK – važiuoja per lėtai konkrečioje situacijoje, t. y. 10–15 km/h mažesniu, nei leistina, greičiu, kai eismo sąlygos to nereikalauja. Ši NK taip pat vertinama kai egzaminuojamasis nepasiekia reikiamo greičio, atlikdamas specialiuosius važiavimo manevrus, aprašytus 2 priede;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "30.4. NK – važiuoja viršydamas leistiną greitį ne daugiau kaip 10 procentų. Klaida neturi būti vertinama, jei buvo trumpalaikė ir greitai ištaisyta.","30.4. NK – važiuoja viršydamas leistiną greitį ne daugiau kaip 10 procentų. Klaida neturi būti vertinama, jei buvo trumpalaikė ir greitai ištaisyta.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "31. Eismo reguliavimo signalų, kelio ženklų ir kelių ženklinimo reikalavimų vykdymas:","31. Eismo reguliavimo signalų, kelio ženklų ir kelių ženklinimo reikalavimų vykdymas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "31.1. NK – sustabdo transporto priemonę taip, kad ji išsikiša už „Stop“ linijos (ženklo „Stop“ linija);","31.1. NK – sustabdo transporto priemonę taip, kad ji išsikiša už „Stop“ linijos (ženklo „Stop“ linija);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "31.2. NK – ketindamas sankryžoje pasukti į kairę, neįvažiuoja į važiuojamųjų dalių sankirtą laukti saugaus tarpo transporto sraute, kai tai dera daryti;","31.2. NK – ketindamas sankryžoje pasukti į kairę, neįvažiuoja į važiuojamųjų dalių sankirtą laukti saugaus tarpo transporto sraute, kai tai dera daryti;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "31.3. NK – nepakankamai supranta eismo reguliavimo signalus, t. y. per ilgai delsia, nors gali važiuoti nekliudydamas kitiems eismo dalyviams;","31.3. NK – nepakankamai supranta eismo reguliavimo signalus, t. y. per ilgai delsia, nors gali važiuoti nekliudydamas kitiems eismo dalyviams;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "31.4. NK – ketindamas sankryžoje pasukti į kairę, netinkamai įvažiuoja į važiuojamųjų dalių sankirtą paskui pirmąją transporto priemonę. Ši NK vertinama, kai egzaminuojamajam važiuoti leidžia šviesoforo (reguliuotojo) signalas.","31.4. NK – ketindamas sankryžoje pasukti į kairę, netinkamai įvažiuoja į važiuojamųjų dalių sankirtą paskui pirmąją transporto priemonę. Ši NK vertinama, kai egzaminuojamajam važiuoti leidžia šviesoforo (reguliuotojo) signalas.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "32. Nuoseklumas:","32. Nuoseklumas:",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "32.1. NK – pasirenka netinkamą transporto priemonės valdymo sistemos seką;","32.1. NK – pasirenka netinkamą transporto priemonės valdymo sistemos seką;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "32.2. NK (BE, C1E, CE, D1E ir DE kategorijos) – netinkamai atidaro sukabinimo įtaiso kablio arba smaigo užrakto žiotis, besiruošdamas atkabinti arba prikabinti priekabą;","32.2. NK (BE, C1E, CE, D1E ir DE kategorijos) – netinkamai atidaro sukabinimo įtaiso kablio arba smaigo užrakto žiotis, besiruošdamas atkabinti arba prikabinti priekabą;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "32.3. NK (BE, C1E, CE, D1E ir DE kategorijos) – nesaugiai įtraukia priekabos atramas, bet nesudaro potencialiai pavojingos situacijos;","32.3. NK (BE, C1E, CE, D1E ir DE kategorijos) – nesaugiai įtraukia priekabos atramas, bet nesudaro potencialiai pavojingos situacijos;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "32.4. NK (BE, C1E, CE, D1E ir DE kategorijos) – prikabinęs priekabą nepatikrina, ar veikia visi priekabos žibintai.","32.4. NK (BE, C1E, CE, D1E ir DE kategorijos) – prikabinęs priekabą nepatikrina, ar veikia visi priekabos žibintai.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "33. Pusiausvyra ir motociklo valdymas (A1, A2 ir A kategorijos):","33. Pusiausvyra ir motociklo valdymas (A1, A2 ir A kategorijos):",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "33.1. NK – neišlaiko pusiausvyros arba nesuvaldo motociklo ir (arba) važiuodamas koja atsiremia į žemę;","33.1. NK – neišlaiko pusiausvyros arba nesuvaldo motociklo ir (arba) važiuodamas koja atsiremia į žemę;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "33.2. NK – neišlaiko lėtai einančio pėsčiojo greičio, atlikdamas specialiuosius važiavimo manevrus mažu greičiu;","33.2. NK – neišlaiko lėtai einančio pėsčiojo greičio, atlikdamas specialiuosius važiavimo manevrus mažu greičiu;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "33.3. NK – nepasvyra motociklu taip, kad saugiai važiuotų posūkyje, atliktų specialiuosius važiavimo manevrus, aprašytus 2 priede;","33.3. NK – nepasvyra motociklu taip, kad saugiai važiuotų posūkyje, atliktų specialiuosius važiavimo manevrus, aprašytus 2 priede;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "33.4. SPK – daugiau kaip penkis kartus neišlaiko pusiausvyros arba nesuvaldo motociklo.","33.4. SPK – daugiau kaip penkis kartus neišlaiko pusiausvyros arba nesuvaldo motociklo.",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "34. Sėdėsena (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos):","34. Sėdėsena (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos):",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "34.1. NK – važiuoja kojų pirštais atsirėmęs į paminas;","34.1. NK – važiuoja kojų pirštais atsirėmęs į paminas;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "34.2. NK – važiuoja keliais neliesdamas degalų bako;","34.2. NK – važiuoja keliais neliesdamas degalų bako;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "34.3. NK – važiuoja laikydamas galvą netinkamoje padėtyje;","34.3. NK – važiuoja laikydamas galvą netinkamoje padėtyje;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "34.4. NK – važiuoja sėdėdamas (atsitraukia) per toli nuo degalų bako;","34.4. NK – važiuoja sėdėdamas (atsitraukia) per toli nuo degalų bako;",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "34.5. NK – važiuoja slidinėdamas iš vienos pusės į kitą per sėdynę (balną);","34.5. NK – važiuoja slidinėdamas iš vienos pusės į kitą per sėdynę (balną);",""));
//        Mistakes.add(new com.multiselect.UserModel(false, "34.6. NK – važiuoja bet kurią ranką atitraukęs nuo vairo be tinkamos priežasties, bet neprarasdamas pusiausvyros ir valdymo.","34.6. NK – važiuoja bet kurią ranką atitraukęs nuo vairo be tinkamos priežasties, bet neprarasdamas pusiausvyros ir valdymo.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "35. Papildomi valdymo ir saugos įtaisai:","35. Papildomi valdymo ir saugos įtaisai:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "35.1. KK – neužsisega saugos diržo, kai tai yra būtina;","35.1. KK – neužsisega saugos diržo, kai tai yra būtina;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "35.2. KK – naudojasi mobiliuoju telefonu laikydamas jį rankoje tuo metu, kai transporto priemonė juda arba stovi, bet variklis neišjungtas;","35.2. KK – naudojasi mobiliuoju telefonu laikydamas jį rankoje tuo metu, kai transporto priemonė juda arba stovi, bet variklis neišjungtas;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "35.3. KK – naudojasi bet kuriuo papildomu įtaisu netinkamai arba nesinaudoja juo, kai tai yra būtina, ir sudaro potencialiai pavojingą situaciją;","35.3. KK – naudojasi bet kuriuo papildomu įtaisu netinkamai arba nesinaudoja juo, kai tai yra būtina, ir sudaro potencialiai pavojingą situaciją;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "35.4. KK (A1, A2 ir A kategorijos) – važiuoja su nuleista atramine šonine kojele;","35.4. KK (A1, A2 ir A kategorijos) – važiuoja su nuleista atramine šonine kojele;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "35.5. KK (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos) – netinkamai pasirenka arba naudoja motociklininkui reikalingą aprangą.","35.5. KK (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos) – netinkamai pasirenka arba naudoja motociklininkui reikalingą aprangą.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "36. Sankabos valdymas:","36. Sankabos valdymas:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "36.1. KK (A1, A2 ir A kategorijos) – pakelia priekinį ratą, nes blogai valdo sankabą arba per greitai įsibėgėja;","36.1. KK (A1, A2 ir A kategorijos) – pakelia priekinį ratą, nes blogai valdo sankabą arba per greitai įsibėgėja;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "36.2. KK – užgesina variklį ir sudaro potencialiai pavojingą situaciją.","36.2. KK – užgesina variklį ir sudaro potencialiai pavojingą situaciją.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "37. Pavaros pasirinkimas:","37. Pavaros pasirinkimas:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "37.1. KK (visos kategorijos, išskyrus A1, A2 ir A) – jungia atbulinės eigos pavarą, kai važiuoja į priekį, arba važiavimo į priekį pavarą, kai važiuoja atbuline eiga, arba transporto priemonės su automatine pavarų dėže stovėjimo („Park“ pozicija) pavarą, kai transporto priemonė juda;","37.1. KK (visos kategorijos, išskyrus A1, A2 ir A) – jungia atbulinės eigos pavarą, kai važiuoja į priekį, arba važiavimo į priekį pavarą, kai važiuoja atbuline eiga, arba transporto priemonės su automatine pavarų dėže stovėjimo („Park“ pozicija) pavarą, kai transporto priemonė juda;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "37.2. KK – pasirenka netinkamą pavarą ir sudaro potencialiai pavojingą situaciją;","37.2. KK – pasirenka netinkamą pavarą ir sudaro potencialiai pavojingą situaciją;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "37.3. KK – nepasirenka pavaros, kuri leidžia palikti transporto priemonę stovėti saugiai, kai neįjungtas stovėjimo stabdys.","37.3. KK – nepasirenka pavaros, kuri leidžia palikti transporto priemonę stovėti saugiai, kai neįjungtas stovėjimo stabdys.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "38. Vairo valdymas (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas):","38. Vairo valdymas (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas):",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "38.1. KK – nesuvaldo (neišlaiko) vairo;","38.1. KK – nesuvaldo (neišlaiko) vairo;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "38.2. KK – atitraukia abi rankas nuo vairo, kai transporto priemonė juda. Klaida nevertinama, jei rankos atitraukiamos trumpam (sekundei), nesukeliant potencialiai pavojingos situacijos, pavyzdžiui, kai manevruojant judama labai lėtai.","38.2. KK – atitraukia abi rankas nuo vairo, kai transporto priemonė juda. Klaida nevertinama, jei rankos atitraukiamos trumpam (sekundei), nesukeliant potencialiai pavojingos situacijos, pavyzdžiui, kai manevruojant judama labai lėtai.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39. Stabdymas:","39. Stabdymas:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.1. KK – be reikalo staigiai stabdo ir sudaro potencialiai pavojingą situaciją;","39.1. KK – be reikalo staigiai stabdo ir sudaro potencialiai pavojingą situaciją;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.2. KK – neišlaiko transporto priemonės vietoje, sustabdęs ją įkalnėje, t. y. leidžia transporto priemonei pariedėti atgal, ir greitai nepasitaiso (sudaro potencialiai pavojingą situaciją);","39.2. KK – neišlaiko transporto priemonės vietoje, sustabdęs ją įkalnėje, t. y. leidžia transporto priemonei pariedėti atgal, ir greitai nepasitaiso (sudaro potencialiai pavojingą situaciją);",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.3. KK (visos kategorijos, išskyrus A1, A2, ir A) – stipriai spaudžia stabdžių paminą, taip blokuodamas transporto priemonės ratus bei priversdamas juos slysti, ir greitai nepasitaiso;","39.3. KK (visos kategorijos, išskyrus A1, A2, ir A) – stipriai spaudžia stabdžių paminą, taip blokuodamas transporto priemonės ratus bei priversdamas juos slysti, ir greitai nepasitaiso;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.4. KK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – be reikalo važiuoja laikydamas koją ant stabdžių paminos. Ši KK vertinama tik po dviejų egzaminuotojo įspėjimų: „Prašau patikrinti valdymo įtaisus ir įrangą“. Turi būti skirta pakankamai laiko egzaminuojamajam pasitaisyti po kiekvieno įspėjimo;","39.4. KK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – be reikalo važiuoja laikydamas koją ant stabdžių paminos. Ši KK vertinama tik po dviejų egzaminuotojo įspėjimų: „Prašau patikrinti valdymo įtaisus ir įrangą“. Turi būti skirta pakankamai laiko egzaminuojamajam pasitaisyti po kiekvieno įspėjimo;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.5. KK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – įjungia stovėjimo stabdį važiuodamas ir nesuvaldo transporto priemonės;","39.5. KK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – įjungia stovėjimo stabdį važiuodamas ir nesuvaldo transporto priemonės;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.6. KK (visos kategorijos, išskyrus A1, A2 ir A) – pradeda važiuoti, palikęs įjungtą stovėjimo stabdį ir greitai nepasitaiso, t. y. transporto priemonės ratai stabdomi arba neįmanoma pajudėti iš vietos. Ši KK vertinama tik po dviejų egzaminuotojo įspėjimų: „Prašau patikrinti valdymo įtaisus ir įrangą“. Turi būti skirta pakankamai laiko egzaminuojamajam pasitaisyti po kiekvieno įspėjimo;","39.6. KK (visos kategorijos, išskyrus A1, A2 ir A) – pradeda važiuoti, palikęs įjungtą stovėjimo stabdį ir greitai nepasitaiso, t. y. transporto priemonės ratai stabdomi arba neįmanoma pajudėti iš vietos. Ši KK vertinama tik po dviejų egzaminuotojo įspėjimų: „Prašau patikrinti valdymo įtaisus ir įrangą“. Turi būti skirta pakankamai laiko egzaminuojamajam pasitaisyti po kiekvieno įspėjimo;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.7. KK (A1, A2 ir A kategorijos) – blokuoja priekinį ratą (taip pat ir atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą);","39.7. KK (A1, A2 ir A kategorijos) – blokuoja priekinį ratą (taip pat ir atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą);",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.8. KK (A1, A2 ir A kategorijos) – blokuoja užpakalinį ratą (taip pat ir atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą) ir greitai nepasitaiso;","39.8. KK (A1, A2 ir A kategorijos) – blokuoja užpakalinį ratą (taip pat ir atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą) ir greitai nepasitaiso;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.9. KK (A1, A2 ir A kategorijos) – nuolat spaudžia stabdžių paminą arba svirtį (stabdžių signalas veikia gana ilgą laiką), kai transporto priemonė juda. Vertindamas šią KK, egzaminuotojas privalo būti tikras, kad klaida įvyko ne dėl stabdžių signalo jungiklio gedimo. Ši KK nevertinama, kai egzaminuojamasis lėtai atlieka specialiuosius važiavimo siaura juosta, aštuoniuke arba gyvatėle manevrus;","39.9. KK (A1, A2 ir A kategorijos) – nuolat spaudžia stabdžių paminą arba svirtį (stabdžių signalas veikia gana ilgą laiką), kai transporto priemonė juda. Vertindamas šią KK, egzaminuotojas privalo būti tikras, kad klaida įvyko ne dėl stabdžių signalo jungiklio gedimo. Ši KK nevertinama, kai egzaminuojamasis lėtai atlieka specialiuosius važiavimo siaura juosta, aštuoniuke arba gyvatėle manevrus;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "39.10. KK – palikdamas transporto priemonę, neįjungia stovėjimo stabdžio, kai neįjungta pavara, kuri leidžia palikti transporto priemonę stovėti saugiai.","39.10. KK – palikdamas transporto priemonę, neįjungia stovėjimo stabdžio, kai neįjungta pavara, kuri leidžia palikti transporto priemonę stovėti saugiai.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "40. Padėties pasirinkimas važiuojamojoje dalyje:","40. Padėties pasirinkimas važiuojamojoje dalyje:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "40.1. KK – pasirenka netinkamą trajektoriją arba persirikiuoja važiuojamųjų dalių sankirtoje ir sudaro potencialiai pavojingą situaciją;","40.1. KK – pasirenka netinkamą trajektoriją arba persirikiuoja važiuojamųjų dalių sankirtoje ir sudaro potencialiai pavojingą situaciją;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "40.2. KK – netinkamai lenkia arba apvažiuoja transporto priemonę;","40.2. KK – netinkamai lenkia arba apvažiuoja transporto priemonę;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "40.3. KK – sustoja, kur sustoti draudžiama;","40.3. KK – sustoja, kur sustoti draudžiama;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "40.4. KK (visos kategorijos, išskyrus A1, A2, A) – važiuoja taip, kad trečdalis transporto priemonės ar daugiau be reikalo yra priešpriešinio eismo juostoje;","40.4. KK (visos kategorijos, išskyrus A1, A2, A) – važiuoja taip, kad trečdalis transporto priemonės ar daugiau be reikalo yra priešpriešinio eismo juostoje;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "40.5. KK (C1, C, C1E ir CE kategorijos) – važiuoja toliau nuo važiuojamosios dalies krašto negu antrąja eismo juosta, išskyrus tuos atvejus, kai reikia sukti į kairę, apsisukti, apvažiuoti kliūtį, sustoti (stovėti) vienpusio eismo kelyje;","40.5. KK (C1, C, C1E ir CE kategorijos) – važiuoja toliau nuo važiuojamosios dalies krašto negu antrąja eismo juosta, išskyrus tuos atvejus, kai reikia sukti į kairę, apsisukti, apvažiuoti kliūtį, sustoti (stovėti) vienpusio eismo kelyje;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "40.6. KK (A1, A2 ir A kategorijos) – važiuoja per arti priešingų krypčių eismo srautus skiriančios linijos (paženklinta arba nepaženklinta važiuojamoji dalis), eismo juostos linijos ar dešiniojo arba kairiojo važiuojamosios dalies krašto ar kelkraščio, sudarydamas potencialiai pavojingą situaciją. Ši KK taip pat vertinama, kai bet kuri motociklininko kūno dalis yra priešpriešinio eismo juostoje.","40.6. KK (A1, A2 ir A kategorijos) – važiuoja per arti priešingų krypčių eismo srautus skiriančios linijos (paženklinta arba nepaženklinta važiuojamoji dalis), eismo juostos linijos ar dešiniojo arba kairiojo važiuojamosios dalies krašto ar kelkraščio, sudarydamas potencialiai pavojingą situaciją. Ši KK taip pat vertinama, kai bet kuri motociklininko kūno dalis yra priešpriešinio eismo juostoje.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "41. Padėties pasirinkimas manevruojant:","41. Padėties pasirinkimas manevruojant:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "41.1. KK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą lygiagrečiai važiuojamosios dalies kraštui manevrą, bent vienas arčiau važiuojamosios dalies (šaligatvio) krašto esantis transporto priemonės ratas yra toliau kaip 80 cm nuo važiuojamosios dalies (šaligatvio) krašto;","41.1. KK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą lygiagrečiai važiuojamosios dalies kraštui manevrą, bent vienas arčiau važiuojamosios dalies (šaligatvio) krašto esantis transporto priemonės ratas yra toliau kaip 80 cm nuo važiuojamosios dalies (šaligatvio) krašto;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "41.2. KK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą įstrižai arba statmenai važiuojamosios dalies kraštui manevrą, egzaminuojamojo vairuojama transporto priemonė stovi arčiau kaip 0,3 m arba toliau kaip 1,5 m atstumu nuo greta stovinčios transporto priemonės;","41.2. KK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą įstrižai arba statmenai važiuojamosios dalies kraštui manevrą, egzaminuojamojo vairuojama transporto priemonė stovi arčiau kaip 0,3 m arba toliau kaip 1,5 m atstumu nuo greta stovinčios transporto priemonės;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "41.3. KK (B1 ir B kategorija) – atlikdamas transporto priemonės pastatymo į laikino stovėjimo vietą lygiagrečiai važiuojamosios dalies kraštui manevrą, nuvažiavo daugiau kaip 5 m nuo priekyje stovinčios transporto priemonės (tarpas tarp egzaminuojamojo vairuojamos transporto priemonės priekio ir priekyje stovinčios transporto priemonės galo);","41.3. KK (B1 ir B kategorija) – atlikdamas transporto priemonės pastatymo į laikino stovėjimo vietą lygiagrečiai važiuojamosios dalies kraštui manevrą, nuvažiavo daugiau kaip 5 m nuo priekyje stovinčios transporto priemonės (tarpas tarp egzaminuojamojo vairuojamos transporto priemonės priekio ir priekyje stovinčios transporto priemonės galo);",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "41.4. KK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – nesugebėjo atlikti specialiojo važiavimo manevro, tris kartus įjungęs atbulinės eigos pavarą;","41.4. KK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – nesugebėjo atlikti specialiojo važiavimo manevro, tris kartus įjungęs atbulinės eigos pavarą;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "41.5. KK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – pabaigus specialųjį pastatymo prie rampos važiavimo manevrą, transporto priemonė stovi daugiau kaip 1 m atstumu prieš rampos imitatorių;","41.5. KK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – pabaigus specialųjį pastatymo prie rampos važiavimo manevrą, transporto priemonė stovi daugiau kaip 1 m atstumu prieš rampos imitatorių;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "41.6. KK (D1, D, D1E ir DE kategorijos) – pabaigus transporto priemonės pastatymo šonu prie pakylos manevrą, bent vienos keleivių įlaipinimo ar išlaipinimo durys yra toliau kaip 50 cm nuo pakylos (šaligatvio) krašto.","41.6. KK (D1, D, D1E ir DE kategorijos) – pabaigus transporto priemonės pastatymo šonu prie pakylos manevrą, bent vienos keleivių įlaipinimo ar išlaipinimo durys yra toliau kaip 50 cm nuo pakylos (šaligatvio) krašto.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "42. Padėties pasirinkimas, sukant į dešinę:","42. Padėties pasirinkimas, sukant į dešinę:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "42.1. KK – suka iš tam neskirtos eismo juostos. Jei egzaminuojamasis ketina sukti į dešinę iš lėtėjimo juostos ir nepersirikiuoja į ją iš anksto, tai vertinama kaip padėties pasirinkimo važiuojamojoje dalyje KK;","42.1. KK – suka iš tam neskirtos eismo juostos. Jei egzaminuojamasis ketina sukti į dešinę iš lėtėjimo juostos ir nepersirikiuoja į ją iš anksto, tai vertinama kaip padėties pasirinkimo važiuojamojoje dalyje KK;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "42.2. KK – užima netinkamą padėtį ir sudaro potencialiai pavojingą situaciją;","42.2. KK – užima netinkamą padėtį ir sudaro potencialiai pavojingą situaciją;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "42.3. KK (visos kategorijos, išskyrus A1, A2 ir A) – suka taip, kad trečdalis transporto priemonės arba daugiau be reikalo išsikiša į priešpriešinio eismo juostą.","42.3. KK (visos kategorijos, išskyrus A1, A2 ir A) – suka taip, kad trečdalis transporto priemonės arba daugiau be reikalo išsikiša į priešpriešinio eismo juostą.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "43. Padėties pasirinkimas, sukant į kairę:","43. Padėties pasirinkimas, sukant į kairę:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "43.1. KK – suka (apsisuka) iš tam neskirtos eismo juostos. Jei egzaminuojamasis ketina sukti į kairę iš lėtėjimo juostos ir nepersirikiuoja į ją iš anksto, tai vertinama kaip padėties pasirinkimo važiuojamojoje dalyje KK;","43.1. KK – suka (apsisuka) iš tam neskirtos eismo juostos. Jei egzaminuojamasis ketina sukti į kairę iš lėtėjimo juostos ir nepersirikiuoja į ją iš anksto, tai vertinama kaip padėties pasirinkimo važiuojamojoje dalyje KK;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "43.2. KK – užima netinkamą padėtį ir sudaro potencialiai pavojingą situaciją;","43.2. KK – užima netinkamą padėtį ir sudaro potencialiai pavojingą situaciją;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "43.3. KK (visos kategorijos, išskyrus A1, A2 ir A) – suka taip, kad trečdalis transporto priemonės arba daugiau įvažiuojant į posūkį arba išvažiuojant iš jo be reikalo išsikiša į priešpriešinio eismo juostą;","43.3. KK (visos kategorijos, išskyrus A1, A2 ir A) – suka taip, kad trečdalis transporto priemonės arba daugiau įvažiuojant į posūkį arba išvažiuojant iš jo be reikalo išsikiša į priešpriešinio eismo juostą;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "43.4. KK (A1, A2 ir A kategorijos) – sukdamas į kairę bet kokia motociklo ar kūno dalimi išsikiša į priešpriešinio eismo juostą ir sudaro potencialiai pavojingą situaciją.","43.4. KK (A1, A2 ir A kategorijos) – sukdamas į kairę bet kokia motociklo ar kūno dalimi išsikiša į priešpriešinio eismo juostą ir sudaro potencialiai pavojingą situaciją.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "44. Situacijos stebėjimas ir žvalgymasis:","44. Situacijos stebėjimas ir žvalgymasis:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "44.1. KK – nesižvalgo ir nestebi eismo situacijos, važiuodamas per geležinkelio pervažą, sankryžą ar įvažiuodamas į kelią iš šalia jo esančių teritorijų;","44.1. KK – nesižvalgo ir nestebi eismo situacijos, važiuodamas per geležinkelio pervažą, sankryžą ar įvažiuodamas į kelią iš šalia jo esančių teritorijų;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "44.2. KK – nesižvalgo ir nestebi eismo situacijos (nei per veidrodžius, nei per petį), persirikiuodamas, ar kitaip keisdamas važiavimo kryptį, kai transporto priemonė pasislenka į šoną per jos plotį ar daugiau;","44.2. KK – nesižvalgo ir nestebi eismo situacijos (nei per veidrodžius, nei per petį), persirikiuodamas, ar kitaip keisdamas važiavimo kryptį, kai transporto priemonė pasislenka į šoną per jos plotį ar daugiau;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "44.3. KK – nukreipia žvilgsnį nuo važiavimo krypties ir sudaro pavojingą arba potencialiai pavojingą situaciją, taip pat ir tikrindamas per užpakalinio vaizdo veidrodžius nematomą kelio dalį;","44.3. KK – nukreipia žvilgsnį nuo važiavimo krypties ir sudaro pavojingą arba potencialiai pavojingą situaciją, taip pat ir tikrindamas per užpakalinio vaizdo veidrodžius nematomą kelio dalį;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "44.4. KK – nesižvalgo ir nestebi aplinkos (nei per veidrodžius, nei per petį) šalia ir už transporto priemonės važiuodamas atbuline eiga.","44.4. KK – nesižvalgo ir nestebi aplinkos (nei per veidrodžius, nei per petį) šalia ir už transporto priemonės važiuodamas atbuline eiga.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "45. Įspėjamųjų signalų naudojimas:","45. Įspėjamųjų signalų naudojimas:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "KK – netinkamai rodo posūkio signalą ir taip sudaro pavojingą situaciją.","KK – netinkamai rodo posūkio signalą ir taip sudaro pavojingą situaciją.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "46. Sprendimų priėmimas:","46. Sprendimų priėmimas:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "46.1. KK – įvažiuoja ir sustabdo transporto priemonę pėsčiųjų perėjoje, važiuojamųjų dalių sankirtoje, kurioje arba už kurios yra kliūtis, arba geležinkelio pervažoje;","46.1. KK – įvažiuoja ir sustabdo transporto priemonę pėsčiųjų perėjoje, važiuojamųjų dalių sankirtoje, kurioje arba už kurios yra kliūtis, arba geležinkelio pervažoje;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "46.2. KK – užsidegus geltonam arba raudonam šviesoforo signalui, neišvažiuoja iš važiuojamųjų dalių sankirtos, kai buvo įvažiavęs į ją ketindamas sukti į kairę. Ši KK vertinama, kai egzaminuojamasis nepasinaudoja saugiu tarpu transporto sraute išvažiuoti iš važiuojamųjų dalių sankirtos;","46.2. KK – užsidegus geltonam arba raudonam šviesoforo signalui, neišvažiuoja iš važiuojamųjų dalių sankirtos, kai buvo įvažiavęs į ją ketindamas sukti į kairę. Ši KK vertinama, kai egzaminuojamasis nepasinaudoja saugiu tarpu transporto sraute išvažiuoti iš važiuojamųjų dalių sankirtos;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "46.3. KK – neduoda kelio (nepraleidžia) transporto priemonėms ar kitiems eismo dalyviams, važiuoja per pėsčiųjų perėją arba pro mokyklinį autobusą neįsitikinęs, kad nėra pėsčiųjų, kuriuos turėtų praleisti. Ši KK nevertinama, jei egzaminuojamasis važiuoja, nes negali duoti kelio dėl kitų eismo dalyvių veiksmų.","46.3. KK – neduoda kelio (nepraleidžia) transporto priemonėms ar kitiems eismo dalyviams, važiuoja per pėsčiųjų perėją arba pro mokyklinį autobusą neįsitikinęs, kad nėra pėsčiųjų, kuriuos turėtų praleisti. Ši KK nevertinama, jei egzaminuojamasis važiuoja, nes negali duoti kelio dėl kitų eismo dalyvių veiksmų.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "47. Saugaus atstumo pasirinkimas:","47. Saugaus atstumo pasirinkimas:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "47.1. KK – laikosi per vieną sekundę (dvi sekundes, jei kelio danga šlapia, apledėjusi, grįsta akmenimis ir pan.) ar mažiau nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės;","47.1. KK – laikosi per vieną sekundę (dvi sekundes, jei kelio danga šlapia, apledėjusi, grįsta akmenimis ir pan.) ar mažiau nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "47.2. KK – laikosi per mažo atstumo iki stovinčių, priešpriešiais ar ta pačia kryptimi važiuojančių transporto priemonių, pėsčiųjų, einančių važiuojamąja dalimi, kliūčių važiuojamosios dalies pakraštyje (pavyzdžiui, važiuodamas 50 km/h greičiu laikosi 0,5 m šoninio atstumo iki apvažiuojamos TP);","47.2. KK – laikosi per mažo atstumo iki stovinčių, priešpriešiais ar ta pačia kryptimi važiuojančių transporto priemonių, pėsčiųjų, einančių važiuojamąja dalimi, kliūčių važiuojamosios dalies pakraštyje (pavyzdžiui, važiuodamas 50 km/h greičiu laikosi 0,5 m šoninio atstumo iki apvažiuojamos TP);",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "47.3. KK – važiuodamas ne didesniu kaip 50 km/h greičiu, laikosi mažesnio kaip 1 m šoninio atstumo arba, važiuodamas didesniu nei 50 km/h greičiu, laikosi mažesnio kaip 1,5 m šoninio atstumo iki dviratininko.","47.3. KK – važiuodamas ne didesniu kaip 50 km/h greičiu, laikosi mažesnio kaip 1 m šoninio atstumo arba, važiuodamas didesniu nei 50 km/h greičiu, laikosi mažesnio kaip 1,5 m šoninio atstumo iki dviratininko.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "48. Greičio pasirinkimas:","48. Greičio pasirinkimas:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "48.1. KK – važiuoja daugiau kaip 10 procentų viršydamas leistiną greitį;","48.1. KK – važiuoja daugiau kaip 10 procentų viršydamas leistiną greitį;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "48.2. KK – važiuoja per lėtai konkrečioje situacijoje, t. y. daugiau kaip 15 km/h mažesniu, nei leistina, greičiu, kai eismo situacija to nereikalauja. Ši KK taip pat vertinama, kai egzaminuojamasis važiuoja per lėtai, atlikdamas specialiuosius važiavimo manevrus, aprašytus 2 priede. Ši KK nevertinama, jei egzaminuojamasis saugiai greitėja su transporto srautu;","48.2. KK – važiuoja per lėtai konkrečioje situacijoje, t. y. daugiau kaip 15 km/h mažesniu, nei leistina, greičiu, kai eismo situacija to nereikalauja. Ši KK taip pat vertinama, kai egzaminuojamasis važiuoja per lėtai, atlikdamas specialiuosius važiavimo manevrus, aprašytus 2 priede. Ši KK nevertinama, jei egzaminuojamasis saugiai greitėja su transporto srautu;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "48.3. KK – didina greitį, kai yra lenkiamas, ir sudaro potencialiai pavojingą situaciją;","48.3. KK – didina greitį, kai yra lenkiamas, ir sudaro potencialiai pavojingą situaciją;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "48.4. KK – važiuoja per greitai, bet neviršija leistino greičio, ir sudaro potencialiai pavojingą situaciją, taip pat ir važiuodamas atbuline eiga;","48.4. KK – važiuoja per greitai, bet neviršija leistino greičio, ir sudaro potencialiai pavojingą situaciją, taip pat ir važiuodamas atbuline eiga;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "48.5. KK, nurodytos 48.1 ir 48.2 papunkčiuose, neturi būti vertinamos, jei buvo trumpalaikės ir greitai ištaisytos.","48.5. KK, nurodytos 48.1 ir 48.2 papunkčiuose, neturi būti vertinamos, jei buvo trumpalaikės ir greitai ištaisytos.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "49. Eismo reguliavimo signalų, kelio ženklų ir kelių ženklinimo reikalavimų vykdymas:","49. Eismo reguliavimo signalų, kelio ženklų ir kelių ženklinimo reikalavimų vykdymas:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "49.1. KK – nevykdo eismo reguliavimo signalo, kelio ženklo arba kelių ženklinimo reikalavimų. Ši KK taip pat vertinama, jei egzaminuojamojo vairuojama transporto priemonė išvažiuoja už linijos, žyminčios specialiajam važiavimo manevrui skirto ploto ribas arba pabaigus specialiuosius važiavimo manevrus, aprašytus 2 priedo 3.3 ir 3.4 papunkčiuose, stovi ant (už) stovėjimo vietą žyminčių linijų, jei stovėjimo vieta paženklinta;","49.1. KK – nevykdo eismo reguliavimo signalo, kelio ženklo arba kelių ženklinimo reikalavimų. Ši KK taip pat vertinama, jei egzaminuojamojo vairuojama transporto priemonė išvažiuoja už linijos, žyminčios specialiajam važiavimo manevrui skirto ploto ribas arba pabaigus specialiuosius važiavimo manevrus, aprašytus 2 priedo 3.3 ir 3.4 papunkčiuose, stovi ant (už) stovėjimo vietą žyminčių linijų, jei stovėjimo vieta paženklinta;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "49.2. KK – sustabdo transporto priemonę už „Stop“ linijos ar važiuojamųjų dalių sankirtos krašto tokiu atstumu, kad sudaro potencialiai pavojingą situaciją. Ši KK taip pat vertinama, jei egzaminuojamasis, atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą, pravažiuoja sustojimo vietą žyminčią liniją (gaireles);","49.2. KK – sustabdo transporto priemonę už „Stop“ linijos ar važiuojamųjų dalių sankirtos krašto tokiu atstumu, kad sudaro potencialiai pavojingą situaciją. Ši KK taip pat vertinama, jei egzaminuojamasis, atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą, pravažiuoja sustojimo vietą žyminčią liniją (gaireles);",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "49.3. KK (A1, A2 ir A kategorijos) – atlikdamas specialųjį važiavimo manevrą, aprašytą 2 priedo 2.6 papunktyje, nesustabdo motociklo nurodytoje vietoje ir, kiek įmanoma, arčiau jos. Sustojimas, kiek įmanoma arčiau, reiškia ne daugiau kaip per 1 m prieš „Stop“ liniją ar kūgiu pažymėtą sustojimo vietą.","49.3. KK (A1, A2 ir A kategorijos) – atlikdamas specialųjį važiavimo manevrą, aprašytą 2 priedo 2.6 papunktyje, nesustabdo motociklo nurodytoje vietoje ir, kiek įmanoma, arčiau jos. Sustojimas, kiek įmanoma arčiau, reiškia ne daugiau kaip per 1 m prieš „Stop“ liniją ar kūgiu pažymėtą sustojimo vietą.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50. Nuoseklumas (BE, C1E, CE, D1E ir DE kategorijos):","50. Nuoseklumas (BE, C1E, CE, D1E ir DE kategorijos):",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.1. KK – nesugeba atkabinti arba prikabinti priekabos;","50.1. KK – nesugeba atkabinti arba prikabinti priekabos;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.2. KK – praleidžia dalį veiksmų, taip sudarydamas potencialiai pavojingą situaciją:","50.2. KK – praleidžia dalį veiksmų, taip sudarydamas potencialiai pavojingą situaciją:",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.2.1. nepanaudoja stovėjimo stabdžio;","50.2.1. nepanaudoja stovėjimo stabdžio;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.2.2. neatlieka sukabinimo patikrinimo manevro;","50.2.2. neatlieka sukabinimo patikrinimo manevro;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.2.3. nepakelia arba nenuleidžia atramų ar neužfiksuoja pakėlimo rankenos;","50.2.3. nepakelia arba nenuleidžia atramų ar neužfiksuoja pakėlimo rankenos;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.2.4. neįsitikina, kad sukabinimo įtaiso kablio arba smaigo užrakto žiotys uždarytos arba atidarytos, kai to reikia;","50.2.4. neįsitikina, kad sukabinimo įtaiso kablio arba smaigo užrakto žiotys uždarytos arba atidarytos, kai to reikia;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.2.5. nenaudoja ratų atsparų, kai to reikia;","50.2.5. nenaudoja ratų atsparų, kai to reikia;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.2.6. neatjungia ir neuždaro arba nesujungia žarnų ar kabelių;","50.2.6. neatjungia ir neuždaro arba nesujungia žarnų ar kabelių;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "50.2.7. (C1E, CE, D1E ir DE kategorijos) prieš pradėdamas važiuoti neįsitikina, ar oro slėgis sistemoje pasiekė tinkamą lygį.","50.2.7. (C1E, CE, D1E ir DE kategorijos) prieš pradėdamas važiuoti neįsitikina, ar oro slėgis sistemoje pasiekė tinkamą lygį.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "51. Pusiausvyra ir motociklo valdymas (A1, A2 ir A kategorijos):","51. Pusiausvyra ir motociklo valdymas (A1, A2 ir A kategorijos):",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "51.1. KK – krenta nuo motociklo arba jį paguldo;","51.1. KK – krenta nuo motociklo arba jį paguldo;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "51.2. KK – neišlaiko pusiausvyros arba nesuvaldo motociklo ir sudaro potencialiai pavojingą situaciją;","51.2. KK – neišlaiko pusiausvyros arba nesuvaldo motociklo ir sudaro potencialiai pavojingą situaciją;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "51.3. KK – nesugeba tinkamai pastatyti motociklo ant stovėjimo kojelių ar šoninės atramos;","51.3. KK – nesugeba tinkamai pastatyti motociklo ant stovėjimo kojelių ar šoninės atramos;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "51.4. KK – nesugeba atlikti specialiojo važiavimo manevro nesustodamas;","51.4. KK – nesugeba atlikti specialiojo važiavimo manevro nesustodamas;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "51.5. KK – išvažiuoja už kūgiais pažymėtos eismo juostos ribų arba nesilaiko kūgių apvažiavimo trajektorijos, atliekant specialiuosius važiavimo manevrus, aprašytus 2 priede.","51.5. KK – išvažiuoja už kūgiais pažymėtos eismo juostos ribų arba nesilaiko kūgių apvažiavimo trajektorijos, atliekant specialiuosius važiavimo manevrus, aprašytus 2 priede.",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "52. Sėdėsena (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos):","52. Sėdėsena (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos):",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "52.1. KK – atitraukia abi rankas nuo vairo, kai motociklas važiuoja;","52.1. KK – atitraukia abi rankas nuo vairo, kai motociklas važiuoja;",""));
//        KK_Mistakes.add(new com.multiselect.UserModel(false, "52.2. KK – padeda kojas ant paminų kitaip, nei skirta kojoms naudoti valdymo įtaisuose.","52.2. KK – padeda kojas ant paminų kitaip, nei skirta kojoms naudoti valdymo įtaisuose.",""));
//        BK_Mistakes.add(new com.multiselect.UserModel(false, "53. BKK – egzaminuotojo įsikišimas žodžiu arba fiziškai, kad:","53. BKK – egzaminuotojo įsikišimas žodžiu arba fiziškai, kad:",""));
//        BK_Mistakes.add(new com.multiselect.UserModel(false, "53.1. būtų išvengta eismo įvykio;","53.1. būtų išvengta eismo įvykio;",""));
//        BK_Mistakes.add(new com.multiselect.UserModel(false, "53.2. būtų išvengta pavojingos situacijos dėl neadekvataus transporto priemonės valdymo, galinčio sukelti grėsmę eismo saugumui;","53.2. būtų išvengta pavojingos situacijos dėl neadekvataus transporto priemonės valdymo, galinčio sukelti grėsmę eismo saugumui;",""));
//        BK_Mistakes.add(new com.multiselect.UserModel(false, "53.3. padėtų egzaminuojamajam bet kokioje egzamino dalyje dėl jo nesugebėjimo, t. y. atliktų už jį veiksmus, kai tai yra būtina saugiam eismui užtikrinti, arba dėl neadekvataus laiko, reikalingo egzaminui užbaigti.","53.3. padėtų egzaminuojamajam bet kokioje egzamino dalyje dėl jo nesugebėjimo, t. y. atliktų už jį veiksmus, kai tai yra būtina saugiam eismui užtikrinti, arba dėl neadekvataus laiko, reikalingo egzaminui užbaigti.",""));
//        BK_Mistakes.add(new com.multiselect.UserModel(false, "54. BKK –susidūrimas. Tai situacija, kai egzaminuojamojo vairuojama transporto priemonė susiduria (užvažiuoja) su kitu objektu. Ši BKK taip pat vertinama užvažiavus vienu arba daugiau ratų ant šaligatvio krašto.","54. BKK –susidūrimas. Tai situacija, kai egzaminuojamojo vairuojama transporto priemonė susiduria (užvažiuoja) su kitu objektu. Ši BKK taip pat vertinama užvažiavus vienu arba daugiau ratų ant šaligatvio krašto.",""));
//        BK_Mistakes.add(new com.multiselect.UserModel(false, "55. BKK – pavojinga situacija. Egzaminuojamojo veiksmų sudaryta pavojinga situacija, dėl kurios kiti eismo dalyviai (taip pat ir pėstieji) priversti imtis veiksmų, kad išvengtų susidūrimo arba kitokio pavojaus.","55. BKK – pavojinga situacija. Egzaminuojamojo veiksmų sudaryta pavojinga situacija, dėl kurios kiti eismo dalyviai (taip pat ir pėstieji) priversti imtis veiksmų, kad išvengtų susidūrimo arba kitokio pavojaus.",""));
//        BK_Mistakes.add(new com.multiselect.UserModel(false, "56. BKK – privalomų nurodymų nevykdymas. Egzaminuojamasis nevykdo tikrinančių pareigūnų (reguliuotojų) nurodymų. ","56. BKK – privalomų nurodymų nevykdymas. Egzaminuojamasis nevykdo tikrinančių pareigūnų (reguliuotojų) nurodymų. ",""));
//        final CustomAdapter adapter = new CustomAdapter(this, Mistakes);
//        final CustomAdapter adapter1 = new CustomAdapter(this, KK_Mistakes);
//        final CustomAdapter adapter2 = new CustomAdapter(this, BK_Mistakes);
//        Spinner DropDown=(Spinner) findViewById(R.id.Spinerris);
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("Nekritines klaidos");
//        arrayList.add("Kritines klaidos");
//        arrayList.add("Bendrosios klaidos");
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        DropDown.setAdapter(arrayAdapter);
//        DropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Spinner_id=position;
//                if(Spinner_id==0){
//                    listView.setAdapter(adapter);
//                }
//                if(Spinner_id==1){
//                    listView.setAdapter(adapter1);
//                }
//                if(Spinner_id==2) {
//                    listView.setAdapter(adapter2);
//                }
//                //Spinner_name = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(parent.getContext(), "Selected: " + Spinner_name+" "+String.valueOf(Spinner_id), Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView <?> parent) {
//            }
//        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(Spinner_id==0) {
//                    com.multiselect.UserModel Selected_mistake = Mistakes.get(i);
//                    if (Selected_mistake.isSelected()) {
//                        Selected_mistake.setSelected(false);
//                    } else {
//                        Selected_mistake.setSelected(true);
//                    }
//                    Mistakes.set(i, Selected_mistake);
//                    //now update adapter
//                    adapter.updateRecords(Mistakes);
//                }
//               else if(Spinner_id==1){
//                    com.multiselect.UserModel Selected_mistake = KK_Mistakes.get(i);
//                    if (Selected_mistake.isSelected()){
//                        Selected_mistake.setSelected(false);
//                    }
//                    else {
//                        Selected_mistake.setSelected(true);
//                    }
//                    KK_Mistakes.set(i, Selected_mistake);
//                    //now update adapter
//                    adapter1.updateRecords(KK_Mistakes);
//                }
//               else if(Spinner_id==2){
//                    com.multiselect.UserModel Selected_mistake = BK_Mistakes.get(i);
//                    if (Selected_mistake.isSelected()){
//                        Selected_mistake.setSelected(false);
//                    }
//                    else {
//                        Selected_mistake.setSelected(true);
//                    }
//                    BK_Mistakes.set(i, Selected_mistake);
//                    //now update adapter
//                    adapter2.updateRecords(BK_Mistakes);
//                }
//            }
//        });
        submit_button=findViewById(R.id.Button1);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.save1)
                            .setTitle("Klaidų išsaugojimas")
                            .setMessage("Ar jūs tikrai norite išsaugoti šiuos duomenis?")
                            .setPositiveButton("Taip", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
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
    private void savePdf() {
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
                        Text_to_file+=expandableListDetail.get(expandableListTitle.get(i)).get(j).userName;
                        Text_to_file+="\n";
                    }
                }

            }
//            for(int i=0;i<Mistakes.size();i++){
//                if(Mistakes.get(i).isSelected()){
//                    Text_to_file+=Mistakes.get(i).getTrue_val();
//                    Text_to_file+="\n";
//                }
////                else{
////                    Text_to_file+=Mistakes.get(i).getFalse_val();
////                    Text_to_file+="\n";
////                }
//            }
//            for(int i=0;i<KK_Mistakes.size();i++){
//                if(KK_Mistakes.get(i).isSelected()){
//                    Text_to_file+=KK_Mistakes.get(i).getTrue_val();
//                    Text_to_file+="\n";
//                }
////                else{
////                    Text_to_file+=Mistakes.get(i).getFalse_val();
////                    Text_to_file+="\n";
////                }
//            }
//            for(int i=0;i<BK_Mistakes.size();i++){
//                if(BK_Mistakes.get(i).isSelected()){
//                    Text_to_file+=BK_Mistakes.get(i).getTrue_val();
//                    Text_to_file+="\n";
//                }
////                else{
////                    Text_to_file+=Mistakes.get(i).getFalse_val();
////                    Text_to_file+="\n";
////                }
//            }
            //add paragraph
            mDoc.add(new Paragraph(Text_to_file));
            mDoc.close();
            Toast.makeText(this,"Saved succesfully"+File_name+".pdf \n saved to "+File_path,Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
}