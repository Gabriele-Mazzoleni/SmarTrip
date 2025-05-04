import 'package:smart_trip_app/Domain/giornata.dart';
import 'package:smart_trip_app/Domain/luogo.dart';

class Mappa{
  final String nomeUtente;
  final String idMappa;
  final double latAlloggio;
  final double longAlloggio;
  final int numGiorni;
  final double velMedia;
  List<Giornata> giornate;
  final List<Luogo> luoghi;


  Mappa({
    required this.nomeUtente, 
    required this.idMappa,
    required this.latAlloggio,
    required this.longAlloggio,
    required this.numGiorni,
    required this.velMedia,
    required this.giornate,
    required this.luoghi,
    });
  

    //metodo di encoding
    Map<String, dynamic> toJson() {
    return {
      'nomeMappa': idMappa,
      'utente': nomeUtente,
      'latA':latAlloggio,
      'lonA':longAlloggio,
      'nGiorni':numGiorni,
      'velocitaMedia':velMedia,
      'giorni':giornate.map((g)=>g.toJson()).toList(),
      'luoghi':luoghi.map((l)=>l.toJson()).toList(),

    };
  }

  @override
String toString() {
  return '''
  Mappa(
  nomeUtente: $nomeUtente,
  idMappa: $idMappa,
  latAlloggio: $latAlloggio,
  longAlloggio: $longAlloggio,
  numGiorni: $numGiorni,
  velMedia: $velMedia,
  giornate: [
    ${giornate.map((g) => g.toString()).join(',\n    ')}
  ],
  luoghi: [
    ${luoghi.map((l) => l.toString()).join(',\n    ')}
  ]
)
''';
}

}