import 'package:smart_trip_app/Domain/luogo_esteso.dart';

class Itinerario{
  Map<String, List<LuogoEsteso>> giorniViaggio;

  Itinerario({required this.giorniViaggio});

  factory Itinerario.fromJson(Map<String, dynamic> json) {
    final mappaGiornate = <String, List<LuogoEsteso>>{};

    json.forEach((giorno, listaLuoghi) {
      mappaGiornate[giorno] = (listaLuoghi as List)
          .map((luogoJson) => LuogoEsteso.fromJSON(luogoJson))
          .toList();
    });

    return Itinerario(giorniViaggio: mappaGiornate);
  }

  Map<String, dynamic> toJson() {
    return giorniViaggio.map((giorno, listaLuoghi) => MapEntry(
          giorno,
          listaLuoghi.map((luogo) => luogo.toJson()).toList(),
        ));
  }

  
}
