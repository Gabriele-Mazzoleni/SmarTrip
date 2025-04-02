import 'package:smart_trip_app/Domain/user.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:crypto/crypto.dart';


//metodo per l'hashing delle password
String hashPassword(String password) {
  var bytes = utf8.encode(password); // Converti la password in byte
  var digest = sha256.convert(bytes); // Applica l'hashing SHA-256
  return digest.toString(); // Ritorna l'hash come stringa esadecimale
}

//metodo per la ricerca dei dati durante il login
Future<User> userSearcher(String username, String password, String indirizzo) async {
  String apiUrl='http://$indirizzo/utenti/login';
  var url = Uri.parse(apiUrl);
  String psw= hashPassword(password); //convertiamo la password in stringa hash

  final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, Object>{
      'username': username,
      'password': psw,
    }),
  );
  
  if (response.statusCode == 200) { //se tutto va bene accedo all'app con i dati che ho inserito
    var user= User(
      username: username,
      password: password,
    );
    return user;
  } else {
    throw Exception('User not found');
  }
}


//metodo per l'inserimento dei dati durante il signin
Future<User> userAdder(String username, String password, String indirizzo) async {
  String apiUrl='http://$indirizzo/utenti/signin';
  var url = Uri.parse(apiUrl);  
  String psw= hashPassword(password); //convertiamo la password in stringa hash

   final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, Object>{
      
      'username': username,
      'password': psw,
    }),
  );

  if (response.statusCode >= 200 && response.statusCode<300) {
    var user= User(
      username: username,
      password: password,
    );
    return user;
    
  } else {
    throw Exception('Invalid credentials');
  }
}