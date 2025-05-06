import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Domain/luogo_esteso.dart';

class Itinerario{
  String nomeItinerario;
  String nomeUtente;
  Map<String, List<LuogoEsteso>> giorniViaggio;

  Itinerario({required this.nomeItinerario, required this.nomeUtente, required this.giorniViaggio});

  factory Itinerario.fromJson(String nomeIt, String nomeUt, Map<String, dynamic> json) {
    final mappaGiornate = <String, List<LuogoEsteso>>{};

    json.forEach((giorno, listaLuoghi) {
      mappaGiornate[giorno] = (listaLuoghi as List)
          .map((luogoJson) => LuogoEsteso.fromJSON(luogoJson))
          .toList();
    });

    return Itinerario(giorniViaggio: mappaGiornate, nomeItinerario: nomeIt, nomeUtente: nomeUt);
  }

  Map<String, dynamic> toJson() {
    return giorniViaggio.map((giorno, listaLuoghi) => MapEntry(
          giorno,
          listaLuoghi.map((luogo) => luogo.toJson()).toList(),
        ));
  }


  String getCity() {
    for (var listaLuoghi in giorniViaggio.values) {
      for (var luogoEsteso in listaLuoghi) {
        final city = luogoEsteso.luogo.city;
        if (city.toLowerCase() != 'default') {
          return city;
        }
      }
    }
    return "Città non trovata";
  }

  List<Luogo> getLuoghi(){
    List<Luogo> luoghiSel=[];
    for(var entry in giorniViaggio.entries){
      //per ogni giornata estraggo la lista di luoghi estesi visitati
      List<LuogoEsteso> luoghiEstesi=entry.value;

      //inserisco tutti gli elementi delle liste estese in una lista di luoghi selezionati per tornare alla pagina requisiti
      for (int j = 1; j < luoghiEstesi.length - 1; j++) {
        luoghiSel.add(luoghiEstesi[j].luogo);
      }
    }
    return luoghiSel;
  }
  
}
