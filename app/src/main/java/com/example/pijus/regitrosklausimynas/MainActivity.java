package com.example.pijus.regitrosklausimynas;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static  final int STORAGE_CODE=1000;
    Button submit_button;
    final List<com.multiselect.UserModel> Mistakes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.List_View);
        Mistakes.add(new com.multiselect.UserModel(false, "13. Patikrinimas prieš važiavimą","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "NK – nesugeba surasti egzaminuotojo nurodytų transporto priemonės valdymo įtaisų ar įrangos; pademonstruoti savo žinių ar sugebėjimų, kaip juos valdyti, sureguliuoti arba patikrinti jų būklės; pasirengti važiuoti saugiai.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "14. Papildomi valdymo ir saugos įtaisai. Šios NK vertinamos tik po dviejų egzaminuotojo įspėjimų: „Prašau patikrinti valdymo įtaisus ir įrangą“. Turi būti skirta pakankamai laiko egzaminuojamajam pasitaisyti po kiekvieno įspėjimo:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15. Sankabos valdymas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.3. NK – per greitai atleidžia sankabos paminą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.8. SPK – šešis kartus užgesina variklį;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "15.9. SPK – šešis kartus važiuoja išjungta sankaba.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "16. Akceleratoriaus valdymas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "16.1. NK – netinkamai valdo akceleratoriaus paminą arba motociklo degalų padavimo reguliatorių (droselį);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "16.2. NK – per daug nuspaudžia akceleratoriaus paminą arba motociklo degalų padavimo reguliatorių (droselį), kai sankaba išjungta ar nevisiškai įjungta;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "16.3. NK – spaudžia akceleratoriaus paminą ir neleidžia transporto priemonei judėti iš inercijos prieš sustojant iš anksto numatytoje vietoje (sankryžoje, kurioje dega draudžiamas šviesoforo signalas, stovi „Stop“ ženklas ir pan.) ar kitose situacijose.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17. Pavaros pasirinkimas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.1. NK – pasirenka pavarą, neatitinkančią: situacijos, transporto priemonės greičio, techninių duomenų ir važiavimo sąlygų, bet nesudaro potencialiai pavojingos situacijos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.2. NK – važiuoja įjungęs neutralią pavarą išlanka (posūkiu ar vingiu) arba nuokalne;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.3. NK - jungia pavarą, kai važiuodamas sankryžoje ar išlankoje suka vairą, ir (arba) pavaros perjungimas trukdo transporto priemonei judėti sklandžiai ir tolygiai;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.4.NK (B kategorija) – aukštesnę pavarą jungia varikliui pasiekus 2300 ar daugiau sūkių per minutę, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.5. NK (B kategorija) – pirma pavara nuvažiuoja didesnį kaip dviejų automobilių ilgio atstumą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.6. NK (B kategorija) – tolygiai važiuoja 30 km/h ar didesniu greičiu, įjungęs žemesnę nei trečią pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.7. NK (B kategorija) – tolygiai važiuoja 50 km/h ar didesniu greičiu, įjungęs žemesnę nei ketvirtą pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.8. NK (B kategorija) – tolygiai važiuoja 70 km/h ar didesniu greičiu, įjungęs žemesnę nei penktą pavarą, kai eismo situacija ir (arba) važiavimo sąlygos to nereikalauja;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.9. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – spaudžia automatinės pavarų dėžės selektoriaus mygtuką, kai iš žemesnių pavarų diapazono (1, 2, 3 pozicijos) persijungia į važiavimo pavarą („Drive“ pozicija);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.10. NK – bando jungti arba jungia pavarą neišjungęs sankabos.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.11. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – per daug triukšmingai jungia pavaras;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.12. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – neperjungia pavaros iš antro karto;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.13. NK (A1, A2 ir A kategorijos) – atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą, nesugeba sklandžiai perjungti pavaros;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.14. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – netinkamai naudojasi pavaromis, kad sumažintų degalų naudojimą ir išmetamųjų teršalų kiekį;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "17.15. SPK – šešios pasikartojančios pavaros pasirinkimo NK.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18. Vairo valdymas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.1. NK – neišlaiko pasirinktos trajektorijos: nukrypsta nuo trajektorijos, trūkčiodamas sukinėja vairą; yra neatidus netinkamai vairuojamųjų ratų padėčiai, palikdamas stovėti transporto priemonę ar pradėdamas važiuoti;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.2. NK – bent vienu ratu įvažiuoja į kelkraštį arba atsiremia į bortelį ar šaligatvio kraštą, bet neužvažiuoja ant jo. Ši NK nevertinama, jei transporto priemonės ratas saugiai ir sklandžiai įvažiuoja į kelkraštį arba paliečia bortelį, ar šaligatvio kraštą, atliekant specialųjį važiavimo manevrą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.3. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – suka vairą netinkamu būdu:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.3.1. deda rankas ant vairo iš vidaus;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.3.2. suka vairą tik viena ranka, išskyrus važiavimą atbuline eiga ir transporto priemonės valdymo įtaisų naudojimą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.3.3. suka vairą vienos rankos delnu;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.3.4. per daug suka vairą, kai transporto priemonė nejuda;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.3.5. laiko vairą sukryžiavęs rankas ar kitokiu būdu, kuris riboja judesius vairu.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "18.4. SPK (visos kategorijos, išskyrus A1, A2 ir A) – šešios pasikartojančios 18.1 arba 18.2 papunkčiuose nurodytos vairo valdymo NK.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19. Stabdymas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.1. NK – netinkamai stabdydamas leidžia transporto priemonei pajudėti į priekį;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.2. NK – neišlaiko transporto priemonės vietoje nei stovėjimo, nei darbiniu stabdžiu, kai ji stovi. Ši NK nevertinama, kai koja perkeliama nuo stabdžių ant akceleratoriaus paminos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.3. NK – valdo stabdžių paminą netinkamu būdu;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.4. NK – neišlaiko vietoje transporto priemonės, sustabdęs įkalnėje, t. y. leidžia jai pariedėti atgal, bet greitai pasitaiso ir nesudaro potencialiai pavojingos situacijos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.5. NK – (visos kategorijos, išskyrus A1, A2, A) blokuoja ratus (taip pat atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą), bet greitai pasitaiso;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.6. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – stipriai spaudžia darbinio stabdžio paminą važiuodamas posūkiu;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.7. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – važiuoja nevisiškai išjungęs stovėjimo stabdį arba naudoja jį be reikalo, bet tai netrukdo transporto priemonei judėti;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.8. NK (visos kategorijos, išskyrus A1, A2, A ir B1 (vairuojama kaip motociklas) – stabdo kaire koja;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.9. NK (A1, A2 ir A kategorijos) – nenaudoja užpakalinio rato stabdžio lėtindamas greitį ar sustodamas;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.10. NK (A1, A2 ir A kategorijos) – blokuoja užpakalinį ratą (taip pat atlikdamas įsibėgėjimo ir tikslaus sustojimo nurodytoje vietoje manevrą), bet greitai pasitaiso;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "19.11. NK (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos) – netinkamai naudoja stabdžius važiuodamas posūkiu, apvažiuodamas nukreipiamuosius kūgius.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "20. Padėties pasirinkimas važiuojamojoje dalyje:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "20.1. NK – nesilaiko eismo juostos ribų, išskyrus atvejus, kai egzaminuojamasis privalo keisti padėtį važiuojamojoje dalyje dėl susidariusios eismo situacijos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "20.2. NK – važiuojamojoje dalyje užima tokią padėtį, kuri neatitinka eismo situacijos, t. y. nemato kelio priekyje ir nekoreguoja pasirinktos trajektorijos, taip pat nepersirikiuoja arba be reikalo persirikiuoja iš vienos eismo juostos į kitą ir pan.;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "20.3. NK – nepasitraukia dešiniau, kur tai įmanoma, važiuodamas keliu, kuriame yra po vieną kiekvienos krypties eismo juostą. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "20.4. NK – be reikalo važiuoja kairiąja eismo juosta. Ši NK nevertinama, jei važiuojant dešiniąja juosta reikia dažnai persirikiuoti dėl joje stovinčių transporto priemonių arba jei reikia važiuoti tiesiai, o iš dešiniosios juostos sankryžoje galima sukti tik į dešinę, arba eismas kitoje juostoje intensyvus;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "20.5. NK – sustabdo transporto priemonę netinkamoje vietoje ( neprivažiavęs „Stop“ linijos, važiuojamųjų dalių sankirtos ir pan.);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "20.6. NK (A1, A2, A kategorija) – važiuoja horizontaliuoju kelių ženklinimu, nešvaria kelio dalimi (tepalo dėmės, smėlis ir pan.), kai to galima išvengti;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "20.7. SPK – šešios pasikartojančios padėties pasirinkimo važiuojamojoje dalyje NK.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21. Padėties pasirinkimas manevruojant:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.1. NK (B1 ir B kategorija) – pasirinko netinkamą padėtį, pradėdamas transporto priemonės pastatymo į laikiną stovėjimo vietą lygiagrečiai, įstrižai arba statmenai važiuojamosios dalies kraštui manevrą, t.y. arčiau kaip 50 cm arba toliau kaip 1 m atstumu iš šono nuo stovinčios transporto priemonės;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.2. NK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą lygiagrečiai važiuojamosios dalies kraštui manevrą:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.2.1. transporto priemonė stovi nelygiagrečiai važiuojamosios dalies (šaligatvio) kraštui;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.2.2. bent vienas arčiau važiuojamosios dalies (šaligatvio) krašto esantis transporto priemonės ratas yra toliau kaip 40 cm nuo važiuojamosios dalies (šaligatvio) krašto;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.2.3. transporto priemonė yra arčiau kaip 1 m arba toliau kaip 2 m atstumu nuo priekyje stovinčios transporto priemonės.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.3. NK (B1 ir B kategorija) – pabaigus transporto priemonės pastatymo į laikino stovėjimo vietą įstrižai arba statmenai važiuojamosios dalies kraštui manevrą transporto priemonė stovi:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.3.1. nelygiagrečiai greta stovinčiai transporto priemonei ar stovėjimo vietą žyminčioms linijoms;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.3.2. arčiau kaip 50 cm arba toliau kaip 1 m atstumu nuo greta stovinčios transporto priemonės;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.3.3. toliau kaip 40 cm atstumu nuo stovėjimo vietos pabaigą žyminčios linijos.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.4. NK (B1 ir B kategorija) – atlikdamas transporto priemonės pastatymo lygiagrečiai važiuojamosios dalies kraštui manevrą, atbuline eiga nuvažiavo daugiau kaip 3 m nuo priekyje stovinčios transporto priemonės (tarpas tarp egzaminuojamojo vairuojamos transporto priemonės priekio ir priekyje stovinčios transporto priemonės galo);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.5. NK (visos kategorijos, išskyrus A1, A2 ir A) – atlikdamas specialiuosius važiavimo manevrus, aprašytus 2 priede, tris ir daugiau kartų naudojo atbulinės eigos pavarą. Ši NK vertinama vieną kartą atliekant kiekvieną specialųjį važiavimo manevrą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.6. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – sustojo daugiau kaip 1 m atstumu prieš eismo juostos pabaigą, atlikdamas 2 priedo 9.1 papunktyje aprašytą specialųjį važiavimo manevrą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.7. NK (B1 ir B kategorijos) – važiuodamas atbuline eiga su posūkiu į dešinę nuvažiavo daugiau kaip 1 m nuo dešiniojo važiuojamosios dalies (šaligatvio) krašto;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.8. SPK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – šešis ar daugiau kartų sustojo daugiau kaip 1 m prieš eismo juostos pabaigą, atlikdamas 2 priedo 9.1 papunktyje aprašytą specialųjį važiavimo manevrą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "21.9. NK (D1, D, ir T kategorijos) – pabaigus transporto priemonės pastatymo šonu prie pakylos manevrą, bent vienos keleivių įlaipinimo arba išlaipinimo durys yra toliau kaip 30 cm nuo pakylos krašto.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "22. Padėties pasirinkimas, sukant į dešinę:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "22.1. NK – prieš posūkį transporto priemonė yra per toli nuo dešiniojo važiuojamosios dalies krašto;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "22.2. NK – pradėjus sukti, transporto priemonė yra per toli nuo dešiniojo važiuojamosios dalies krašto, sukama per plačiai ir posūkis užbaigiamas per arti priešingos krypties eismo juostos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "22.3. NK (C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – baigdamas posūkį į dešinę bet kuria transporto priemonės dalimi be reikalo įvažiuoja į priešingos krypties eismo juostą.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "23. Padėties pasirinkimas, sukant į kairę:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "23.1. NK – prieš posūkį (apsisukimą) transporto priemonė yra ne eismo juostoje, nelygiagreti ženklinimui, toliau nei būtina nuo priešingų krypčių eismo srautus skiriančios (pažymėtos ar nepažymėtos) linijos arba skiriamosios juostos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "23.2. NK – suka būdamas per toli nuo kairiojo važiuojamosios dalies krašto vienpusio eismo kelyje;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "23.3. NK – pasuka priekinius ratus į kairę laukdamas posūkio, išskyrus situacijas, kai tai yra būtina;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "23.4. NK – suka plačiai, važiuoja per daug į dešinę nuo važiuojamųjų dalių sankirtos centro, kur eismo sąlygos to nereikalauja;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "23.5. NK (visos kategorijos, išskyrus A1, A2 ir A) – įvažiuodamas į važiuojamųjų dalių sankirtą arba išvažiuodamas iš jos nežymiai išvažiuoja į priešingos krypties eismo juostą, bet nesudaro potencialiai pavojingos situacijos.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "24. Situacijos stebėjimas ir žvalgymasis:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "24.1. NK – nesižvalgo ir nestebi viso kelio priekyje, iš šono ir už transporto priemonės;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "24.2. NK – pasukdamas galvą per atitinkamą petį netikrina per užpakalinio vaizdo veidrodžius nematomos kelio dalies kiekvieną kartą apvažiuodamas kliūtį, persirikiuodamas į kitą eismo juostą arba kelių išsiskyrimo ar jungimosi vietoje. Ši NK ne visada gali būti vertinama, jei egzaminuojamasis vairuoja C, CE, D ar DE kategorijos transporto priemonę;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "24.3. NK – nepasižiūri atgal prieš pradėdamas važiuoti atbuline eiga. Ši NK taip pat vertinama, kai egzaminuojamasis vairuoja B1 arba B kategorijos transporto priemonę ir pradėdamas važiuoti atbuline eiga pasižiūri atgal tik per užpakalinio vaizdo veidrodžius;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "24.4. NK – per ilgai atitraukia dėmesį nuo važiavimo krypties, bet nesudaro potencialiai pavojingos situacijos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "24.5. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – atlikdamas 2 priedo 5 arba 9.2.3 papunktyje aprašytus specialiuosius važiavimo manevrus išlipo apsižvalgyti daugiau kaip vieną kartą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "24.6. SPK – šešios situacijos ir daugiau, kai reikia patikrinti per užpakalinio vaizdo veidrodžius nematomą kelio dalį, bet per petį nepasižiūrima.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "25. Veidrodžių naudojimas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "25.1. NK – nežiūri į užpakalinio vaizdo veidrodžius prieš sulėtindamas, sustabdydamas transporto priemonę, įvažiuodamas į posūkį, persirikiuodamas, išvažiuodamas iš kelio ar įvažiuodamas į kelią, artėdamas prie bet kokios kliūties;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "25.2. NK – per ilgai (pagal situaciją) žiūri į užpakalinio vaizdo veidrodį;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "25.3. NK (BE, C1, C, C1E, CE, D1, D, D1E ir DE kategorijos) – nesinaudoja išoriniais užpakalinio vaizdo veidrodžiais, kai žiūri atgal važiavimo atbuline eiga manevro metu, t. y. žiūri per langą, kad galėtų valdyti transporto priemonę. Atsitiktiniai žvilgsniai leistini.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "26. Įspėjamųjų signalų naudojimas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "26.1. NK – tinkamai neparodo posūkio signalo, kad įspėtų apie savo ketinimus, kai tai būtina. Ši NK nevertinama, kai dėl tam tikrų aplinkybių (mažas atstumas tarp artimiausio kertamo kelio arba įvažiavimo į šalia kelio esančią teritoriją ir numatyto posūkio vietos ir pan.) tai daryti netikslinga;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "26.2. NK – nerodo posūkio į dešinę signalo prieš išvažiuodamas iš žiedinės sankryžos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "26.3. NK – rodo klaidingos krypties posūkio signalą. Ši NK vertinama, jei egzaminuojamasis suka į priešingą pusę, nei rodo signalą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "26.4. NK – neišjungia posūkio signalo per penkias sekundes po to, kai posūkis ar persirikiavimas buvo baigtas;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "26.5. NK – išjungia posūkio signalą per anksti, t. y. anksčiau nei posūkis ar persirikiavimas pradėtas ar baigtas, ir daugiau jo nebeįjungia;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "26.6. NK – rodo posūkio signalą be reikalo ir gali suklaidinti kitus eismo dalyvius;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "26.7. SPK – šešios pasikartojančios įspėjamųjų signalų naudojimo NK.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "27. Eismo pavojų įvertinimas","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "NK – netinkamai ar nepakankamai greitai reaguoja į eismo pavojų.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "28. Sprendimų priėmimas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "28.1. NK – tinkamai neįvertina greičio ir atstumo iki kitų transporto priemonių pradėdamas važiuoti, važiuodamas per sankryžą, persirikiuodamas iš vienos eismo juostos į kitą, įvažiuodamas į kelią, prieš pradėdamas atlikti arba atlikdamas specialųjį važiavimo manevrą, aprašytą 2 priede;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "28.2. NK – sustoja tinkamai, bet esant saugiam tarpui transporto sraute nesiryžta važiuoti;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "28.3. NK – be reikalo sustoja (delsia) ir nevažiuoja toliau, kai nėra transporto priemonių ar pėsčiųjų, kuriems galėtų trukdyti, o kelias yra laisvas;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "28.4. NK – be reikalo duoda kelią kitam eismo dalyviui, t. y. netinkamai taiko važiavimo pirmumo taisykles.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "29. Saugaus atstumo pasirinkimas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "29.1. NK – laikosi mažesnio kaip per dvi sekundes, bet didesnio kaip per vieną sekundę nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "29.2. NK – laikosi mažesnio kaip per tris sekundes, bet didesnio kaip per dvi sekundes nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės kelyje, kuriame yra dvi ar daugiau eismo juostų, skirtų važiuoti viena kryptimi, kai eismo situacija to nereikalauja.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "29.3. NK – nepalankiomis eismo sąlygomis laikosi mažesnio kaip per keturias sekundes, bet didesnio kaip per dvi sekundes nuvažiuojamo atstumo iki priešais važiuojančios transporto priemonės. Egzaminuotojas privalo įvertinti papildomus (didesnius) atstumus, kurių turi laikytis C1, C, C1E, CE, D1, D, D1E, DE ir T kategorijų transporto priemonių vairuotojai;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "29.4. NK – laikosi per mažo šoninio atstumo iki stovinčių, priešpriešiais ar ta pačia kryptimi važiuojančių transporto priemonių, dviratininkų, pėsčiųjų, einančių važiuojamąja kelio dalimi, kliūčių važiuojamosios dalies pakraštyje, kelkraščio arba šaligatvio, tačiau nesudaro potencialiai pavojingos situacijos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "29.5. NK – sustabdo transporto priemonę, palikdamas nepakankamą tarpą iki priešais sustojusios transporto priemonės, kad prireikus galėtų apvažiuoti priešais sustojusią transporto priemonę, nevažiuodamas atbuline eiga.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "30. Greičio pasirinkimas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "30.1. NK – įsibėgėja per greitai arba per lėtai, neatsižvelgdamas į eismo sąlygas;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "30.2. NK – važiuoja per greitai konkrečioje situacijoje, bet neviršija leistino greičio. Ši NK vertinama, kai egzaminuojamasis važiuoja didesniu nei tinkama greičiu;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "30.3. NK – važiuoja per lėtai konkrečioje situacijoje, t. y. 10–15 km/h mažesniu, nei leistina, greičiu, kai eismo sąlygos to nereikalauja. Ši NK taip pat vertinama kai egzaminuojamasis nepasiekia reikiamo greičio, atlikdamas specialiuosius važiavimo manevrus, aprašytus 2 priede;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "30.4. NK – važiuoja viršydamas leistiną greitį ne daugiau kaip 10 procentų. Klaida neturi būti vertinama, jei buvo trumpalaikė ir greitai ištaisyta.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "31. Eismo reguliavimo signalų, kelio ženklų ir kelių ženklinimo reikalavimų vykdymas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "31.1. NK – sustabdo transporto priemonę taip, kad ji išsikiša už „Stop“ linijos (ženklo „Stop“ linija);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "31.2. NK – ketindamas sankryžoje pasukti į kairę, neįvažiuoja į važiuojamųjų dalių sankirtą laukti saugaus tarpo transporto sraute, kai tai dera daryti;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "31.3. NK – nepakankamai supranta eismo reguliavimo signalus, t. y. per ilgai delsia, nors gali važiuoti nekliudydamas kitiems eismo dalyviams;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "31.4. NK – ketindamas sankryžoje pasukti į kairę, netinkamai įvažiuoja į važiuojamųjų dalių sankirtą paskui pirmąją transporto priemonę. Ši NK vertinama, kai egzaminuojamajam važiuoti leidžia šviesoforo (reguliuotojo) signalas.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "32. Nuoseklumas:","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "32.1. NK – pasirenka netinkamą transporto priemonės valdymo sistemos seką;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "32.2. NK (BE, C1E, CE, D1E ir DE kategorijos) – netinkamai atidaro sukabinimo įtaiso kablio arba smaigo užrakto žiotis, besiruošdamas atkabinti arba prikabinti priekabą;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "32.3. NK (BE, C1E, CE, D1E ir DE kategorijos) – nesaugiai įtraukia priekabos atramas, bet nesudaro potencialiai pavojingos situacijos;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "32.4. NK (BE, C1E, CE, D1E ir DE kategorijos) – prikabinęs priekabą nepatikrina, ar veikia visi priekabos žibintai.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "33. Pusiausvyra ir motociklo valdymas (A1, A2 ir A kategorijos):","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "33.1. NK – neišlaiko pusiausvyros arba nesuvaldo motociklo ir (arba) važiuodamas koja atsiremia į žemę;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "33.2. NK – neišlaiko lėtai einančio pėsčiojo greičio, atlikdamas specialiuosius važiavimo manevrus mažu greičiu;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "33.3. NK – nepasvyra motociklu taip, kad saugiai važiuotų posūkyje, atliktų specialiuosius važiavimo manevrus, aprašytus 2 priede;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "33.4. SPK – daugiau kaip penkis kartus neišlaiko pusiausvyros arba nesuvaldo motociklo.","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "34. Sėdėsena (A1, A2, A ir B1 (vairuojama kaip motociklas) kategorijos):","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "34.1. NK – važiuoja kojų pirštais atsirėmęs į paminas;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "34.2. NK – važiuoja keliais neliesdamas degalų bako;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "34.3. NK – važiuoja laikydamas galvą netinkamoje padėtyje;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "34.4. NK – važiuoja sėdėdamas (atsitraukia) per toli nuo degalų bako;","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "34.5. NK – važiuoja slidinėdamas iš vienos pusės į kitą per sėdynę (balną);","tiesa","melas"));
        Mistakes.add(new com.multiselect.UserModel(false, "34.6. NK – važiuoja bet kurią ranką atitraukęs nuo vairo be tinkamos priežasties, bet neprarasdamas pusiausvyros ir valdymo.","tiesa","melas"));
        final com.multiselect.CustomAdapter adapter = new com.multiselect.CustomAdapter(this, Mistakes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                com.multiselect.UserModel model = Mistakes.get(i);
                if (model.isSelected()){
                    model.setSelected(false);
                }
                else {
                    model.setSelected(true);
                }
                Mistakes.set(i, model);
                //now update adapter
                adapter.updateRecords(Mistakes);
            }
        });
        submit_button=findViewById(R.id.Button1);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            for(int i=0;i<Mistakes.size();i++){
                if(Mistakes.get(i).isSelected()){
                    Text_to_file+=Mistakes.get(i).getTrue_val();
                    Text_to_file+="\n";
                }
                else{
                    Text_to_file+=Mistakes.get(i).getFalse_val();
                    Text_to_file+="\n";
                }
            }
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