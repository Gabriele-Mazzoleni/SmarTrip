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
  
}