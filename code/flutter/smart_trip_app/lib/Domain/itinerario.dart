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

  
}
