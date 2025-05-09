import 'dart:convert';
import 'package:http/http.dart' as http;

//DA SISTEMARE 
Future<List<String>> retrieveUserMaps(String userName,String indirizzo) async{
  String apiUrl='http://$indirizzo/itinerari/mappe/$userName';

  var url = Uri.parse(apiUrl);

   final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    }
  );

  List<String> mapNames=[];

  if (response.statusCode >= 200 && response.statusCode<300) {
    final responseBody = jsonDecode(response.body);
    mapNames=List<String>.from(responseBody);
    return mapNames;
    
  } else {
    return mapNames;
  }
}
