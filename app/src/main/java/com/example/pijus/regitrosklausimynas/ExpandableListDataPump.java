package com.example.pijus.regitrosklausimynas;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<UserModel>> getData() {
        HashMap<String, List<UserModel> > mano_listas=new HashMap<>();// va toki tureciau naudoti
        List<UserModel> klaidos=new ArrayList<>();

        klaidos.add(new UserModel(false, "14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;","14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;",""));
        klaidos.add(new UserModel(false, "14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;","14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;",""));
        klaidos.add(new UserModel(false, "14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;","14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;",""));
        klaidos.add(new UserModel(false, "14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.","14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.",""));
        List<UserModel> klaidos1=new ArrayList<>();
        klaidos1.add(new UserModel(false, "15. Sankabos valdymas:","15. Sankabos valdymas:",""));
        klaidos1.add(new UserModel(false, "15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);","15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);",""));
        klaidos1.add(new UserModel(false, "15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;","15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;",""));
        klaidos1.add(new UserModel(false, "15.3. NK – per greitai atleidžia sankabos paminą;","15.3. NK – per greitai atleidžia sankabos paminą;",""));
        klaidos1.add(new UserModel(false, "15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;","15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;",""));
        klaidos1.add(new UserModel(false, "15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);","15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);",""));
        klaidos1.add(new UserModel(false, "15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;","15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;",""));
        klaidos1.add(new UserModel(false, "15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);","15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);",""));
        klaidos1.add(new UserModel(false, "15.8. SPK – šešis kartus užgesina variklį;","15.8. SPK – šešis kartus užgesina variklį;",""));
        klaidos1.add(new UserModel(false, "15.9. SPK – šešis kartus važiuoja išjungta sankaba.","15.9. SPK – šešis kartus važiuoja išjungta sankaba.",""));

        mano_listas.put("13",klaidos);
        mano_listas.put("14",klaidos1);

        return mano_listas;
    }
    public static HashMap<String, List<UserModel>> getData1() {
        HashMap<String, List<UserModel> > mano_listas=new HashMap<>();// va toki tureciau naudoti
        List<UserModel> klaidos=new ArrayList<>();

        klaidos.add(new UserModel(false, "14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;","14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;",""));
        klaidos.add(new UserModel(false, "14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;","14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;",""));
        klaidos.add(new UserModel(false, "14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;","14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;",""));
        klaidos.add(new UserModel(false, "14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.","14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.",""));
        List<UserModel> klaidos1=new ArrayList<>();
        klaidos1.add(new UserModel(false, "15. Sankabos valdymas:","15. Sankabos valdymas:",""));
        klaidos1.add(new UserModel(false, "15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);","15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);",""));
        klaidos1.add(new UserModel(false, "15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;","15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;",""));
        klaidos1.add(new UserModel(false, "15.3. NK – per greitai atleidžia sankabos paminą;","15.3. NK – per greitai atleidžia sankabos paminą;",""));
        klaidos1.add(new UserModel(false, "15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;","15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;",""));
        klaidos1.add(new UserModel(false, "15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);","15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);",""));
        klaidos1.add(new UserModel(false, "15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;","15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;",""));
        klaidos1.add(new UserModel(false, "15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);","15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);",""));
        klaidos1.add(new UserModel(false, "15.8. SPK – šešis kartus užgesina variklį;","15.8. SPK – šešis kartus užgesina variklį;",""));
        klaidos1.add(new UserModel(false, "15.9. SPK – šešis kartus važiuoja išjungta sankaba.","15.9. SPK – šešis kartus važiuoja išjungta sankaba.",""));

        mano_listas.put("13",klaidos);
        mano_listas.put("14",klaidos1);

        return mano_listas;
    }
    public static HashMap<String, List<UserModel>> getData2() {
        HashMap<String, List<UserModel> > mano_listas=new HashMap<>();// va toki tureciau naudoti
        List<UserModel> klaidos=new ArrayList<>();

        klaidos.add(new UserModel(false, "14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;","14.1. NK – naudojasi bet kuriuo papildomu įtaisu netinkamai;",""));
        klaidos.add(new UserModel(false, "14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;","14.2. NK – netinkamai sureguliuotas arba užsegtas saugos diržas;",""));
        klaidos.add(new UserModel(false, "14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;","14.3. NK – veda variklį, pasirinkęs netinkamą pavarą arba neišjungęs sankabos;",""));
        klaidos.add(new UserModel(false, "14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.","14.4. NK – bando antrą kartą užvesti variklį, kai jis jau veikia.",""));
        List<UserModel> klaidos1=new ArrayList<>();
        klaidos1.add(new UserModel(false, "15. Sankabos valdymas:","15. Sankabos valdymas:",""));
        klaidos1.add(new UserModel(false, "15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);","15.1. NK – be reikalo ilgai važiuoja nevisiškai įjungęs sankabą (įskaitant manevrus mažu greičiu);",""));
        klaidos1.add(new UserModel(false, "15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;","15.2. NK – laiko koją ant arba virš sankabos paminos, kai sankaba nejungiama ilgiau kaip penkiolika sekundžių. Ši NK vertinama vieną kartą, važiuojant kelio ruožu (gatve) nuo sankryžos iki sankryžos, kurioje sukama;",""));
        klaidos1.add(new UserModel(false, "15.3. NK – per greitai atleidžia sankabos paminą;","15.3. NK – per greitai atleidžia sankabos paminą;",""));
        klaidos1.add(new UserModel(false, "15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;","15.4. NK – netinkamai valdo sankabos paminą, perjungdamas pavaras;",""));
        klaidos1.add(new UserModel(false, "15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);","15.5. NK (A1, A2, A, B1, B ir BE kategorijos) – važiuodamas mažu greičiu netinkamu būdu valdo sankabos paminą (svirtį);",""));
        klaidos1.add(new UserModel(false, "15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;","15.6. NK – laiko nuspaudęs sankabos paminą, kai pavara nejungiama ilgiau kaip tris sekundes, išskyrus atvejus, kai reikia pradėti važiuoti, sustoti arba manevruoti mažu greičiu;",""));
        klaidos1.add(new UserModel(false, "15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);","15.7. NK – be reikalo užgesina variklį (dėl netinkamo sankabos valdymo, netinkamos pavaros ar pan.);",""));
        klaidos1.add(new UserModel(false, "15.8. SPK – šešis kartus užgesina variklį;","15.8. SPK – šešis kartus užgesina variklį;",""));
        klaidos1.add(new UserModel(false, "15.9. SPK – šešis kartus važiuoja išjungta sankaba.","15.9. SPK – šešis kartus važiuoja išjungta sankaba.",""));

        mano_listas.put("13",klaidos);
        mano_listas.put("14",klaidos1);

        return mano_listas;
    }
}