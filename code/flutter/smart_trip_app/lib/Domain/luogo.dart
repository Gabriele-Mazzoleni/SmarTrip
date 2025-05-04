class Luogo{
  final String nome;
  final double latitudine;
  final double longitudine;
  final String city;
  final String indirizzo;
  final String tipo;
  final int tempoVisita;
  final String immagine;

   Luogo({
    required this.nome,
    required this.latitudine, 
    required this.longitudine,
    required this.city,
    required this.indirizzo,
    required this.tipo,
    required this.tempoVisita,
    required this.immagine,
  });

  //metodo di decoding
  Luogo.fromJSON(Map<String, dynamic> jsonMap) :
    nome=(jsonMap['nome'] ?? ""),
    latitudine=(jsonMap['latitudine'] ?? ""),
    longitudine=(jsonMap['longitudine'] ?? ""),
    city=(jsonMap['citta'] ?? ""),
    indirizzo=(jsonMap['indirizzo'] ?? ""),
    tipo=(jsonMap['tipo'] ?? ""),
    tempoVisita=(jsonMap['tempoDiVisita'] ?? ""),
    immagine=(jsonMap['immagine'] ?? "");


    //metodo di encoding
    Map<String, dynamic> toJson() {
    return {
      'nome': nome,
      'latitudine': latitudine,
      'longitudine': longitudine,
      'citta': city,
      'indirizzo': indirizzo,
      'tipo': tipo,
      'tempoDiVisita': tempoVisita,
      'immagine': immagine
    };
  }


      @override
String toString() {
  return '''
  Luogo(
  nome: $nome,
  latitudine: $latitudine,
  longitudine: $longitudine,
  città: $city,
  indirizzo: $indirizzo,
  tipo: $tipo,
  tempoDiVisita: $tempoVisita
)
''';
}

}